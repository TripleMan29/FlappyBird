
class Bird {
    private int y;
    final static int sizeBird = 50;
    final static int x = (Frame.DEFAULT_WIDTH - sizeBird) / 2 - 75;
    private int ymotion = 0;
    private boolean jump = false;


    Bird() {

        y = (Frame.DEFAULT_HEIGHT - sizeBird) / 2;
    }
    void start() {
        if (y < Frame.DEFAULT_HEIGHT - 120 - sizeBird){
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
    }

    void jump() {
        ymotion = 0;
        jump = true;
    }

    int getY() {
        return y;
    }
}
