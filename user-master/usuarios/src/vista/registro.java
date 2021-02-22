package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modelo.Hash;
import modelo.SQLUsuarios;
import modelo.Usuarios;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class registro extends JFrame {

	private JPanel contentPane;
	private JTextField txtUsuarios;
	private JTextField textEmail;
	private JPasswordField txtPassword;
	private JPasswordField txtConfirmarPassword;
	private JTextField textNombre;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					registro frame = new registro();
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
	public registro() {
		addWindowListener(new WindowAdapter() {
			
			//Es creado para poder reabrir la pestaña en el menu
			@Override
			public void windowClosing(WindowEvent e) {
				
				inicio.frmReg = null;
			}
		});
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 470, 515);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblUsuario.setBounds(67, 65, 82, 20);
		contentPane.add(lblUsuario);

		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPassword.setBounds(67, 114, 108, 20);
		contentPane.add(lblPassword);

		JLabel lblRepetirPassword = new JLabel("Repetir Password:");
		lblRepetirPassword.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblRepetirPassword.setBounds(67, 163, 178, 20);
		contentPane.add(lblRepetirPassword);

		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblEmail.setBounds(67, 214, 69, 20);
		contentPane.add(lblEmail);

		txtUsuarios = new JTextField();
		txtUsuarios.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtUsuarios.setBounds(255, 65, 146, 30);
		contentPane.add(txtUsuarios);
		txtUsuarios.setColumns(10);

		textEmail = new JTextField();
		textEmail.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textEmail.setBounds(255, 211, 146, 26);
		contentPane.add(textEmail);
		textEmail.setColumns(10);

		txtPassword = new JPasswordField();
		txtPassword.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtPassword.setBounds(255, 111, 146, 30);
		contentPane.add(txtPassword);

		txtConfirmarPassword = new JPasswordField();
		txtConfirmarPassword.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtConfirmarPassword.setBounds(255, 157, 146, 28);
		contentPane.add(txtConfirmarPassword);

		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNombre.setBounds(67, 263, 108, 20);
		contentPane.add(lblNombre);

		JButton btnRegistro = new JButton("Registro");
		btnRegistro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SQLUsuarios modSql = new SQLUsuarios();
				Usuarios mod = new Usuarios();

				/*
				 * Evaluacion de contraseña
				 */
				String pass = new String(txtPassword.getPassword());
				String passCon = new String(txtConfirmarPassword.getPassword());

				/*
				 * Evaluacion de campos vacios
				 */
				if (txtUsuarios.getText().equals("") || pass.equals("") || passCon.equals("")
						|| textNombre.getText().equals("") || textEmail.getText().equals("")) {

					JOptionPane.showMessageDialog(null, "Rellenar todos los campos");

				} else {

					 //Evaluacion de contraseña
					if (pass.equals(passCon)) {
						//Evaluacion de usuario
						if (modSql.existeUsuario(txtUsuarios.getText()) == 0) {
							//Evaluacion de Email
							if (modSql.esEmail(textEmail.getText())) {

								String nuevoPass = Hash.sha1(pass);

								mod.setUsuario(txtUsuarios.getText());
								mod.setPassword(nuevoPass);
								mod.setNombre(textNombre.getText());
								mod.setCorreo(textEmail.getText());
								mod.setId_tipo(1);

								if (modSql.registrar(mod)) {
									JOptionPane.showMessageDialog(null, "Registro guardado");
									limpiar();

								}else {
									JOptionPane.showMessageDialog(null, "Error al guardar");
								}
								
							}else {
								JOptionPane.showMessageDialog(null, "El correo electronico no es valido ");
							}

						}else {
							JOptionPane.showMessageDialog(null, "El usuario ya exite");
						}

					}else {
						JOptionPane.showMessageDialog(null, "Contraseña no son iguales");
					}
				}
			}

			private void limpiar() {
				txtUsuarios.setText("");
				txtPassword.setText("");
				txtConfirmarPassword.setText("");
				textNombre.setText("");
				textEmail.setText("");
			}

		});
		btnRegistro.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnRegistro.setBounds(175, 348, 115, 29);
		contentPane.add(btnRegistro);

		textNombre = new JTextField();
		textNombre.setBounds(255, 262, 146, 26);
		contentPane.add(textNombre);
		textNombre.setColumns(10);
	}
}
