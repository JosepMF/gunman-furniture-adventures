package sprites.bullets;

import sprites.Sprite;
import window.Game;

import java.awt.*;

public class Bullet extends Sprite {
    public float damage;
    public boolean isPlayerBullet;
    double[] vectorMotion;

    public Bullet(double[] vectorMotion, int positionX, int positionY, int damage, boolean isPlayerBullet) {
        this.speed = 6;
        this.damage = damage;
        this.positionX = positionX;
        this.positionY = positionY;
        this.vectorMotion = vectorMotion;
        this.isPlayerBullet = isPlayerBullet;
    }

    @Override
    public void update() {
        positionX += (int) (vectorMotion[0] * speed);
        positionY += (int) (vectorMotion[1] * speed);

        spriteRectangle.setLocation(positionX, positionY);
    }

    @Override
    public void draw(Graphics2D g2) {
        g2.setColor(Color.green);
        g2.fillOval(positionX, positionY, Game.TILE_SIZE, Game.TILE_SIZE);
    }
}
