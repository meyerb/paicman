package code_benjamin;

public class Case {
	private Position pos;
	private int taille;
	//type correspond au type de case (false:mur|true:chemin libre)
	private boolean type;
	//item correspond � l'objet pr�sent sur la position (gomme,bonus...)
	private Item item;
	//apparence correspond � l'aspect visuel (pour l'instant un entier)
	private int apparence;
	public Case(int posx, int posy,int taille,boolean _type){
		this.pos=new Position(posx*taille,posy*taille);
		this.type=_type;
		if(_type){
			this.item=new Vide();
			this.apparence=0;
		}
		else{
			this.item=null;
			this.apparence=1;
		}
	}
	//_typer>2 correspond a un mur
	Case(int posx,int posy,int taille,int _type){
		this.pos=new Position(posx*taille,posy*taille);
		switch(_type){
			case 0:
				this.item=new Vide();
				this.type=true;
				break;
			case 1:
				this.item=new Gomme();
				this.type=true;
				break;
			case 2:
				this.item=new SuperGomme();
				this.type=true;
				break;
			default:
				this.item=null;
				this.type=false;
				break;
		}
		this.apparence=_type;
	}
	public boolean get_type(){
		return this.type;
	}
	public Item get_item(){
		return this.item;
	}
	//change l'item en fonction du nom en parametre
	public void set_item(String newt){
		if(this.item!=null){
			switch(newt){
			case "gomme":
				this.item=new Gomme();
				break;
			case "supergomme":
				this.item=new SuperGomme();
				break;
			default:
				this.item=new Vide();
				break;
			}
		}
	}
	public String toString(){
		return "[Case:(:"+pos.get_posx()+","+pos.get_posy()+")|"
				+ "type:"+item.toString()+"]";
	}
}
