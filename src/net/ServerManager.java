package net;

import jswing.Ponto;

import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created by Zerbs on 12.11.2016.
 */
public class ServerManager {
    private static volatile Server server = null;
    public static void main(String[] args) {
        server = new Server(6666);
        server.start();
    }
}
