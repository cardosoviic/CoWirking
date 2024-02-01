package view;

import javax.swing.JDialog;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.JPasswordField;
import java.awt.Rectangle;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Cursor;
import javax.swing.ImageIcon;
import java.awt.Color;

public final class Login extends JDialog {
	private JTextField inputLogin;
	private JPasswordField inputSenha;

	public Login() {
		getContentPane().setBackground(new Color(255, 255, 255));
		setResizable(false);
		getContentPane().setFont(new Font("Trebuchet MS", Font.PLAIN, 11));
		setTitle("Login");
		setBounds(new Rectangle(0, 0, 512, 380));
		setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/img/logo.png")));
		getContentPane().setLayout(null);

		JLabel txtLogin = new JLabel("Login");
		txtLogin.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		txtLogin.setBounds(133, 135, 46, 17);
		getContentPane().add(txtLogin);

		JLabel txtSenha = new JLabel("Senha");
		txtSenha.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		txtSenha.setBounds(133, 181, 46, 14);
		getContentPane().add(txtSenha);

		inputLogin = new JTextField();
		inputLogin.setBounds(178, 134, 147, 20);
		getContentPane().add(inputLogin);
		inputLogin.setColumns(10);

		inputSenha = new JPasswordField();
		inputSenha.setBounds(178, 179, 147, 20);
		getContentPane().add(inputSenha);
		
		JButton btnLogin = new JButton("Entrar");
		btnLogin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnLogin.setBounds(208, 233, 89, 23);
		getContentPane().add(btnLogin);
		
		JLabel tituloLogin = new JLabel("Acessar conta");
		tituloLogin.setHorizontalAlignment(SwingConstants.CENTER);
		tituloLogin.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tituloLogin.setBounds(10, 72, 486, 17);
		getContentPane().add(tituloLogin);
		
		JLabel imgDatabase = new JLabel("");
		imgDatabase.setIcon(new ImageIcon(Login.class.getResource("/img/databaseOff.png")));
		imgDatabase.setBounds(21, 265, 53, 65);
		getContentPane().add(imgDatabase);
	}

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login dialog = new Login();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);

				}

				catch (Exception e) {
					e.printStackTrace();
				}
			}

		});

	}
}
