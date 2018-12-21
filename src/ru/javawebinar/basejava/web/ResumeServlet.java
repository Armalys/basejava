package ru.javawebinar.basejava.web;

import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.storage.SqlStorage;
import ru.javawebinar.basejava.storage.Storage;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ResumeServlet extends HttpServlet {
    private Storage storage = new SqlStorage("jdbc:postgresql://localhost:5432/resumes", "postgres", "2121");

    protected void doPost(HttpServletRequest request, HttpServletResponse response) {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-Type", "text/html; charset=UTF-8");

        String uuid = request.getParameter("uuid");
        if (uuid == null) {
            response.getWriter().write("<table class=\"tftable\" border=\"1\">" +
                    " <tr><th> uuid</th><th>" +
                    "full name</th></tr>");
            for (Resume resume : storage.getAllSorted()) {
                response.getWriter().write("<tr><td>" + resume.getUuid() + "</td><td>" +
                        resume.getFullName() + "</td></tr>");
            }
            response.getWriter().write("</table>");
        } else {


            Resume resume = storage.get(uuid);
            response.getWriter().write("<table class=\"tftable\" border=\"1\">\n" +
                    "<tr><th>uuid</th><th>full name</th></tr>\n" +
                    "<tr><td>" + resume.getUuid() + "</td><td>" + resume.getFullName() + "</td></tr>\n" +
                    "</table>");
        }
    }
}
