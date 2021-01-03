# 使用说明
## 接口调用
项目启动后通过POST请求调用 http://localhost:8080/migrate?sourcePath={sourcePath}&destPath={destPath}

## 项目结构
core - 代码的核心域  
job - 扫描文件是否更新的定时任务，执行间隔在application.properties里配置  
web - 提供对外的rest api  