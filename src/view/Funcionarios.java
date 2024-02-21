package view;

import javax.swing.JDialog;

import java.awt.EventQueue;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import model.DAO;
import net.proteanit.sql.DbUtils;

import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.Cursor;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Color;

public class Funcionarios extends JDialog {
	private JTextField inputNome;
	private JTextField inputEmail;
	private JTextField inputLogin;
	private JPasswordField inputSenha;
	public JButton imgCreate;
	public JButton imgUpdate;
	public JButton imgDelete;

	public Funcionarios() {
		setTitle("Funcionários");
		setResizable(false);
		setBounds(new Rectangle(300, 100, 614, 403));
		setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/img/logo.png")));
		getContentPane().setLayout(null);
		
		JLabel nomeFunc = new JLabel("Nome:");
		nomeFunc.setBounds(24, 58, 46, 14);
		getContentPane().add(nomeFunc);
		
		JLabel loginFunc = new JLabel("Login:");
		loginFunc.setBounds(24, 184, 46, 14);
		getContentPane().add(loginFunc);
		
		JLabel senhaFunc = new JLabel("Senha:");
		senhaFunc.setBounds(309, 184, 46, 14);
		getContentPane().add(senhaFunc);
		
		JLabel emailFunc = new JLabel("E-mail:");
		emailFunc.setBounds(309, 233, 46, 14);
		getContentPane().add(emailFunc);
		
		JLabel perfilFunc = new JLabel("Perfil:");
		perfilFunc.setBounds(24, 233, 46, 14);
		getContentPane().add(perfilFunc);
		
		inputNome = new JTextField();
		inputNome.setBounds(74, 55, 440, 20);
		getContentPane().add(inputNome);
		inputNome.setColumns(10);
		
		inputNome.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e ) {
				buscarFuncionarioNaTabela();
			}
		});
		
		inputEmail = new JTextField();
		inputEmail.setColumns(10);
		inputEmail.setBounds(365, 230, 200, 20);
		getContentPane().add(inputEmail);
		
		inputLogin = new JTextField();
		inputLogin.setColumns(10);
		inputLogin.setBounds(74, 181, 200, 20);
		getContentPane().add(inputLogin);
		
		inputSenha = new JPasswordField();
		inputSenha.setBounds(365, 181, 200, 20);
		getContentPane().add(inputSenha);
		
		imgCreate = new JButton("");
		imgCreate.setBackground(new Color(240, 240, 240));
		imgCreate.setBorderPainted(false);
		imgCreate.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		imgCreate.setIcon(new ImageIcon(Funcionarios.class.getResource("/img/create.png")));
		imgCreate.setBounds(348, 290, 65, 54);
		getContentPane().add(imgCreate);
		
		
		
		imgCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adicionarFuncionario();
			}
		});
		
		
		imgUpdate = new JButton("");
		imgUpdate.setBackground(new Color(240, 240, 240));
		imgUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		imgUpdate.setBorderPainted(false);
		imgUpdate.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		imgUpdate.setIcon(new ImageIcon(Funcionarios.class.getResource("/img/update.png")));
		imgUpdate.setBounds(423, 290, 65, 54);
		getContentPane().add(imgUpdate);
		
		imgDelete = new JButton("");
		imgDelete.setBackground(new Color(240, 240, 240));
		imgDelete.setBorderPainted(false);
		imgDelete.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		imgDelete.setIcon(new ImageIcon(Funcionarios.class.getResource("/img/delete.png")));
		imgDelete.setBounds(500, 290, 65, 54);
		getContentPane().add(imgDelete);
		
		inputPerfil = new JComboBox();
		inputPerfil.setModel(new DefaultComboBoxModel(new String[] {"", "Administrador", "Gerência", "Atendimento", "Suporte"}));
		inputPerfil.setBounds(74, 229, 200, 22);
		getContentPane().add(inputPerfil);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(74, 75, 440, 83);
		getContentPane().add(scrollPane);
		
		tblFuncionarios = new JTable();
		scrollPane.setViewportView(tblFuncionarios);
		
		JButton btnPesquisar = new JButton("");
		btnPesquisar.setBackground(new Color(240, 240, 240));
		btnPesquisar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnPesquisar.setBorderPainted(false);
		btnPesquisar.setIcon(new ImageIcon(Funcionarios.class.getResource("/img/search.png")));
		btnPesquisar.setBounds(536, 52, 57, 23);
		getContentPane().add(btnPesquisar);
		
		tblFuncionarios.addMouseListener(new MouseAdapter() {
			public void mouseClicked (MouseEvent e) {
				setarCaixasTexto();
			}
		});

	}
	
	//Criar um objeto da classe DAO para estabelecer conexão com banco
	DAO dao = new DAO();
	private JComboBox inputPerfil;
	private JTable tblFuncionarios;
	
	private void adicionarFuncionario() {
		String create = "insert into funcionario (nomeFunc, login, senha, perfil, email) values (?, ?, md5(?), ?, ?);";
		
		
		try {
			// Estabelecer a conexão
			Connection conexaoBanco = dao.conectar();
			
			// Preparar a execusão do script SQL
			PreparedStatement executarSQL = conexaoBanco.prepareStatement(create);
			
			//Substituir os pontos de interrogação pelo conteúdo das caixas de texto (inputs)
			executarSQL.setString(1, inputNome.getText());
			executarSQL.setString(2, inputLogin.getText());
			executarSQL.setString(3, inputSenha.getText());
		
			executarSQL.setString(4, inputPerfil.getSelectedItem().toString());
			
			executarSQL.setString(5, inputEmail.getText());
			
			//Executar os comandos SQL e inserir o funcionario no banco de dados
			executarSQL.executeUpdate();
			
			JOptionPane.showMessageDialog(null, "Usuário cadastrado com sucesso!");
			
			 limparCampos();
			
			conexaoBanco.close();
		} 
		
		catch (SQLIntegrityConstraintViolationException error) {
			JOptionPane.showMessageDialog(null, "Login em uso. \nEscolha outro nome de usuário.");
			
		}
		
		catch (Exception e) {
		   System.out.println(e);
		  
		}
		
	}
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Funcionarios dialog = new Funcionarios();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	private void buscarFuncionarioNaTabela() {
		
		String readTabela = "select idFuncionario as ID, nomeFunc as Nome, email as Email from funcionario"
				+ " where nomeFunc like ?; ";
		
		try {
			//Estabelecer a conexão 
			Connection conexaoBanco = dao.conectar();
			
			//Preparar a execução dos comandos SQL 
			PreparedStatement executarSQL = conexaoBanco.prepareStatement(readTabela);
			
			//Substituir o ? pelo conteúdo da caixa de texto 
			executarSQL.setString(1, inputNome.getText() + "%");
			
			//Executar o comando SQL e exibir o resultado na tabela 
			
			ResultSet resultadoExecucao = executarSQL.executeQuery();
			
			// Exibir o resultado na tabela 
			tblFuncionarios.setModel(DbUtils.resultSetToTableModel(resultadoExecucao));
			
			conexaoBanco.close();
			
		}
		
		catch (Exception e) {
			System.out.println(e);
		}
		
	}
	
	
	private void setarCaixasTexto() {
		
		
		//Criar uma variável para receber a linha da tabela 
		int setarLinha = tblFuncionarios.getSelectedRow();
		
		inputNome.setText(tblFuncionarios.getModel().getValueAt(setarLinha, 1).toString());
		//inputEmail.setText(tblFuncionarios.getModel().getValueAt(setarLinha,2).toString());
	}

	//Criar método para buscar funcionário pelo botão pesquisar 
	
	private void btnBuscarFuncionario() {
		String readBtn = "select * from funcionario where nomeFunc = ?;";
		 
		try {
			//Estabelecer a conexão 
			Connection conexaoBanco = dao.conectar();
			
			PreparedStatement executarSQL = conexaoBanco.prepareStatement(readBtn);
			
			executarSQL.setString(1, inputNome.getText());
			
			//Executar o comando SQL e exibir o resultado no formulário funcionário (todos os seus dados)
			ResultSet resultadoExecucao = executarSQL.executeQuery();
			
			if (resultadoExecucao.next()) {
				//Preencher os campos do formulário
				inputLogin.setText(resultadoExecucao.getString(3));
				inputSenha.setText(resultadoExecucao.getString(4));
				inputPerfil.setSelectedItem(resultadoExecucao.getString(5));
			
			}
			
		}
		
		catch (Exception e) {
			System.out.println(e);
		}
		
	}
	

    public void limparCampos() {
	 inputNome.setText(null);
	 inputLogin.setText(null);
	 inputSenha.setText(null);
	 inputEmail.setText(null);
	 inputPerfil.setSelectedItem(null);
	 inputNome.requestFocus();
	 
   }
 }
