package dev.lukmann.rtsp.server;

import dev.lukmann.rtsp.camera.Camera;
import org.bytedeco.javacv.*;

import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

class RtspStream extends Thread {
    private Socket clientSocket;
    private FrameGrabber frameGrabber;
    private FrameRecorder frameRecorder;
    private List<Camera> cameras = new ArrayList<>();

    public RtspStream(Socket clientSocket) {
        this.clientSocket = clientSocket;

        cameras.add(new Camera("192.168.9.6", "554", "stream"));
        cameras.add(new Camera("192.168.9.9", "554", "stream"));
    }

    private List<Camera> getAllCameras() {
        return cameras;
    }

    @Override
    public void run() {
        try {
            List<Camera> allCameras = this.getAllCameras();

            int cameraIndex = (int) (Math.random() * allCameras.toArray().length);
            String cameraURL = allCameras.get(cameraIndex).getRtspUrl();

            frameGrabber = new FFmpegFrameGrabber(cameraURL);
            frameGrabber.start();

            frameRecorder = new OpenCVFrameRecorder(InetAddress.getLocalHost().getHostAddress(), 8080, RtspServer.getFPS());
            frameRecorder.start();

            while (true) {
                try {
                    Frame frame = frameGrabber.grab();
                    if (frame != null) {
                        frameRecorder.record(frame);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (frameGrabber != null) {
                    frameGrabber.stop();
                }
                if (frameRecorder != null) {
                    frameRecorder.stop();
                }
                if (clientSocket != null) {
                    clientSocket.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
