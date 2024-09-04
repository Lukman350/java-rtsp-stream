package dev.lukmann.rtsp.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class RtspServer {
    private static final int FPS = 30;
    private static final int PORT = 554;
    private final List<RtspStream> streams;
    private ServerSocket serverSocket;

    public RtspServer() {
        streams = new ArrayList<>();
        try {
            serverSocket = new ServerSocket(PORT);
            System.out.println("RTSP server started on port " + PORT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start() {
        while (true) {
            try {
                Socket clientSocket = serverSocket.accept();

                System.out.println("Client connected from " + clientSocket.getInetAddress());


                RtspStream stream = new RtspStream(clientSocket);
                streams.add(stream);
                stream.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static int getFPS() {
        return FPS;
    }
}
