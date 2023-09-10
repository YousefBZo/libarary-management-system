/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelClasses;

import java.util.Date;

/**
 *
 * @author Yousef
 */
public class IssueBook {

    private int issueBookID;
    private String bookISBIN;
    private String studentID;
    private Date issuedDate;
    private Date DueDate;
    private String status;

    public int getIssueBookID() {
        return issueBookID;
    }

    public void setIssueBookID(int issueBookID) {
        this.issueBookID = issueBookID;
    }

    public String getBookISBIN() {
        return bookISBIN;
    }

    public void setBookISBIN(String bookISBIN) {
        this.bookISBIN = bookISBIN;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public Date getIssuedDate() {
        return issuedDate;
    }

    public void setIssuedDate(Date issuedDate) {
        this.issuedDate = issuedDate;
    }

    public Date getDueDate() {
        return DueDate;
    }

    public void setDueDate(Date DueDate) {
        this.DueDate = DueDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "IssueBook{" + "issueBookID=" + issueBookID + ", bookISBIN=" + bookISBIN + ", studentID=" + studentID + ", issuedDate=" + issuedDate + ", DueDate=" + DueDate + ", status=" + status + '}';
    }

}
