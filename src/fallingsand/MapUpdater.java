/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fallingsand;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.Timer;

/**
 *
 * @author efren
 */
public class MapUpdater implements ActionListener, MouseListener, MouseMotionListener {

    private final Timer timer;
    private final boolean running;
    private boolean drawing;
    private final int[][] map;
    private int[] drawingCoords;
    private int drawingWith;
    private final int drawingRadius;
    private final int pixelSize;

    public MapUpdater(int[][] map, int pixelSize, int drawingRadius, Timer timer, boolean running) {
        this.map = map;
        this.pixelSize = pixelSize;
        this.running = running;
        this.timer = timer;
        this.drawingRadius = drawingRadius;
        drawingCoords=new int[]{0,0};
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (drawing) {
            for (int y = -drawingRadius + drawingCoords[0]; y < drawingRadius + drawingCoords[0] + 1; y++) {
                for (int x = -drawingRadius + drawingCoords[1]; x < drawingRadius + drawingCoords[1] + 1; x++) {
                    System.out.println("x: "+x+"y: "+y);
                    if (y < map.length && y > 0 && x < map[0].length && x > 0) {
                        map[y][x] = drawingWith;
                    }
                }
            }
        }
        for (int line = 0; line < map.length; line++) {

            for (int pixel = 0; pixel < map[line].length; pixel++) {
                int oldpixel;
                switch (map[line][pixel]) {
                    case 0:
                        break;
                    case 1:
                        if (line > 0 && map[line - 1][pixel] == 0) {
                            oldpixel = map[line - 1][pixel];
                            map[line - 1][pixel] = map[line][pixel];
                            map[line][pixel] = oldpixel;
                        } else if (line > 0) {
                            try {
                                switch ((int) (Math.random() * 2)) {
                                    case 0:
                                        if (pixel + 1 < map[line].length && map[line - 1][pixel + 1] == 0 && map[line][pixel + 1] == 0) {

                                            oldpixel = map[line - 1][pixel + 1];
                                            map[line - 1][pixel + 1] = map[line][pixel];
                                            map[line][pixel] = oldpixel;

                                        } else if (pixel - 1 > 0 && map[line - 1][pixel - 1] == 0 && map[line][pixel - 1] == 0) {
                                            oldpixel = map[line - 1][pixel - 1];
                                            map[line - 1][pixel - 1] = map[line][pixel];
                                            map[line][pixel] = oldpixel;
                                        }
                                        break;
                                    case 1:
                                        if (pixel - 1 > 0 && map[line - 1][pixel - 1] == 0 && map[line][pixel - 1] == 0) {
                                            oldpixel = map[line - 1][pixel - 1];
                                            map[line - 1][pixel - 1] = map[line][pixel];
                                            map[line][pixel] = oldpixel;
                                        } else if (pixel + 1 < map[line].length && map[line - 1][pixel + 1] == 0 && map[line][pixel + 1] == 0) {
                                            oldpixel = map[line - 1][pixel + 1];
                                            map[line - 1][pixel + 1] = map[line][pixel];
                                            map[line][pixel] = oldpixel;
                                            break;
                                        }
                                }
                            } catch (ArrayIndexOutOfBoundsException exception) {
                                System.out.println(exception.getLocalizedMessage());

                            }
                        }
                        break;
                    case 2:
                        if (line > 0 && map[line - 1][pixel] == 0) {
                            oldpixel = map[line - 1][pixel];
                            map[line - 1][pixel] = map[line][pixel];
                            map[line][pixel] = oldpixel;
                        } else if (line > 0) {
                            try {
                                switch ((int) (Math.random() * 2)) {
                                    case 0:
                                        if (pixel + 1 < map[line].length && map[line - 1][pixel + 1] == 0 && map[line][pixel + 1] == 0) {

                                            oldpixel = map[line - 1][pixel + 1];
                                            map[line - 1][pixel + 1] = map[line][pixel];
                                            map[line][pixel] = oldpixel;

                                        } else if (pixel - 1 > 0 && map[line - 1][pixel - 1] == 0 && map[line][pixel - 1] == 0) {
                                            oldpixel = map[line - 1][pixel - 1];
                                            map[line - 1][pixel - 1] = map[line][pixel];
                                            map[line][pixel] = oldpixel;
                                        } else if (pixel + 1 < map[line].length && map[line][pixel + 1] == 0) {
                                            oldpixel = map[line][pixel + 1];
                                            map[line][pixel + 1] = map[line][pixel];
                                            map[line][pixel] = oldpixel;
                                        }
                                        break;
                                    case 1:
                                        if (pixel - 1 > 0 && map[line - 1][pixel - 1] == 0 && map[line][pixel - 1] == 0) {
                                            oldpixel = map[line - 1][pixel - 1];
                                            map[line - 1][pixel - 1] = map[line][pixel];
                                            map[line][pixel] = oldpixel;
                                        } else if (pixel + 1 < map[line].length && map[line - 1][pixel + 1] == 0 && map[line][pixel + 1] == 0) {
                                            oldpixel = map[line - 1][pixel + 1];
                                            map[line - 1][pixel + 1] = map[line][pixel];
                                            map[line][pixel] = oldpixel;
                                        } else if (pixel > 0 && map[line][pixel - 1] == 0) {
                                            oldpixel = map[line][pixel - 1];
                                            map[line][pixel - 1] = map[line][pixel];
                                            map[line][pixel] = oldpixel;
                                        }
                                        break;
                                }
                            } catch (ArrayIndexOutOfBoundsException exception) {
                                System.out.println(exception.getLocalizedMessage());

                            }
                        }
                        break;

                }

            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mousePressed(MouseEvent e) {
        drawing = true;
        switch (e.getButton()) {
            case 1:
                drawingWith = 1;
                break;
            case 2:
                drawingWith = 2;
                break;
            case 3:
                drawingWith = 3;
                break;
                
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        drawing = false;

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        drawingCoords = new int[]{map.length - 1 - e.getYOnScreen() / pixelSize, e.getXOnScreen() / pixelSize};
    }

}
