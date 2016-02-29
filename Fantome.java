package code_benjamin;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import model.Labyrinthe;


public class Fantome extends Entity{
	private Position coin;
	//coin correspond � l'endroit attribu� pour un fantome (maison)
	//{1:(0,0)|2:(0,fin_y)|3:(fin_x,0)|4:(fin_x,fin_y)}
	public Fantome(){
		super(-1,-1,'n',"fantome");
		this.shape= new Circle((-1+11), (-1+11), 10, Color.PALETURQUOISE);
        this.getChildren().add(shape);
		coin=new Position(0,0);
	}
	public Fantome(int posx,int posy){
		super(posx,posy,'n',"fantome");
		this.shape= new Circle((posx+11), (posy+11), 10, Color.PALETURQUOISE);
        this.getChildren().add(shape);
		coin=new Position(-1,-1);
	}
	public Fantome(int posx,int posy,int _coin){
		super(posx,posy,'n',"fantome");
		this.shape= new Circle((posx+11), (posy+11), 10, Color.PALETURQUOISE);
        this.getChildren().add(shape);
		switch(_coin){
		case 1:this.coin=new Position(0,0);
			break;
		case 2:this.coin=new Position(0,31*22);
			break;
		case 3:this.coin=new Position(28*22,0);
			break;
		case 4:this.coin=new Position(28*22,31*22);
			break;
		default:this.coin=new Position(-1,-1);
			break;
		}
	}
	public String toString(){
		return "["+this.nom+"|"+this.dir+"|"+this.pos.toString()+"|Coin:"+
				this.coin.toString()+"]";
	}
	//{'H':hunt(chasse le pacman)|'A':ambush(tenter une ambuscade du pacman)|
	//'E':escape(tente d'eviter le pacman)|'R':rentre dans son coin si a moins
	//d'un rayon de 9 cases sinon chasse}
	public void move(Labyrinthe lab,Entity pac){
		super.move(lab, pac);
		switch(this.etat){
		case 'H':
			hunt(lab,pac);
			break;
		case 'A':
			ambush(lab,pac);
			break;
		case 'R':
			rentre(lab,pac);
		case 'E':
			escape(lab);
			if(timer==0)
				this.etat='H';
			break;
		default:break;
		}
	}
	//tente de fuir pacman (random)
	public void escape(Labyrinthe lab){
		
	}
	//suit le pacman si a plus de 9 cases, sinon rentre dans son coin
	public void rentre(Labyrinthe lab,Entity pac){
		if(this.pos.diffx(pac.get_pos())>-(9*22)&&this.pos.diffx(pac.get_pos())<(9*22)&&
			this.pos.diffy(pac.get_pos())>-(9*22)&&this.pos.diffy(pac.get_pos())<(9*22)){
			tenter(lab,this.coin);
		}
		else
			hunt(lab,pac);
	}
	//tente d'aller deux cases devant le pacman
	public void ambush(Labyrinthe lab,Entity pac){
		Position p=new Position(pac.get_pos());
		switch(pac.get_dir()){
			case 'n':
				p.set_posy(pac.get_pos().get_posy()-2);
				break;
			case 's':
				p.set_posy(pac.get_pos().get_posy()+2);
				break;
			case 'o':
				p.set_posx(pac.get_pos().get_posx()-2);
				break;
			case 'e':
				p.set_posx(pac.get_pos().get_posx()+2);
				break;
		}
		tenter(lab,p);
	}
	public void hunt(Labyrinthe lab,Entity pac){
		tenter(lab,pac.get_pos());
	}
	public void tenter(Labyrinthe lab,Position pos){
		char dir_pos=this.get_pos().get_dir(pos);
		if(dir_pos==this.get_dir()){
			if(dispo(lab,dir)){
				deplacement(lab,dir_pos);
			}
			else if(is_Right(pos)&&dispo(lab,dir_right(this.dir))){
				this.dir=dir_right(this.dir);
				deplacement(lab,this.dir);
			}
			else if(is_Left(pos)&&dispo(lab,dir_left(this.dir))){
				this.dir=dir_left(this.dir);
				deplacement(lab,this.dir);
			}
			else if(dispo(lab,dir_right(this.dir))){
				this.dir=dir_right(this.dir);
				deplacement(lab,this.dir);
			}
			else if(dispo(lab,dir_left(this.dir))){
				this.dir=dir_left(this.dir);
				deplacement(lab,this.dir);
			}
			else if(dispo(lab,dir_behind(this.dir))){
				this.dir=dir_behind(this.dir);
				deplacement(lab,this.dir);
			}
		}
		else if(dir_pos==dir_behind(this.dir)){
			if(is_Right(pos)&&dispo(lab,dir_right(this.dir))){
				this.dir=dir_right(this.dir);
				deplacement(lab,this.dir);
			}
			else if(is_Left(pos)&&dispo(lab,dir_left(this.dir))){
				this.dir=dir_left(this.dir);
				deplacement(lab,this.dir);
			}
			else if(dispo(lab,dir_right(this.dir))){
				this.dir=dir_right(this.dir);
				deplacement(lab,this.dir);
			}
			else if(dispo(lab,dir_left(this.dir))){
				this.dir=dir_left(this.dir);
				deplacement(lab,this.dir);
			}
			else if(dispo(lab,this.dir)){
				deplacement(lab,this.dir);
			}
			else if(this.dispo(lab,dir_behind(this.dir))){
				this.dir=dir_behind(this.dir);
				deplacement(lab,this.dir);
			}
		}
		else{
			if(dispo(lab,dir_pos)){
				this.dir=dir_pos;
				deplacement(lab,this.dir);
			}
			else if(dispo(lab,this.dir))
				deplacement(lab,this.dir);
			else if(dispo(lab,dir_behind(dir_pos))){
				this.dir=dir_behind(dir_pos);
				deplacement(lab,this.dir);
			}
			else if(dispo(lab,dir_behind(this.dir))){
				this.dir=dir_behind(this.dir);
				deplacement(lab,this.dir);
			}
		}
	}
}
