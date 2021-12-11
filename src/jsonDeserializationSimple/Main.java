package jsonDeserializationSimple;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Arrays;

class Book {
    String name;
    int pages;
    String author;

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", pages=" + pages +
                ", author='" + author + '\'' +
                '}';
    }
}

class Library {
    Book[] books;

    @Override
    public String toString() {
        return "Library{" +
                "books=" + Arrays.toString(books) +
                '}';
    }
}

public class Main {
    static String JSON_STR = "{\n" +
            "   \"books\":[\n" +
            "      {\n" +
            "         \"name\":\"Carrie\",\n" +
            "         \"pages\":199,\n" +
            "         \"author\":\"Stephen King\"\n" +
            "      },\n" +
            "      {\n" +
            "         \"name\":\"It\",\n" +
            "         \"pages\":1138,\n" +
            "         \"author\":\"Stephen King\"\n" +
            "      }\n" +
            "   ]\n" +
            "}";

    public static void main(String[] args) {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Library myBooks = gson.fromJson(JSON_STR, Library.class);
        System.out.println(myBooks);
        System.out.println(JSON_STR);
    }
}
