package net;

import java.io.IOException;
import java.net.*;

public class Server {
    private final int port;
    private ServerSocket serverSocket;
    private Socket socket;
    private Thread listenThread;

    private boolean running = false;

    public Server(String addressName, int port) {
        this.port = port;
    }

    public void start(){
        try {
            serverSocket = new ServerSocket(port);

            running = true;
            listenThread = new Thread(() -> listen());
            listenThread.start();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
    }

    public void stop(){
        running = false;
        closeServerSocket();
    }

    private void closeServerSocket(){
        try {
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void listen(){
        while(running) {
            Socket socket;
            try {
                socket = serverSocket.accept();
                new Thread(new ClientSession(socket)).start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public int getPort(){
        return port;
    }
}
