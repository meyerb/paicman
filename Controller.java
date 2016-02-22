package controller;

import javafx.scene.Parent;
import view.*;
import model.*;

public class Controller extends Parent {

    private View  view;
    private Model model;

    public Controller(View view, Model model){
        this.view  = view;
        this.model = model;
    }

    public void control(){
        //if (!this.view.getDirection().equals(this.model.getDirection())){
        //if (this.view.isNewDirection()) {
            switch (this.view.getDirection()) {
                case "up":
                    this.model.paicman.set_etat('U');
                    this.model.paicman.deplacement(this.model.labyrinthe,'n');
                    break;
                case "down":
                    this.model.paicman.set_etat('D');
                    this.model.paicman.deplacement(this.model.labyrinthe,'s');
                    break;
                case "right":
                    this.model.paicman.set_etat('R');
                    this.model.paicman.deplacement(this.model.labyrinthe,'e');
                    break;
                case "left":
                    this.model.paicman.set_etat('L');
                    this.model.paicman.deplacement(this.model.labyrinthe,'o');
                    break;
                default:
                    break;
            }
        //}
        //}
    }

}
