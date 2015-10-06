package iu;

import java.util.Map;
import java.util.Scanner;


import entidades.*;

import excepciones.AppExceptions;

import negocio.CtrlJuego;


//esta clase ya no sirve para nada
public class IU_consola_test {
	
	CtrlJuego ctrl = CtrlJuego.getInstancia();
	
	public static void main(String [] args){
		
		IU_consola_test programa = new IU_consola_test();
		programa.start();
	}

	
	
	
	public void start(){
		
		Scanner teclado = new Scanner(System.in);
		String ori,dest;
		//ctrl.iniciarPartida();
		int dni1, dni2;
		System.out.println("Ingresar DNI Jugador 1: ");
		dni1 = teclado.nextInt();
		System.out.println("Ingresar DNI Jugador 2: ");
		dni2 = teclado.nextInt();
		//ctrl.getPiezas();
		teclado.nextLine();//limpia buffer
		if(ctrl.buscarJugadorXDni(dni1)==null || ctrl.buscarJugadorXDni(dni2)==null)
			System.out.println("DNI no encontrado");
		
		else{buscarPartidasJugadores(dni1, dni2);
			 System.out.println("Ingresa nro de partida para continuarla o 0 para una nueva ");
		     int opc = teclado.nextInt();
		     teclado.nextLine();
		     if(opc == 0) ctrl.iniciarPartida();
		     else{
		    	     cargarPartida(opc);
		     }
		     if(ctrl.getGameState() == 'F') {
		    	 System.out.println("Partida finalizada");
		    	 System.out.println("Ganó el jugador nro: "+getGanador());
		     }
			 else{	  while(true){
								     printGameState();
								     //Scanner teclado = new Scanner(System.in);
							         System.out.println("Ingresar origen ej: c2");
							    
							         try{ori = teclado.nextLine();
							              if (ori.equalsIgnoreCase("exit")){
							            	 guardarPartida(dni1, dni2, opc);
							    	         teclado.close();
									         return;
								          }else{
									          System.out.println("Ingresar destino ej: c2");
							                  dest = teclado.nextLine();
							                  ctrl.manejarMovim(ori, dest);
							                  
							                  if(ctrl.getGameState() == 'F') {
							                	  guardarPartida(dni1, dni2, opc);
									    	      teclado.close();
											      return;
							                  }
							          
								          }
							   
							          }catch(IllegalArgumentException | StringIndexOutOfBoundsException ex){
							    	       System.out.println("Formato de entrada invalido");
							     
							          }catch(AppExceptions ex){
							    	       System.out.println(ex.getMessage());
							          }
						}
			 }
		}
	}
	
	
	public void actTablero(){
		Map<Posicion, Pieza> casillas = ctrl.getPiezas();
		System.out.println("Fichas blancas.........");
    	for (Map.Entry<Posicion, Pieza> entry : casillas.entrySet()) {
    	    Posicion key = entry.getKey();
    	    Pieza value = entry.getValue();
    	    if(value.getColor()=='B')
    	    	System.out.println(key+"  ->  "+value.getLetra());
    	    
    	}
    
        System.out.println("Fichas negras.........");
    	for (Map.Entry<Posicion, Pieza> entry : casillas.entrySet()) {
    	    Posicion key = entry.getKey();
    	    Pieza value = entry.getValue();
    	    if(value.getColor()=='N')
    	    	System.out.println(key+"  ->  "+value.getLetra());
    	    
    	}
	}
	
	
	
	private void printGameState() {
		Map<Posicion, Pieza> casillas = ctrl.getPiezas();
		System.out.println("  a  b  c  d  e  f  g  h  ");
		for (int row = 8; row >= 1; row--) {

			System.out.println(" +--+--+--+--+--+--+--+--+");
			String strRow = row + "|";
			for (char column = 'a'; column <= 'h'; column++) {
				Pieza piece = casillas.get(new Posicion(column,row));
				String pieceStr = getNameOfPiece(piece);
				strRow += pieceStr + "|";
				
			}
			System.out.println(strRow + row);
		}
		System.out.println(" +--+--+--+--+--+--+--+--+");
		System.out.println("  a  b  c  d  e  f  g  h  ");

		String gameStateStr = "unknown";
		switch (ctrl.getGameState()) {
			case 'N': gameStateStr="negras";break;
			case 'F': gameStateStr="Finalizada";break;
			case 'B': gameStateStr="blancas";break;
		}
		System.out.println("turno: " + gameStateStr);

	}
	
	
	
	private String getNameOfPiece(Pieza piece) {
		if (piece == null)
			return "  ";

		return piece.toString();
	}
	
	
	
	
	public void guardarPartida(int dni1, int dni2, int opc){
		Partida p = new Partida();
		Jugador j1 = new Jugador();j1.setDni(dni1);
		Jugador j2 = new Jugador();j2.setDni(dni2);
		p.setJugador1(j1);p.setJugador2(j2);
		
		p.setEstadoJuego(String.valueOf(ctrl.getGameState()));
		
		if(opc != 0) p.setNroPartida(opc);
		try {
			ctrl.guardarPartida(p);
		} catch (AppExceptions e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		
		
	}
	
	
	
	
	public void buscarPartidasJugadores(int dni1, int dni2){
		try {
			Jugador j1 = new Jugador();j1.setDni(dni1);
			Jugador j2 = new Jugador();j2.setDni(dni2);
			for(int i=0; i < (ctrl.buscarPartidasJugadores(j1, j2)).size(); i++)
				System.out.println("Nro partida: "+(ctrl.buscarPartidasJugadores(j1, j2)).get(i).getNroPart()+
						"  "+"Turno: "+(ctrl.buscarPartidasJugadores(j1, j2).get(i).getEstado())+
						(ctrl.buscarPartidasJugadores(j1, j2).get(i).getJugador1().getDni())+
						(ctrl.buscarPartidasJugadores(j1, j2).get(i).getJugador2().getDni()));
		} catch (AppExceptions e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	
	
	}



    public void cargarPartida(int opc){
    	ctrl.getPosicionesGuardadas(opc);
    	ctrl.setGameState((ctrl.getXnroPartida(opc).getEstado()).charAt(0));
    }
    
    
    
    public int getGanador(){
		Map<Posicion, Pieza> casillas = ctrl.getPiezas();
		int ganador = 0;
    	for (Map.Entry<Posicion, Pieza> entry : casillas.entrySet()) {
    	    
    	    Pieza value = entry.getValue();
    	    if(value.getLetra() == 'R')
    	    	if(value.getColor()=='B')
    	    	   ganador = 1;
    	    	else ganador = 2;
    	    
    	}
        return ganador;
    }





}
























