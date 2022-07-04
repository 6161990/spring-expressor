package pattern.facade;

public class Ftp {

    private String host;
    private int port;
    private String path;

    public Ftp(String host, int port, String path) {
        this.host = host;
        this.port = port;
        this.path = path;
    }


    public void connect() {
        System.out.println("Ftp Host : " + host + " Port : " + port + "로 연결 합니다.");
    }

    public void moveDirectory() {
        System.out.println("Ftp Host : " + path + "로 이동합니다.");
    }

    public void disConnect() {
        System.out.println("Ftp 연결을 종료합니다.");
    }
}
