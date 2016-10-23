package jswing;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by Zerbs on 22.10.2016.
 */
public class Lab4 extends JFrame{
    private JComboBox<Double> xComboBox;
    private ArrayList<JCheckBox> yCheckBoxes;
    private JSpinner rSpinner;
    private JTextField pTextField;
    private Double R;
    GraphPanel theGraphPanel;

    private ArrayList<Point> points = new ArrayList<>();

    public Lab4()
    {

        // Initialization
        super();
        R = 10d;
        setSize(720, 480);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setTitle("Fourth Lab");
        setResizable(false);

        Box theBox = Box.createVerticalBox();

        JPanel thePanel = new JPanel();
        thePanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        JPanel theSecondPanel = new JPanel();
        theSecondPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        JPanel theThirdPanel = new JPanel();
        theThirdPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        JPanel theFourthPanel = new JPanel();
        theFourthPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        JPanel thePanel2 = new JPanel();
        thePanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        //UI components for x
        JLabel xLabel = new JLabel("Please, select x :");
        thePanel.add(xLabel);

        xComboBox = getComboBoxForX();
        thePanel.add(xComboBox);
        theBox.add(thePanel);

        //UI components for y
        JLabel yLabel = new JLabel("Please, select y :");
        theSecondPanel.add(yLabel);
        yCheckBoxes = getCheckBoxesForY();
        for (JCheckBox checkBox : yCheckBoxes){
            theSecondPanel.add(checkBox);
        }
        theBox.add(theSecondPanel);

        //UI components for R
        JLabel rLabel = new JLabel("Please, select R :");
        rSpinner = getSpinnerForR();
        theThirdPanel.add(rLabel);
        theThirdPanel.add(rSpinner);
        theBox.add(theThirdPanel);
        rSpinner.addChangeListener(new SpinnerChangeListener());

        //UI components for point coordinates
        JLabel pLabel = new JLabel("Selected point :");
        pTextField = getTextFieldForP();
        theFourthPanel.add(pLabel);
        theFourthPanel.add(pTextField);
        theBox.add(theFourthPanel);

        //UI components for graph
        theGraphPanel = new GraphPanel();
        theGraphPanel.setPreferredSize(new Dimension(400,200));
        theBox.add(theGraphPanel);


        theGraphPanel.addMouseListener(new GraphPanelMouseListener());

        add(theBox);
        //add(theSecondPanel);
        setVisible(true);
    }

    private javax.swing.JComboBox<Double> getComboBoxForX() {
        Double[] xs = {-4d, -3d, -2d, 0d, 1d, 2d, 3d, 4d};
        JComboBox<Double> jCB = new javax.swing.JComboBox<>(xs);
        jCB.setFont(new Font("Calibri",Font.PLAIN,12));
        jCB.setPreferredSize(new Dimension(100,20));
        return jCB;
    }

    private ArrayList<JCheckBox> getCheckBoxesForY() {
        ArrayList<JCheckBox> jCBs = new ArrayList<>();
        JCheckBox jCB;
        Double[] ys = {-4d, -3d, -2d, 0d, 1d, 2d, 3d, 4d};
        for (Double value : ys){
            jCB = new JCheckBox(value.toString());
            jCBs.add(jCB);
            jCB.addActionListener(new CheckBoxListener());
        }
        return jCBs;
    }

    private JSpinner getSpinnerForR() {
        JSpinner rSpinner = new JSpinner();
        rSpinner.setValue(10);
        rSpinner.setPreferredSize(new Dimension(100,20));
        return rSpinner;
    }

    private JTextField getTextFieldForP() {
        JTextField pTextField = new JTextField();
        pTextField.setEditable(false);
        pTextField.setText("None");
        pTextField.setPreferredSize(new Dimension(300,20));
        return pTextField;
    }

    public static void main(String[] args) {
        new Lab4();
    }

    private class GraphPanelMouseListener implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            ((GraphPanel)e.getSource()).showPoint(e.getX(),e.getY(),((GraphPanel)e.getSource()).getGraphics());
            points.add(new Point((e.getX()-100)*R/60,(-e.getY()+100)*R/60));
            pTextField.setText("x = "+(e.getX()-100)*R/60+" ; y = "+(-e.getY()+100)*R/60);
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

    }

    private class SpinnerChangeListener implements ChangeListener{

        @Override
        public void stateChanged(ChangeEvent e) {
            System.out.println("Changed");
            theGraphPanel.paint(theGraphPanel.getGraphics());
            R = ((Integer)((JSpinner)e.getSource()).getModel().getValue()).doubleValue();

            for (Point point : points){
                theGraphPanel.showPoint((point.getX()*60)/R+100,(-point.getY()*60)/R+100,theGraphPanel.getGraphics());
            }
        }
    }

    private class CheckBoxListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            //System.out.println(((JCheckBox)e.getSource()).getText());
            //System.out.println(((JCheckBox)e.getSource()).isSelected());
            double x = Double.parseDouble(xComboBox.getModel().getSelectedItem().toString());
            double y = Double.parseDouble(((JCheckBox)e.getSource()).getText());

            if (((JCheckBox)e.getSource()).isSelected()){
                theGraphPanel.showPoint((x*60)/R+100,(-y*60)/R+100,theGraphPanel.getGraphics());
                points.add(new Point(x,y));
                pTextField.setText("x = "+x+" ; y = "+y);

            }
        }
    }

    private class Point {
        private double x;
        private double y;
        public Point(double x, double y){
            this.x = x;
            this.y = y;
        }
        public double getX(){
            return x;
        }
        public double getY(){
            return y;
        }
    }
}
