import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class HowToPlayFrame extends JFrame {
    public static final int INFO_WIDTH = 500;
    public static final int INFO_HEIGHT = 200;

    public HowToPlayFrame() {
        this.setSize(INFO_WIDTH, INFO_HEIGHT);
        this.setTitle("How to Play");
        this.setIconImage(new ImageIcon(getClass().getResource("/Background/icytower.gif")).getImage());
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
        this.setFocusable(true);
        this.requestFocus();
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE || e.getKeyCode() == KeyEvent.VK_ENTER) {
                    dispose();
                }
            }
        });
        HowToPlayPanel instructions = new HowToPlayPanel();
        this.add(instructions);
    }
}
