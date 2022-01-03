<!-- vscode-markdown-toc -->
* 1. [查看帖子表](#)
* 2. [查看帖子详情](#-1)
* 3. [获取所有帖子类型](#-1)
* 4. [发布帖子带图片](#-1)
* 5. [帖子点赞与取消点赞](#-1)
* 6. [帖子评论](#-1)
* 7. [帖子回复](#-1)
* 8. [搜索帖子](#-1)
* 9. [查看我的帖子](#-1)

<!-- vscode-markdown-toc-config
	numbering=true
	autoSave=true
	/vscode-markdown-toc-config -->
<!-- /vscode-markdown-toc -->
# 帖子管理
##  1. <a name=''></a>查看帖子表
**URL:** `/news/admin/checkNews`

**Type:** `GET`


**Content-Type:** `application/x-www-form-urlencoded;charset=utf-8`

**Description:** 查看帖子表



**Query-parameters:**

Parameter|Type|Description|Required|Since
---|---|---|---|---
page|int32|页数|true|-
size|int32|每页记录数|true|-


**Request-example:**
```
curl -X GET -i /news/admin/checkNews?size=10&page=1
```
**Response-fields:**

Field | Type|Description|Since
---|---|---|---
success|boolean|成功标志|-
code|int32|状态码|-
msg|string|处理信息|-
data|object|处理数据|-
└─total|int64|No comments found.|-
└─list|array|No comments found.|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─newsid|int32|帖子id|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─userid|int32|发帖人账号|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─username|string|发帖人用户名|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─headimage|string|发帖人头像url|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─newstitle|string|标题|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─content|string|内容|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─sendtime|string|发布时间|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─viewnum|int32|浏览量|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─likenum|int32|点赞量|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─newstypename|string|帖子类型名字|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─imgUrls|array|帖子图像下载url|-
└─pageNum|int32|当前页|-
└─pageSize|int32|每页的数量|-
└─size|int32|当前页的数量|-
└─startRow|int64|由于startRow和endRow不常用，这里说个具体的用法<br>可以在页面中"显示startRow到endRow 共size条数据"<br>当前页面第一个元素在数据库中的行号|-
└─endRow|int64|当前页面最后一个元素在数据库中的行号|-
└─pages|int32|总页数|-
└─prePage|int32|前一页|-
└─nextPage|int32|下一页|-
└─firstPage|boolean|是否为第一页|-
└─lastPage|boolean|是否为最后一页|-
└─hasPreviousPage|boolean|是否有前一页|-
└─hasNextPage|boolean|是否有下一页|-
└─navigatePages|int32|导航页码数|-
└─navigatepageNums|array|所有导航页号|-
└─navigateFirstPage|int32|导航条上的第一页|-
└─navigateLastPage|int32|导航条上的最后一页|-
timestamp|string|时间戳|-

**Response-example:**
```
{
  "success": true,
  "code": 200,
  "msg": "success",
  "data": {
    "total": 533,
    "list": [
      {
        "newsid": 386,
        "userid": 813,
        "username": "delores.schroeder",
        "headimage": "nhg4xk",
        "newstitle": "4xxfom",
        "content": "5tjfch",
        "sendtime": "2021-12-21 00:27:02",
        "viewnum": 834,
        "likenum": 127,
        "newstypename": "delores.schroeder",
        "imgUrls": [
          "zkyxsl"
        ]
      }
    ],
    "pageNum": 51,
    "pageSize": 10,
    "size": 10,
    "startRow": 38,
    "endRow": 619,
    "pages": 474,
    "prePage": 1,
    "nextPage": 1,
    "firstPage": true,
    "lastPage": true,
    "hasPreviousPage": true,
    "hasNextPage": true,
    "navigatePages": 531,
    "navigatepageNums": [
      36
    ],
    "navigateFirstPage": 1,
    "navigateLastPage": 1
  },
  "timestamp": "2021-12-21 00:27:02"
}
```

##  2. <a name='-1'></a>查看帖子详情
**URL:** `/news/view`

**Type:** `GET`


**Content-Type:** `application/x-www-form-urlencoded;charset=utf-8`

**Description:** 查看帖子详情



**Query-parameters:**

Parameter|Type|Description|Required|Since
---|---|---|---|---
newsid|int32|No comments found.|true|-


**Request-example:**
```
curl -X GET -i /news/view?newsid=121
```
**Response-fields:**

Field | Type|Description|Since
---|---|---|---
success|boolean|成功标志|-
code|int32|状态码|-
msg|string|处理信息|-
data|object|处理数据|-
└─newsid|int32|帖子id|-
└─userid|int32|发帖人账号|-
└─username|string|发帖人用户名|-
└─headimage|string|发帖人头像url|-
└─newstitle|string|标题|-
└─content|string|内容|-
└─sendtime|string|发布时间|-
└─viewnum|int32|浏览量|-
└─likenum|int32|点赞量|-
└─newstypename|string|帖子类型名字|-
└─anonymousstate|boolean|发帖人匿名状态|-
└─imgUrls|array|帖子图像下载url|-
└─isLike|boolean|是否点赞|-
└─comments|array|帖子评论|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─commentid|int32|评论id|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─userid|int32|用户账号|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─username|string|用户名|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─headImage|string|用户头像|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─commenttime|string|评论时间|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─content|string|内容|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─showreply|boolean|是否展示回复|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─replynum|int32|回复数|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─replys|array|回复|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─replyid|int32|回复id|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─targetid|int32|目标id|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─commentid|int32|评论id|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─userid|int32|用户账号|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─username|string|用户名|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─headImage|string|头像|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─commenttime|string|评论时间|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─content|string|评论内容|-
timestamp|string|时间戳|-

**Response-example:**
```
{
  "success": true,
  "code": 200,
  "msg": "success",
  "data": {
    "newsid": 506,
    "userid": 256,
    "username": "delores.schroeder",
    "headimage": "nkli1k",
    "newstitle": "woj616",
    "content": "e7opir",
    "sendtime": "2021-12-21 00:27:02",
    "viewnum": 463,
    "likenum": 482,
    "newstypename": "delores.schroeder",
    "anonymousstate": true,
    "imgUrls": [
      "fektfs"
    ],
    "isLike": true,
    "comments": [
      {
        "commentid": 839,
        "userid": 756,
        "username": "delores.schroeder",
        "headImage": "c54m5d",
        "commenttime": "2021-12-21 00:27:02",
        "content": "b98c8u",
        "showreply": true,
        "replynum": 396,
        "replys": [
          {
            "replyid": 228,
            "targetid": 52,
            "commentid": 114,
            "userid": 385,
            "username": "delores.schroeder",
            "headImage": "7peq96",
            "commenttime": "2021-12-21 00:27:02",
            "content": "pkfpz5"
          }
        ]
      }
    ]
  },
  "timestamp": "2021-12-21 00:27:02"
}
```

##  3. <a name='-1'></a>获取所有帖子类型
**URL:** `/news/getNewsType`

**Type:** `GET`


**Content-Type:** `application/x-www-form-urlencoded;charset=utf-8`

**Description:** 获取所有帖子类型





**Request-example:**
```
curl -X GET -i /news/getNewsType
```
**Response-fields:**

Field | Type|Description|Since
---|---|---|---
success|boolean|成功标志|-
code|int32|状态码|-
msg|string|处理信息|-
data|array|处理数据|-
└─typeid|int32|帖子类型编号|-
└─typename|string|帖子类型名|-
timestamp|string|时间戳|-

**Response-example:**
```
{
  "success": true,
  "code": 200,
  "msg": "success",
  "data": [
    {
      "typeid": 856,
      "typename": "delores.schroeder"
    }
  ],
  "timestamp": "2021-12-21 00:27:02"
}
```

##  4. <a name='-1'></a>发布帖子带图片
**URL:** `/news/addwithimages`

**Type:** `POST`


**Content-Type:** `multipart/form-data`

**Description:** 发布帖子带图片



**Query-parameters:**

Parameter|Type|Description|Required|Since
---|---|---|---|---
multipartFiles|file|No comments found.(array of file)|true|-
userid|int32|用户账号|false|-
newstitle|string|标题|false|-
content|string|内容|false|-
newstypeid|int32|帖子类型|false|-


**Request-example:**
```
curl -X POST -H 'Content-Type: multipart/form-data' -i /news/addwithimages --data 'content=24jil5&newstypeid=963&newstitle=smw4m1&userid=177'
```
**Response-fields:**

Field | Type|Description|Since
---|---|---|---
success|boolean|成功标志|-
code|int32|状态码|-
msg|string|处理信息|-
data|object|处理数据|-
timestamp|string|时间戳|-

**Response-example:**
```
{
  "success": true,
  "code": 200,
  "msg": "success",
  "data": {
    "waring": "You may have used non-display generics."
  },
  "timestamp": "2021-12-21 00:27:02"
}
```

##  5. <a name='-1'></a>帖子点赞与取消点赞
**URL:** `/news/likenews`

**Type:** `GET`


**Content-Type:** `application/x-www-form-urlencoded;charset=utf-8`

**Description:** 帖子点赞与取消点赞



**Query-parameters:**

Parameter|Type|Description|Required|Since
---|---|---|---|---
newsid|int32|No comments found.|true|-


**Request-example:**
```
curl -X GET -i /news/likenews?newsid=593
```
**Response-fields:**

Field | Type|Description|Since
---|---|---|---
success|boolean|成功标志|-
code|int32|状态码|-
msg|string|处理信息|-
data|array|处理数据|-
timestamp|string|时间戳|-

**Response-example:**
```
{
  "success": true,
  "code": 200,
  "msg": "success",
  "data": [
    183,
    901
  ],
  "timestamp": "2021-12-21 00:27:02"
}
```

##  6. <a name='-1'></a>帖子评论
**URL:** `/news/comment`

**Type:** `POST`


**Content-Type:** `application/json; charset=utf-8`

**Description:** 帖子评论




**Body-parameters:**

Parameter|Type|Description|Required|Since
---|---|---|---|---
newsid|int32|帖子id|false|-
content|string|评论内容|false|-

**Request-example:**
```
curl -X POST -H 'Content-Type: application/json; charset=utf-8' -i /news/comment --data '{
  "newsid": 404,
  "content": "ewbtv7"
}'
```
**Response-fields:**

Field | Type|Description|Since
---|---|---|---
success|boolean|成功标志|-
code|int32|状态码|-
msg|string|处理信息|-
data|object|处理数据|-
└─newsid|int32|帖子id|-
└─userid|int32|发帖人账号|-
└─username|string|发帖人用户名|-
└─headimage|string|发帖人头像url|-
└─newstitle|string|标题|-
└─content|string|内容|-
└─sendtime|string|发布时间|-
└─viewnum|int32|浏览量|-
└─likenum|int32|点赞量|-
└─newstypename|string|帖子类型名字|-
└─anonymousstate|boolean|发帖人匿名状态|-
└─imgUrls|array|帖子图像下载url|-
└─isLike|boolean|是否点赞|-
└─comments|array|帖子评论|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─commentid|int32|评论id|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─userid|int32|用户账号|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─username|string|用户名|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─headImage|string|用户头像|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─commenttime|string|评论时间|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─content|string|内容|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─showreply|boolean|是否展示回复|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─replynum|int32|回复数|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─replys|array|回复|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─replyid|int32|回复id|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─targetid|int32|目标id|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─commentid|int32|评论id|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─userid|int32|用户账号|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─username|string|用户名|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─headImage|string|头像|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─commenttime|string|评论时间|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─content|string|评论内容|-
timestamp|string|时间戳|-

**Response-example:**
```
{
  "success": true,
  "code": 200,
  "msg": "success",
  "data": {
    "newsid": 179,
    "userid": 207,
    "username": "delores.schroeder",
    "headimage": "1ogc0a",
    "newstitle": "9rx1i0",
    "content": "6zrhro",
    "sendtime": "2021-12-21 00:27:02",
    "viewnum": 403,
    "likenum": 64,
    "newstypename": "delores.schroeder",
    "anonymousstate": true,
    "imgUrls": [
      "cbw5vm"
    ],
    "isLike": true,
    "comments": [
      {
        "commentid": 180,
        "userid": 462,
        "username": "delores.schroeder",
        "headImage": "gt7xu6",
        "commenttime": "2021-12-21 00:27:02",
        "content": "kqqo5m",
        "showreply": true,
        "replynum": 360,
        "replys": [
          {
            "replyid": 575,
            "targetid": 482,
            "commentid": 407,
            "userid": 493,
            "username": "delores.schroeder",
            "headImage": "ajgrky",
            "commenttime": "2021-12-21 00:27:02",
            "content": "xix5ei"
          }
        ]
      }
    ]
  },
  "timestamp": "2021-12-21 00:27:02"
}
```

##  7. <a name='-1'></a>帖子回复
**URL:** `/news/reply`

**Type:** `POST`


**Content-Type:** `application/json; charset=utf-8`

**Description:** 帖子回复




**Body-parameters:**

Parameter|Type|Description|Required|Since
---|---|---|---|---
commentid|int32|评论id|false|-
content|string|内容|false|-

**Request-example:**
```
curl -X POST -H 'Content-Type: application/json; charset=utf-8' -i /news/reply --data '{
  "commentid": 933,
  "content": "rk7lqz"
}'
```
**Response-fields:**

Field | Type|Description|Since
---|---|---|---
success|boolean|成功标志|-
code|int32|状态码|-
msg|string|处理信息|-
data|object|处理数据|-
└─newsid|int32|帖子id|-
└─userid|int32|发帖人账号|-
└─username|string|发帖人用户名|-
└─headimage|string|发帖人头像url|-
└─newstitle|string|标题|-
└─content|string|内容|-
└─sendtime|string|发布时间|-
└─viewnum|int32|浏览量|-
└─likenum|int32|点赞量|-
└─newstypename|string|帖子类型名字|-
└─anonymousstate|boolean|发帖人匿名状态|-
└─imgUrls|array|帖子图像下载url|-
└─isLike|boolean|是否点赞|-
└─comments|array|帖子评论|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─commentid|int32|评论id|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─userid|int32|用户账号|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─username|string|用户名|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─headImage|string|用户头像|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─commenttime|string|评论时间|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─content|string|内容|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─showreply|boolean|是否展示回复|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─replynum|int32|回复数|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─replys|array|回复|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─replyid|int32|回复id|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─targetid|int32|目标id|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─commentid|int32|评论id|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─userid|int32|用户账号|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─username|string|用户名|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─headImage|string|头像|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─commenttime|string|评论时间|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─content|string|评论内容|-
timestamp|string|时间戳|-

**Response-example:**
```
{
  "success": true,
  "code": 200,
  "msg": "success",
  "data": {
    "newsid": 856,
    "userid": 364,
    "username": "delores.schroeder",
    "headimage": "iwrq54",
    "newstitle": "nb8vtz",
    "content": "cv4kgz",
    "sendtime": "2021-12-21 00:27:02",
    "viewnum": 708,
    "likenum": 517,
    "newstypename": "delores.schroeder",
    "anonymousstate": true,
    "imgUrls": [
      "gzf5vr"
    ],
    "isLike": true,
    "comments": [
      {
        "commentid": 19,
        "userid": 919,
        "username": "delores.schroeder",
        "headImage": "y5qi85",
        "commenttime": "2021-12-21 00:27:02",
        "content": "0fq5iy",
        "showreply": true,
        "replynum": 302,
        "replys": [
          {
            "replyid": 856,
            "targetid": 425,
            "commentid": 21,
            "userid": 749,
            "username": "delores.schroeder",
            "headImage": "6scmnw",
            "commenttime": "2021-12-21 00:27:02",
            "content": "icoves"
          }
        ]
      }
    ]
  },
  "timestamp": "2021-12-21 00:27:02"
}
```

##  8. <a name='-1'></a>搜索帖子
**URL:** `/news/search`

**Type:** `POST`


**Content-Type:** `application/json; charset=utf-8`

**Description:** 搜索帖子




**Body-parameters:**

Parameter|Type|Description|Required|Since
---|---|---|---|---
key|string|关键词|false|-
sort|int32|排序方式<br>1=按时间排序<br>2=按浏览量排序<br>3=按点赞量排序|false|-
type|string|查找的帖子类型|false|-
pageSize|int32|每页的记录数|false|-
pageNum|int32|当前页数|false|-

**Request-example:**
```
curl -X POST -H 'Content-Type: application/json; charset=utf-8' -i /news/search --data '{
  "key": "饭堂",
  "sort": 1,
  "type": "校园卡丢失",
  "pageSize": 8,
  "pageNum": 1
}'
```
**Response-fields:**

Field | Type|Description|Since
---|---|---|---
success|boolean|成功标志|-
code|int32|状态码|-
msg|string|处理信息|-
data|object|处理数据|-
└─total|int64|No comments found.|-
└─list|array|No comments found.|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─newsid|int32|帖子id|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─userid|int32|发帖人账号|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─username|string|发帖人用户名|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─headimage|string|发帖人头像url|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─newstitle|string|标题|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─content|string|内容|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─sendtime|string|发布时间|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─viewnum|int32|浏览量|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─likenum|int32|点赞量|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─newstypename|string|帖子类型名字|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─imgUrls|array|帖子图像下载url|-
└─pageNum|int32|当前页|-
└─pageSize|int32|每页的数量|-
└─size|int32|当前页的数量|-
└─startRow|int64|由于startRow和endRow不常用，这里说个具体的用法<br>可以在页面中"显示startRow到endRow 共size条数据"<br>当前页面第一个元素在数据库中的行号|-
└─endRow|int64|当前页面最后一个元素在数据库中的行号|-
└─pages|int32|总页数|-
└─prePage|int32|前一页|-
└─nextPage|int32|下一页|-
└─firstPage|boolean|是否为第一页|-
└─lastPage|boolean|是否为最后一页|-
└─hasPreviousPage|boolean|是否有前一页|-
└─hasNextPage|boolean|是否有下一页|-
└─navigatePages|int32|导航页码数|-
└─navigatepageNums|array|所有导航页号|-
└─navigateFirstPage|int32|导航条上的第一页|-
└─navigateLastPage|int32|导航条上的最后一页|-
timestamp|string|时间戳|-

**Response-example:**
```
{
  "success": true,
  "code": 200,
  "msg": "success",
  "data": {
    "total": 597,
    "list": [
      {
        "newsid": 653,
        "userid": 496,
        "username": "delores.schroeder",
        "headimage": "a8dcqa",
        "newstitle": "cbl4pw",
        "content": "6jupjo",
        "sendtime": "2021-12-21 00:27:02",
        "viewnum": 483,
        "likenum": 293,
        "newstypename": "delores.schroeder",
        "imgUrls": [
          "nkjp7x"
        ]
      }
    ],
    "pageNum": 252,
    "pageSize": 10,
    "size": 10,
    "startRow": 124,
    "endRow": 560,
    "pages": 743,
    "prePage": 1,
    "nextPage": 1,
    "firstPage": true,
    "lastPage": true,
    "hasPreviousPage": true,
    "hasNextPage": true,
    "navigatePages": 890,
    "navigatepageNums": [
      13
    ],
    "navigateFirstPage": 1,
    "navigateLastPage": 1
  },
  "timestamp": "2021-12-21 00:27:02"
}
```

##  9. <a name='-1'></a>查看我的帖子
**URL:** `/news/mynews`

**Type:** `GET`


**Content-Type:** `application/x-www-form-urlencoded;charset=utf-8`

**Description:** 查看我的帖子



**Query-parameters:**

Parameter|Type|Description|Required|Since
---|---|---|---|---
page|int32|No comments found.|true|-
size|int32|No comments found.|true|-


**Request-example:**
```
curl -X GET -i /news/mynews?size=10&page=1
```
**Response-fields:**

Field | Type|Description|Since
---|---|---|---
success|boolean|成功标志|-
code|int32|状态码|-
msg|string|处理信息|-
data|object|处理数据|-
└─total|int64|No comments found.|-
└─list|array|No comments found.|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─newsid|int32|帖子id|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─userid|int32|发帖人账号|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─username|string|发帖人用户名|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─headimage|string|发帖人头像url|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─newstitle|string|标题|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─content|string|内容|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─sendtime|string|发布时间|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─viewnum|int32|浏览量|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─likenum|int32|点赞量|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─newstypename|string|帖子类型名字|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─imgUrls|array|帖子图像下载url|-
└─pageNum|int32|当前页|-
└─pageSize|int32|每页的数量|-
└─size|int32|当前页的数量|-
└─startRow|int64|由于startRow和endRow不常用，这里说个具体的用法<br>可以在页面中"显示startRow到endRow 共size条数据"<br>当前页面第一个元素在数据库中的行号|-
└─endRow|int64|当前页面最后一个元素在数据库中的行号|-
└─pages|int32|总页数|-
└─prePage|int32|前一页|-
└─nextPage|int32|下一页|-
└─firstPage|boolean|是否为第一页|-
└─lastPage|boolean|是否为最后一页|-
└─hasPreviousPage|boolean|是否有前一页|-
└─hasNextPage|boolean|是否有下一页|-
└─navigatePages|int32|导航页码数|-
└─navigatepageNums|array|所有导航页号|-
└─navigateFirstPage|int32|导航条上的第一页|-
└─navigateLastPage|int32|导航条上的最后一页|-
timestamp|string|时间戳|-

**Response-example:**
```
{
  "success": true,
  "code": 200,
  "msg": "success",
  "data": {
    "total": 338,
    "list": [
      {
        "newsid": 66,
        "userid": 842,
        "username": "delores.schroeder",
        "headimage": "wpupl1",
        "newstitle": "9ablr2",
        "content": "jh7mmk",
        "sendtime": "2021-12-21 00:27:02",
        "viewnum": 150,
        "likenum": 165,
        "newstypename": "delores.schroeder",
        "imgUrls": [
          "736qda"
        ]
      }
    ],
    "pageNum": 63,
    "pageSize": 10,
    "size": 10,
    "startRow": 613,
    "endRow": 363,
    "pages": 612,
    "prePage": 1,
    "nextPage": 1,
    "firstPage": true,
    "lastPage": true,
    "hasPreviousPage": true,
    "hasNextPage": true,
    "navigatePages": 936,
    "navigatepageNums": [
      810
    ],
    "navigateFirstPage": 1,
    "navigateLastPage": 1
  },
  "timestamp": "2021-12-21 00:27:02"
}
```

