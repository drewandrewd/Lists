import java.io.*;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        File file = new File(args[0]);
        File resultFile = new File(args[1]);
        String[][] arr = Logic.readArray(file);
        List<Integer> list3 = Logic.createNewList(arr);
        Logic.writeArray(resultFile, list3);
    }
}
