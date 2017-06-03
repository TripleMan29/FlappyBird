import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

class Column {
    private Random random;
    private ArrayList<Rectangle> columns;
    private final static int space = 300;
    private final static int widthColumn = 100;
    private int heightColumn;

    Column(int width, int height){
        heightColumn = 50 + random.nextInt(300);
        columnAdd(width, height);
    }

    public void columnAdd(int width, int height){
        columns.add(new Rectangle(width + widthColumn + columns.size() * 300, height - heightColumn - 120, widthColumn, heightColumn));
        columns.add(new Rectangle(width + widthColumn + (columns.size() - 1) * 300, 0, widthColumn, height - heightColumn - space));
        columns.add(new Rectangle(width + widthColumn + (columns.size() - 2) * 300, 0, widthColumn, height - heightColumn - space));
        columns.add(new Rectangle(width + widthColumn + (columns.size() - 3) * 300, 0, widthColumn, height - heightColumn - space));
    }

}