import javax.swing.*;

public class Frame extends JFrame {
    public static final int DEFAULT_WIDTH = 700;
    public static final int DEFAULT_HEIGHT = 750;

    private Frame(String title) {
        super(title);
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        MainPanel mainPanel = new MainPanel(DEFAULT_WIDTH,DEFAULT_HEIGHT);
        add(mainPanel);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Frame("Flappy Bird"));
    }
}