package GUI;

import java.io.IOException;
import java.util.ArrayList;

import javax.mail.MessagingException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import log.Log;
import logic.Access;
import logic.EmailControl;
import logic.FieldValidator;
import logic.DAO.CollaborationDAO;
import logic.DAO.ProfessorDAO;
import logic.DAO.StudentDAO;
import logic.classes.Collaboration;
import logic.classes.Professor;
import logic.classes.Student;

public class AddStudentController {
    private static final org.apache.log4j.Logger LOG = Log.getLogger(AddStudentController.class);

    @FXML
    private Button buttonHome;

    @FXML
    private void goToHome(ActionEvent event){
        FXMLLoader homePageLoader = new FXMLLoader(getClass().getResource("/GUI/professorHome.fxml"));
        ChangeWindowManager.changeWindowTo(event, homePageLoader);
    }

    @FXML 
    private Button buttonCollaboration;

    @FXML
    private void goToCollaborations(ActionEvent event){
        FXMLLoader collaborationsLoader = new FXMLLoader(getClass().getResource("/GUI/collaborationOptions.fxml"));
        ChangeWindowManager.changeWindowTo(event, collaborationsLoader);

    }
    @FXML
    private Button buttonStudents;
    
    @FXML
    private void goToStudents(ActionEvent event){
        FXMLLoader goToStudentsLoader = new FXMLLoader(getClass().getResource("/GUI/studentOptions.fxml"));
        ChangeWindowManager.changeWindowTo(event, goToStudentsLoader);

    }
    @FXML
    private Button buttonSettings;

    @FXML
    private void goToSettings(ActionEvent event){
        FXMLLoader settingsLoader = new FXMLLoader(getClass().getResource("/GUI/professorSettings.fxml"));
        ChangeWindowManager.changeWindowTo(event, settingsLoader);

    }

    @FXML
    private Button buttonMinimize;
    @FXML
    private void minimizeWindow(ActionEvent event){
        ChangeWindowManager.minimizeWindow(event);
    }

    @FXML
    private Button buttonClose;
    @FXML
    private void closeWindow(ActionEvent event){
        ChangeWindowManager.closeWindow(event);
    }

   

    @FXML
    private Button buttonBack;
    @FXML
    private void goBack(ActionEvent event){
        FXMLLoader studentOptionsLoader = new FXMLLoader(getClass().getResource("/GUI/studentOptions.fxml"));
        ChangeWindowManager.changeWindowTo(event, studentOptionsLoader);
    }

    @FXML
    private Button buttonLogout;
    @FXML
    private void logout(ActionEvent event){
        FXMLLoader loginLoader = new FXMLLoader(getClass().getResource("/GUI/login.fxml"));
        try {
            ChangeWindowManager.logout(event, loginLoader);
            UserSessionManager.getInstance().logoutAdmin();
        } catch (IOException ioException){
            LOG.error(ioException);
        }
    }


    @FXML
    private TextField textFieldEmailStudent;
    @FXML
    private Button buttonAddStudent;
    @FXML
    private TableView<Student> tableViewStudents;
    @FXML
    private TableColumn<Student, String> tableColumnId;
    @FXML
    private TableColumn<Student, String> tableColumnEmail;
    @FXML
    private void addStudent(){
        Professor professorData = new Professor();
        professorData = UserSessionManager.getInstance().getProfessorUserData();
        ProfessorDAO professorDAO = new ProfessorDAO();
        int professorId = professorDAO.getProfessorIdByUser(professorData.getUser());
        StudentDAO studentDAO = new StudentDAO();
        String email = textFieldEmailStudent.getText();
        if(!FieldValidator.isEmail(email)){
            Alert emptyFieldsAlert = new Alert(AlertType.ERROR);
            emptyFieldsAlert.setTitle("Campos vacíos");
            emptyFieldsAlert.setHeaderText("Campos vacíos");
            emptyFieldsAlert.setContentText("No se puede puede añadir al estudiante, no ha ingresado un correo válido");
            emptyFieldsAlert.show();
        }else if (studentDAO.isStudentRegistered(email) == true){
            if(studentDAO.isStudentAssignedToProfessor(email, professorId)){
                Alert studentDuplicatedAlert = new Alert(AlertType.INFORMATION);
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
                    tableViewStudents.getItems().addAll(students);
                } else {
                    Alert addingStudentErrorAlert = new Alert(AlertType.ERROR);
                    addingStudentErrorAlert.setTitle("Error conexión");
                    addingStudentErrorAlert.setHeaderText("Error conexión");
                    addingStudentErrorAlert.setContentText("Se perdió la conexión a la base de datos, inténtelo de nuevo más tarde");
                    addingStudentErrorAlert.show();
                }
            }


        } else{
                Student student = new Student();
                student.setEmail(email);
                student.setProfessorId(professorId);
                Access access = new Access();
                String password = access.passwordGenerator(8);
                student.setPassword(password);
                int result = studentDAO.addStudent(student);
                EmailControl emailControl = new EmailControl();
                try {
                    emailControl.sendEmail(email, "Agregado al sistema COIL-VIC", "Su contraseña para el sistema es: " + password);
                    Alert studentAddedAlert = new Alert(AlertType.INFORMATION);
                    studentAddedAlert.setTitle("Notificación correo");
                    studentAddedAlert.setHeaderText("Se ha enviado un correo al estudiante");
                    studentAddedAlert.setContentText("Se envió un correo al estudiante sobre su cuenta");
                    studentAddedAlert.show();
                } catch (MessagingException e) {
                    Alert addingStudentErrorAlert = new Alert(AlertType.ERROR);
                    addingStudentErrorAlert.setTitle("Error al enviar el correo");
                    addingStudentErrorAlert.setHeaderText("Error conexión");
                    addingStudentErrorAlert.setContentText("No se logro mandar el correo al estudiante, favor de comunicarle");
                    addingStudentErrorAlert.show();
                }
            if (result > 0){
                Alert studentAddedAlert = new Alert(AlertType.INFORMATION);
                studentAddedAlert.setTitle("Estudiante añadido");
                studentAddedAlert.setHeaderText("Estudiante añadido");
                studentAddedAlert.setContentText("Se ha añadido al estudiante exitosamente");
                studentAddedAlert.show();
                ArrayList<Student> students =  studentDAO.getStudentsByProfessorId(professorId);
                tableViewStudents.getItems().addAll(students);
            } else {
                Alert addingStudentErrorAlert = new Alert(AlertType.ERROR);
                addingStudentErrorAlert.setTitle("Error conexión");
                addingStudentErrorAlert.setHeaderText("Error conexión");
                addingStudentErrorAlert.setContentText("Se perdió la conexión a la base de datos, inténtelo de nuevo más tarde");
                addingStudentErrorAlert.show();
            }
        }
        
    }

    @FXML
    private Button buttonAssign;
    @FXML
    private void assignStudent(ActionEvent event){
        String email = selectedStudent.getEmail();
        Professor professorData = new Professor();
        professorData = UserSessionManager.getInstance().getProfessorUserData();
        ProfessorDAO professorDAO = new ProfessorDAO();
        int professorId = professorDAO.getProfessorIdByUser(professorData.getUser());
        CollaborationDAO collaborationDAO = new CollaborationDAO();
        ArrayList<Collaboration> collaborations =  collaborationDAO.searchCollaborationByStatusAndProfessorId("Activa", professorId);
        if(collaborations.isEmpty()){
            buttonAssign.setDisable(true);
            Alert assignErrorAlert = new Alert(AlertType.ERROR);
            assignErrorAlert.setTitle("Error colaboración");
            assignErrorAlert.setHeaderText("Error no tiene colaboración");
            assignErrorAlert.setContentText("No tiene una colaboración activa para asignar el estudiante");
        } else {
            int collaborationId = collaborations.get(0).getCollaborationId();
            if(collaborationDAO.isStudentAssignedToCollaboration(email, collaborationId)){
                Alert duplicatedStudentAlert = new Alert(AlertType.ERROR);
                duplicatedStudentAlert.setTitle("Estudiante duplicado");
                duplicatedStudentAlert.setHeaderText("Estudiante duplicado");
                duplicatedStudentAlert.setContentText("El estudiante ya se encuentra asignado a esta colaboración");
                duplicatedStudentAlert.show();
            } else {
                int result =  collaborationDAO.assignStudentToCollaboration(email, collaborationId);
                if (result > 0){
                    Alert studentAssignedAlert = new Alert(AlertType.INFORMATION);
                    studentAssignedAlert.setTitle("Estudiante asignado");
                    studentAssignedAlert.setHeaderText("Estudiante asignado");
                    studentAssignedAlert.setContentText("El estudiante ha sido asignado exitosamente");
                    studentAssignedAlert.show();
                } else{
                    Alert assignErrorAlert = new Alert(AlertType.ERROR);
                    assignErrorAlert.setTitle("Error conexión");
                    assignErrorAlert.setHeaderText("Error conexión");
                    assignErrorAlert.setContentText("Se perdió la conexión con la base de datos, inténtelo de nuevo más tarde");
                }
            }
        }
        
        

    }
    @FXML
    private Label labelUser;
    private Student selectedStudent;
    @FXML
    private void initialize(){
        Professor professorData = new Professor();
        professorData = UserSessionManager.getInstance().getProfessorUserData();
        labelUser.setText(professorData.getName());
        StudentDAO studentDAO = new StudentDAO();
        ProfessorDAO professorDAO = new ProfessorDAO();
        int professorId = professorDAO.getProfessorIdByUser(professorData.getUser());
        ArrayList<Student> students =  studentDAO.getStudentsByProfessorId(professorId);
        
        tableViewStudents.getItems().addAll(students);

        tableViewStudents.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            selectedStudent = newValue;
        });

        buttonAssign.setDisable(true);

        tableViewStudents.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            buttonAssign.setDisable(newValue == null);
            
        });

    }

    

}
