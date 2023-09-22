package ma.youcode.utils;

public class Collector {

    public static int[][] collect(int[][]... ints){
        int totalLength = 0;
        for (int[][] group : ints) {
            totalLength += group.length;
        }
        int[][] collection = new int[totalLength][];
        int index = 0;
        for (int[][] group : ints) {
            for (int[] arr : group) {
                collection[index] = arr;
                index++;
            }
        }
       return collection;
    }
}
