package entidades;

public class Torre extends Pieza {

    public static final char Letra = 'T';
	
	public Torre(char color){
		super(color);
		
	}
	
	
	public char getLetra(){
		return Letra;
	}
	
	
	
	@Override
	public boolean esMovValido(int filaOrig, char colOrig, int filaDest,char colDest, boolean destinoLibre) {
		
		if(filaOrig==filaDest || colOrig==colDest)
			return true;
		
		else return false;// o throw
	}
	
	
	
	public String toString() {
		// TODO Auto-generated method stub
		return Letra+String.valueOf(color);
	}

}
