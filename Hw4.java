import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Hw4 {
	static int N;
	static int M;

	static int[][] maxMatrix;
	static int[][] allocationMatrix;
	static int[][] needMatrix;

	static int[] resource;
	static int[] available;
	static int[] work = null;
	static boolean[] finish = null;

	static ArrayList<String> seq = null;
	
	static Scanner inputScanner = new Scanner(System.in);

	public static void main(String[] args) {
		try {
			System.out.println("Enter input file name: ");
			String fileName = inputScanner.nextLine();
			
			Scanner scanner = new Scanner(new File(fileName));

			// first line is always N
			N = Integer.parseInt(scanner.nextLine());
			// second line is always M
			M = Integer.parseInt(scanner.nextLine());

			// init the matrixes
			resource = new int[M];
			available = new int[M];
			maxMatrix = new int[N][M];
			allocationMatrix = new int[N][M];

			// read resource data
			String tempRow = scanner.nextLine();
			String[] tempRowArray = tempRow.split(" ");
			for (int x = 0; x < M; x++) {
				resource[x] = Integer.parseInt(tempRowArray[x]);
			}

			// read available data
			tempRow = scanner.nextLine();
			tempRowArray = tempRow.split(" ");
			for (int x = 0; x < M; x++) {
				available[x] = Integer.parseInt(tempRowArray[x]);
			}

			// readLine N times for the max matrix
			for (int x = 0; x < N; x++) {
				String row = scanner.nextLine();
				String[] column = row.split(" ");

				// write to the max matrix
				for (int y = 0; y < M; y++) {
					maxMatrix[x][y] = Integer.parseInt(column[y]);
				}
			}

			// readLine N times for the allocation matrix
			for (int x = 0; x < N; x++) {
				String row = scanner.nextLine();
				String[] column = row.split(" ");

				// write to the max matrix
				for (int y = 0; y < M; y++) {
					allocationMatrix[x][y] = Integer.parseInt(column[y]);
				}
			}

			// Done reading data
			scanner.close();

			needMatrix = getNeedMatix();

			initializeStuff();

			boolean notSafe = false;

			while (isFinishAllTrue() == false) {
				for (int i = 0; i < N; i++) {
					// Step 2: Find an i, such that Finish[i] == false...
					if (finish[i] == false) {

						// step 2: ...and Need[i][j] <= Work[j] for all j = 1, 2, ..., M
						for (int j = 0; j < M; j++) {
							if (needMatrix[i][j] <= work[j]) {
								// condition meets requirement so keep looking
								continue;
							} else {
								// on of the condition is no good so stop looking
								// and set flag
								notSafe = true;
								break;
							}
						}

						if (notSafe) {
							// reset notSafe and keep looking
							notSafe = false;
						} else {
							// step 3

							// +1 to i because it started from zero
							seq.add("P" + (i + 1));

							for (int j = 0; j < M; j++) {
								work[j] = work[j] + allocationMatrix[i][j];
							}

							finish[i] = true;

							// Step 3: Go to step 2 to check the status of resource availability for another
							// process
						}
					} else {
						// do nothing, just go to next index and check if it false
					}
				}// end for
			}// end while

			// step 4
			if (isFinishAllTrue()) {
				System.out.println("System is save: " + seq.toString());
			} else {
				System.out.println("System is UNSAFE");
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Return true if all index is true else false
	 * 
	 * @return
	 */
	private static boolean isFinishAllTrue() {
		boolean returnValue = true;

		for (int x = 0; x < finish.length; x++) {
			if (finish[x] == false) {
				returnValue = false;
			}
		}

		return returnValue;
	}

	/**
	 * Initialize the work, finish, seq array Step 1
	 */
	private static void initializeStuff() {
		work = new int[M];
		// java default boolean to false so no extra work
		finish = new boolean[N];
		seq = new ArrayList<>();

		// Initialize work to available
		for (int x = 0; x < M; x++) {
			work[x] = available[x];
		}
	}

	/**
	 * Do matrix math on the two matrixes and return the result
	 * 
	 * @return
	 */
	private static int[][] getNeedMatix() {
		int[][] needMatrix = new int[N][M];

		for (int x = 0; x < N; x++) {
			for (int y = 0; y < M; y++) {
				needMatrix[x][y] = maxMatrix[x][y] - allocationMatrix[x][y];
			}
		}

		return needMatrix;
	}

}
