package com.lexkom.controll;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.lexkom.utility.Action_Events;
import com.lexkom.utility.SQLDataBaseHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class NewLoginController {

    @FXML
    private AnchorPane rootPane;
    @FXML
    private AnchorPane signinPane;
    @FXML
    private AnchorPane signupPane;
    @FXML
    private JFXButton btn_signIn;
    @FXML
    private JFXButton btn_signUn;

    @FXML
    private JFXButton login;
    @FXML
    private JFXButton cancelButton;
    @FXML
    private JFXButton cancelIn;
    @FXML
    private JFXButton confirmButton;
    @FXML
    private JFXTextField loginField;
    @FXML
    private JFXPasswordField firstPass;
    @FXML
    private JFXPasswordField repeatPass;
    @FXML
    private Label invalidEmail;
    @FXML
    private Label warningLabel;

    @FXML
    private Label invalid_label;

    @FXML
    private JFXTextField username_box;

    @FXML
    private JFXPasswordField password_box;

    @FXML
    public void cancelAction (ActionEvent event){
        if (event.getSource() == cancelButton){
            signinPane.toFront();
        } else if (event.getSource() == cancelIn )
        System.exit(0);
    }



    @FXML
    private void changeStages (ActionEvent event){
        if (event.getSource() == btn_signIn){
            signinPane.toFront();
        } else if (event.getSource() == btn_signUn){

            signupPane.toFront();
        }
    }

    @FXML
    private void loginAction(ActionEvent event) throws IOException {



        if (isValidCredentials())
        {
            System.out.println("DO IT");
            Parent home_page_parent =  FXMLLoader.load(getClass().getResource("../view/GettingStart.fxml"));
            Scene home_page_scene = new Scene(home_page_parent);
            Stage app_stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            app_stage.hide(); //optional
            app_stage.setScene(home_page_scene);
            app_stage.show();
        }
        else
        {
            username_box.clear();
            password_box.clear();
            invalid_label.setText("Sorry, invalid credentials");
        }
    }

    private boolean isValidCredentials()
    {
        boolean let_in = false;
//        System.out.println( "SELECT * FROM Users WHERE USERNAME= " + "'" + username_box.getText() + "'"
//                + " AND PASSWORD= " + "'" + password_box.getText() + "'" );


        Connection c = null;
        Statement stmt = null;
        try {

            c = DriverManager.getConnection("jdbc:sqlite:userAccounts.db");
            c.setAutoCommit(false);

            System.out.println("Opened database successfully");
            stmt = c.createStatement();

            ResultSet rs = stmt.executeQuery( "SELECT * FROM users WHERE EMAIL= " + "'" + username_box.getText() + "'"
                    + " AND PASSWORD= " + "'" + password_box.getText() + "'");

            while ( rs.next() ) {
                if (rs.getString("EMAIL") != null && rs.getString("PASSWORD") != null) {
                    String  username = rs.getString("EMAIL");
//                    System.out.println( "USERNAME = " + username );
                    String password = rs.getString("PASSWORD");
//                    System.out.println("PASSWORD = " + password);
                    let_in = true;
                }
            }
            rs.close();
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Operation done successfully");
        return let_in;

    }

    @FXML
    private void registrateUser () throws SQLException, ClassNotFoundException {
        try {
            SQLDataBaseHelper.initDB();
            String login = loginField.getText().trim();
            String pass_1 = firstPass.getText().trim();
            String pass_2 = repeatPass.getText().trim();

            if (isValidInput() && isValidEmailInput(login) & isValidPass(pass_1, pass_2)){
                SQLDataBaseHelper.insertIntoDB(login, pass_1);



                Parent home_page_parent =  FXMLLoader.load(getClass().getResource("../view/GettingStart.fxml"));
                Scene home_page_scene = new Scene(home_page_parent);
                Stage app_stage = (Stage) confirmButton.getScene().getWindow();

                    app_stage.hide(); //optional
                    app_stage.setScene(home_page_scene);
                    app_stage.show();

            }
        } catch (Exception e){
            e.printStackTrace();
        }
//            EmployeeDAO.insertEmp(loginField.getText(),firstPass.getText(),emailText.getText());
//            resultArea.setText("Employee inserted! \n");
//        } catch (SQLException e) {
//            resultArea.setText("Problem occurred while inserting employee " + e);
//            throw e;
//        }
    }

    private boolean isValidInput(){
        if (loginField.getText().isEmpty() && firstPass.getText().isEmpty() && repeatPass.getText().isEmpty()){
            warningLabel.setText("Please enter all needed information");
            return false;
        } else warningLabel.setText("");
        return true;
    }

    private boolean isValidEmailInput(String mail){
        String emailPattern = "\\w+([-+.']\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";
        if (mail.isEmpty()){
            warningLabel.setText("Enter email");
            return false;
        } else if (!mail.matches(emailPattern)){
            invalidEmail.setText("Enter correct email");
            warningLabel.setText("");
            return false;
        }
        invalidEmail.setText("");
        warningLabel.setText("");
        return true;
    }

    private boolean isValidPass(String pass1, String pass2){
        if (pass1.isEmpty() || pass2.isEmpty()){
            warningLabel.setText("Enter password");
            return false;
        } else if (!pass1.equals(pass2)){
            warningLabel.setText("Passwords don't match");
            firstPass.clear();
            repeatPass.clear();
            return false;
        }
        warningLabel.setText("");
        return true;
    }


    @FXML
    public void initialize() {
        Action_Events.addDraggableNode(rootPane);
    }
}

