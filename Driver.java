import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Driver {

	public static int START_NUMBER = 100000;
	public static int END_NUMBER = 999999;

	public static void main(String[] args) {

		// https://os.ecci.ucr.ac.cr/slides/Abraham-Silberschatz-Operating-System-Concepts-10th-2018.pdf#page=236
		// create thread pool with unlimited number of threads, p. 236, section 4.5.1.1
		// Java Thread Pools
		ExecutorService poolOfThread = Executors.newCachedThreadPool();

		// Use lambda notation to create a Callable object to find even number within a
		// range that are vampire number. Print the number when found, keep a count of
		// how many found and return it when done
		Callable<Integer> lookForEven = () -> {
			int foundCount = 0;

			return foundCount;

		};

		// Use lambda notation to create a Callable object to find odd number within a
		// range that are vampire number. Print the number when found, keep a count of
		// how many found and return it when done
		Callable<Integer> lookForOdd = () -> {
			int foundCount = 0;

			return foundCount;

		};

		Future<Integer> evenNumberResult = poolOfThread.submit(lookForEven);
		Future<Integer> oddNumberResult = poolOfThread.submit(lookForOdd);

		try {
			System.out.println(evenNumberResult.get());
			System.out.println(oddNumberResult.get());
		} catch (InterruptedException | ExecutionException e) {
			// ignore all exception
		}

	}
	
	/**
	 * Check if this number is a vampire number
	 * @param number
	 * @return true if it is, else false
	 */
	public static boolean isAVampireNumber(int number) {
		
		return false;
	}

}
