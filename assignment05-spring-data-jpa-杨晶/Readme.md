[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/Yk-Oojc7)

# Web应用开发作业05：基于Spring Data JPA的数据访问层开发实践

## 一、作业背景与目的
本次作业旨在考察大家对**Spring Data JPA**核心组件的理解与应用，掌握**ORM实体建模**、**基本增删改查操作**、**分页与排序查询**以及**复杂查询自定义扩展**的能力。

---

## 二、实践任务

### 任务一：基础CRUD功能实现（面向 CrudRepository）
**目标：掌握Spring Data JPA最基本的增删改查。**

- 设计一个简单的实体（如`Book`实体），包括以下字段：
    - `id`（主键，自增）
    - `title`（书名）
    - `author`（作者）
    - `price`（价格）
    - `publishedDate`（出版日期）
- 基于**CrudRepository**接口，完成以下API：
    - 创建一本新书
    - 查询所有书籍
    - 根据ID查询单本书籍
    - 更新一本书的信息
    - 删除一本书

**要求：**
- 正确配置JPA实体映射关系。
- 提供Restful风格接口供前端访问。

---

### 任务二：分页与排序查询实现（面向 PagingAndSortingRepository）
**目标：掌握Spring Data JPA中的分页与排序操作。**

- 继续使用**任务一**中的`Book`实体，在此基础上：
    - 通过**PagingAndSortingRepository**扩展接口，增加分页查询API：
        - 查询所有书籍并支持分页（每页5本，可传页码参数）。
        - 支持按出版日期、价格排序（升序/降序可选）。

**要求：**
- 接口接受`page`、`size`、`sort`等参数。
- 返回标准分页结果，包括当前页码、总页数、总记录数、当前页数据列表。

---

### 任务三：复杂查询与自定义Repository扩展（面向 JpaRepository）
**目标：掌握使用JpaRepository进行复杂条件查询和扩展自定义方法。**

- 在前两个任务基础上，基于**JpaRepository**，完成以下功能：
    - 查询指定作者的所有书籍。
    - 查询某一年（传入年份）出版的所有书籍。
    - 通过书名模糊搜索（比如输入"Spring"可以查出"Spring Boot in Action"）。


**要求：**
- 尽量使用Spring Data JPA方法命名规则自动生成查询。
- 使用QueryByExample/JPASpecification
- 返回数据清晰区分各类查询结果。

---

## 三、技术要求

- Spring Boot 3.x及以上。
- Spring Data JPA。
- 数据库可使用H2（内存数据库）或MySQL。
- 接口风格统一采用Restful API。

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

## 五、评分标准

评分项 | 分值
ORM实体建模正确（字段映射、主键配置） | 20分
基础CRUD功能实现（任务一） | 30分
分页与排序查询实现（任务二） | 20分
复杂查询与自定义扩展（任务三） | 20分
代码规范与文档完整性 | 10分
（加分项）动态查询/异常处理/DTO优化 | +10分


| 评分项                  | 分值   |
|----------------------|------|
| ORM实体建模正确（字段映射、主键配置） | 20分  |
| 基础CRUD功能实现（任务一）      | 30分  |
| 分页与排序查询实现（任务二）       | 20分  |
| 复杂查询与自定义扩展（任务三）      | 20分  |
| 代码规范与文档完整性           | 10分  |
| （加分项）动态查询/异常处理/DTO优化 | +10分 |

---

## 六、项目说明

### 项目基本功能描述
本项目是一个基于Spring Data JPA的图书管理系统，实现了以下功能：
- **基础CRUD操作**：创建、查询、更新和删除书籍。
- **分页与排序查询**：支持分页查询书籍列表，并可按出版日期或价格进行升序或降序排序。
- **复杂查询**：支持按作者查询、按出版年份查询以及书名模糊搜索。

### 使用的鉴权方式
本项目未实现鉴权机制，API接口为公开访问，适合开发和测试环境。

### 如何运行与测试
1. **运行项目**：
   - 确保已安装Maven。
   - 在项目根目录下运行命令：`mvn spring-boot:run`。
   - 项目启动后，默认端口为8080，可通过浏览器访问H2数据库控制台：`http://localhost:8080/h2-console`（用户名：sa，密码为空）。
   - jdbcurl：`jdbc:h2:mem:testdb`
2. **测试API**：
   - 使用Postman或其他API测试工具访问以下端点：
     - **创建书籍**：POST `http://localhost:8080/books` （请求体示例：`{"title": "Spring Boot in Action", "author": "Craig Walls", "price": 39.99, "publishedDate": "2019-01-01"}`）
     - **查询所有书籍**：GET `http://localhost:8080/books`
     - **查询单本书籍**：GET `http://localhost:8080/books/{id}`
     - **更新书籍**：PUT `http://localhost:8080/books/{id}` （请求体同创建）
     - **删除书籍**：DELETE `http://localhost:8080/books/{id}`
     - **分页查询**：GET `http://localhost:8080/books/paged?page=0&size=5&sort=price,asc`
     - **按作者查询**：GET `http://localhost:8080/books/by-author?author=Craig Walls`
     - **按年份查询**：GET `http://localhost:8080/books/by-year?year=2019`
     - **模糊搜索**：GET `http://localhost:8080/books/search?keyword=Spring`

### 测试账号信息
由于未实现鉴权，无需测试账号信息即可访问所有API。

### 加分项说明
本项目未实现加分项功能（如动态查询、异常处理、DTO优化），但代码结构清晰，注释完整，符合基本要求。
