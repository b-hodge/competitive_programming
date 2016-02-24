// TODO: Input/Output

import java.util.*;
import java.io.*;
import java.awt.Point;

public class Maze {

	final static int[] dx = new int[]{0, -1, 1, 0};
	final static int[] dy = new int[]{1, 0, 0, -1};

	static boolean isValid(int row, int col, int n, int m) {
		if((row < 0 || row >= n) || (col < 0 || col >= m)) {
			return false;
		}
		return true;
	}

	static void printPath(char[][] maze, Point[][] prev, int row, int col) {
		String path = "";
		printPathHelper(maze, prev, row, col, path);
		return;
	}

	static void printPathHelper(char[][] maze, Point[][] prev, int row, int col, String path) {
		//Base case
		if (maze[row][col] == 's') {
			System.out.println(path);
			return;
		}

		Point prev_point = prev[row][col];
		int row_prev = (int)prev_point.getY();
		int col_prev = (int)prev_point.getX();

		// Previous is below -> add U to beginning of string
		if((row_prev - row) == 1) {
			path = "U" + path;
			printPathHelper(maze, prev, row_prev, col_prev, path);
		}

		// Previous is above -> add D to beginning of string
		if((row_prev - row) == -1) {
			path = "D" + path;
			printPathHelper(maze, prev, row_prev, col_prev, path);
		}

		// Previous is right -> add L to beginning of string
		if((col_prev - col) == 1) {
			path = "L" + path;
			printPathHelper(maze, prev, row_prev, col_prev, path);
		}
		// Previous is left -> add R to beginning of string
		if((col_prev - col) == -1) {
			path = "R" + path;
			printPathHelper(maze, prev, row_prev, col_prev, path);
		}
		
		// We shouldn't ever get here
		return;
	}

	static void solveMaze(char[][] maze, char[][] alreadyVisited, Point[][] prev, int n, int m) {
		// First we need to search for the starting point 's'
		Point start = new Point();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (maze[i][j] == 's') {
					start.setLocation(j, i);
				}

			}
		}

		// Now the Point start contains the coordinates of 's' in the maze
		// Now we solve the maze!
		Queue<Point> queue = new LinkedList<Point>();
		queue.add(start);
		int start_col = start.getX();
		int start_row = start.getY();
		alreadyVisited[start_row][start_col] = 'v';
		while(!queue.isEmpty()) {
			// Get the first element from the queue
			Point now = queue.remove();
			int col = (int)now.getX();
			int row = (int)now.getY();

			//If this is true, we've reached the end of the maze
			//BFS guarantees shortest path gets there first
			//Ordering of dx and dy ensure alphabetically first path is found first
			if (maze[row][col] == 'e') {
				printPath(maze, prev, row, col);
				return;
			}

			// Check the adjacent elements
			// If they're visitable && haven't yet been visited, add to queue
			for (int i = 0; i < 4; i++) {
				int col_prime = col + dx[i];
				int row_prime = row + dy[i];
				if ((isValid(row_prime, col_prime, n, m)) 
					&& ((maze[row_prime][col_prime] == '.') || (maze[row_prime][col_prime] == 'e'))  
					&& (alreadyVisited[row_prime][col_prime] != 'v')) {
						Point next = new Point(col_prime, row_prime);
						queue.add(next);
						// mark previously visited as being the Point 'now'
						prev[row_prime][col_prime] = now;
						alreadyVisited[row_prime][col_prime] = 'v';
				}
			}
		}

		//If we get here, there is no solution
		System.out.println(":(");
		return;
	} 

	public static void main(String[] args) {
		// BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		// int numTestCases = Integer.parseInt(in.readLine());

		// int n = 3;
		// int m = 3;
		// char[][] maze = new char[][]{
		// 	{'.', '#', 'e'},
		// 	{'.', '.', '.'},
		// 	{'s', '#', '.'}
		// };

		int n = 2;
		int m = 3;
		char[][] maze = new char[][]{
			{'.', '.', 'e'},
			{'s', '.', '.'}
		};

		char[][] alreadyVisited = new char[n][m];
		Point[][] prev = new Point[n][m];

		solveMaze(maze, alreadyVisited, prev, n, m);



	}
}