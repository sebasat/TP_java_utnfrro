package entidades;



public abstract class Pieza {
       
       protected char color;
       
       protected boolean esCapturada = false;
       
       public Pieza(char color){
    	   this.color = color;
       }
       
       
       public char getColor(){
    	   return color;
       }
       
       //declarar throws para usar en las subclases
       public abstract boolean esMovValido(int filaOrig, char colOrig, int filaDest,char colDest, boolean destinoLibre);
       public abstract char getLetra();
       public abstract String toString();
       
}
