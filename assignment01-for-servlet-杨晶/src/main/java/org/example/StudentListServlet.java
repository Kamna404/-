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
import java.util.List;

@WebServlet("/students")
public class StudentListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String name = request.getParameter("name");
        List<Student> students;

        if (name == null || name.isEmpty()) {
            students = StudentData.getAllStudents();
            out.println("<h2>Student List (All):</h2>");
        } else {
            students = StudentData.getStudentsByName(name);
            out.println("<h2>Student List (Search by name: " + name + "):</h2>");
        }

        if (students.isEmpty()) {
            out.println("<p>No students found.</p>");
        } else {
            out.println("<ul>");
            for (Student student : students) {
                out.println("<li><a href='/student/" + student.getName() + "'>" + student.getName() + "</a> - " + student.getAge() + "</li>");
            }
            out.println("</ul>");
        }
        out.println("<p><a href='/register.html'>Register New Student</a></p>");
        out.close();
    }
}
