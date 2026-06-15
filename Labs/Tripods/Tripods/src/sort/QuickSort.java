package sort;

import tripods.Tripod;

import java.util.ArrayList;

/**
 * Perform an "out of place" quick sort on an array list of Tripod's by
 * ascending tripod sum.
 * <pre>
 * quick_sort (not in place):
 * best=O(nlogn)
 * worst=O(n^2)
 * </pre>
 *
 * @author RIT CS
 * @author Aamir Sohail
 */
public class QuickSort {
    /**
     * Partition the array for values less than the pivot.
     *
     * @param data  the full array of data
     * @param pivot the pivot
     * @return data less than the pivot
     */

    public static ArrayList<Tripod> partitionLess(ArrayList<Tripod> data, Tripod pivot) {
        ArrayList<Tripod> llist = new ArrayList<>();
        
        for (int i = 0; i < data.size(); i++){
            if (data.get(i).sum() < pivot.sum()){
                llist.add(data.get(i));
            }
        }
        return llist;
    }

    /**
     * Partition the array for values equal to the pivot.
     *
     * @param data  the full array of data
     * @param pivot the pivot
     * @return data equal to the pivot
     */
    public static ArrayList<Tripod> partitionEqual(ArrayList<Tripod> data, Tripod pivot) {
        ArrayList<Tripod> elist = new ArrayList<>();
        for (int i = 0; i < data.size(); i++){
            if(data.get(i).sum() == pivot.sum()){
                elist.add(data.get(i));
            }
        }
        return elist;
    }

    /**
     * Partition the array for values greater than the pivot.
     *
     * @param data  the full array of data
     * @param pivot the pivot
     * @return data greater than  the pivot
     */
    public static ArrayList<Tripod> partitionGreater(ArrayList<Tripod> data, Tripod pivot) {
        ArrayList<Tripod> glist = new ArrayList<>();
        for (int i = 0; i < data.size(); i++){
            if(data.get(i).sum() > pivot.sum()){
                glist.add(data.get(i));
            }
        }
        return glist;
    }

    /**
     * Performs a quick sort and returns a newly sorted array.
     *
     * @param data the data to be sorted
     * @return a sorted array
     */
    public static ArrayList<Tripod> quickSort(ArrayList<Tripod> data) {
        if (data.size()==0){
            return new ArrayList<>();
        }
        ArrayList<Tripod> list = new ArrayList<>();
        Tripod pivot = data.get(0);
        list.addAll(quickSort(partitionLess(data, pivot)));
        list.addAll((partitionEqual(data, pivot)));
        list.addAll(quickSort(partitionGreater(data, pivot)));
        return list;
    }
}
