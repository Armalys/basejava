package ru.javawebinar.basejava.storage.serializer;

import ru.javawebinar.basejava.model.*;
import ru.javawebinar.basejava.util.LocalDateAdapter;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DataStreamSerializer implements StreamSerializer {
    private LocalDateAdapter localDateAdapter = new LocalDateAdapter();

    @Override
    public void doWrite(Resume r, OutputStream os) throws IOException {

        try (DataOutputStream dos = new DataOutputStream(os)) {
            dos.writeUTF(r.getUuid());
            dos.writeUTF(r.getFullName());

            Map<ContactType, String> contacts = r.getContacts();
            dos.writeInt(contacts.size());
            for (Map.Entry<ContactType, String> entry : contacts.entrySet()) {
                dos.writeUTF(entry.getKey().name());
                dos.writeUTF(entry.getValue());
            }

            Map<SectionType, AbstractSection> sections = r.getSections();
            dos.writeInt(sections.size());
            for (Map.Entry<SectionType, AbstractSection> entry : sections.entrySet()) {
                switch (entry.getKey().name()) {
                    case "OBJECTIVE":
                    case "PERSONAL":
                        dos.writeUTF(entry.getKey().name());
                        dos.writeUTF(((TextSection) entry.getValue()).getContent());
                        break;
                    case "ACHIEVEMENT":
                    case "QUALIFICATIONS":
                        dos.writeUTF(entry.getKey().name());
                        List<String> listOfStrings = ((ListSection) entry.getValue()).getItems();
                        dos.writeInt(listOfStrings.size());
                        for (String contentOfTextSection : listOfStrings) {
                            dos.writeUTF(contentOfTextSection);
                        }
                        break;
                    case "EXPERIENCE":
                    case "EDUCATION":
                        dos.writeUTF(entry.getKey().name());
                        List<Organization> listOfOrganizations = ((OrganizationSection) entry.getValue()).getOrganizations();
                        dos.writeInt(listOfOrganizations.size());

                        for (Organization organization : listOfOrganizations) {
                            dos.writeUTF(organization.getHomePage().getName());
                            dos.writeUTF(organization.getHomePage().getUrl());
                            List<Organization.Position> positions = organization.getPosition();
                            dos.writeInt(positions.size());
                            for (Organization.Position position : positions) {
                                try {
                                    dos.writeUTF(localDateAdapter.marshal(position.getStartDate()));
                                } catch (Exception e) {
                                    System.out.println("!!!!" + e);
                                }

                                try {
                                    dos.writeUTF(localDateAdapter.marshal(position.getEndDate()));
                                } catch (Exception e) {
                                    System.out.println("!!!!" + e);
                                }

                                dos.writeUTF(position.getTitle());

                                if (position.getDescription() != null) {
                                    dos.writeUTF(position.getDescription());
                                } else {
                                    dos.writeUTF("");
                                }
                            }
                        }
                        break;
                }
            }
        }
    }

    @Override
    public Resume doRead(InputStream is) throws IOException {
        try (DataInputStream dis = new DataInputStream(is)) {
            String uuid = dis.readUTF();
            String fullName = dis.readUTF();
            Resume resume = new Resume(uuid, fullName);

            int size = dis.readInt();
            for (int i = 0; i < size; i++) {
                resume.addContact(ContactType.valueOf(dis.readUTF()), dis.readUTF());
            }

            size = dis.readInt();

            for (int i = 0; i < size; i++) {
                switch (dis.readUTF()) {
                    case "OBJECTIVE":
                        resume.addSection(SectionType.OBJECTIVE, new TextSection(dis.readUTF()));
                        break;
                    case "PERSONAL":
                        resume.addSection(SectionType.PERSONAL, new TextSection(dis.readUTF()));
                        break;
                    case "ACHIEVEMENT":
                        int sizeOfAchievements = dis.readInt();
                        List<String> listOfAchievements = new ArrayList<>();
                        for (int j = 0; j < sizeOfAchievements; j++) {
                            listOfAchievements.add(dis.readUTF());
                        }
                        resume.addSection(SectionType.ACHIEVEMENT, new ListSection(listOfAchievements));
                        break;
                    case "QUALIFICATIONS":
                        int sizeQualifications = dis.readInt();
                        List<String> listOfQualifications = new ArrayList<>();
                        for (int j = 0; j < sizeQualifications; j++) {
                            listOfQualifications.add(dis.readUTF());
                        }
                        resume.addSection(SectionType.QUALIFICATIONS, new ListSection(listOfQualifications));
                        break;
                    case "EXPERIENCE":
                        int sizeOfOrgOfExp = dis.readInt();
                        List<Organization> listOfOrganizations = new ArrayList<>();
                        readOrganizations(dis, localDateAdapter, sizeOfOrgOfExp, listOfOrganizations);
                        resume.addSection(SectionType.EXPERIENCE, new OrganizationSection(listOfOrganizations));
                        break;

                    case "EDUCATION":
                        int sizeOfEdu = dis.readInt();
                        List<Organization> listOfEducations = new ArrayList<>();
                        readOrganizations(dis, localDateAdapter, sizeOfEdu, listOfEducations);
                        resume.addSection(SectionType.EDUCATION, new OrganizationSection(listOfEducations));
                        break;
                }
            }
            System.out.println(resume);
            return resume;
        }
    }

    private void readOrganizations(DataInputStream dis, LocalDateAdapter localDateAdapter, int sizeOfOrgOfExp, List<Organization> listOfOrg) throws IOException {
        for (int j = 0; j < sizeOfOrgOfExp; j++) {
            Organization organization = new Organization(new Link(dis.readUTF(), dis.readUTF()));
            int sizeOfStages = dis.readInt();
            List<Organization.Position> listOfStages = new ArrayList<>();
            for (int k = 0; k < sizeOfStages; k++) {
                try {
                    listOfStages.add(new Organization.Position(
                            localDateAdapter.unmarshal(dis.readUTF()),
                            localDateAdapter.unmarshal(dis.readUTF()),
                            dis.readUTF(),
                            dis.readUTF())
                    );
                } catch (Exception e) {
                    e.printStackTrace();
                }
                organization.setPosition(listOfStages);
            }
            listOfOrg.add(organization);
        }
    }
}
