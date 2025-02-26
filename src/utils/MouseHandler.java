package utils;


import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseHandler implements MouseListener {
    private final Point pointMouse = new Point();
    public boolean isClicked;

    @Override
    public void mouseClicked(MouseEvent e) {
        isClicked = true;
        pointMouse.setLocation(e.getX(), e.getY());
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public Point getPointMouse() {
        return pointMouse;
    }
}
