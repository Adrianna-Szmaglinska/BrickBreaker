import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GamePlay extends JPanel implements KeyListener, ActionListener {
    
    private boolean play = false;
    private boolean gameStarted = false;
    private int score = 0;
    private int totalbricks = 21;
    private Timer Timer;
    private int delay = 8;
    private int playerX = 310;
    private int ballposX = 120;
    private int ballposY = 350;
    private int ballXdir = -1;
    private int ballYdir = -2;
    private MapGenerator map;
    private Image background;


    public GamePlay() {
        map = new MapGenerator(3, 7);
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        Timer = new Timer(delay, this);
        Timer.start();

        // Load the background image
        ImageIcon backgroundIcon = new ImageIcon("lib/space-background.png"); // Replace with your image file name
        background = backgroundIcon.getImage();
    }
    
     public void paint(Graphics g) {
        g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
        //g.setColor(Color.black);
        //g.fillRect(0, 0, getWidth(), getHeight()); 
        //g.fillRect(1, 1, 692, 592);

        if (!gameStarted) {
            g.setColor(Color.white);
            g.setFont(new Font("oswald", Font.BOLD, 30));
            g.drawString("Press Enter to Start", 200, 300);
            return;
        }

        map.draw((Graphics2D) g);

        //g.setColor(Color.white);
        //g.fillRect(0, 0, 0, 592);
        //g.fillRect(0, 0, 692, 3);
        //g.fillRect(691, 0, 0, 592);

        g.setColor(Color.white);
        g.setFont(new Font("oswald", Font.BOLD, 25));
        g.drawString("" + score, 590, 30);

        g.setColor(Color.red);
        g.fillRect(playerX, 550, 100, 8);

        //ball
        g.setColor(Color.MAGENTA);
        g.fillOval(ballposX, ballposY, 20, 20);

        if (ballposY > 570) {
            play = false;
            ballXdir = 0;
            ballYdir = 0;
            g.setColor(Color.white);
            g.setFont(new Font("oswald", Font.BOLD, 30));
            g.drawString("Game Over Score: " + score, 190, 300);

            g.setFont(new Font("oswald", Font.BOLD, 30));
            g.drawString("Press Enter to Restart", 190, 340);
        }
        if(totalbricks == 0){
            play = false;
            ballYdir = -2;
            ballXdir = -1;
            g.setColor(Color.red);
            g.setFont(new Font("oswald",Font.BOLD,30));
            g.drawString("Game Over: "+score,190,300);

            g.setFont(new Font("oswald", Font.BOLD, 30));
            g.drawString("Press Enter to Restart", 190, 340);
        }
        //g.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Timer.start();

        if (play) {
            if (new Rectangle(ballposX, ballposY, 20, 20).intersects(new Rectangle(playerX, 550, 100, 8))) {
                ballYdir = -ballYdir;
            }

            A:
            for (int i = 0; i < map.map.length; i++) {
                for (int j = 0; j < map.map[0].length; j++) {
                    if (map.map[i][j] > 0) {
                        int brickX = j * map.bricksWidth + 80;
                        int brickY = i * map.bricksHeight + 50;
                        int bricksWidth = map.bricksWidth;
                        int bricksHeight = map.bricksHeight;

                        Rectangle rect = new Rectangle(brickX, brickY, bricksWidth, bricksHeight);
                        Rectangle ballrect = new Rectangle(ballposX, ballposY, 20, 20);
                        Rectangle brickrect = rect;

                        if (ballrect.intersects(brickrect)) {
                            map.setBricksValue(0, i, j);
                            totalbricks--;
                            score += 5;
                            if (ballposX + 19 <= brickrect.x || ballposX + 1 >= brickrect.x + bricksWidth) {
                                ballXdir = -ballXdir;
                            } else {
                                ballYdir = -ballYdir;
                            }
                            break A;
                        }
                    }

                }
            }


            ballposX += ballXdir;
            ballposY += ballYdir;
            if (ballposX < 0 || ballposX > 670) {
                ballXdir = -ballXdir; // Reflect the ball when hitting the left or right walls
            }
            if (ballposY < 0) {
                ballYdir = -ballYdir; // Reflect the ball when hitting the top wall
            }
            //if (ballposX > 670) {
                //ballXdir = -ballXdir;
            //}
        }
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {

       }


    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (playerX >= 600) {
                playerX = 600;
            } else {
                moveRight();
            }
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            if (playerX < 10) {
                playerX = 10;
            } else {
                moveLeft();
            }
        } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!gameStarted) {
                // Start the game when Enter is pressed for the first time
                gameStarted = true;
            } else if (!play) {
                // Restart the game when Enter is pressed again
                    ballposX = 120;
                    ballposY = 350;
                    ballXdir = -1;
                    ballYdir = -2;
                    score = 0;
                    playerX = 310;
                    totalbricks = 21;
                    map = new MapGenerator(3, 7);
                }
                play = true;
                repaint();
            }
        }

        public void moveRight ()
        {
            play = true;
            playerX += 40;
        }
        public void moveLeft ()
        {
            play = true;
            playerX -= 40;
        }
}