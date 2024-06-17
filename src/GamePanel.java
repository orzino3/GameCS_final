import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class GamePanel extends JPanel implements Runnable {
    public static final int SCORE_MULTIPLIER = 10;
    public static final int TEXT_HEIGHT = 450;
    public static final int BLOCK_COUNT = 150;


    private GameFrame gameFrame;
    private Thread gameThread;

    public GamePanel(GameFrame gameFrame) {
        this.gameFrame = gameFrame;
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);
        //repeat the background and border
        if (gameFrame.background.y >= 0) {
            gameFrame.background.y = gameFrame.background.img.getHeight(null) * -1 + 475;
        }
        if (gameFrame.border.y >= 0) {
            gameFrame.border.y = gameFrame.border.img.getHeight(null) * -1 + 475;
        }
        gameFrame.setCount(gameFrame.getCount() + 1);
        if (gameFrame.getCount() % 20 == 0) {
            gameFrame.player.img = new ImageIcon(getClass().getResource("/Character/2.png")).getImage();
        }
        if (gameFrame.getCount() % 30 == 0) {
            gameFrame.player.img = new ImageIcon(getClass().getResource("/Character/3.png")).getImage();
        }
        //draw background
        graphics.drawImage(gameFrame.background.img, gameFrame.background.x, gameFrame.background.y, this);
        //draw blocks
        for (int i = 0; i < GameFrame.SIZE; i++) {
            graphics.drawImage(gameFrame.blocks[i].img, gameFrame.blocks[i].x, gameFrame.blocks[i].y, this);
        }
        //draw border
        graphics.drawImage(gameFrame.border.img, gameFrame.border.x, gameFrame.border.y, this);
        //draw character
        graphics.drawImage(gameFrame.player.img, gameFrame.player.x, gameFrame.player.y, this);
        //draw score and level
        graphics.setColor(Color.WHITE);
        graphics.setFont(new Font("Tahoma", Font.BOLD, 20));
        graphics.drawString("  Level: " + gameFrame.level, MainFrame.MARGIN * 10, TEXT_HEIGHT - MainFrame.MARGIN * 5);
        graphics.drawString("  Score: " + Block.getIndex * SCORE_MULTIPLIER, MainFrame.MARGIN * 10, TEXT_HEIGHT);

        if (gameFrame.player.isDead()) {
            graphics.drawImage(new ImageIcon(getClass().getResource("/Background/gameover.png")).getImage(), GameFrame.BOARD_WIDTH / 7, GameFrame.BOARD_HEIGHT / 2, this);
        }
    }

    @Override
    public void run() {
        while (true) {
            gameFrame.player.update();
            gameFrame.background.update();
            gameFrame.border.update();
            for (int i = 0; i < GameFrame.SIZE; i++) {
                gameFrame.blocks[i].update();
            }
            repaint();
            //check if dead
            if (gameFrame.player.isDead()) {
                int option = JOptionPane.showConfirmDialog(null, "Game Over!\nYour Score : " + Block.getIndex * 10 + "\nDo you want to reset game?", "Icy Tower Jump", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    gameFrame.restartGame();
                } else {
                    MainFrame.theme.stop();
                    gameFrame.dispose();
                    new MainFrame().setVisible(true);
                }
                Block.getIndex = 0;
                gameFrame.setLevel(1);
                gameFrame.setCount(0);
                break;
            }
            //check for level
            if (Block.getIndex != 0 && Block.getIndex / 50 == gameFrame.level) {
                gameFrame.background.stored_dy++;
                gameFrame.border.stored_dy++;
                for (int i = 0; i < GameFrame.SIZE; i++) {
                    gameFrame.blocks[i].stored_dy++;
                }
                gameFrame.player.stored_dy++;
                gameFrame.player.setJumpLevel(gameFrame.player.getJumpLevel() - 10);
                if (gameFrame.level == 3) {
                    gameFrame.player.dx++;
                }
                if (gameFrame.level < 3) {
                    gameFrame.setLevel(gameFrame.level + 1);
                }
            }
            if (Block.getIndex == BLOCK_COUNT) {
                int option = JOptionPane.showConfirmDialog(null, "You Win!\nYour Score : " + Block.getIndex * 10 + "\nPlay Again!", "Icy Tower Jump", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    gameFrame.restartGame();
                } else {
                    gameFrame.dispose();
                    new MainFrame().setVisible(true);
                }
                Block.getIndex = 0;
                gameFrame.setLevel(1);
                gameFrame.setCount(0);
                break;
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {

            }
        }
    }
}
