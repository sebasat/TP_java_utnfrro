package entidades;

public class Rey extends Pieza {

	
    public static final char Letra = 'R';
	
	public Rey(char color){
		super(color);
		
	}
	
	
	public char getLetra(){
		return Letra;
	}
	
	
	
	@Override
	public boolean esMovValido(int filaOrig, char colOrig, int filaDest,char colDest, boolean destinoLibre) {
		
		if(Math.abs(filaOrig-filaDest)<= 1 && Math.abs(colOrig-colDest)<= 1)
			return true;
		
		else return false;// o throw
	}
	
	
	
	public String toString() {
		// TODO Auto-generated method stub
		return Letra+String.valueOf(color);
	}

}
