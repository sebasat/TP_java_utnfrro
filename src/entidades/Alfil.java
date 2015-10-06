package entidades;

public class Alfil extends Pieza {
    
	public static final char Letra = 'A';
	
	public Alfil(char color){
		super(color);
		
	}
	
	
	
	public char getLetra(){
		return Letra;
	}
	
	
	
	@Override
	public boolean esMovValido(int filaOrig, char colOrig, int filaDest,char colDest, boolean destinoLibre) {
		
          if(Math.abs(filaOrig-filaDest)==Math.abs(colOrig-colDest))
        	  return true;
          else return false;
	}



	@Override
	public String toString() {
		
		return Letra+String.valueOf(color);
	}
	
	

}
