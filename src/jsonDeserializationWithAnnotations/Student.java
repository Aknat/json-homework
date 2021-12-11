package jsonDeserializationWithAnnotations;

public class Student {
    @Save private String firstName;
    @Save private String lastName;
    private int age;
    private String groupName;
    private int sheetId;
    @Save private CourseBook courseBook;

    public Student(String firstName, String lastName, int age, String groupName, int sheetId, CourseBook courseBook) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.groupName = groupName;
        this.sheetId = sheetId;
        this.courseBook = courseBook;
    }

    public Student() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public int getSheetId() {
        return sheetId;
    }

    public void setSheetId(int sheetId) {
        this.sheetId = sheetId;
    }

    public CourseBook getCourseBook() {
        return courseBook;
    }

    public void setCourseBook(CourseBook courseBook) {
        this.courseBook = courseBook;
    }

    @Override
    public String toString() {
        return "Student{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", groupName='" + groupName + '\'' +
                ", sheetId=" + sheetId +
                ", courseBook=" + courseBook +
                '}';
    }
}


