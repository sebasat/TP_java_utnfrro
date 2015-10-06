package entidades;

import java.util.HashMap;
import java.util.Map;

public class Partida {
	
	private int nroPartida;//traer desde BD
	private Map<Posicion, Pieza> posicionesOcup = new HashMap<Posicion, Pieza>();
	private Jugador jugador1,jugador2;
    private String estadoJuego;
    
    
    public Partida(){
    	
    	
    }
    
    
    
    public void setNroPartida(int nro){
    	nroPartida = nro;
    }
    
    
    public Map<Posicion, Pieza> getPosicionesOcup() {
		return posicionesOcup;
	}



	public void setPosicionesOcup(Map<Posicion, Pieza> posicionesOcup) {
		this.posicionesOcup = posicionesOcup;
	}



	public void setJugador1(Jugador jugador1) {
		this.jugador1 = jugador1;
	}



	public void setJugador2(Jugador jugador2) {
		this.jugador2 = jugador2;
	}



	public void setEstadoJuego(String estadoJuego) {
		this.estadoJuego = estadoJuego;
	}



	public int getNroPart(){
    	return nroPartida;
    }
    
    
    public Jugador getJugador1(){
    	return jugador1;
    }
    
    
    public Jugador getJugador2(){
    	return jugador2;
    }
    
    public String getEstado(){
    	return estadoJuego;
    }
    
    
    
    
    
    
    
}
