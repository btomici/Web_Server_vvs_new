package webserver;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.StringTokenizer;

public class WebServerTest {
    private WebServer webServer;
    private Socket clientSocket = new Socket();
    private ServerSocket serverSocket;
    private InputStreamReader inputReader = new InputStreamReader(clientSocket.getInputStream());
    private BufferedReader bufferedReader = new BufferedReader(inputReader);
    private PrintWriter printWriter = new PrintWriter(clientSocket.getOutputStream());
    private StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
    private String returnMethodServer = stringTokenizer.nextToken().toUpperCase();
    private File file = new File(webServer.getINDEX());

    public WebServerTest() throws IOException {
        ServerSocket serverSocket = new ServerSocket(10008);
    }

    @BeforeClass
    public void setWebServer() {
        try {
            webServer = new WebServer(serverSocket.accept());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void createPrintWriterInstance() throws IOException {
        Assert.assertNotNull(printWriter);
    }

    @Test
    public void createInputStream() {
        Assert.assertNotNull(inputReader);
    }

    @Test
    public void createBufferedReader() {
        Assert.assertNotNull(bufferedReader);
    }

    @Test
    public void createStringTokenizer() {
        Assert.assertNotNull(stringTokenizer);
    }

    @Test
    public void verifySocket() {
        Assert.assertEquals(webServer.getClientSocket(), serverSocket);
    }

    @Test
    public void verifyReturnMethodFromServer() {
        Assert.assertEquals("GET", returnMethodServer);
    }

    @Test
    public void verifyFileCreation() {
        Assert.assertNotNull(file);
    }

    @Test
    public void verifyIfServerIsRunning(){
        Assert.assertEquals(1, WebServer.getStatus());
    }

    @Test
    public void verifyIfServerIsStopped(){
        Assert.assertEquals(2, WebServer.getStatus());
    }

    @Test
    public void verifyIfServerIsInMaintenance(){
        Assert.assertEquals(3, WebServer.getStatus());
    }

    @Test
    public void verifyIndexPagePath(){
        Assert.assertEquals("C:\\Users\\Tomici\\Desktop\\index.html", WebServer.getINDEX());
    }

    @Test
    public void verifyErrorPagePath(){
        Assert.assertEquals("C:\\Users\\Tomici\\Desktop\\eroare.html", WebServer.getERROR());
    }

    @Test
    public void verifyStoppedPagePath(){
        Assert.assertEquals("C:\\Users\\Tomici\\Desktop\\stop.html", WebServer.getSTOPPED());
    }

    @Test
    public void verifyMaintenancePagePath(){
        Assert.assertEquals("C:\\Users\\Tomici\\Desktop\\mentenanta.html", WebServer.getMAINTENANCE());
    }
}

