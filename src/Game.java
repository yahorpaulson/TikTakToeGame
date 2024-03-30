import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Game extends Thread {
    private Socket playerX;
    private Socket playerO;

    private BufferedReader inX;
    private PrintWriter outX;
    private BufferedReader inO;
    private PrintWriter outO;
    private char[][] board = new char[3][3];
    private boolean isX;

    public Game(Socket playerX, Socket playerO) throws IOException {
        this.playerO = playerO;
        this.playerX = playerX;
        inX = new BufferedReader(new InputStreamReader(playerX.getInputStream()));
        outX = new PrintWriter(playerX.getOutputStream(), true);
        inO = new BufferedReader(new InputStreamReader(playerO.getInputStream()));
        outO = new PrintWriter(playerO.getOutputStream(), true);
        outX.println("You are Player X");
        outO.println("You are Player O");
        isX = true;
    }

    @Override
    public void run() {
        try {
            while (true) {
                char currentPlayer = isX ? 'X' : 'O';
                showBoard();

                if(isFull()){
                    outX.println("It's a draw!");
                    outO.println("It's a draw!");
                    break;
                }

                if (isX) {
                    outX.println("Your turn: ");
                    int pos = Integer.parseInt(inX.readLine());
                    processMove(pos, 'X');
                } else {
                    outO.println("Your turn: ");
                    int pos = Integer.parseInt(inO.readLine());
                    processMove(pos, 'O');
                }
                if(isWinner(currentPlayer)){
                    outX.println(currentPlayer + " wins! Starting a new game...");
                    outO.println(currentPlayer + " wins! Starting a new game...");
                    resetBoard();
                    isX = true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void processMove(int pos, char currentPlayer) {
        int row = pos / 3;
        int col = pos % 3;
        if (board[row][col] == '\0') {
            board[row][col] = currentPlayer;
            outX.println("Move successful");
            outO.println("Move successful");
            isX = !isX;
        } else {
            if (currentPlayer == 'X') {
                outX.println("Invalid move.");
            } else {
                outO.println("Invalid move");
            }
        }
    }

    private void resetBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = '\0';
            }
        }
    }

    public void showBoard() {
        outO.println("Board: ");
        outX.println("Board: ");

        for (char[] row : board) {
            for (char cell : row) {
                outX.print(cell == '\0' ? '-' : cell);
                outO.print(cell == '\0' ? '-' : cell);
                outX.print(" ");
                outO.print(" ");
            }
            outX.println(" ");
            outO.println(" ");
        }
    }

    private boolean isWinner(char player) {
        for (int i = 0; i < 3; i++) {
            if (((board[i][0]) == player && board[i][1] == player && board[i][2] == player) ||
                    (board[0][i] == player && board[1][i] == player && board[2][i] == player)) {
                return true;
            }
        }
        if (board[0][0] == player && board[1][1] == player && board[2][2] == player) {
            return true;
        }
        if (board[0][2] == player && board[1][1] == player && board[2][0] == player) {
            return true;
        }
        return false;
    }
    private boolean isFull(){
        for (char[] row : board) {
            for (char cell : row) {
                if (cell == '\0') {
                    return false;
                }
            }
        }
        return true;
    }
}
