package entidades;

public class Posicion {
        private char col;
        private int fila;
        
        public Posicion(char columna, int fila){
        	col = columna;
        	this.fila = fila;
        }
        
        
        
        public String toString(){
        	return (col+String.valueOf(fila));
        	
        }
        
        
        
        public char getCol() {
			return col;
		}



		public int getFila() {
			return fila;
		}
        
        
        
        public int hashCode(){
    		int result = 17;
              result = 37*result + (int) col;
              result = 37*result + (int) fila;
              return result;	
    	}
    	
    	
    	
        public boolean equals (Object pos){
    		return (pos instanceof Posicion 
    				&& this.getFila()==((Posicion)pos).getFila() 
    				&& this.getCol()==((Posicion)pos).getCol());
    	}



		





}
