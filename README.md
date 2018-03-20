# 简介
一个内容销售系统，系统分为两类用户：买家和卖家。

# 使用技术 

IoC容器:spring

web框架:springmvc

orm框架:mybatis

安全框架:shiro

数据源:dbcp2

日志: sl4j

Json: Gson

前端框架:Bootstrap

# 起步:

1.初始化项目

1)下载Mysql,创建一个数据库名字为kaola，kaola.sql、order.sql与shiro.sql,直接运行其中sql即可

2)下载Tomcat

3)使用Intellij Idea导入项目,之后配置项目启动方式,使用刚才下载好的Tomcat
  
4)运行

# 使用简介:

1.权限介绍:

根据导入的shiro.sql,运行网站初始时会存在两个权限角色:

1)买家,账号为buyer，密码为reyub

2)卖家,账号为seller，密码为relles

注意：由于初始数据库中没有商品，因此请先登录seller账号进行添加商品操作。


# 功能介绍:
##  1.买家功能:

1)查看所有商品内容

2)购买商品

3)将商品加入购物车，并在购物车页面结算

4)查看账务


## 2.卖家功能:
1)查看所有商品及出售情况

2)对商品进行增、删、改操作



