/**
 * @author Group 2
 * @reference Ouda
 */

//importing the libraries that will be needed in this program

import javax.swing.*;
import java.awt.*;
import java.util.Calendar;
import java.util.Random;

//The class that has all the sorts in it
public class SortShow extends JPanel {


    // An array to hold the lines_lengths to be sorted
    public int[] lines_lengths;
    //The amount of lines needed
    public final int total_number_of_lines = 256;
    // An array to holds the scrambled lines_lengths
    public int[] scramble_lines;
    //A temp Array that is used later for sorts
    public int[] tempArray;

    //the default constructor for the SortShow class
    public SortShow() {
        //assigning the size for the lines_lengths below
        lines_lengths = new int[total_number_of_lines];
        for (int i = 0; i < total_number_of_lines; i++)
            lines_lengths[i] = i + 5;

    }


    //A method that scrambles the lines
    public void scramble_the_lines() {
        //A random generator
        Random num = new Random();
        //Randomly switching the lines
        for (int i = 0; i < total_number_of_lines; i++) {
            //getting a random number using the nextInt method (a number between 0 to i + 1)
            int j = num.nextInt(i + 1);
            //swapping The element at i and j
            swap(i, j);
        }
        //assigning the size for the scramble_lines below
        scramble_lines = new int[total_number_of_lines];
        //copying the now scrambled lines_lengths array into the scramble_lines array
        //to store for reuse for other sort methods
        //so that all sort methods will use the same scrambled lines for fair comparison
        for (int i = 0; i < total_number_of_lines; i++) {
            scramble_lines[i] = lines_lengths[i];
        }
        //Drawing the now scrambled lines_lengths
        paintComponent(this.getGraphics());
    }

    //Swapping method that swaps two elements in the lines_lengths array
    public void swap(int i, int j) {
        //storing the i element in lines_lengths in temp
        int temp = lines_lengths[i];
        //giving i element in lines_lengths the value of j element in lines_lengths
        lines_lengths[i] = lines_lengths[j];
        //giving j element in lines_lengths the value of temp
        lines_lengths[j] = temp;
    }

    //////////////////////////////////////////////////////////////////////

    //The selectionSort method
    public void SelectionSort() {
        //getting the date and time when the selection sort starts
        Calendar start = Calendar.getInstance();

        //Using the selection sort to lines_lengths sort the array
        int arrLength = lines_lengths.length;

        //Iterate through the array to place the smallest number
        for (int i = 0; i < arrLength - 1; i++){
            int minIndex = i; // index of min element

            //Search for and swap the smallest number
            minIndex = getIndexOfSmallest(minIndex, arrLength - 1);
            swap(i, minIndex);

            //redrawing the lines_lengths
            paintComponent(this.getGraphics());
            //Causing a delay for 10ms
            delay(10);
        }

        //getting the date and time when the selection sort ends
        Calendar end = Calendar.getInstance();
        //getting the time it took for the selection sort to execute
        //subtracting the end time with the start time
        SortGUI.selectionTime = end.getTime().getTime() - start.getTime().getTime();
    }

    //this method gets the smallest element in the array of lines_lengths
    public int getIndexOfSmallest(int first, int last) {
        int minIndex = first;

        //Searching for the smallest number
        for (int i = minIndex + 1; i <= last; i++){
            if (lines_lengths[i] < lines_lengths[minIndex]){
                minIndex = i;
            }
        }

        //redrawing the lines_lengths
        paintComponent(this.getGraphics());
        //Causing a delay for 10ms
        delay(10);
        return minIndex; //modify this line
    }

    //Bubble sort method
    public void bubbleSort() {
        //getting the date and time when the bubble sort starts
        Calendar start = Calendar.getInstance();

        // Traverse the entire array n - 1 times.
        for (int i = 0; i < total_number_of_lines - 1; i++) {
            // Inner loop runs n - i times in order to traverse
            // through the array (expect for the last i - 1).
            for (int j = 0; j < total_number_of_lines - i - 1; j++) {
                // If the curr element is greater than the next adj element, swap them.
                if (lines_lengths[j] > lines_lengths[j + 1]) {
                    swap(j, (j + 1));
                    paintComponent(this.getGraphics());
                }
            }
            //redrawing the lines_lengths
            //Causing a delay for 10ms
            delay(10);
        }

        Calendar end = Calendar.getInstance();
        //getting the time it took for the bubble sort to execute
        //subtracting the end time with the start time
        SortGUI.bubbleTime = end.getTime().getTime() - start.getTime().getTime();
    }

    //Shell sort method
    public void shellSort() {
        //getting the date and time when the shell sort starts
        Calendar start = Calendar.getInstance();

        int unsorted, i;
        // Start with a big gap of n/2, then reduce the gap by 2 in each iteration.
        for (int gap = total_number_of_lines / 2; gap > 0; gap /= 2) {
            for (int begin = 0; begin < gap; begin++) {
                // Do insertion sort based on the gap.
                for (unsorted = begin + gap; unsorted <= total_number_of_lines - 1; unsorted += gap) {
                    int firstUnsorted = lines_lengths[unsorted];
                    for (i = unsorted - gap; (i >= begin) && (firstUnsorted < lines_lengths[i]); i -= gap) {
                        lines_lengths[i + gap] = lines_lengths[i];
                    }
                    lines_lengths[i + gap] = firstUnsorted;

                    //redrawing the lines_lengths
                    paintComponent(this.getGraphics());
                    //Causing a delay for 2ms
                    delay(2);
                }
            }
        }

        Calendar end = Calendar.getInstance();
        //getting the time it took for the shell sort to execute
        //subtracting the end time with the start time
        SortGUI.shellTime = end.getTime().getTime() - start.getTime().getTime();
    }

    //Insertion sort method
    public void insertionSort() {
        //getting the date and time when the bubble sort starts
        Calendar start = Calendar.getInstance();

        int index;
        int first = 0;
        int last = total_number_of_lines - 1;
        int unsortedElement;

        //scans through data set going to each unsorted element
        for(index=first+1;index<=last;index++){
            unsortedElement = lines_lengths[index];
            insertInOrder(unsortedElement,first,index-1);
        }


        //redrawing the lines_lengths
        paintComponent(this.getGraphics());
        //Causing a delay for 10ms
        delay(10);

        Calendar end = Calendar.getInstance();
        //getting the time it took for the bubble sort to execute
        //subtracting the end time with the start time
        SortGUI.insertionTime = end.getTime().getTime() - start.getTime().getTime();
    }

    public void insertInOrder(int element , int first, int last){
        int index = last;

        // "slides" the data set over while element is smaller than the current index
        while(index>=first && element<lines_lengths[index]){
            lines_lengths[index + 1] = lines_lengths[index];
            paintComponent(this.getGraphics());
            index--;
        }
        // places element into its sorted order position after the data set "slides" over
        lines_lengths[index+1] = element;
    }

    //Quick sort method
    //It is a wrapper method for the recursive function
    public void quickSort() {
        //getting the date and time when the bubble sort starts
        Calendar start = Calendar.getInstance();

        // Call the recursive quickSort.
        tempArray = new int[lines_lengths.length];
        int high = lines_lengths.length - 1;
        quickSort(0, high);

        Calendar end = Calendar.getInstance();
        //getting the time it took for the bubble sort to execute
        //subtracting the end time with the start time
        SortGUI.quickTime = end.getTime().getTime() - start.getTime().getTime();
    }

    //Recursive method for
    public void quickSort(int low, int high) {
        // Base case
        if (low < high) {
            int pIndex = partition(low, high);

            // recursive call on the left of pivot
            quickSort(low, pIndex - 1);

            //redrawing the lines_lengths
            paintComponent(this.getGraphics());
            //Causing a delay for 10ms
            delay(10);

            // recursive call on the right of pivot
            quickSort(pIndex + 1, high);
        }
    }

    //Partition the array int L,E,G
    public int partition(int first, int last) {
        //select the last element as the pivot
        int pivot = lines_lengths[last];
        int i = (first - 1);

        // compare each element with pivot
        for (int j = first; j < last; j++) {

            if (lines_lengths[j] <= pivot) {
                i++;
                // swapping element at i with element at j
                swap(i, j);
            }
        }

        // swap pivot and greater element
        swap(i + 1, last);

        return (i + 1);
    }

    //recursive merge sort method
    public void R_MergeSort() {
        //getting the date and time when the recursive merge sort starts
        Calendar start = Calendar.getInstance();
        //assigning the size for the tempArray below
        tempArray = new int[total_number_of_lines];

        //You need to complete this part.
        R_MergeSort(0, total_number_of_lines - 1);
        Calendar end = Calendar.getInstance();

        //getting the time it took for the iterative merge sort to execute
        //subtracting the end time with the start time
        SortGUI.rmergeTime = end.getTime().getTime() - start.getTime().getTime();
    }

    //recursive merge sort method
    public void R_MergeSort(int first, int last) {
        if (first < last) {

            //You need to complete this part.
            int mid = (first + last) / 2; //finds middle using first and last
            R_MergeSort(first, mid); //recursively calls R_MergeSort on first half of subarray
            R_MergeSort(mid + 1, last); //recursively calls R_MergeSort on first half of subarray
            R_Merge(first, mid, last); //merges subarrays
            //Causing a delay for 10ms
            delay(10);
        }
        //redrawing the lines length
        paintComponent(this.getGraphics());
    }


    //recursive merge sort method
    public void R_Merge(int first, int mid, int last) {

        //You need to complete this part.
        int leftHalfIndex, rightHalfIndex;
        leftHalfIndex = first;
        rightHalfIndex = mid + 1;
        int i = first;

        // Merge both subarrays from lines_length into tempArray, sorting at each step.
        while (leftHalfIndex <= mid && rightHalfIndex <= last) {
            if (lines_lengths[leftHalfIndex] < lines_lengths[rightHalfIndex]) {
                tempArray[i] = lines_lengths[leftHalfIndex];
                leftHalfIndex++;
            } else {
                tempArray[i] = lines_lengths[rightHalfIndex];
                rightHalfIndex++;
            }
            i++;
        }

        //redrawing the lines length
        paintComponent(this.getGraphics());

        // Copy remaining elements of the left subarray if any.
        while (leftHalfIndex <= mid) {
            tempArray[i] = lines_lengths[leftHalfIndex];
            leftHalfIndex++;
            i++;
        }

        // Copy remaining elements of the right subarray if any.
        while (rightHalfIndex <= last) {
            tempArray[i] = lines_lengths[rightHalfIndex];
            rightHalfIndex++;
            i++;
        }

        // Copy elements from tempArray (both subarrays) to lines_length (original)
        for (i = first; i <= last; i++)
            lines_lengths[i] = tempArray[i];
    }

    //iterative merge sort method
    public void I_MergeSort() {
        //getting the date and time when the iterative merge sort starts
        Calendar start = Calendar.getInstance();
        //assigning the size for the tempArray below
        tempArray = new int[total_number_of_lines];
        //saving the value of total_number_of_lines
        int beginLeftovers = total_number_of_lines;


        for (int segmentLength = 1; segmentLength <= total_number_of_lines / 2; segmentLength = 2 * segmentLength) {
            beginLeftovers = I_MergeSegmentPairs(total_number_of_lines, segmentLength);
            int endSegment = beginLeftovers + segmentLength - 1;
            if (endSegment < total_number_of_lines - 1) {
                I_Merge(beginLeftovers, endSegment, total_number_of_lines - 1);
            }
        }

        // merge the sorted leftovers with the rest of the sorted array
        if (beginLeftovers < total_number_of_lines) {
            I_Merge(0, beginLeftovers - 1, total_number_of_lines - 1);
        }
        //getting the date and time when the iterative merge sort ends
        Calendar end = Calendar.getInstance();
        //getting the time it took for the iterative merge sort to execute
        //subtracting the end time with the start time
        SortGUI.imergeTime = end.getTime().getTime() - start.getTime().getTime();
    }

    // Merges segments pairs (certain length) within an array
    public int I_MergeSegmentPairs(int l, int segmentLength) {
        //The length of the two merged segments

        //You suppose  to complete this part (Given).
        int mergedPairLength = 2 * segmentLength;
        int numberOfPairs = l / mergedPairLength;

        int beginSegment1 = 0;
        for (int count = 1; count <= numberOfPairs; count++) {
            int endSegment1 = beginSegment1 + segmentLength - 1;

            int beginSegment2 = endSegment1 + 1;
            int endSegment2 = beginSegment2 + segmentLength - 1;
            I_Merge(beginSegment1, endSegment1, endSegment2);

            beginSegment1 = endSegment2 + 1;
            //redrawing the lines_lengths
            paintComponent(this.getGraphics());
            //Causing a delay for 10ms
            delay(10);
        }
        // Returns index of last merged pair
        return beginSegment1;
        //return 1;//modify this line
    }

    public void I_Merge(int first, int mid, int last) {
        //You suppose  to complete this part (Given).
        // Two adjacent sub-arrays
        int beginHalf1 = first;
        int endHalf1 = mid;
        int beginHalf2 = mid + 1;
        int endHalf2 = last;

        // While both sub-arrays are not empty, copy the
        // smaller item into the temporary array
        int index = beginHalf1; // Next available location in tempArray
        for (; (beginHalf1 <= endHalf1) && (beginHalf2 <= endHalf2); index++) {
            // Invariant: tempArray[beginHalf1..index-1] is in order
            if (lines_lengths[beginHalf1] < lines_lengths[beginHalf2]) {
                tempArray[index] = lines_lengths[beginHalf1];
                beginHalf1++;
            } else {
                tempArray[index] = lines_lengths[beginHalf2];
                beginHalf2++;
            }
        }
        //redrawing the lines_lengths
        paintComponent(this.getGraphics());

        // Finish off the nonempty sub-array

        // Finish off the first sub-array, if necessary
        for (; beginHalf1 <= endHalf1; beginHalf1++, index++)
            // Invariant: tempArray[beginHalf1..index-1] is in order
            tempArray[index] = lines_lengths[beginHalf1];

        // Finish off the second sub-array, if necessary
        for (; beginHalf2 <= endHalf2; beginHalf2++, index++)
            // Invariant: tempa[beginHalf1..index-1] is in order
            tempArray[index] = lines_lengths[beginHalf2];

        // Copy the result back into the original array
        for (index = first; index <= last; index++)
            lines_lengths[index] = tempArray[index];
    }

    //////////////////////////////////////////////////////////////////////

    //This method resets the window to the scrambled lines display
    public void reset() {
        if (scramble_lines != null) {
            //copying the old scrambled lines into lines_lengths
            for (int i = 0; i < total_number_of_lines; i++) {
                lines_lengths[i] = scramble_lines[i];
            }
            //Drawing the now scrambled lines_lengths
            paintComponent(this.getGraphics());
        }
    }


    //This method colours the lines and prints the lines
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        //A loop to assign a colour to each line
        for (int i = 0; i < total_number_of_lines; i++) {
            //using eight colours for the lines
            if (i % 8 == 0) {
                g.setColor(Color.green);
            } else if (i % 8 == 1) {
                g.setColor(Color.blue);
            } else if (i % 8 == 2) {
                g.setColor(Color.yellow);
            } else if (i % 8 == 3) {
                g.setColor(Color.red);
            } else if (i % 8 == 4) {
                g.setColor(Color.black);
            } else if (i % 8 == 5) {
                g.setColor(Color.orange);
            } else if (i % 8 == 6) {
                g.setColor(Color.magenta);
            } else
                g.setColor(Color.gray);

            //Drawing the lines using the x and y-components
            g.drawLine(4 * i + 25, 300, 4 * i + 25, 300 - lines_lengths[i]);
        }

    }

    //A delay method that pauses the execution for the milliseconds time given as a parameter
    public void delay(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        }
    }

}

