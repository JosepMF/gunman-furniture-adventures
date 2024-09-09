package sprites.enemies;

import sprites.player.Player;
import window.Game;

import java.awt.*;

public class Chair extends Enemy{
    public Chair(Player playerTracked, int positionX, int positionY) {
        super(positionX, positionY);
        this.positionX = 200;
        this.positionY = 200;
        this.color = Color.CYAN; // test
        this.damage = 10;
        this.speed = 5;
        this.totalHealth = 50;
        this.health = totalHealth;
        this.playerTracked = playerTracked;
    }

    @Override
    public void attack() {
        // if the player rectangle collides with playerTracked rectangle, the chair subtracts 1 point of the health of the playerTracked
        if(this.spriteRectangle.intersects(this.playerTracked.spriteRectangle)) this.playerTracked.health -= 1;
    }

    @Override
    public void attackStrategy() {
        double[] trackedVec = new double[2];
        trackedVec[0] = playerTracked.positionX - positionX;
        trackedVec[1] = playerTracked.positionY - positionY;
        double[] attackDirection = new double[2];
        double a = Math.pow(trackedVec[0], 2) + Math.pow(trackedVec[1], 2);
        attackDirection[0] = trackedVec[0] / Math.sqrt(a);
        attackDirection[1] = trackedVec[1] / Math.sqrt(a);

        // move after calculate the direction
        positionX += (int) (attackDirection[0] * speed);
        positionY += (int) (attackDirection[1] * speed);
    }

    @Override
    public void draw(Graphics2D g2) {
        g2.setColor(color);
        g2.fillOval(positionX, positionY, Game.TILE_SIZE, Game.TILE_SIZE);
        this.drawHeathBar(g2);
    }
}
