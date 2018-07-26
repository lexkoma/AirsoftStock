package com.lexkom.controll;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.lexkom.utility.Action_Events;
import com.lexkom.utility.SQLDataBaseHelper;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.sqlite.SQLiteException;

import java.sql.SQLException;

public class RegistrationController extends Thread {

    private double xOffset = 0;
    private double yOffset = 0;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private JFXButton cancelButton;
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
    public void cancel (){
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void registration (){
        String login = loginField.getText().trim();
        System.out.println(login);
        String pass_1 = firstPass.getText().trim();
        String pass_2 = repeatPass.getText().trim();
        if (pass_1.equals(pass_2)){
            System.out.println("correct");
        } else System.out.println("invalid");
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
//                Parent home_page_parent =  FXMLLoader.load(getClass().getResource("../view/StartWindow.fxml"));
//                Scene home_page_scene = new Scene(home_page_parent);
//                Stage app_stage = (Stage) confirmButton.getScene().getWindow();
//                app_stage.setScene(home_page_scene);
//                app_stage.show();
                confirmButton.getScene().getWindow().hide();

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
