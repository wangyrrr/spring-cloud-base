# spring-cloud-base
基于spring cloud全家桶快速开发的脚手架  

# 架构思路  
业务支撑层：各业务中心  
技术支撑层：注册中心、配置中心、定时调度、网关、消息通知、第三方对接、监控、日志   
基础设施层：云服务器、云容器引擎、云数据库、云中间件、对象存储、CI/CD  

# 技术选项
Maven  
Mysql  
Redis  
RabbitMQ  
Spring Cloud  
Mybatis-Plus  
Sharding-JDBC  
Nacos  
Xxl-job  
Sa-Token  
Fastjson2  
Skywalking  
Prometheus  
Loki  


# 服务规划：
common：公共包   
gateway：8000，网关，服务转发、鉴权、限流、熔断   
message：通知消息服务，短信、邮件、站内消息、微信第三方消息  
third：第三方对接服务，对接三方系统，保留调用记录，转换为内部数据  
search：8020，搜索服务，es全文检索  
report：报表服务，从库多数据源，复杂查询，定时统计、报表  
base：基础服务，基础数据维护（行政区域）、通用基本功能（地图经纬度解析）   
system：8010，后台系统服务，用户角色菜单rbac权限体系，基于sa-token    
user：9010，客户端用户服务，客户端用户注册、登录、第三方授权，jwt  
... ：业务服务

# TODO
升级到 spring cloud 2021  
AOP操作日志  
base省市区  