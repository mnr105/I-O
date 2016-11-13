package nio;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.*;

public class Server {

    public static final int PORT = 8090;
    public static final String HOST = "localhost";
    private Selector selector;
    private Map<SocketChannel, List> dataMapper;
    private InetSocketAddress listenAddress;

    public static void main(String[] args) throws Exception {
        new Server(HOST, PORT).startServer();
    }
    //private Map<Integer,String> users = new HashMap();

    public Server(String address, int port) throws IOException {
        listenAddress = new InetSocketAddress(address, port);
        dataMapper = new HashMap<SocketChannel, List>();
    }

    /**
     * Create server channel
     *
     * @throws IOException
     */
    private void startServer() throws IOException {
        selector = Selector.open();
        ServerSocketChannel serverChannel = ServerSocketChannel.open();
        serverChannel.configureBlocking(false);
        // retrieve server socket and bind to port
        serverChannel.socket().bind(listenAddress);
        serverChannel.register(selector, SelectionKey.OP_ACCEPT);

        System.out.println("Server started.");

        while (true) {

            // wait for events
            selector.select();

            // work on selected keys
            Iterator keys = selector.selectedKeys().iterator();
            while (keys.hasNext()) {
                SelectionKey key = (SelectionKey) keys.next();

                // this is necessary to prevent the same key from coming up again the next time around
                keys.remove();

                if (!key.isValid()) {
                    continue;
                }

                if (key.isAcceptable()) {
                    accept(key);

                } else if (key.isReadable()) {
                    read(key);
                }
            }
        }
    }

    /**
     * Accept a connection made to this channel's socket
     *
     * @param key
     * @throws IOException
     */

    private void accept(SelectionKey key) throws IOException {
        ServerSocketChannel serverChannel = (ServerSocketChannel) key.channel();
        SocketChannel channel = serverChannel.accept();
        channel.configureBlocking(false);
        Socket socket = channel.socket();
        SocketAddress remoteSocketAddress = socket.getRemoteSocketAddress();
        System.out.println("Connected to: " + remoteSocketAddress
                + " On port: " + socket.getPort());
        // register channel with selector for further IO
        dataMapper.put(channel, new ArrayList());

        channel.register(selector, SelectionKey.OP_READ);
    }

    private void read(SelectionKey key) throws IOException {
        ByteBuffer buffer = ByteBuffer.allocate(2048);
        SocketChannel channel = (SocketChannel) key.channel();
        int numRead = -1;
        numRead = channel.read(buffer);

        if (numRead == -1) {
            dataMapper.remove(channel);
            Socket socket = channel.socket();
            SocketAddress remoteSocketAddress = socket.getRemoteSocketAddress();
            System.out.println("Connection closed by client: " + remoteSocketAddress);
            channel.close();
            key.cancel();
            return;
        }
        byte[] data = new byte[numRead];
        System.arraycopy(buffer.array(), 0, data, 0, numRead);

        // asa obtii inputul de la client:
        /* String z = new String(data).trim();
        System.out.println("Data.tostring: " + z);*/
        // verifica userul:

        if (check(channel) == true &&
                checkname(new String(data).trim()) == true) {
            // daca e nou: adauga user-ul
            dataMapper.get(channel).add(new String(data).trim());
            // Trimite inapoi la client informatia ca userul a fost ok:
            // facem un string true
            String sendmsg = "true";
            boolean raspSpreClent = true;
            //facem un vector de biti dataz caruia ii dam dimensiunea lu sendmsg
            byte[] dataz = new byte[sendmsg.length()];
            ByteBuffer sendbuffer = ByteBuffer.wrap(sendmsg.getBytes());
            channel.write(sendbuffer);
            System.out.println("1");

        } else if (checkname(new String(data)) == false) {
            // nu adauga userul
            String sendmsg = "false";
            byte[] dataz = new byte[sendmsg.length()];
            ByteBuffer sendbuffer;
            sendbuffer = ByteBuffer.wrap(sendmsg.getBytes());
            //System.arraycopy(sendbuffer.array(), 0, dataz, 0, numRead);
            channel.write(sendbuffer);
            System.out.println("2");
        }

        if (dataMapper.get(channel).size() != 0)
            System.out.println("User " + dataMapper.get(channel).get(0) +
                    " says: " + new String(data));
    }

    private boolean checkname(String name) {
        for (HashMap.Entry<SocketChannel, List> mp : dataMapper.entrySet()) {
            if (mp.getValue().size() != 0) {
                System.out.println(mp.getValue().get(0).toString().trim()
                        + "*******" + name);
            }
            if ((mp.getValue().size() != 0) &&
                    (mp.getValue().get(0).toString().trim().equals(name))) {
                System.out.println(mp.getValue().get(0) + "///////");
                return false;
            }
        }
        return true;
    }

    private boolean check(SocketChannel channel) {
        System.out.println("CHECK");
        for (HashMap.Entry<SocketChannel, List> mp : dataMapper.entrySet()) {
            if (mp.getKey() == channel && mp.getValue().size() == 0) {
                //daca elem de pe poz 0 in arraylist nu e null
                // si arrayul e gol(trebuie adaugat userul)
                return true;
            }
        }
        return false;
    }
}