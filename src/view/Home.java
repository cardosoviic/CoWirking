package view;

import java.awt.EventQueue;
import java.awt.Rectangle;

import javax.swing.JDialog;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;
import java.text.DateFormat;
import java.awt.event.ActionEvent;
import java.awt.Cursor;
import javax.swing.JPanel;

public class Home extends JDialog {
	
	public JPanel panelUsuario;
    public JLabel txtUsuarioLogado;
    public JLabel txtData;
   
	
	public Home() {
		addWindowListener (new WindowAdapter() {
			public void windowActivated(WindowEvent e) {
			    Date dataSistema = new Date();
				DateFormat formatadorData = DateFormat.getDateInstance(DateFormat.FULL);
				txtData.setText(formatadorData.format(dataSistema));
			}
		});
		
		
		getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		setTitle("Home");
		setBounds(new Rectangle(0, 0, 516, 381));
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Home.class.getResource("/img/logo.png")));
		getContentPane().setLayout(null);
		
		JButton btnUser = new JButton("");
		btnUser.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnUser.setIcon(new ImageIcon(Home.class.getResource("/img/user.png")));
		btnUser.setBounds(57, 63, 116, 105);
		getContentPane().add(btnUser);
		btnUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Funcionarios func = new Funcionarios();
				func.setVisible(true);
			}
		});
		
		
		JButton btnRoom = new JButton("");
		btnRoom.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnRoom.setIcon(new ImageIcon(Home.class.getResource("/img/room.png")));
		btnRoom.setBounds(198, 63, 116, 105);
		getContentPane().add(btnRoom);
		
		btnRoom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Salas sala = new Salas();
				sala.setVisible(true);
			}
		});
		
		JButton btnReserve = new JButton("");
		btnReserve.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnReserve.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Reservas reserva = new Reservas();
				reserva.setVisible(true);
			}
		});
		btnReserve.setIcon(new ImageIcon(Home.class.getResource("/img/reserve.png")));
		btnReserve.setBounds(339, 63, 116, 105);
		getContentPane().add(btnReserve);
		
		panelUsuario = new JPanel();
		panelUsuario.setBounds(0, 288, 501, 54);
		getContentPane().add(panelUsuario);
		panelUsuario.setLayout(null);
		
		txtData = new JLabel("");
		txtData.setBounds(214, 0, 258, 37);
		panelUsuario.add(txtData);
		
		txtUsuarioLogado = new JLabel("");
		txtUsuarioLogado.setBounds(10, 0, 194, 37);
		panelUsuario.add(txtUsuarioLogado);
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home dialog = new Home();
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
