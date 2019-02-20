package ru.tsc.srb.leftjoin;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CreateTableFromFile {

    public List<Row> getTable(File file) throws IOException {
        List<Row> table = new ArrayList<>();
        int z = 1;
        String line;
        FileReader fileReader = null;
        fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        while ((line = bufferedReader.readLine()) != null) {
            int id = 0;
            String[] arr = line.split(" ");
            try {
                id = Integer.parseInt(arr[0]);
            } catch (NumberFormatException e) {
                System.out.println("Введен не верный id в строке " + z);
            }
            List<String> values = new ArrayList<>();
            for (int i = 1; i < arr.length; i++) {
                values.add(arr[i]);
            }
            table.add(new Row(id, values));
            z++;
        }
        return table;
    }
}
