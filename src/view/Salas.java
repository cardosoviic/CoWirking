package view;

import java.awt.EventQueue;
import java.awt.Rectangle;

import javax.swing.JDialog;
import java.awt.Toolkit;

public class Salas extends JDialog{
	public Salas() {
		setTitle("Salas");
		setBounds(new Rectangle(0, 0, 516, 381));
		setIconImage(Toolkit.getDefaultToolkit().getImage(Salas.class.getResource("/img/logo.png")));
		setResizable(false);
		getContentPane().setLayout(null);
	}

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Salas dialog = new Salas();
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
