# MagicRedis语义

## 实现

### redis()

- 创建redis对象

### redisExists()

- 判断环境中是否存在redis数据库

### redisPut()

- 存键值

### redisGet()

- 根据键获取值

### redisKeyExists()

- 判断键是否存在

## 参数格式

### redis()

- redis()
  - 空参数尝试获取redis://localhost:6379
- redis(字符串url)
  - url格式 redis://IP地址:端口号
- redis(IP地址 端口号)
  - 根据IP和端口号获取
- redis(IP地址 端口号 密码)
  - 根据IP和端口号以及密码获取

### redisExists()

### redisPut(redis对象 键 值 库号(可选))

### redisGet(redis对象 键 库号(可选))

### redisKeyExists(redis对象 键 库号(可选))

## 返回值

### redis()

- redis对象

### redisExists()

- boolean

### redisPut(redis对象 键 值)

- null

### redisGet(redis对象 键)

- str

### redisKeyExists(redis对象 键)

- boolean

