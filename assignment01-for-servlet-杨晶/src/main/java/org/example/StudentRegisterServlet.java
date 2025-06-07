package org.example;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.StudentData;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/register")
public class StudentRegisterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String name = request.getParameter("name");
        String ageStr = request.getParameter("age");

        try {
            if (name == null || name.isEmpty() || ageStr == null || ageStr.isEmpty()) {
                throw new IllegalArgumentException("Name and Age are required.");
            }

            int age = Integer.parseInt(ageStr);
            if (age <= 0) {
                throw new IllegalArgumentException("Age must be a positive number.");
            }

            StudentData.addStudent(name, age);
            System.out.println("Registered Student: " + name + ", Age: " + age);
            out.println("<h2>Registration Successful!</h2>");
            out.println("<p>Student " + name + " has been registered.</p>");
        } catch (NumberFormatException e) {
            out.println("<h2>Error</h2>");
            out.println("<p>Invalid age format. Please enter a valid number.</p>");
        } catch (IllegalArgumentException e) {
            out.println("<h2>Error</h2>");
            out.println("<p>" + e.getMessage() + "</p>");
        }

        out.println("<p><a href='/students'>Back to Student List</a></p>");
        out.close();
    }
}
