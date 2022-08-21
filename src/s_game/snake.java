package s_game;

import java.awt.EventQueue;
import javax.swing.JFrame;
public class snake extends JFrame {
	
	snake(){
		initUI();
		/*  add(new board());
		setResizable(false);
		setTitle("Snake Game");
		pack();
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  */
	}
	
	void initUI() {
		add(new board());
		setResizable(false);
		setTitle("Snake Game");
		pack();
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		new snake().setVisible(true);
		// TODO Auto-generated method stub
		/*EventQueue.invokeLater(new Runnable() {
		public void run() {
				
			JFrame ex = new snake();
			ex.setVisible(true);
			}
		});*/
     
	}

}
