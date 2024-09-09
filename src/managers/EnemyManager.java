package managers;

import sprites.enemies.Chair;
import sprites.enemies.Enemy;
import sprites.enemies.Table;
import sprites.player.Player;
import window.Game;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class EnemyManager {
    private final Game gameManager;
    private final Player playerTracked;
    public ArrayList<Enemy> enemiesArrayList;

    public EnemyManager(Player playerTracked, Game gameManager) {
        enemiesArrayList = new ArrayList<>();

        this.playerTracked = playerTracked;

        this.gameManager = gameManager;

        Thread enemyThread = new Thread(this::spawnEnemy);
        enemyThread.start();
    }

    public void spawnEnemy() {
        enemiesArrayList.add((new Table(50, 50, gameManager)));

        boolean canSpawnEnemy = true;
        while (canSpawnEnemy) {
            int x = (new Random()).nextInt(0, Game.WIDTH) - Game.TILE_SIZE;
            int y = (new Random()).nextInt(0, Game.HEIGHT) - Game.TILE_SIZE;
            enemiesArrayList.add(new Chair(playerTracked, x, y));
            enemiesArrayList.add((new Table(x, y, gameManager)));

            if (!enemiesArrayList.isEmpty()) {
                enemiesArrayList.add((new Table(x, y, gameManager)));
                enemiesArrayList.add((new Chair(playerTracked, x, y)));
            }
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void update() {
        for (Enemy enemy : enemiesArrayList) {
            if (enemy != null) {
                // update the enemies
                enemy.update();

            }
        }
        // check if the enemies must be died
        enemiesArrayList.removeIf(enemy -> enemy.health <= 0);
    }

    public void draw(Graphics2D g2) {
        for (Enemy enemy : enemiesArrayList) {
            if (enemy != null) {
                enemy.draw(g2);
            }
        }
    }
}
