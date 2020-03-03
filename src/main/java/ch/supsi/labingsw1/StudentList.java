package ch.supsi.labingsw1;

import ch.supsi.labingsw1.model.Course;
import ch.supsi.labingsw1.model.Student;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.ArrayList;

public class StudentList extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        ListView studentList = new ListView<Student>();
        ListView courseList = new ListView<Course>();

        ArrayList<Course> arrayList = new ArrayList();
        ArrayList<Student> studentArrayList = new ArrayList<>();

        arrayList.add(new Course("Matematica"));
        arrayList.add(new Course("Informatica"));

        Student s1 = new Student("Luca", "Pasini", arrayList.get(0));
        Student s2 =  new Student("gabri", "Schinchi", arrayList.get(1));

        studentArrayList.add(s2);
        studentArrayList.add(s1);

        ObservableList<Student> studentsObs = FXCollections.observableArrayList(studentArrayList);
        ObservableList<Course> courseObs = FXCollections.observableArrayList(arrayList);

        studentList.setItems(studentsObs);

        StackPane root = new StackPane();
        root.getChildren().add(studentList);
        primaryStage.setScene(new Scene(root,200,250));
        primaryStage.show();
    }
}
