import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;
//Makes panels
public class Circle_Square extends JFrame {
 JFrame frame = new JFrame();
 Shape drawpanel = new Circle();
 Shape drawpanel2 = new Circle();
 Shape drawpanel3 = new Square();
 Shape drawpanel4 = new Square();
 public static void main(String[] args) {
  Circle_Square gui = new Circle_Square();
  gui.go();
 }

 public void go() {
  frame.getContentPane().setLayout(null);
  frame.getContentPane().add(drawpanel);
  frame.getContentPane().add(drawpanel2);
  frame.getContentPane().add(drawpanel3);
  frame.getContentPane().add(drawpanel4);
  drawpanel.setBounds(0, 0, 50, 50);
  drawpanel2.setBounds(50, 0, 50, 50);
  drawpanel3.setBounds(0, 50, 50, 50);
  drawpanel4.setBounds(50, 50, 50, 50);

  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  frame.setSize(150, 150);
  frame.setVisible(true);
  frame.setMinimumSize(new Dimension(150,150));
  frame.setMaximumSize(new Dimension(150,150));
 }

}
//Adds random colours to graphics
class Shape extends JComponent implements MouseListener {
 public void paintComponent(Graphics g) {

  int red = (int) (Math.random() * 255);
  int green = (int) (Math.random() * 255);
  int blue = (int) (Math.random() * 255);
  Color startrandomColor = new Color(red, green, blue);

  red = (int) (Math.random() * 255);
  green = (int) (Math.random() * 255);
  blue = (int) (Math.random() * 255);
  Color endrandomColor = new Color(red, green, blue);

  Graphics2D g2d = (Graphics2D) g;
  this.addMouseListener(this);
  GradientPaint gradient = new GradientPaint(70, 70, startrandomColor,
    150, 150, endrandomColor);

  g2d.setPaint(gradient);


 }
//paints when clicked
 @Override
 public void mouseClicked(MouseEvent e) {
  if ((e.getButton() == 1))
   this.repaint();
   
  }


 @Override
 public void mouseEntered(MouseEvent e) {
  // TODO Auto-generated method stub

 }

 @Override
 public void mouseExited(MouseEvent e) {
  // TODO Auto-generated method stub

 }

 @Override
 public void mousePressed(MouseEvent e) {
  // TODO Auto-generated method stub

 }

 @Override
 public void mouseReleased(MouseEvent e) {
  // TODO Auto-generated method stub

 }
}
//Created circles
 class Circle extends Shape{
	 public void paintComponent(Graphics g) {
		 super.paintComponent(g);
		 g.fillOval(0,0,50,50);
	 }
 }
//creats squares 
 class Square extends Shape{
	 public void paintComponent(Graphics g) {
		 super.paintComponent(g);
		 g.fillRect(0,0,50,50);
	 }
 }
