import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {
    private Image backgroundImage;

    public MainPanel() {
        try {
            this.backgroundImage = new ImageIcon(getClass().getResource("/Background/background1.gif")).getImage();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.setBounds(0, 0, MainFrame.GAME_WIDTH, MainFrame.GAME_HEIGHT);
        this.setLayout(null);
        this.setPreferredSize(new Dimension(MainFrame.GAME_WIDTH, MainFrame.GAME_HEIGHT));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(this.backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
