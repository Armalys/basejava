package ru.javawebinar.basejava.storage;

import org.junit.Before;
import org.junit.Test;
import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.*;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

public abstract class AbstractStorageTest {
    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String DUMMY = "dummy";

    private static final Resume RESUME_1;
    private static final Resume RESUME_2;
    private static final Resume RESUME_3;
    private static final Resume RESUME_DUMMY;

    protected Storage storage;

    static {
        RESUME_1 = new Resume(UUID_1, "full name 1");

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

        Section personal = new TextSection("Аналитический склад ума, сильная логика, креативность, инициативность. Пурист кода и архитектуры.");
        Section objective = new TextSection("Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям");
        Section achievements = new ListSection(achievement);
        Section qualifications = new ListSection(qualification);
        Section experience = new OrganizationSection(experiences);
        Section education = new OrganizationSection(educations);

        Map<ContactType, String> contacts = RESUME_1.getContacts();
        contacts.put(ContactType.PHONE, "+7(921) 855-0482");
        contacts.put(ContactType.SKYPE, "grigory.kislin");
        contacts.put(ContactType.EMAIL, "gkislin@yandex.ru");
        contacts.put(ContactType.LINKEDIN, "https://www.linkedin.com/in/gkislin");
        contacts.put(ContactType.GITHUB, "https://github.com/gkislin");
        contacts.put(ContactType.STACKOVERFLOW, "https://stackoverflow.com/users/548473");
        contacts.put(ContactType.HOMEPAGE, "http://gkislin.ru/");

        RESUME_1.setContacts(contacts);

        Map<SectionType, Section> sections = RESUME_1.getSections();
        sections.put(SectionType.PERSONAL, personal);
        sections.put(SectionType.OBJECTIVE, objective);
        sections.put(SectionType.ACHIEVEMENT, achievements);
        sections.put(SectionType.QUALIFICATIONS, qualifications);
        sections.put(SectionType.EXPERIENCE, experience);
        sections.put(SectionType.EDUCATION, education);

        RESUME_1.setSections(sections);

        RESUME_2 = new Resume(UUID_2, "full name 2");

        RESUME_2.getContacts().put(ContactType.SKYPE, "Mashnert");
        RESUME_2.getContacts().put(ContactType.GITHUB, "Armalys");
        RESUME_2.getContacts().put(ContactType.PHONE, "890450143551");

        RESUME_2.getSections().put(SectionType.PERSONAL, new TextSection("Адекватность, внимательность, усидчивость"));
        RESUME_2.getSections().put(SectionType.OBJECTIVE, new TextSection("Студент курса Base Java"));

        List<Organization> spbgasuR2 = new ArrayList<>();
        spbgasuR2.add(new Organization("СПБГАСУ", "www.spbgasu.ru",
                YearMonth.of(2011, 9), YearMonth.of(2015, 6),
                "ПГС", null));

        RESUME_2.getSections().put(SectionType.EDUCATION, new OrganizationSection(spbgasuR2));

        RESUME_3 = new Resume(UUID_3, "full name 3");

        RESUME_3.getContacts().put(ContactType.PHONE, "8900400");
        RESUME_3.getContacts().put(ContactType.SKYPE, "Kerty");

        RESUME_DUMMY = new Resume(DUMMY, "full name dummy");

        RESUME_DUMMY.getContacts().put(ContactType.SKYPE, DUMMY);
    }

    protected AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() {
        storage.clear();
        storage.save(RESUME_1);
        storage.save(RESUME_2);
        storage.save(RESUME_3);
    }

    @Test
    public void size() throws Exception {
        assertSize(3);
    }

    @Test
    public void clear() {
        storage.clear();
        assertSize(0);
    }

    @Test
    public void save() {
        storage.save(RESUME_DUMMY);
        assertSize(4);
        assertEquals(RESUME_DUMMY, storage.get(DUMMY));
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() {
        storage.save(RESUME_1);
    }

    @Test
    public void update() {
        Resume newResume = new Resume(UUID_1, "New Name");
        storage.update(newResume);
        assertSame(newResume, storage.get(UUID_1));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        storage.update(RESUME_DUMMY);
    }

    @Test
    public void get() {
        assertGet(RESUME_1);
        assertGet(RESUME_2);
        assertGet(RESUME_3);
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get(DUMMY);
    }

    @Test
    public void getAllSorted() {
        List<Resume> storageExpected = Arrays.asList(RESUME_1, RESUME_2, RESUME_3);
        List<Resume> storageActual = storage.getAllSorted();
        assertEquals(storageExpected.size(), storageActual.size());
        assertEquals(storageExpected, storageActual);
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() {
        storage.delete(UUID_1);
        assertSize(2);
        storage.get(UUID_1);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() {
        storage.delete(DUMMY);
    }

    private void assertGet(Resume resume) {
        assertEquals(resume, storage.get(resume.getUuid()));
    }

    private void assertSize(int size) {
        assertEquals(size, storage.size());
    }
}