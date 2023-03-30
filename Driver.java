import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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

		poolOfThread.shutdown();

		System.out.println(getPermutation(102510));

		System.out.println(isAVampireNumber(102510));
	}

	/**
	 * Check if this number is a vampire number
	 * 
	 * @param number
	 * @return true if it is, else false
	 */
	public static boolean isAVampireNumber(int number) {

		List<Integer> permutation = new ArrayList<>(getPermutation(number));
		
		boolean returnValue = false;

		Integer fang1;
		Integer fang2;

		for (int x = 0; x < permutation.size(); x++) {
			fang1 = permutation.get(x);
			
			// loop again but start at the next number
			for (int y = x + 1; y < permutation.size(); y++) {
				fang2 = permutation.get(y);

				if (fang1.equals(fang2)) {
					// same permutation number so we skip it
				} else {
					if (isAPairOfFangNumber(number, fang1, fang2)) {
						System.out.println("Found some fangs: " + fang1 + " " + fang2);
						returnValue = true;
					}
				}
			}
		}

		return returnValue;
	}

	/**
	 * If the product of the two fang number equals the input number then return
	 * true else return false
	 * 
	 * @param aNumber
	 * @param fang1
	 * @param fang2
	 * @return
	 */
	public static boolean isAPairOfFangNumber(int aNumber, int fang1, int fang2) {

		if (aNumber == (fang1 * fang2)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Convert number to String array so we can find all possible permutation.
	 * Permutation length will be half the input number length. Don't have to worry
	 * about odd length because requirement range is 100000 - 999999.
	 * 
	 * @param aNumber
	 * @return
	 */
	public static Set<Integer> getPermutation(int aNumber) {
		Set<Integer> permutation = new HashSet<>();

		// Convert number to string and split it into array so we can loop through them
		String numberAsString = String.valueOf(aNumber);
		String[] splitNumbers = numberAsString.split("");

		for (int x = 0; x < splitNumbers.length; x++) {

			String fang = splitNumbers[x];

			if (fang.equals("0")) {
				// number should start with zero so we reset and skip
				fang = "";
				continue;
			} else {

				for (int y = 0; y < splitNumbers.length; y++) {

					// skip if we are at the same number we starting with
					if (x == y) {
						continue;
					} else {
						// combine next number to current number
						fang += splitNumbers[y];

						for (int z = 0; z < splitNumbers.length; z++) {

							// skip if current position is the same as the first two numbers
							if (z == x || z == y) {
								continue;
							} else {
								// at new number so combine it
								fang += splitNumbers[z];

								// when fang length is half the length of the original number then
								// we check if it is a valid fang
								if (fang.length() == splitNumbers.length / 2) {
									permutation.add(Integer.valueOf(fang));

									// remove the last number because we just found a permutation
									fang = fang.substring(0, fang.length() - 1);
									continue;
								}
							}
						}
					}

					// done with current permutation so remove last number
					fang = fang.substring(0, fang.length() - 1);
				}
			}

			// reset
			fang = "";
		}

		return permutation;
	}

}
