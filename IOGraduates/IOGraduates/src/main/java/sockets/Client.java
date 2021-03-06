package sockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {

        try {
            Socket socket = new Socket("127.0.0.1", Server.PORT);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            Scanner scanner = new Scanner(System.in);

            while (true) {

                String msg = scanner.nextLine();
                out.println(msg);
                String serverMessage = in.readLine();
                System.out.println("Mesaj de la server: "+serverMessage);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
