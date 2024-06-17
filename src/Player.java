import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class Player extends ImageModel {
    private Movement movement;
    private int y_cpy = -1;
    private boolean jump = false;
    private boolean end_jump = true;
    private boolean dead = false;
    private int jumpLevel = 90;
    private boolean right_space = false;
    private boolean left_space = false;
    private boolean right = false;
    private boolean left = false;
    private int countRight = 0;
    private int countLeft = 0;


    public Player(Image img, Movement movement) {
        this.img = img;
        width = img.getWidth(null);
        height = img.getHeight(null);
        this.x = (500 - 5) / 2 - width / 2;
        this.y = 500 - 25 - height;
        dx = 3;
        dy = 0;
        this.movement = movement;
        stored_dy = 5;
    }

    public boolean isDead() {
        return dead;
    }

    public int getJumpLevel() {
        return jumpLevel;
    }

    public boolean isRight() {
        return right;
    }

    public boolean isLeft() {
        return left;
    }

    public void setJump(boolean jump) {
        this.jump = jump;
    }

    public void setEnd_jump(boolean end_jump) {
        this.end_jump = end_jump;
    }

    public void setJumpLevel(int jumpLevel) {
        this.jumpLevel = jumpLevel;
    }

    @Override
    public void update() {
        if (x < 60) {
            x = 60;
        }
        if (x > GameFrame.BOARD_WIDTH - width - 5 - 60) {
            x = GameFrame.BOARD_WIDTH - width - 5 - 60;
        }
        if (y > GameFrame.BOARD_HEIGHT - height - 25) {
            dead = true;
            y = -y;
        }
        if (jump) {
            dy = -stored_dy;
            img = new ImageIcon(getClass().getResource("/Character/jump.png")).getImage();
        }
        if (y <= y_cpy - jumpLevel) {
            if (right_space) {
                img = new ImageIcon(getClass().getResource("/Character/jump2right.png")).getImage();
                right_space = false;
            } else if (left_space) {
                img = new ImageIcon(getClass().getResource("/Character/jump2left.png")).getImage();
                left_space = false;
            }
            y_cpy = -1;
            dy = stored_dy;
            jump = false;
            end_jump = false;
        } else if (y < 0) {
            y_cpy = -1;
            dy = stored_dy;
            jump = false;
            end_jump = false;
        }
        //Walking Right
        if (movement.keyIs(KeyEvent.VK_RIGHT)) {
            x += dx;
            right = true;
            countRight++;
        }
        //Walking Left
        if (movement.keyIs(KeyEvent.VK_LEFT)) {
            x -= dx;
            left = true;
            countLeft++;
        }
        //Jump State
        if (movement.keyIs(KeyEvent.VK_SPACE)) {
            if (!jump && end_jump) {
                jump = true;
                y_cpy = y;
            }
        }
        //Changing icons when jumping and walking
        if (movement.keyIs(KeyEvent.VK_RIGHT) && movement.keyIs(KeyEvent.VK_SPACE)) {
            right_space = true;
            img = new ImageIcon(getClass().getResource("/Character/jump1right.png")).getImage();
        } else if (movement.keyIs(KeyEvent.VK_LEFT) && movement.keyIs(KeyEvent.VK_SPACE)) {
            left_space = true;
            img = new ImageIcon(getClass().getResource("/Character/jump1left.png")).getImage();
        }
        //Changing icons when walking right and not jumping
        if (right && !jump) {
            switch (countRight) {
                case 1:
                    img = new ImageIcon(getClass().getResource("/Character/walk1right.png")).getImage();
                    right = false;
                    break;
                case 2:
                    img = new ImageIcon(getClass().getResource("/Character/walk2right.png")).getImage();
                    right = false;
                    break;
                case 3:
                    img = new ImageIcon(getClass().getResource("/Character/walk3right.png")).getImage();
                    countRight = 0;
                    right = false;
                    break;
                default:
                    countRight = 0;
                    right = false;
            }
            //Changing icons when walking left and not jumping
        } else if (left && !jump) {
            switch (countLeft) {
                case 1:
                    img = new ImageIcon(getClass().getResource("/Character/walk1left.png")).getImage();
                    left = false;
                    break;
                case 2:
                    img = new ImageIcon(getClass().getResource("/Character/walk2left.png")).getImage();
                    left = false;
                    break;
                case 3:
                    img = new ImageIcon(getClass().getResource("/Character/walk3left.png")).getImage();
                    countLeft = 0;
                    left = false;
                    break;
                default:
                    countLeft = 0;
                    left = false;
            }
        }
        y += dy;
    }
}
