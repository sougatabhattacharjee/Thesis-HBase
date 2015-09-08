package hbase.transformation.hbase;

/**
 * Created by IntelliJ IDEA 14.0
 *
 * @author sougata
 * @date February 27, 2015
 * @brief Application class to test
 */
public class VShellSyntaxBean {

    private String operation;
    private String inputTable;
    private String virtualTable;
    private String notaqlScript;
    private String rowKey;



    /*
        constructor for scan operation
        @param operation
        @param inputTable
     */
    public VShellSyntaxBean(String operation, String inputTable) {
        this.operation = operation;
        this.inputTable = inputTable;
    }

    /*
        constructor for get operation
        @param operation
        @param inputTable
        @param rowKey
     */

    public VShellSyntaxBean(String operation, String inputTable, String rowKey) {
        this.operation = operation;
        this.inputTable = inputTable;
        this.rowKey = rowKey;
    }

    /*
        constructor for define operation
        @param operation
        @param inputTable
        @param virtualTable
        @param notaqlScript
     */
    public VShellSyntaxBean(String operation, String virtualTable, String inputTable, String notaqlScript) {
        this.operation = operation;
        this.virtualTable = virtualTable;
        this.inputTable = inputTable;
        this.notaqlScript = notaqlScript;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getInputTable() {
        return inputTable;
    }

    public void setInputTable(String inputTable) {
        this.inputTable = inputTable;
    }

    public String getVirtualTable() {
        return virtualTable;
    }

    public void setVirtualTable(String virtualTable) {
        this.virtualTable = virtualTable;
    }

    public String getNotaqlScript() {
        return notaqlScript;
    }

    public void setNotaqlScript(String notaqlScript) {
        this.notaqlScript = notaqlScript;
    }

    public String getRowKey() {
        return rowKey;
    }

    public void setRowKey(String rowKey) {
        this.rowKey = rowKey;
    }
}
