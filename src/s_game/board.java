package s_game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class board extends JPanel implements ActionListener {
	private final int b_width = 300;
	private final int b_height = 300;
	private final int dot_size = 10;
	private final int total_dots = 900;
	private final int rand_num = 29;
	private final int delay = 140;
	
	private int dots;
	private int apple_x;
	private int apple_y;
	
	private final int[] x = new int[total_dots];
	private final int[] y = new int[total_dots];
	
	private boolean left_d = false;
	private boolean right_d = true;
	private boolean up_d = false;
	private boolean down_d = false;
	private boolean ingame = true;
	
	private Timer timer;
	private Image ball;
	private Image apple;
	private Image head;
	
	public board() {
		initBoard();
	}
	
	private void initBoard() {
		addKeyListener(new TAdapter());
		setBackground(Color.black);
		setPreferredSize(new Dimension(b_width, b_height));
		setFocusable(true);
		loadImage();
		initGame();
	}
	private void loadImage(){
		ImageIcon iid = new ImageIcon("src/resources/dot.png");
		ball = iid.getImage();
		ImageIcon iia = new ImageIcon("src/resources/apple.png");
		apple = iia.getImage();
		ImageIcon iih = new ImageIcon("src/resources/head.png");
		head = iih.getImage();
	}
	
	private void initGame(){
		dots = 3;
		for(int i=0; i<dots; i++) {
			x[i] = 50 - (i*dot_size);
			y[i] = 50;
		}
		LocateApple();
		timer = new Timer(delay, this);
		timer.start();
	}
	
	private void LocateApple() {
		int r = (int) (Math.random() * rand_num);
		apple_x = r * dot_size;
		
		r = (int) (Math.random() * rand_num);
		apple_y = r * dot_size;
	}
	
	private void checkApple(){
		if((x[0] == apple_x) && (y[0] == apple_y)){
			dots++;
			LocateApple();
		}
	}
	
	private void move() {
		for(int z=dots; z>0; z--) {
			x[z] = x[z-1];
			y[z] = y[z-1];
		}
		if(left_d)
			x[0] -= dot_size;
		if(right_d)
			x[0] += dot_size;
		if(up_d)
			y[0] -= dot_size;
		if(down_d)
			y[0] += dot_size;
	}
	
	private void checkCollision() {
		for(int z=dots; z>0; z--) {
			if(z>4 && x[0]==x[z] && y[0] == y[z])
				ingame = false;
		}
		if(y[0]<0)
			ingame = false;
		if(y[0]>=b_height)
			ingame = false;
		if(x[0]<0)
			ingame = false;
		if(x[0]>=b_width)
			ingame = false;
		if(!ingame)
			timer.stop();
	}
	
	public void paintComponent(Graphics g){
	super.paintComponent(g);
	doDrawing(g);
	}
	
	void doDrawing(Graphics g){
		if(ingame) {
			g.drawImage(apple, apple_x, apple_y, this);
			
			for(int z=0; z<dots; z++) {
				if(z==0)
					g.drawImage(head, x[0], y[0], this);
				else
					g.drawImage(ball, x[z], y[z], this);
			}
			Toolkit.getDefaultToolkit().sync();
		}
		else
			gameOver(g);
	}
	
	void gameOver(Graphics g){
		String msg = "Game Over";
        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics metr = getFontMetrics(small);

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(msg, (b_width - metr.stringWidth(msg)) / 2, b_height / 2);
	}
	
	public void actionPerformed(ActionEvent e) {
		if(ingame) {
			checkApple();
			checkCollision();
			move();
		}
		repaint();
	}
	
	private class TAdapter extends KeyAdapter {
		
		//@Override
		public void keyPressed(KeyEvent e) {
			int key = e.getKeyCode();
			if((key == KeyEvent.VK_LEFT) && (!right_d)) {
				left_d = true;
				up_d = false;
				down_d = false;
			}
			if(key == KeyEvent.VK_RIGHT && (!left_d)) {
				right_d = true;
				up_d = false;
				down_d = false;
			}
			if(key == KeyEvent.VK_UP && (!down_d)) {
				left_d = false;
				up_d = true;
				right_d = false;
			}
			if(key == KeyEvent.VK_DOWN && (!up_d)) {
				left_d = false;
				right_d = false;
				down_d = true;
			}
		}
	}

}
