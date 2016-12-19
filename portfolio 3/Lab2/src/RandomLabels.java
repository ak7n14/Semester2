import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
public class RandomLabels {
	public static void main(String args[]) {
		RLFrame window = new RLFrame("Random Labels!");
		window.init();
			
	}
	
}

class RLFrame extends JFrame{
	public RLFrame (String title){
		super(title);
	}
	
	public void init(){
		Random rand = new Random();
		
		Container panel = this.getContentPane();
		panel.setLayout(new BorderLayout());
		
		
		JPanel topPanel = new JPanel();
		topPanel.setLayout(null);
		
		
		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(new FlowLayout());
		JTextField positionLabel = new JTextField("x=Null , y = Null");
		bottomPanel.add(positionLabel);
		
		class LabelListener implements MouseMotionListener{

			public void mouseDragged(MouseEvent e) {
				
			}

		
			public void mouseMoved(MouseEvent e) {
				positionLabel.setText("x = "+ e.getX() + " y = "+e.getY());
			}
			
		}
		

		
		for(int i =0;i<5;i++){
			JLabel label = new JLabel();
			label.addMouseMotionListener(new LabelListener());
			label.setBackground(Color.GREEN);
			label.setOpaque(true);
			label.setBounds(rand.nextInt(400), rand.nextInt(100), 50, 50);
			topPanel.add(label);
		
			
		}
;
		
		panel.add(topPanel,BorderLayout.CENTER);
		panel.add(bottomPanel,BorderLayout.SOUTH);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(400,400);
		this.setVisible(true);
		}
		
}
