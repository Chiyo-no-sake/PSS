package ch.supsi.labingsw1.model;

public class Student {
    private String name;
    private String surname;
    private Course course;

    public Student(String name, String surname, Course course) {
        this.name = name;
        this.surname = surname;
        this.course = course;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return "Student: " + name + " " + surname + "\n" +
                "\tCourse: " + course + "";
    }
}
