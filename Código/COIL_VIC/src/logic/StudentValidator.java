package logic;

import java.util.ArrayList;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableView;
import logic.DAO.StudentDAO;
import logic.classes.Student;

public class StudentValidator {

    public static boolean validateStudentRegister(StudentDAO studentDAO, Student student, TableView<Student> tableViewStudents){
        String email = student.getEmail();
        int professorId = student.getProfessorId();
        if(studentDAO.isStudentRegistered(email) == true){
            if(studentDAO.isStudentAssignedToProfessor(email, professorId)){
                Alert studentDuplicatedAlert = new Alert(AlertType.ERROR);
                studentDuplicatedAlert.setTitle("Estudiante duplicado");
                studentDuplicatedAlert.setHeaderText("Estudiante duplicado");
                studentDuplicatedAlert.setContentText("No se puede añadir al estudiante, ya se encuentra agregado");
                studentDuplicatedAlert.show();
            } else{
                int result = studentDAO.changeProfessorAssigned(professorId, email);
                if (result > 0){
                    Alert studentAddedAlert = new Alert(AlertType.INFORMATION);
                    studentAddedAlert.setTitle("Estudiante añadido");
                    studentAddedAlert.setHeaderText("Estudiante añadido");
                    studentAddedAlert.setContentText("Se ha añadido al estudiante exitosamente");
                    studentAddedAlert.show();
                    ArrayList<Student> students =  studentDAO.getStudentsByProfessorId(professorId);
                    tableViewStudents.getItems().clear();
                    tableViewStudents.getItems().addAll(students);
                } else {
                    Alert addingStudentErrorAlert = new Alert(AlertType.ERROR);
                    addingStudentErrorAlert.setTitle("Error conexión");
                    addingStudentErrorAlert.setHeaderText("Error conexión");
                    addingStudentErrorAlert.setContentText("Se perdió la conexión a la base de datos, inténtelo de nuevo más tarde");
                    addingStudentErrorAlert.show();
                }
            }
            return true;
        } else {
            return false;
        }
    }

    public static void showAlert(AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(title);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public static void showAlerts(int result){
        if (result > 0){
            showAlert(AlertType.INFORMATION, "Estudiante asignado", "El estudiante ha sido asignado exitosamente");
        } else{
            showAlert(AlertType.ERROR, "Error conexión", "Se perdió la conexión a la base de datos, inténtelo de nuevo más tarde");
        }
    }



}
