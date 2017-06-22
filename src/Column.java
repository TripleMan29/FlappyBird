import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

class Column {
    private Random random;
    private static int rnd1, rnd2;

    private int y2, y3, x;
    public final static int space = 300;
    public final static int widthColumn = 100;
    public final static int heightColumn = 375;
   // private int heightColumn;


    Column(){
        changeColumn();
        move();
        x = Frame.DEFAULT_WIDTH;
    }

    private void changeColumn() {
        for (int i = 0; i < 5; i++) {
            rnd2 = new Random().nextInt(4);
            if (rnd1 != rnd2) {
                rnd1 = rnd2;
                break;
            }
        }

        switch (rnd1) {
            case 0:
                y2 = 50;

                break;
            case 1:

                y2 = 150;

                break;
            case 2:
                y2 = 250;
                break;
            case 3:
                y2 = 350;
                break;
        }
        y3 = y2 + 200;
    }


    public void move() {
        if (x < -100){
            changeColumn();
            x = Frame.DEFAULT_WIDTH + 400;
        }
        x -= 8;
    }

    public void setX(int x) {
        this.x = x;
    }

    int getY2(){
        return y2;
    }

    int getY3(){
        return y3;
    }

    int getx(){
        return x;
    }
}