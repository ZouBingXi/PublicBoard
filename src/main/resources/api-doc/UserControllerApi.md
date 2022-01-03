<!-- vscode-markdown-toc -->
* 1. [用户注册](#)
* 2. [查看用户表](#-1)
* 3. [返回所有用户](#-1)
* 4. [测试](#-1)
* 5. [我是谁](#-1)
* 6. [查看个人信息](#-1)
* 7. [修改个人信息](#-1)
* 8. [修改密码](#-1)
* 9. [开启/关闭匿名模式](#-1)
* 10. [发送验证码](#-1)
* 11. [忘记密码](#-1)
* 12. [修改邮箱](#-1)

<!-- vscode-markdown-toc-config
	numbering=true
	autoSave=true
	/vscode-markdown-toc-config -->
<!-- /vscode-markdown-toc -->

# 用户管理
##  1. <a name=''></a>用户注册
**URL:** `/user/add`

**Type:** `POST`


**Content-Type:** `application/json; charset=utf-8`

**Description:** 用户注册




**Body-parameters:**

Parameter|Type|Description|Required|Since
---|---|---|---|---
userid|int32|账号|false|-
password|string|密码|false|-
email|string|邮箱|false|-
code|string|验证邮箱的验证码|false|-

**Request-example:**
```
curl -X POST -H 'Content-Type: application/json; charset=utf-8' -i /user/add --data '{
  "userid": "2019xxxxxx",
  "password": "123456789",
  "email": "123456789@qq.com",
  "code": "123456"
}'
```
**Response-fields:**

Field | Type|Description|Since
---|---|---|---
success|boolean|成功标志|-
code|int32|状态码|-
msg|string|处理信息|-
data|object|处理数据|-
└─userid|int32|用户账号|-
└─username|string|用户名|-
└─email|string|邮箱|-
└─headimage|string|头像|-
└─banstate|string|用户状态|-
└─usertype|string|用户类型|-
└─logintime|string|登录时间|-
└─anonymousmode|boolean|匿名模式|-
timestamp|string|时间戳|-

**Response-example:**
```
{"success":true,"code":200,"msg":"success","data":{"userid":2019192018,"username":"张三","email":"2019192000@szu.edu.cn","headimage":""05fe71557e164b32b503708271365dfd.png"","banstate":"封禁状态","usertype":"管理员","logintime":"2021-12-21 00:27:02","anonymousmode":true},"timestamp":"2021-12-21 00:27:02"}
```

##  2. <a name='-1'></a>查看用户表
**URL:** `/user/admin/checkuser`

**Type:** `GET`


**Content-Type:** `application/x-www-form-urlencoded;charset=utf-8`

**Description:** 查看用户表



**Query-parameters:**

Parameter|Type|Description|Required|Since
---|---|---|---|---
page|int32|页数|true|-
size|int32|每页记录数|true|-


**Request-example:**
```
curl -X GET -i /user/admin/checkuser?page=1&size=10
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
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─userid|int32|用户账号|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─username|string|用户名|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─email|string|邮箱|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─headimage|string|头像|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─banstate|string|用户状态|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─usertype|string|用户类型|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─logintime|string|登录时间|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─anonymousmode|boolean|匿名模式|-
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
{"success":true,"code":200,"msg":"success","data":{"total":88,"list":[{"userid":2019192018,"username":"张三","email":"2019192000@szu.edu.cn","headimage":""05fe71557e164b32b503708271365dfd.png"","banstate":"封禁状态","usertype":"管理员","logintime":"2021-12-21 00:27:02","anonymousmode":true}],"pageNum":514,"pageSize":10,"size":10,"startRow":683,"endRow":932,"pages":976,"prePage":1,"nextPage":1,"firstPage":true,"lastPage":true,"hasPreviousPage":true,"hasNextPage":true,"navigatePages":65,"navigatepageNums":[696],"navigateFirstPage":1,"navigateLastPage":1},"timestamp":"2021-12-21 00:27:02"}
```

##  3. <a name='-1'></a>返回所有用户
**URL:** `/user/all`

**Type:** `GET`


**Content-Type:** `application/x-www-form-urlencoded;charset=utf-8`

**Description:** 返回所有用户





**Request-example:**
```
curl -X GET -i /user/all
```
**Response-fields:**

Field | Type|Description|Since
---|---|---|---
success|boolean|成功标志|-
code|int32|状态码|-
msg|string|处理信息|-
data|array|处理数据|-
└─userid|int32|用户账号|-
└─username|string|用户名|-
└─email|string|邮箱|-
└─headimage|string|头像|-
└─banstate|string|用户状态|-
└─usertype|string|用户类型|-
└─logintime|string|登录时间|-
└─anonymousmode|boolean|匿名模式|-
timestamp|string|时间戳|-

**Response-example:**
```
{"success":true,"code":200,"msg":"success","data":[{"userid":2019192018,"username":"张三","email":"2019192000@szu.edu.cn","headimage":""05fe71557e164b32b503708271365dfd.png"","banstate":"封禁状态","usertype":"管理员","logintime":"2021-12-21 00:27:02","anonymousmode":true}],"timestamp":"2021-12-21 00:27:02"}
```

##  4. <a name='-1'></a>测试
**URL:** `/user/test`

**Type:** `GET`


**Content-Type:** `application/x-www-form-urlencoded;charset=utf-8`

**Description:** 测试





**Request-example:**
```
curl -X GET -i /user/test
```

**Response-example:**
```
string
```

##  5. <a name='-1'></a>我是谁
**URL:** `/user/whoami`

**Type:** `GET`


**Content-Type:** `application/x-www-form-urlencoded;charset=utf-8`

**Description:** 我是谁





**Request-example:**
```
curl -X GET -i /user/whoami
```

**Response-example:**
```
997
```

##  6. <a name='-1'></a>查看个人信息
**URL:** `/user/showprofile`

**Type:** `GET`


**Content-Type:** `application/x-www-form-urlencoded;charset=utf-8`

**Description:** 查看个人信息





**Request-example:**
```
curl -X GET -i /user/showprofile
```
**Response-fields:**

Field | Type|Description|Since
---|---|---|---
success|boolean|成功标志|-
code|int32|状态码|-
msg|string|处理信息|-
data|object|处理数据|-
└─userid|int32|用户账号|-
└─username|string|用户名|-
└─email|string|邮箱|-
└─headimage|string|头像|-
└─banstate|string|用户状态|-
└─usertype|string|用户类型|-
└─logintime|string|登录时间|-
└─anonymousmode|boolean|匿名模式|-
timestamp|string|时间戳|-

**Response-example:**
```
{"success":true,"code":200,"msg":"success","data":{"userid":2019192018,"username":"张三","email":"2019192000@szu.edu.cn","headimage":""05fe71557e164b32b503708271365dfd.png"","banstate":"封禁状态","usertype":"管理员","logintime":"2021-12-21 00:27:02","anonymousmode":true},"timestamp":"2021-12-21 00:27:02"}
```

##  7. <a name='-1'></a>修改个人信息
**URL:** `/user/updateprofile`

**Type:** `POST`


**Content-Type:** `multipart/form-data`

**Description:** 修改个人信息



**Query-parameters:**

Parameter|Type|Description|Required|Since
---|---|---|---|---
multipartFile|file|No comments found.|true|-
userid|int32|用户账号|false|-
username|string|用户名|false|-
email|string|邮箱|false|-
headimage|string|头像|false|-
banstate|string|用户状态|false|-
usertype|string|用户类型|false|-
logintime|string|登录时间|false|-
anonymousmode|boolean|匿名模式|false|-


**Request-example:**
```
curl -X POST -H 'Content-Type: multipart/form-data' -i /user/updateprofile --data 'username=张三&banstate=封禁状态&usertype=管理员&anonymousmode=true&email=2019192000@szu.edu.cn&logintime=2021-12-21 00:27:02&userid=2019192018&headimage=05fe71557e164b32b503708271365dfd.png'
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

##  8. <a name='-1'></a>修改密码
**URL:** `/user/changepassword`

**Type:** `POST`


**Content-Type:** `application/json; charset=utf-8`

**Description:** 修改密码




**Body-parameters:**

Parameter|Type|Description|Required|Since
---|---|---|---|---
oldPassword|string|旧密码|false|-
newPassword|string|新密码|false|-

**Request-example:**
```
curl -X POST -H 'Content-Type: application/json; charset=utf-8' -i /user/changepassword --data '{
  "oldPassword": "k17edd",
  "newPassword": "pqd6sw"
}'
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

##  9. <a name='-1'></a>开启/关闭匿名模式
**URL:** `/user/switchmode`

**Type:** `POST`


**Content-Type:** `application/x-www-form-urlencoded;charset=utf-8`

**Description:** 开启/关闭匿名模式





**Request-example:**
```
curl -X POST -i /user/switchmode
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

##  10. <a name='-1'></a>发送验证码
**URL:** `/user/sendcode`

**Type:** `GET`


**Content-Type:** `application/x-www-form-urlencoded;charset=utf-8`

**Description:** 发送验证码



**Query-parameters:**

Parameter|Type|Description|Required|Since
---|---|---|---|---
to|string|  验证码发往的邮箱|false|-
flag|string|"exist"表示需要邮箱已注册(忘记密码);     "noexist"表示需要邮箱未注册(注册，修改邮箱)|false|-


**Request-example:**
```
curl -X GET -i /user/sendcode?to=z9v8bd&flag=wcz6hx
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

##  11. <a name='-1'></a>忘记密码
**URL:** `/user/forget`

**Type:** `POST`


**Content-Type:** `application/json; charset=utf-8`

**Description:** 忘记密码




**Body-parameters:**

Parameter|Type|Description|Required|Since
---|---|---|---|---
newPassword|string|新密码|false|-
email|string|邮箱|false|-
code|string|验证邮箱的验证码|false|-

**Request-example:**
```
curl -X POST -H 'Content-Type: application/json; charset=utf-8' -i /user/forget --data '{
  "newPassword": "123456789",
  "email": "123456789@qq.com",
  "code": "123456"
}'
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

##  12. <a name='-1'></a>修改邮箱
**URL:** `/user/changeemail`

**Type:** `POST`


**Content-Type:** `application/json; charset=utf-8`

**Description:** 修改邮箱




**Body-parameters:**

Parameter|Type|Description|Required|Since
---|---|---|---|---
email|string|新邮箱|false|-
code|string|验证码|false|-

**Request-example:**
```
curl -X POST -H 'Content-Type: application/json; charset=utf-8' -i /user/changeemail --data '{
  "email": "shirleen.parisian@yahoo.com",
  "code": "51666"
}'
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

