package view;

import code_benjamin.Pacman;
import controller.Controller;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import model.Labyrinthe;
import model.Model;

public class View extends Parent{

    private Model model;
    private String direction;
    private boolean newDirection;

    public View(Model model) {
        //this.controller = controller;
        this.model = model;
        this.direction = "";
        this.newDirection = true;

        this.model.paicman.setOnKeyPressed(me -> {
            switch (me.getCode()){
                case LEFT:
                    if (!this.direction.equals("left")) {
                        this.direction = "left";
                        this.newDirection = true;
                    }
                    System.out.println("Pressed left");
                    break;
                case UP:
                    if (!this.direction.equals("up")) {
                        this.direction = "up";
                        this.newDirection = true;
                    }
                    System.out.println("Pressed up");
                    break;
                case RIGHT:
                    if (!this.direction.equals("right")) {
                        this.direction = "right";
                        this.newDirection = true;
                    }
                    System.out.println("Pressed right");
                    break;
                case DOWN:
                    if (!this.direction.equals("down")) {
                        this.direction = "down";
                        this.newDirection = true;
                    }
                    System.out.println("Pressed down");
                    break;
                default:
                    break;
            }
        });
    }


    public Scene getTheScene(double h, double w) {
        Group root = new Group();
        Scene scene = new Scene(root, h, w, Color.WHITE);
        GridPane gridPane = new GridPane();
        Labyrinthe labyrinthe = this.model.getLabyrinthe();
        for (int i = 0;i<28;i++){
            for (int j = 0;j<31;j++){
                if (!labyrinthe.get_lab()[i][j].get_type())
                    gridPane.add(new Rectangle(22,22),i,j);;
            }
        }
        root.getChildren().add(gridPane);
        Pacman paicman = this.model.getPaicman();
        root.getChildren().add(labyrinthe);
        root.getChildren().add(paicman);
        return scene;
    }

    public String getDirection() {
        return direction;
    }

    public boolean isNewDirection() {
        return newDirection;
    }
}
