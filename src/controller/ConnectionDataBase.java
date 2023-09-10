package controller;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author Yousef
 */
public class ConnectionDataBase {

    private static Connection con;
    private static String url = "jdbc:mysql://localhost/library_management?useUnicode=true&characterEncoding=UTF-8";

    private ConnectionDataBase() {

    }

    public static Connection openConnection() {
        if (con == null) {
            try {
                con = (Connection) DriverManager.getConnection(url, "root", "");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return con;
    }

    public static void closeConnection() {
        if (con != null) {
            con = null;
        }
    }

    public static void writeLog(String message) {
        LocalDateTime currentTime = LocalDateTime.now();
        String formattedTime = currentTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        try (PrintWriter writer = new PrintWriter(new FileWriter("Database Transactions.log", true))) {
            writer.println(formattedTime + ": " + message);
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the log file: " + e.getMessage());
        }
    }

}
