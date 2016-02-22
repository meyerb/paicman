package model;

import code_benjamin.Pacman;
import javafx.scene.paint.Color;

import java.io.File;

public class Model {

    public Labyrinthe labyrinthe;
    public Pacman paicman;
    public String direction;

    public Model(String file){
        this.labyrinthe = new Labyrinthe(file);
        this.paicman = new Pacman(14,23);
    }

    public void haut(){
        this.paicman.setTranslateY(this.paicman.getTranslateY()-2);
        this.direction="up";
    }
    public void bas(){
        this.paicman.setTranslateY(this.paicman.getTranslateY()+2);
        this.direction="down";
    }
    public void droite(){
        this.paicman.setTranslateX(this.paicman.getTranslateX()+2);
        this.direction="right";
    }
    public void gauche(){
        this.paicman.setTranslateX(this.paicman.getTranslateX()-2);
        this.direction="left";
    }

    public Labyrinthe getLabyrinthe() {
        return this.labyrinthe;
    }

    public Pacman getPaicman() {
        return this.paicman;
    }

    public String getDirection() {
        return direction;
    }
}
