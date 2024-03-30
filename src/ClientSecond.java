import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientSecond {
    private static final String SERVER_IP = "127.0.0.1";
    private static final int SERVER_PORT = 12345;

    public static void main(String[] args) {
        ClientSecond clientSecond = new ClientSecond();
        clientSecond.start();
    }

    public void start() {
        try (Socket socket = new Socket(SERVER_IP, SERVER_PORT);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in))) {

            System.out.println(in.readLine());

            String response;
            while ((response = in.readLine()) != null) {
                System.out.println(response);
                if (response.startsWith("Your turn")) {
                    String move = userInput.readLine();
                    out.println(move);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
