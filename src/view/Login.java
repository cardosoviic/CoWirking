package view;

import javax.swing.JDialog;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.JPasswordField;
import java.awt.Rectangle;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import com.mysql.cj.protocol.x.SyncFlushDeflaterOutputStream;

import model.DAO;

import java.awt.Cursor;
import javax.swing.ImageIcon;
import java.awt.Color;

public final class Login extends JDialog {
	private JTextField inputLogin;
	private JPasswordField inputSenha;


	public Login() {
		
		addWindowListener(new WindowAdapter(){
			public void windowActivated(WindowEvent e) {
				statusConexaoBanco();
			}
		});
		
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
		
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				logar();
			}
		});
		
		
		JLabel tituloLogin = new JLabel("Acessar conta");
		tituloLogin.setHorizontalAlignment(SwingConstants.CENTER);
		tituloLogin.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tituloLogin.setBounds(10, 72, 486, 17);
		getContentPane().add(tituloLogin);
		
		imgDatabase_1 = new JLabel("");
		imgDatabase_1.setIcon(new ImageIcon(Login.class.getResource("/img/databaseOff.png")));
		imgDatabase_1.setBounds(21, 265, 53, 65);
		getContentPane().add(imgDatabase_1);
	}
	
	DAO dao = new DAO();
	
	private JLabel imgDatabase_1;
	private void statusConexaoBanco() {
		try {
			Connection conexaoBanco = dao.conectar();
			
			if (conexaoBanco == null) {
				//Escolher a imagem 
				imgDatabase_1.setIcon(new ImageIcon (Login.class.getResource("/img/databaseOff.png")));
				
			}
			
			else {
				//Trocar a imagem se houver conexão 
				imgDatabase_1.setIcon(new ImageIcon (Login.class.getResource("/img/databaseOn.png")));
				
			}
			conexaoBanco.close();
		}
		
		catch (Exception e) {
			System.out.println(e);
		}
		
		
		
	}
	
	
	private void logar() {
		String read = "select * from funcionario where login=? and senha=md5(?)";
		
		try {
			//Estabelecer a conexão 
			Connection conexaoBanco = dao.conectar();
			
			//Preparar a execução do script SQL 
			PreparedStatement executarSQL = conexaoBanco.prepareStatement(read);
			
			//Atribuir valores de login e senha 
			//Substituir as interrogações ? ? pelo conteúdo da caixa dee texto (input)
			executarSQL.setString(1, inputLogin.getText());
			executarSQL.setString(2, inputSenha.getText());
			
			//Executar os comandos SQL e de acordo  com o resultado liberar os recursos na tela
			ResultSet resultadoExecucao = executarSQL.executeQuery();
			
			//Validação do funcionário (autenticação)
			//resultadoExcucao.next() significa que o login e a senha existem, ou seja, correspondem
			
			if (resultadoExecucao.next()) {
				Home home = new Home ();
				home.setVisible(true);
			
			}
			
			else {
				System.out.println("Login e/ou senha inválidos");
			}
	}
	
		
		catch (Exception e ) {
	    	System.out.println(e);	
		
		}
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
