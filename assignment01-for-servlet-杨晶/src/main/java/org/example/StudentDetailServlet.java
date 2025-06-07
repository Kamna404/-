package org.example;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.StudentData;
import org.example.Student;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/student/*")
public class StudentDetailServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        // 从路径中提取学生姓名
        String pathInfo = request.getPathInfo();
        if (pathInfo == null || pathInfo.equals("/")) {
            out.println("<h2>Error</h2>");
            out.println("<p>Student name is required in URL.</p>");
        } else {
            String name = pathInfo.substring(1); // 去掉前面的斜杠
            Student student = StudentData.getStudentByName(name);

            if (student == null) {
                out.println("<h2>Error</h2>");
                out.println("<p>Student " + name + " not found.</p>");
            } else {
                out.println("<h2>Student Details:</h2>");
                out.println("<p>Name: " + student.getName() + "</p>");
                out.println("<p>Age: " + student.getAge() + "</p>");
                out.println("<p>ID: " + student.getId() + "</p>");
            }
        }
        out.println("<p><a href='/students'>Back to Student List</a></p>");
        out.close();
    }
}
