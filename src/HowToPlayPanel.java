import javax.swing.*;
import java.awt.*;

public class HowToPlayPanel extends JPanel
{
    private Image backgroundImage;


    public HowToPlayPanel(){
        this.backgroundImage = new ImageIcon(getClass().getResource("/Background/instructions.gif")).getImage();
        this.setBounds(0,0,MainFrame.GAME_WIDTH,MainFrame.GAME_HEIGHT);
        this.setLayout(null);
        this.setFocusable(true);
        this.requestFocus();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(this.backgroundImage,0,0,this);
    }


}
