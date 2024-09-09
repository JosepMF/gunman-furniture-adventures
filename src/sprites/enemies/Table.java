package sprites.enemies;

import sprites.bullets.Bullet;
import window.Game;

import java.awt.*;

public class Table extends Enemy {
    private int counter;

    public Table(int positionX, int positionY, Game gameManager) {
        super(positionX, positionY);

        this.totalHealth = 100;
        this.health = totalHealth;

        this.positionX = positionX;
        this.positionY = positionY;

        this.damage = 10; // the damage to the bullets what it can throw

        this.gameManger = gameManager;
    }

    /*
     * The attack counter when is equals to 100 the table enemy throw two bullets in the opposite directions,
     * the first bullets will be throwing in the x coordinate, when the counter will be equals to 200 the direction of the bullets change
     * for moving in the y coordinate. Also, when the counter arrives to 200, it will be equals to -1. Finally, the counter-increase by 1
     */
    @Override
    public void attack() {
        if (counter == 100) {
            gameManger.bulletManager.addBullet(new Bullet((new double[]{1, 0}), positionX, positionY, 10, false));
            gameManger.bulletManager.addBullet(new Bullet((new double[]{-1, 0}), positionX, positionY, 10, false));
        }
        if (counter == 200) {
            gameManger.bulletManager.addBullet(new Bullet((new double[]{0, 1}), positionX, positionY, 10, false));
            gameManger.bulletManager.addBullet(new Bullet((new double[]{0, -1}), positionX, positionY, 10, false));
            counter = -1;
        }
        counter++;
    }

    @Override
    public void attackStrategy() {
        // no attack Strategy
    }

    @Override
    public void draw(Graphics2D g2) {
        g2.setColor(Color.ORANGE);
        g2.fillRect(positionX, positionY, Game.TILE_SIZE, Game.TILE_SIZE);
        this.drawHeathBar(g2);
    }
}
