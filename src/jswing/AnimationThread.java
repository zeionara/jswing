package jswing;

import javax.swing.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Zerbs on 25.10.2016.
 */
public class AnimationThread extends Thread{

    private int step = 51;
    private int delay = 20;

    private GraphPanel animatedGraphPanel;

    public void setAnimatedGraphPanel(GraphPanel graphPanel){
        animatedGraphPanel = graphPanel;
    }

    private ArrayList<Point> points;

    public void setPoints(ArrayList<Point> points){
        this.points = points;
    }

    private double R;

    public void setR(double R){
        this.R = R;
    }

    private GeneralSilhouette gsh;

    public void setGeneralSilhouette(GeneralSilhouette gsh){
        this.gsh = gsh;
    }

    public AnimationThread(GraphPanel graphPanel, ArrayList<Point> points, double R, GeneralSilhouette gsh){
        setAnimatedGraphPanel(graphPanel);
        setPoints(points);
        setR(R);
        setGeneralSilhouette(gsh);
    }

    public void run(){
        int r = 0;
        int g = 0;
        int b = 255;

        animatedGraphPanel.setAreaColor(r,g,b);
        while (r != 255){
            r+=step;
            g+=step;
            b-=step;
            animatedGraphPanel.setAreaColor(r,g,b);

            animatedGraphPanel.paint(animatedGraphPanel.getGraphics());

            for (Point point : points){
                animatedGraphPanel.showPoint((point.getX()*60)/R+100,(-point.getY()*60)/R+100,animatedGraphPanel.getGraphics(),gsh);
            }

            try{
                Thread.sleep(delay);
            } catch (InterruptedException e){

            }
        }
        while (r != 0){
            r-=step;
            g-=step;
            b+=step;
            animatedGraphPanel.setAreaColor(r,g,b);

            animatedGraphPanel.paint(animatedGraphPanel.getGraphics());

            for (Point point : points){
                animatedGraphPanel.showPoint((point.getX()*60)/R+100,(-point.getY()*60)/R+100,animatedGraphPanel.getGraphics(),gsh);
            }

            try{
                Thread.sleep(delay);
            } catch (InterruptedException e){

            }
        }
    }
}
