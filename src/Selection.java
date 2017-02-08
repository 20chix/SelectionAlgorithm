 /******************************************************************************
 *  Compilation within a console or terminal:  
 *  Windows version: javac -cp .;stdlib.jar Selection.java
 *  OS / Linux version: javac -cp .:stdlib.jar Selection.java
 *  
 *  Execution within a console or terminal:  
 *  Windows version: java -cp .;stdlib.jar Selection < tiny.txt (or words3.txt)
 *  OS / Linux version: javac -cp .:stdlib.jar Selection < tiny.txt (or words3.txt)
 *  
 *  Adaptation: Enable and disable statements where appropriately in the lines of code below.
 *  
 *  NOTE for execution within an IDE (i.e., Eclipse or NetBeans): In Eclipse, drag the corresponding input data files
 *  tiny.txt and words3.txt over the main project folder and COPY it; In NetBeans. copy-paste the .txt files into the NetBeans project
 *  directory. Accordingly, change the file name in the statement, for instance, reading data from the 
 *  tiny.txt file:
 *  
 *  input = new Scanner(new File("tiny.txt"));
 *  
 *  NOTE (2) for execution within an IDE: Do not forget to add the stdlib.jar to the Libraries.
 *
 *
 ******************************************************************************/

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Comparator;
import java.util.Scanner;

/**
 *  The {@code Selection} class provides static methods for sorting an
 *  array using selection sort.
 *  <p>
 *
 */
public class Selection {

    // This class should not be instantiated.
    private Selection() { }

    /**
     * Rearranges the array in ascending order, using the natural order.
     * @param a the array to be sorted
     */
    public static void sort(Comparable[] a) {
        int n = a.length;
        for (int i = 0; i < n; i++) {
            int min = i;
            for (int j = i+1; j < n; j++) {
                if (less(a[j], a[min])) min = j;
            }
            exch(a, i, min);
            assert isSorted(a, 0, i);
        }
        assert isSorted(a);
    }

    /**
     * Rearranges the array in ascending order, using a comparator.
     * @param a the array
     * @param comparator the comparator specifying the order
     */
    public static void sort(Object[] a, Comparator comparator) {
        int n = a.length;
        for (int i = 0; i < n; i++) {
            int min = i;
            for (int j = i+1; j < n; j++) {
                if (less(comparator, a[j], a[min])) min = j;
            }
            exch(a, i, min);
            assert isSorted(a, comparator, 0, i);
        }
        assert isSorted(a, comparator);
    }


   /***************************************************************************
    *  Helper sorting functions.
    ***************************************************************************/
    
    // is v < w ?
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    // is v < w ?
    private static boolean less(Comparator comparator, Object v, Object w) {
        return comparator.compare(v, w) < 0;
    }
        
        
    // exchange a[i] and a[j]
    private static void exch(Object[] a, int i, int j) {
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }


   /***************************************************************************
    *  Check if array is sorted - useful for debugging.
    ***************************************************************************/

    // is the array a[] sorted?
    private static boolean isSorted(Comparable[] a) {
        return isSorted(a, 0, a.length - 1);
    }
        
    // is the array sorted from a[lo] to a[hi]
    private static boolean isSorted(Comparable[] a, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++)
            if (less(a[i], a[i-1])) return false;
        return true;
    }

    // is the array a[] sorted?
    private static boolean isSorted(Object[] a, Comparator comparator) {
        return isSorted(a, comparator, 0, a.length - 1);
    }

    // is the array sorted from a[lo] to a[hi]
    private static boolean isSorted(Object[] a, Comparator comparator, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++)
            if (less(comparator, a[i], a[i-1])) return false;
        return true;
    }



    // print array to standard output
    private static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            StdOut.println(a[i]);
        }
    }

    /**
     * Reads in a sequence of strings from standard input; selection sorts them; 
     * and prints them to standard output in ascending order. 
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
    	
    	// ONLY for IDE version, remove otherwise
    	Scanner input = null;
		try {
			input = new Scanner(new File("words3.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// END of IDE version
		
		int N = input.nextInt();
		String[] a = new String[N];
		
		// ONLY for IDE version, remove otherwise
		int i = 0;
		while (input.hasNext()) {
			a[i] = input.next();
			i++;
		};
		
		// END of IDE version
		
		// Enable line below for the console/terminal version....
        // String[] a = StdIn.readAllStrings();
		
        Selection.sort(a);
        show(a);
    }
}

/******************************************************************************
 *  You can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 ******************************************************************************/
