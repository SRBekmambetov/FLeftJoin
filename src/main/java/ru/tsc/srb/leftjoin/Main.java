package ru.tsc.srb.leftjoin;

import java.io.*;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        if (args.length != 2) {
            System.out.println("Некорректно введено два имени файла");
            return;
        }

        File file1 = new File(args[0]);
        File file2 = new File(args[1]);

        CreateTableFromFile createTableFromFile = new CreateTableFromFile();

        List<Row> tableA = null;
        List<Row> tableB = null;

        try {
            tableA = createTableFromFile.getTable(file1);
            tableB = createTableFromFile.getTable(file2);
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден");
            return;
        } catch (IOException e) {
            System.out.println("Файл не доступен для чтения");
            return;
        }

        System.out.println("Table A: " + tableA);
        System.out.println();
        System.out.println("Table B: " + tableB);
        System.out.println();
        System.out.println("Left Join ArrayList: " + new LeftJoin(tableA, tableB).getResultLeftJoinFromArrayList());
        System.out.println();
        System.out.println("Left Join SortedLinkedList: " + new LeftJoin(tableA, tableB).getResultLeftJoinFromSortedLinkedList());
        System.out.println();
        System.out.println("Left Join HashMap: " + new LeftJoin(tableA, tableB).getResultLeftJoinHashMap());
    }
}
