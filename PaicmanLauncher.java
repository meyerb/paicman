package main;

import controller.Controller;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.stage.Stage;
import model.Model;
import view.View;

public class PaicmanLauncher extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Model model = new Model("src\\assets\\1.map");
        View view = new View(model);
        primaryStage.setTitle("Labyrinthe");
        //primaryStage.getIcons().add()
        primaryStage.setScene(view.getTheScene(primaryStage.getHeight(),primaryStage.getWidth()));
        primaryStage.setResizable(false);
        primaryStage.sizeToScene();
        primaryStage.show();
        Controller controller = new Controller(view,model);
        model.paicman.requestFocus();
        //model.getLabyrinthe().afficherType();
        new AnimationTimer() {

            @Override
            public void handle(long now) {
                controller.control();
            }
        }.start();


    }


}