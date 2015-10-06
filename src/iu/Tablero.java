package iu;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.net.URL;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import entidades.Pieza;
import entidades.Posicion;
import negocio.CtrlJuego;


public class Tablero extends JPanel {

    private static final long serialVersionUID = 1L;
	
	protected static final int COMIENZO_TABLERO_X = 301;
	protected static final int COMIENZO_TABLERO_Y = 51;

	protected static final int ANCHO_CUADRO = 50;
	protected static final int ALTO_CUADRO = 50;

	protected static final int ANCHO_PIEZA = 48;
	protected static final int ALTO_PIEZA = 48;
	
	protected static final int COMIENZO_PIEZAS_X =  COMIENZO_TABLERO_X+ (int)(ANCHO_CUADRO/2.0 - ANCHO_PIEZA/2.0);
	protected static final int COMIENZO_PIEZAS_Y = COMIENZO_TABLERO_Y + (int)(ANCHO_CUADRO/2.0 - ALTO_PIEZA/2.0);
	
	protected static final int DRAG_TARGET_SQUARE_START_X = COMIENZO_TABLERO_X - (int)(ANCHO_PIEZA/2.0);
	protected static final int DRAG_TARGET_SQUARE_START_Y = COMIENZO_TABLERO_Y - (int)(ALTO_PIEZA/2.0);
    
	protected Pieza piezaOrigen;//pieza a ser marcada como seleccionada y usada para dibujar destinos posibles
	int x,y;//coordenadas en pixel de la pieza origen
	
	protected Image imgFondo;
	protected Ajedrez_juego ventanaJuego;
	CtrlJuego control;
	
	

	public Tablero() {
		this.setLayout(null);
		control = CtrlJuego.getInstancia();
		
		//imagen tablero
		URL urlImgFondo = getClass().getResource("/recursos/bo.png");
		this.imgFondo = new ImageIcon(urlImgFondo).getImage();
		MoverPiezasListener listener = new MoverPiezasListener(this);
	    this.addMouseListener(listener);
		
	}
		
	
       
	
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
	    
		g2.drawImage(this.imgFondo, 0, 0, null);
		
		
		for (Map.Entry<Posicion, Pieza> entry : control.getPiezas().entrySet()) {
    	    Posicion key = entry.getKey();
    	    Pieza value = entry.getValue();
    	    char col = key.getCol();
    	    int fila = key.getFila();
		
    	    g2.drawImage(getImagenPieza(value.getColor(), value.getLetra()), Tablero.deColumna_a_X(col), Tablero.deFila_a_Y(fila), null);
	    }
		
		
		//usar una linea mas gruesa para el cuadrado a dibujar
		g2.setStroke(new BasicStroke(3.0f));
		//dibuja un recuadro sobre el cuadro de la pieza seleccionada para mover, pero lo dibuja solo si es el turno del
		//jugador
		if(piezaOrigen!=null && piezaOrigen.getColor()==control.getGameState()){
			
	        g2.setColor(Color.GREEN);
	        //parametros: coord x,y, ancho y alto del rectangulo, y radio del arco de las esquinas
	        g2.drawRect(x+3, y+3, ANCHO_CUADRO-9, ANCHO_CUADRO-9);
	        
	        
	        //prueba: dibujar destinos validos posibles
	        for(char columna = 'a'; columna <='h'; columna++)
	        	for(int fila = 1; fila <=8; fila++)
	              if(control.esDestinoLibre(new Posicion(columna, fila)) || control.esCapturable(new Posicion(columna, fila)))
	            	
	            	  if(piezaOrigen.esMovValido(Tablero.deY_a_fila(y), (char)(Tablero.deX_a_columna(x)+97), 
	            		  fila, columna, control.esDestinoLibre(new Posicion(columna, fila))) && 
	            		  control.noHayPiezaEnElMedio(Tablero.deY_a_fila(y), (char)(Tablero.deX_a_columna(x)+97), 
	    	            		  fila, columna))
	            	     
	            		  g2.drawRect(Tablero.deColumna_a_X(columna)+3, Tablero.deFila_a_Y(fila)+3, ANCHO_CUADRO-9, ANCHO_CUADRO-9);
		              
		}
	
	}
	
	
	
	//carga la imagen para pieza segun su tipo y color
	private Image getImagenPieza(char color, char tipo) {

		String pathImagen = "";

		pathImagen += (color == 'B' ? "w" : "b");
		switch (tipo) {
			case 'A':
				pathImagen += "b";
				break;
			case 'R':
				pathImagen += "k";
				break;
			case 'C':
				pathImagen += "n";
				break;
			case 'P':
				pathImagen += "p";
				break;
			case 'D':
				pathImagen += "q";
				break;
			case 'T':
				pathImagen += "r";
				break;
		}
		pathImagen += ".png";

		URL urlImgPieza = getClass().getResource("/recursos/" + pathImagen);
		return new ImageIcon(urlImgPieza).getImage();
	}
	
	
	
	//transforma una columna logica en un valor en pixels de X
	public static int deColumna_a_X(char col){
		return COMIENZO_TABLERO_X + ALTO_CUADRO*((col-96)-1);
		       
	}
	
	
	//transforma una fila logica en un valor en pixels de Y
	public static int deFila_a_Y(int fila){
		return COMIENZO_TABLERO_Y + ALTO_CUADRO *(8-fila);
	}
	
	
	//transforma un valor en pixels de Y en una fila logica
	public static int deY_a_fila(int y){
		return 8 - (y - DRAG_TARGET_SQUARE_START_Y)/ANCHO_CUADRO;
	}
	
	
	//transforma un valor en pixels de X en una columna logica
	public static int deX_a_columna(int x){
		return ((x - DRAG_TARGET_SQUARE_START_X)/ANCHO_CUADRO);
	}
}

