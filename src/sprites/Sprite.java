package sprites;

import window.Game;

import java.awt.*;

public abstract class Sprite {
    public int health, shield, positionX, positionY, speed;

    public Color color; // TODO: this code is only a test, this will be an image, no a color

    public Game gameManger;

    public Rectangle spriteRectangle = new Rectangle(positionX, positionY, Game.TILE_SIZE, Game.TILE_SIZE);

    public abstract void update();

    public abstract void draw(Graphics2D g2);
}
