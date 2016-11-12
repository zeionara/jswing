package net;

/**
 * Created by Zerbs on 12.11.2016.
 */
public class Tester {
    public static void main(String[] args) {
        Server server = new Server("127.0.0.1",6666);
        server.start();

        Client client = new Client("127.0.0.1",6668);
        client.send(new byte[]{2,2,3},server.getAddress(),server.getPort());

        Client client2 = new Client("127.0.0.1",6669);
        client.send(new byte[]{13,2,3},server.getAddress(),server.getPort());
        server.stop();
    }
}
