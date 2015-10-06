package entidades;


public class Caballo extends Pieza {

	public static final char Letra = 'C';
	
	public Caballo(char color){
		super(color);
		
	}
	
	
	
	public char getLetra(){
		return Letra;
	}
	
	
	
	@Override
	public boolean esMovValido(int filaOrig, char colOrig, int filaDest,char colDest, boolean destinoLibre){
		
		if(Math.abs(filaDest-filaOrig)==1 && Math.abs(colDest-colOrig)==2)
			return true;
		
		else if (Math.abs(filaDest-filaOrig)==2 && Math.abs(colDest-colOrig)==1)
			return true;
		
		else return false;//o throw 
	}



	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return Letra+String.valueOf(color);
	}

}
