// TODO: Everything but the bottom layer

import java.util.*;
import java.io.*;
import java.awt.Point;

public class Serial {

	final static int[] dx = new int[]{0, -1, 1, 0};
	final static int[] dy = new int[]{1, 0, 0, -1};

	static boolean isValid(int row, int col, int n, int m) {
		if((row < 0 || row >= n) || (col < 0 || col >= m)) {
			return false;
		}
		return true;
	}

	static void fillPlate(char[][][] plate, char[][][] alreadyFilled, int n, int m, int k, int x, int y) {
		// Counts number of squares that will be filled (i.e. time in seconds)
		int count = 0;

		// Make x and y zero-based, because for some reason codeforces doesn't
		// Do I have to do everything around here?
		x -= 1;
		y -= 1;

		// Determine the deepest layer we have access to
		int deepest_k = -1;
		for (int i = 0; i < k; i++) {
			if(plate[i][y][x] == '.') {
				deepest_k = i;
			}
		}
		System.out.println("Deepest_k = " + deepest_k);
		// Now deepest_k contains the k-index of deepest we can go
		// Do a standard BFS on the deepest_k'th layer
		Queue<Point> queue = new LinkedList<Point>();
		Point source = new Point(x, y);
		queue.add(source);
		alreadyFilled[deepest_k][y][x] = 'v';
		while(!queue.isEmpty()) {
			// Remove top element from queue
			Point now = queue.remove();
			int col = (int)now.getX();
			int row = (int)now.getY();
			// Increment on 'inspection'
			count++;

			// Check each of the 4 directions
			for (int i = 0; i < 4; i++) {
				int col_prime = col + dx[i];
				int row_prime = row + dy[i];
				if(isValid(row_prime, col_prime, n, m)
					&& plate[deepest_k][row_prime][col_prime] == '.'
					&& alreadyFilled[deepest_k][row_prime][col_prime] != 'v') {
						System.out.println(row_prime + "," + col_prime);
						Point next = new Point(col_prime, row_prime);
						queue.add(next);
						alreadyFilled[deepest_k][row_prime][col_prime] = 'v';
				}
			}
		}
		System.out.println("Count for bottom layer = " + count);
	}

	public static void main(String[] args) {
		int k = 3;
		int n = 3;
		int m = 3;

		int x = 1;
		int y = 1;
		char[][][] plate = new char[][][]{
			{
				{'.', '#', '.'},
				{'#', '#', '#'},
				{'#', '#', '.'}
			},
			{
				{'.', '#', '#'},
				{'#', '#', '#'},
				{'#', '#', '.'}
			},
			{
				{'.', '.', '.'},
				{'.', '.', '.'},
				{'.', '.', '.'}
			}
		};

		char[][][] alreadyFilled = new char[n][m][k];
		fillPlate(plate, alreadyFilled, n, m, k, x, y);
	}
}