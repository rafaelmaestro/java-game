import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class Score extends Rectangle {
    
    static int APP_WIDTH;
    static int APP_HEIGHT;
    int player1;
    int player2;

    Score(int APP_WIDTH, int APP_HEIGHT){
        Score.APP_WIDTH = APP_WIDTH;
        Score.APP_HEIGHT = APP_HEIGHT;
    }
    
    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Consolas", Font.BOLD, 30));

        g.drawLine(APP_WIDTH/2, 0, APP_WIDTH/2, APP_HEIGHT);

        // draw the scores
        // String.valueOf() gets the value of the parameter and converts the int to a string
        // String.valueOf(player2/10) + String.valueOf(player2%10) makes the score look like 00 instead of 0

        g.drawString(String.valueOf(player1/10) + String.valueOf(player1%10), (APP_WIDTH/2) - 85, 35);
        g.drawString(String.valueOf(player2/10) + String.valueOf(player2%10), (APP_WIDTH/2) + 45, 35);
    }
}
