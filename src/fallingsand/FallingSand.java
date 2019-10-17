/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fallingsand;

import java.awt.Canvas;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author efren
 */
public class FallingSand {

    public static void main(String[] args) {
        int pixelSize;
        int fieldHeight;
        int fieldWidth;
        boolean running;

        pixelSize = 3;
        fieldHeight = 225;
        fieldWidth = 400;
        running = true;

        int[][] map = new int[fieldHeight][fieldWidth];

        for (int line = map.length - 1; line >= 0; line--) {

            for (int pixel = 0; pixel < map[line].length; pixel++) {
                map[line][pixel] = 0;
                if (line < 210 && line > 205) {

                    map[line][pixel] = 2;
                }
                if (line < 100 && pixel <50 &&pixel>20) {

                    map[line][pixel] = 3;
                }
                if (line < 100 && pixel <150+line &&pixel>50+line) {

                    map[line][pixel] = 3;
                }
            }

        }

        JFrame frame = new SandWindow("Sand");
        frame.getContentPane().setSize(fieldWidth * pixelSize, fieldHeight * pixelSize);
        frame.getContentPane().setPreferredSize(new Dimension(fieldWidth * pixelSize, fieldHeight * pixelSize));

        JPanel canvas = new SandViewer(map, pixelSize,10, running);
        frame.add(canvas);
        canvas.setSize(900 * pixelSize, 900 * pixelSize);

                
        frame.pack();
        frame.setVisible(true);
    }

}
