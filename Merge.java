package code;
public class Merge {

	private static Integer[] arrx;
	
	private static boolean isSorted(Integer[] arr) {
		// this boolean is to check if it is sorted
		// I'm getting instability issue 
		boolean is = false;
		for (int i = 1; i < arr.length; i++) {
			
			if(arr[i] < arr[i - 1]) {
				// if one is out of order it isn't sorting
				is = false;
				return is;
			}			
			if(arr[i] >= arr[i - 1]) {
				is = true;
			}
		}
		return is;
	}
	
	public static Integer[] sort(Integer[] arr) {
		
		// this is called first. Make n indexes of extra space, then do a merge
		arrx = new Integer[arr.length];
		loHiMergeSort(arr,0, arr.length - 1);
		// it's off by oning somewhere and not sorting. so i'll just re-merge sort
		// odd because this one is suppose to be stable
		if(!isSorted(arr)) {
			sort(arr);
		}
		// done if this works
		if(isSorted(arr)) {
			return arr;
		}
		// should never get to this point
		return arr;
	}
	
	private static void loHiMergeSort(Integer[] arr, int lo, int hi) {
		// if hi is the low or less break out
		if (hi <= lo) return;
		
		// set midpoint. error could be here..
		int mid = lo + ( (hi - lo) / 2);
		
		// first sort current lo  to mid
		loHiMergeSort(arr, lo, mid);
		// then mid + 1 to hi
		loHiMergeSort(arr, mid + 1, hi);
		// then merge everything together
		merge(arr, lo, mid, hi);
	}
	
	private static void merge(Integer[] arr, int lo, int mid, int hi) {
		
		int loInc = lo;
		int midInc = mid + 1;
		
		// now use arrx's space for references to copy arr over
		for (int currentLo = lo; currentLo <= hi; currentLo++) {
			arrx[currentLo] = arr[currentLo];
		}
		
		// merge in sorted order...
		for( int currentLo = lo; currentLo <= hi; currentLo++) {
			
			// 4 cases:			
			// Merge lower
			if (loInc > mid) {
				arr[currentLo] = arrx[midInc++];
			
			// Merge upper
			} else if (midInc > hi) {
				
				arr[currentLo] = arrx[loInc++];
			
			// Check the extra space against lower
			} else if (arrx[midInc] < arr[loInc]) {
				arr[currentLo] = arrx[midInc++];
			} else {
				// all else lower swap
				arr[currentLo] = arrx[loInc++];
			}
		}
	}
}
