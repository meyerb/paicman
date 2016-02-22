package code_benjamin;

import model.Labyrinthe;


public class Fantome extends Entity{
	private Position coin;
	//coin correspond � l'endroit attribu� pour un fantome (maison)
	//{1:(0,0)|2:(0,fin_y)|3:(fin_x,0)|4:(fin_x,fin_y)}
	Fantome(){
		super(-1,-1,'n',"fantome");
		coin=new Position(0,0);
	}
	Fantome(int posx,int posy){
		super(posx,posy,'n',"fantome");
		coin=new Position(-1,-1);
	}
	Fantome(int posx,int posy,int _coin){
		super(posx,posy,'n',"fantome");
		switch(_coin){
		case 1:this.coin=new Position(0,0);
			break;
		case 2:this.coin=new Position(0,1);
			break;
		case 3:this.coin=new Position(1,0);
			break;
		case 4:this.coin=new Position(1,1);
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
	//'E':escape(tente d'eviter le pacman)}
	public void move(Labyrinthe lab,Entity pac){
		super.move(lab, pac);
		switch(this.etat){
		case 'H':
			hunt(lab,pac);
			break;
		case 'A':
			ambush(lab,pac);
			break;
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
	//tente d'aller sur la position du pacman
	public void hunt(Labyrinthe lab,Entity pac){
		tenter(lab,pac.get_pos(),this.get_dir_comparer(pac.get_pos()));
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
		tenter(lab,p,this.get_dir_comparer(p));
	}
}
