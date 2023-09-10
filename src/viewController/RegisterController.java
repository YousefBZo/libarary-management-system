/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package viewController;

import controller.UserController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modelClasses.User;

/**
 * FXML Controller class
 *
 * @author Yousef
 */
public class RegisterController implements Initializable {

    @FXML
    private TextField registerTxtUsername;
    @FXML
    private TextField registerTxtEmail;
    @FXML
    private TextField registerTxtJobID;
    @FXML
    private PasswordField registerTxtPassword;
    @FXML
    private Button registerBtnRegister;
    @FXML
    private ComboBox<Integer> registerAge;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Populate the registerAge ComboBox with numbers from 10 to 60
        ObservableList<Integer> ageList = FXCollections.observableArrayList();
        for (int i = 10; i <= 60; i++) {
            ageList.add(i);
        }
        registerAge.setItems(ageList);
    }

    @FXML
    public void isRegister(Event e) {
        String username = registerTxtUsername.getText();
        String email = registerTxtEmail.getText();
        String jobID = registerTxtJobID.getText();
        String password = registerTxtPassword.getText();
        Integer age = registerAge.getValue();

        boolean isJobIDValid = jobID.matches("[0-9]+");
        boolean isUsernameValid = username.matches("[a-zA-Z0-9_]+");
        boolean isEmailValid = email.matches("^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$");
        boolean isPasswordValid = password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$");
        boolean isAgeValid = (age != null) && (age >= 10 && age <= 60);

        if (!isUsernameValid) {
            showError("Invalid username");
        } else if (!isEmailValid) {
            showError("Invalid email");
        } else if (!isJobIDValid) {
            showError("Invalid job ID");
        } else if (!isPasswordValid) {
            showError("Invalid password");
        } else if (!isAgeValid) {
            showError("Invalid age");
        } else {
            User user = new User();
            user.setJobID(jobID);
            user.setUsername(username);
            user.setEmail(email);
            user.setPassword(password);
            user.setAge(age);
            UserController userController = new UserController();
            userController.addUser(user);
            showSuccess("Registration successful");

            // Close the current window and open the login window
            Node node = (Node) e.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.close();
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.setTitle("Register");
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(RegisterController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showSuccess(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void backToLogin(Event e) {
        Node node = (Node) e.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Register");
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(RegisterController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
