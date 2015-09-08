package org.apache.hadoop.vhbase.client;

import hbase.transformation.notaql.NotaQLProcess;
import hbase.transformation.notaql.NotaQLTransformation;
import hbase.transformation.notaql.hbase.InputTable;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.client.*;

import java.io.IOException;

/**
 * Created by IntelliJ IDEA 14.0
 *
 * @author sougata
 * @date April 29, 2015
 * @brief Used to communicate with a single HBase View.
 */
public class VirtualHTable extends HTable {

    private static volatile Configuration conf;
    private String viewName;
    private static String baseTable;
    private String notaqlString;
    private static NotaQLProcess notaql;
    private HTable hTable;

//    private HTable hTable;
    /**
     * Creates an object to access a HBase views.
     * Shares zookeeper connection and other resources with other HTable instances
     * created with the same <code>conf</code> instance.  Uses already-populated
     * region cache if one is available, populated by any other HTable instances
     * sharing this <code>conf</code> instance.  Recommended.
     * @param conf Configuration object to use.
     * @param viewName Name of the table.
     * @throws java.io.IOException if a remote or network exception occurs
     */
    public VirtualHTable(Configuration conf, final String viewName) throws Exception, ClassNotFoundException {
        this(conf, getBaseTable(conf, viewName), getNotaqlString(conf, viewName));
    }

    public VirtualHTable(Configuration conf, final String baseTable, final String notaqlString) throws Exception {
        super(conf, baseTable);
        this.baseTable = baseTable;
        this.conf = conf;
        if(notaqlString==null)
            return;
        this.notaql = new NotaQLProcess(notaqlString);
        this.setNotaql(notaql);
    }

    public VirtualResultScanner getScanner(final Scan scan) throws IOException {

        ResultScanner rs = super.getScanner(scan);
        return new VirtualResultScanner(rs, notaql);
    }

    public Result get(final Scan scan, final String rowKey) throws IOException {


        ResultScanner rs = super.getScanner(scan);
//        if(notaql==null)
//            return super.get(get);
        notaql.setTransformationContext(notaql.getNotaQLContext().transformation());
        notaql.setNotaQLTransform(new NotaQLTransform(notaql.getTransformationContext()));
        return notaql.getNotaQLTransform().transform(rs,rowKey);
    }

    public Result get1(final Get get) throws IOException {

        String rowKey = new String(get.getRow());
        Scan scan = new Scan();
        ResultScanner rs = super.getScanner(scan);
        if(notaql==null)
           return super.get(get);
        notaql.setTransformationContext(notaql.getNotaQLContext().transformation());
        notaql.setNotaQLTransform(new NotaQLTransform(notaql.getTransformationContext()));
        return notaql.getNotaQLTransform().transform(rs,rowKey);
    }

    public NotaQLProcess getNotaql() {
        return notaql;
    }

    public void setNotaql(NotaQLProcess notaql) {
        this.notaql = notaql;
    }

    private static String getBaseTable(Configuration conf, String viewName) throws IOException, ClassNotFoundException {
        VirtualHbaseAdmin virtualHbaseAdmin = new VirtualHbaseAdmin(conf);
        if(virtualHbaseAdmin.getViewDescriptor(viewName)!=null)
            return virtualHbaseAdmin.getViewDescriptor(viewName).getBaseTable();
        else
            return viewName;
    }

    private static String getNotaqlString(Configuration conf, String viewName) throws IOException, ClassNotFoundException {
        VirtualHbaseAdmin virtualHbaseAdmin = new VirtualHbaseAdmin(conf);
        if(virtualHbaseAdmin.getViewDescriptor(viewName)!=null)
            return virtualHbaseAdmin.getViewDescriptor(viewName).getNotaqlString();
        else
            return null;
    }
}
