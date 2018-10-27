package ru.javawebinar.basejava;

import ru.javawebinar.basejava.model.Resume;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MainReflection {

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, NoSuchFieldException {
        Resume resume = new Resume();
        Method fieldToString = resume.getClass().getDeclaredMethod("toString");
        Field fieldUuid = resume.getClass().getDeclaredField("uuid");
        fieldUuid.setAccessible(true);
        fieldUuid.set(resume,"uuid1");
        System.out.println(fieldToString.invoke(resume));
    }
}