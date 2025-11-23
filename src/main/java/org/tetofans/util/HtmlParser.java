package org.tetofans.util;

import com.alibaba.fastjson.JSON;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.mikufans.service.playwright.WebScraperService;
import org.springframework.stereotype.Component;
import org.tetofans.entity.baike.PageData;
import org.tetofans.enumeration.AcgType;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@Component
@RequiredArgsConstructor
public class HtmlParser {
  private final static String BASE_URL = "https://baike.baidu.com";
  private final WebScraperService webScraperService;

  private PageData parsePageDate(Element body) {
    Element pageDataElement = body.getElementsByTag("script").stream().filter(script -> script.data().contains("window.PAGE_DATA")).toList().get(0);
    String data = pageDataElement.data();
    data = data.substring(data.indexOf("{"), data.lastIndexOf("}") + 1);
    return JSON.parseObject(data, PageData.class);
  }

  public String parseCoverUrl(String keyword, AcgType acgType) throws Exception {
    Thread.sleep((long) (Math.random() * 5000));
    String fullUrl = BASE_URL + "/lemma/api/entry?word=" + keyword;
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
//    Document html = HttpUtil.createConnection(fullUrl).get();
    String s = webScraperService.fetchRenderedHtml(fullUrl);
    Document html = Jsoup.parse(s);
    if (html.select("div#root").html().isBlank()) {
      log.warn("page not found,keyword: {}", keyword);
      return "";
    }
    String title = html.getElementsByTag("title").get(0).text();
    Pattern urlPattern = Pattern.compile("\"url\"\\s*:\\s*\"([^\"]+)\"");
    PageData pageData = parsePageDate(html);
    if (!title.contains(acgType.getValue())) {
      Map<String, Object> navigation = pageData.getNavigation();
      if (navigation == null) {
        log.warn("navigation not found,keyword: {}", keyword);
        return "";
      }
      Object lemmas = navigation.get("lemmas");
      if (lemmas == null) {
        log.warn("lemmas not found,keyword: {}", keyword);
        return "";
      }
      for (Map<String, ?> lemma : (List<Map<String, ?>>) lemmas) {
        String lemmaDesc = (String) lemma.get("lemmaDesc");
        if (lemmaDesc.contains(acgType.getValue())) {
          String lemmaId = lemma.get("lemmaId").toString();
          String lemmaTitle = (String) lemma.get("lemmaTitle");
          String url = BASE_URL + "/item/" + lemmaTitle + "/" + lemmaId;
          try {
            Thread.sleep((long) (Math.random() * 5000));
          } catch (InterruptedException e) {
            throw new RuntimeException(e);
          }
//          Element body2 = HttpUtil.createConnection(url).get().body();
          String s1 = webScraperService.fetchRenderedHtml(url);
          Document html21 = Jsoup.parse(s1);
          pageData = parsePageDate(html21);
          break;
        }
      }
    }
    Map<String, Object> abstractAlbum = pageData.getAbstractAlbum();
    String coverPic = String.valueOf(abstractAlbum.get("coverPic"));
    Matcher matcher = urlPattern.matcher(coverPic);
    if (!matcher.find()) {
      log.warn("coverPic not found,keyword: {}", keyword);
      return "";
    }
    return matcher.group(1);
  }


  private void parseLightNovel(Element body) {

  }

  private void parseComic(Element body) {

  }


}
