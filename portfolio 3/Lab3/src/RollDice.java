

import java.awt.*;
import javax.swing.*;

public class RollDice extends JApplet {
    
    public RollDice() {
        this.setContentPane(new RollDicePanel());
    }
    
    //Initializing evrything
    public static void main(String[] args) {
        JFrame window = new JFrame("Dice Demo");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setContentPane(new RollDicePanel());
        window.pack();
        //System.out.println(window.getContentPane().getSize());
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }
}