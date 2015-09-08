package hbase.transformation.notaql.hbase;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA 14.0
 *
 * @author sougata
 * @date March 05, 2015
 * @brief Represent a hbase row implementation. hbase row is basically a structure type of Map<K, V>
 */
public class Row extends LinkedHashMap<Column, AtomValue<?>> implements IRow {

    private AtomValue<?> rowKey;

    /**
     * Constructs stuff
     * @param rowKey             The id of the row
     */
    public Row(AtomValue<?> rowKey) {
        super();
        this.rowKey = rowKey;
    }


    public AtomValue getRowKey() {
        return this.rowKey;
    }


    @Override
    public Map<Column, AtomValue<?>> getColumnFamily(AtomValue<?> columnFamilyId) {

        final Map<Column, AtomValue<?>> columnFamily = new HashMap<Column, AtomValue<?>>();

        for (Map.Entry<Column, AtomValue<?>> column : this.entrySet()) {

            if(column.getKey().getColumnFamily().equals(columnFamilyId))
                columnFamily.put(column.getKey(), column.getValue());

        }

        return columnFamily;
    }

    @Override
    public AtomValue<?> get(AtomValue<?> columnName, AtomValue<?> columnFamilyId) {
//        System.out.println("columnFamilyId>>> = " + columnFamilyId);

        boolean columnPresent = false;
        if(columnFamilyId!=null) {
            for (Map.Entry<Column, AtomValue<?>> column : this.entrySet()) {
                if(column.getKey().getColumnFamily().equals(columnFamilyId)) {
                    if (column.getKey().getColumnName().equals(columnName)) {
                        columnPresent = true;      //check if the column is present in the current column family
                        return column.getValue();
                    }
                }
            }
        }
        if(columnFamilyId==null || columnPresent==false){
            for (Map.Entry<Column, AtomValue<?>> column : this.entrySet()) {
                if(column.getKey().getColumnName().equals(columnName))
                    return column.getValue();
            }
        }
     return null;
    }


    @Override
    public AtomValue<?> getTimeStamp(AtomValue<?> columnName, AtomValue<?> columnFamilyId) {
//        System.out.println("columnFamilyId>>> = " + columnFamilyId);

        boolean columnPresent = false;
        if(columnFamilyId!=null) {
            for (Map.Entry<Column, AtomValue<?>> column : this.entrySet()) {
                if(column.getKey().getColumnFamily().equals(columnFamilyId)) {
                    if (column.getKey().getColumnName().equals(columnName)) {
                        columnPresent = true;      //check if the column is present in the current column family
                        return column.getKey().getTimeStamp();
                    }
                }
            }
        }
        if(columnFamilyId==null || columnPresent==false){
            for (Map.Entry<Column, AtomValue<?>> column : this.entrySet()) {
                if(column.getKey().getColumnName().equals(columnName))
                    return column.getKey().getTimeStamp();
            }
        }
        return null;
    }

}
