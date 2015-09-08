package hbase.transformation.hbase;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by IntelliJ IDEA 14.0
 *
 * @author sougata
 *
 * @date January 25, 2015
 *
 * @brief JavaBean class (/POJO) for HBase Table.
 */


public class HbaseTableSchema {



    private String tableName;

    private Map<String, HBaseColumnDesc> hbaseColumn = new HashMap<String, HBaseColumnDesc>();


    HbaseTableSchema(){}

    HbaseTableSchema(Map<String, HBaseColumnDesc> hbaseColumn)
    {
        this.hbaseColumn = hbaseColumn;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public Map<String, HBaseColumnDesc> getHbaseColumn() {
        return hbaseColumn;
    }

    public void setHbaseColumn(Map<String, HBaseColumnDesc> hbaseColumn) {
        this.hbaseColumn = hbaseColumn;
    }


}
