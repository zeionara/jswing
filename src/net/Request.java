package net;

import java.io.*;

public class Request {


    public static Request toRequest(InputStream in){
        return new Request(0.0,0.0,0.0);
    }

    private double X;
    private double Y;
    private double R;
    private int hash;

    public Request(double x, double y, double r){
        X = x;
        Y = y;
        R = r;
        hash = (int)(x+y+r);
    }

    public void writeTo(OutputStream out){
        try {
            ObjectOutputStream serializer = new ObjectOutputStream(out);
            serializer.writeObject(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Request readFrom(InputStream in){
        Request request = null;
        try {
            ObjectInputStream deserializer = new ObjectInputStream(in);
            request = (Request)deserializer.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return request;
    }

    public double getX(){
        return X;
    }

    public double getY(){
        return Y;
    }

    public double getR(){
        return R;
    }

    public int getHash(){
        return hash;
    }

}
