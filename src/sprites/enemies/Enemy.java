package sprites.enemies;

import sprites.Sprite;
import sprites.player.Player;
import window.Game;

import java.awt.*;

public abstract class Enemy extends Sprite {
    public int damage;
    public Player playerTracked;
    public int totalHealth;
    private int fillHeathRect;

    public Enemy(int positionX, int positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
    }

    public abstract void attack();

    public abstract void attackStrategy();

    public void drawHeathBar(Graphics2D g2) {
        g2.setColor(Color.RED);
        g2.drawRect(positionX, positionY - Game.TILE_SIZE - 9, Game.TILE_SIZE, 8);

        if (totalHealth == 0) {
            System.out.println(totalHealth);
            return;
        }

        fillHeathRect = Game.TILE_SIZE * health/totalHealth;
        g2.fillRect(positionX, positionY - Game.TILE_SIZE - 9, fillHeathRect, 8);
    }

    @Override
    public void update() {
        attack();
        attackStrategy();
        spriteRectangle.setLocation(positionX, positionY);
    }
}
