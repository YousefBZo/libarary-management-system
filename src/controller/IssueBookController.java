/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import modelClasses.IssueBook;

/**
 *
 * @author Yousef
 */
public class IssueBookController {

    public void addIssueBook(IssueBook issueBook) {
        try {
            PreparedStatement insertIssueBook = ConnectionDataBase.openConnection().prepareStatement("INSERT INTO `issue_books`(`book_ISBIN`, `student_id`, `issued_date`, `due_date`, `status`) VALUES (?,?,?,?,?)");
            String bookISBIN = issueBook.getBookISBIN();
            insertIssueBook.setString(1, bookISBIN);
            insertIssueBook.setString(2, issueBook.getStudentID());
            Date issueDate = new Date(issueBook.getIssuedDate().getTime());
            Date dueDate = new Date(issueBook.getDueDate().getTime());
            insertIssueBook.setDate(3, issueDate);
            insertIssueBook.setDate(4, dueDate);
            insertIssueBook.setString(5, issueBook.getStatus());
            int excute = insertIssueBook.executeUpdate();
            if (excute > 0) {
                new BookController().updateQuantity(bookISBIN, "-");
            }
            ConnectionDataBase.closeConnection();
            ConnectionDataBase.writeLog("Added issue book: " + issueBook.getBookISBIN() + " for student: " + issueBook.getStudentID());
        } catch (SQLException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateIssueBook(IssueBook issueBook) {
        try {
            PreparedStatement updateIssueBook = ConnectionDataBase.openConnection().prepareStatement("UPDATE `issue_books` SET `book_ISBIN`=?, `student_id`=?, `issued_date`=?, `due_date`=?  WHERE `id`=?");
            updateIssueBook.setString(1, issueBook.getBookISBIN());
            updateIssueBook.setString(2, issueBook.getStudentID());
            Date issueDate = new Date(issueBook.getIssuedDate().getTime());
            Date dueDate = new Date(issueBook.getDueDate().getTime());
            updateIssueBook.setDate(3, issueDate);
            updateIssueBook.setDate(4, dueDate);
            updateIssueBook.setInt(5, issueBook.getIssueBookID());
            updateIssueBook.executeUpdate();
            ConnectionDataBase.closeConnection();
            ConnectionDataBase.writeLog("Updated issue book with ID: " + issueBook.getIssueBookID());
        } catch (SQLException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deleteIssueBook(int id) {
        try {
            PreparedStatement deleteIssueBook = ConnectionDataBase.openConnection().prepareStatement("DELETE FROM `issue_books` WHERE `id`=?");
            deleteIssueBook.setInt(1, id);
            deleteIssueBook.executeUpdate();
            ConnectionDataBase.closeConnection();
            ConnectionDataBase.writeLog("Deleted issue book with ID: " + id);
        } catch (SQLException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ObservableList<IssueBook> selectAllIssueBook() {
        ObservableList<IssueBook> issueBooks = FXCollections.observableArrayList();
        try {
            PreparedStatement allIssueBooks = ConnectionDataBase.openConnection().prepareStatement("SELECT * FROM issue_books");
            ResultSet rs = allIssueBooks.executeQuery();
            while (rs.next()) {
                IssueBook issueBook = new IssueBook();
                issueBook.setIssueBookID(rs.getInt("id"));
                issueBook.setBookISBIN(rs.getString("book_ISBIN"));
                issueBook.setStudentID(rs.getString("student_id"));
                issueBook.setIssuedDate(rs.getDate("issued_date"));
                issueBook.setDueDate(rs.getDate("due_date"));
                issueBooks.add(issueBook);
            }
            rs.close();
            allIssueBooks.close();
            ConnectionDataBase.closeConnection();

            ConnectionDataBase.writeLog("Retrieved all issue books from the database");
        } catch (SQLException ex) {
            Logger.getLogger(IssueBookController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return issueBooks;
    }

    public ObservableList<IssueBook> getSearchIssueBook(Date issueDate, Date dueDate) {
        ObservableList<IssueBook> issueBooks = FXCollections.observableArrayList();
        try {
            PreparedStatement searchIssueBook = ConnectionDataBase.openConnection().prepareStatement("SELECT * FROM issue_books WHERE issued_date BETWEEN ? AND ?");
            searchIssueBook.setDate(1, issueDate);
            searchIssueBook.setDate(2, dueDate);
            ResultSet rs = searchIssueBook.executeQuery();

            while (rs.next()) {
                IssueBook issueBook = new IssueBook();
                issueBook.setIssueBookID(rs.getInt("id"));
                issueBook.setBookISBIN(rs.getString("book_ISBIN"));
                issueBook.setStudentID(rs.getString("student_id"));
                issueBook.setIssuedDate(rs.getDate("issued_date"));
                issueBook.setDueDate(rs.getDate("due_date"));
                issueBooks.add(issueBook);
            }
            ConnectionDataBase.closeConnection();
            ConnectionDataBase.writeLog("Performed search for issue books with issue date between " + issueDate + " and " + dueDate);
        } catch (SQLException ex) {
            Logger.getLogger(BookController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return issueBooks;
    }

    public void updateReturnBook(String studentID, String bookISBIN) {
        try {
            PreparedStatement updateIssueBook = ConnectionDataBase.openConnection().prepareStatement("UPDATE `issue_books` SET `status`='returned' WHERE `book_ISBIN`=? AND `student_id`=?");
            updateIssueBook.setString(1, bookISBIN);
            updateIssueBook.setString(2, studentID);

            int excute=updateIssueBook.executeUpdate();
            updateIssueBook.close();
            if (excute > 0) {
                new BookController().updateQuantity(bookISBIN, "+");
            }
            ConnectionDataBase.closeConnection();
            ConnectionDataBase.writeLog("Updated issue book status to 'returned' for book: " + bookISBIN + " and student: " + studentID);
        } catch (SQLException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public IssueBook selectIssueBookInformation(String studentID, String bookISBIN) {
        IssueBook issueBook = null;
        try {
            PreparedStatement issueBookInfo = ConnectionDataBase.openConnection().prepareStatement("SELECT * FROM issue_books WHERE `book_ISBIN`=? AND `student_id`=? AND `status`='pending'");
            issueBookInfo.setString(1, bookISBIN);
            issueBookInfo.setString(2, studentID);
            ResultSet rs = issueBookInfo.executeQuery();

            if (rs.next()) {
                issueBook = new IssueBook();
                issueBook.setIssueBookID(rs.getInt("id"));
                issueBook.setBookISBIN(rs.getString("book_ISBIN"));
                issueBook.setStudentID(rs.getString("student_id"));
                issueBook.setIssuedDate(rs.getDate("issued_date"));
                issueBook.setDueDate(rs.getDate("due_date"));
            }

            rs.close();
            issueBookInfo.close();
            ConnectionDataBase.closeConnection();
            ConnectionDataBase.writeLog("Retrieved issue book information for book: " + bookISBIN + " and student: " + studentID);
        } catch (SQLException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return issueBook;
    }

    public ObservableList<IssueBook> selectAllLaggingStudents() {
        ObservableList<IssueBook> issueBooks = FXCollections.observableArrayList();
        try {
            PreparedStatement selectIssueBook = ConnectionDataBase.openConnection().prepareStatement("SELECT * FROM issue_books WHERE due_date < NOW() and status='pending'");
            ResultSet rs = selectIssueBook.executeQuery();

            while (rs.next()) {
                IssueBook issueBook = new IssueBook();
                issueBook.setIssueBookID(rs.getInt("id"));
                issueBook.setBookISBIN(rs.getString("book_ISBIN"));
                issueBook.setStudentID(rs.getString("student_id"));
                issueBook.setIssuedDate(rs.getDate("issued_date"));
                issueBook.setDueDate(rs.getDate("due_date"));

                issueBooks.add(issueBook);
            }

            rs.close();
            selectIssueBook.close();
            ConnectionDataBase.closeConnection();
            ConnectionDataBase.writeLog("Retrieved all lagging students from the database");
        } catch (SQLException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return issueBooks;
    }

    public int countIssueBooks() {
        int count = 0;
        try {
            PreparedStatement searchBooks = ConnectionDataBase.openConnection().prepareStatement("SELECT * FROM issue_books");
            ResultSet rs = searchBooks.executeQuery();
            while (rs.next()) {
                count++;
            }
            ConnectionDataBase.writeLog("A select for count issue books from issue_books table was called");
        } catch (SQLException ex) {
            Logger.getLogger(BookController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count;
    }

    public ObservableList<IssueBook> getSearchLaggingStudents(String id) {
        ObservableList<IssueBook> issueBooks = FXCollections.observableArrayList();
        try {
            PreparedStatement searchLagging = ConnectionDataBase.openConnection().prepareStatement("SELECT * FROM issue_books WHERE status='pending' and student_id LIKE ?");
            searchLagging.setString(1, "%" + id + "%");
            ResultSet rs = searchLagging.executeQuery();

            while (rs.next()) {
                IssueBook issueBook = new IssueBook();
                issueBook.setIssueBookID(rs.getInt("id"));
                issueBook.setBookISBIN(rs.getString("book_ISBIN"));
                issueBook.setStudentID(rs.getString("student_id"));
                issueBook.setIssuedDate(rs.getDate("issued_date"));
                issueBook.setDueDate(rs.getDate("due_date"));
                issueBooks.add(issueBook);
            }

            ConnectionDataBase.closeConnection();
            ConnectionDataBase.writeLog("Performed search for lagging students with issue date");
        } catch (SQLException ex) {
            Logger.getLogger(BookController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return issueBooks;
    }

    public int countLaggingStudents() {
        int count = 0;
        try {
            PreparedStatement searchBooks = ConnectionDataBase.openConnection()
                    .prepareStatement("SELECT * FROM issue_books WHERE status='pending' and due_date < NOW()");
            ResultSet rs = searchBooks.executeQuery();
            while (rs.next()) {
                count++;
            }
            ConnectionDataBase.writeLog("A select for count lagging students from issue_books table was called");
        } catch (SQLException ex) {
            Logger.getLogger(BookController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count;
    }
}
