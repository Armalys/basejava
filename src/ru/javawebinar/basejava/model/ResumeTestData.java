package ru.javawebinar.basejava.model;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;

public class ResumeTestData {
    public static void main(String[] args) {
        Resume resume = new Resume("uuid1", "FullName");
        EnumMap<ContactType, String> contacts = new EnumMap<>(ContactType.class);
        EnumMap<SectionType, AbstractType> sections = new EnumMap<>(SectionType.class);

        List<String> achievement = new ArrayList<>();
        achievement.add("С 2013 года: разработка проектов \"Разработка Web приложения\",\"Java Enterprise\", \"Многомодульный maven. Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). Удаленное взаимодействие (JMS/AKKA)\". Организация онлайн стажировок и ведение проектов. Более 1000 выпускников.");
        achievement.add("Реализация двухфакторной аутентификации для онлайн платформы управления проектами Wrike. Интеграция с Twilio, DuoSecurity, Google Authenticator, Jira, Zendesk.");
        achievement.add("Налаживание процесса разработки и непрерывной интеграции ERP системы River BPM. Интеграция с 1С, Bonita BPM, CMIS, LDAP. Разработка приложения управления окружением на стеке: Scala/Play/Anorm/JQuery. Разработка SSO аутентификации и авторизации различных ERP модулей, интеграция CIFS/SMB java сервера.");

        List<String> qualification = new ArrayList<>();
        qualification.add("JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2");
        qualification.add("Version control: Subversion, Git, Mercury, ClearCase, Perforce");
        qualification.add("DB: PostgreSQL(наследование, pgplsql, PL/Python), Redis (Jedis), H2, Oracle,");
        qualification.add("MySQL, SQLite, MS SQL, HSQLDB");

        List<String> experiences = new ArrayList<>();
        experiences.add("Java Online Projects\n" + "10/2013 - Сейчас\tАвтор проекта.\n" + "Создание, организация и проведение Java онлайн проектов и стажировок.");
        experiences.add("Wrike\n" + "10/2014 - 01/2016\tСтарший разработчик (backend)\n" + "Проектирование и разработка онлайн платформы управления проектами Wrike (Java 8 API, Maven, Spring, MyBatis, Guava, Vaadin, PostgreSQL, Redis). Двухфакторная аутентификация, авторизация по OAuth1, OAuth2, JWT SSO.\n");
        experiences.add("RIT Center\n" + "04/2012 - 10/2014\tJava архитектор\n" + "Организация процесса разработки системы ERP для разных окружений: релизная политика, версионирование, ведение CI (Jenkins), миграция базы (кастомизация Flyway), конфигурирование системы (pgBoucer, Nginx), AAA via SSO. Архитектура БД и серверной части системы. Разработка интергационных сервисов: CMIS, BPMN2, 1C (WebServices), сервисов общего назначения (почта, экспорт в pdf, doc, html). Интеграция Alfresco JLAN для online редактирование из браузера документов MS Office. Maven + plugin development, Ant, Apache Commons, Spring security, Spring MVC, Tomcat,WSO2, xcmis, OpenCmis, Bonita, Python scripting, Unix shell remote scripting via ssh tunnels, PL/Python");

        List<String> educations = new ArrayList<>();
        educations.add("Coursera\n" + "03/2013 - 05/2013\t\"Functional Programming Principles in Scala\" by Martin Odersky");
        educations.add("Luxoft\n" + "03/2011 - 04/2011\tКурс \"Объектно-ориентированный анализ ИС. Концептуальное моделирование на UML.\"");
        educations.add("Siemens AG\n" + "01/2005 - 04/2005\t3 месяца обучения мобильным IN сетям (Берлин)");

        AbstractType personal = new TextType("Аналитический склад ума, сильная логика, креативность, инициативность. Пурист кода и архитектуры.\n");
        AbstractType objective = new TextType("Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям");
        AbstractType achievements = new ListType(achievement);
        AbstractType qualifications = new ListType(qualification);
        AbstractType experience = new ListType(experiences);
        AbstractType education = new ListType(educations);

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

        for (AbstractType text : resume.getSections().values()) {
            System.out.println(text);
        }
    }
}
