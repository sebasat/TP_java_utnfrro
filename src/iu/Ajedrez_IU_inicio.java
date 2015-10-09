package iu;

import java.awt.EventQueue;
import entidades.*;
import excepciones.AppExceptions;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTable;

import negocio.CtrlJuego;
import java.util.ArrayList;
import javax.swing.ListSelectionModel;

public class Ajedrez_IU_inicio {

	private JFrame ventanaPrincipal;
	private Ajedrez_juego ventanaJuego;
	private JTextField txtJug1;
	private JTextField txtJug2;
	private JTable tablaPartidas;
	private CtrlJuego ctrl = CtrlJuego.getInstancia();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ajedrez_IU_inicio window = new Ajedrez_IU_inicio();
					window.ventanaPrincipal.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Ajedrez_IU_inicio() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		ventanaPrincipal = new JFrame("Pantalla inicial");
		ventanaPrincipal.setResizable(false);
		ventanaPrincipal.setBounds(100, 100, 436, 422);
		ventanaPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventanaPrincipal.setLocationRelativeTo(null);
		
		JLabel lblDniJugador1 = new JLabel("DNI Jugador 1:");
		JLabel lblDniJugador2 = new JLabel("DNI Jugador 2:");
		
		
		//textfield dni 1
		txtJug1 = new JTextField();
		txtJug1.setColumns(10);
		soloNros(txtJug1);
		
		
		//textfield dni 2
		txtJug2 = new JTextField();
		txtJug2.setColumns(10);
		soloNros(txtJug2);
		
		
		JButton btnJugar = new JButton("Nueva");
		btnJugar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(dniLleno()){
				        setPartidaEnJuego(iniciarPartidaNueva());
				}
			}
		});
		
		JScrollPane scrollPane = new JScrollPane();
		
		
		
		//este boton busca partidas guardadas entre 2 jugadores
		JButton btnVerPartidasGuardadas = new JButton("ver partidas guardadas");
		btnVerPartidasGuardadas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(dniLleno()){
						iniciarTabla();
				}
				
			}
		});
		
		
		GroupLayout groupLayout = new GroupLayout(ventanaPrincipal.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(34)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblDniJugador1)
								.addComponent(lblDniJugador2))
							.addGap(37)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(txtJug2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(txtJug1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addGap(28)
									.addComponent(btnJugar, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE))))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(23)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(scrollPane, 0, 0, Short.MAX_VALUE)
								.addComponent(btnVerPartidasGuardadas))))
					.addContainerGap(197, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(24)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDniJugador1)
						.addComponent(txtJug1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnJugar))
					.addGap(35)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDniJugador2)
						.addComponent(txtJug2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(84)
					.addComponent(btnVerPartidasGuardadas)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(42, Short.MAX_VALUE))
		);
		
		
		//manipulacion de jtable, setearla no visible al inicio y doble click para cargar partida guardada
		tablaPartidas = new JTable();
		tablaPartidas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		//listener a la tabla para seleccionar partida con doble click
		tablaPartidas.addMouseListener(new MouseAdapter() {
	    	    public void mousePressed(MouseEvent Mouse_evt) {
	    	           JTable table =(JTable) Mouse_evt.getSource();
	    	           //Point point = Mouse_evt.getPoint();
	    	           //int row = table.rowAtPoint(point);
	    	           if (Mouse_evt.getClickCount() == 2) {
	    	        	       cargarPartida((Integer)table.getValueAt(table.getSelectedRow(), 0));
	    	           }
	    	    }
	    });
		
		
		//listener para seleccionar partida con ENTER
		tablaPartidas.addKeyListener( new KeyAdapter() {
			public void keyPressed(java.awt.event.KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                	   cargarPartida((Integer)tablaPartidas.getValueAt(tablaPartidas.getSelectedRow(), 0));;
                }
			                          }
		});
		
		tablaPartidas.setVisible(false);
		scrollPane.setViewportView(tablaPartidas);
		
		ventanaPrincipal.getContentPane().setLayout(groupLayout);
	}
	
	
	
	//busca partidas guardadas por 2 jugadores, mapeando datos desde los textfield dni
	public ArrayList<Partida> buscarPartidasJugadores(){
		
			try {
				Jugador j1 = new Jugador();j1.setDni(Integer.parseInt(txtJug1.getText()));
				Jugador j2 = new Jugador();j2.setDni(Integer.parseInt(txtJug2.getText()));
				return ctrl.buscarPartidasJugadores(j1, j2);
			
			} catch (AppExceptions e) {
				// TODO Auto-generated catch block
				e.getMessage();
				return null;
			} catch (RuntimeException re){
				JOptionPane.showMessageDialog(ventanaPrincipal.getContentPane(), re.getMessage(),"Aviso", JOptionPane.INFORMATION_MESSAGE);
			    return null;
			}
		
	}
	
	
	
	//devuelve el modelo de tabla con datos para ser presentados en tabla de partidas guardadas
	public ModeloTablaPartidas getModeloTabla(){
		
		return new ModeloTablaPartidas(buscarPartidasJugadores());
	}
	
	
	
	//valida q en el textfield dni solo se puedan ingresar numeros
	public void soloNros(JTextField txtf){
	    	txtf.addKeyListener(new KeyAdapter(){
	    		public void keyTyped(KeyEvent e){
	    			char c=e.getKeyChar();
	    			  if(!Character.isDigit(c)){
	    				  
	    				  e.consume();
	    			  }
	    		}
	    	});
	    	
	}
	
	
	//valida q haya dni en los textfield dni para poder realizar alguna accion
    protected boolean dniLleno(){
    	if(txtJug1.getText().length()==0 || txtJug2.getText().length()==0){
			JOptionPane.showMessageDialog(ventanaPrincipal.getContentPane(), "Ingresar DNI para iniciar","Aviso", JOptionPane.INFORMATION_MESSAGE);
			return false;
		} else return true;
    }
    
    
    
    //carga una partida obteniendo el nro de la misma desde el jtable
    public void cargarPartida(int opc){
    	ctrl.getPosicionesGuardadas(opc);
    	Partida partida = ctrl.getXnroPartida(opc);
    	setPartidaEnJuego(partida);
    	
    }
    
    
    
    //inicia nueva partida al presionar boton "Nueva" y la devuelve a la ventana del juego para setearla
    public Partida iniciarPartidaNueva(){
    	Partida p = null;
    	if(esJugadorRegistrado(txtJug1.getText()) && esJugadorRegistrado(txtJug2.getText())){    	
    	        p = new Partida();
		    	Jugador j1 = new Jugador();
		    	j1.setDni(Integer.parseInt(txtJug1.getText()));
				Jugador j2 = new Jugador();
				j2.setDni(Integer.parseInt(txtJug2.getText()));
		    	p.setJugador1(j1);
		    	p.setJugador2(j2);
		    	ctrl.iniciarPartida();
		    	p.setEstadoJuego(String.valueOf(ctrl.getGameState()));
		    	
    	}    	
    	return p;
    }
    
    
    
    
    public void iniciarTabla(){
    	tablaPartidas.setVisible(true);
        tablaPartidas.setModel(getModeloTabla());
        tablaPartidas.setToolTipText("Doble click o ENTER para continuar partida");
    }
    
    
    
    //valida q los jugadores esten registrados en el sistema, si no lo estan, no comienza la partida
    public boolean esJugadorRegistrado(String dni){
    	;
    	if(ctrl.buscarJugadorXDni(Integer.parseInt(dni))== null){
    		int rta = JOptionPane.showConfirmDialog(ventanaPrincipal, "Jugador no registrado, ¿desea registrarse?", "Aviso", JOptionPane.YES_NO_OPTION);
            if (rta == JOptionPane.YES_OPTION) {
                  registrarJugador(dni);
            }
            else {
               return false;
            }
    	}
    	return true;
    		
    	
    }
    
    
    
    
    //abre un jdialog para ingresar datos del jugador
    public void registrarJugador(String dni){
    	
    	RegistrarJugador ventana_registro = new RegistrarJugador(ventanaPrincipal,true);
    	ventana_registro.setDniEnPantalla(dni);
    	ventana_registro.setLocationRelativeTo(ventanaPrincipal);
    	ventana_registro.setVisible(true);
    }
    
    
    
    
    //setea una partida(nueva o cargada) en la ventana ajedrez_juego y agrega listener para q cuando se cierre el jdialog
    //se actualice la tabla
    public void setPartidaEnJuego(Partida p){
    	if(p != null){
		    	ventanaJuego = new Ajedrez_juego(ventanaPrincipal,true);
		    	ventanaJuego.setPartida(p);
		    	ventanaJuego.setLocationRelativeTo(ventanaPrincipal);
		    	ventanaJuego.addWindowListener(new WindowAdapter() {
                   
                    public void windowClosing(WindowEvent evt) {
                        iniciarTabla();
                    }
                });
		    	ventanaJuego.setVisible(true);
    	}
    }
    
}
