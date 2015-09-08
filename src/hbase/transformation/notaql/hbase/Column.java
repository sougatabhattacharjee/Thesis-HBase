package hbase.transformation.notaql.hbase;

/**
 * Created by IntelliJ IDEA 14.0
 *
 * @author sougata
 * @date March 05, 2015
 * @brief Class to represent hbase column family and column quantifiers. columnName represents quantifiers.
 */
public class Column {

    private AtomValue<?> columnFamily;
    private AtomValue<?> columnName;
    private AtomValue<?> timeStamp;


    /**
     * Constructor for hbase ColumnFamily with TimeStamp
     *
     * @param columnFamily
     * @param columnName
     * @param timeStamp
     */
    public Column(AtomValue<?> columnFamily, AtomValue<?> columnName, AtomValue<?> timeStamp) {
        this.columnFamily = columnFamily;
        this.columnName = columnName;
        this.timeStamp = timeStamp;
    }


    /**
     * Constructor for hbase ColumnFamily

     *
     * @param columnFamily
     * @param columnName
     */
    public Column(AtomValue<?> columnFamily, AtomValue<?> columnName) {
        this.columnFamily = columnFamily;
        this.columnName = columnName;
    }




    /**
     * Constructor for hbase ColumnFamily where columnFamily is not present
     * @param columnName
     */
    public Column(AtomValue<?> columnName) {
        this.columnFamily = null;
        this.columnName = columnName;
    }

    public AtomValue<?> getColumnFamily() {
        return columnFamily;
    }

    public AtomValue<?> getColumnName() {
        return columnName;
    }

    public AtomValue<?> getTimeStamp() {
        return timeStamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Column columnId = (Column) o;

        if (columnFamily != null ? !columnFamily.equals(columnId.columnFamily) : columnId.columnFamily != null) return false;
        if (!columnName.equals(columnId.columnName)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = columnFamily != null ? columnFamily.hashCode() : 0;
        result = result + columnName.hashCode();
//        result += timeStamp.hashCode();
        return result;
    }

    //example: Information:Born
    @Override
    public String toString() {
        return columnFamily + ":" + columnName;
    }

}
