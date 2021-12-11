package jsonDeserializationWithAnnotations;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public abstract class StudentHelper {

    public static String saveToJSON(Student student) {
        Class<?> cls = student.getClass();
        Field[] fields = cls.getDeclaredFields();
        Map<String, Object> map = new HashMap<>();
        for (Field f : fields) {
            if (f.isAnnotationPresent(Save.class)) {

                String fieldName = "";
                Object fieldValue = null;
                try {
                    fieldName = f.getName();
                    Field field = cls.getDeclaredField(fieldName);
                    field.setAccessible(true);
                    fieldValue = field.get(student);
                    map.put(fieldName, fieldValue);
                } catch (IllegalAccessException | NoSuchFieldException e) {
                    e.printStackTrace();
                }
            }
        }
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String result = gson.toJson(map);
        saveToFile(result);
        return result;
    }


    public static void saveToFile(String text) {
        File file = new File("toFile.txt");
        try (Writer writer = new FileWriter(file)) {
            writer.write(text);
        } catch (IOException e) {
            try {
                throw e;
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }


    public static Student loadFromJSON(File file) {
        String result = "";
        Student student = new Student();
        try (Reader read = new FileReader(file)) {
            char[] chars = new char[100];
            int readChars = 0;
            for (; ; ) {
                readChars = read.read(chars);
                if (readChars <= 0) {
                    break;
                }
                result += new String(chars, 0, readChars);
            }

            Gson gson = new GsonBuilder().create();
            student = gson.fromJson(result, Student.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Student in the file with the full profile:" + "\n" + student);

        Class<?> cls = student.getClass();
        Field[] fields = cls.getDeclaredFields();
        Student studentResult = new Student();
        for (Field f : fields) {
            if (f.isAnnotationPresent(Save.class)) {

                String fieldName = "";
                Object fieldValue = null;
                try {
                    fieldName = f.getName();
                    Field field = cls.getDeclaredField(fieldName);
                    field.setAccessible(true);
                    fieldValue = field.get(student);
                    if (fieldName.equals("firstName")) studentResult.setFirstName(fieldValue.toString());
                    if (fieldName.equals("lastName")) studentResult.setLastName(fieldValue.toString());
                    if (fieldName.equals("groupName")) studentResult.setGroupName(fieldValue.toString());
                    if (fieldName.equals("age")) studentResult.setAge((int) fieldValue);
                    if (fieldName.equals("sheetId")) studentResult.setSheetId((int) fieldValue);
                    if (fieldName.equals("courseBook")) {
                        studentResult.setCourseBook(student.getCourseBook());
                    }

                } catch (NoSuchFieldException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return studentResult;
    }
}
