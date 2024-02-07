CS 4050 Group 2 - Assignment 2

Implements many sorting algorithms on a GUI.


  public void R_MergeSort() {
        //getting the date and time when the recursive merge sort starts
        Calendar start = Calendar.getInstance();
        //assigning the size for the tempArray below

        //You need to complete this part.
        R_MergeSort(0,total_number_of_lines-1);
        Calendar end = Calendar.getInstance();
        //getting the time it took for the iterative merge sort to execute
        //subtracting the end time with the start time
        SortGUI.rmergeTime = end.getTime().getTime() - start.getTime().getTime();

    }

    //recursive merge sort method
    public void R_MergeSort(int first, int last){
        Calendar start = Calendar.getInstance();

        if(first < last){

            //You need to complete this part.
            int mid = (first+last)/2; //finds middle using first and last
            R_MergeSort(first,mid); //recursively calls R_MergeSort on first half of subarray
            R_MergeSort(mid+1,last); //recursively calls R_MergeSort on first half of subarray
            R_Merge(first,mid,last); //merges subarrays
            //Causing a delay for 10ms
            delay(10);
        }

        paintComponent(this.getGraphics());

        Calendar end = Calendar.getInstance();

        SortGUI.rmergeTime = end.getTime().getTime() - start.getTime().getTime();
    }


    //recursive merge sort method
    public void R_Merge(int first, int mid, int last){

        //You need to complete this part.
        int leftHalfIndex, rightHalfIndex;
        leftHalfIndex = first;
        rightHalfIndex = mid+1;
        int i = first;

        while(leftHalfIndex < mid && rightHalfIndex < last){
            if(lines_lengths[leftHalfIndex]<=lines_lengths[rightHalfIndex]){
                tempArray[i] = lines_lengths[leftHalfIndex];
                leftHalfIndex++;
            }
            else{
                tempArray[i] = lines_lengths[rightHalfIndex];
                rightHalfIndex++;
            }
            i++;
        }

        paintComponent(this.getGraphics());


        for (i = first; i < last; i++)
            lines_lengths[i] = tempArray[i];



    }
