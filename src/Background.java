
import java.awt.Image;
import java.awt.event.KeyEvent;

public class Background extends ImageModel {
    private Movement movement;

    public Background(Image img, double dy, Movement movement) {
        this.img = img;
        this.x = 0;
        this.y = img.getHeight(null) * -1 + 475;
        this.stored_dy = dy;
        this.movement = movement;
    }

    @Override
    public void update() {
        if (this.movement.keyIs(KeyEvent.VK_SPACE)) {
            this.dy = this.stored_dy;
        }
        this.y += this.dy;
    }


}
