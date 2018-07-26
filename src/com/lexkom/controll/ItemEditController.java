package com.lexkom.controll;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.lexkom.model.Items;
import com.lexkom.utility.Action_Events;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class ItemEditController {
    @FXML
    private AnchorPane rootPane;
    @FXML
    private JFXTextField nameOfItem;
    @FXML
    private JFXTextField USD;
    @FXML
    private JFXTextField UAH;
    @FXML
    private JFXTextField price;
    @FXML
    private JFXTextField quantity;
    @FXML
    private JFXTextField availability;
    @FXML
    private JFXButton cancel_btn;
    @FXML
    private JFXButton confirm_btn;

    private Stage dialogStage;
    private Items item;
    private boolean okClicked = false;


    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        Action_Events.addDraggableNode(rootPane);
    }

    /**
     * Sets the stage of this dialog.
     *
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    /**
     * Sets the item to be edited in the dialog.
     *
     * @param item
     */
    public void setItem(Items item) {
        this.item = item;

        nameOfItem.setText(item.getName());
        USD.setText(item.getCostUSD());
        UAH.setText(item.getCostUAH());
        price.setText(item.getPrice());
        quantity.setText(item.getQuantity());
        availability.setText(item.getAvailability());
    }

    /**
     * Returns true if the user clicked OK, false otherwise.
     *
     * @return
     */
    public boolean isOkClicked() {
        return okClicked;
    }

    /**
     * Called when the user clicks ok.
     */
  @FXML
    private void handleOk() {
        if (isInputValid()) {
            item.setName(nameOfItem.getText());
            item.setCostUSD(USD.getText());
            item.setCostUAH(UAH.getText());
            item.setPrice(price.getText());
            item.setQuantity(quantity.getText());
            item.setAvailability(availability.getText());

            okClicked = true;
            dialogStage.close();
        }
    }

    /**
     * Called when the user clicks cancel.
     */
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    /**
     * Validates the user input in the text fields.
     *
     * @return true if the input is valid
     */
    private boolean isInputValid() {
        String errorMessage = "";
        return true;// later take from here!!!

        /*if (firstNameField.getText() == null || firstNameField.getText().length() == 0) {
            errorMessage += "No valid first name!\n";
        }
        if (lastNameField.getText() == null || lastNameField.getText().length() == 0) {
            errorMessage += "No valid last name!\n";
        }
        if (streetField.getText() == null || streetField.getText().length() == 0) {
            errorMessage += "No valid street!\n";
        }

        if (postalCodeField.getText() == null || postalCodeField.getText().length() == 0) {
            errorMessage += "No valid postal code!\n";
        } else {
            // try to parse the postal code into an int.
            try {
                Integer.parseInt(postalCodeField.getText());
            } catch (NumberFormatException e) {
                errorMessage += "No valid postal code (must be an integer)!\n";
            }
        }

        if (cityField.getText() == null || cityField.getText().length() == 0) {
            errorMessage += "No valid city!\n";
        }

        if (birthdayField.getText() == null || birthdayField.getText().length() == 0) {
            errorMessage += "No valid birthday!\n";
        } else {
            if (!DateUtil.validDate(birthdayField.getText())) {
                errorMessage += "No valid birthday. Use the format dd.mm.yyyy!\n";
            }
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }*/
    }



}
