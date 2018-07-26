package com.lexkom.controll;

import com.jfoenix.controls.*;
import com.lexkom.utility.Action_Events;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.BoundingBox;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StartwindowController {

    @FXML
    private AnchorPane holderPane;
    @FXML
    private JFXToolbar window;

    @FXML
    private AnchorPane sidePane;

    @FXML
    private Label txtCurrentWindow;

    @FXML
    private Button logoutBtn;


    public StartwindowController() throws IOException {
    }


    private void setNode(Node node) {
        AnchorPane.setBottomAnchor(node, 0.);
        AnchorPane.setTopAnchor(node, 0.);
        AnchorPane.setLeftAnchor(node, 0.);
        AnchorPane.setRightAnchor(node, 0.);
        holderPane.getChildren().clear();
        holderPane.getChildren().add((Node) node);
    }


    @FXML
    public void closelabel (){
        System.exit(0);
    }


    @FXML
    private void logOut(ActionEvent event) {
        try {
            Stage window=(Stage) logoutBtn.getScene().getWindow();
            DiplomaFX diplomaFX=new DiplomaFX();
            diplomaFX.start(new Stage());
            window.close();
        } catch (Exception ex) {
            Logger.getLogger(StartwindowController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    public void minimizeStage() {
        Stage stage=(Stage) window.getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    public void maximizeStage() {
        Stage stage=(Stage) window.getScene().getWindow();
//        Screen screen = Screen.getPrimary();
//        Rectangle2D bounds = screen.getVisualBounds();
////        stage.setX(bounds.getMinX());
////        stage.setY(bounds.getMinY());
////        stage.setWidth(bounds.getWidth());
////        stage.setHeight(bounds.getHeight());
//
//
//        stage.setX(bounds.getMinX());
//        stage.setY(bounds.getMinY());
//
//        stage.setMaxWidth(bounds.getWidth());
//        stage.setMinWidth(bounds.getWidth());
//
//        stage.setMaxHeight(bounds.getHeight());
//        stage.setMinHeight(bounds.getHeight());
        stage.setMaximized(true);
//        if (stage.isMaximized()){
//            stage.setMaximized(false);
//        }
    }



    @FXML
    public void initialize() {
        Action_Events.addDraggableNode(window);

        try {

            AnchorPane marketPane = FXMLLoader.load(getClass().getResource("../view/StocksView.fxml"));

//            AnchorPane welcome = FXMLLoader.load(getClass().getResource(Routes.WELCOMEVIEW));
//            setNode(marketPane);
            for (Node node:sidePane.getChildrenUnmodifiable()) {
                System.out.println(node.getAccessibleText());

            }


            for (Node node : sidePane.getChildren()) {
                if (node.getAccessibleText() != null) {
                    node.addEventHandler(MouseEvent.MOUSE_PRESSED, (MouseEvent ev) -> {
                        switch (node.getAccessibleText()) {
//                            case "homeMenu":
//                                drawer.close();
//                                setNode(welcome);
//                                break;
                            case "storesMenu":
                                setNode(marketPane);
                                txtCurrentWindow.setText("Stores");
                                break;
//                            case "currencyMenu":
//                                setNode(payments);
//                                break;

                        }
                    });
                }

            }

        } catch (IOException ex) {
            Logger.getLogger(StartwindowController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}

