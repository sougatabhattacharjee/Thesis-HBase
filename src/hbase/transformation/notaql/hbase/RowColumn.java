package hbase.transformation.notaql.hbase;

/**
 * Created by IntelliJ IDEA 14.0
 *
 * @author sougata
 * @date April 10, 2015
 * @brief Application class to represent row and column pair
 */
public class RowColumn<R,C> {

    private final R rowKey;
    private final C columnKeyID;

    public RowColumn(R rowKey, C columnKeyID) {
        this.rowKey = rowKey;
        this.columnKeyID = columnKeyID;
    }

    public R getRowKey() {
        return this.rowKey;
    }

    public C getColumnKeyID() {
        return this.columnKeyID;
    }

    @Override
    public int hashCode(){
        int hashcode = 0;
        hashcode = rowKey.hashCode()*20;
        hashcode += columnKeyID.hashCode()*20;
        return hashcode;
    }

    @Override
    public boolean equals(Object obj){
        if (obj instanceof RowColumn) {
            RowColumn pp = (RowColumn) obj;
            return (pp.rowKey.equals(this.rowKey) && pp.columnKeyID == this.columnKeyID);
        } else {
            return false;
        }
    }
}
