/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import static controller.ConnectionDataBase.writeLog;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelClasses.User;

/**
 *
 * @author Yousef
 */
public class UserController {

    // function to add user and add it to the database using preparedStatement
    public void addUser(User user) {
        try {
            PreparedStatement insertUser = ConnectionDataBase.openConnection().prepareStatement("INSERT INTO `user`( `username`, `email`, `jobID`, `password`, `age`) VALUES (?,?,?,?,?)");
            insertUser.setString(1, user.getUsername());
            insertUser.setString(2, user.getEmail());
            insertUser.setString(3, user.getJobID());
            insertUser.setString(4, user.getPassword());
            insertUser.setInt(5, user.getAge());
            insertUser.executeUpdate();
            ConnectionDataBase.closeConnection();
            writeLog("User added: " + user.getUsername());
        } catch (SQLException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // function to update user and update it in the database using preparedStatement
    public void updateUser(User user) {
        try {
            PreparedStatement updateUser = ConnectionDataBase.openConnection().prepareStatement("UPDATE `user` SET `username`=?, `email`=?, `password`=?, `age`=? WHERE `jobID`=?");
            updateUser.setString(1, user.getUsername());
            updateUser.setString(2, user.getEmail());
            updateUser.setString(3, user.getPassword());
            updateUser.setInt(4, user.getAge());
            updateUser.setString(5, user.getJobID());
            updateUser.executeUpdate();
            ConnectionDataBase.closeConnection();
            writeLog("User updated: " + user.getUsername());
        } catch (SQLException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // function to delete user from the database using preparedStatement
    public void deleteUser(String jobID) {
        try {
            PreparedStatement deleteUser = ConnectionDataBase.openConnection().prepareStatement("DELETE FROM `user` WHERE `jobID`=?");
            deleteUser.setString(1, jobID);
            deleteUser.executeUpdate();
            ConnectionDataBase.closeConnection();
            writeLog("User deleted: " + jobID);
        } catch (SQLException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // function to check if username and password are valid or not
    public boolean isLogin(User user) {
        try {
            PreparedStatement checkLogin = ConnectionDataBase.openConnection().prepareStatement("SELECT * FROM user WHERE jobID=? AND password=?");
            checkLogin.setString(1, user.getJobID());
            checkLogin.setString(2, user.getPassword());
            ResultSet rs = checkLogin.executeQuery();

            if (rs.next()) {
                ConnectionDataBase.closeConnection();
                ConnectionDataBase.writeLog("Successful login for jobID: " + user.getJobID());
                return true;
            }

            ConnectionDataBase.closeConnection();
        } catch (SQLException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    // fetch user information from the database
    public User selectUserInformation(String jobID) {
        User user = null;
        try {
            PreparedStatement userInfo = ConnectionDataBase.openConnection().prepareStatement("SELECT * FROM user WHERE jobID=?");
            userInfo.setString(1, jobID);
            ResultSet rs = userInfo.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setJobID(rs.getString("jobID"));
                user.setUsername(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setAge(rs.getInt("age"));
            }

            ConnectionDataBase.closeConnection();
            ConnectionDataBase.writeLog("User information was fetched from the database");
        } catch (SQLException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }

    public boolean checkPassword(User user) {
        try {
            PreparedStatement check = ConnectionDataBase.openConnection().prepareStatement("SELECT * FROM user WHERE password= ?");
            check.setString(1, user.getPassword());
            ResultSet rs = check.executeQuery();
            while (rs.next()) {
                ConnectionDataBase.writeLog("User information was fetched from the database"); // Provide the specific log message here
                return true;
            }
            ConnectionDataBase.closeConnection();
        } catch (SQLException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

}
