package hbase.transformation.notaql.hbase;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

import java.util.*;

/**
 * Created by IntelliJ IDEA 14.0
 *
 * @author sougata
 * @date April 02, 2015
 * @brief This class which stores rowKey, column and values in the form of HashBasedTable.
 */


public class AggTimeStampTable<R, C, V> implements Table<R, C, List<V>> {
    private Table<R, C, List<V>> table;

    private Table<R, C, List<Long>> tableTimeStamp;

    public AggTimeStampTable() {
        table = HashBasedTable.create();
        tableTimeStamp = HashBasedTable.create();
    }

    /**
     * Tests if the value is contained within a list in the table
     *
     * @param value The value which we test for
     * @return
     */
    @Override
    public boolean containsValue(Object value) {
        for (Map<C, List<V>> row : rowMap().values()) {
            for (List<V> valList : row.values()) {
                if (valList.contains(value)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Appends the new value v to the list of vs for the given cell.
     * This will also initialize the list in case it doesn't exist.
     *
     * @param rowKey The row key
     * @param column The column key
     * @param value The value
     */
    public void append(R rowKey, C column, V value) {

        //TODO keep the insertion order correct
        List<V> curVals = this.table.get(rowKey, column);


        if (curVals == null) {
            curVals = new LinkedList<V>();
        }
            curVals.add(value);

        this.table.put(rowKey, column, curVals);

    }

    public void appendTimeStamp(R rowKey, C column, Long value) {

        //TODO keep the insertion order correct
        List<Long> curVal = this.tableTimeStamp.get(rowKey, column);


        if (curVal == null) {
            curVal = new LinkedList<Long>();
        }
        curVal.add(value);

        this.tableTimeStamp.put(rowKey, column, curVal);

//        Collections.max(this.tableTimeStamp.get(rowKey, column));

    }

    /**
     * Appends the new values v to the list of vs for the given cell.
     * This will also initialize the list in case it doesn't exist.
     *
     * @param r  The row key
     * @param c  The column key
     * @param vs The values
     */
    public void appendAll(R r, C c, Collection<V> vs) {
        List<V> curVs = this.table.get(r, c);
        if (curVs == null) {
            curVs = new LinkedList<V>();
        }
        curVs.addAll(vs);
        this.table.put(r, c, curVs);
    }


    /**
     * Removes a value from a cell. If the cell list becomes empty this way, it removes the cell.
     *
     * @param rowKey
     * @param column
     * @param value
     */
    public void removeValue(R rowKey, C column, V value) {
        List<V> curVs = this.table.remove(rowKey, column);
        if (curVs == null)
            return;

        curVs.remove(value);
        if (curVs.size() <= 0)
            return;

        this.table.put(rowKey, column, curVs);
    }




    @Override
    public boolean contains(Object rowKey, Object colKey) {
        // Note: for this to work it must be guaranteed that empty value lists are removed always - which currently is the case
        return this.table.contains(rowKey, colKey);
    }

    @Override
    public boolean containsRow(Object rowKey) {
        return this.table.containsRow(rowKey);
    }

    @Override
    public boolean containsColumn(Object colKey) {
        return this.table.containsColumn(colKey);
    }

    @Override
    public List<V> get(Object rowKey, Object colKey) {
        return this.table.get(rowKey, colKey);
    }

    @Override
    public boolean isEmpty() {
        return this.table.isEmpty();
    }

    @Override
    public int size() {
        return this.table.size();
    }

    @Override
    public void clear() {
        this.table.clear();
    }

    @Override
    public List<V> put(R r, C c, List<V> vs) {
        return this.table.put(r, c, vs);
    }

    @Override
    public void putAll(Table<? extends R, ? extends C, ? extends List<V>> table) {
        this.table.putAll(table);
    }

    @Override
    public List<V> remove(Object rowKey, Object colKey) {
        return this.table.remove(rowKey, colKey);
    }

    @Override
    public Map<C, List<V>> row(R r) {
        return this.table.row(r);
    }

    @Override
    public Map<R, List<V>> column(C c) {
        return this.table.column(c);
    }

    @Override
    public Set<Cell<R, C, List<V>>> cellSet() {
        return this.table.cellSet();
    }

    @Override
    public Set<R> rowKeySet() {
        return this.table.rowKeySet();
    }

    @Override
    public Set<C> columnKeySet() {
        return this.table.columnKeySet();
    }

    @Override
    public Collection<List<V>> values() {
        return this.table.values();
    }

    @Override
    public Map<R, Map<C, List<V>>> rowMap() {
        return this.table.rowMap();
    }

    @Override
    public Map<C, Map<R, List<V>>> columnMap() {
        return this.table.columnMap();
    }
}
