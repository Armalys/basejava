package ru.javawebinar.basejava;

import ru.javawebinar.basejava.model.ContactType;
import ru.javawebinar.basejava.model.Resume;

public class ResumeTestData {
    public static void fillData(Resume resume) {

        resume.addContact(ContactType.PHONE, "+7(921) 855-0482");
        resume.addContact(ContactType.SKYPE, "grigory.kislin");
        resume.addContact(ContactType.EMAIL, "gkislin@yandex.ru");
        resume.addContact(ContactType.LINKEDIN, "https://www.linkedin.com/in/gkislin");
        resume.addContact(ContactType.GITHUB, "https://github.com/gkislin");
        resume.addContact(ContactType.STACKOVERFLOW, "https://stackoverflow.com/users/548473");
        resume.addContact(ContactType.HOMEPAGE, "http://gkislin.ru/");


//        resume.addSection(SectionType.PERSONAL, new TextSection(
//                "Аналитический склад ума, сильная логика, креативность, инициативность. Пурист кода и архитектуры."));
//
//        resume.addSection(SectionType.OBJECTIVE, new TextSection(
//                "Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям"));
//
//        resume.addSection(SectionType.ACHIEVEMENT, new ListSection(
//                "С 2013 года: разработка проектов \"Разработка Web приложения\",\"Java Enterprise\"," +
//                        " \"Многомодульный maven. Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP)." +
//                        " Удаленное взаимодействие (JMS/AKKA)\". Организация онлайн стажировок и ведение проектов. Более 1000 выпускников.",
//                "Реализация двухфакторной аутентификации для онлайн платформы управления проектами Wrike." +
//                        " Интеграция с Twilio, DuoSecurity, Google Authenticator, Jira, Zendesk.",
//                "Налаживание процесса разработки и непрерывной интеграции ERP системы River BPM. Интеграция с 1С, Bonita BPM, CMIS, LDAP." +
//                        " Разработка приложения управления окружением на стеке: Scala/Play/Anorm/JQuery." +
//                        " Разработка SSO аутентификации и авторизации различных ERP модулей, интеграция CIFS/SMB java сервера."));
//
//        resume.addSection(SectionType.QUALIFICATIONS, new ListSection(
//                "JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2",
//                "Version control: Subversion, Git, Mercury, ClearCase, Perforce",
//                "DB: PostgreSQL(наследование, pgplsql, PL/Python), Redis (Jedis), H2, Oracle,",
//                "MySQL, SQLite, MS SQL, HSQLDB"));
//
//        resume.addSection(SectionType.EXPERIENCE, new OrganizationSection(
//                new Organization("Java Online Projects", "http://javaops.ru/",
//                        new Organization.Position(2013, Month.of(10),
//                                "Автор проекта.",
//                                "Создание, организация и проведение Java онлайн проектов и стажировок.")),
//                new Organization("Wrike", "https://www.wrike.com/",
//                        new Organization.Position(2014, Month.of(10), 2016, Month.of(1),
//                                "Старший разработчик (backend)",
//                                "Проектирование и разработка онлайн платформы управления проектами Wrike (Java 8 API, Maven, Spring, MyBatis, Guava, Vaadin, PostgreSQL, Redis)." +
//                                        " Двухфакторная аутентификация, авторизация по OAuth1, OAuth2, JWT SSO.")),
//                new Organization("Yota", "https://www.yota.ru/",
//                        new Organization.Position(
//                                2008, Month.of(6), 2010, Month.of(12),
//                                "Ведущий специалист",
//                                "Дизайн и имплементация Java EE фреймворка для отдела \"Платежные Системы\" " +
//                                        "(GlassFish v2.1, v3, OC4J, EJB3, JAX-WS RI 2.1, Servlet 2.4, JSP, JMX, JMS, Maven2)." +
//                                        " Реализация администрирования, статистики и мониторинга фреймворка." +
//                                        " Разработка online JMX клиента (Python/ Jython, Django, ExtJS)"))));
//
//        resume.addSection(SectionType.EDUCATION, new OrganizationSection(
//                new Organization("СПБНИУ ИТМО", "https://www.ifmo.ru",
//                        new Organization.Position(2013, Month.of(9), 1996, Month.of(7),
//                                "Аспирантура(программист С,С++)", null),
//                        new Organization.Position(1987, Month.of(9), 1993, Month.of(9),
//                                "Инженер (программист Fortran,C)",
//                                null)),
//                new Organization("Coursera", "https://www.coursera.org/course/progfun",
//                        new Organization.Position(
//                                2013, Month.of(3), 2013, Month.of(5),
//                                "Functional Programming Principles in Scala by Martin Odersky",
//                                null)),
//                new Organization("Luxoft", "http://www.luxoft-training.ru/training/catalog/course.html?ID=22366",
//                        new Organization.Position(2011, Month.of(3), 2011, Month.of(4),
//                                "Курс \"Объектно-ориентированный анализ ИС. Концептуальное моделирование на UML.",
//                                null)),
//                new Organization("Siemens AG", "http://www.siemens.ru/",
//                        new Organization.Position(
//                                2005, Month.of(1), 2005, Month.of(4),
//                                "3 месяца обучения мобильным IN сетям (Берлин)",
//                                null))));
    }
}
