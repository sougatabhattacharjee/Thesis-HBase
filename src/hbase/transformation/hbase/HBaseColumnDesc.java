package hbase.transformation.hbase;

/**
 * Created by IntelliJ IDEA 14.0
 *
 * @author sougata
 *
 * @date January 23, 2015
 *
 * @brief JavaBean class (/POJO) for HBase Column description
 */

public class HBaseColumnDesc {

    public static String DEFAULT_COLUMN_FAMILY = "Information";

    private String rowKey;
    private String colFamily;
    private String qualifier;
    private String value;

    HBaseColumnDesc() {}

    HBaseColumnDesc(String rowKey, String colFamily, String qualifier)
    {
        this.rowKey = rowKey;
        this.colFamily = colFamily ;
        this.qualifier = qualifier;
    }

    HBaseColumnDesc(String rowKey, String colFamily, String qualifier, String value)
    {
        this.rowKey = rowKey;
        this.colFamily = colFamily ;
        this.qualifier = qualifier;
        this.value = value;
    }


    public String getRowKey() {
        return rowKey;
    }

    public void setRowKey(String rowKey) {
        this.rowKey = rowKey;
    }

    public String getColFamily() {
        return colFamily;
    }

    public void setColFamily(String colFamily) {
        this.colFamily = colFamily;
    }

    public String getQualifier() {
        return qualifier;
    }

    public void setQualifier(String qualifier) {
        this.qualifier = qualifier;
    }
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
