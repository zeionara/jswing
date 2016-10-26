package jswing;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Set;

public class GraphPanel extends JPanel {
    public static final int BLUE = 0x0000FF;
    public static final int SIZE_OF_GRAPH = 200;
    public static final int SIZE_OF_POINT = 2;
    public static final Color INNER_POINT_COLOR = Color.green;
    public static final Color OUTER_POINT_COLOR = Color.red;

    private Color areaColor = new Color(BLUE);

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

        //Circle
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

        //Border
        g.drawLine(0,0,200,0);
        g.drawLine(0,0,0,200);
        g.drawLine(200,0,200,200);
        g.drawLine(0,200,200,200);

    }

    public void showPonto(Ponto p, GeneralSilhouette gsh){
        if (isPontoOnGraph(p,gsh.getR())){
            addPontoToGraph(p,getGraphics(),gsh);
        }
    }

    public void showPontoAnimated(Ponto p, Set<Ponto> pontos, GeneralSilhouette gsh){
        if (isPontoOnGraph(p,gsh.getR())){
            addPontoToGraph(p,getGraphics(),gsh);
            // animation
            new AnimationThread(this,pontos,gsh.getR(),gsh).start();
        }
    }

    private void addPontoToGraph(Ponto p, Graphics g, GeneralSilhouette gsh){
        if(gsh.checkPonto(p)){
            g.setColor(INNER_POINT_COLOR);
        } else {
            g.setColor(OUTER_POINT_COLOR);
        }

        g.fillOval((int)p.getGraphX(gsh.getR())-SIZE_OF_POINT,(int)p.getGraphY(gsh.getR())-SIZE_OF_POINT,SIZE_OF_POINT*2,SIZE_OF_POINT*2);
    }

    private boolean isPontoOnGraph(Ponto p, double R){
        return (p.getGraphX(R) >= 0) &&
                (p.getGraphX(R) <= SIZE_OF_GRAPH) &&
                (p.getGraphY(R)>=0) &&
                (p.getGraphY(R)<= SIZE_OF_GRAPH);
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
}
