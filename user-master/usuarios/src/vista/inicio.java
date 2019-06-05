package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class inicio extends JFrame {
	
	public static login frmLog;
	public static registro frmReg;
	
	

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					inicio frame = new inicio();
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
	public inicio() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 673, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnRegistro = new JButton("Registro");
		btnRegistro.setBounds(78, 198, 115, 29);
		btnRegistro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(frmReg == null) {
					frmReg = new registro();
					frmReg.setVisible(true);
				}
			}
		});
		
		JButton btnInicio = new JButton("Inicio");
		btnInicio.setBounds(387, 198, 115, 29);
		btnInicio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(frmLog == null) {
					frmLog = new login();
					frmLog.setVisible(true);
				}
			}
		});
		
		
		btnRegistro.setBounds(78, 198, 115, 29);
		contentPane.add(btnRegistro);
		
		btnInicio.setBounds(387, 198, 115, 29);
		contentPane.add(btnInicio);
	}

}
