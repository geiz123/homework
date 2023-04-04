import java.util.Scanner;

public class Final412 {

	static int numberN = 0;
	static String referenceString;
	static String[] referenceStringArray;

	static Scanner inputScanner = new Scanner(System.in);
	
	public static void main(String[] args) {

		int option = printMenuAndWaitForOption();

		while (true) {
			if (option == 0) {
				// break from infinite loop so program can close the scanner and exit
				break;
			} else if (option == 1) {
				while (true) {
					System.out.println("Enter a value between 2 and 8 (inclusive): ");
					try {
						numberN = inputScanner.nextInt();

						// clear all input
						inputScanner.nextLine();

						if (numberN >= 2 && numberN <= 8) {
							// exit the while loop
							break;
						} else {
							System.out.println("Bad input for N, try again.");
						}

					} catch (Exception e) {
						System.out.println("Exception happened, try again.");
						e.printStackTrace();
						// clear all input
						inputScanner.nextLine();
					}
				}
			} else if (option == 2) {
				if (numberN == 0) {
					System.out.println("You need to select option 1 first!");
				} else {
					while (true) {
						System.out.println("Enter number from 0 - 9 separated by ONE space, there should be at least "
								+ numberN + " numbers and at most 20 number");

						referenceString = inputScanner.nextLine();

						referenceStringArray = referenceString.split(" ");

						// Check if the number of inputs are correct
						if (referenceStringArray.length < numberN || referenceStringArray.length > 20) {
							System.out.println("Bad number of inputs, try again.");
							continue;
						}

						// Check if all inputed number are correct
						if (!areAllNumberCorrect()) {
							System.out.println("Some numbers did not match the requirements, try again.");
							continue;
						}

						// If we make it here then everything is good so break from loop
						break;
					}
				}
			} else if (option == 3) {
				simulateOPTAlgorithym();
			} else if (option == 4) {
				System.out.println("You selected " + option);
			} else {
				System.out.println("Invalid option form menu, try again");
			}

			// ask for option again
			option = printMenuAndWaitForOption();
		}

		// We are done so close the scanner
		inputScanner.close();
	}

	/**
	 * Check if all numbers in the array are between 0 and 9 inclusive
	 * 
	 * @return
	 */
	public static boolean areAllNumberCorrect() {
		// TODO: exception can happen if they input like this: 1 2
		for (String str : referenceStringArray) {
			if (Integer.valueOf(str) < 0 || Integer.valueOf(str) > 9)
				return false;
		}

		return true;
	}

	/**
	 * Print out the menu and read in user input and return it
	 * 
	 * @return
	 */
	public static int printMenuAndWaitForOption() {
		int option = 0;

		System.out.println("0 – Exit\r\n" + 
				"1 – Input N\r\n" + 
				"2 – Input the reference string\r\n" + 
				"3 – Simulate the OPT algorithm\r\n" + 
				"4 – Simulate the NEW algorithm\r\n" + 
				"Select option: ");

		try {

			inputScanner = new Scanner(System.in);

			option = inputScanner.nextInt();

			inputScanner.nextLine();

		} catch (Exception e) {
			System.out.println("Bad input, so defaulting to option 0.");

			e.printStackTrace();
		}

		return option;
	}

	/**
	 * 
	 */
	public static void simulateOPTAlgorithym() {
		if (referenceStringArray != null && numberN >= 2 && numberN <= 8) {
			String[][] theGrid = new String[numberN][referenceStringArray.length];
			fillGridWithOneSpace(theGrid);
			
			// track where we are at in the reference string array
			int currentPageIndex = 0;
			
			// keep track of what is currently in the physical frame
			String[] frameTracker = new String[numberN];
			
			while (true) {
				if (currentPageIndex == referenceStringArray.length) {
					// finish simulation, break from loop
					break;
				}
				
				printOPTAlgorithym(theGrid, referenceStringArray);
				
				pressAnyKeyToContinue();
				
				String page = referenceStringArray[currentPageIndex];
				
				int emptyFrameIndex = getEmptyFrameOrExistingPageIndex(frameTracker, page);
				
				// find empty frame to put page into
				if (emptyFrameIndex != -1) {
					frameTracker[emptyFrameIndex] = page;
				} else {
					// no frame were empty or page is not currently in a frame 
					// so we need to find the victim page using OPT algorithm
					
					// find the page that will not be needed the longest
					int frameToReplaceIndex = 0;
					
					int highestIndex = -1;
					
					for (int x = 0; x < frameTracker.length; x++) {
						int tempIndex = getIndexOfNextOccurance(frameTracker[x], currentPageIndex + 1);
						
						// page never occurs again so we break from loop and make it the frame to be replace
						if (tempIndex == -1) {
							frameToReplaceIndex = x;
							break;
						} else {
							if (highestIndex < tempIndex) {
								// make temp index the current highest
								highestIndex = tempIndex;
								
								// update index number of the frame array so we know
								//   which one to replace later
								frameToReplaceIndex = x;
							} else {
								// We do nothing because we don't care about page
								//   that occur before the highest index
							}
						}
					}// end for
					
					// by this point we should know which index of the frame array
					//  to replace the current page with 
				}
				
				
			}
		} else {
			System.out.println("Invalid inputs, reference string cannot be null and N must be >= 2 and <=8");
		}
	}

	private static int getIndexOfNextOccurance(String page, int startingIndex) {
		// TODO Auto-generated method stub
		
		for (int x = startingIndex; x < referenceStringArray.length; x++) {
			if (referenceStringArray[x].equals(page)) {
				// found the next occurrence so return the index 
				return x;
			}
		}
		
		return -1;
	}

	/**
	 * Look for empty frame or frame that already have the page we are trying to add and return the index of it
	 * @param frameTracker
	 * @param page
	 * @return
	 */
	private static int getEmptyFrameOrExistingPageIndex(String[] frameTracker, String page) {
		for (int x = 0; x < frameTracker.length; x++) {
			if (frameTracker[x].equals("") || frameTracker[x].equals(page)) {
				return x;
			}
		}
		return -1;
	}

	private static void pressAnyKeyToContinue() {
		System.out.println("Press ENTER key to continue...");
		inputScanner.nextLine();
	}

	/**
	 * TODO need page fault and victim page
	 * @param theGrid
	 * @param referenceString
	 */
	private static void printOPTAlgorithym(String[][] theGrid, String[] referenceStringArray) {
		
		// Build the reference string text and print it out
		String referenceRow = "Reference String |";
		
		for (String ref : referenceStringArray) {
			referenceRow += " " + ref + " |";
		}
		
		String border = getOuterBorder(referenceRow);
		
		// print top border
		System.out.println(border);
		
		// print the reference row
		System.out.println(referenceRow);
		
		// print separator
		System.out.println(border);
		
		// Build all the frames and print them out
		for (int x = 0; x < theGrid.length; x++) {
			String frameString = "Physical Frame " + x + " |";
			
			for (int y = 0; y < theGrid[0].length; y++) {
				frameString += " " + theGrid[x][y] + " |";
			}
			
			System.out.println(frameString);
		}
		
		// print the bottom border
		System.out.println(border);
	}

	private static String getOuterBorder(String referenceRow) {
		String border = "";
		
		for (int x = 0; x < referenceRow.length(); x++) {
			border += "-";
		}
		
		return border;
	}

	/**
	 * Fill the grid with spaces. Don't need to return the grid because all object
	 * are pass by reference in Java so any changes in here will also change the
	 * original grid
	 * 
	 * @param theGrid
	 */
	private static void fillGridWithOneSpace(String[][] theGrid) {
		for (int x = 0; x < theGrid.length; x++) {
			for (int y = 0; y < theGrid[x].length; y++) {
				theGrid[x][y] = " ";
			}
		}
	}

}
