import java.util.Scanner;

public class TicTacToe {

    static char[][] board = {
        {' ', '|', ' ', '|', ' '},
        {'-', '+', '-', '+', '-'},
        {' ', '|', ' ', '|', ' '},
        {'-', '+', '-', '+', '-'},
        {' ', '|', ' ', '|', ' '}
    };

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char currentPlayer = 'X';

        System.out.println("=== Welcome to Tic-Tac-Toe ===");

        while (true) {
            printBoard();
            System.out.println("Player " + currentPlayer + ", enter your move (row[1-3] and column[1-3]): ");
            int row = scanner.nextInt();
            int col = scanner.nextInt();

            // Convert to board coordinates
            int mappedRow = (row - 1) * 2;
            int mappedCol = (col - 1) * 2;

            if (isValidMove(mappedRow, mappedCol)) {
                board[mappedRow][mappedCol] = currentPlayer;

                if (checkWinner(currentPlayer)) {
                    printBoard();
                    System.out.println("Player " + currentPlayer + " wins!");
                    break;
                } else if (isDraw()) {
                    printBoard();
                    System.out.println("It's a draw!");
                    break;
                }

                // Switch player
                currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
            } else {
                System.out.println("Invalid move. Try again.");
            }
        }

        scanner.close();
    }

    public static void printBoard() {
        for (char[] row : board) {
            for (char ch : row) {
                System.out.print(ch);
            }
            System.out.println();
        }
    }

    public static boolean isValidMove(int row, int col) {
        return board[row][col] == ' ';
    }

    public static boolean isDraw() {
        for (int i = 0; i <= 4; i += 2) {
            for (int j = 0; j <= 4; j += 2) {
                if (board[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean checkWinner(char player) {
        // Check rows and columns
        for (int i = 0; i <= 4; i += 2) {
            if ((board[i][0] == player && board[i][2] == player && board[i][4] == player) || 
                (board[0][i] == player && board[2][i] == player && board[4][i] == player)) {
                return true;
            }
        }

        // Check diagonals
        if ((board[0][0] == player && board[2][2] == player && board[4][4] == player) ||
            (board[0][4] == player && board[2][2] == player && board[4][0] == player)) {
            return true;
        }

        return false;
    }
}
