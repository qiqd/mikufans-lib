package org.mikufans.parser;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.mongodb.lang.Nullable;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.mikufans.entity.Animation;
import org.mikufans.entity.base.Image;
import org.mikufans.entity.base.Item;
import org.mikufans.entity.base.Person;
import org.mikufans.entity.base.RatingInfo;
import org.mikufans.entity.enumeration.StaffType;
import org.mikufans.util.StringUtil;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Component
@Slf4j
public class DoubanParse {
  @Nullable
  private static StaffType getStaffType(String role) {
    StaffType type = null;
    if (role.contains("Director")) {
      type = StaffType.DIRECTOR;
    }
    if (role.contains("Voice")) {
      type = StaffType.ACTOR;
    }
    if (role.contains("Writer") || role.contains("原著作者")) {
      type = StaffType.WRITER;
    }
    if (role.contains("Music") || role.contains("作曲") || role.contains("作词")) {
      type = StaffType.MUSICIAN;
    }
    if (role.contains("Animator") || role.contains("动画师") || role.contains("监督")) {
      type = StaffType.ANIMATOR;
    }
    if (role.contains("Producer")) {
      type = StaffType.PRODUCER;
    }
    return type;
  }

  public List<Item> getId(String html, String title) {
    Element body = Jsoup.parse(html).body();
    List<Element> data = body.getElementsByTag("script").stream().filter(script -> script.data().contains("window.__DATA__")).toList();
    String script = data.get(0).data();
    script = script.substring(script.indexOf("["), script.lastIndexOf("]") + 1);
    return JSON.parseObject(script, new TypeReference<>() {
    });
  }

  /**
   * 获取动画详情
   *
   * @param html 动画详情HTML
   * @return no id and staff info
   * @throws Exception 解析异常
   */
  public Animation getDetail(String html) throws Exception {
    if (html.contains("搜索访问太频繁")) {
      log.error("=========搜索访问太频繁========");
    }
    Element body = Jsoup.parse(html).body();
    String title = body.select("span[property=\"v:itemreviewed\"]").text();
    String cover = body.select("a.nbgnbg img").attr("src");
    List<String> genre = body.select("span[property=\"v:genre\"]").eachText();
    Elements span = body.select("span.pl");
    String releaseDate = body.select("span[property=\"v:initialReleaseDate\"]").text();
    if (releaseDate.contains("(")) {
      releaseDate = releaseDate.substring(0, releaseDate.indexOf("("));
    }
    String country = span.size() >= 5 ? String.valueOf(span.get(4).nextSibling()) : "";
    String language = span.size() >= 6 ? String.valueOf(span.get(5).nextSibling()) : "";
    String totalEpisode = span.size() >= 8 ? String.valueOf(span.get(7).nextSibling()) : "";
    String duration = span.size() >= 9 ? String.valueOf(span.get(8).nextSibling()) : "";
    String otherTitle = span.size() >= 10 ? String.valueOf(span.get(9).nextSibling()) : "";
    String rating = body.select("strong[property=\"v:average\"]").text();
    String ratingCount = body.select("span[property=\"v:votes\"]").text();
    if (duration.isBlank()) {
      duration = body.select("span[property=\"v:runtime\"]").text();
    }
    String summary = body.select("span[property=\"v:summary\"]").html().replaceAll("<br/>", "\r\n");
    Map<String, String> titles = StringUtil.separateTitles(title);
    List<Element> scripts = body.getElementsByTag("script").stream().filter(script -> script.data().contains("SERIES_OTHER_SUBJECTS")).toList();
    Elements ratingInfoBox = body.select("div.ratings-on-weight .item");
    String fiveStar = ratingInfoBox.isEmpty() ? "" : ratingInfoBox.get(0).select("span.rating_per").text();
    String fourStar = ratingInfoBox.size() >= 2 ? ratingInfoBox.get(1).select("span.rating_per").text() : "";
    String threeStar = ratingInfoBox.size() >= 3 ? ratingInfoBox.get(2).select("span.rating_per").text() : "";
    String twoStar = ratingInfoBox.size() >= 4 ? ratingInfoBox.get(3).select("span.rating_per").text() : "";
    String oneStar = ratingInfoBox.size() >= 5 ? ratingInfoBox.get(4).select("span.rating_per").text() : "";
    RatingInfo ratingInfo = new RatingInfo(rating, ratingCount, fiveStar, fourStar, threeStar, twoStar, oneStar);
    Animation animation = Animation.builder().titleCn(titles.get("titleCn")).title(titles.get("title")).image(Image.builder().small(cover).build()).genres(genre).country(country).language(language).episodeCount(totalEpisode.trim()).duration(duration.trim()).ratingInfo(ratingInfo).summary(summary).dataSource("douban").build();
    if (parsePartialDate(releaseDate) != null) {
      animation.setReleaseDate(parsePartialDate(releaseDate));
    }
    String[] s = otherTitle.replaceAll(" ", "").split("/");
    animation.setOtherTitle(Arrays.stream(s).toList());
    if (!scripts.isEmpty()) {
      Pattern pattern = Pattern.compile("SERIES_OTHER_SUBJECTS\\s*:\\s*(\\[[\\s\\S]*?]),");
      Matcher matcher = pattern.matcher(scripts.get(0).data());
      if (matcher.find()) {
        String seriesArray = matcher.group(1);
        List<Map<String, String>> series = JSON.parseObject(seriesArray, new TypeReference<>() {
        });
        List<Animation> animations = series.stream().map(item -> Animation.builder().id(item.get("id")).ratingInfo(RatingInfo.builder().rating(item.get("rating")).build()).titleCn(item.get("title")).image(Image.builder().small(item.get("pic")).build()).build()).collect(Collectors.toList());
        animation.setSeries(animations);
      }
    }
    return animation;
  }

  public Animation getStaff(String html) {
    Element body = Jsoup.parse(html).body();
    List<Person> personList = body.select("li.celebrity").stream().map(item -> {
      String style = item.select("div.avatar").attr("style");
      String cover = style.substring(style.indexOf("(") + 1, style.lastIndexOf(")"));
      String nameCn = item.select("span.name").text();
      String personId = item.select("span.name a").attr("href");
      // 使用正则表达式提取其中的数字ID
      Pattern pattern = Pattern.compile("/(\\d+)/");
      Matcher matcher = pattern.matcher(personId);
      if (matcher.find()) {
        personId = matcher.group(1);
      }
      String role = item.select("span.role").text();
      StaffType type = getStaffType(role);
      List<String> works = item.select("span.works a").stream().map(Element::text).collect(Collectors.toList());
      return Person.builder().id(personId).nameCn(nameCn).role(role).type(type).image(Image.builder().small(cover).build()).works(works).build();
    }).collect(Collectors.toList());
    Map<StaffType, List<Person>> map = personList.stream().filter(person -> person.getType() != null).collect(Collectors.groupingBy(Person::getType));
    return Animation.builder().actor(map.get(StaffType.ACTOR)).animator(map.get(StaffType.ANIMATOR)).director(map.get(StaffType.DIRECTOR)).producer(map.get(StaffType.PRODUCER)).musician(map.get(StaffType.MUSICIAN)).writer(map.get(StaffType.WRITER)).build();
  }

  private LocalDate parsePartialDate(String dateStr) {
    if (dateStr == null || dateStr.trim().isEmpty()) {
      return null;
    }

    String trimmedDate = dateStr.trim();

    // 处理只有年份的情况，如 "2030"
    if (trimmedDate.matches("\\d{4}")) {
      return LocalDate.of(Integer.parseInt(trimmedDate), 1, 1);
    }

    // 处理年月格式，如 "2030-05"
    if (trimmedDate.matches("\\d{4}-\\d{2}")) {
      return YearMonth.parse(trimmedDate).atDay(1);
    }

    // 完整日期格式直接解析
    try {
      return LocalDate.parse(trimmedDate);
    } catch (DateTimeParseException e) {
      // 如果都失败，则返回null或默认日期
      return null;
    }
  }

}
