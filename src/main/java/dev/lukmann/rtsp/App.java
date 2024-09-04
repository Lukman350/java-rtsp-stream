package dev.lukmann.rtsp;

import dev.lukmann.rtsp.server.RtspServer;

public class App {
    public static void main(String[] args) {
        RtspServer rtspServer = new RtspServer();
        rtspServer.start();
    }
}
