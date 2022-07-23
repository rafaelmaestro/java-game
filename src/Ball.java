// Developed by Rafael Maestro dos Santos, 201021137
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;



public class Ball extends Rectangle {
    
    // set de X and Y velocities for the ball, its gonna set how fast de ball moves in the X and Y axis
    int initialSpeed = 2;
    int ballXVelocity;
    int ballYVelocity;
    Random random;

    Ball(int x, int y, int width, int height){
		super(x,y,width,height);
        // using the random class to generate a random direction for the ball and initial speed to multiply
        // the direction to get the ball moving in a random direction. This will add some randomness to the game.
        // And since the ball is moving in a random direction, it will be harder to defend the goal.
		random = new Random();
		int randomXDir = random.nextInt(2);
		if(randomXDir == 0)
			randomXDir--;
		setXDir(randomXDir*initialSpeed);
		
		int randomYDir = random.nextInt(2);
		if(randomYDir == 0)
			randomYDir--;
		setYDir(randomYDir*initialSpeed);
    }
    public void move() {
        //incrementes the ball position in the X and Y axis
		x += ballXVelocity;
		y += ballYVelocity;
    }
    public void draw(Graphics g) {
        // set the ball color and makes the ball oval
		g.setColor(Color.YELLOW);
		g.fillOval(x, y, height, width);
    }
    public void setXDir(int randomXDir) {
        //sets the x direction of the ball, using the randomXDir variable when the ball touch the edges
        ballXVelocity = randomXDir;
    }
    public void setYDir(int randomYDir) {
        //sets the y direction of the ball, using the randomXDir variable when the ball touch the edges
        ballYVelocity = randomYDir;
    }
}
