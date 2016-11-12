package net;

import jswing.Lab4;
import jswing.Ponto;

import java.io.IOException;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Set;

public class PontoCheckingSuperstructure {
    Lab4 lab4;
    Thread checking;
    Set<Ponto> pontos;
    Socket socket;
    SocketAddress socketAddress;

    public PontoCheckingSuperstructure(Set<Ponto> pontos, SocketAddress socketAddress, Lab4 lab4){
        this.pontos = pontos;
        this.socketAddress = socketAddress;
        this.socket = new Socket();
        this.lab4 = lab4;

        checking = new Thread(() -> circuloInferni());
        checking.start();
    }

    private void circuloInferni(){
        while(true){
            pontos.stream().filter((Ponto p) -> !p.isChecked()).forEach((Ponto p) -> {
                Request request;
                request = new Request(p.getX(),p.getY(), lab4.getR());
                boolean sent = false;
                do{
                    try {
                        Channel.sendRequest(request,socket);
                        sent = true;
                    } catch (SocketException e) {
                        tryConnect();
                    }
                    Response response = Channel.receiveResponse(socket);
                }while(!sent);
            });
        }// while
    }

    private void tryConnect(){
        assert(!socket.isConnected());
        boolean connected = false;
        boolean firstTry = true;
        do{
            if (!firstTry){
                try{
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                firstTry = false;
            }
            socket = new Socket();
            try {
                socket.connect(socketAddress,1000);
                connected = true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        } while(!connected);
    }
}
