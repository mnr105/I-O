package nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) throws IOException, InterruptedException {
        new Client().startClient();
    }

    private String username;

    public void startClient() throws IOException, InterruptedException {

        InetSocketAddress hostAddress = new InetSocketAddress(Server.HOST, Server.PORT);
        SocketChannel client = SocketChannel.open(hostAddress);
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Type Client username: ");
            this.username = scanner.nextLine();
            //1.buffout
            ByteBuffer buffout = ByteBuffer.wrap(this.username.getBytes());
            client.write(buffout);

            //2. buffin
            ByteBuffer buffin = ByteBuffer.allocate(2048);
            client.read(buffin);
            String s = "true";
            if (new String(buffin.array()).trim().equals(s)) {
                System.out.println("Username OK!");
                break;
            }
            System.out.println("Username already in use!");
        }
        System.out.println("Client " + this.username + " started.");

        while (true) {

            String message = scanner.nextLine();

            //3. buffereioi
            ByteBuffer buffere = ByteBuffer.allocate(1024);
            buffere = ByteBuffer.wrap(message.getBytes());
            client.write(buffere);
            buffere.flip();
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}