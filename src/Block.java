import java.awt.Image;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;

public class Block extends ImageModel {

    private Player player;
    private Movement movement;
    public int index = 0;
    public static int getIndex;


    public Block(Image img, int x, int y, Player player, Movement movement) {
        this.img = img;
        this.x = x;
        this.y = y;
        this.width = img.getWidth(null);
        this.height = img.getHeight(null);
        this.player = player;
        this.dy = 0;
        this.movement = movement;
        this.stored_dy = 1;
    }

    @Override
    public void update() {
        //Checking if player is on block and updating
        if (player.getDy() > stored_dy && player.y + player.height >= y && player.y + player.height <= y + height
                && player.x + player.width / 2 >= x && player.x + player.width / 2 <= x + width) {
            player.dy = stored_dy;
            getIndex = index;
            player.y = y - player.height + 2;
            player.setJump(false);
            player.setEnd_jump(true);
        } else if (player.dy == stored_dy && player.y + player.height >= y && player.y + player.height <= y + height
                && (player.x + player.width / 2 <= x || player.x + player.width / 2 >= x + width)) {
            if (player.isRight()) {
                player.img = new ImageIcon(getClass().getResource("/Character/jump3right.png")).getImage();
            } else if (player.isLeft()) {
                player.img = new ImageIcon(getClass().getResource("/Character/jump3left.png")).getImage();
            }
            player.dy = stored_dy + 4;
            player.setJump(false);
            player.setEnd_jump(false);
        }
        // קפיצה בבלוק הראשון תתחיל את המשחק
        if (movement.keyIs(KeyEvent.VK_SPACE)) {
            dy = stored_dy;
        }
        y += dy;
    }
}
