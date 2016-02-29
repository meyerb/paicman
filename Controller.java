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
    	this.model.fantome.set_etat('R');
            switch (this.view.getDirection()) {
                case "up":
                	this.model.paicman.go_up(this.model.labyrinthe);
                    break;
                case "down":
                	this.model.paicman.go_down(this.model.labyrinthe);
                    break;
                case "right":
                	this.model.paicman.go_right(this.model.labyrinthe);
                    break;
                case "left":
                	this.model.paicman.go_left(this.model.labyrinthe);
                    break;
                default:
                    break;
            }
            this.model.getFantome().move(this.model.getLabyrinthe(),this.model.paicman);
            if(this.model.paicman.collision(this.model.getFantome()))
            	System.out.println("TOUCHE");
    }

}
