# spring-cloud-base
基于spring cloud全家桶快速开发基本框架  

# 服务规划：
common：公共包  
gateway：网关，服务转发、鉴权、限流、熔断  
base：基础服务，基础数据维护（行政区域）、通用基本功能（短信，邮件，地图经纬度解析）  
account：用户中心，后台用户、客户端用户  
auth：授权认证服务，jwt   
system：系统服务，菜单角色rbac权限体系  
search：搜索服务，es全文检索
report：报表服务，从库多数据源，复杂查询，定时报表  
log：日志服务，第三方系统交互日志，操作日志，各种记录  
biz-monitor：业务监控服务，通过定时任务检测是否存在异常数据
admin-monitor：springboot admin监控

