import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class ticTacToe {

	static ArrayList<Integer> playerPositions = new ArrayList<Integer>();
	static ArrayList<Integer> compPositions = new ArrayList<Integer>();

	public static void main(String[] args) {

		char[][] gameBoard = { { ' ', '|', ' ', '|', ' ' }, { '-', '+', '-', '+', '-' }, { ' ', '|', ' ', '|', ' ' },
				{ '-', '+', '-', '+', '-' }, { ' ', '|', ' ', '|', ' ' } };

		printGameBoard(gameBoard);

		while (true) {

			Scanner scan = new Scanner(System.in);
			System.out.println("Enter your placement (1 - 9): ");
			int playerPos = scan.nextInt();
			while (playerPositions.contains(playerPos) || compPositions.contains(playerPositions)) {
				System.out.println("Position taken! Enter an available position");
				playerPos = scan.nextInt();
			}

			placePiece(gameBoard, playerPos, "player");
			String result = checkWinner();
			if (result.length() > 0) {
				System.out.println(result);
				break;
			}

			Random rand = new Random();
			int comPos = rand.nextInt(9) + 1;
			while (playerPositions.contains(comPos) || compPositions.contains(comPos)) {

				comPos = rand.nextInt(9) + 1;
			}

			placePiece(gameBoard, comPos, "computer");

			printGameBoard(gameBoard);

			result = checkWinner();
			if (result.length() > 0) {
				System.out.println(result);
				break;
			}
			System.out.println(result);
		}

	}

	public static void printGameBoard(char[][] gameBoard) {
		for (char[] row : gameBoard) {
			for (char c : row) {
				System.out.print(c);
			}
			System.out.println();
		}
	}

	public static void placePiece(char[][] gameBoard, int pos, String user) {

		char symbol = ' ';

		if (user.equals("player")) {
			symbol = 'X';
			playerPositions.add(pos);
		} else if (user.equals("computer")) {
			symbol = 'O';
			compPositions.add(pos);
		}

		switch (pos) {
		case 1:
			gameBoard[0][0] = symbol;
			break;
		case 2:
			gameBoard[0][2] = symbol;
			break;
		case 3:
			gameBoard[0][4] = symbol;
			break;
		case 4:
			gameBoard[2][0] = symbol;
			break;
		case 5:
			gameBoard[2][2] = symbol;
			break;
		case 6:
			gameBoard[2][4] = symbol;
			break;
		case 7:
			gameBoard[4][0] = symbol;
			break;
		case 8:
			gameBoard[4][2] = symbol;
			break;
		case 9:
			gameBoard[4][4] = symbol;
			break;
		default:
			break;
		}
	}

	public static String checkWinner() {

		List topRow = Arrays.asList(1, 2, 3);
		List midRow = Arrays.asList(4, 5, 6);
		List bottomRow = Arrays.asList(7, 8, 9);

		List leftCol = Arrays.asList(1, 4, 7);
		List midCol = Arrays.asList(2, 5, 8);
		List rightCol = Arrays.asList(3, 6, 9);

		List diagonal1 = Arrays.asList(1, 5, 9);
		List diagonal2 = Arrays.asList(7, 5, 3);

		List<List> win = new ArrayList<List>();
		win.add(topRow);
		win.add(midRow);
		win.add(bottomRow);
		win.add(leftCol);
		win.add(midCol);
		win.add(rightCol);
		win.add(diagonal1);
		win.add(diagonal2);

		for (List l : win) {
			if (playerPositions.containsAll(l)) {
				return "Congratualations you won!";
			} else if (compPositions.containsAll(l)) {
				return "Computer wins! Sorry :(";
			}
		}
		if (playerPositions.size() + compPositions.size() == 9) {
			return "Tie!";
		}

		return "";

	}
}
