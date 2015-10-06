package iu;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Map;
import negocio.CtrlJuego;
import entidades.Pieza;
import entidades.Posicion;

public class MoverPiezasListener implements MouseListener{

	private Pieza piezaOrigen;//pieza seleccionada al hacer click sobre ella
    private int seleccionCuadro_X;
    private int seleccionCuadro_Y;
    CtrlJuego control;
    String origen, destino;
    Tablero miTablero;
	
    
    public MoverPiezasListener(Tablero tablero){
    	control = CtrlJuego.getInstancia();
    	miTablero = tablero;
	}
	

    //click para seleccionar origen(pieza origen == null) y otro click para seleccionar destino
	@Override
	public void mouseClicked(MouseEvent evt) {
		// TODO Auto-generated method stub
	  if(piezaOrigen==null){	
				int x = evt.getPoint().x;
		        int y = evt.getPoint().y;
		 
		        //le "pregunta" a cada pieza si ha sido seleccionada, si es asi, guarda la pieza seleccionada y su posicion
		        //en las vars origen, para ser usada en la ejecucion del movimiento
		        for (Map.Entry<Posicion, Pieza> entry : control.getPiezas().entrySet()) {
		    	    Posicion posn = entry.getKey();
		    	    Pieza pieza = entry.getValue();
		    	    char col = posn.getCol();
		    	    int fila = posn.getFila();
		 
		            if(mouseSobrePieza(posn,x,y)){
		               
		                this.seleccionCuadro_X = x - (Tablero.deColumna_a_X(col));
		                this.seleccionCuadro_Y = y - (Tablero.deFila_a_Y(fila));
		                this.piezaOrigen = pieza;
		                
		                miTablero.piezaOrigen=pieza;
		                miTablero.x=Tablero.deColumna_a_X(col);
		                miTablero.y=Tablero.deFila_a_Y(fila);
		                miTablero.repaint();
		                
		                origen = String.valueOf(col)+fila;
		                break;
		            }
		        }
	   }else{
		    int x = evt.getPoint().x-seleccionCuadro_X;
	        int y = evt.getPoint().y-seleccionCuadro_Y;
	        int fila = Tablero.deY_a_fila(y);
	        int colInt = Tablero.deX_a_columna(x);
	        char col = (char)(colInt+97);
	        destino = String.valueOf(col)+fila;
	        
	        
			miTablero.ventanaJuego.getMovimiento(origen, destino);
					
			        
			this.piezaOrigen = null;
			miTablero.piezaOrigen=null;
			miTablero.repaint();
	        
	   }
	}
	
	
	
	
	private boolean mouseSobrePieza(Posicion pos,int x,int y) {
        return Tablero.deColumna_a_X(pos.getCol()) <= x
            && Tablero.COMIENZO_TABLERO_X+ Tablero.ANCHO_CUADRO*(pos.getCol()-96)>= x
            && Tablero.deFila_a_Y(pos.getFila()) <= y
            && Tablero.COMIENZO_TABLERO_Y+ Tablero.ANCHO_CUADRO *(8-pos.getFila()+1) >= y;
    }


	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
	@Override
    public void mousePressed(MouseEvent evt) {
      
        
    }
 
 
    
    @Override
    public void mouseReleased(MouseEvent ev) {
    	
    }
	

}
