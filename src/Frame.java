// Developed by Rafael Maestro dos Santos, 201021137
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class Frame extends JFrame {
    
    // creating a panel instance that will hold the frame for the game
    Panel panel;
    
    Frame() {
        super("Pong Game");

        //panel configs
        panel = new Panel();
        this.add(panel);
        this.setResizable(false); // set the frame to not be resizable
        this.setBackground(Color.BLACK); // sets the background color of the frame
        this.pack();
        this.setVisible(true);


        // window configs
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // setting the frame to start in the center of the screen not the top left corner as usual
        this.setLocationRelativeTo(null); 

    }

    public void main (String[] args) throws Exception {
        Frame frame = new Frame();
    }

}
