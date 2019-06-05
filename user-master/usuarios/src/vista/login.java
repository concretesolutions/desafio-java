package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modelo.Hash;
import modelo.SQLUsuarios;
import modelo.Usuarios;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class login extends JFrame {

	private JPanel contentPane;
	private JTextField txtUsuarios;
	private JPasswordField txtPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					login frame = new login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public login() {
		addWindowListener(new WindowAdapter() {
			//Es creado para poder reabrir la pestaña en el menu
			@Override
			public void windowClosing(WindowEvent arg0) {
				
				inicio.frmLog = null;
				
			}
		});
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 576, 490);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setBounds(91, 107, 69, 20);
		contentPane.add(lblUsuario);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(91, 195, 69, 20);
		contentPane.add(lblPassword);
		
		txtUsuarios = new JTextField();
		txtUsuarios.setBounds(204, 104, 146, 26);
		contentPane.add(txtUsuarios);
		txtUsuarios.setColumns(10);
		
		txtPassword = new JPasswordField();
		txtPassword.setBounds(204, 192, 146, 26);
		contentPane.add(txtPassword);
		
		JButton btnIniciarSesion = new JButton("Iniciar Sesion");
		btnIniciarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				SQLUsuarios modSql = new SQLUsuarios();
				Usuarios mod = new Usuarios();
				
				Date date = new Date();
				DateFormat fechaHora = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				
				String pass = new String (txtPassword.getPassword());
				
				if(!txtUsuarios.getText().equals("") && !pass.equals("")) {
					
					String nuevoPass = Hash.sha1(pass);
					
					mod.setUsuario(txtUsuarios.getText());
					mod.setPassword(nuevoPass);
					mod.setLas_session(fechaHora.format(date));
					
					if(modSql.login(mod)) {
						
						inicio.frmLog = null;
						dispose();
						
						home fmrHome = new home();
						fmrHome.setVisible(true);
						
					}else {
						JOptionPane.showMessageDialog(null, "Datos incorrectos");

					}
				} else {
					JOptionPane.showMessageDialog(null, "Rellenar todos los campos");

				}
			}
		});
		btnIniciarSesion.setBounds(132, 264, 181, 45);
		contentPane.add(btnIniciarSesion);
	}

}
