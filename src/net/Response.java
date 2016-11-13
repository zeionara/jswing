package net;

import java.io.*;
import java.net.SocketException;

/**
 * Created by Zerbs on 12.11.2016.
 */
public class Response implements Serializable{
    private boolean supremumIudicium;
    private double R;
    private int hash;

    public void writeTo(OutputStream out){
        try {
            ObjectOutputStream serializer = new ObjectOutputStream(out);
            serializer.writeObject(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Response readFrom(InputStream in) throws SocketException{
        Response response = null;
        try {
            ObjectInputStream deserializer = new ObjectInputStream(in);
            response = (Response)deserializer.readObject();
        } catch (SocketException e){
            System.out.println("Connection reset");
            throw e;
        }
        catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return response;
    }

    public Response(int hash, boolean supremumIudicium, double R){
        this.R = R;
        this.supremumIudicium = supremumIudicium;
        this.hash = hash;
    }

    public boolean getSupremimIudicium(){
        return supremumIudicium;
    }

}
