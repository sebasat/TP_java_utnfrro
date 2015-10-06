package entidades;

public class Peon extends Pieza {
	
	public static final char Letra = 'P';
	
	public Peon(char color){
		super(color);
		
	}
	

	public char getLetra(){
		return Letra;
	}
	
	
	//el peon debe hacer 2 validaciones, puede moverse solo hacia un espacio libre verticalmente, y comer un pieza diagonalmente
	public boolean esMovValido(int filaOrig, char colOrig, int filaDest,char colDest, boolean destinoLibre) {//ver throws
		  
		   if(destinoLibre){
			     if(colOrig == colDest)
			    	    return validarFila(filaOrig, filaDest);
			     else return false;
		   
		   }else if(colOrig-1 == colDest || colOrig+1 == colDest)
			            return validarFila(filaOrig, filaDest);
		         else return false;
	}
	
	
	
	public boolean validarFila(int filaOrig, int filaDest) {
		
		    if(color == 'B'){
		    	
		    	  if(filaOrig+1 == filaDest)
		    	            return true;
		    	  else return false;
		    
		    }else if(filaOrig-1 == filaDest)
	                        return true;
	              else return false; 

	}


	public String toString() {
		// TODO Auto-generated method stub
		return Letra+String.valueOf(color);
	}


}
