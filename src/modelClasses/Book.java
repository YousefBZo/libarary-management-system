/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelClasses;

/**
 *
 * @author Yousef
 */
public class Book {

    private String bookISBIN;
    private String title;
    private String author;
    private int quantity;

  

    public String getBookISBIN() {
        return bookISBIN;
    }

    public void setBookISBIN(String book_ISBIN) {
        this.bookISBIN = book_ISBIN;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Book{" + "book_ISBIN=" + bookISBIN + ", title=" + title + ", author=" + author + ", quantity=" + quantity + '}';
    }

}
