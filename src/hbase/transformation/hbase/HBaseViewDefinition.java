package hbase.transformation.hbase;

import hbase.transformation.notaql.NotaQLProcess;

/**
 * Created by IntelliJ IDEA 14.0
 *
 * @author sougata
 * @date February 19, 2015
 * @brief POJO class to define the view definition
 */
public class HBaseViewDefinition implements java.io.Serializable{


    private String baseTable;
    private NotaQLProcess notaql;
    private String notaqlScript;

    public HBaseViewDefinition(String baseTable, String notaqlScript) throws Exception {
        this.baseTable = baseTable;
        this.notaqlScript = notaqlScript;
        this.notaql = new NotaQLProcess(notaqlScript);
    }

    public HBaseViewDefinition() {
    }

    public String getBaseTable() {
        return baseTable;
    }

    public void setBaseTable(String baseTable) {
        this.baseTable = baseTable;
    }

    public NotaQLProcess getNotaql() {
        return notaql;
    }

    public void setNotaql(NotaQLProcess notaql) {
        this.notaql = notaql;
    }

    public String getNotaqlScript() {
        return notaqlScript;
    }

    public void setNotaqlScript(String notaqlScript) {
        this.notaqlScript = notaqlScript;
    }
}
