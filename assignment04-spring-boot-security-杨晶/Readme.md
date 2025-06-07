# 简易任务管理系统

这是一个基于Spring Boot Security的简易任务管理系统，实现了用户鉴权和任务管理功能。

## 项目基本功能描述
- **用户注册与登录**：用户可以通过注册接口创建账号，并通过登录接口获取JWT token。
- **任务管理**：用户可以查看、新建和删除自己的任务。
- **角色权限**：系统支持ROLE_USER和ROLE_ADMIN两种角色，ROLE_ADMIN可以查询所有用户的任务。
- **OAuth2认证**：支持通过GitHub进行第三方登录。

## 使用的鉴权方式
- **JWT Authentication**：用户登录后获取JWT token，后续请求需携带Bearer Token访问受保护资源。
- **OAuth2 Authentication**：支持GitHub登录，需在application.properties中配置client-id和client-secret。

## 如何运行与测试
1. **运行项目**：
   - 确保已安装Maven和Java 17。
   - 在项目根目录下运行命令：`mvn spring-boot:run`。
   - 项目启动后，访问 `http://localhost:8080`。
2. **访问H2数据库控制台**：
   - 访问 `http://localhost:8080/h2-console`。
   - 使用JDBC URL `jdbc:h2:mem:testdb`，用户名 `sa`，密码为空。
3. **测试API端点**：
   - 使用Postman或其他API测试工具。
   - 注册用户：POST `http://localhost:8080/api/register`，请求体示例：
     ```json
     {
       "username": "user1",
       "password": "password123",
       "role": "ROLE_USER"
     }
     ```
   - 登录获取token：POST `http://localhost:8080/api/login`，请求体示例：
     ```json
     {
       "username": "user1",
       "password": "password123"
     }
     ```
   - 使用返回的accessToken访问受保护端点，如GET `http://localhost:8080/api/tasks`，需在请求头中设置 `Authorization: Bearer <your-token>`。
4. **OAuth2登录**：
   - 访问 `http://localhost:8080/oauth2/authorization/github` 进行GitHub登录（需配置有效的client-id和client-secret）。

## 测试账号信息
- **普通用户**：
  - 用户名：user1
  - 密码：password123
  - 角色：ROLE_USER
- **管理员用户**：
  - 用户名：admin
  - 密码：admin123
  - 角色：ROLE_ADMIN

## 加分项说明
- 实现了基于OAuth2的第三方认证（GitHub登录）。
- 在登录接口返回中，增加了token过期时间与刷新机制（accessToken和refreshToken）。


---


[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/3agWo9VA)
# Web应用开发作业04：基于Spring Boot的Web应用鉴权功能开发

## 一、作业背景与目的
本次作业旨在考察学生对**Spring Boot Security模块**的掌握情况，通过在实际业务场景中实现用户鉴权功能，理解不同鉴权机制（Basic Authentication、JWT Authentication）的应用方式与实现细节。对于能够拓展实现OAuth2认证流程的同学，将给予额外加分。

---

## 二、业务场景描述

请根据以下虚拟业务场景开发一个简单的后端API应用：

> **场景：**  
> 你正在开发一个“简易任务管理系统”。该系统主要面向公司内部员工，员工可以通过账号登录后管理自己的任务清单。系统要求对接口进行保护，未认证用户无法访问数据，且不同用户的数据必须隔离。

- **基本用户功能：**
    - 注册账号（可选，如果时间紧可固定用户数据）
    - 登录并获得访问权限
    - 查看自己的任务列表
    - 新建任务
    - 删除自己的任务

---

## 三、技术要求

1. **基础框架：**
    - 使用Spring Boot 3.x及以上版本。
    - 使用Spring Security实现接口保护。
    - 数据存储可以选用内存（如H2数据库）或简单的内存列表模拟（推荐H2）。

2. **鉴权机制（任选其一完成）：**
    - **Basic Authentication**
        - 用户通过HTTP Basic方式提交用户名和密码。
        - 每次请求都需附带Authorization头。
    - **JWT Authentication**
        - 用户通过登录接口获得JWT令牌。
        - 后续请求通过Bearer Token方式访问受保护资源。

3. **接口保护要求：**
    - 除登录或注册接口外，所有接口必须鉴权。
    - 用户仅能访问和管理自己的任务，禁止跨用户访问。

4. **角色与权限（选做加分项）：**
    - 为系统增加不同角色（如`ROLE_USER`、`ROLE_ADMIN`）。
    - 限制某些接口仅`ROLE_ADMIN`可以访问，比如可以查询所有用户的任务信息。

5. **额外加分项（自愿完成）：**
    - 实现基于OAuth2（如GitHub登录、Google登录等）的第三方认证。
    - 在接口返回中，增加token过期时间与刷新机制。

---

## 四、交付要求

1. **提交内容：**
    - 完整的项目源码。
    - README文档，说明：
        - 项目基本功能描述
        - 使用的鉴权方式
        - 如何运行与测试
        - 测试账号信息
    - 若实现了加分项，请在README中特别标注。

2. **代码要求：**
    - 保持良好的项目结构。
    - 代码注释清晰，体现出对Security配置逻辑的理解。

3. **提交方式与截止时间：**
    - 通过Github Classroom提供的Git仓库地址进行提交。

---

## 五、作业评分标准

| 项目                         | 分值  |
|------------------------------|-------|
| 基础功能（鉴权正确实现）       | 60分  |
| 代码规范与项目结构             | 15分  |
| README与文档完整性             | 10分  |
| 接口保护细致性  | 15分  |
| 加分项（OAuth2/角色管理等）     | +15分 |

