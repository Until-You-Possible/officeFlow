### 关于redis的一些积累(mac)
### redis 官网： https://redis.io/
1: 使用homebrew安装

```
brew install redis
```

2: redis的配置文件redis.conf存在在 /usr/local/etc路径下

3：启动redis

```
// 使用brew启动软件
brew services start redis
// 执行redis-server 可以看到
```

![img.png](Desktop/github/officeSystem/officeFlow/deps/db/img/img.png)

从这里可以看出 redis的版本， 端口和PID

4: 查看redis服务进程

```
ps axu | grep redis
```

![img_1.png](Desktop/github/officeSystem/officeFlow/deps/db/img/img_1.png)


