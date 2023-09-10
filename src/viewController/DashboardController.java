/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package viewController;

import controller.BookController;
import controller.IssueBookController;
import controller.StudentController;
import controller.UserController;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import modelClasses.Book;
import modelClasses.IssueBook;
import modelClasses.Student;
import java.util.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import modelClasses.User;

/**
 * FXML Controller class
 *
 * @author Yousef
 */
public class DashboardController implements Initializable {

    @FXML
    private Button btnHome;
    @FXML
    private Button btnBooks;
    @FXML
    private Button btnStudents;
    @FXML
    private Button btnIssueBook;
    @FXML
    private Button btnReturnBook;
    @FXML
    private Button btnLaggingStudents;
    @FXML
    private Button btnSetting;
    @FXML
    private Button btnAbout;
    @FXML
    private Button btnLogout;
    @FXML
    private Pane paneStudent;
    @FXML
    private TableView<Student> studentTable;
    @FXML
    private TableColumn<Student, String> studentID;
    @FXML
    private TableColumn<Student, String> studentName;
    @FXML
    private TableColumn<Student, String> studentMajor;
    @FXML
    private TableColumn<Student, String> studentAddress;
    @FXML
    private TableColumn<Student, String> studentMobile;
    @FXML
    private TableColumn<Student, String> studentIssuedBooks;
    @FXML
    private TextField studentSearchName;
    @FXML
    private Button studentAdd;
    @FXML
    private Button studentUpdate;
    @FXML
    private Button studentDelete;
    @FXML
    private TextField studentTxtID;
    @FXML
    private TextField studentTxtName;
    @FXML
    private TextField studentTxtMajor;
    @FXML
    private TextField studentTxtMobile;
    @FXML
    private TextField studentTxtAddress;
    @FXML
    private Pane paneBooks;
    @FXML
    private TableView<Book> bookTable;
    @FXML
    private TableColumn<Book, String> bookISBIN;
    @FXML
    private TableColumn<Book, String> bookTitle;
    @FXML
    private TableColumn<Book, String> bookAuthor;
    @FXML
    private TableColumn<Book, Integer> bookQuantity;
    @FXML
    private Button bookBtnSearch;
    @FXML
    private TextField bookSearchTitle;
    @FXML
    private Button bookAdd;
    @FXML
    private Button bookUpdate;
    @FXML
    private Button bookDelete;
    @FXML
    private TextField bookTxtISBIN;
    @FXML
    private TextField bookTxtTitle;
    @FXML
    private TextField bookTxtAuthor;
    @FXML
    private TextField bookTxtQuantity;
    @FXML
    private Pane paneDashboard;
    @FXML
    private Text dashboardNoBooks;
    @FXML
    private Text dashboardNoStudents;
    @FXML
    private Text dashboardNoIssuedBooks;
    @FXML
    private Text dashboardNoLagging;
    @FXML
    private Pane paneIssueBook;
    @FXML
    private TableView<IssueBook> issueTable;
    @FXML
    private TableColumn<IssueBook, Integer> issueIssueID;
    @FXML
    private TableColumn<IssueBook, String> issueBookISBIN;
    @FXML
    private TableColumn<IssueBook, String> issueStudentID;
    @FXML
    private TableColumn<IssueBook, Date> issueIssuedDate;
    @FXML
    private TableColumn<IssueBook, Date> issueDueDate;
    @FXML
    private Button issueBtnSearch;
    @FXML
    private DatePicker issueSearchIssueDate;
    @FXML
    private DatePicker issueSearchDueDate;
    @FXML
    private Button issueBtnAdd;
    @FXML
    private Button issueBtnUpdate;
    @FXML
    private Button issueBtnDelete;
    @FXML
    private TextField issueTxtBookISBIN;
    @FXML
    private TextField issueTxtStudentID;
    @FXML
    private DatePicker issueTxtIssueDate;
    @FXML
    private DatePicker issueTxtDueDate;
    @FXML
    private Pane paneReturnBook;
    @FXML
    private TextField returnStudentID;
    @FXML
    private TextField returnBookISBIN;
    @FXML
    private Button returnBtnFindDetail;
    @FXML
    private Button returnBtnReturnBook;
    @FXML
    private Pane returnPaneFindDetail;
    @FXML
    private Pane paneLaggingStudents;
    @FXML
    private TableView<IssueBook> lagingTable;
    @FXML
    private TableColumn<IssueBook, Integer> laggingIssueID;
    @FXML
    private TableColumn<IssueBook, String> laggingBookISBIN;
    @FXML
    private TableColumn<IssueBook, String> laggingStudentID;
    @FXML
    private TableColumn<IssueBook, Date> laggingIssueDate;
    @FXML
    private TableColumn<IssueBook, Date> laggingDueDate;
    @FXML
    private Button laggingBtnSearch;
    @FXML
    private TextField laggingTxtSearch;
    @FXML
    private Pane paneAbout;
    @FXML
    private Pane paneSetting;
    @FXML
    private TextField settingTxtEmail;
    @FXML
    private TextField settingTxtJobID;
    @FXML
    private Button SettingBtnUpdate;
    @FXML
    private ComboBox<Integer> settingAge;
    @FXML
    private PasswordField settingtxtOldPassword;
    @FXML
    private Button settingBtnDelete;
    @FXML
    private TextField SettingTxtName;
    @FXML
    private PasswordField settingTxtNewPassword;

    BookController bookController;
    StudentController studentController;
    IssueBookController issueBookController;
    UserController userController = new UserController();
    @FXML
    private Button studentBtnSearch;
    @FXML
    private TextField issueTxtID;
    @FXML
    private Text returnTxtIssueID;
    @FXML
    private Text returnTxtStudentID;
    @FXML
    private Text returnTxtISBIN;
    @FXML
    private Text returnIssueDate;
    @FXML
    private Text returnDueDate;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        disappearPanes();
        //make objects 
        bookController = new BookController();
        studentController = new StudentController();
        issueBookController = new IssueBookController();
        //dashboard
        dashboardNoBooks.setText(bookController.countBooks() + "");
        dashboardNoStudents.setText(studentController.countStudents() + "");
        dashboardNoIssuedBooks.setText(issueBookController.countIssueBooks() + "");
        dashboardNoLagging.setText(issueBookController.countLaggingStudents() + "");
        paneDashboard.setVisible(true);
        //books
        bookISBIN.setCellValueFactory(new PropertyValueFactory<>("bookISBIN"));
        bookTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        bookAuthor.setCellValueFactory(new PropertyValueFactory<>("author"));
        bookQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        //students
        studentID.setCellValueFactory(new PropertyValueFactory<>("studentID"));
        studentName.setCellValueFactory(new PropertyValueFactory<>("name"));
        studentMajor.setCellValueFactory(new PropertyValueFactory<>("major"));
        studentAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        studentMobile.setCellValueFactory(new PropertyValueFactory<>("mobile"));
        //issue book
        issueIssueID.setCellValueFactory(new PropertyValueFactory<>("issueBookID"));
        issueBookISBIN.setCellValueFactory(new PropertyValueFactory<>("bookISBIN"));
        issueStudentID.setCellValueFactory(new PropertyValueFactory<>("studentID"));
        issueIssuedDate.setCellValueFactory(new PropertyValueFactory<>("issuedDate"));
        issueDueDate.setCellValueFactory(new PropertyValueFactory<>("DueDate"));
        //laggingStudents
        laggingIssueID.setCellValueFactory(new PropertyValueFactory<>("issueBookID"));
        laggingBookISBIN.setCellValueFactory(new PropertyValueFactory<>("bookISBIN"));
        laggingStudentID.setCellValueFactory(new PropertyValueFactory<>("studentID"));
        laggingIssueDate.setCellValueFactory(new PropertyValueFactory<>("issuedDate"));
        laggingDueDate.setCellValueFactory(new PropertyValueFactory<>("DueDate"));

        //comboBox age
        ObservableList<Integer> ageList = FXCollections.observableArrayList();
        for (int i = 10; i <= 60; i++) {
            ageList.add(i);
        }
        settingAge.setItems(ageList);
    }
//show dashboard pane when click in home 

    @FXML
    private void showDashboard(ActionEvent e) {
        disappearPanes();
        paneDashboard.setVisible(true);
        dashboardNoBooks.setText(bookController.countBooks() + "");
        dashboardNoStudents.setText(studentController.countStudents() + "");
        dashboardNoIssuedBooks.setText(issueBookController.countIssueBooks() + "");
        dashboardNoLagging.setText(issueBookController.countLaggingStudents() + "");
    }
//show Books pane when click in home 

    @FXML
    private void showBooksPane(ActionEvent e) {
        disappearPanes();
        bookTable.setItems(bookController.selectAllBooks());
        paneBooks.setVisible(true);
    }
//when click in search show specific books

    @FXML
    private void showBooksSearch(ActionEvent e) {
        String title = bookSearchTitle.getText();
        if (title != null) {
            bookTable.setItems(bookController.getSearchBook(title));
        }
    }
// when click to table put specific record in fields

    @FXML
    private void showRecordBookInFields(MouseEvent e) {
        Book book = bookTable.getSelectionModel().getSelectedItem();

        if (book != null) {
            bookTxtISBIN.setText(book.getBookISBIN());
            bookTxtTitle.setText(book.getTitle());
            bookTxtAuthor.setText(book.getAuthor());
            bookTxtQuantity.setText(Integer.toString(book.getQuantity()));
        }
    }
// add book into database

    @FXML
    private void addBook(ActionEvent event) {
        String isbin = bookTxtISBIN.getText();
        String title = bookTxtTitle.getText();
        String author = bookTxtAuthor.getText();
        String quantityText = bookTxtQuantity.getText();

        if (isbin.isEmpty() || title.isEmpty() || author.isEmpty() || !isNumeric(quantityText)) {
            showError("Invalid values");
        } else {
            int quantity = Integer.parseInt(quantityText);
            Book book = new Book();
            book.setBookISBIN(isbin);
            book.setTitle(title);
            book.setAuthor(author);
            book.setQuantity(quantity);
            bookController.addBook(book);
            showSuccess("Book successfully added");
            makeFieldsEmptyBook();
            bookTable.setItems(bookController.selectAllBooks());
        }
    }
//update book into database

    @FXML
    private void updateBook(ActionEvent event) {
        String isbin = bookTxtISBIN.getText();
        String title = bookTxtTitle.getText();
        String author = bookTxtAuthor.getText();
        String quantityText = bookTxtQuantity.getText();

        if (isbin.isEmpty() || title.isEmpty() || author.isEmpty() || !isNumeric(quantityText)) {
            showError("Invalid values");
        } else {
            int quantity = Integer.parseInt(quantityText);
            Book book = new Book();
            book.setBookISBIN(isbin);
            book.setTitle(title);
            book.setAuthor(author);
            book.setQuantity(quantity);
            bookController.updateBook(book);
            showSuccess("Book successfully updated");
            makeFieldsEmptyBook();
            bookTable.setItems(bookController.selectAllBooks());

        }
    }
//delete book from database

    @FXML
    private void deleteBook(ActionEvent event) {
        String isbin = bookTxtISBIN.getText();

        if (isbin.isEmpty()) {
            showError("Invalid values");
        } else {
            bookController.deleteBook(isbin);
            showSuccess("Book successfully deleted");
            makeFieldsEmptyBook();
            bookTable.setItems(bookController.selectAllBooks());

        }
    }
//show student pane when click in studnet

    @FXML
    private void showStudentPane(ActionEvent e) {
        disappearPanes();
        studentTable.setItems(studentController.selectAllStudents());
        paneStudent.setVisible(true);
    }
//when click in search show specific students

    @FXML
    private void showStudentsSearch(ActionEvent e) {
        String name = studentSearchName.getText();
        if (name != null) {
            studentTable.setItems(studentController.getSearchStudent(name));
        }
    }
//when click in table show specific record into fields

    @FXML
    private void showRecordStudentInFields(MouseEvent e) {
        Student student = studentTable.getSelectionModel().getSelectedItem();

        if (student != null) {
            studentTxtID.setText(student.getStudentID());
            studentTxtName.setText(student.getName());
            studentTxtMajor.setText(student.getMajor());
            studentTxtAddress.setText(student.getAddress());
            studentTxtMobile.setText(student.getMobile());
        }
    }
// add student to database

    @FXML
    private void addStudent(ActionEvent event) {
        String id = studentTxtID.getText();
        String name = studentTxtName.getText();
        String major = studentTxtMajor.getText();
        String mobile = studentTxtMobile.getText();
        String address = studentTxtAddress.getText();

        if (id.isEmpty() || name.isEmpty() || major.isEmpty() || mobile.isEmpty() || address.isEmpty()) {
            showError("Invalid values");
        } else {
            Student student = new Student();
            student.setStudentID(id);
            student.setName(name);
            student.setMajor(major);
            student.setMobile(mobile);
            student.setAddress(address);
            studentController.addStudent(student);
            showSuccess("Student successfully added");
            makeFieldsEmptyStudent();
            studentTable.setItems(studentController.selectAllStudents());
        }
    }
//update student to database

    @FXML
    private void updateStudent(ActionEvent event) {
        String id = studentTxtID.getText();
        String name = studentTxtName.getText();
        String major = studentTxtMajor.getText();
        String mobile = studentTxtMobile.getText();
        String address = studentTxtAddress.getText();

        if (id.isEmpty() || name.isEmpty() || major.isEmpty() || mobile.isEmpty() || address.isEmpty()) {
            showError("Invalid values");
        } else {
            Student student = new Student();
            student.setStudentID(id);
            student.setName(name);
            student.setMajor(major);
            student.setMobile(mobile);
            student.setAddress(address);
            studentController.updateStudent(student);
            showSuccess("Student successfully updated");
            makeFieldsEmptyStudent();
            studentTable.setItems(studentController.selectAllStudents());

        }
    }

    @FXML
    private void deleteStudent(ActionEvent event) {
        String id = studentTxtID.getText();

        if (id.isEmpty()) {
            showError("Invalid values");
        } else {
            studentController.deleteStudent(id);
            showSuccess("Student successfully deleted");
            makeFieldsEmptyStudent();
            studentTable.setItems(studentController.selectAllStudents());

        }
    }

    @FXML
    private void showIssueBookPane(ActionEvent e) {
        disappearPanes();
        issueTable.setItems(issueBookController.selectAllIssueBook());
        paneIssueBook.setVisible(true);
    }

    @FXML
    private void showIssueBooksSearch(ActionEvent e) {
        LocalDate issueDateLocal = issueSearchIssueDate.getValue();
        LocalDate dueDateLocal = issueSearchDueDate.getValue();

        if (issueDateLocal != null && dueDateLocal != null) {
            java.sql.Date issueDate = java.sql.Date.valueOf(issueDateLocal);
            java.sql.Date dueDate = java.sql.Date.valueOf(dueDateLocal);
            issueTable.setItems(issueBookController.getSearchIssueBook(issueDate, dueDate));
        }

    }

    @FXML
    private void showRecordIssueBookInFields(MouseEvent e) {
        IssueBook issueBook = issueTable.getSelectionModel().getSelectedItem();

        if (issueBook != null) {
            issueTxtID.setText(issueBook.getIssueBookID() + "");
            issueTxtBookISBIN.setText(issueBook.getBookISBIN());
            issueTxtStudentID.setText(issueBook.getStudentID());

        }
    }

    private LocalDate toLocalDate(Date date) {
        // Convert the java.util.Date object to Instant, using the system default time zone,
        // and then convert it to the corresponding local date in that time zone.
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    // Declare numberID as a class member variable
//    private int numberID = 0;
//
//    // Define the file path where the numberID will be stored
//    private static final String NUMBER_ID_FILE_PATH = "numberID.txt";
//
//    // Method to retrieve the last saved numberID from a file
//    private int getLastNumberID() {
//        try {
//            File file = new File(NUMBER_ID_FILE_PATH);
//            if (file.exists()) {
//                BufferedReader reader = new BufferedReader(new FileReader(file));
//                String lastNumberIDStr = reader.readLine();
//                reader.close();
//                return Integer.parseInt(lastNumberIDStr);
//            }
//        } catch (IOException | NumberFormatException e) {
//            e.printStackTrace();
//        }
//        return 0;
//    }
//
//    // Method to save the current numberID to a file
//    private void saveNumberID() {
//        try {
//            File file = new File(NUMBER_ID_FILE_PATH);
//            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
//            writer.write(String.valueOf(numberID));
//            writer.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @FXML
//    private void addIssueBook() {
//        String bookISBIN = issueTxtBookISBIN.getText();
//        String studentID = issueTxtStudentID.getText();
//        LocalDate issueDateLocal = issueTxtIssueDate.getValue();
//        LocalDate dueDateLocal = issueTxtDueDate.getValue();
//
//        // Check if bookISBIN and studentID are valid
//        if (bookISBIN.isEmpty() || studentID.isEmpty() || issueDateLocal == null || dueDateLocal == null) {
//            showError("Invalid values");
//        } else {
//            // Retrieve the last value of numberID from the file
//            int lastNumberID = getLastNumberID();
//            // Update numberID if the last value is greater than or equal to the current numberID
//            if (lastNumberID >= numberID) {
//                numberID = lastNumberID + 1;
//            }
//
//            Date issueDate = Date.from(issueDateLocal.atStartOfDay(ZoneId.systemDefault()).toInstant());
//            Date dueDate = Date.from(dueDateLocal.atStartOfDay(ZoneId.systemDefault()).toInstant());
//
//            // Create an instance of IssueBook and set its properties
//            IssueBook issueBook = new IssueBook();
//            issueBook.setIssueBookID(numberID++);
//            issueBook.setBookISBIN(bookISBIN);
//            issueBook.setStudentID(studentID);
//            issueBook.setIssuedDate(issueDate);
//            issueBook.setDueDate(dueDate);
//            issueBook.setStatus("pending");
//
//            // Add the issueBook to the database or perform any necessary operations
//            // Save the current numberID to the file
//            saveNumberID();
//
//            // Reset the input fields
//            issueTxtBookISBIN.clear();
//            issueTxtStudentID.clear();
//            issueTxtIssueDate.setValue(null);
//            issueTxtDueDate.setValue(null);
//
//            // Display a success message to the user
//            showSuccess("Issue book successfully added");
//        }
//    }
//
    @FXML
    private void updateIssueBook(ActionEvent event) {
        String issueID = issueTxtID.getText();
        String bookISBIN = issueTxtBookISBIN.getText();
        String studentID = issueTxtStudentID.getText();
        LocalDate issueDateLocal = issueTxtIssueDate.getValue();
        LocalDate dueDateLocal = issueTxtDueDate.getValue();
        if (bookController.countBookByISBIN(bookISBIN) > 0 && studentController.countStudentByID(studentID) > 0) {
            if (bookISBIN.isEmpty() || studentID.isEmpty() || issueDateLocal == null || dueDateLocal == null) {
                showError("Invalid values");
            } else {
                Date issueDate = Date.from(issueDateLocal.atStartOfDay(ZoneId.systemDefault()).toInstant());
                Date dueDate = Date.from(dueDateLocal.atStartOfDay(ZoneId.systemDefault()).toInstant());
                IssueBook issueBook = new IssueBook();
                issueBook.setIssueBookID(Integer.parseInt(issueID));
                issueBook.setBookISBIN(bookISBIN);
                issueBook.setStudentID(studentID);
                issueBook.setIssuedDate(issueDate);
                issueBook.setDueDate(dueDate);
                issueBook.setStatus("pending");
                issueBookController.updateIssueBook(issueBook);
                showSuccess("Issue book successfully updated");
                makeFieldsEmptyIssueBook();
                issueTable.setItems(issueBookController.selectAllIssueBook());
            }
        } else {
            showError("Invalid values");
        }

    }

    @FXML
    private void addIssueBook(ActionEvent event) {
        String bookISBIN = issueTxtBookISBIN.getText();
        String studentID = issueTxtStudentID.getText();
        LocalDate issueDateLocal = issueTxtIssueDate.getValue();
        LocalDate dueDateLocal = issueTxtDueDate.getValue();
        int numberID = 1;
        if (bookController.countBookByISBIN(bookISBIN) > 0 && studentController.countStudentByID(studentID) > 0) {
            if (bookISBIN.isEmpty() || studentID.isEmpty() || issueDateLocal == null || dueDateLocal == null) {
                showError("Invalid values");
            } else {
                Date issueDate = Date.from(issueDateLocal.atStartOfDay(ZoneId.systemDefault()).toInstant());
                Date dueDate = Date.from(dueDateLocal.atStartOfDay(ZoneId.systemDefault()).toInstant());
                IssueBook issueBook = new IssueBook();
                issueBook.setIssueBookID(numberID++);
                issueBook.setBookISBIN(bookISBIN);
                issueBook.setStudentID(studentID);
                issueBook.setIssuedDate(issueDate);
                issueBook.setDueDate(dueDate);
                issueBook.setStatus("pending");
                issueBookController.addIssueBook(issueBook);
                showSuccess("Issue book successfully added");
                makeFieldsEmptyIssueBook();
                issueTable.setItems(issueBookController.selectAllIssueBook());
            }
        } else {
            showError("Invalid values");
        }
    }

    @FXML
    private void deleteIssue(ActionEvent event
    ) {
        String issueID = issueTxtID.getText();

        if (issueID.isEmpty()) {
            showError("Invalid values");
        } else {
            int issueIDInt = Integer.parseInt(issueID);
            issueBookController.deleteIssueBook(issueIDInt);
            showSuccess("Issue successfully deleted");
            makeFieldsEmptyIssueBook();
            issueTable.setItems(issueBookController.selectAllIssueBook());

        }
    }

    @FXML
    private void showReturnBookPane(ActionEvent e) {
        disappearPanes();
        paneReturnBook.setVisible(true);
    }

    @FXML
    private void findIssueBook(ActionEvent event) {
        String studentID = returnStudentID.getText();
        String bookISBIN = returnBookISBIN.getText();

        if (bookISBIN != null && studentID != null) {
            if (bookController.countBookByISBIN(bookISBIN) > 0 && studentController.countStudentByID(studentID) > 0) {
                IssueBook issueBook = issueBookController.selectIssueBookInformation(studentID, bookISBIN);

                if (issueBook != null) {
                    returnTxtIssueID.setText(String.valueOf(issueBook.getIssueBookID()));
                    returnTxtStudentID.setText(issueBook.getStudentID());
                    returnTxtISBIN.setText(issueBook.getBookISBIN());
                    returnIssueDate.setText(issueBook.getIssuedDate().toString());
                    returnDueDate.setText(issueBook.getDueDate().toString());
                    returnPaneFindDetail.setVisible(true);
                } else {
                    showError("No issue book found for the given student and book.");
                }
            } else {
                showError("Invalid values for student ID or book ISBIN.");
            }
        } else {
            showError("Please enter student ID and book ISBIN.");
        }
    }

    @FXML
    private void returnBook(ActionEvent event) {
        String studentID = returnStudentID.getText();
        String bookISBIN = returnBookISBIN.getText();
        if (bookController.countBookByISBIN(bookISBIN) > 0 && studentController.countStudentByID(studentID) > 0) {
            issueBookController.updateReturnBook(studentID, bookISBIN);
            showSuccess("Successfully returned book");
            returnPaneFindDetail.setVisible(false);
            makeFieldsEmptyReturnBook();

        }
    }

    @FXML
    private void showLaggingStudentsPane(ActionEvent e) {
        disappearPanes();
        lagingTable.setItems(issueBookController.selectAllLaggingStudents());
        paneLaggingStudents.setVisible(true);
    }

    @FXML
    private void showLaggingSearch(ActionEvent e) {
        String id = laggingTxtSearch.getText();
        if (id != null) {
            lagingTable.setItems(issueBookController.getSearchLaggingStudents(id));
        }
    }

    @FXML
    private void showAboutPane(ActionEvent e) {
        disappearPanes();
        paneAbout.setVisible(true);
    }

    @FXML
    private void showSettingPane(ActionEvent e) {
        disappearPanes();
        LoginController loginController = new LoginController();
        User user = userController.selectUserInformation(loginController.getJobID() != null ? loginController.getJobID() : "120210235");
        if (user != null) {
            settingTxtJobID.setText(user.getJobID());
            SettingTxtName.setText(user.getUsername());
            settingTxtEmail.setText(user.getEmail());
            settingAge.setValue(user.getAge());
            paneSetting.setVisible(true);
        }
    }

    @FXML
    private void updateAccount(ActionEvent event) {
        String jobID = settingTxtJobID.getText();
        String username = SettingTxtName.getText();
        String email = settingTxtEmail.getText();
        String oldPassword = settingtxtOldPassword.getText();
        String newPassword = settingTxtNewPassword.getText();
        int age = settingAge.getValue();
        boolean isJobIDValid = jobID.matches("[0-9]+");
        boolean isUsernameValid = username.matches("\\w{3,20}");
        boolean isEmailValid = email.matches("^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$");
        boolean isPasswordValid = newPassword.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$");
        boolean isAgeValid = (age >= 10 && age <= 60);
        if (!isUsernameValid) {
            showError("Invalid job ID");
        } else if (!isEmailValid) {
            showError("Invalid email");
        } else if (!isJobIDValid) {
            showError("Invalid username");
        } else if (!isPasswordValid) {
            showError("Invalid password");
        } else if (!isAgeValid) {
            showError("Invalid age");
        } else if (oldPassword.isEmpty()) {
            showError("please fill old password");
        } else {
            User user = new User();
            user.setPassword(oldPassword);
            if (userController.checkPassword(user)) {
                user.setJobID(jobID);
                user.setUsername(username);
                user.setEmail(email);
                user.setPassword(newPassword);
                user.setAge(age);
                userController.updateUser(user);
                showSuccess("Updated successful");

            } else {
                showError("Invalid Password");
            }
        }

    }

    @FXML
    private void deleteAccount(ActionEvent e) {
        String jobID = settingTxtJobID.getText();
        if (!jobID.isEmpty()) {
            userController.deleteUser(jobID);
            showSuccess("Deleted Successful");
            // Close the current window and open the login window
            Node node = (Node) e.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.close();
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.setTitle("Login");
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(RegisterController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            showError("Deleted failed");
        }
    }

    public void logout(ActionEvent e) {
        // Close the current window and open the login window
        Node node = (Node) e.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Login");
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(RegisterController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
//convert string to integer

    private boolean isNumeric(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
// show alert message 

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
// show alert message

    private void showSuccess(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
// make fields empty

    private void makeFieldsEmptyBook() {
        bookTxtISBIN.setText("");
        bookTxtTitle.setText("");
        bookTxtAuthor.setText("");
        bookTxtQuantity.setText("");
    }

    private void makeFieldsEmptyStudent() {
        studentTxtID.setText("");
        studentTxtName.setText("");
        studentTxtMajor.setText("");
        studentTxtAddress.setText("");
        studentTxtMobile.setText("");
    }

    private void makeFieldsEmptyIssueBook() {
        issueTxtBookISBIN.setText("");
        issueTxtStudentID.setText("");
        issueTxtIssueDate.setValue(null);
        issueTxtDueDate.setValue(null);
    }

    private void makeFieldsEmptyReturnBook() {
        returnStudentID.setText("");
        returnBookISBIN.setText("");
    }
//make panes disappear

    private void disappearPanes() {
        paneDashboard.setVisible(false);
        paneBooks.setVisible(false);
        paneStudent.setVisible(false);
        paneIssueBook.setVisible(false);
        paneReturnBook.setVisible(false);
        paneLaggingStudents.setVisible(false);
        paneSetting.setVisible(false);
        paneAbout.setVisible(false);
        returnPaneFindDetail.setVisible(false);
    }

}
