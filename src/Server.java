import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread {
    private static final int PORT = 12345;

    public static void main(String[] args) {
        Server server = new Server();
        server.start();
    }

    public void run() {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Waiting for players...");
            Socket playerX = serverSocket.accept();
            System.out.println("Player X connected.");
            Socket playerO = serverSocket.accept();
            System.out.println("Player O connected.");

            Game game = new Game(playerX, playerO);
            game.start();
            game.join();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
