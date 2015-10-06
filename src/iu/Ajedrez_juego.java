package iu;

import entidades.*;
import excepciones.AppExceptions;
import negocio.CtrlJuego;
import java.text.ParseException;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.text.MaskFormatter;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Color;

public class Ajedrez_juego extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private Partida partida;
    private JLabel labelMuestraTurno, lblsetDni1, lblsetDni2, lbl_muestra_ganador;
	private MaskFormatter formato;
	private CtrlJuego ctrl;
	Tablero tablero;
	private  JFormattedTextField txtFldOrigen;
	private JFormattedTextField txtFldDestino;
	private JButton btnMover;
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
        
		//inicio-- formato de entrada para los campos de texto origen y destino(de a a b y de 1 a 8)
		try{
			formato = new MaskFormatter("L#");
		}catch(ParseException pe){
			
		}
		formato.setValidCharacters("abcdefgh12345678");
		txtFldOrigen = new JFormattedTextField(formato);
		txtFldOrigen.setFocusLostBehavior(JFormattedTextField.COMMIT);
		txtFldOrigen.setToolTipText("Ingresa ColumnaFila, ej: c2");
		txtFldOrigen.setBounds(102, 278, 28, 19);
		tablero.add(txtFldOrigen);
		txtFldOrigen.setColumns(10);
		
		txtFldDestino = new JFormattedTextField(formato);
		txtFldDestino.setFocusLostBehavior(JFormattedTextField.COMMIT);
		txtFldDestino.setToolTipText("Ingresa ColumnaFila, ej: c2");
		txtFldDestino.setBounds(102, 317, 28, 19);
		tablero.add(txtFldDestino);
		txtFldDestino.setColumns(10);
		//fin-- formato de entrada para los campos de texto
		
		
		JLabel lblJugador1 = new JLabel("Jugador 1:");
		lblJugador1.setForeground(Color.WHITE);
		lblJugador1.setBounds(12, 53, 83, 15);
		tablero.add(lblJugador1);
		
		JLabel lblJugador2 = new JLabel("Jugador 2:");
		lblJugador2.setBounds(12, 84, 83, 15);
		tablero.add(lblJugador2);
		
		lblsetDni1 = new JLabel("New label");
		lblsetDni1.setForeground(Color.WHITE);
		lblsetDni1.setBounds(102, 53, 105, 15);
		tablero.add(lblsetDni1);
		
		lblsetDni2 = new JLabel("New label");
		lblsetDni2.setBounds(102, 84, 105, 15);
		tablero.add(lblsetDni2);
		
		JLabel lblGanador = new JLabel("Ganador:");
		lblGanador.setForeground(Color.BLUE);
		lblGanador.setBounds(12, 147, 70, 15);
		tablero.add(lblGanador);
		
		lbl_muestra_ganador = new JLabel("");
		lbl_muestra_ganador.setBounds(102, 147, 105, 15);
		tablero.add(lbl_muestra_ganador);
		
		JLabel lblTurno = new JLabel("Turno:");
		lblTurno.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTurno.setBounds(12, 212, 70, 15);
		tablero.add(lblTurno);
		
		labelMuestraTurno = new JLabel("New label");
		labelMuestraTurno.setBounds(102, 212, 105, 15);
		tablero.add(labelMuestraTurno);
		
		JLabel lblOrigen = new JLabel("Origen:");
		lblOrigen.setHorizontalAlignment(SwingConstants.RIGHT);
		lblOrigen.setBounds(25, 280, 70, 15);
		tablero.add(lblOrigen);
		
		JLabel lblDestino = new JLabel("Destino:");
		lblDestino.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDestino.setBounds(25, 319, 70, 15);
		tablero.add(lblDestino);
		
		
		btnMover = new JButton("Mover");
		btnMover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(coordenadasLleno()) {
					//getMovimiento();
					tablero.repaint();
				}
			}
		});
		btnMover.setBounds(151, 298, 83, 25);
		tablero.add(btnMover);
		
		
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
	
	
	
	//evita q se ingresen espacios en blanco en las cajas origen-destino
	protected boolean coordenadasLleno(){
    	if(txtFldOrigen.getText().trim().length()<2 || txtFldDestino.getText().trim().length()<2){
			JOptionPane.showMessageDialog(getContentPane(), "Ingresar coordenadas","Aviso", JOptionPane.INFORMATION_MESSAGE);
			return false;
		} else return true;
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
	
	
	
	//si la partida esta finalizada se desactiva el boton mover e imprime el ganador
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

