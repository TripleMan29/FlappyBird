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
    private Column[] columns;
    private Image birdIMG, building;
    int score;


    public MainPanel(int width, int height) {

        score = 0;
        bird = new Bird(height);
        columns = new Column[3];
        for (int i = 0; i < 3; i++){
            columns[i] = new Column();
            columns[i].setX(columns[i].getx() + i*400);
        }

        try {
            birdIMG = ImageIO.read(new File("C:\\Users\\Денис\\FlappyBird\\FlappyBird.png"));
            building = ImageIO.read(new File("C:\\Users\\Денис\\FlappyBird\\Building1.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        setFocusable(true);


        /**/
        setBackground(Color.cyan);
        /**/

        KeyListener  keyListener = new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (!gameOver) {
                    if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                        started = true;
                        bird.jump();
                    }
                }
            }
        };

        addKeyListener(keyListener);
        ActionListener timerListener = e -> repaint();


        Timer timer1 = new Timer(20, timerListener);
        timer1.start();


        ActionListener timeListener1 = e -> {
            if(!gameOver && started) {
                for (Column column : columns) {
                    column.move();
                }
                bird.start();
                isGameOver();
            }
        };

        Timer timer2 = new Timer(20, timeListener1);
        timer2.start();
    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);
        g.drawImage(birdIMG, Bird.x, bird.getY(), null);
        paintColumns(g);

        g.setColor(Color.gray);
        g.fillRect(0, Frame.DEFAULT_HEIGHT - 120, Frame.DEFAULT_WIDTH, 100);

        g.setColor(Color.darkGray);
        g.fillRect(0, Frame.DEFAULT_HEIGHT - 120, Frame.DEFAULT_WIDTH, 20);





    }

    private void paintColumns(Graphics g){
        g.setColor(Color.gray);
        for(Column element: columns){

            g.drawImage(building, element.getx(), 0, Column.widthColumn, element.getY2(), null);
            g.drawImage(building, element.getx(), element.getY3(), Column.widthColumn, Frame.DEFAULT_HEIGHT - element.getY3(), null);

        }
    }

    private void isGameOver(){
        for (Column element: columns) {
            //       if (element.getY2() == 0 && bird.getX() + Bird.sizeBird/2 > element.getx() + Column.widthColumn/2 - 10 && bird.getX() + Bird.sizeBird/2 < element.getx() + Column.widthColumn/2 + 10){
            //           score++;
            //           System.out.println(score);
            if ((Bird.x + Bird.sizeBird > element.getx() && Bird.x + 5 < element.getx() + Column.widthColumn && (bird.getY() + 5 < element.getY2() || bird.getY() - 5 + Bird.sizeBird > element.getY3()))  || bird.getY() + Bird.sizeBird - 5 > Frame.DEFAULT_HEIGHT - 120 ) {
                gameOver = true;
            }
        }
        }

    }
