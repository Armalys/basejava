package ru.javawebinar.basejava;

import ru.javawebinar.basejava.model.*;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;

public class ResumeTestData {
    public static void main(String[] args) {
        Resume resume = new Resume("uuid1", "FullName");
        EnumMap<ContactType, String> contacts = new EnumMap<>(ContactType.class);
        EnumMap<SectionType, AbstractTypeOfSection> sections = new EnumMap<>(SectionType.class);

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
        experiences.add(new Organization("Java Online Projects", "10/2013 - Сейчас", "http://javaops.ru/", "Автор проекта.", "Создание, организация и проведение Java онлайн проектов и стажировок."));
        experiences.add(new Organization("Wrike", "10/2014 - 01/2016", "https://www.wrike.com/", "Старший разработчик (backend)", "Проектирование и разработка онлайн платформы управления проектами Wrike (Java 8 API, Maven, Spring, MyBatis, Guava, Vaadin, PostgreSQL, Redis). Двухфакторная аутентификация, авторизация по OAuth1, OAuth2, JWT SSO."));
        experiences.add(new Organization("Yota", "06/2008 - 12/2010", "https://www.yota.ru/", "Ведущий специалист", "Дизайн и имплементация Java EE фреймворка для отдела \"Платежные Системы\" (GlassFish v2.1, v3, OC4J, EJB3, JAX-WS RI 2.1, Servlet 2.4, JSP, JMX, JMS, Maven2). Реализация администрирования, статистики и мониторинга фреймворка. Разработка online JMX клиента (Python/ Jython, Django, ExtJS)"));

        List<Organization> educations = new ArrayList<>();
        educations.add(new Organization("Coursera", "03/2013 - 05/2013", "https://www.coursera.org/course/progfun", "Functional Programming Principles in Scala\" by Martin Odersky"));
        educations.add(new Organization("Luxoft", "03/2011 - 04/2011", "http://www.luxoft-training.ru/training/catalog/course.html?ID=22366", "Курс \"Объектно-ориентированный анализ ИС. Концептуальное моделирование на UML.\""));
        educations.add(new Organization("Siemens AG", "01/2005 - 04/2005", "http://www.siemens.ru/", "3 месяца обучения мобильным IN сетям (Берлин)"));

        AbstractTypeOfSection personal = new TextSection("Аналитический склад ума, сильная логика, креативность, инициативность. Пурист кода и архитектуры.\n");
        AbstractTypeOfSection objective = new TextSection("Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям");
        AbstractTypeOfSection achievements = new ListOfTextSection(achievement);
        AbstractTypeOfSection qualifications = new ListOfTextSection(qualification);
        AbstractTypeOfSection experience = new ListOfOrganization(experiences);
        AbstractTypeOfSection education = new ListOfOrganization(educations);

        contacts.put(ContactType.PHONE, "+7(921) 855-0482");
        contacts.put(ContactType.SKYPE, "grigory.kislin");
        contacts.put(ContactType.EMAIL, "gkislin@yandex.ru");
        contacts.put(ContactType.LINKEDIN, "https://www.linkedin.com/in/gkislin");
        contacts.put(ContactType.GITHUB, "https://github.com/gkislin");
        contacts.put(ContactType.STACKOVERFLOW, "https://stackoverflow.com/users/548473");
        contacts.put(ContactType.HOMEPAGE, "http://gkislin.ru/");

        resume.setContacts(contacts);

        sections.put(SectionType.PERSONAL, personal);
        sections.put(SectionType.OBJECTIVE, objective);
        sections.put(SectionType.ACHIEVEMENT, achievements);
        sections.put(SectionType.QUALIFICATIONS, qualifications);
        sections.put(SectionType.EXPERIENCE, experience);
        sections.put(SectionType.EDUCATION, education);

        resume.setSections(sections);

        for (String text : resume.getContacts().values()) {
            System.out.println(text);
        }

        for (AbstractTypeOfSection text : resume.getSections().values()) {
            System.out.println(text);
        }
    }
}
