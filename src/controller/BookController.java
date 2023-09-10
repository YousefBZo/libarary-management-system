/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import modelClasses.Book;

/**
 *
 * @author Yousef
 */
public class BookController {
    //add book to database

    public void addBook(Book book) {
        try {
            PreparedStatement insertBook = ConnectionDataBase.openConnection().prepareStatement("INSERT INTO `book`( `ISBIN`, `title`, `author`, `quantity`) VALUES (?,?,?,?)");
            insertBook.setString(1, book.getBookISBIN());
            insertBook.setString(2, book.getTitle());
            insertBook.setString(3, book.getAuthor());
            insertBook.setInt(4, book.getQuantity());
            insertBook.executeUpdate();
            ConnectionDataBase.closeConnection();
        } catch (SQLException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ConnectionDataBase.writeLog("An insert into the book table was made: " + book.getTitle());
    }

    // function to update book in the database using prepared statement
    public void updateBook(Book book) {
        try {
            PreparedStatement updateBook = ConnectionDataBase.openConnection().prepareStatement("UPDATE `book` SET `title`=?, `author`=?, `quantity`=?  WHERE `ISBIN`=?");
            updateBook.setString(1, book.getTitle());
            updateBook.setString(2, book.getAuthor());
            updateBook.setInt(3, book.getQuantity());
            updateBook.setString(4, book.getBookISBIN());
            updateBook.executeUpdate();
            ConnectionDataBase.closeConnection();
        } catch (SQLException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ConnectionDataBase.writeLog("An update on the book table was made for book with ISBIN: " + book.getBookISBIN());
    }

    public void updateQuantity(String ISBIN, String sign) {
        try {
            PreparedStatement updateBook = ConnectionDataBase.openConnection().prepareStatement("UPDATE `book` SET `quantity` = `quantity` " + sign + " 1  WHERE `ISBIN` = ?");
            updateBook.setString(1, ISBIN);
            updateBook.executeUpdate();
            ConnectionDataBase.closeConnection();
            ConnectionDataBase.writeLog("An update on the book table for quantity was made for book with ISBIN: " + ISBIN);
        } catch (SQLException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // function to delete book from the database using prepared statement
    public void deleteBook(String ISBIN) {
        try {
            PreparedStatement deleteBook = ConnectionDataBase.openConnection().prepareStatement("DELETE FROM `book` WHERE `ISBIN`=?");
            deleteBook.setString(1, ISBIN);
            deleteBook.executeUpdate();
            ConnectionDataBase.closeConnection();
        } catch (SQLException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ConnectionDataBase.writeLog("A delete on the book table was made for book with ISBIN: " + ISBIN);
    }

    // fetch all books from the database
    public ObservableList<Book> selectAllBooks() {
        ObservableList<Book> books = FXCollections.observableArrayList();
        try {
            PreparedStatement allBooks = ConnectionDataBase.openConnection().prepareStatement("SELECT * FROM book");
            ResultSet rs = allBooks.executeQuery();
            while (rs.next()) {
                Book book = new Book();
                book.setBookISBIN(rs.getString("ISBIN"));
                book.setTitle(rs.getString("title"));
                book.setAuthor(rs.getString("author"));
                book.setQuantity(rs.getInt("quantity"));
                books.add(book);
            }
            ConnectionDataBase.closeConnection();
        } catch (SQLException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ConnectionDataBase.writeLog("A select all records from the book table was called");
        return books;
    }

    // fetch search books from the database
    public ObservableList<Book> getSearchBook(String title) {
        ObservableList<Book> books = FXCollections.observableArrayList();
        try {
            PreparedStatement searchBooks = ConnectionDataBase.openConnection().prepareStatement("SELECT * FROM book WHERE title LIKE ?");
            searchBooks.setString(1, "%" + title + "%");
            ResultSet rs = searchBooks.executeQuery();

            while (rs.next()) {
                Book book = new Book();
                book.setBookISBIN(rs.getString("ISBIN"));
                book.setTitle(rs.getString("title"));
                book.setAuthor(rs.getString("author"));
                book.setQuantity(rs.getInt("quantity"));
                books.add(book);
            }

            ConnectionDataBase.closeConnection();
            ConnectionDataBase.writeLog("A select for search book from book table was called");
        } catch (SQLException ex) {
            Logger.getLogger(BookController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return books;
    }

    public int countBooks() {
        int count = 0;
        try {
            PreparedStatement searchBooks = ConnectionDataBase.openConnection().prepareStatement("SELECT * FROM book");
            ResultSet rs = searchBooks.executeQuery();
            while (rs.next()) {
                count++;
            }
            ConnectionDataBase.writeLog("A select for count book from book table was called");
        } catch (SQLException ex) {
            Logger.getLogger(BookController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count;
    }

    public int countBookByISBIN(String ISBIN) {
        int count = 0;
        try {
            PreparedStatement searchBooks = ConnectionDataBase.openConnection().prepareStatement("SELECT * FROM book where ISBIN=?");
            searchBooks.setString(1, ISBIN);
            ResultSet rs = searchBooks.executeQuery();
            while (rs.next()) {
                count++;
            }
            ConnectionDataBase.writeLog("A select for count book from book table when equal ISBIN  was called");
        } catch (SQLException ex) {
            Logger.getLogger(BookController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count;
    }

}
