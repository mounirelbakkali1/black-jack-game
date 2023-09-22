package ma.youcode.utils;

public class ListUtils {

    public static int[][] addToList(int[][] list ,int[] newElement){
        int[][] newList = new int[list.length + 1][];
        for (int i = 0; i < list.length; i++) {
            newList[i] = list[i];
        }
        newList[list.length] = newElement;
        return newList;
    }

    public static int[][] removeLast(int[][] pioche) {
        int[][] newList = new int[pioche.length - 1][];
        for (int i = 0; i < newList.length; i++) {
            newList[i] = pioche[i];
        }
        return newList;
    }
}
