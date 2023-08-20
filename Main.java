import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        // Source array
        int[] sourceArray = {70, 250, 50, 300, 1};
        System.out.println("Исходный массив: ");
        // Output the contents of the source array
        System.out.println(Arrays.toString(sourceArray));
        // Getting the minimum and maximum values in the array
        Map<String, Integer> result = getMinMax(sourceArray);
        // Displaying the minimum and maximum values
        System.out.println("Min = " + result.get("min"));
        System.out.println("Max = " + result.get("max"));
    }

    public static Map<String, Integer> getMinMax(int[] sourceArray) {
        // Defining a container variable to return the result
        Map<String, Integer> results = new HashMap<>();
        // Sorting the array using the fast sorting method using recursion
        quickSort(sourceArray, 0, sourceArray.length - 1);
        // Putting the minimum value in the container
        results.put("min", sourceArray[0]);
        // Putting the maximum value in the container
        results.put("max", sourceArray[sourceArray.length - 1]);
        // returning the result
        return results;
    }

    public static void quickSort(int[] array, int lowIndex, int highIndex) {
        // If the array is empty, then exit
        if (array.length == 0)
            return;

        // The lower index should be less than the upper one, if it is equal to or greater, then we exit
        if (lowIndex >= highIndex)
            return;

        // Defining the index of the reference element
        int middleIndex = lowIndex + (highIndex - lowIndex) / 2;
        // Value of the reference element
        int middleValue = array[middleIndex];

        // Setting the initial values of the indexes to check the elements to the right and left of the reference element
        int lowIndexCheck = lowIndex, highIndexCheck = highIndex;
        // We continue iterating through the array until the checked subscript is less than or equal to the checked superscript
        // Exit the loop if the lower index being checked is greater than the upper index being checked
        while (lowIndexCheck <= highIndexCheck) {
            // On the left side of the reference element (by sequentially iterating the index: from zero up), 
            // we find an element (and its index) that is equal to or greater than the reference element (movement from left to right)
            while (middleValue > array[lowIndexCheck]) {
                // Increasing the index value to check the next element (moving to the right - approaching the reference element)
                lowIndexCheck++;
            }
            // On the right side of the reference element, we are looking for an element that is equal to or less than the reference element 
            // (movement from right to left: the index value decreases from the length of the array -1 )
            while (array[highIndexCheck] > middleValue) {
                // Increasing the index value to check the next element (moving to the left - approaching the reference element)
                highIndexCheck--;
            }

            // If the lower checked index is smaller than the upper checked index, 
            // then we swap the positions of these elements (mutually change their indexes between them)
            if (lowIndexCheck <= highIndexCheck) {
                if (array[lowIndexCheck] != array[highIndexCheck] && // We rearrange only elements with different values (there may be the same ones)!
                    lowIndexCheck != highIndexCheck ) {  // Rearranging the same value is also useless to process (so as not to rearrange the same element from its position to its own)!
                    // we store the value of the element from the "left" part of the array (with the "lower" index) in a temporary variable
                    int temp = array[lowIndexCheck];
                    // assign a value with a "lower" index to a value with an "upper" index (from the "right" part of the array)
                    array[lowIndexCheck] = array[highIndexCheck];
                    // assign a value with an "upper" index to a value with a "lower" index (from a temporary variable)
                    array[highIndexCheck] = temp;
                }
                // Even if the array elements were not rearranged, it is necessary to change the values of the following variables (indexes)
                // Go to the element with the following index (move to the right)
                lowIndexCheck++;
                // Go to the element with the following index (move to the left)
                highIndexCheck--;
            }
        }

        // recursion calls to sort the left and right parts
        // If the lower index is less than the upper index being checked, then we continue sorting and set new boundaries:
        // If the lower index is equal to the upper one being checked, then there is nothing to do
        if (lowIndex < highIndexCheck) {
            quickSort(array, lowIndex, highIndexCheck);
        }

        // If the lower checked index is less than the right border
        if (lowIndexCheck < highIndex) {
            quickSort(array, lowIndexCheck, highIndex);
        }
    }
}
