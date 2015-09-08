package hbase.transformation.hbase;

/**
 * Created by IntelliJ IDEA 14.0
 *
 * @author sougata
 *
 * @date January 27, 2015
 *
 * @brief JavaBean class (/POJO) for NotaQL Script
 */

public class NotaQLscript {


    private String iN_FILTER;
    private String qualifierProjection;
    private String qualifier;
    private String operation;
    private String value;

    public NotaQLscript(String IN_FILTER, String qualifierProjection) {
        this.iN_FILTER = IN_FILTER;
        this.qualifierProjection = qualifierProjection;
    }

    public NotaQLscript(String iN_FILTER, String qualifierProjection, String qualifier, String operation, String value) {
        this.iN_FILTER = iN_FILTER;
        this.qualifierProjection = qualifierProjection;
        this.qualifier = qualifier;
        this.operation = operation;
        this.value = value;
    }

    public NotaQLscript(String qualifier, String operation, String value) {
        this.qualifier = qualifier;
        this.operation = operation;
        this.value = value;
    }

    public String getiN_FILTER() {
        return iN_FILTER;
    }

    public void setiN_FILTER(String iN_FILTER) {
        this.iN_FILTER = iN_FILTER;
    }


    public String getQualifierProjection() {
        return qualifierProjection;
    }

    public void setQualifierProjection(String qualifierProjection) {
        this.qualifierProjection = qualifierProjection;
    }

    public String getQualifier() {
        return qualifier;
    }

    public void setQualifier(String qualifier) {
        this.qualifier = qualifier;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
