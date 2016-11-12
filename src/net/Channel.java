package net;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketException;

public class Channel {
    public static void sendResponse(Response response, Socket socket){
        try {
            OutputStream out = socket.getOutputStream();
            response.writeTo(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void sendRequest(Request request, Socket socket) throws SocketException{
        OutputStream out = null;
        try {
            out = socket.getOutputStream();
        }catch (SocketException e){
            throw e;
        }catch (IOException e) {
            e.printStackTrace();
        }
        request.writeTo(out);
    }

    public static Response receiveResponse(Socket socket){
        Response response = null;
        try {
            InputStream in = socket.getInputStream();
            response.readFrom(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    public static Request receiveRequest(Socket socket){
        Request request = null;
        try {
            InputStream in = socket.getInputStream();
            request.readFrom(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return request;
    }
}
