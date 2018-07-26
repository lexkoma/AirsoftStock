package com.lexkom.utility;

import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import javax.xml.soap.Node;

public class Action_Events {
    private static double xOffset;
    private static double yOffset;


    public static void addDraggableNode(Parent window){

        window.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });

        window.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                window.getScene().getWindow().setX(event.getScreenX() - xOffset);
                window.getScene().getWindow().setY(event.getScreenY() - yOffset);
            }
        });
    }
}
