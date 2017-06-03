import java.awt.*;

class Bird {
    private int x, y;
    private int dy = 0;

    private boolean jump = false;
    private final static int sizeBird = 50;

    Bird(int width, int height) {
        x = (width - sizeBird) / 2;
        y = (height - sizeBird) / 2;
    }
    void start() {
        if (jump) {
            dy -= 1;
            y -= dy;
            if (dy < -50) {
                jump = false;
                dy = 0;
            }
        } else {
            dy += 1;
            y += dy;
        }
    }

    void jump() {
        jump = true;
    }

    int getX() {
        return x;
    }

    int getY() {
        return y;
    }





}
