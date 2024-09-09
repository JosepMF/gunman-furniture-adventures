package managers;

import sprites.bullets.Bullet;
import sprites.enemies.Enemy;
import window.Game;

import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class CollisionManager {
    private final Game gameManager;
    BlockingQueue<ArrayList<Enemy>> arrayListBlockingQueueEnemy;
    BlockingQueue<ArrayList<Bullet>> arrayListBlockingQueueBullet;

    public CollisionManager(Game gameManager) {
        this.arrayListBlockingQueueEnemy = new LinkedBlockingQueue<>();
        this.arrayListBlockingQueueBullet = new LinkedBlockingQueue<>();

        this.gameManager = gameManager;

        Thread shootsThread = new Thread(this::checkBulletCollisionWithEnemies);
        shootsThread.start();
    }

    private void checkBulletCollisionWithEnemies() {
        // TODO: make it better mother fucker
        try {
            while (true) {
                ArrayList<Enemy> enemyArrayList = arrayListBlockingQueueEnemy.take();
                ArrayList<Bullet> bulletArrayList = arrayListBlockingQueueBullet.take();
                if(!enemyArrayList.isEmpty()) {
                   for (Enemy enemy : enemyArrayList) {
                       bulletArrayList.removeIf(bullet -> {
                           if (bullet.spriteRectangle.intersects(enemy.spriteRectangle) && bullet.isPlayerBullet) {
                               enemy.health -= (int) bullet.damage;
                               gameManager.score += (int) bullet.damage;
                           }
                           if(bullet.spriteRectangle.intersects(gameManager.player.spriteRectangle) && !bullet.isPlayerBullet) gameManager.player.health -= (int) bullet.damage;
                           return bullet.spriteRectangle.intersects(enemy.spriteRectangle) && bullet.isPlayerBullet;
                       });
                   }
                }
            }

        } catch (InterruptedException e) {
            System.err.println(e.getCause());
        }
    }

    public void setEnemyArrayListAndBulletArrayList(ArrayList<Enemy> enemyArrayList, ArrayList<Bullet> bulletArrayList) throws InterruptedException {
        arrayListBlockingQueueBullet.put(bulletArrayList);
        arrayListBlockingQueueEnemy.put(enemyArrayList);
    }
}
