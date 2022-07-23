// Developed by Rafael Maestro dos Santos, 201021137
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class Controller extends Rectangle{
    
    // controller variables configs
    int controllerId;
    // controllerVelocity is gonna set how fast de controller move up and down
    int controllerVelocity;
    int speed = 10;

    Controller(int x, int y, int CONTROLLER_WIDTH, int CONTROLLER_HEIGHT, int controllerId){
        super(x, y, CONTROLLER_WIDTH, CONTROLLER_HEIGHT);
        this.controllerId = controllerId;
    }
    public void draw(Graphics g) {
        // set controllers colors
        g.setColor(Color.WHITE);
        g.fillRect(x, y, width, height);
    }
    public void move() {
        //make the movement more smoth for the controllers
        y = y + controllerVelocity;
    }
    public void setYDir(int yDirection) {
        controllerVelocity = yDirection;
    }
    public void keyPressed(KeyEvent e) {
        //handle the key events
        // REVIEW: change the conditionals to not use so many if statements
        switch(controllerId){
            case 1:
                if(e.getKeyCode() == KeyEvent.VK_W){
                    setYDir(-speed);
                    move();
                }
                if(e.getKeyCode() == KeyEvent.VK_S){
                    setYDir(speed);
                    move();
                }
            break;
            case 2:
            if(e.getKeyCode() == KeyEvent.VK_UP){
                setYDir(-speed);
                move();
            }
            if(e.getKeyCode() == KeyEvent.VK_DOWN){
                setYDir(speed);
                move();
            }
            break;
        }
    }
    public void keyReleased(KeyEvent e) {
        switch(controllerId){
            case 1:
                if(e.getKeyCode() == KeyEvent.VK_W){
                    setYDir(0);
                    move();
                }
                if(e.getKeyCode() == KeyEvent.VK_S){
                    setYDir(0);
                    move();
                }
            break;
            case 2:
            if(e.getKeyCode() == KeyEvent.VK_UP){
                setYDir(0);
                move();
            }
            if(e.getKeyCode() == KeyEvent.VK_DOWN){
                setYDir(0);
                move();
            }
            break;
        }
    }
}
