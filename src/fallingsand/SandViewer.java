/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fallingsand;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author efren
 */
public class SandViewer extends JPanel implements ActionListener, MouseListener, MouseMotionListener {

    private final Timer timer = new Timer(1000 / 144, this);
    private final int pixelSize;
    private boolean running = true;
    private int[][] map;
    private int drawingRadius;
    MapUpdater mapUpdater;

    public SandViewer(int[][] map, int pixelSize,int drawingRadius, boolean running) {
        this.pixelSize = pixelSize;
        this.running = running;
        this.map = map;
        this.drawingRadius=drawingRadius;
        mapUpdater = new MapUpdater(map, pixelSize, drawingRadius, timer, running);
        timer.start();
        this.setFocusable(true);
        addMouseListener(this);
        addMouseMotionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (running) {

            mapUpdater.actionPerformed(e);
            repaint();

        } else {
            timer.stop();
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        for (int line = map.length - 1; line >= 0; line--) {

            for (int pixel = 0; pixel < map[line].length; pixel++) {
                Color color;
                switch (map[line][pixel]) {
                    case 0:
                        color = Color.BLACK;
                        break;
                    case 1:
                        color = Color.YELLOW;
                        break;
                    case 2:
                        color = Color.BLUE;
                        break;
                    case 3:
                        color = Color.GRAY;
                        break;
                    case 4:
                        color = Color.MAGENTA;
                        break;
                    default:
                        color = Color.WHITE;
                        break;
                }
                drawPixel(g, pixel, map.length - 1 - line, pixelSize, color);

            }

        }

    }

    private static void drawPixel(Graphics g, int x, int y, int pixelSize, Color color) {
        g.setColor(color);
        g.fillRect(x * pixelSize, y * pixelSize, pixelSize, pixelSize);
    }

    @Override
    public void update(Graphics g) {
        paint(g);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        mapUpdater.mousePressed(e);

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        mapUpdater.mouseReleased(e);

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        mapUpdater.mouseMoved(e);

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        //System.out.println((map.length - 1 - e.getYOnScreen() / pixelSize) + ", " + (e.getXOnScreen() / pixelSize));
        mapUpdater.mouseMoved(e);
    }

}
