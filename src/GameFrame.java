import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {
    protected static final int SIZE = 200;
    public static final int BOARD_WIDTH = 500;
    public static final int BOARD_HEIGHT = 500;

    private Movement movement;
    protected Player player;
    protected Block[] blocks;
    protected int level;
    private int count;
    protected Background background;
    protected Background border;

    public GameFrame() {
        this.movement = new Movement(this.player);
        this.player = new Player(new ImageIcon(getClass().getResource("/Character/1.png")).getImage(), movement);
        this.count = 0;
        this.level = 1;
        this.setSize(BOARD_WIDTH, BOARD_HEIGHT);
        this.setTitle("Icy Tower Jump");
        this.setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setIconImage(new ImageIcon(getClass().getResource("/Character/1.png")).getImage());
        initGame();
        setLayout(new BorderLayout());
        add(new GamePanel(this), BorderLayout.CENTER);
        addKeyListener(movement);
        setVisible(true);
    }

    public void restartGame() {
        this.player = new Player(new ImageIcon(getClass().getResource("/Character/1.png")).getImage(), movement);
        this.movement = new Movement(this.player);
        this.count = 0;
        this.level = 1;
        initGame();
        this.getContentPane().removeAll();
        this.add(new GamePanel(this), BorderLayout.CENTER);
        this.revalidate();
        this.repaint();
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getCount() {
        return this.count;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void initGame() {
        this.background = new Background(new ImageIcon(getClass().getResource("/Background/background.png")).getImage(), 2, movement);
        this.border = new Background(new ImageIcon(getClass().getResource("/Background/border2.png")).getImage(), 1, movement);
        this.blocks = new Block[SIZE];
        int distanceBlock = 70;
        for (int i = 0; i < SIZE; i++) {
            if ((i + 1) % 50 == 0) {
                blocks[i] = new Block(new ImageIcon(getClass().getResource("/Blocks/Block.png")).getImage(), 0, 475 - distanceBlock, player, movement);
            } else {
                blocks[i] = new Block(new ImageIcon(getClass().getResource("/Blocks/Block" + (int) ((Math.random() * 3) + 1) + ".png")).getImage(), (int) ((Math.random() * 195) + 75), 475 - distanceBlock, player, movement);
            }
            blocks[i].index = i + 1;
            distanceBlock += 70;
        }
    }
}
