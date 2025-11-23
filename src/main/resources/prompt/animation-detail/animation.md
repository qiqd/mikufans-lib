## 请以包括但不限于【东京电视台、NHK、AT-X、WOWOW、AniDB、MyAnimeList、日本动画年鉴及官方等放送记录】为基础，完整、系统、无删减地整理整理 2007年 在日本放送的动画番剧列表，并按照季度分批次整理，每个季度最多100个作品，最少15个作品，不够15个作品的季度，也请尽量包含在内；此外不能压缩字段，不能无中生有，不能选取部分作品作为代表展示，要求完整的返回，此外为保证结果完整准确(防止上下文token限制而导致的结果不完整或者压缩字段)，按照多次返回完整季度数据(比如第一季度第一批，第一季度第二批等等)，每次返回15条数据，返回这15条数据之后询问我是否继续下一个批次，直到这一季度结束为止。返回的格式如下：

```json
[
  {
    "id": "1",
    "title": "鬼灭之刃",
    "originalTitle": "鬼滅の刃",
    "englishTitle": "Demon Slayer",
    "otherTitle": "Demon Slayer",
    "description": "大正时期，主人公炭治郎的家人被鬼杀害...",
    "releaseDate": "",
    "country": "日本",
    "language": "日语",
    "status": "COMPLETED",
    "image": {
      "small": "https://example.com/small.jpg",
      "medium": "https://example.com/medium.jpg",
      "large": "https://example.com/large.jpg"
    },
    "officialWebsite": "https://example.com",
    "averageRating": 9.5,
    "totalViews": 1000000,
    "studioOrAuthor": "Ufotable",
    "ageRatingSystem": "CERO",
    "ageRatingCode": "A",
    "ageRatingMinAge": 12,
    "ageRatingDescription": "对12岁以上用户推荐",
    "createdAt": "",
    "updatedAt": "",
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
    "mainVoiceActors": [
      "花江夏树",
      "鬼头明里",
      "下野纮"
    ],
    "sourceMaterial": "漫画改编"
  }
]
```

**请求参数**:

| 参数名称                             | 参数说明                                        | 请求类型 | 是否必须  | 数据类型              | schema    |
|----------------------------------|---------------------------------------------|------|-------|-------------------|-----------|
| animations                       | 动画信息                                        | body | true  | array             | Animation |
| &emsp;&emsp;id                   | 主键ID                                        |      | false | string            |           |
| &emsp;&emsp;title                | 作品名称（中文名）                                   |      | false | string            |           |
| &emsp;&emsp;originalTitle        | 原名（如日文名）                                    |      | false | string            |           |
| &emsp;&emsp;englishTitle         | 英文名称                                        |      | false | string            |           |
| &emsp;&emsp;otherTitle           | 其他名称                                        |      | false | array             | string    |
| &emsp;&emsp;description          | 作品简介                                        |      | false | string            |           |
| &emsp;&emsp;releaseDate          | 发布日期                                        |      | false | string(date-time) |           |
| &emsp;&emsp;country              | 制作/开发国家/地区                                  |      | false | string            |           |
| &emsp;&emsp;language             | 原始语言                                        |      | false | string            |           |
| &emsp;&emsp;status               | 作品状态,可用值:ONGOING,COMPLETED,CANCELLED,HIATUS |      | false | string            |           |
| &emsp;&emsp;image                |                                             |      | false | Image             | Image     |
| &emsp;&emsp;&emsp;&emsp;small    | 小尺寸图片                                       |      | false | string            |           |
| &emsp;&emsp;&emsp;&emsp;medium   | 中尺寸图片                                       |      | false | string            |           |
| &emsp;&emsp;&emsp;&emsp;large    | 大尺寸图片                                       |      | false | string            |           |
| &emsp;&emsp;officialWebsite      | 官方网站链接                                      |      | false | string            |           |
| &emsp;&emsp;averageRating        | 平均评分,可用值:                                   |      | false | number(float)     |           |
| &emsp;&emsp;totalViews           | 总观看/阅读/浏览次数                                 |      | false | integer(int64)    |           |
| &emsp;&emsp;studioOrAuthor       | 制作公司/作者/开发商                                 |      | false | string            |           |
| &emsp;&emsp;ageRatingSystem      | 评级系统                                        |      | false | string            |           |
| &emsp;&emsp;ageRatingCode        | 评级代码                                        |      | false | string            |           |
| &emsp;&emsp;ageRatingMinAge      | 建议最小年龄                                      |      | false | integer(int32)    |           |
| &emsp;&emsp;ageRatingDescription | 评级说明                                        |      | false | string            |           |
| &emsp;&emsp;createdAt            | 这条数据的创建时间                                   |      | false | string(date-time) |           |
| &emsp;&emsp;updatedAt            | 这条数据的最后更新时间                                 |      | false | string(date-time) |           |
| &emsp;&emsp;genres               | 分类                                          |      | false | array             | string    |
| &emsp;&emsp;singleUpdateTime     | 单集更新时间                                      |      | false | string            |           |
| &emsp;&emsp;episodeCount         | 总集数                                         |      | false | integer(int32)    |           |
| &emsp;&emsp;durationPerEpisode   | 单集时长（分钟）                                    |      | false | integer(int32)    |           |
| &emsp;&emsp;broadcastPlatform    | 播放平台                                        |      | false | string            |           |
| &emsp;&emsp;startDate            | 开播日期                                        |      | false | string(date)      |           |
| &emsp;&emsp;endDate              | 完结日期                                        |      | false | string(date)      |           |
| &emsp;&emsp;directors            | 导演                                          |      | false | array             | string    |
| &emsp;&emsp;scriptWriters        | 编剧                                          |      | false | array             | string    |
| &emsp;&emsp;musicComposers       | 音乐制作, 多个音乐制作人员之间用逗号分隔                       |      | false | array             | string    |
| &emsp;&emsp;animationStudio      | 动画制作公司                                      |      | false | string            |           |
| &emsp;&emsp;mainVoiceActors      | 主要声优列表, 多个声优之间用逗号分隔                         |      | false | array             | string    |
| &emsp;&emsp;sourceMaterial       | 改编来源                                        |      | false | string            |           |
