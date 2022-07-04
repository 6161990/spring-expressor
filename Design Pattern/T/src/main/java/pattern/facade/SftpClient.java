package pattern.facade;


public class SftpClient {

    private Ftp ftpProtocol;
    private Reader fileReader;
    private Writer fileWriter;

    public SftpClient(String host, int port, String path, String fileName) {
        this.ftpProtocol = new Ftp(host, port, path);
        this.fileReader = new Reader(fileName);
        this.fileWriter = new Writer(fileName);
    }

    public void connect() {
        ftpProtocol.connect();
        ftpProtocol.moveDirectory();
        fileReader.fileConnect();
        fileWriter.fileConnect();

    }

    public void write() {
        fileWriter.Write();
    }

    public void read() {
        fileReader.fileRead();
    }

    public void disConnect() {
        fileReader.fileDisconnect();
        fileWriter.fileDisconnect();
        ftpProtocol.disConnect();
    }

}
