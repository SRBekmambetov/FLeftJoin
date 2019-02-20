package ru.tsc.srb.leftjoin;

import java.util.List;
import java.util.Objects;

public class Row implements Comparable<Row> {

    private int id;
    private List<String> values;

    public Row(int id, List<String> values) {
        this.id = id;
        this.values = values;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<String> getValues() {
        return values;
    }

    public void setValues(List<String> values) {
        this.values = values;
    }

    @Override
    public int compareTo(Row o) {
        return this.id - o.getId();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Row row = (Row) o;
        return id == row.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("\n" + id + "");
        for (String value: values) {
            sb.append(" ").append(value);
        }
        return sb.toString();
    }
}
