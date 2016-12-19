import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;


public class RollDicePanel extends JPanel {

    private Die die;    
 


    RollDicePanel() {
        //Create the dice
        die  = new Die();
        
        //Create the button to roll the dice
        JButton rollButton = new JButton("Roll");
        
        // Add listener.
        rollButton.addActionListener(new RollListener());
        
        //Layout components
        this.setLayout(new FlowLayout());
        this.add(rollButton, BorderLayout.NORTH);
        this.add(die , BorderLayout.CENTER);
        
        JTextField textbox = new JTextField("update Value",10);
        JButton button = new JButton("Submit Value");
        
        this.add(textbox);
        
        button.addActionListener(new SubmitListener(textbox));
        this.add(button);
        this.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
    }
    
    

    private class RollListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            die.roll();
 
        }
    }
    
    private class SubmitListener implements ActionListener {
    	JTextField textbox;
    	public SubmitListener(JTextField textbox){
    		this.textbox = textbox;
    	}
        public void actionPerformed(ActionEvent e) {
            die.roll(Integer.parseInt(textbox.getText()),textbox);
 
        }
    }
}