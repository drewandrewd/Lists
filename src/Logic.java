import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Logic {

    public static String[][] readArray(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        String[][] arrList = new String[2][];
        for (int i = 0; i < arrList.length; i++) {
            arrList[i] = scanner.nextLine().split(" ");
        }
        scanner.close();
        return arrList;
    }

    public static List<List<Integer>> arrToList(String[][] arr) {
        List<List<Integer>> lists = new ArrayList<>(2);
        for (int i = 0; i < arr.length; i++) {
            if (i == 0) {
                lists.add(new ArrayList<>());
                for (int j = 0; j < arr[i].length; j++) {
                    lists.get(i).add(Integer.parseInt(arr[i][j]));
                }
            } else {
                lists.add(new ArrayList<>());
                for (int j = 0; j < arr[i].length; j++) {
                    lists.get(i).add(Integer.parseInt(arr[i][j]));
                }
            }
        }
        return lists;
    }

    public static void writeArray(File file, List<Integer> list3) throws IOException {
        FileWriter fileWriter = new FileWriter(file);
        for (Integer num : list3) {
            fileWriter.write(num + " ");
        }
        fileWriter.flush();
        fileWriter.close();
    }

    public static void writeNewArray(File file, String[][] arr1) throws IOException {
        FileWriter fileWriter = new FileWriter(file);
        for (int i = 0; i < arr1.length; i++) {
            for (int j = 0; j < arr1[i].length; j++) {
                fileWriter.write(arr1[i][j] + " ");
            }
            fileWriter.write("\n");
        }
        fileWriter.flush();
        fileWriter.close();
    }

    public static List<Integer> createNewList(String[][] arr) {
        List<List<Integer>> lists = arrToList(arr);
        List<Integer> result = new ArrayList<>();
        for (Integer dividend : lists.get(0)) {
            for (Integer divider : lists.get(1)) {
                if (dividend != Math.abs(divider) && divider != 0 && Math.abs(divider) != 1 && dividend % divider == 0) {
                    result.add(dividend);
                    break;
                }
            }
        }
        return result;
    }

    public static String[] listToArrays(List<Integer> list) throws FileNotFoundException {
        String[] newArr = new String[list.size()];
        for (int i = 0; i < newArr.length; i++) {
            newArr[i] = list.get(i) + "";
        }
        return newArr;
    }
}
