import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;


class MainPanel extends JPanel {
    boolean started, gameOver;
    private Bird bird;
    private Column column;
    private Image birdIMG;


    public MainPanel(int width, int height) {
        bird = new Bird(width, height);
//        column = new Column(getWidth(),getHeight());

        try {
            birdIMG = ImageIO.read(new File("GUIAPP\\background\\flappyBird.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        setFocusable(true);


        /**/
        setBackground(Color.cyan);
        /**/

        KeyListener  keyListener = new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE){
                    bird.jump();
                }
            }
        };

        addKeyListener(keyListener);
        ActionListener timerListener = e ->{
            bird.jump();
            repaint();
        };

        Timer timer = new Timer(20, timerListener);
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(birdIMG, bird.getX(), bird.getY(), null);

//        g.fillRect();


    }
}