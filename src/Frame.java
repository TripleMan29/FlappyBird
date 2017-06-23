import javax.swing.*;

public class Frame extends JFrame {
    static final int DEFAULT_WIDTH = 700;
    static final int DEFAULT_HEIGHT = 750;

    private Frame(String title) {
        super(title);
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        add(new MainPanel());
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Frame("Flappy Bird"));
    }
}