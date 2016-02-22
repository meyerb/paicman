package code_benjamin;

public class Position {
	private int posx;
	private int posy;
	//posx et posy correspond � la position dans le tableau
	//(0,0) en haut � gauche
	Position(){
		this.posx=0;
		this.posy=0;
	}
	Position(int x,int y){
		this.posx=x;
		this.posy=y;
	}
	Position(Position bis){
		this.posx=bis.get_posx();
		this.posy=bis.get_posy();
	}
    //conversion position pixel en case
    public int conversion(int pixel){
        return pixel/22;
    }
	public int get_posx(){
		return this.posx;
	}
	public int get_posy(){
		return this.posy;
	}
	public void set_posx(int x){
		this.posx=x;
	}
	public void set_posy(int y){
		this.posy=y;
	}
	public void set_pos(int x,int y){
		this.posx=x;
		this.posy=y;
	}
	public void set_pos(Position pbis){
		set_pos(pbis.get_posx(),pbis.posy);
	}
	public boolean is_Equal(int _posx, int _posy){
		return (this.posx==_posx&&this.posy==_posy);
	}
	public boolean is_Equal(Position pbis){
		return (this.posx==pbis.get_posx()&&this.posy==pbis.get_posy());
	}
	//retourne diff�rence de posx
	public int diffx(Position p){
		return diffx(p.get_posx());
	}
	public int diffx(int _posx){
		return this.posx-_posx;
	}
	//retourne diff�rence de posy
	public int diffy(Position p){
		return diffy(p.get_posy());
	}
	public int diffy(int _posy){
		return this.posy-_posy;
	}
	//retourne la direction g�n�rale d'une autre position par rapport � celui-ci
	//('n'|'o'|'e'|'s')
	public char get_dir(Position p){
		return get_dir(p.get_posx(),p.get_posy());
	}
	public char get_dir(int _posx,int _posy){
		int diffx=this.posx-_posx;
		int diffy=this.posy-_posy;
		
		if(diffx==0&&diffy==0)
			return 'n';
		else if(diffx>0){//vers l'est 
			if(diffy>0){//vers le sud
				if(diffy>diffx)
					return 's';
				else
					return 'e';
			}
			else{//vers le nord
				if(-(diffy)>diffx)
					return 'n';
				else
					return 'e';
			}
		}
		else{//vers l'ouest
			if(diffy>0){//vers le sud
				if(diffy>-(diffx))
					return 's';
				else
					return 'o';
			}
			else{//vers le nord
				if(-(diffy)>-(diffx))
					return 'n';
				else
					return 'o';
			}
		}
	}
	public String toString(){
		return "[X:"+this.posx+"|Y:"+this.posy+"]";
	}
}
