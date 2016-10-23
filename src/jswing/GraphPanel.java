package jswing;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Zerbs on 23.10.2016.
 */
public class GraphPanel extends JPanel {
    private Graphics gContext;

    public void paint(Graphics g){
        //Background
        g.setColor(new Color(0x48CC5E));
        g.fillRect(0,0,200,200);

        //Rectangle
        g.setColor(new Color(0x2983CC));
        g.drawRect(100,100,-60,60);

        //Triangle
        int[] txs = {100,130,100};
        int[] tys = {100,100,130};
        Polygon triangle = new Polygon(txs,tys,3);
        g.fillPolygon(triangle);

        //Segment of circle
        g.fillArc(40,40,120,120,0,90);

        //Coordinates
        g.setColor(Color.black);
        g.drawLine(100,180,100,20);
        g.drawLine(100,20,105,25);
        g.drawLine(100,20,95,25);
        g.drawString("y",110,20);

        g.drawLine(180,100,20,100);
        g.drawLine(180,100,175,95);
        g.drawLine(180,100,175,105);
        g.drawString("x",170,90);

        //Tags
        g.drawLine(160,98,160,102);
        g.drawString("R",155,95);

        g.drawLine(130,98,130,102);
        g.drawString("R/2",120,95);

        g.drawLine(40,98,40,102);
        g.drawString("-R",30,95);

        g.drawLine(70,98,70,102);
        g.drawString("-R/2",60,95);

        //

        g.drawLine(98,160,102,160);
        g.drawString("-R",105,165);

        g.drawLine(98,130,102,130);
        g.drawString("-R/2",105,135);

        g.drawLine(98,70,102,70);
        g.drawString("R/2",105,75);

        g.drawLine(98,40,102,40);
        g.drawString("R",105,45);



        //g.drawLine(180,100,100,20);
        //g.drawLine(100,180,100,20);

        g.drawLine(0,0,200,0);
        g.drawLine(0,0,0,200);
        g.drawLine(200,0,200,200);
        g.drawLine(0,200,200,200);

    }

    public void showPoint(double x, double y, Graphics g){
        if ((x >= 0) && (x <= 200) && (y>=0) && (y<=200)){
            g.fillOval((int)x,(int)y,5,5);
        }
    }
}
