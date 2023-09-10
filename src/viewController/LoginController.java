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
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modelClasses.User;

/**
 * FXML Controller class
 *
 * @author Yousef
 */
public class LoginController implements Initializable {

    @FXML
    private TextField loginTxtJobID;
    @FXML
    private PasswordField loginTxtPassword;
    @FXML
    private Button loginBtnLogin;
    @FXML
    private Button loginBtnRegister;
    @FXML
    private Label loginShowErrorMessage;
    private String jobID2;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

    }

    //function to check is corect login
    @FXML
    public void loginBtn(Event e) {
        User user = new User(); // Create a new User object
        String jobID = loginTxtJobID.getText(); // Get the jobID entered by the user
        String password = loginTxtPassword.getText(); // Get the password entered by the user

        user.setJobID(jobID); // Set the jobID in the User object
        user.setPassword(password); // Set the password in the User object

        UserController userController = new UserController(); // Create a new UserController object
        boolean isLogin = userController.isLogin(user); // Check if the user exists in the database

        // If the user exists in the database, show the dashboard GUI
        if (isLogin) {
            jobID2 = jobID;
            Node node = (Node) e.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.close();

            try {
                Parent root = FXMLLoader.load(getClass().getResource("/view/Dashboard.fxml"));
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.setTitle("Dashboard");
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } // If the user does not exist in the database, show an error message
        else {
            loginShowErrorMessage.setVisible(true);
        }
    }

    public String getJobID() {
        return jobID2;
    }

    //function to show register GUI
    @FXML
    public void registerBtn(Event e) {

        Node node = (Node) e.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("/view/Register.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Register");
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
