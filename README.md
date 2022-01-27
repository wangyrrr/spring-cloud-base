# pxys-parent 通用父工程
## 通用父工程：产品线下的所有项目必须指定一个父工程项目，以复用 POM 的 <build> 配置

服务规划：
common：公共包
api：各服务暴露的内部api包
base：基础服务，基础数据维护（产品，行政区，营销区域）、通用基本功能（短信，邮件，经纬度解析）
manger：后台管理服务，后台入口，后台用户、菜单权限体系
code：码服务，四码关联、码维护
wechat：消费者服务，消费者小程序入口，消费者活动相关
store：商户服务，商户小程序，商户活动相关
sfa：sfa服务，销售小程序入口，内部业务员，销售管理、经销商管理，crm
promotion：促销服务，促销活动管理，内部推广，临时促销员
search：搜索服务，es全文检索，数据来源其他系统异步消息
report：报表服务，从库多数据源，复杂查询，定时报表
log：日志服务，扫码日志，第三方系统交互日志，操作日志，各种记录
monitor：业务监控服务，通过定时任务检测数据是否正常


这个是我设想的服务划分，可以一起讨论补充一下