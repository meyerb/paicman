package code_benjamin;

public class Item {
	protected String nom;
	//poins attribu� lorsque utilis�
	protected int points;
	//timer pour le temps d'un effet bonus
	//Seul cas : supergomme
	protected int timer;
	Item(String _nom,int _points){
		this.nom=_nom;
		this.points=_points;
		this.timer=0;
	}
	Item(String _nom,int _points,int _timer){
		this.nom=_nom;
		this.points=_points;
		this.timer=_timer;
	}
	public String get_nom(){
		return this.nom;
	}
	public int get_points(){
		return this.points;
	}
	public int get_timer(){
		return this.timer;
	}
	public void set_timer(int _timer){
		this.timer=_timer;
	}
	public String toString(){
		return "{"+this.nom+"||"+this.points+"}";
	}
}
