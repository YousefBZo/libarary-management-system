/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import modelClasses.Student;

/**
 *
 * @author Yousef
 */
public class StudentController {

    // add student to database
    public void addStudent(Student student) {
        try {
            PreparedStatement insertStudent = ConnectionDataBase.openConnection().prepareStatement("INSERT INTO `student`( `id`, `name`, `major`, `address`,`mobile`) VALUES (?,?,?,?,?)");
            insertStudent.setString(1, student.getStudentID());
            insertStudent.setString(2, student.getName());
            insertStudent.setString(3, student.getMajor());
            insertStudent.setString(4, student.getAddress());
            insertStudent.setString(5, student.getMobile());
            insertStudent.executeUpdate();
            ConnectionDataBase.closeConnection();
            ConnectionDataBase.writeLog("An insert into student table was made:" + student.getName());
        } catch (SQLException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // function to update student and update it to database use preparedStatement
    public void updateStudent(Student student) {
        try {
            PreparedStatement updateStudent = ConnectionDataBase.openConnection().prepareStatement("UPDATE `book` SET `name`=?, `major`=?, `address`=?, `mobile`=?  WHERE `id`=?");
            updateStudent.setString(1, student.getName());
            updateStudent.setString(2, student.getMajor());
            updateStudent.setString(3, student.getAddress());
            updateStudent.setString(4, student.getMobile());
            updateStudent.setString(5, student.getStudentID());
            updateStudent.executeUpdate();
            ConnectionDataBase.closeConnection();
            ConnectionDataBase.writeLog("An update on student table was made:" + student.getStudentID());
        } catch (SQLException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // function to delete student and delete it from the database using preparedStatement
    public void deleteStudent(String id) {
        try {
            PreparedStatement deleteStudent = ConnectionDataBase.openConnection().prepareStatement("DELETE FROM `student` WHERE `id`=?");
            deleteStudent.setString(1, id);
            deleteStudent.executeUpdate();
            ConnectionDataBase.closeConnection();
            ConnectionDataBase.writeLog("A delete on student table was made:" + id);
        } catch (SQLException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // fetch all students from the database
    public ObservableList<Student> selectAllStudents() {
        ObservableList<Student> students = FXCollections.observableArrayList();
        try {
            PreparedStatement allStudents = ConnectionDataBase.openConnection().prepareStatement("SELECT * FROM student");
            ResultSet rs = allStudents.executeQuery();
            while (rs.next()) {
                Student student = new Student();
                student.setStudentID(rs.getString("id"));
                student.setName(rs.getString("name"));
                student.setMajor(rs.getString("major"));
                student.setAddress(rs.getString("address"));
                student.setMobile(rs.getString("mobile"));
                students.add(student);
            }
            ConnectionDataBase.closeConnection();
            ConnectionDataBase.writeLog("A select all records from student table was called");
        } catch (SQLException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return students;
    }

    // fetch search students from the database
    public ObservableList<Student> getSearchStudent(String name) {
        ObservableList<Student> students = FXCollections.observableArrayList();
        try {
            PreparedStatement searchStudent = ConnectionDataBase.openConnection().prepareStatement("SELECT * FROM student WHERE name LIKE ?");
            searchStudent.setString(1, "%" + name + "%");
            ResultSet rs = searchStudent.executeQuery();
            while (rs.next()) {
                Student student = new Student();
                student.setStudentID(rs.getString("id"));
                student.setName(rs.getString("name"));
                student.setMajor(rs.getString("major"));
                student.setAddress(rs.getString("address"));
                student.setMobile(rs.getString("mobile"));
                students.add(student);
            }
            ConnectionDataBase.closeConnection();
            ConnectionDataBase.writeLog("A select for search student from student table was called");
        } catch (SQLException ex) {
            Logger.getLogger(BookController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return students;
    }

    public int countStudents() {
        int count = 0;
        try {
            PreparedStatement searchBooks = ConnectionDataBase.openConnection().prepareStatement("SELECT * FROM student");
            ResultSet rs = searchBooks.executeQuery();
            while (rs.next()) {
                count++;
            }
            ConnectionDataBase.writeLog("A select for count student from student table was called");
        } catch (SQLException ex) {
            Logger.getLogger(BookController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count;
    }

    public int countStudentByID(String ID) {
        int count = 0;
        try {
            PreparedStatement searchstudent = ConnectionDataBase.openConnection().prepareStatement("SELECT * FROM student where id=?");
            searchstudent.setString(1, ID);
            ResultSet rs = searchstudent.executeQuery();
            while (rs.next()) {
                count++;
            }
            ConnectionDataBase.writeLog("A select for count book from book table  when equal ID was called");
        } catch (SQLException ex) {
            Logger.getLogger(BookController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count;
    }
}
