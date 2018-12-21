package ru.javawebinar.basejava.web;

import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.storage.SqlStorage;
import ru.javawebinar.basejava.storage.Storage;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

public class ResumeServlet extends HttpServlet {
    private Storage storage = new SqlStorage("jdbc:postgresql://localhost:5432/resumes", "postgres", "2121");

    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = UUID.randomUUID().toString();
    private static final String UUID_3 = UUID.randomUUID().toString();


    private static final Resume RESUME_1 = new Resume(UUID_1, "full name 1");
    private static final Resume RESUME_2 = new Resume(UUID_2, "full name 2");
    private static final Resume RESUME_3 = new Resume(UUID_3, "full name 3");


    protected void doPost(HttpServletRequest request, HttpServletResponse response) {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-Type", "text/html; charset=UTF-8");

        Resume resume = storage.getAllSorted().get(1);
//        String uuid = request.getParameter("uuid");

        response.getWriter().write(resume.getUuid() + " " + resume.getFullName());

    }
}
