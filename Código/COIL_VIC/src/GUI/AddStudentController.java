package GUI;

import java.io.IOException;
import java.util.ArrayList;

import javax.mail.MessagingException;

import dataAccess.DatabaseConnectionChecker;
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
import logic.StudentValidator;
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
        FXMLLoader homePageLoader = new FXMLLoader(getClass().getResource("/GUI/ProfessorHomeWindow.fxml"));
        ChangeWindowManager.changeWindowTo(event, homePageLoader);
    }

    @FXML 
    private Button buttonCollaboration;

    @FXML
    private void goToCollaborations(ActionEvent event){
        FXMLLoader collaborationsLoader = new FXMLLoader(getClass().getResource("/GUI/CollaborationOptionsWindow.fxml"));
        ChangeWindowManager.changeWindowTo(event, collaborationsLoader);

    }
    @FXML
    private Button buttonStudents;
    
    @FXML
    private void goToStudents(ActionEvent event){
        FXMLLoader goToStudentsLoader = new FXMLLoader(getClass().getResource("/GUI/StudentOptionsWindow.fxml"));
        ChangeWindowManager.changeWindowTo(event, goToStudentsLoader);

    }
    @FXML
    private Button buttonSettings;

    @FXML
    private void goToSettings(ActionEvent event){
        FXMLLoader settingsLoader = new FXMLLoader(getClass().getResource("/GUI/ProfessorSettingsWindow.fxml"));
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
        FXMLLoader studentOptionsLoader = new FXMLLoader(getClass().getResource("/GUI/StudentOptionsWindow.fxml"));
        ChangeWindowManager.changeWindowTo(event, studentOptionsLoader);
    }

    @FXML
    private Button buttonLogout;
    @FXML
    private void logout(ActionEvent event){
        FXMLLoader loginLoader = new FXMLLoader(getClass().getResource("/GUI/LoginWindow.fxml"));
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
        if(!DatabaseConnectionChecker.isDatabaseConnected()){
            DatabaseConnectionChecker.showNoConnectionDialog();
            return;
        }
        Professor professorData = new Professor();
        professorData = UserSessionManager.getInstance().getProfessorUserData();
        ProfessorDAO professorDAO = new ProfessorDAO();
        int professorId = professorDAO.getProfessorIdByUser(professorData.getUser());
        String email = textFieldEmailStudent.getText();
        if(!FieldValidator.isEmail(email)){
            StudentValidator.showAlert(AlertType.ERROR, "Correo no válido", "No se puede añadir al estudiante, el correo no es válido");
            return;
        }

        StudentDAO studentDAO = new StudentDAO();
        if (studentDAO.isStudentRegistered(email)){
            handleExistingStudent(studentDAO, email, professorId);
        }else{
            registerNewStudent(studentDAO, email, professorId);
        }
        
    }

    @FXML
    private Button buttonAssign;
    @FXML
    private void assignStudent(ActionEvent event){
        if(!DatabaseConnectionChecker.isDatabaseConnected()){
            DatabaseConnectionChecker.showNoConnectionDialog();
            return;
        }
        String email = selectedStudent.getEmail();
        Professor professorData = new Professor();
        professorData = UserSessionManager.getInstance().getProfessorUserData();
        ProfessorDAO professorDAO = new ProfessorDAO();
        int professorId = professorDAO.getProfessorIdByUser(professorData.getUser());
        CollaborationDAO collaborationDAO = new CollaborationDAO();
        ArrayList<Collaboration> collaborations =  collaborationDAO.searchCollaborationByStatusAndProfessorId("Publicada", professorId);
        if(collaborations.isEmpty()){
            StudentValidator.showAlert(AlertType.ERROR, "Error colaboración", "No tiene una colaboración publicada para asignar el estudiante");
            buttonAssign.setDisable(true);
        } else {
            int collaborationId = collaborations.get(0).getCollaborationId();
            if(collaborationDAO.isStudentAssignedToCollaboration(email, collaborationId)){
                StudentValidator.showAlert(AlertType.INFORMATION, "Estudiante duplicado", "No se puede añadir al estudiante, ya se encuentra asignado a esta colaboración");
            } else {
                int result =  collaborationDAO.assignStudentToCollaboration(email, collaborationId);
                StudentValidator.showAlerts(result);
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
        if(!DatabaseConnectionChecker.isDatabaseConnected()){
            DatabaseConnectionChecker.showNoConnectionDialog();
            return;
        }
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

    
    private void handleExistingStudent(StudentDAO studentDAO, String email, int professorId) {
        if (studentDAO.isStudentAssignedToProfessor(email, professorId)) {
            StudentValidator.showAlert(AlertType.INFORMATION, "Estudiante duplicado", "No se puede añadir al estudiante, ya se encuentra agregado");
        } else {
            int result = studentDAO.changeProfessorAssigned(professorId, email);
            if (result > 0) {
                StudentValidator.showAlert(AlertType.INFORMATION, "Estudiante añadido", "Se ha añadido al estudiante exitosamente");
                updateStudentTable(professorId);
            } else {
                StudentValidator.showAlert(AlertType.ERROR, "Error conexión", "Se perdió la conexión a la base de datos, inténtelo de nuevo más tarde");
            }
        }
    }

    

    private void updateStudentTable(int professorId) {
        StudentDAO studentDAO = new StudentDAO();
        ArrayList<Student> students = studentDAO.getStudentsByProfessorId(professorId);
        tableViewStudents.getItems().clear();
        tableViewStudents.getItems().addAll(students);
    }

    private void registerNewStudent(StudentDAO studentDAO, String email, int professorId) {
        Student student = new Student();
        student.setEmail(email);
        student.setProfessorId(professorId);
        Access access = new Access();
        String password = access.passwordGenerator(8);
        student.setPassword(password);
    
        int result = studentDAO.addStudent(student);
        if (result > 0) {
            StudentValidator.showAlert(AlertType.INFORMATION, "Estudiante añadido", "Se ha añadido al estudiante exitosamente");
            updateStudentTable(professorId);
            sendEmailToStudent(email, student.getPassword());
        } else {
            StudentValidator.showAlert(AlertType.ERROR, "Error conexión", "Se perdió la conexión a la base de datos, inténtelo de nuevo más tarde");
        }
    }

    private void sendEmailToStudent(String email, String password) {
        EmailControl emailControl = new EmailControl();
        try {
            emailControl.sendEmail(email, "Agregado al sistema COIL-VIC", "Su contraseña para el sistema es: " + password);
            StudentValidator.showAlert(AlertType.INFORMATION, "Notificación correo", "Se envió un correo al estudiante sobre su cuenta");
        } catch (MessagingException e) {
            StudentValidator.showAlert(AlertType.ERROR, "Error al enviar el correo",  "No se logró mandar el correo al estudiante, favor de comunicarle sus datos de acceso \nContraseña: " + password);
            LOG.error(e);
        }
    }
}