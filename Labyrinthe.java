package model;

import code_benjamin.Case;
import code_benjamin.Position;
import javafx.scene.Parent;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Labyrinthe extends Parent{
    //(0,0) en haut � gauche du labyrinthe
    //hauteur et largeur accessible par tous a tout moment via Labyrinthe.attribut
    private int hauteur = 31*24;
    private int largeur = 28*24;
    private Case[][] lab = new Case[28][31];
    private int nbGomme = 0;
    //nbGomme correspond au nombre de gommes dans le labyrinthe
    Labyrinthe(String file){
        GridPane gridPane = new GridPane();
        int row = 0;
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            while(br.ready()){
                String s = br.readLine();
                for (int i = 0;i<28;i++){
                    if (s.charAt(i)>='a' && s.charAt(i)<='z')
                        lab[i][row] = new Case(i,row,24,false);
                    else if (s.charAt(i)=='2')
                        lab[i][row]=new Case(i,row,24,true);
                }
                row++;
            }
        }catch (FileNotFoundException e){
            System.out.println("fnfe");
        }catch (IOException e){
            System.out.println("ioe");
        }
        this.getChildren().add(gridPane);
    }
    public int get_largeur(){
        return this.largeur;
    }
    public int get_hauteur(){
        return this.hauteur;
    }
    //insere une gomme a une position en parametre
    public boolean put_gomme(Position pos){
        return put_gomme(pos.get_posx(),pos.get_posy());
    }
    public boolean put_gomme(int i, int j){
        if(i>0&&j>0&&i<this.largeur&&j<this.hauteur&&lab[i][j].get_type()){
            lab[i][j].set_item("gomme");
            return true;
        }else
            return false;
    }
    public int get_nbGomme(){
        return this.nbGomme;
    }
    public void set_nbGomme(int nb){
        this.nbGomme=nb;
    }
    public boolean dec_nbGomme(){
        if(this.nbGomme>0){
            this.nbGomme--;
            return true;
        }
        else
            return false;
    }
    public Case[][] get_lab(){
        return this.lab;
    }
    //retourne le type de case à la position (x,y) en pixel
    public boolean get_type(int x,int y){
        return this.lab[x/24][x/24].get_type();
    }
}
