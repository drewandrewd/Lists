import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class MainTable extends JFrame{

    private static File file1 = new File("file.txt");
    private static File file2 = new File("resultFile.txt");
    private static String[] resultArr;
    private static List<Integer> resultList;
    private static String[][] arrayOfNums;

    public static int getColumns() throws FileNotFoundException {
        int length;
        if (arrayOfNums[0].length > arrayOfNums[1].length) {
            length = arrayOfNums[0].length;
        } else {
            length = arrayOfNums[1].length;
        }
        return  length;
    }

    public MainTable() {
        super("Списки массивов");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        JTable table = new JTable();
        table.setRowHeight(30);
        table.setIntercellSpacing(new Dimension(10, 10));
        table.setGridColor(Color.blue);
        table.setShowVerticalLines(false);

        JTable table2  = new JTable();
        table2.setRowHeight(30);
        table2.setIntercellSpacing(new Dimension(10, 10));
        table2.setGridColor(Color.blue);
        table2.setShowVerticalLines(false);

        JButton addArrFromFile = new JButton("Отобразить массив из файла");
        addArrFromFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    arrayOfNums = Logic.readArray(file1);
                    table.setModel(new DefaultTableModel(arrayOfNums, new String[getColumns()]));
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
        });

        JButton result = new JButton("Получить делимое");
        result.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    arrayOfNums = Logic.readArray(file1);
                    resultList = Logic.createNewList(arrayOfNums);
                    resultArr = Logic.listToArrays(resultList);
                } catch (FileNotFoundException r) {
                    r.printStackTrace();
                }
                int column = 0;
                table2.setModel(new DefaultTableModel(1, resultArr.length));
                for (String num : resultArr) {
                    table2.setValueAt(num, 0, column);
                    column++;
                }
                try {
                    Logic.writeArray(file2, Logic.createNewList(arrayOfNums));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        JButton newTable = new JButton("Записать новые данные");
        newTable.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String[] first = new String[getColumns()];
                    String[] second = new String[getColumns()];
                    for (int i = 0; i < first.length; i++) {
                        first[i] = table.getValueAt(0, i) + "";
                    }
                    arrayOfNums[0] = first;
                    for (int i = 0; i < first.length; i++) {
                        second[i] = table.getValueAt(1, i) + "";
                    }
                    arrayOfNums[1] = second;
                   Logic.writeNewArray(file1, arrayOfNums);
                } catch (FileNotFoundException r) {
                    r.printStackTrace();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });

        Box contents = new Box(BoxLayout.LINE_AXIS);
        contents.add(new JScrollPane(table));
        contents.add(new JScrollPane(table2));
        getContentPane().add(contents);

        JPanel pnlButtons = new JPanel();
        pnlButtons.add(addArrFromFile);
        pnlButtons.add(result);
        pnlButtons.add(newTable);
        getContentPane().add(pnlButtons, "South");

        setSize(600, 500);
        setVisible(true);
    }
    public static void main(String[] args) {
        new MainTable();
    }
}
