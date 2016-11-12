package net;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by Zerbs on 12.11.2016.
 */
public class ClientSession implements Runnable{
    private Socket socket;
    private InputStream in;
    private OutputStream out;

    public ClientSession(Socket socket) throws IOException{
        this.socket = socket;
        in = socket.getInputStream();
        out = socket.getOutputStream();
    }

    @Override
    public void run() {
        Request request;
        while(true) {
            request = Request.toRequest(in);
            if (request == null){
                continue;
            }
            new ClientProcess(socket,request);
        }
    }
}
