package ch.supsi.labingsw1;

import ch.supsi.labingsw1.model.Colors;
import ch.supsi.labingsw1.model.Course;
import ch.supsi.labingsw1.model.Student;
import ch.supsi.labingsw1.model.StyledSelectableCell;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * TODO:
 *  BUG -> when clicking the second time to a course, it load another reversed list
 *  of student at the bottom of the view.
 */

public class StudentList extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) {
        // Default not selected color
        Random r = new Random();

        int rand1 = r.nextInt(6)+1;
        int rand2 = r.nextInt(6)+1;

        Colors STUD_COL = Colors.values()[rand1];
        Colors COUR_COL = Colors.values()[rand2];

        // Create Students
        Student s1 = new Student("Manuel", "Grgic");
        Student s2 = new Student("Michel", "Rosselli");
        Student s3 = new Student("Francesco", "Ingold");
        Student s4 = new Student("Luca", "Pasini");
        Student s5 = new Student("Matteo", "Miggiano");
        Student s6 = new Student("Giorgio", "Vanni");

        // Fill students arrays
        List<Student> l1 = new ArrayList<>();
        List<Student> l2 = new ArrayList<>();

        l1.add(s1);
        l1.add(s2);
        l1.add(s3);

        l2.add(s3);
        l2.add(s4);
        l2.add(s5);
        l2.add(s6);

        // Create courses
        ArrayList<Course> courses = new ArrayList<>();
        courses.add(new Course("Software", l1));
        courses.add(new Course("C++", l2));

        // stdout courses
        for(Course c1 : courses) {
            System.out.println("Course name: " + c1);
            for (int i = 0; i < c1.getStudents().size(); i++) {
                System.out.println("\t - " + c1.getStudents().get(i));
            }
        }

        // Creating 2 observable lists
        ObservableList<Student> studentList = FXCollections.observableArrayList();
        ObservableList<Course> courseList = FXCollections.observableArrayList(courses);

        // Creating listview for stuxents and courses
        ListView<Course> courseLv = new ListView<>(courseList);
        ListView<Student> studentLv = new ListView<>(studentList);


        // Setting custom colored cells
        // TODO bug only present with this two lines uncommented
        courseLv.setCellFactory(
                courseListView -> new StyledSelectableCell<>(STUD_COL)
        );

        studentLv.setCellFactory(
                studentListView -> new StyledSelectableCell<>(COUR_COL)
        );

        VBox studentSelection = new VBox();
        studentSelection.getChildren().add(studentLv);

        VBox courseSelection = new VBox();
        courseSelection.getChildren().add(courseLv);

        GridPane pane = new GridPane();
        pane.addColumn(1, studentSelection);
        pane.addColumn(0, courseSelection);

        courseLv.getSelectionModel().selectedItemProperty().addListener((observableValue, course, t1) -> {
            studentList.clear();
            studentList.addAll(t1.getStudents());
        });

        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.setTitle("Courses and students");
        stage.show();
    }
}
