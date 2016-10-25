package jswing;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import static jswing.Lab4.getRealX;
import static jswing.Lab4.getRealY;

/**
 * Created by Zerbs on 23.10.2016.
 */
public class GraphPanel extends JPanel {
    private Graphics gContext;
    public static final int BLUE = 0x0000FF;
    public static final int YELLOW = 0xFFFF00;

    private Color areaColor = new Color(BLUE);

    public Color getAreaColor(){
        return areaColor;
    }

    private int correctValue(int value, int lowbound, int highbound){
        if (value < lowbound){
            return lowbound;
        }
        if (value > highbound){
             return highbound;
        }
        return value;
    }
    public void setAreaColor(int r, int g, int b){
        areaColor = new Color(correctValue(r,0,255), correctValue(g,0,255), correctValue(b,0,255));
    }


    public void paint(Graphics g){
        //Background
        g.setColor(new Color(0x48CC5E));

        g.fillRect(0,0,200,200);

        //Rectangle
        g.setColor(areaColor);
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

    public void showPoint(double x, double y, Graphics g, GeneralSilhouette gsh){
        if ((x >= 0) && (x <= 200) && (y>=0) && (y<=200)){
            if(gsh.checkPonto(new Ponto((float)getRealX(x,gsh.getR()),(float)getRealY(y,gsh.getR())))){
                g.setColor(new Color(0x00FF00));
            } else {
                g.setColor(new Color(0xFF0000));
            }

            g.fillOval((int)x-2,(int)y-2,4,4);
        }
    }

    public void showPointAnimated(double x, double y, Graphics g, ArrayList<Point> points, double R,GeneralSilhouette gsh){
        if ((x >= 0) && (x <= 200) && (y>=0) && (y<=200)){

            if(gsh.checkPonto(new Ponto((float)getRealX(x,R),(float)getRealY(y,R)))){
                g.setColor(new Color(0x00FF00));
            } else {
                g.setColor(new Color(0xFF0000));
            }

            g.fillOval((int)x-2,(int)y-2,4,4);
        }
        // animation
        new AnimationThread(this,points,R,gsh).start();
    }
}
