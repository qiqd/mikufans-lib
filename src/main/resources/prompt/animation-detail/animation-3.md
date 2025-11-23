# ai prompt

## Q1: 请统计 2024 全年日本放送的所有新番，统计范围需包含：全新 TV 番剧、TV 番剧续作、番外篇、特别篇、公开放送的 OVA（含网络平台公开的 OVA）、泡面番；需排除非 2024 年首次放送的重播番剧。请按 1 - 12 月的月份顺序逐一整理，每个月份的结果需包含番剧的中文名称、日文名称以及放送时间（精确到具体日期，无明确日期的标注为 “2024 年 X 月”）。若番剧存在延期、复播等特殊情况，需额外标注备注。最后统计 2024 全年符合上述要求的日本新番总数量。

## Q2(侧重日本官方及国际权威版): 请统计 2024 全年日本放送的所有新番，统计范围需包含：全新 TV 番剧、TV 番剧续作、番外篇、特别篇、公开放送的 OVA（含网络平台公开的 OVA）、泡面番；需排除非 2024 年首次放送的重播番剧。请按 1 - 12 月的月份顺序逐一整理，每个月份的结果需包含番剧的中文名称、日文名称以及放送时间（精确到具体日期，无明确日期的标注为 “2024 年 X 月”）。若番剧存在延期、复播等特殊情况，需额外标注备注。数据需优先采集自日本官方渠道（如 MBS、东京电视台等动漫播出主流电视台官网、MAPPA、京都动画等制作公司官宣信息、《Newtype》杂志公布的番剧清单），补充参考国际权威动漫数据库（如 AniDB、MyAnimeList）；最后统计总数量，并备注各番剧信息对应的权威来源（如某电视台官网、某数据库）。

## Q3(侧重国内权威平台版):Q: 请统计 2024 全年日本放送的所有新番，统计范围需包含：全新 TV 番剧、TV 番剧续作、番外篇、特别篇、公开放送的 OVA（含网络平台公开的 OVA）、泡面番；需排除非 2024 年首次放送的重播番剧。请按 1 - 12 月的月份顺序逐一整理，每个月份的结果需包含番剧的中文名称、日文名称以及放送时间（精确到具体日期，无明确日期的标注为 “2024 年 X 月”）。若番剧存在延期、复播等特殊情况，需额外标注备注。数据需来源于国内权威二次元平台，包括番组百科（anibk.com）、哔哩哔哩番剧索引专区；最后统计 2024 全年符合上述要求的日本新番总数量，并在文末注明每部番剧对应的信息来源平台名称。

## Q3(综合多权威来源核验版):Q: 请统计 2023 全年日本放送的所有新番，统计范围需包含：全新 TV 番剧、TV 番剧续作、番外篇、特别篇、公开放送的 OVA（含网络平台公开的 OVA）、泡面番；需排除非 2023 年首次放送的重播番剧。请按 1 - 12 月的月份顺序逐一整理，每个月份的结果需包含番剧的中文名称、日文名称以及放送时间（精确到具体日期，无明确日期的标注为 “2023 年 X 月”）。若番剧存在延期、复播等特殊情况，需额外标注备注。数据需通过多权威渠道交叉核验获取，国内以萌娘百科、Bangumi 番组计划、蜜柑计划 - Mikan Project、番组百科、B 站番剧专题页为准，日本本土以主流播出电视台官网、制作委员会官宣为准，国际层面参考 AniDB、MyAnimeList 补充完善；若不同渠道信息存在差异，需标注差异内容并以日本官方渠道信息为准。最后统计符合要求的新番总数量，并附上各渠道信息交叉核验的简要说明。

## Q4(综合多权威来源no核验版):Q: 请统计 2023 全年日本放送的所有新番，统计范围需包含：全新 TV 番剧、TV 番剧续作、番外篇、特别篇、公开放送的 OVA（含网络平台公开的 OVA）、泡面番；需排除非 2023 年首次放送的重播番剧。请按 1 - 12 月的月份顺序逐一整理，每个月份的结果需包含番剧的中文名称、日文名称以及放送时间（精确到具体日期，无明确日期的标注为 “2023 年 X 月”）。若番剧存在延期、复播等特殊情况，需额外标注备注。国内以萌娘百科、Bangumi 番组计划、蜜柑计划 - Mikan Project、番组百科、B 站番剧专题页为准，日本本土以主流播出电视台官网、制作委员会官宣为准，国际层面参考 AniDB、MyAnimeList 补充完善；若不同渠道信息存在差异，需标注差异内容并以日本官方渠道信息为准。最后统计符合要求的新番总数量。
---

## Q:  好的，现在基于你刚才的最新的回答，按照下面的格式帮我整理这些番剧信息，尽可能每个字段都有值(如果单个网站没有提供某个字段，试试从其他网站获取)，如果不能全部返回的话，就按照批次返回，注意，对于你刚才回答的每一部番剧，都要按照下面的格式返回：

### **格式如下**

```json
[
  {
    "title": "鬼灭之刃",
    "originalTitle": "鬼滅の刃",
    "englishTitle": "Demon Slayer",
    "otherTitle": "Demon Slayer",
    "description": "大正时期，主人公炭治郎的家人被鬼杀害...",
    "releaseDate": "",
    "country": "日本",
    "language": "日语",
    "status": "COMPLETED",
    "officialWebsite": "https://example.com",
    "averageRating": 9.5,
    "ratingCount": 1000000,
    "totalViews": 1000000,
    "studioOrAuthor": "Ufotable",
    "ageRatingSystem": "CERO",
    "ageRatingCode": "A",
    "ageRatingMinAge": 12,
    "ageRatingDescription": "对12岁以上用户推荐",
    "genres": [
      "校园",
      "战斗",
      "爱情"
    ],
    "singleUpdateTime": "每周三20:00",
    "episodeCount": 26,
    "durationPerEpisode": 24,
    "broadcastPlatform": "Bilibili",
    "startDate": "2019-04-06",
    "endDate": "2019-09-28",
    "directors": [
      "新海诚",
      "虚渊玄"
    ],
    "scriptWriters": [
      "虚渊玄",
      "新海诚"
    ],
    "musicComposers": [
      "梶浦由记",
      "新海诚"
    ],
    "animationStudio": "Ufotable",
    "mainVoiceActors": {
      "空条承太郎": "小野大辅",
      "迪奥·布兰度": "子安武人"
    },
    "sourceMaterial": "漫画改编"
  }
]
```

### **参数含义**

| 参数名称                             | 参数说明                                        | 请求类型 | 是否必须  | 数据类型              |  schema   |
|----------------------------------|---------------------------------------------|------|-------|-------------------|:---------:|
| animations                       | 动画信息                                        | body | true  | array             | Animation |
| &emsp;&emsp;title                | 作品名称（中文名）                                   |      | false | string            |           |
| &emsp;&emsp;originalTitle        | 原名（如日文名）                                    |      | false | string            |           |
| &emsp;&emsp;englishTitle         | 英文名称                                        |      | false | string            |           |
| &emsp;&emsp;otherTitle           | 其他名称                                        |      | false | array             |  string   |
| &emsp;&emsp;description          | 作品简介                                        |      | false | string            |           |
| &emsp;&emsp;releaseDate          | 发布日期                                        |      | false | string(date-time) |           |
| &emsp;&emsp;country              | 制作/开发国家/地区                                  |      | false | string            |           |
| &emsp;&emsp;language             | 原始语言                                        |      | false | string            |           |
| &emsp;&emsp;status               | 作品状态,可用值:ONGOING,COMPLETED,CANCELLED,HIATUS |      | false | string            |
| &emsp;&emsp;officialWebsite      | 官方网站链接                                      |      | false | string            |           |
| &emsp;&emsp;averageRating        | 平均评分,可用值:                                   |      | false | number(float)     |           |
| &emsp;&emsp;ratingCount          | 评分人数                                        |      | false | integer(int64)    |           |
| &emsp;&emsp;totalViews           | 总观看/阅读/浏览次数                                 |      | false | integer(int64)    |           |
| &emsp;&emsp;studioOrAuthor       | 制作公司/作者/开发商                                 |      | false | string            |           |
| &emsp;&emsp;ageRatingSystem      | 评级系统                                        |      | false | string            |           |
| &emsp;&emsp;ageRatingCode        | 评级代码                                        |      | false | string            |           |
| &emsp;&emsp;ageRatingMinAge      | 建议最小年龄                                      |      | false | integer(int32)    |           |
| &emsp;&emsp;ageRatingDescription | 评级说明                                        |      | false | string            |           |
| &emsp;&emsp;genres               | 分类                                          |      | false | array             |  string   |
| &emsp;&emsp;singleUpdateTime     | 单集更新时间                                      |      | false | string            |           |
| &emsp;&emsp;episodeCount         | 总集数                                         |      | false | integer(int32)    |           |
| &emsp;&emsp;durationPerEpisode   | 单集时长（分钟）                                    |      | false | integer(int32)    |           |
| &emsp;&emsp;broadcastPlatform    | 播放平台                                        |      | false | string            |           |
| &emsp;&emsp;startDate            | 开播日期                                        |      | false | string(date)      |           |
| &emsp;&emsp;endDate              | 完结日期                                        |      | false | string(date)      |           |
| &emsp;&emsp;directors            | 导演                                          |      | false | array             |  string   |
| &emsp;&emsp;scriptWriters        | 编剧                                          |      | false | array             |  string   |
| &emsp;&emsp;musicComposers       | 音乐制作, 多个音乐制作人员之间用逗号分隔                       |      | false | array             |  string   |
| &emsp;&emsp;animationStudio      | 动画制作公司                                      |      | false | string            |           |
| &emsp;&emsp;mainVoiceActors      | 主要角色以及对应的声优                                 |      | false | object            |           |
| &emsp;&emsp;sourceMaterial       | 改编来源                                        |      | false | string            |           |
