package ru.javawebinar.basejava;

import ru.javawebinar.basejava.model.*;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ResumeTestData {
    public static void fillData(Resume resume) {
        List<String> achievement = new ArrayList<>();
        achievement.add("С 2013 года: разработка проектов \"Разработка Web приложения\",\"Java Enterprise\", \"Многомодульный maven. Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). Удаленное взаимодействие (JMS/AKKA)\". Организация онлайн стажировок и ведение проектов. Более 1000 выпускников.");
        achievement.add("Реализация двухфакторной аутентификации для онлайн платформы управления проектами Wrike. Интеграция с Twilio, DuoSecurity, Google Authenticator, Jira, Zendesk.");
        achievement.add("Налаживание процесса разработки и непрерывной интеграции ERP системы River BPM. Интеграция с 1С, Bonita BPM, CMIS, LDAP. Разработка приложения управления окружением на стеке: Scala/Play/Anorm/JQuery. Разработка SSO аутентификации и авторизации различных ERP модулей, интеграция CIFS/SMB java сервера.");

        List<String> qualification = new ArrayList<>();
        qualification.add("JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2");
        qualification.add("Version control: Subversion, Git, Mercury, ClearCase, Perforce");
        qualification.add("DB: PostgreSQL(наследование, pgplsql, PL/Python), Redis (Jedis), H2, Oracle,");
        qualification.add("MySQL, SQLite, MS SQL, HSQLDB");

        List<Organization> experiences = new ArrayList<>();
        experiences.add(new Organization(
                "Java Online Projects", "http://javaops.ru/",
                YearMonth.of(2013, 10), YearMonth.now(),
                "Автор проекта.",
                "Создание, организация и проведение Java онлайн проектов и стажировок."));
        experiences.add(new Organization(
                "Java Online Projects", "http://javaops.ru/",
                YearMonth.of(2014, 10), YearMonth.now(),
                "Автор курса.",
                "Создание, организация и проведение Java онлайн проектов и стажировок."));
        experiences.add(new Organization(
                "Wrike", "https://www.wrike.com/",
                YearMonth.of(2014, 10), YearMonth.of(2016, 1),
                "Старший разработчик (backend)",
                "Проектирование и разработка онлайн платформы управления проектами Wrike (Java 8 API, Maven, Spring, MyBatis, Guava, Vaadin, PostgreSQL, Redis). Двухфакторная аутентификация, авторизация по OAuth1, OAuth2, JWT SSO."));
        experiences.add(new Organization(
                "Yota", "https://www.yota.ru/",
                YearMonth.of(2008, 6), YearMonth.of(2010, 12),
                "Ведущий специалист",
                "Дизайн и имплементация Java EE фреймворка для отдела \"Платежные Системы\" (GlassFish v2.1, v3, OC4J, EJB3, JAX-WS RI 2.1, Servlet 2.4, JSP, JMX, JMS, Maven2). Реализация администрирования, статистики и мониторинга фреймворка. Разработка online JMX клиента (Python/ Jython, Django, ExtJS)"));

        List<Organization> educations = new ArrayList<>();
        Link itmo = new Link("СПБНИУ ИТМО", "https://www.ifmo.ru");
        Stage itmoStage1 = new Stage(YearMonth.of(2013, 9), YearMonth.of(1996, 7),
                "Аспирантура(программист С,С++)",
                null);
        Stage itmoStage2 = new Stage(YearMonth.of(1987, 9), YearMonth.of(1993, 7),
                "Инженер (программист Fortran,C)",
                null);
        educations.add(new Organization(itmo, itmoStage1, itmoStage2));
        educations.add(new Organization(
                "Coursera", "https://www.coursera.org/course/progfun",
                YearMonth.of(2013, 3), YearMonth.of(2013, 5),
                "Functional Programming Principles in Scala\" by Martin Odersky",
                null));
        educations.add(new Organization("Luxoft", "http://www.luxoft-training.ru/training/catalog/course.html?ID=22366",
                YearMonth.of(2011, 3), YearMonth.of(2011, 4),
                "Курс \"Объектно-ориентированный анализ ИС. Концептуальное моделирование на UML.\"",
                null));
        educations.add(new Organization("Siemens AG", "http://www.siemens.ru/",
                YearMonth.of(2005, 1), YearMonth.of(2005, 4),
                "3 месяца обучения мобильным IN сетям (Берлин)",
                null));

        AbstractSection personal = new TextAbstractSection("Аналитический склад ума, сильная логика, креативность, инициативность. Пурист кода и архитектуры.");
        AbstractSection objective = new TextAbstractSection("Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям");
        AbstractSection achievements = new ListAbstractSection(achievement);
        AbstractSection qualifications = new ListAbstractSection(qualification);
        AbstractSection experience = new OrganizationAbstractSection(experiences);
        AbstractSection education = new OrganizationAbstractSection(educations);

        Map<ContactType, String> contacts = resume.getContacts();
        contacts.put(ContactType.PHONE, "+7(921) 855-0482");
        contacts.put(ContactType.SKYPE, "grigory.kislin");
        contacts.put(ContactType.EMAIL, "gkislin@yandex.ru");
        contacts.put(ContactType.LINKEDIN, "https://www.linkedin.com/in/gkislin");
        contacts.put(ContactType.GITHUB, "https://github.com/gkislin");
        contacts.put(ContactType.STACKOVERFLOW, "https://stackoverflow.com/users/548473");
        contacts.put(ContactType.HOMEPAGE, "http://gkislin.ru/");

        resume.setContacts(contacts);

        Map<SectionType, AbstractSection> sections = resume.getSections();
        sections.put(SectionType.PERSONAL, personal);
        sections.put(SectionType.OBJECTIVE, objective);
        sections.put(SectionType.ACHIEVEMENT, achievements);
        sections.put(SectionType.QUALIFICATIONS, qualifications);
        sections.put(SectionType.EXPERIENCE, experience);
        sections.put(SectionType.EDUCATION, education);

        resume.setSections(sections);
    }
}