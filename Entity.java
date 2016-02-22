package code_benjamin;

import javafx.scene.Parent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import model.Labyrinthe;


public class Entity extends Parent{
    public Circle shape= new Circle((int)((600/28)*14.5), (int)((700/31)*23.5), 10, Color.GOLD);
	protected Position pos;
	//landmark correspondant � une coordonn�e fix� qui peut �tre utilis�
	protected Position landmark;
	protected String nom;
	protected int timer;
	//dir correspond � la direction vers lequel est tourn� l'entit�
		//{'n'|'o'|'s'|'e'}
	protected char dir;
	//etat correspond � l'�tat du fantome
	protected char etat;
	Entity(){
		this.pos=new Position();
		this.landmark=new Position();
		this.dir='n';
		this.nom="inconnu";
		this.etat='I';
		this.timer=0;
        this.getChildren().add(shape);
	}
	Entity(int posx,int posy){
		this.pos=new Position(posx,posy);
		this.landmark=new Position();
		this.dir='n';
		this.nom="inconnu";
		this.etat='I';
		this.timer=0;
        this.getChildren().add(shape);
	}
	Entity(int posx,int posy, char _dir){
		this.pos=new Position(posx,posy);
		this.landmark=new Position();
		this.dir=_dir;
		this.nom="inconnu";
		this.etat='I';
		this.timer=0;
        this.getChildren().add(shape);
	}
	Entity(int posx,int posy, char _dir,String _nom){
		this.pos=new Position(posx*600/28,posy*700/31);
		this.landmark=new Position();
		this.dir=_dir;
		this.nom=_nom;
		this.etat='I';
		this.timer=0;
        this.getChildren().add(shape);
	}
	public String toString(){
		return "["+this.nom+"|"+this.dir+"|"+this.pos.toString()+"|etat:"+this.etat+"]";
	}
	public Position get_pos(){
		return this.pos;
	}
	public void set_pos(Position p){
		this.set_pos(p.get_posx(),p.get_posy());
	}
	public void set_pos(int _posx,int _posy){
		this.pos.set_pos(_posx,_posy);
	}
	public char get_dir(){
		return this.dir;
	}
	public Position get_landmark(){
		return this.landmark;
	}
	public void set_landmark(Position p){
		set_landmark(p.get_posx(),p.get_posy());
	}
	public void set_landmark(int x,int y){
		this.landmark.set_pos(x, y);
	}
	public char dir_left(char _dir){
		switch(_dir){
			case 'n':
				return 'o';
			case 'o':
				return 's';
			case 'e':
				return 'n';
			case 's':
				return 'e';
			default:
				return _dir;
		}
	}
	public char dir_right(char _dir){
		switch(_dir){
		case 'n':
			return 'e';
		case 'o':
			return 'n';
		case 'e':
			return 's';
		case 's':
			return 'o';
		default:
			return _dir;
	}
	}
	public char dir_behind(char _dir){
		switch(_dir){
		case 'n':
			return 's';
		case 'o':
			return 'e';
		case 'e':
			return 'o';
		case 's':
			return 'n';
		default:
			return _dir;
		}
	}
	//p est-il a gauche de l'entity(par rapport a la direction)
	public boolean is_Left(Position p){
		switch(this.dir){
			case 'n':
				if(this.pos.diffx(p)>=0)
					return true;
				else
					return false;
			case 's':
				if(this.pos.diffx(p)<=0)
					return true;
				else
					return false;
			case 'o':
				if(this.pos.diffy(p)<=0)
					return true;
				else
					return false;
			case 'e':
				if(this.pos.diffy(p)>=0)
					return true;
				else
					return false;
			default:
				return false;
		}
	}
	//p est-il a droite de l'entity(par rapport a la direction)
	public boolean is_Right(Position p){
		switch(this.dir){
		case 'n':
			if(this.pos.diffx(p)<=0)
				return true;
			else
				return false;
		case 's':
			if(this.pos.diffx(p)>=0)
				return true;
			else
				return false;
		case 'o':
			if(this.pos.diffy(p)>=0)
				return true;
			else
				return false;
		case 'e':
			if(this.pos.diffy(p)<=0)
				return true;
			else
				return false;
		default:
			return false;
		}
	}
	//p est-il derriere l'entity(par rapport a la direction)
	public boolean is_Behind(Position p){
		switch(this.dir){
		case 'n':
			if(this.pos.diffy(p)<=0)
				return true;
			else
				return false;
		case 's':
			if(this.pos.diffy(p)>=0)
				return true;
			else
				return false;
		case 'o':
			if(this.pos.diffx(p)<=0)
				return true;
			else
				return false;
		case 'e':
			if(this.pos.diffx(p)>=0)
				return true;
			else
				return false;
		default:
			return false;
		}
	}
	//p est-il devant l'entity(par rapport a la direction)
	public boolean is_Forward(Position p){
		switch(this.dir){
		case 'n':
			if(this.pos.diffy(p)>=0)
				return true;
			else
				return false;
		case 's':
			if(this.pos.diffy(p)<=0)
				return true;
			else
				return false;
		case 'o':
			if(this.pos.diffx(p)>=0)
				return true;
			else
				return false;
		case 'e':
			if(this.pos.diffx(p)<=0)
				return true;
			else
				return false;
		default:
			return false;
	}
	}
	public boolean set_dir(char _dir){
		if(_dir=='n'||_dir=='o'||_dir=='e'||_dir=='s'){
			this.dir=_dir;
			return true;
		}
		else
			return false;
	}
	public char get_etat(){
		return this.etat;
	}
	public void set_etat(char c){
		this.etat=c;
	}
	public void set_timer(int t){
		this.timer=t;
	}
	public String get_nom(){
		return this.nom;
	}
	//retourne la direction g�n�ral d'une position par rapport � la position de cette entity
	public char get_dir_comparer(Position p){
		return this.pos.get_dir(p);
	}
	//l'appel de move permet de modifier les attributs de l'Entity
	//en fonction de son etat en appelant la fonction de mouvement
	//qui lui est attribu�
	//{'L':landmark(tente d'aller sur le repere)|'I':idle(ne tente rien)}
	public void move(Labyrinthe lab,Entity pac){
		if(this.timer>0)
			this.timer--;
		switch(this.etat){
		case 'L':
			go_landmark(lab);
			break;
		case 'I':
			break;
		default:break;
		}
	}
	//tente d'aller sur le rep�re
	public void go_landmark(Labyrinthe lab){
		tenter(lab,this.landmark,this.get_dir_comparer(this.landmark));
	}
	//tente un deplacement vers la direction en parametre
	public void deplacement(Labyrinthe lab,char _dir){
		switch(_dir){
			case 'n':
				deplacement(lab,this.pos.get_posx(),this.pos.get_posy()-1);
				break;
			case 's':
				deplacement(lab,this.pos.get_posx(),this.pos.get_posy()+1);
				break;
			case 'o':
				deplacement(lab,this.pos.get_posx()-1,this.pos.get_posy());
				break;
			case 'e':
				deplacement(lab,this.pos.get_posx()+1,this.pos.get_posy());
				break;
			default:
				deplacement(lab,this.pos);
				break;
		}
	}
	//tente un deplacement sur la position en parametre
	public void deplacement(Labyrinthe lab,Position p){
		deplacement(lab,p.get_posx(),p.get_posy());
	}
	public void deplacement(Labyrinthe lab,int _posx,int _posy){
		if(_posx<0){
			if(lab.get_type(lab.get_largeur()-1,this.pos.get_posy()))
				this.pos.set_posx(lab.get_largeur()-1);
		}
		else if(_posx>=lab.get_largeur()){
			if(lab.get_type(0,this.pos.get_posy()))
				this.pos.set_posx(0);
		}
		else if(_posy<0){
            System.out.println("Is ok");
			if(lab.get_type(this.pos.get_posx(),lab.get_hauteur()-1))
				this.pos.set_posy(lab.get_hauteur()-1);
		}
		else if(_posy>=lab.get_hauteur()){
			if(lab.get_type(this.pos.get_posx(),0))
				this.pos.set_posy(0);
		}
		else{
			this.pos.set_pos(_posx,_posy);
            System.out.println(this.etat);
            switch(this.etat){
                case 'U':
                    this.setTranslateY(this.getTranslateY()-1);
                    break;
                case 'D':
                    this.setTranslateY(this.getTranslateY()+1);
                    break;
                case 'L':
                    this.setTranslateX(this.getTranslateX()-1);
                    break;
                case 'R':
                    this.setTranslateX(this.getTranslateX()+1);
                    break;
            }
        }
	}
	//regarde si la case vers la direction en parametre est libre
	//on consid�rera une sortie du labyrinthe comme �tant possible
	public boolean dispo(Labyrinthe lab,char _dir){
		switch(_dir){
			case 'n':
				if(this.pos.get_posy()-1>=0){
					return lab.get_type(this.pos.get_posx(),this.pos.get_posy()-1);
				}
				else{
					return true;}
			case 'e':
				if(this.pos.get_posx()+1<lab.get_largeur()){
					return lab.get_type(this.pos.get_posx()+1,this.pos.get_posy());
				}
				else{
					return true;}
			case 'o':
				if(this.pos.get_posx()-1>=0){
					return lab.get_type(this.pos.get_posx()-1,this.pos.get_posy());
				}
				else{
					return true;}
			case 's':
				if(this.pos.get_posy()+1<lab.get_hauteur()){
					return lab.get_type(this.pos.get_posx(),this.pos.get_posy()+1);
				}
				else{
					return true;}
			default:
				return false;
		}
	}
	//tentative de deplacement en g�n�ral du fantome (a deplacer vers fantome et remplacer par
	//un mouvement basique vers l'avant)
	//si direction initiale bloquer, regarde les autres directions possible en fonction de
	//l'emplacement du pacman
	//CAS GENERAL: aucun fantome ne peux faire demi-tour sauf si obliger
	public void tenter(Labyrinthe lab,Position target,char tenter_dir){
		if(!dispo(lab,tenter_dir)){
			if(tenter_dir==this.dir||tenter_dir==this.dir_behind(dir)){
				if(is_Left(target)&&dispo(lab,dir_left(this.dir))){
					this.dir=dir_left(this.dir);
					deplacement(lab,this.dir);
				}
				else if(is_Right(target)&&dispo(lab,dir_right(this.dir))){
					this.dir=dir_right(this.dir);
					deplacement(lab,this.dir);
				}
				else if(dispo(lab,dir_left(this.dir))){
					this.dir=dir_left(this.dir);
					deplacement(lab,this.dir);
				}
				else if(dispo(lab,dir_right(this.dir))){
					this.dir=dir_right(this.dir);
					deplacement(lab,this.dir);
				}
				else if(tenter_dir==this.dir&&dispo(lab,dir_behind(this.dir))){
					this.dir=dir_behind(this.dir);
					deplacement(lab,this.dir);
				}
				else if(tenter_dir==this.dir_behind(this.dir)&&dispo(lab,this.dir)){
					deplacement(lab,this.dir);
				}
			}
			else{
				if(dispo(lab,this.dir))
					deplacement(lab,this.dir);
				else if(dispo(lab,this.dir_behind(tenter_dir))){
					this.dir=this.dir_behind(tenter_dir);
					deplacement(lab,this.dir);
				}
				else if(dispo(lab,this.dir_behind(this.dir))){
					this.dir=this.dir_behind(this.dir);
					deplacement(lab,this.dir);
				}
			}
		}
		else{
			if(tenter_dir!=this.dir_behind(dir)){
				this.dir=tenter_dir;
				deplacement(lab,this.dir);
			}
			else{
				if(is_Left(target)&&dispo(lab,dir_left(this.dir))){
					this.dir=dir_left(this.dir);
					deplacement(lab,this.dir);
				}
				else if(is_Right(target)&&dispo(lab,dir_right(this.dir))){
					this.dir=dir_right(this.dir);
					deplacement(lab,this.dir);
				}
				else if(dispo(lab,dir_left(this.dir))){
					this.dir=dir_left(this.dir);
					deplacement(lab,this.dir);
				}
				else if(dispo(lab,dir_right(this.dir))){
					this.dir=dir_right(this.dir);
					deplacement(lab,this.dir);
				}
				else if(dispo(lab,this.dir)){
					deplacement(lab,this.dir);
				}
				else{
					this.dir=dir_behind(this.dir);
					deplacement(lab,this.dir);
				}
			}
		}
	}
}
