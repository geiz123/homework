import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.util.Scanner;
import java.util.stream.Stream;

public class Hw6 {

	static Scanner inputScanner = new Scanner(System.in);

	static Path workingPath = null;

	public static void main(String[] args) {
		String userInput = printMenuAndGetInput();

		// loop until user enter 0
		while (!userInput.equals("0")) {

			switch (userInput) {
			case "1":
				workingPath = getUserInputOfDirectory();
				break;
			case "2":
				if (workingPath != null) {
					printOutTheContentOfDirectory();
				} else {
					System.out.println("You must select option 1 and enter a valid directory");
				}
				break;
			case "3":
				if (workingPath != null) {
					System.out.println(userInput);
				} else {
					System.out.println("You must select option 1 and enter a valid directory");
				}
				break;
			case "4":
				if (workingPath != null) {
					System.out.println(userInput);
				} else {
					System.out.println("You must select option 1 and enter a valid directory");
				}
				break;
			case "5":
				if (workingPath != null) {
					System.out.println(userInput);
				} else {
					System.out.println("You must select option 1 and enter a valid directory");
				}
				break;
			}

			// Done doing work so print menu again and ask for input
			userInput = printMenuAndGetInput();
		}

		inputScanner.close();
	}

	/**
	 * This option displays the content of the directory (previously selected by
	 * option 1) on the screen. All the files and sub-directories from the
	 * firstlevelONLYmust be displayed (files and directories should be listed
	 * separately one item per line, directories first). For each file, its size
	 * must also be displayed, in bytes. If no directory was selected an error
	 * message must be displayed.
	 */
	private static void printOutTheContentOfDirectory() {
		// use to say how many level we want Java to walk down the directory level
		int depth = 1; // 1 is for just the current level

		// List directory first
		try (Stream<Path> paths = Files.walk(workingPath, depth)) {
			paths.filter(Files::isDirectory).forEach(directory -> {
				System.out.println("Directory Name: " + directory.getFileName());
			});
		} catch (IOException e) {
			System.out.println("Something bad happened when listing all files.");
			e.printStackTrace();
		}

		// List files last
		try (Stream<Path> paths = Files.walk(workingPath, depth)) {
			paths.filter(Files::isRegularFile).forEach(file -> {
				try {
					System.out.println("File Name: " + file.getFileName() + " | File Size: " + Files.size(file));
				} catch (IOException e) {
					System.out.println("Could not get file size for: " + file.getFileName());
					e.printStackTrace();
				}
			});
		} catch (IOException e) {
			System.out.println("Something bad happened when listing all files.");
			e.printStackTrace();
		}

	}

	/**
	 * Ask user to enter a path to a directory. Return the path object if it exist
	 * and not a symbolic link, else we return null
	 * 
	 * @return
	 */
	private static Path getUserInputOfDirectory() {
		System.out.println("Type the absolute path to a directory, ex: /mac/user/documents");

		String pathName = inputScanner.nextLine();

		Path path = Path.of(pathName);

		// if path is a symbolic link to a directory then we ignore it
		if (Files.exists(path, LinkOption.NOFOLLOW_LINKS)) {
			System.out.println("Directory you entered exist, good job!");
			return path;
		} else {
			System.out.println("Directory you entered does not exist!  Now start over.");
			return null;
		}

	}

	/**
	 * Print menu and return what the user type
	 */
	private static String printMenuAndGetInput() {
		System.out.println("0 – Exit \r\n" + "1 – Select directory \r\n" + "2 – List directory content \r\n"
				+ "3 – Display file (hexadecimal view)\r\n" + "4 – Delete file \r\n"
				+ "5 – Mirror reflect file (byte level)\r\n" + "Select option:");

		return inputScanner.nextLine();

	}

}
