package xyz.necrozma.sc.communication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.util.Collections;

import com.google.common.eventbus.EventBus;
import nl.pvdberg.pnet.client.Client;
import nl.pvdberg.pnet.client.util.PlainClient;
import nl.pvdberg.pnet.event.PNetListener;
import nl.pvdberg.pnet.packet.Packet;
import nl.pvdberg.pnet.packet.PacketBuilder;
import nl.pvdberg.pnet.server.Server;
import nl.pvdberg.pnet.server.util.PlainServer;
import org.java_websocket.WebSocket;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;






public class WebsocketServer extends WebSocketServer {

    private static final Logger logger = LogManager.getLogger(WebsocketServer.class);

    private static final int PORT = 80;

    private static Client client;

    static WebsocketServer instance;

    public WebsocketServer(int port) throws UnknownHostException {
        super(new InetSocketAddress(port));
    }

    public WebsocketServer(InetSocketAddress address) {
        super(address);
    }

    public WebsocketServer(int port, Draft_6455 draft) {
        super(new InetSocketAddress(port), Collections.singletonList(draft));
    }

    @Override
    public void onOpen(WebSocket conn, ClientHandshake handshake) {
        conn.send("Welcome to the server!"); //This method sends a message to the new client
        broadcast("new connection: " + handshake
                .getResourceDescriptor()); //This method sends a message to all clients connected
        logger.info(conn.getRemoteSocketAddress().getAddress().getHostAddress() + " entered the room!");
    }

    @Override
    public void onClose(WebSocket conn, int code, String reason, boolean remote) {
        broadcast(conn + " has left the room!");
        logger.info(conn + " has left the room!");
    }

    @Override
    public void onMessage(WebSocket conn, String message) {
        broadcast(message);
        logger.info(conn.getRemoteSocketAddress() + ": " + message);

    }

    @Override
    public void onMessage(WebSocket conn, ByteBuffer message) {
        //broadcast(message.array());
        //logger.info("received ByteBuffer from " + conn.getRemoteSocketAddress());


    }


    public static void main(String[] args) throws InterruptedException, IOException {
        int port = 8887; // 843 flash policy port
        try {
            port = Integer.parseInt(args[0]);
        } catch (Exception ex) {
        }
        instance = new WebsocketServer(port);
        instance.start();

        client = new PlainClient();
        client.setClientListener(new PNetListener()
        {
            @Override
            public void onConnect(final Client c)
            {
                logger.info("Connected to server.");
            }

            @Override
            public void onDisconnect(final Client c)
            {
                logger.info("Disconnected from server.");
            }

            @Override
            public void onReceive(final Packet p, final Client c) throws IOException
            {
                logger.info("Received packet.");
            }
        });

        try {
            client.connect("127.0.0.1", 8888);
            Packet packet = new PacketBuilder(Packet.PacketType.Request)
                    .withInt(99)
                    .withString("abc")
                    .withBoolean(true)
                    .build();
            client.send(packet);
        } catch (Exception e) {
            logger.error("Failed to connect to server.", e);
            throw new RuntimeException(e);
        }


        BufferedReader sysin = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            String in = sysin.readLine();
            instance.broadcast(in);
            if (in.equals("exit")) {
                instance.stop(1000);
                break;
            }
        }
    }

    @Override
    public void onError(WebSocket conn, Exception ex) {
        logger.error("An error occurred on connection " + conn, ex);
        if (conn != null) {
            // some errors like port binding failed may not be assignable to a specific websocket
        }
    }

    @Override
    public void onStart() {
        logger.info("Websocket server started on port " + getPort());
        setConnectionLostTimeout(0);
        setConnectionLostTimeout(100);
    }

    public void stop() {
        if (instance != null) {
            stop();

            client.close();
        }
    }
}


