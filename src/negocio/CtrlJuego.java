package negocio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import datos.DatosJuego;

import entidades.*;
import excepciones.AppExceptions;

public class CtrlJuego {
	private DatosJuego datosJuego;
	private char gameState;
	public static final char GAME_STATE_WHITE = 'B';
    public static final char GAME_STATE_BLACK = 'N';
    public static final char GAME_STATE_END = 'F';
    Map<Posicion,Pieza> posiciones=new HashMap<Posicion,Pieza>();
    
    
    //Prueba singleton controlador
    //public CtrlJuego(){
    	//datosJuego = new DatosJuego();
   // }
    private CtrlJuego(){
    	datosJuego = new DatosJuego();
	}
	
	private static CtrlJuego instancia;
	
	public static CtrlJuego getInstancia(){
		if (instancia==null){
			instancia = new CtrlJuego();
		}
		return instancia;
	}
	
   
    
    //fin prueba singleton controlador
    
    
    //rellena hashmap con las posiciones iniciales de las piezas-----Partida nueva
    public void iniciarPartida(){
    	 gameState = GAME_STATE_WHITE;
    	 posiciones = new HashMap<Posicion,Pieza>();
		//peones
		 int filaB=2, filaN=7;
		 for(char col='a'; col <='h'; col++){
				posiciones.put(new Posicion(col,filaB), new Peon('B'));//peones blancos
			    posiciones.put(new Posicion(col,filaN), new Peon('N'));//peones negros
		 }
		 
		
		//torres
		 filaB=1; filaN=8;//estos valores se comparten para las demas piezas
		 for(char col='a'; col <='h'; col+=7){
			    posiciones.put(new Posicion(col,filaB), new Torre('B'));//torres blancos
			    posiciones.put(new Posicion(col,filaN), new Torre('N'));//torres negros
		 }
		 
		
		//caballos
		 for(char col='b'; col <='g'; col+=5){
			    posiciones.put(new Posicion(col,filaB), new Caballo('B'));//caballos blancos
			    posiciones.put(new Posicion(col,filaN), new Caballo('N'));//caballos negros
		 }
		 
		
		//alfiles
		 for(char col='c'; col <='f'; col+=3){
			    posiciones.put(new Posicion(col,filaB), new Alfil('B'));//alfiles blancos
			    posiciones.put(new Posicion(col,filaN), new Alfil('N'));//alfiles negros
		 }
		 
		
		//reinas
		 char col='d';
		        posiciones.put(new Posicion(col,filaB), new Reina('B'));//reina blanca
		        posiciones.put(new Posicion(col,filaN), new Reina('N'));//reina negra
		 
		
		//reyes
		 col='e';
		        posiciones.put(new Posicion(col,filaB), new Rey('B'));//rey blanco
		        posiciones.put(new Posicion(col,filaN), new Rey('N'));//rey negro
	}
    
    
    
    //devuelve que color tiene el turno (blanco o negro)
    public char getGameState() {
        return this.gameState;
    }
    
    
    
    public void setGameState(char state) {
        gameState = state;
    }
    
    
    
    //cambia el turno (de blanco a negro y viceversa)
    public void changeGameState() {
        switch (this.gameState) {
            case GAME_STATE_BLACK:
                this.gameState = GAME_STATE_WHITE;
                break;
            case GAME_STATE_WHITE:
                this.gameState = GAME_STATE_BLACK;
                break;
            case GAME_STATE_END:
                
                break;
            default:
                throw new IllegalStateException("Estado desconocido:" + this.gameState);
        }
    }
    
    
    
    
    //valida q la posicion origen tenga pieza
    public boolean hayPiezaOrigen(Posicion p) throws AppExceptions{
    	if(posiciones.get(p) != null) return true;
    	 else throw new AppExceptions("No hay pieza en origen ingresado"); 
    }
    
    
    
    //valida q sea el turno del jugador q hizo el movim
    public boolean esSuTurno(Posicion p) throws AppExceptions{
    	if(posiciones.get(p).getColor()==gameState) return true;
    	 else throw new AppExceptions("No es tu turno");
    }
    	 
    
    
    //valida q la posicion destino contenga pieza del contrario
    public boolean esCapturable(Posicion p){//throws
    	if(!(posiciones.get(p).getColor()==gameState)) return true;
    	 else return false;
    		 //throw new
    }
    
    
    
    //comprueba si la posicion destino esta libre u ocupada
    public boolean esDestinoLibre(Posicion p){//throws
    	if(posiciones.get(p)==null) return true;
    	else return false;
    	  
    }
    
    
    
    
    
    //hace el movimento comprobando si la posicion destino contiene al rey contrario 
    public void moverPieza(Posicion pOr, Posicion pDes, boolean esjaque) throws AppExceptions{
    	
    	posiciones.put(pDes, posiciones.get(pOr));
    	posiciones.remove(pOr);
    	changeGameState();
    	if(esjaque){
    		gameState = GAME_STATE_END;
    		throw new AppExceptions("Jaque Mate!!! Ganaste");
    	}
  
    }


    
    //recibe un par ori-dest(Strings) y determina si es posible hacer el movimiento o no
    public void manejarMovim (String ori, String dest) throws AppExceptions{
    	//ori y dest estan formados por pares de String char-int
    	boolean esValido = false;
    	boolean esJaque = false;
    	char colOrig = ori.charAt(0);
    	int filaOrig = Integer.parseInt(String.valueOf(ori.charAt(1)));
    	
    	char colDest = dest.charAt(0);
    	int filaDest = Integer.parseInt(String.valueOf(dest.charAt(1)));
    	
    	Posicion pOrigen = new Posicion(colOrig,filaOrig );
    	Posicion pDestino = new Posicion(colDest, filaDest);
    	
    	
    	if(hayPiezaOrigen(pOrigen) && esSuTurno(pOrigen)){	
    		if(esDestinoLibre(pDestino) || esCapturable(pDestino)){ //el envio del param boolean esDestinoLibre se hace por el peon, ya q tiene mov distintos para mover y para comer
    			 esValido = posiciones.get(pOrigen).esMovValido(filaOrig,colOrig,filaDest,colDest, esDestinoLibre(pDestino)) && noHayPiezaEnElMedio(filaOrig,colOrig,filaDest,colDest); 
    			 esJaque = esJaque(pDestino, esDestinoLibre(pDestino));
    		}
    	}
    	//si el movimiento es valido para la pieza, hacerlo
    	if(esValido)
    	    moverPieza(pOrigen, pDestino, esJaque);
    	else throw new AppExceptions("Movimiento no valido");    
    	
    }



    //devuelve las posiciones ocupadas
    public Map<Posicion, Pieza> getPiezas(){
    	return posiciones;
    }
    
    
    //comprueba si la pieza a ser comida es el rey contrario
    public boolean esJaque(Posicion pDest, boolean esDestinoLibre){
    	if(!esDestinoLibre && posiciones.get(pDest).getLetra()=='R')
    		  return true;
    	else return false;
    		    	
    }
    
    
  //valida q no haya pieza entre el origen y el destino
    public boolean noHayPiezaEnElMedio(int fo,char co ,int fd,char cd){
    	//validacion de la torre/reina
    	if(fo == fd || co==cd){
    		//torre hacia arriba 
    		if(fd-fo>1){
    			int foConInc=fo+1;
    			while(foConInc<fd)
    				if(posiciones.get(new Posicion(co,foConInc))==null)
    					foConInc+=1;
    				else {return false;}
    			return true;
    		}
    		
    		//torre hacia abajo
    		if(fd-fo<-1){
    			int foConInc=fo-1;
    			while(foConInc>fd)
    				if(posiciones.get(new Posicion(co,foConInc))==null)
    					foConInc-=1;
    				else {return false;}
    			return true;
    		}
    		
    		//torre hacia derecha
    		if(cd-co>1){
    			char coConInc=++co;
    			while(coConInc<cd)
    				if(posiciones.get(new Posicion(coConInc, fo))==null)
    					coConInc+=1;
    				else {return false;}
    			return true;
    		}
    		
    		
    		//torre hacia izq
    		if(cd-co<-1){
    			char coConInc=--co;
    			while(coConInc>cd)
    				if(posiciones.get(new Posicion(coConInc, fo))==null)
    					coConInc-=1;
    				else {return false;}
    			return true;
    		}
    	}
    	
    	
    	//validacion del alfil/reina
    	if(Math.abs(fo-fd)==Math.abs(co-cd)){
    		//alfil arriba derecha
    		if(fd-fo>1 && cd-co>1){
    			int foConInc=fo+1; char coConInc=++co;
    			while(foConInc<fd && coConInc<cd)
    				if(posiciones.get(new Posicion(coConInc,foConInc))==null){
    					foConInc+=1;coConInc+=1;
    				
    				}else {return false;}
    			return true;
    		}
    		
    		//alfil arriba izquierda
    		if(fd-fo>1 && cd-co<-1){
    			int foConInc=fo+1; char coConInc=--co;
    			while(foConInc<fd && coConInc>cd)
    				if(posiciones.get(new Posicion(coConInc,foConInc))==null){
    					foConInc+=1;coConInc-=1;
    				
    				}else {return false;}
    			return true;
    		}
    		
    		//alfil abajo izquierda
    		if(fd-fo<-1 && cd-co<-1){
    			int foConInc=fo-1; char coConInc=--co;
    			while(foConInc>fd && coConInc>cd)
    				if(posiciones.get(new Posicion(coConInc,foConInc))==null){
    					foConInc-=1;coConInc-=1;
    				
    				}else {return false;}
    			return true;
    		}
    		
    		
    		//alfil abajo derecha
    		if(fd-fo<-1 && cd-co>1){
    			int foConInc=fo-1; char coConInc=++co;
    			while(foConInc>fd && coConInc<cd)
    				if(posiciones.get(new Posicion(coConInc,foConInc))==null){
    					foConInc-=1;coConInc+=1;
    				
    				}else {return false;}
    			return true;
    		}
    		
    		
    	}
    	
    		
    	return true;	
    		    	
    }
    
    
    
    //operaciones en base de datos desde aca!!!!!!!!!!!!!!!!!!
    
    //comprueba si la partida a guardar existe, si no existe (if=null), se invoca a primero a guardarPartida (de la capa de datos)
    //y luego al metodo q guarda las posiciones con las piezas
    public void guardarPartida(Partida p) throws AppExceptions{
    	if(getXnroPartida(p.getNroPart()) == null){
    		 datosJuego.guardarPartida(p);
    	
    	}
    	guardarPartidaPosicion(p);
    	    	 
    	    	
    }
    
    
    //guarda las posiciones y piezas de una partida
    //primero hay q borrar las posiciones ocupadas correspondientes a la partida cargada previamente(en caso de partida re-guardada)
    public void guardarPartidaPosicion(Partida p){
    	
    	
    	datosJuego.delete(p);
    	for (Map.Entry<Posicion, Pieza> entry : posiciones.entrySet()) {
    	    String posic = entry.getKey().toString();
    	    String pieza = entry.getValue().toString();
    	    
    	
    		 datosJuego.guardarPartidaPosicion(p.getNroPart(), posic, pieza);
 	    	
 	    }
    	
    	datosJuego.actualizarEstadoPartida(p);
    }



    //busca partidas iniciadas por 2 jugadores
    public ArrayList<Partida> buscarPartidasJugadores(Jugador j1, Jugador j2) throws AppExceptions{
    	return datosJuego.getPartidasJugadores(j1, j2);
    }
    
    
    
    //obtiene de la BD las posiciones de una partida y las asigna a la var posiciones para enviarla a la capa de presentacion
    //este metodo deberia llamarse solo al cargar una partida guardada
    public void getPosicionesGuardadas(int nro){
    	posiciones = datosJuego.getPosicionesPartida(nro);
    	
    }
    
    
    
    public Jugador buscarJugadorXDni(int dni){
    	return datosJuego.buscarJugador(dni);
    }
    
    
    
    public Partida getXnroPartida(int nro){
    	return datosJuego.getXnroPart(nro);
    }


}


















