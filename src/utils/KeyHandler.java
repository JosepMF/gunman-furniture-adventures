package utils;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    public boolean upKey, leftKey, downKey, rightKey; // motion keys

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W) upKey = true;
        if (e.getKeyCode() == KeyEvent.VK_S) downKey = true;
        if (e.getKeyCode() == KeyEvent.VK_A) leftKey = true;
        if (e.getKeyCode() == KeyEvent.VK_D) rightKey = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W) upKey = false;
        if (e.getKeyCode() == KeyEvent.VK_S) downKey = false;
        if (e.getKeyCode() == KeyEvent.VK_A) leftKey = false;
        if (e.getKeyCode() == KeyEvent.VK_D) rightKey = false;
    }
}
