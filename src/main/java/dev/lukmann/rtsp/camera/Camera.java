package dev.lukmann.rtsp.camera;

public class Camera {
    private String rtspUrl;
    private String host;
    private String port;
    private String endpoint;
    private String username;
    private String password;

    public Camera(String host, String port, String endpoint) {
        this.host = host;
        this.port = port;
        this.endpoint = endpoint;
        this.username = "";
        this.password = "";
        this.rtspUrl = String.format("rtsp://%s:%s/%s", this.getHost(), this.getPort(), this.getEndpoint());
    }

    public String getRtspUrl() {
        return rtspUrl;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public void setRtspUrl(String rtspUrl) {
        this.rtspUrl = rtspUrl;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
