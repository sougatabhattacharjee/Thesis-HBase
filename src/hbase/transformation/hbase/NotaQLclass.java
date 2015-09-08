package hbase.transformation.hbase;

/**
 * Created by IntelliJ IDEA 14.0
 *
 * @author sougata
 *
 * @date January 22, 2015
 *
 * @brief JavaBean class (/POJO) for NotaQL Class
 */
public final class NotaQLclass {

    private String table;
    private String notaqlScript;

    public NotaQLclass(String table, String notaql)
    {
        this.table = table;
        this.notaqlScript = notaql;
    }

    public String getNotaqlScript() {
        return notaqlScript;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public void setNotaqlScript(String notaqlScript) {
        this.notaqlScript = notaqlScript;
    }

}
