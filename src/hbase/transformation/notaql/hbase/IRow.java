package hbase.transformation.notaql.hbase;

import java.util.Map;
import java.util.Set;
/**
 * Created by IntelliJ IDEA 14.0
 *
 * @author sougata
 * @date March 05, 2015
 * @brief This class represents a Row in a column store.
 */

public interface IRow extends Map<Column, AtomValue<?>> {
    /**
     * Provides the row id
     *
     * @return
     */
    public AtomValue<String> getRowKey();

    /**
     * Provides the columns of the given column family
     *
     * @param columnFamily
     * @return
     */
    public Map<Column, AtomValue<?>> getColumnFamily(AtomValue<?> columnFamily);

    /**
     * Provides the Value for any matching column without looking at the column family
     * @param columnName
     * @param columnFamilyId
     * @return
     */
    public AtomValue<?> get(AtomValue<?> columnName, AtomValue<?> columnFamilyId);

    /**
     * Provides the TimeStamp for any matching column without looking at the column family
     * @param columnName
     * @param columnFamilyId
     * @return
     */
    public AtomValue<?> getTimeStamp(AtomValue<?> columnName, AtomValue<?> columnFamilyId);



}
