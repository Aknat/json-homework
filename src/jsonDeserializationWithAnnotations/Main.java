package jsonDeserializationWithAnnotations;

import java.io.*;

import static jsonDeserializationWithAnnotations.StudentHelper.*;

public class Main {
    public static void main(String[] args) {

//        CourseBook sociology = new CourseBook(1, "The Art of Computer Programming", "Donald Knuth");
//        Student a = new Student("Darth", "Vader", 15, "science", 666, sociology);
//        System.out.println(saveToJSON(a));

        File file = new File("fromFile.txt");
        System.out.println("Student with annotated profile parameters:" + "\n" + loadFromJSON(file));
    }
}
