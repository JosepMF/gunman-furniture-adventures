package window;

import javax.swing.*;

public class GameWindow extends JFrame {
    private static GameWindow gameWindow;

    private GameWindow() {
        Game game = new Game();

        this.setTitle("pvp-game");
        this.setResizable(false);
        this.add(game);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void run() {
        if (gameWindow == null) {
            gameWindow = new GameWindow();
        }
    }
}
