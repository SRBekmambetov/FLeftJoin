package ru.tsc.srb.leftjoin;

import java.util.*;

public class LeftJoin {

    private List<Row> tableA;
    private List<Row> tableB;

    public LeftJoin(List<Row> tableA, List<Row> tableB) {
        this.tableA = tableA;
        this.tableB = tableB;
    }

    public List<Row> getTableA() {
        return tableA;
    }

    public void setTableA(List<Row> tableA) {
        this.tableA = tableA;
    }

    public List<Row> getTableB() {
        return tableB;
    }

    public void setTableB(List<Row> tableB) {
        this.tableB = tableB;
    }

    public List<Row> getResultLeftJoinFromArrayList() {
        List<Row> resultTable = new ArrayList<>();
        for (Row rowA: tableA) {
            boolean flag = false;
            for (Row rowB: tableB) {
                if (rowA.equals(rowB)) {
                    resultTable.add(new Row(rowA.getId(), createValuesList(rowA, rowB)));
                    flag = true;
                }
            }
            if (flag == false) {
                resultTable.add(rowA);
            }
        }
        return resultTable;
    }

    public List<Row> getResultLeftJoinFromSortedLinkedList() {
        List<Row> resultTable = new LinkedList<>();
        Collections.sort(tableA);
        Collections.sort(tableB);
        ListIterator<Row> iteratorA = tableA.listIterator();
        ListIterator<Row> iteratorB = tableB.listIterator();
        Row rowA = iteratorA.next();
        Row rowB = iteratorB.next();
        while (iteratorA.hasNext() && iteratorB.hasNext()) {
            if (rowA.getId() < rowB.getId()) {
                resultTable.add(rowA);
                rowA = iteratorA.next();
            } else if (rowA.getId() > rowB.getId()) {
                rowB = iteratorB.next();
            } else {
                int z = 0;
                while (rowA.getId() == rowB.getId()) {
                    resultTable.add(new Row(rowA.getId(), createValuesList(rowA, rowB)));
                    rowB = iteratorB.next();
                    z++;
                }
                while (z != 0) {
                    rowB = iteratorB.previous();
                    z--;
                }
                rowA = iteratorA.next();
            }
        }
        return resultTable;
    }

    public List<Row> getResultLeftJoinHashMap() {
        return getLeftJoinFromHashMap(getMap(tableA), getMap(tableB));
    }

    private List<Row> getLeftJoinFromHashMap(Map<Integer, List<Row>> mapA, Map<Integer, List<Row>> mapB) {
        List<Row> resultTable = new ArrayList<>();
        for (Integer idA: mapA.keySet()) {
            if (!mapB.containsKey(idA)) {
                resultTable.addAll(mapA.get(idA));
                continue;
            } else {
                for(Row rowA: mapA.get(idA)) {
                    for (Row rowB: mapB.get(idA)) {
                        resultTable.add(new Row(rowA.getId(), createValuesList(rowA, rowB)));
                    }
                }
            }
        }
        return resultTable;
    }

    private Map<Integer, List<Row>> getMap(List<Row> table) {
        Map<Integer, List<Row>> map = new HashMap<>();
        for (Row row: table) {
            if (map.containsKey(row.getId())) {
                map.get(row.getId()).add(row);
            } else {
                List<Row> rows = new ArrayList<>();
                rows.add(row);
                map.put(row.getId(), rows);
            }
        }
        return map;
    }

    private List<String> createValuesList(Row rowA, Row rowB) {
        List<String> values = new ArrayList<>();
        values.addAll(rowA.getValues());
        values.addAll(rowB.getValues());
        return values;
    }
}
