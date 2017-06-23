import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.io.*;

class MainPanel extends JPanel {
    private File file;
    private boolean started, gameOver;
    private Bird bird;
    private Column[] columns;
    private Image birdIMG, building, background;
    private int score, maxScore;


    MainPanel() {

        file = new File("C:\\Users\\Денис\\FlappyBird\\bestScore.txt");
        maxScore = readScore();
        score = 0;
        bird = new Bird();
        columns = new Column[3];
        for (int i = 0; i < 3; i++){
            columns[i] = new Column();
            columns[i].setX(columns[i].getx() + i*400);
        }

        try {
            birdIMG = ImageIO.read(new File("C:\\Users\\Денис\\FlappyBird\\sprites\\FlappyBird.png"));
            building = ImageIO.read(new File("C:\\Users\\Денис\\FlappyBird\\sprites\\Building1.png"));
            background = ImageIO.read(new File("C:\\Users\\Денис\\FlappyBird\\sprites\\city.png"));

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

                if (gameOver && e.getKeyCode() == KeyEvent.VK_ENTER){
                    bird = new Bird();
                    for (int i = 0; i < 3; i++){
                        columns[i] = new Column();
                        columns[i].setX(columns[i].getx() + i*400);
                        gameOver = false;
                        started = false;
                        score = 0;
                    }
                }
            }
        };

        addKeyListener(keyListener);
        ActionListener timerListener = e -> repaint();


        Timer timer1 = new Timer(10, timerListener);
        timer1.start();


        ActionListener timeListener1 = e -> {
            if (started) {
                if (!gameOver) {
                    for (Column column : columns) {
                        column.move();
                    }
                    isGameOver();
                }
                bird.start();
            }
        };

        Timer timer2 = new Timer(20, timeListener1);
        timer2.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;

        g2D.drawImage(background, 0, 0, null);

        paintColumns(g2D);

        g2D.drawImage(birdIMG, Bird.x, bird.getY(), null);

        g2D.setColor(Color.BLACK);
        g2D.fillRect(0, Frame.DEFAULT_HEIGHT - 120, Frame.DEFAULT_WIDTH, 100);


        g2D.setColor(Color.ORANGE);
        g2D.fillRect(0, Frame.DEFAULT_HEIGHT - 120, Frame.DEFAULT_WIDTH, 20);




        g2D.setFont(new Font("Calibri", Font.BOLD, 50));
        g2D.setColor(Color.YELLOW);
        if (!gameOver) {
            if (started)
                g2D.drawString(String.valueOf(score), Frame.DEFAULT_WIDTH / 2, 70);
            else
                g2D.drawString("Press space to play", Frame.DEFAULT_WIDTH / 2 - 180, 160);
        }
        else {
            g2D.drawString("Game Over", Frame.DEFAULT_WIDTH / 2 - 110, 160);
            g2D.drawString("Your score " + score, Frame.DEFAULT_WIDTH / 2 - 120, 240);
            if (score > maxScore) {
                maxScore = score;
                g2D.drawString("Best score " + maxScore, Frame.DEFAULT_WIDTH / 2 - 115, 320);
                writeScore(maxScore);
            }
            else g2D.drawString("Best score " + maxScore, Frame.DEFAULT_WIDTH / 2 - 115, 320);

            g2D.setFont(new Font("Calibri", Font.BOLD, 20));
            g2D.setColor(Color.BLACK);
            g2D.drawString("Press ENTER to return ", Frame.DEFAULT_WIDTH / 2 - 165, 645);
        }

    }

    private void paintColumns(Graphics2D g2D){
        g2D.setColor(Color.gray);
        for(Column element: columns){

            g2D.drawImage(building, element.getx(), 0, Column.widthColumn, element.getY2(), null);
            g2D.drawImage(building, element.getx(), element.getY3(), Column.widthColumn, Frame.DEFAULT_HEIGHT - element.getY3(), null);

        }
    }

    private void isGameOver(){
        for (Column element: columns) {
            //       if (element.getY2() == 0 && bird.getX() + Bird.sizeBird/2 > element.getx() + Column.widthColumn/2 - 10 && bird.getX() + Bird.sizeBird/2 < element.getx() + Column.widthColumn/2 + 10){
            //           score++;
            //           System.out.println(score);
            if ((Bird.x + Bird.sizeBird > element.getx() && Bird.x + 5 < element.getx() + Column.widthColumn && (bird.getY() + 5 < element.getY2() || bird.getY() - 5 + Bird.sizeBird > element.getY3()))  || bird.getY() + Bird.sizeBird - 5 > Frame.DEFAULT_HEIGHT - 125 ) {
                gameOver = true;
            }
            else if(Bird.x + Bird.sizeBird == element.getx() && bird.getY() + 5 > element.getY2() && bird.getY() - 5 + Bird.sizeBird < element.getY3()) {
                score++;
            }
        }


        }
    private void writeScore(int maxScore) {
        try {
            try (PrintWriter out = new PrintWriter(file.getAbsoluteFile())) {
                out.print(maxScore);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Integer readScore() {
        try (BufferedReader in = new BufferedReader(new FileReader(file.getAbsoluteFile()))) {
            return new Integer(in.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    }
