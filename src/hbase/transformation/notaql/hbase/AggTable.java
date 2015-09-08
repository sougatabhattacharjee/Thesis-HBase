package hbase.transformation.notaql.hbase;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.ImmutableTable;
import com.google.common.collect.TreeBasedTable;
import com.google.common.collect.Table;

import java.util.*;

/**
 * Created by IntelliJ IDEA 14.0
 *
 * @author sougata
 * @date April 02, 2015
 * @brief This class which stores rowKey, column and values in the form of ImmutableTable to keep the insertion order.
 */
public class AggTable<R, C, V> implements Table<R, C, List<V>> {

    private ImmutableTable.Builder<R, C, List<V>> table;

    private Table<R, C, List<V>> table1;

    public AggTable() {
        table = ImmutableTable.builder();

    }


    /**
     * Appends the new value v to the list of vs for the given cell.
     * This will also initialize the list in case it doesn't exist.
     *
     * @param r The row key
     * @param c The column key
     * @param v The value
     */
    public void append(R r, C c, V v) {

       List<V> curVs = new LinkedList<V>();



        if (curVs == null) {
            curVs = new LinkedList<V>();
        }
        curVs.add(v);


        this.table.put(r, c, curVs);

    }

    @Override
    public boolean contains(Object o, Object o1) {
        return false;
    }

    @Override
    public boolean containsRow(Object o) {
        return false;
    }

    @Override
    public boolean containsColumn(Object o) {
        return false;
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

    @Override
    public List<V> get(Object o, Object o1) {
        return null;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public void clear() {

    }

    @Override
    public List<V> put(R r, C c, List<V> vs) {
        return null;
    }

    @Override
    public void putAll(Table<? extends R, ? extends C, ? extends List<V>> table) {

    }

    @Override
    public List<V> remove(Object o, Object o1) {
        return null;
    }

    @Override
    public Map<C, List<V>> row(R r) {
        return null;
    }

    @Override
    public Map<R, List<V>> column(C c) {
        return null;
    }

    @Override
    public Set<Cell<R, C, List<V>>> cellSet() {
        return null;
    }

    @Override
    public Set<R> rowKeySet() {
        return null;
    }

    @Override
    public Set<C> columnKeySet() {
        return null;
    }

    @Override
    public Collection<List<V>> values() {
        return null;
    }

    @Override
    public Map<R, Map<C, List<V>>> rowMap() {
        return null;
    }

    @Override
    public Map<C, Map<R, List<V>>> columnMap() {
        return null;
    }




}
