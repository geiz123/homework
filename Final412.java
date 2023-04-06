import java.util.Scanner;

public class Final412 {

	static int numberN = 4;
	static String referenceString = "3 1 6 2 1 3 7 3 2 1 5 4 2 3 7 2 1";
	static String[] referenceStringArray = referenceString.split(" ");

//	static int numberN = 0;
//	static String referenceString;
//	static String[] referenceStringArray;
	
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
				simulateOPTAlgorithm();
			} else if (option == 4) {
				simulateNEWAlgorithm();
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
	 * Simulate NEW Algorithm
	 */
	private static void simulateNEWAlgorithm() {
		if (referenceStringArray != null && numberN >= 2 && numberN <= 8) {
			String[][] theGrid = new String[numberN][referenceStringArray.length];
			fillGridWithOneSpace(theGrid);
			
			// track where we are at in the reference string array
			int currentPageIndex = 0;
			
			// keep track of what is currently in the physical frame
			String[] frameTracker = new String[numberN];
			
			String[] pageFaults = new String[referenceStringArray.length];
			
			String[] victimPages = new String[referenceStringArray.length];
			
			while (true) {
				if (currentPageIndex == referenceStringArray.length) {
					// finish simulation, print last frame and break from loop
					printOPTAlgorithym(theGrid, pageFaults, victimPages, referenceStringArray);
					break;
				}
				
				printOPTAlgorithym(theGrid, pageFaults, victimPages, referenceStringArray);
				
				pressAnyKeyToContinue();
				
				String page = referenceStringArray[currentPageIndex];
				
				int emptyFrameIndex = getEmptyFrameIndex(frameTracker, page);
				
				// find empty frame to put page into
				if (emptyFrameIndex != -1) {
					frameTracker[emptyFrameIndex] = page;
					
					// save page fault for display
					pageFaults[currentPageIndex] = page;
				} else {
					// No empty frame so figure out where to put the new page
					
					// check if page already in frame
					if (isPageInFrame(page, frameTracker)) {
						// page already in frame so nothing to do but move on to the next page
						// Move to next page
						currentPageIndex++;
						
						continue;
					}
					
					// Page is not currently in a frame 
					// so we need to find the victim page using NEW algorithm
					
					//
					// find the SECOND least recently used page from the frame in the reference string array
					//
					
					String leastUsed = "";
					String leastUsed2nd = "";
					
					int lastIndex = referenceStringArray.length;
					
					// Find the index of the least used page from the string array, starting from where we are at
					for (int y = 0; y < frameTracker.length; y++) {
						for (int x = currentPageIndex - 1; x >= 0; x--) {
							if (frameTracker[y].equals(referenceStringArray[x]) && x < lastIndex) {
								lastIndex = x;
							}
						}
					}
					
					// We now have the last index of the least used page from the referenceStringArray
					leastUsed = referenceStringArray[lastIndex];
					
					// Loop through the string array starting from the last page found from the frame through where we are at.
					// If the page is the same as the least used then we skip, but break as soon as they are different
					// because we have found our 2nd least used page.
					for (int x = lastIndex; x <= currentPageIndex; x++) {
						if (referenceStringArray[x].equals(leastUsed)) {
							continue;//ignore
						} else {
							
							// Check if this page is currently in the frame tracker before
							// accepting it as the 2nd least used page
							for (int y = 0; y < frameTracker.length; y++) {
								if (referenceStringArray[x].equals(frameTracker[y])) {
									leastUsed2nd = referenceStringArray[x];
									break;
								}
							}
							
							// Check if 2nd least used page was found so we can stop looking
							if (!leastUsed2nd.equals("")) {
								break;
							}
						}
					}
					
					// Now find the index of this 2nd least used page from the frameTracker array
					// so can replace it with a new page
					int victimIndex = -1;
					
					for (int x = 0; x < frameTracker.length; x++) {
						if (frameTracker[x].equals(leastUsed2nd)) {
							// found so save it and break
							victimIndex = x;
							break;
						}
					}
					
					// Save the victim page before replacing it
					victimPages[currentPageIndex] = leastUsed2nd;
					
					// Save the pageFault
					pageFaults[currentPageIndex] = page;
					
					// by this point we should know which index of the frame array
					//  to replace the current page with 
					frameTracker[victimIndex] = page;
					
				}
				
				// Write the frame to the grid
				for (int frame = 0; frame < theGrid.length; frame++) {
					if (frameTracker[frame] == null) {
						theGrid[frame][currentPageIndex] = " ";
					} else {
						theGrid[frame][currentPageIndex] = frameTracker[frame];
					}
				}
				
				// Move to next page
				currentPageIndex++;
				
			}
		} else {
			System.out.println("Invalid inputs, reference string cannot be null and N must be >= 2 and <=8");
		}
	}
	
	/**
	 *  Simulate the OPT Algorithm
	 */
	public static void simulateOPTAlgorithm() {
		if (referenceStringArray != null && numberN >= 2 && numberN <= 8) {
			String[][] theGrid = new String[numberN][referenceStringArray.length];
			fillGridWithOneSpace(theGrid);
			
			// track where we are at in the reference string array
			int currentPageIndex = 0;
			
			// keep track of what is currently in the physical frame
			String[] frameTracker = new String[numberN];
			
			String[] pageFaults = new String[referenceStringArray.length];
			
			String[] victimPages = new String[referenceStringArray.length];
			
			while (true) {
				if (currentPageIndex == referenceStringArray.length) {
					// finish simulation, print last frame and break from loop
					printOPTAlgorithym(theGrid, pageFaults, victimPages, referenceStringArray);
					break;
				}
				
				printOPTAlgorithym(theGrid, pageFaults, victimPages, referenceStringArray);
				
				pressAnyKeyToContinue();
				
				String page = referenceStringArray[currentPageIndex];
				
				int emptyFrameIndex = getEmptyFrameIndex(frameTracker, page);
				
				// find empty frame to put page into
				if (emptyFrameIndex != -1) {
					frameTracker[emptyFrameIndex] = page;
					
					// save page fault for display
					pageFaults[currentPageIndex] = page;
				} else {
					
					// check if page already in frame
					if (isPageInFrame(page, frameTracker)) {
						// page already in frame so nothing to do but move on to the next page
						// Move to next page
						currentPageIndex++;
						
						continue;
					}
					
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
					
					// Save the victim page before replacing it
					victimPages[currentPageIndex] = frameTracker[frameToReplaceIndex];
					
					// Save the pageFault
					pageFaults[currentPageIndex] = page;
					
					// by this point we should know which index of the frame array
					//  to replace the current page with 
					frameTracker[frameToReplaceIndex] = page;
					
				}
				
				// Write the frame to the grid
				for (int frame = 0; frame < theGrid.length; frame++) {
					if (frameTracker[frame] == null) {
						theGrid[frame][currentPageIndex] = " ";
					} else {
						theGrid[frame][currentPageIndex] = frameTracker[frame];
					}
				}
				
				// Move to next page
				currentPageIndex++;
				
			}
		} else {
			System.out.println("Invalid inputs, reference string cannot be null and N must be >= 2 and <=8");
		}
	}

	/**
	 * Loop through the frameTracker array and to see if the current page exist
	 * @param page
	 * @param frameTracker
	 * @return
	 */
	private static boolean isPageInFrame(String page, String[] frameTracker) {
		for (int x = 0; x < frameTracker.length; x++) {
			if (frameTracker[x].equals(page)) {
				return true;
			}
		}
		return false;
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
	 * Look for empty frame return the index of it
	 * @param frameTracker
	 * @param page
	 * @return
	 */
	private static int getEmptyFrameIndex(String[] frameTracker, String page) {
		for (int x = 0; x < frameTracker.length; x++) {
			if (frameTracker[x] == null || frameTracker[x].equals("")) {
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
	private static void printOPTAlgorithym(String[][] theGrid, String[] pageFaults, String[] victimPages, 
			String[] referenceStringArray) {
		
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
		
		// print separator
		System.out.println(border);
		
		// Page faults
		String pageFaultsStr = "Page Faults      | ";
		for (int x = 0; x < pageFaults.length; x++) {
			if (pageFaults[x] == null) {
				pageFaultsStr += "  | ";
			} else {
				pageFaultsStr += pageFaults[x] + " | ";
			}
		}
		
		// print the page fault string
		System.out.println(pageFaultsStr);
		
		// print the bottom border
		System.out.println(border);
		
		// Victim Pages
		String victimPagesStr = "Victim Pages     | ";
		for (int x = 0; x < victimPages.length; x++) {
			if (victimPages[x] == null) {
				victimPagesStr += "  | ";
			} else {
				victimPagesStr += victimPages[x] + " | ";
			}
		}
		
		// print the page fault string
		System.out.println(victimPagesStr);
		
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