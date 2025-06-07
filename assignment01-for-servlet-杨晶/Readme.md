[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/GzX8aBNh)
### **Web应用开发作业01：Servlet 的学生信息管理系统**
**目标**：通过 Servlet 处理不同类型的请求参数，实现简单的学生信息查询、注册和动态路由访问。

---

### **任务描述**

1. **搭建基本项目**  
   创建一个基于 Servlet 的 Web 项目，项目结构如下：
```
src/
├── StudentListServlet.java
├── StudentRegisterServlet.java
└── StudentDetailServlet.java
web/
└── register.html
```

---

### **查询学生列表 (Query Parameter)**
编写 `StudentListServlet`，处理 `GET` 请求，查询学生信息列表，支持按姓名搜索。

- URL 示例：
   - 查询全部学生：`http://localhost:8080/students`
   - 按姓名查询：`http://localhost:8080/students?name=Alice`

- 返回结果：

```
Student List:
1. Alice - 20
2. Bob - 22
```

---

### **注册新学生 (Form Parameter)**
编写 `StudentRegisterServlet`，处理 `POST` 请求，保存学生信息。

- 前端页面：`register.html`

```html
<h2>Student Registration</h2>
<form action="/register" method="post">
    Name: <input type="text" name="name"><br>
    Age: <input type="number" name="age"><br>
    <input type="submit" value="Register">
</form>
```

- Servlet 处理
- 在控制台打印相关注册学生信息

---

### **查看学生详情 (Path Parameter)**
编写 `StudentDetailServlet`，处理路径参数，查看指定学生详情。

- URL 示例：`http://localhost:8080/student/Alice`
- 返回结果：`Student: Alice, Age: 20, ID: 001`

---

## **作业要求**
1. **功能完整**：实现学生查询、注册、详情查看三大功能。
2. **页面交互**：设计简单页面，支持表单提交和超链接跳转。
3. **数据模拟**：可以用简单的 `ArrayList` 模拟学生数据。
4**异常处理**：处理参数缺失、格式错误等异常情况。

### **提交方式**
- 提交完整代码和截图，包括成功的请求和响应结果（请放在reousrces文件夹中）。
- 可以用本地 Tomcat 部署测试，或者使用 IDE 内置的 Web 服务器。

---
