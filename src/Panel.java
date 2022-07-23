// Developed by Rafael Maestro dos Santos, 201021137
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.imageio.*;
import java.io.*;

public class Panel extends JPanel implements Runnable{
    
    // create configs for the panel constants that will have the same value for multiple instances of the app

    // constants configs
    static final int APP_WIDTH = 1000; // set the width of the app
    static final int APP_HEIGHT = (int)(APP_WIDTH * (0.5555)); // set the height of the app
    static final Dimension APP_DIMENSION = new Dimension(APP_WIDTH, APP_HEIGHT); // set the dimension of the app
    static final int BALL_SIZE = 20; // set the ball diameter
    static final int CONTROLLER_WIDTH = 25; // set the controller width
    static final int CONTROLLER_HEIGHT = 100; // set the controller height

    // instances configs
    Controller controller1;
    Controller controller2;
    Ball ball;
    Score score;
    Random random;
    Thread gameThread;
    Image image;
    Image background;
    Graphics graphics;
    Graphics backgroundGraphics;

    Panel(){
        newBall();
        newController();
        score = new Score(APP_WIDTH, APP_HEIGHT);
        this.setFocusable(true);
        this.addKeyListener(new AL());
        this.setPreferredSize(APP_DIMENSION);

        // load the background image
        try {
            background = ImageIO.read(new File(".\\dist\\table.jpg"));
            } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Missing required images!\n" + e, "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
            }

        //game thread
        gameThread = new Thread(this);
        gameThread.start();
    }
    
    public void newController(){
        //sets the positions where the controllers will be placed
        controller1 = new Controller(0, (APP_HEIGHT/2)-(CONTROLLER_HEIGHT/2), CONTROLLER_WIDTH, CONTROLLER_HEIGHT, 1);
        controller2 = new Controller(APP_WIDTH-CONTROLLER_WIDTH, (APP_HEIGHT/2)-(CONTROLLER_HEIGHT/2), CONTROLLER_WIDTH, CONTROLLER_HEIGHT, 2);
    }

    // this code snippet its supossed to work but it doesnt
    public void paintComponent(Graphics g){
        // draw the background and the controllers imgages
        // using paint() doesnt work because it doesnt draw the background image
        super.paintComponent(g);
        g.drawImage(background, 0, 0, null);
        controller1.draw(g);
        controller2.draw(g);
        ball.draw(g);
        score.draw(g);
        Toolkit.getDefaultToolkit().sync();
    }

    public void newBall(){
		random = new Random();
        // sets the ball position to the center of the screen randonly on the y axis
		ball = new Ball((APP_WIDTH/2)-(BALL_SIZE/2),random.nextInt(APP_HEIGHT-BALL_SIZE),BALL_SIZE,BALL_SIZE);
    }

    public void run(){
        //game loop (?????) - CHECK LATER
        // stackoverflow.com/questions/8986499/how-to-make-a-game-loop-in-java
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        System.out.println("Running...");
        while(true){
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;

            if(delta >= 1){
                move();
                checkCollision();
                repaint();
                delta--;
            }
        }
    }

    public void checkCollision(){
        //DONT FORGET - implements the collision detection on the app size screen limits for the controllers

        // REVIEW: change the conditionals to not use so many if statements

        //bounce ball off top & bottom window edges
		if(ball.y <=0 || ball.y >= (APP_HEIGHT-BALL_SIZE)) {
            System.out.println("The ball bounced off top or bottom " + ball.ballYVelocity);
			ball.setYDir(-ball.ballYVelocity);
		}

		//bounce ball off paddle controllers
		if(ball.intersects(controller1)) {
			ball.ballXVelocity = Math.abs(ball.ballXVelocity);
			ball.ballXVelocity++;
			if(ball.ballYVelocity>0)
				ball.ballYVelocity++;
			else
				ball.ballYVelocity--;
			ball.setXDir(ball.ballXVelocity);
			ball.setYDir(ball.ballYVelocity);
		}
		if(ball.intersects(controller2)) {
			ball.ballXVelocity = Math.abs(ball.ballXVelocity);
			ball.ballXVelocity++;
			if(ball.ballYVelocity>0)
				ball.ballYVelocity++;
			else
				ball.ballYVelocity--;
			ball.setXDir(-ball.ballXVelocity);
			ball.setYDir(ball.ballYVelocity);
		}

        // check collision and stops controller 1
        if(controller1.y <= 0)
            controller1.y = 0;
        if(controller1.y >= (APP_HEIGHT-CONTROLLER_HEIGHT))
            controller1.y = APP_HEIGHT-CONTROLLER_HEIGHT;

        // chekc collision and stops controller 2
        if(controller2.y <= 0)
            controller2.y = 0;
        if(controller2.y >= (APP_HEIGHT-CONTROLLER_HEIGHT))
            controller2.y = APP_HEIGHT-CONTROLLER_HEIGHT;

        /*gives a player 1 if the ball hits left or right edges of the screen and create new controllers
        & ball
        */
        if(ball.x >= APP_WIDTH-BALL_SIZE) {
            score.player1++;
            newController();
            newBall();
            System.out.println("Player 1 scored -> Player 1:" + score.player1);
        }
        if(ball.x <= 0){
            score.player2++;
            newController();
            newBall();
            System.out.println("Player 2 scored -> Player 2:" + score.player2);
        }
    }
    public void move(){
        //making movement on the controllers more smooth 
        controller1.move();
        controller2.move();
        ball.move();
    }
    public class AL extends KeyAdapter{
        // class that will handle the key events calling the controller methods
        public void keyPressed(KeyEvent e){
            controller1.keyPressed(e);
            controller2.keyPressed(e);
        }
        public void keyReleased(KeyEvent e){
            controller1.keyReleased(e);
            controller2.keyReleased(e);
        }
    }
}