package code_benjamin;

import model.Labyrinthe;


public class Pacman extends Entity{
	private int points;
	public Pacman(int posx, int posy){
		super(posx,posy,'n',"Pacman");
		this.points=0;
	}
	//action consiste en deux points:verifier si le pacman meurt a cause d'un fantome
	//puis activer un item si pr�sent
	//return false si tu�
	public boolean action(Labyrinthe lab,Entity[] listeE){
		for(int i=0;i<listeE.length-1;i++){
			if(listeE[i].get_nom().equals("fantome")&&listeE[i].get_pos().is_Equal(this.pos))
			{
				if(listeE[i].get_etat()=='F')
					listeE[i].set_etat('M');
				else
					return false;
			}
		}
		Case actuel=lab.get_lab()[this.pos.get_posx()][this.pos.get_posy()];
		if(actuel.get_item().get_nom().equals("gomme")){
			actuel.set_item("vide");
			this.points+=actuel.get_item().get_points();
			lab.dec_nbGomme();
		}
		if(actuel.get_item().get_nom().equals("supergomme")){
			for(int i=0;i<listeE.length-1;i++){
				if(listeE[i].get_nom().equals("fantome")){
					listeE[i].set_etat('F');
					listeE[i].set_timer(actuel.get_item().get_timer());
				}
			}
		}
		return true;
	}
	//{'F':forward(tente d'aller devant lui)|'L':left(tente de tourner a gauche)|
	//'R':right(tente d'aller a droite)|'B':behind(tente de faire demi-tour)}
	public void move(Labyrinthe lab,Entity pac){
        super.move(lab, pac);
		switch(this.etat){
		case 'U':
			go_up(lab);
			break;
		case 'L':
			go_left(lab);
			break;
		case 'R':
			go_right(lab);
			break;
		case 'D':
			go_down(lab);
			break;
		default:break;
		}
	}
	public void go_up(Labyrinthe lab){
        if(dispo(lab,'n'))
            tenter(lab,'n');
        else
            tenter(lab,this.dir);
	}
	public void go_left(Labyrinthe lab){
        if(dispo(lab,'o'))
            tenter(lab,'o');
        else
            tenter(lab,this.dir);
	}
	public void go_right(Labyrinthe lab){
        if(dispo(lab,'e'))
            tenter(lab,'e');
        else
            tenter(lab,this.dir);
	}
	public void go_down(Labyrinthe lab){
        if(dispo(lab,'s'))
            tenter(lab,'s');
        else
            tenter(lab,this.dir);
	}
	public void tenter(Labyrinthe lab,char target){
		if(dispo(lab,target)){
			this.dir=target;
			deplacement(lab,target);
		}
	}
}
