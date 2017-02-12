package code;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Sorts {

	private static Map<String, String> valid = new HashMap<String, String>();

	private static void makeValid() {
		
		// Letter inputs for when user is picking what type of sort they want
		valid.put("I", "Insertion Sort");
		valid.put("S", "Selection Sort");
		valid.put("B", "Bubble Sort");
		valid.put("H", "Shell Sort");
		valid.put("M", "Merge Sort");
	}

	private static void runSort(Integer[] arr, String picked) {
		
		// do the correct sort for the correct user choice
		if (picked.equals("I")) { insertionSort(arr); }
		if (picked.equals("S")) { selectionSort(arr); }		
		if (picked.equals("B")) { bubbleSort(arr); }		
		if (picked.equals("H")) { shellSort(arr); }
		if (picked.equals("M")) { mergeSort(arr); }
	}

	public static ArrayList<Integer[]> getRandomizedArrays() {
		
		//Crating new array list
		ArrayList<Integer[]> all = new ArrayList<Integer[]>();
		
		// setting arrays
		Integer[] Array1 = new Integer[10];
		Integer[] Array2 = new Integer[100];
		Integer[] Array3 = new Integer[1000];
		
		//add all numbers
		all.add(Array1);
		all.add(Array2);
		all.add(Array3);

		// for all the numbers in the array, ad as list and return the list
		for (Integer[] arr : all) {
			for (int i = 0; i < arr.length; i++) {
				arr[i] = i;
			} Collections.shuffle(Arrays.asList(arr));
		} return all;
	}

	public static Integer[] makeRandomArray(int size) {
		Integer[] arr = new Integer[size];
		
		// randomize list
		for (int i = 0; i < arr.length; i++) {
			arr[i] = i;
		}
		Collections.shuffle(Arrays.asList(arr));
		return arr;
	}

	public static Integer[] makeNormalArray(int size) {
		Integer[] arr = new Integer[size];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = i;
		}
		return arr;
	}

	public static Integer[] makeHomogeneousArray(int size) {
		Integer[] arr = new Integer[size];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = size;
		}
		return arr;
	}

	public static void bubbleSort(Integer[] arr) {
		int temp = 0;
		int len = arr.length;
		printElementsOfArray(arr);
		
		long start = System.nanoTime();
		long end = System.nanoTime();

		long actual = (end - start);
		for (int i = 0; i < len; i++) {
			for (int j = 1; j < len; j++) {
				if (arr[j] < arr[j - 1]) {
					// do swap if less than
					temp = arr[j - 1];
					arr[j - 1] = arr[j];
					arr[j] = temp;
				}
			}
		}
		printElementsOfArray(arr);
		printTimeFormat(actual);
	}

	public static void selectionSort(Integer[] arr) {
		printElementsOfArray(arr);
		long start = System.nanoTime();
		long end = System.nanoTime();
		
		for (int i = 0; i < arr.length - 1; i++) {
			int temp = i;
			for (int j = (i + 1); j < arr.length; j++) {
				if (arr[temp] > arr[j]) {
					int holder = arr[j];
					arr[j] = arr[temp];
					arr[temp] = holder;
				}
			}
		}
		long actual = (end - start);
		printElementsOfArray(arr);
		printTimeFormat(actual);
	}

	public static void insertionSort(Integer[] arr) {
		printElementsOfArray(arr);
		long start = System.nanoTime();
		for (int i = 1; i < arr.length; i++) {
			int j = i;
			while (0 < j && arr[j] < arr[j - 1]) {
				int temp = arr[j - 1];
				arr[j - 1] = arr[j];
				arr[j] = temp;
				j--;
			}
		}
		long end = System.nanoTime();
		long actual = (end - start);
		printElementsOfArray(arr);
		printTimeFormat(actual);
	}

	public static void shellSort(Integer[] arr) {
		printElementsOfArray(arr);
		long start = System.nanoTime();
		long end = System.nanoTime();
		int L = arr.length;
		int h = 1;

		while (h < (L / 3))
			h = (h * 3) + 1;

		while (h >= 1) {
			for (int i = h; i < L; i++) {
				for (int j = i; ((h <= j) && (arr[j] < arr[j - h])); j -= h) {
					int temp = arr[j - 1];
					arr[j - 1] = arr[j];
					arr[j] = temp;
				}
			} h = h / 3;
		}
		long actual = (end - start);

		printElementsOfArray(arr);
		printTimeFormat(actual);
	}

	public static void mergeSort(Integer[] arr) {
		// open declaration to see implementation
		printElementsOfArray(arr);
		long start = System.nanoTime();
		long end = System.nanoTime();
		printElementsOfArray(Merge.sort(arr));
		long actual = (end - start);
		printTimeFormat(actual);	
	}

	public static void printAllValues(ArrayList<Integer[]> all) {
		// for printing output
		for (Integer[] arr : all) {
			for (Integer a : arr) {
				System.out.println(a.toString());
			}
		}
	}

	public static void printElementsOfArray(Integer[] arr) {
		// just for printing output in a cleaner way
		// the len % even divisor is just to split lines for readability
		StringBuffer buffer = new StringBuffer();
		for (Integer a : arr) {
			buffer.append(a.toString() + ", ");
			if (arr.length > 1000) {
				if (Arrays.asList(arr).indexOf(a) % (arr.length / (arr.length / 10)) == 0) {
					buffer.append("\n"); }
			}
			if (arr.length == 1000) {
				if (Arrays.asList(arr).indexOf(a) % (arr.length / 50) == 0) {
					buffer.append("\n"); }
			}
			if (1000 > arr.length && arr.length >= 100) {
				if (Arrays.asList(arr).indexOf(a) % (arr.length / 4) == 0) {
					buffer.append("\n"); }
			}
		}
		System.out.println(buffer);
	}

	private static boolean keepSorting() {
		
		// When user inputs what sort they want 
		boolean state = false;
		try {
			// reads user input
			BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
			System.out.print("Sort? Enter Capital Y or N: ");
			String test;

			while ((test = input.readLine()) != null) {
				
				//if user input is Y:
				if (test.equals("Y") && test.length() == 1) {
					System.out.println("Please wait.. Currently sorting...\n");
					state = true;
					break;
				}
				if (test.equals("N") && test.length() == 1) {
					System.out.println("Good bye! \n");
					state = false;
					break;
				}
			}
		} catch (IOException e) {
			// TODO: handle exception
		}
		return state;
	}

	private static String getSortChoiceFromUserInput() {
		String out = "";
		try {
			BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
			String test;
			System.out.print("Enter the corresponding letter of the Sort you'd like to use:\n");
			System.out.println("I - Insertion\tS - Selection\n\n");
			System.out.println("B - Bubble\tH - Shell\n\nM - Merge");

			while ((test = input.readLine()) != null) {
				if (valid.containsKey(test) && test.length() == 1) {
					System.out.println("Now Sorting with: " + valid.get(test) + " Sort...\n");
					out = test.toUpperCase();
					break;
				}
			}
			System.out.println(
					"Sort Mode...");
		} catch (IOException io) {
			io.printStackTrace();
		}
		return out;
	}

	private static int getSortSizeFromUserInput() {
		int out = 0;
		try {
			BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
			String temp;
			System.out.print("Enter the size of the sort you'd like to use (Positive only): ");

			while ((temp = input.readLine()) != null) {
				if (isInteger(temp)) {
					System.out.println("Now sorting with: " + valid.get(temp) + " sort...");
					out = Integer.parseInt(temp);
					System.out.println("Size is " + out + ".");
					break;
				}
			} System.out.println( "Size Selection Mode...");
		} catch (IOException io) {
			io.printStackTrace(); }
		return out;
	}

	private static boolean isInteger(String possibleInt) {
		try {
			Integer.parseInt(possibleInt);
		} catch (NumberFormatException e) {
			// not int
			return false;
		} catch (NullPointerException e) {
			// user entered nothing
			return false;
		}
		// if not false then is integer
		return true;
	}
	
	private static void printTimeFormat(long actual) {
		StringBuffer buff = new StringBuffer();
		buff.append("\n\nThe time this took was: " + actual + " nanoseconds.\n\n");
		System.out.println(buff);
	}

	public static void main(String[] args) {	
		// get user inputs, make arrays, run homogeneous, sorted, random order and continue
		makeValid();
		while (keepSorting()) {
			String choice = getSortChoiceFromUserInput();
			int size = getSortSizeFromUserInput();
			
			Integer[] duplicate = makeHomogeneousArray(size);
			Integer[] normal = makeNormalArray(size); 
			Integer[] random = makeRandomArray(size);
			
			runSort(duplicate, choice);
			runSort(normal, choice);
			runSort(random, choice);
		}
		
		// this was for testing the implementation of the sorts
//		ArrayList<Integer[]> all = getRandomizedArrays();
//
//		// bubblesort
//		for (Integer[] arr : all) {
//			System.out.println("\nboop. bubble sorting");
//			bubbleSort(arr);
//
//		}
//		// selection sort
//		all = getRandomizedArrays();
//		for (Integer[] arr : all) {
//			System.out.println("\nSsshhhh! selection sorting\n");
//			selectionSort(arr);
//		}
//		// insertionsort
//		all = getRandomizedArrays();
//		for (Integer[] arr : all) {
//			System.out.println("\nIiiieee! insertion sorting\n");
//			insertionSort(arr);
//		}
//		// shellsort
//		all = getRandomizedArrays();
//		for (Integer[] arr : all) {
//			System.out.println("\nShell Shock?! shell sorting\n");
//			shellSort(arr);
//		}
//		// quicksort recursive - rightmost pivot
//		// quicksort recursive - rightmost pivot insertion sort when cutoff int
//		// is hit
//		// quicksort recursive - use median of three as pivot
//		// mergesort recursive
//		all = getRandomizedArrays();
//		for (Integer[] arr : all) {
//			System.out.println("\n...mer? merging sorting\n");
//			mergeSort(arr);
//		}
//		// heapsort

	}

}
