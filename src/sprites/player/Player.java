package sprites.player;

import sprites.Sprite;
import sprites.bullets.Bullet;
import utils.KeyHandler;
import utils.MouseHandler;
import window.Game;

import java.awt.*;

public class Player extends Sprite {
    private final KeyHandler keyHandler;
    private final MouseHandler mouseHandler;

    public Player(Game gameManager) {
        this.positionX = 0;
        this.positionY = 0;
        this.health = 100;
        this.shield = 0;
        this.speed = 5;
        this.gameManger = gameManager;
        this.keyHandler = gameManager.keyHandler;
        this.mouseHandler = gameManager.mouseHandler;
    }

    @Override
    public void update() {
        motion();
        shoot();
        setSpriteRectanglePosition();
        if (health <= 0) {
            health = 0;
            gameManger.gameOver = true;
        }
    }

    @Override
    public void draw(Graphics2D g2) {
        g2.setColor(Color.BLUE);
        g2.fillRect(positionX, positionY, Game.TILE_SIZE, Game.TILE_SIZE);
        drawHealth(g2);
    }

    // draw the health of the player
    private void drawHealth(Graphics2D g2) {
        g2.setColor(Color.RED);
        g2.setFont(new Font("arial", Font.BOLD, Game.TILE_SIZE));
        g2.drawString(String.valueOf(this.health), 0, Game.TILE_SIZE);
    }

    // motion of player
    private void motion() {
        if(keyHandler.upKey) positionY -= speed;
        if(keyHandler.downKey) positionY += speed;
        if(keyHandler.leftKey) positionX -= speed;
        if(keyHandler.rightKey) positionX += speed;
    }

    private void shoot() {
        // calculate unitary vector of motion

        Point pointMouse = mouseHandler.getPointMouse();
        double[] shootVec = new double[2];
        shootVec[0] = pointMouse.getX() - positionX;
        shootVec[1] = pointMouse.getY() - positionY;
        double[] unitaryShootVec = new double[2];
        final double a = Math.pow(shootVec[0], 2) + Math.pow(shootVec[1], 2);
        unitaryShootVec[0] = shootVec[0] / Math.sqrt(a);
        unitaryShootVec[1] = shootVec[1] / Math.sqrt(a);


        if(mouseHandler.isClicked) {
            Bullet bullet = new Bullet(unitaryShootVec, positionX, positionY, 25, true);
            gameManger.bulletManager.addBullet(bullet);
        }

        mouseHandler.isClicked = false;
    }

    private void setSpriteRectanglePosition() {
        this.spriteRectangle.setLocation(positionX, positionY);
    }
}
