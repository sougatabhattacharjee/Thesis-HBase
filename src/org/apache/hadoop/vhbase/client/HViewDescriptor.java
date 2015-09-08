package org.apache.hadoop.vhbase.client;

import hbase.transformation.connectionPool.HBaseConnection;
import hbase.transformation.notaql.NotaQLProcess;

/**
 * Created by IntelliJ IDEA 14.0
 *
 * @author sougata
 * @date April 29, 2015
 * @brief HViewDescriptor contains the details about an HBase View such as the BaseTableName and NotaQL Script.
 */
public class HViewDescriptor implements java.io.Serializable{

    private static final long serialVersionUID = 1L;

    private String viewName;
    private String baseTable;
    private String notaqlString;
    private NotaQLProcess notaql;


    public HViewDescriptor(final String viewName) {
        addViewName(viewName);
    }

    public HViewDescriptor(final String viewName, final String baseTable, final String notaqlString) throws Exception {
        addViewName(viewName);
        addBaseTable(baseTable);
        addNotaqlString(notaqlString);
    }

    public String getNotaqlString() {
        return notaqlString;
    }

    public void addNotaqlString(final String notaqlString) throws Exception {
        this.notaqlString = notaqlString;
    }

     public String getBaseTable() {
        return baseTable;
    }

    public void addBaseTable(final String baseTable) throws Exception {
        if(HBaseConnection.tableExist(baseTable)) {
            this.baseTable = baseTable;
        }
        else throw new HBaseConnection.TableNotFoundException("Unknown Table \"" + baseTable + "\"!");
    }

    public String getViewName() {
        return viewName;
    }

    public void addViewName(final String viewName) {
        this.viewName = viewName;
    }

    //parse notaql string to check if any error exists
    public void parseNotaQL(final String notaqlString) throws Exception {
        this.notaql = new NotaQLProcess(notaqlString);
    }

    public NotaQLProcess getNotaqlProcess() {
        return notaql;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HViewDescriptor)) return false;

        HViewDescriptor that = (HViewDescriptor) o;

        if (!baseTable.equals(that.baseTable)) return false;
        if (!notaql.equals(that.notaql)) return false;
        if (!notaqlString.equals(that.notaqlString)) return false;
        if (!viewName.equals(that.viewName)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = viewName==null?0:viewName.hashCode();
        result = 31 * result + baseTable==null?0:baseTable.hashCode();
        result = 31 * result + notaqlString==null?0:notaqlString.hashCode();
        result = 31 * result + notaql.hashCode();
        return result;
    }
}
