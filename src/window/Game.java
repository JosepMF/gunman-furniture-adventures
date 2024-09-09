package window;

import managers.BulletManager;
import managers.CollisionManager;
import managers.EnemyManager;
import sprites.player.Player;
import utils.KeyHandler;
import utils.MouseHandler;

import javax.swing.*;
import java.awt.*;

public class Game extends JPanel {
    // size of the panel
    public static final int COLS = 42, ROWS = 26;
    public static final int TILE_SIZE = 32;
    public static final int WIDTH = COLS * TILE_SIZE, HEIGHT = ROWS * TILE_SIZE;

    // FPS
    private final int FPS = 60;

    // key handler
    public KeyHandler keyHandler;

    // muse handler
    public MouseHandler mouseHandler;

    // player instance
    public Player player;


    // managers
    // bullet manager
    public BulletManager bulletManager;
    // enemy manager
    public EnemyManager enemyManager;
    // collision manager
    public CollisionManager collisionManager;


    // game thread init
    Thread gameThread;

    // game over manager
    public boolean gameOver = false;

    // score
    public int score;


    // TODO: testing the chair enemy

    Game() {
        // handlers
        //init key handler
        keyHandler = new KeyHandler();
        // init mouse handler
        mouseHandler = new MouseHandler();

        // init player instance
        player = new Player(this);

        // managers init
        // bullet manager init
        bulletManager = new BulletManager();
        // enemy manager init
        enemyManager = new EnemyManager(player, this);
        // collision manager
        collisionManager = new CollisionManager(this);


        // Panel config
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.addMouseListener(mouseHandler);
        this.setFocusable(true);
        this.setBackground(Color.BLACK);

        // game thread
        gameThread = new Thread(this::loop); // cuando pones this::funcion, haces referencia a la funcion pero no la llamas a ejecutar | when you put "this::naeFunction", you make a reference of that function but don't call it
        gameThread.start();
    }

    private void loop() {
        double drawInterval = (double) 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        long drawCount = 0;

        while (gameThread != null) {

            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (delta >= 1) {
                update();
                repaint();
                delta--;
                drawCount++;
            }

            if (timer >= 1000000000) {
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponents(g);

        Graphics2D g2 = (Graphics2D) g;

        if(gameOver) {
            g2.setColor(Color.red);
            g2.setFont(new Font("time new romance", Font.BOLD, Game.TILE_SIZE));
            g2.drawString("game over".toUpperCase(),(Game.WIDTH/2)-Game.TILE_SIZE, (Game.HEIGHT/2)-Game.TILE_SIZE);
            return;
        }

        // repainting
        g2.setColor(Color.BLACK);
        g2.fillRect(0,0,WIDTH,HEIGHT);
        // drawing functions
        player.draw(g2);

        bulletManager.draw(g2);

        enemyManager.draw(g2);

        g2.dispose();
    }

    // run the update functions
    private void update() {
        if (gameOver) return;

        player.update();
        bulletManager.update();
        enemyManager.update();
        try {
            collisionManager.setEnemyArrayListAndBulletArrayList(enemyManager.enemiesArrayList, bulletManager.bulletArrayList);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
