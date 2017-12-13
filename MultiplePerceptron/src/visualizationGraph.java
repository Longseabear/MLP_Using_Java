import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Line2D;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class visualizationGraph extends JPanel implements ActionListener {

	public static final int LAYER1_X = 100;
	public static final Color LAYER1_COLOR = new Color(223, 140, 168, 255);

	public static final int LAYER2_X = 300;
	public static final Color LAYER2_COLOR = new Color(153, 140, 168, 255);

	public static final int LAYER3_X = 500;
	public static final Color LAYER3_COLOR = new Color(50, 140, 168, 255);

	public static final int LAYER4_X = 700;
	public static final Color LAYER4_COLOR = new Color(50, 140, 244, 255);
	int r = 50;

	public ArrayList<Layer> module;
	public Matrix loss;
	LayerManager container;
	Timer tm = new Timer(15, this);
	int x = 0, velX = 2;

	public visualizationGraph(ArrayList<Layer> _module, Matrix _loss) {
		module = _module;
		loss = _loss;
	}

	public visualizationGraph(LayerManager _container) {
		// TODO Auto-generated constructor stub
		container = _container;
		module = _container.module;
	}

	public Color nomalizationColor(float f) {
		if( Math.abs(f) <0.1){
			return new Color(0,0,0,0);
		}
		if (f > 0)
			return new Color((int) Math.min(255, f * 50), 0, 0, (int) Math.min(255, f * 300));
		else
			return new Color(0, 0, (int) Math.min(255, -1 * f * 50), (int) Math.min(255, -1*f * 300));
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(2, BasicStroke.CAP_ROUND, 0));
		for (int i = 0; i < 6; i++)
			for (int j = 0; j < 6; j++) {
				g.setColor(nomalizationColor(module.get(0).weight.matrix[i][j]));
				g2.draw(new Line2D.Double(LAYER1_X + (r / 2), 50 + (r / 2) + (j * 100), LAYER2_X + (r / 2),
						50 + (r / 2) + (i * 100)));
			}
		for (int i = 0; i < 6; i++)
			for (int j = 0; j < 6; j++) {
				g.setColor(nomalizationColor(module.get(1).weight.matrix[i][j]));
				g2.draw(new Line2D.Double(LAYER2_X + (r / 2), 50 + (r / 2) + (j * 100), LAYER3_X + (r / 2),
						50 + (r / 2) + (i * 100)));
			}
		for (int j = 0; j < 6; j++) {
			g.setColor(nomalizationColor(module.get(2).weight.matrix[0][j]));
			g2.draw(new Line2D.Double(LAYER3_X + (r / 2), 50 + (r / 2) + (j * 100), LAYER4_X + (r / 2), 300 + (r / 2)));
		}


		g.setColor(LAYER1_COLOR);
		g.fillOval(LAYER1_X, 50, r, r);
		g.fillOval(LAYER1_X, 150, r, r);
		g.fillOval(LAYER1_X, 250, r, r);
		g.fillOval(LAYER1_X, 350, r, r);
		g.fillOval(LAYER1_X, 450, r, r);
		g.fillOval(LAYER1_X, 550, r, r);

		g.setColor(LAYER2_COLOR);
		g.fillOval(LAYER2_X, 50, r, r);
		g.fillOval(LAYER2_X, 150, r, r);
		g.fillOval(LAYER2_X, 250, r, r);
		g.fillOval(LAYER2_X, 350, r, r);
		g.fillOval(LAYER2_X, 450, r, r);
		g.fillOval(LAYER2_X, 550, r, r);

		g.setColor(LAYER3_COLOR);
		g.fillOval(LAYER3_X, 50, r, r);
		g.fillOval(LAYER3_X, 150, r, r);
		g.fillOval(LAYER3_X, 250, r, r);
		g.fillOval(LAYER3_X, 350, r, r);
		g.fillOval(LAYER3_X, 450, r, r);
		g.fillOval(LAYER3_X, 550, r, r);

		g.setColor(LAYER4_COLOR);
		g.fillOval(LAYER4_X, 300, r, r);
		
		for (int j = 0; j < 6; j++) {
			g.setColor(nomalizationColor(module.get(0).bias.matrix[j][0]));
			g.fillOval(LAYER2_X+(r/4), 50+(j*100)+(r/4), r/2, r/2);
		}
		for (int j = 0; j < 6; j++) {
			g.setColor(nomalizationColor(module.get(1).bias.matrix[j][0]));
			g.fillOval(LAYER3_X+(r/4), 50+(j*100)+(r/4), r/2, r/2);
		}
		if (container.costFun._output != null) {
			g.drawString("" + container.costFun._output.matrix[0][0], LAYER4_X, 290);
		}
		tm.start();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		// if(x<0 || x>254)
		// velX *= -1;
		// x += velX;
		repaint();
	}

	public static void main(String[] args) {

		LayerManager container = new LayerManager();
		container.module.add(new LayerNN(6, 6, new ActivationSigmoid()));
		container.module.add(new LayerNN(6, 6, new ActivationSigmoid()));
		container.module.add(new LayerNN(6, 1, new ActivationSigmoid()));
		container.setLossFunction(new LayerMSE(1, 1));
		container.setAlpha(0.1f);

		JFrame jf = new JFrame();
		jf.setTitle("Visualization");
		jf.setVisible(true);
		jf.setSize(800, 900);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		visualizationGraph GameObject = new visualizationGraph(container);
		jf.add(GameObject);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ArrayList<float[]> test = ReadDataSet.readData("training.txt");
		for (int i = 0; i != 10000; i++) {
			System.out.println(i + " iter");
			try {
				container.training(test);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("Training Fail");
			}
		}
		container.printAllNodeWeight();
	}
}
