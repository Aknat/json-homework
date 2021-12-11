package jsonDeserializationToClass;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public abstract class ClassConstructor {

    public static void generateClass(String className) {

        String result = "";
        Map<String, Object> map = new HashMap<>();
        try (Reader reader = new FileReader("jsonForClass.txt")) {
            char[] chars = new char[100];
            int readChars = 0;
            for (; ; ) {
                readChars = reader.read(chars);
                if (readChars <= 0) {
                    break;
                }
                result += new String(chars, 0, readChars);
            }

            map = new Gson().fromJson(result, new TypeToken<HashMap<String, String>>() {
            }.getType());
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("json from file converted into the following map:" + "\n" + map);

        File file = new File("/Users/Jupiter/Documents/Development/education/java/progua/pro/json-homework/src/jsonDeserializationToClass/" +
                className + ".java");
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String temp = file.getParent();
        String[] parts = temp.split("/");
        String packageName = parts[parts.length - 1];

        try (PrintWriter pw = new PrintWriter(new FileWriter(file))) // перезапись
        {
            pw.println("package " + packageName + ";" + "\n" +
                    "\n" +
                    "public class " + className + " {" + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }

        String fieldNameLast = null;
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            String fieldName = entry.getKey();
            String fieldValue = (String) entry.getValue();
            String fieldType;

            if (fieldValue.matches("^[0-9]*$")) fieldType = "int ";
            else if (fieldValue.contains("true") || fieldValue.contains("false")) fieldType = "boolean ";
            else fieldType = "String ";

            try (PrintWriter pw = new PrintWriter(new FileWriter(file, true))) // дозапись
            {
                pw.println("\t" + fieldType + fieldName + ";");
            } catch (IOException e) {
                e.printStackTrace();
            }
            fieldNameLast = fieldName;
        }


        try (PrintWriter pw = new PrintWriter(new FileWriter(file, true))) // дозапись
        {
            pw.print("\n" + "\t" + "public " + className + "(");
        } catch (IOException e) {
            e.printStackTrace();
        }


        for (Map.Entry<String, Object> entry : map.entrySet()) {
            String fieldName = entry.getKey();
            String fieldValue = (String) entry.getValue();
            String fieldType;

            if (fieldValue.matches("^[0-9]*$")) fieldType = "int ";
            else if (fieldValue.contains("true") || fieldValue.contains("false")) fieldType = "boolean ";
            else fieldType = "String ";

            if (!entry.getKey().equals(fieldNameLast)) {
                try (PrintWriter pw = new PrintWriter(new FileWriter(file, true))) // дозапись
                {
                    pw.print(fieldType + fieldName + ", ");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                try (PrintWriter pw = new PrintWriter(new FileWriter(file, true))) // дозапись
                {
                    pw.print(fieldType + fieldName + ") {");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        for (Map.Entry<String, Object> entry : map.entrySet()) {
            String fieldName = entry.getKey();
            try (PrintWriter pw = new PrintWriter(new FileWriter(file, true))) // дозапись
            {
                pw.print("\n" + "\t" + "\t" + "this." + fieldName + " = " + fieldName + ";");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try (PrintWriter pw = new PrintWriter(new FileWriter(file, true))) // дозапись
        {
            pw.println("\n" + "\t" + "}" +
                    "\n" + "}");
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }
}
