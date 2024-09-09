package managers;

import sprites.bullets.Bullet;
import window.Game;

import java.awt.*;
import java.util.ArrayList;

public class BulletManager {

    public ArrayList<Bullet> bulletArrayList;

    public BulletManager() {
        bulletArrayList = new ArrayList<>();
    }

    public void addBullet(Bullet b) {
        if (b != null) {
            bulletArrayList.add(b);
        }
    }

    public void update() {
        if (bulletArrayList.isEmpty()) {
            return;
        }
        for (Bullet b : bulletArrayList) {
            if (b != null) {
                b.update();
            }
        }
        // the bullets will be removed if it leaves outside the windows

        bulletArrayList.removeIf(bullet -> bullet.positionX > Game.WIDTH || bullet.positionX + Game.TILE_SIZE < 0 || bullet.positionY > Game.HEIGHT || bullet.positionY + Game.TILE_SIZE < 0);
    }

    public void draw(Graphics2D g2) {
        if (bulletArrayList.isEmpty()) {
            return;
        }
        for (Bullet b : bulletArrayList) {
            if (b != null) {
                b.draw(g2);
            }
        }
    }
}
