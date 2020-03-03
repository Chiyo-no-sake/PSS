package ch.supsi.labingsw1;

import ch.supsi.labingsw1.model.Course;
import ch.supsi.labingsw1.model.Student;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;
import java.util.ArrayList;

public class StudentList extends Application {

    public static void main(String[] args) {


        Student s1 = new Student("Manuel", "Grgic");
        Student s2 = new Student("Michel", "Rosselli");
        Student s3 = new Student("Francesco", "Ingold");
        Student s4 = new Student("Thomas", "Scaltrito");

        List<Student> l1 = new ArrayList<>();
        List<Student> l2 = new ArrayList<>();

        l1.add(s1);
        l1.add(s2);
        l1.add(s3);

        l2.add(s2);
        l2.add(s3);
        l2.add(s4);

        Course c1 = new Course("Software", l1);
        Course c2 = new Course("C++", l2);

        System.out.println("Course name: " + c1);
        for(int i = 0; i < c1.getStudents().size(); i++){
            System.out.println("\t - " + c1.getStudents().get(i));
        }

        System.out.println("Course name: " + c2);
        for(int i = 0; i < c2.getStudents().size(); i++){
            System.out.println("\t - " + c2.getStudents().get(i));
        }

        Application.launch(args);
    }

    @Override
    public void start(Stage stage) {

        Student s1 = new Student("Manuel", "Grgic");
        Student s2 = new Student("Michel", "Rosselli");
        Student s3 = new Student("Francesco", "Ingold");
        Student s4 = new Student("Thomas", "Scaltrito");

        List<Student> l1 = new ArrayList<>();
        List<Student> l2 = new ArrayList<>();

        l1.add(s1);
        l1.add(s2);
        l1.add(s3);

        l2.add(s2);
        l2.add(s3);
        l2.add(s4);

        Course c1 = new Course("Software", l1);
        Course c2 = new Course("C++", l2);

        ObservableList<Student> studentList = FXCollections.observableArrayList();
        ObservableList<Course> courseList = FXCollections.observableArrayList(c1, c2);

        ListView<Course> courseLv = new ListView<>(courseList);
        ListView<Student> studentLv = new ListView<>(studentList);

        VBox studentSelection = new VBox();
        studentSelection.getChildren().add(studentLv);

        VBox courseSelection = new VBox();
        courseSelection.getChildren().add(courseLv);

        GridPane pane = new GridPane();
        pane.addColumn(1, studentSelection);
        pane.addColumn(0, courseSelection);

        courseLv.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Course>() {
            @Override
            public void changed(ObservableValue<? extends Course> observableValue, Course course, Course t1) {
                studentList.clear();
                studentList.addAll(t1.getStudents());
            }
        });

        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.setTitle("Courses and students");
        stage.show();
    }
}
