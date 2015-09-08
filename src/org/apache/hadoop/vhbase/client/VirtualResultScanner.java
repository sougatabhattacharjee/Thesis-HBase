package org.apache.hadoop.vhbase.client;

import hbase.transformation.notaql.NotaQLProcess;
import hbase.transformation.notaql.NotaQLTransformation;
import hbase.transformation.notaql.hbase.InputTable;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by IntelliJ IDEA 14.0
 *
 * @author sougata
 * @date May 18, 2015
 * @brief Application class to scan the HBase Views
 */
public class VirtualResultScanner implements ResultScanner {

    private Iterator<Result> results;
    private ResultScanner resultScanner;

    public VirtualResultScanner(ResultScanner rs, NotaQLProcess notaql) throws IOException {
        resultScanner = rs;

        if(notaql==null) {
            return;
        }
        notaql.setTransformationContext(notaql.getNotaQLContext().transformation());
        notaql.setNotaQLTransform(new NotaQLTransform(notaql.getTransformationContext()));
        this.results = notaql.getNotaQLTransform().transform(rs);
    }

    /**
     * Grab the next row's worth of values. The scanner will return a Result.
     *
     * @return Result object if there is another row, null if the scanner is
     * exhausted.
     * @throws java.io.IOException e
     */
    @Override
    public Result next() throws IOException {
        if(results==null)
            return resultScanner.next();
        return results.next();
    }

    /**
     * @param nbRows number of rows to return
     * @return Between zero and <param>nbRows</param> Results
     * @throws java.io.IOException e
     */
    @Override
    public Result[] next(int nbRows) throws IOException {
        if(results==null)
            return resultScanner.next(nbRows);
        return new Result[0];
    }

    /**
     * Closes the scanner and releases any resources it has allocated
     */
    @Override
    public void close() {
        resultScanner.close();
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<Result> iterator() {
        if(results==null)
            return resultScanner.iterator();
        return results;
    }
}
