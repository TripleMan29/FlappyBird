import java.awt.*;

class Bird {
    private int y;
    final static int sizeBird = 50;
    final static int x = (Frame.DEFAULT_WIDTH - sizeBird) / 2 - 75;;
    private int ymotion = 0;

    private boolean jump = false;


    Bird(int height) {

        y = (height - sizeBird) / 2;
    }
    void start() {
        if (jump) {
            ymotion -= 1;
            y += ymotion;
            if (ymotion < 0) {
                jump = false;
                ymotion = -12;
            }
        } else {
            ymotion += 1;
            y += ymotion;

        }
    }

    void jump() {
        ymotion = 0;
        jump = true;
    }


    int getY() {
        return y;
    }





}
