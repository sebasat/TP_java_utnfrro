package iu;

import entidades.*;
import excepciones.AppExceptions;
import negocio.CtrlJuego;
import java.util.Map;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Color;
import java.awt.Font;

public class Ajedrez_juego extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private Partida partida;
    private JLabel labelMuestraTurno, lblsetDni1, lblsetDni2, lbl_muestra_ganador;
	private CtrlJuego ctrl;
	Tablero tablero;
	/**
	 * Create the dialog.
	 */
	public Ajedrez_juego(JFrame fr, boolean m) {
		super(fr,m);
		setResizable(false);
		setBounds(100, 100, 469, 541);
		this.ctrl = CtrlJuego.getInstancia();
        getContentPane().setLayout(null);
        tablero = new Tablero();
        tablero.ventanaJuego=this;
        tablero.setBounds(0, 0, tablero.imgFondo.getWidth(null), tablero.imgFondo.getHeight(null));
        getContentPane().add(tablero);
		setSize(tablero.imgFondo.getWidth(null), tablero.imgFondo.getHeight(null));
        
		
		JLabel lblJugador1 = new JLabel("Jugador 1:");
		lblJugador1.setFont(new Font("Dialog", Font.BOLD, 18));
		lblJugador1.setForeground(Color.WHITE);
		lblJugador1.setBounds(12, 53, 110, 22);
		tablero.add(lblJugador1);
		
		JLabel lblJugador2 = new JLabel("Jugador 2:");
		lblJugador2.setFont(new Font("Dialog", Font.BOLD, 18));
		lblJugador2.setBounds(12, 124, 110, 22);
		tablero.add(lblJugador2);
		
		lblsetDni1 = new JLabel("New label");
		lblsetDni1.setFont(new Font("Dialog", Font.BOLD, 18));
		lblsetDni1.setForeground(Color.WHITE);
		lblsetDni1.setBounds(134, 53, 138, 22);
		tablero.add(lblsetDni1);
		
		lblsetDni2 = new JLabel("New label");
		lblsetDni2.setFont(new Font("Dialog", Font.BOLD, 18));
		lblsetDni2.setBounds(134, 124, 138, 22);
		tablero.add(lblsetDni2);
		
		JLabel lblGanador = new JLabel("Ganador");
		lblGanador.setFont(new Font("Dialog", Font.BOLD, 18));
		lblGanador.setForeground(new Color(0, 204, 255));
		lblGanador.setBounds(12, 213, 95, 22);
		tablero.add(lblGanador);
		
		lbl_muestra_ganador = new JLabel("");
		lbl_muestra_ganador.setFont(new Font("Dialog", Font.BOLD, 18));
		lbl_muestra_ganador.setBounds(134, 213, 146, 22);
		tablero.add(lbl_muestra_ganador);
		
		JLabel lblTurno = new JLabel("Turno");
		lblTurno.setFont(new Font("Dialog", Font.BOLD, 18));
		lblTurno.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTurno.setBounds(12, 300, 83, 28);
		tablero.add(lblTurno);
		
		labelMuestraTurno = new JLabel("New label");
		labelMuestraTurno.setFont(new Font("Dialog", Font.BOLD, 18));
		labelMuestraTurno.setBounds(134, 303, 121, 22);
		tablero.add(labelMuestraTurno);
		
		
		//listener para guardar partida al cerrar ventana
		addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent evt) {
                guardarPartida();
                dispose();
            }
        });
	}
	
	
	
	//setea la partida enviada desde la ventana pcpal(nueva o cargada) y setea el controlador
	protected void setPartida(Partida p){
		this.partida = p;
		ctrl.setGameState(partida.getEstado().charAt(0));
		muestraTurno();
		lblsetDni1.setText(String.valueOf(partida.getJugador1().getDni()));
		lblsetDni2.setText(String.valueOf(partida.getJugador2().getDni()));
		checkPartidaFinalizada();
		
	}
	
	
	
	//setea el turno en el label
	public void muestraTurno(){
		switch(ctrl.getGameState() ){
		   case 'N': labelMuestraTurno.setText("Negras");labelMuestraTurno.setForeground(Color.BLACK);break;
		   case 'F': labelMuestraTurno.setText("Finalizada");labelMuestraTurno.setForeground(Color.GREEN);break;
		   case 'B': labelMuestraTurno.setText("Blancas");labelMuestraTurno.setForeground(Color.WHITE);break;
	    }
	}
	
	
	
	//obtiene el origen-destino del movimiento para ser ejecutado
	public void getMovimiento(String origen, String destino){
	    try {
			ctrl.manejarMovim(origen, destino);
		} catch (AppExceptions e) {
			
			//JOptionPane.showMessageDialog(getContentPane(), e.getMessage() ,"Aviso", JOptionPane.INFORMATION_MESSAGE);
		}
	    muestraTurno();
	    checkPartidaFinalizada();
	}
	
	
	
	
	//guarda la partida en curso, si se cargo una partida q estaba terminada, no la vuelve a guardar al cerrar la ventana 
	public void guardarPartida(){
		if(partida.getEstado().charAt(0)!='F'){
				partida.setEstadoJuego(String.valueOf(ctrl.getGameState()));
				try {
					ctrl.guardarPartida(partida);
				} catch (AppExceptions e) {
					JOptionPane.showMessageDialog(getContentPane(), e.getMessage() ,"Aviso", JOptionPane.INFORMATION_MESSAGE);
				}
		}
	}
	
	
	
	
	//si la partida esta finalizada se desactiva la ventana e imprime el ganador
	public void checkPartidaFinalizada(){
		
		if(ctrl.getGameState()=='F'){
			
			//si la partida esta terminada, se desactiva la ventana
			this.setEnabled(false);
			//imprimir nombre del ganador en el label
	    	for (Map.Entry<Posicion, Pieza> entry : ctrl.getPiezas().entrySet()) {
	    	    
	    	    Pieza value = entry.getValue();
	    	    if(value.getLetra() == 'R')
	    	    	if(value.getColor()=='B')
	    	    		{lbl_muestra_ganador.setText("Jugador 1");lbl_muestra_ganador.setForeground(Color.WHITE);
	    	    		
	    	    	}else lbl_muestra_ganador.setText("Jugador 2");
		    }
	    }
	
     }
}

