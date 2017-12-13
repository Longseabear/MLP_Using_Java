import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class visualizationTesting extends JFrame {
	BufferedImage img = null;
	BufferedImage img2 = null;
	BufferedImage img3 = null;
	BufferedImage img4 = null;

	JTextField Input1;
	JTextField Input2;
	JTextField Input3;
	JTextField Input4;
	JTextField Input5;
	JTextField Input6;
	JButton bt;
	JButton go;
	MyPanel panel;
	MyPanel2 panel2;
	MyPanel3 panel3;
	JButton loading;

	public static void main(String[] args) {
		new visualizationTesting();
	}

	// 생성자
	public visualizationTesting() {
		setTitle("로그인 테스트");
		setSize(1600, 900);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		// 레이아웃 설정
		setLayout(null);
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(0, 0, 1600, 900);
		layeredPane.setLayout(null);

		// 패널1
		// 이미지 받아오기
		try {
			img = ImageIO.read(new File("src\\back.png"));
			img2 = ImageIO.read(new File("src\\pic.png"));
			img3 = ImageIO.read(new File("src\\pic2.png"));
			img4 = ImageIO.read(new File("src\\tenor.gif"));
		} catch (IOException e) {
			System.out.println("이미지 불러오기 실패");
			System.exit(0);
		}

		panel = new MyPanel();
		panel.setBounds(0, 0, 1600, 900);

		loading = new JButton();
		loading.setBounds(570, 250, 450, 300);
		loading.setIcon(new ImageIcon("src\\tenor.gif"));
		layeredPane.add(loading);
		loading.setVisible(false);

		panel2 = new MyPanel2();
		panel2.setBounds(0, 0, 1600, 900);
		layeredPane.add(panel2);

		panel3 = new MyPanel3();
		panel3.setBounds(120, 0, 1600, 900);
		layeredPane.add(panel3);

		panel2.setVisible(false);
		panel3.setVisible(false);

		// 로그인 필드
		Input1 = new JTextField(15);
		Input1.setHorizontalAlignment(JTextField.CENTER);
		Input1.setBounds(100, 70, 90, 60);
		layeredPane.add(Input1);
		Input1.setOpaque(false);
		Input1.setForeground(Color.WHITE);
		Input1.setBorder(javax.swing.BorderFactory.createEmptyBorder());

		// 로그인 필드
		Input2 = new JTextField(15);
		Input2.setHorizontalAlignment(JTextField.CENTER);
		Input2.setBounds(100, 195, 90, 60);
		layeredPane.add(Input2);
		Input2.setOpaque(false);
		Input2.setForeground(Color.WHITE);
		Input2.setBorder(javax.swing.BorderFactory.createEmptyBorder());

		// 로그인 필드
		Input3 = new JTextField(15);
		Input3.setHorizontalAlignment(JTextField.CENTER);
		Input3.setBounds(100, 310, 90, 60);
		layeredPane.add(Input3);
		Input3.setOpaque(false);
		Input3.setForeground(Color.WHITE);
		Input3.setBorder(javax.swing.BorderFactory.createEmptyBorder());

		// 로그인 필드
		Input4 = new JTextField(15);
		Input4.setHorizontalAlignment(JTextField.CENTER);
		Input4.setBounds(100, 432, 90, 60);
		layeredPane.add(Input4);
		Input4.setOpaque(false);
		Input4.setForeground(Color.WHITE);
		Input4.setBorder(javax.swing.BorderFactory.createEmptyBorder());

		// 로그인 필드
		Input5 = new JTextField(15);
		Input5.setHorizontalAlignment(JTextField.CENTER);
		Input5.setBounds(100, 570, 90, 60);
		layeredPane.add(Input5);
		Input5.setOpaque(false);
		Input5.setForeground(Color.WHITE);
		Input5.setBorder(javax.swing.BorderFactory.createEmptyBorder());

		// 로그인 필드
		Input6 = new JTextField(15);
		Input6.setHorizontalAlignment(JTextField.CENTER);
		Input6.setBounds(100, 717, 90, 60);
		layeredPane.add(Input6);
		Input6.setOpaque(false);
		Input6.setForeground(Color.WHITE);
		Input6.setBorder(javax.swing.BorderFactory.createEmptyBorder());

		// 로그인버튼 추가
		go = new JButton("GO!!");
		go.setBounds(755, 689, 104, 48);

		go.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						System.out.println(!loading.isVisible());
						loading.setVisible(!loading.isVisible());
					}
				});
				
				// TODO Auto-generated method stub
				if (go.getText().equals("BACK")) {
					panel2.setVisible(false);
					panel3.setVisible(false);
					go.setText("GO!!!");
					return;
				}
				System.out.println("hello");

				LayerManager container = new LayerManager();
				container.module.add(new LayerNN(6, 6, new ActivationSigmoid()));
				container.module.add(new LayerNN(6, 6, new ActivationSigmoid()));
				container.module.add(new LayerNN(6, 1, new ActivationSigmoid()));
				container.setLossFunction(new LayerMSE(1, 1));
				container.setAlpha(0.1f);

				container.netLoad("netTest.net");

				try {
					Thread t = new Thread(){
						@Override
						public void run(){
							try {
								Thread.sleep(3000);
								if (container.test(new Matrix(new float[] { Float.valueOf(Input1.getText()),
										Float.valueOf(Input2.getText()), Float.valueOf(Input3.getText()),
										Float.valueOf(Input4.getText()), Float.valueOf(Input5.getText()),
										Float.valueOf(Input6.getText()) })).matrix[0][0] >= 0.5) {
									panel2.setVisible(true);
								} else {
									panel3.setVisible(true);
								}
								SwingUtilities.invokeLater(new Runnable() {
									public void run() {
										go.setText("BACK");
									}
								});
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}							
						}
					};
					t.start();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		// 버튼 투명처리
		layeredPane.add(go);

		// 마지막 추가들
		layeredPane.add(panel);
		add(layeredPane);
		setVisible(true);

	}

	class MyPanel extends JPanel {
		public void paint(Graphics g) {
			g.drawImage(img, 0, 0, 1588, 860, null);
			Graphics2D g2 = (Graphics2D) g;

		}
	}

	class MyPanel2 extends JPanel {
		public void paint(Graphics g) {
			{
				g.drawImage(img2, 0, 0, 1588, 860, null);
				Graphics2D g2 = (Graphics2D) g;

			}
		}
	}

	class MyPanel3 extends JPanel {
		public void paint(Graphics g) {
			{
				g.drawImage(img3, 0, 0, 1588, 860, null);
				Graphics2D g2 = (Graphics2D) g;
			}
		}
	}
}