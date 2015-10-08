package iu;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import negocio.CtrlJuego;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import entidades.Jugador;
import excepciones.AppExceptions;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RegistrarJugador extends JDialog {

	
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldDNI;
	private JTextField textFieldNombre;
	private JTextField textFieldApe;
	private CtrlJuego ctrl;
	
	public RegistrarJugador(JFrame fr, boolean m) {
		super(fr,m);
		setResizable(false);
		setBounds(100, 100, 285, 266);
		this.ctrl = CtrlJuego.getInstancia();
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		JLabel lblDni = new JLabel("DNI:");
		
		JLabel lblNombre = new JLabel("Nombre:");
		
		JLabel lblApellido = new JLabel("Apellido:");
		
		textFieldDNI = new JTextField();
		textFieldDNI.setEditable(false);
		textFieldDNI.setColumns(10);
		
		textFieldNombre = new JTextField();
		textFieldNombre.setColumns(10);
		
		textFieldApe = new JTextField();
		textFieldApe.setColumns(10);
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblDni)
						.addComponent(lblNombre)
						.addComponent(lblApellido))
					.addGap(21)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING, false)
						.addComponent(textFieldApe, GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
						.addComponent(textFieldNombre)
						.addComponent(textFieldDNI))
					.addContainerGap(41, Short.MAX_VALUE))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(24)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblDni)
						.addComponent(textFieldDNI, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(23)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNombre)
						.addComponent(textFieldNombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(30)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblApellido)
						.addComponent(textFieldApe, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(60, Short.MAX_VALUE))
		);
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						registrar();
						dispose();
					}
				});
				okButton.setHorizontalAlignment(SwingConstants.LEFT);
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						textFieldApe.setText("");
					    textFieldNombre.setText("");
					    registrar();
					    dispose();
						
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	
	public void setDniEnPantalla(String dni){
		textFieldDNI.setText(dni);
	}
	
	
	public void registrar(){
		Jugador j = new Jugador();
		j.setDni(Integer.parseInt(textFieldDNI.getText()));
		j.setApellido(textFieldApe.getText());
		j.setNombre(textFieldNombre.getText());
		try {
			ctrl.registrarJugador(j);
		} catch (AppExceptions e) {
			JOptionPane.showMessageDialog(getContentPane(), e.getMessage(),"Aviso", JOptionPane.INFORMATION_MESSAGE);
		}
		
	}
}
