package org.apache.hadoop.vhbase.client;

import hbase.transformation.notaql.hbase.AtomValue;
import hbase.transformation.notaql.hbase.Column;
import hbase.transformation.notaql.hbase.InputTableRow;
import hbase.transformation.notaql.hbase.Row;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.NavigableMap;

/**
 * Created by IntelliJ IDEA 14.0
 *
 * @author sougata
 * @date June 18, 2015
 * @brief Application class to test
 */
public class BaseTable implements InputTableRow {

    private final Iterator<Result> iterator;

    public BaseTable(ResultScanner scanner) throws IOException {
        this.iterator = scanner.iterator();
    }

    @Override
    public Iterator<Row> iterator() {
        return new Iterator<Row>() {
            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public Row next() {
                final Result result = iterator.next();

                final NavigableMap<byte[], NavigableMap<byte[], byte[]>> inCols = result.getNoVersionMap();
                final String rowKey = Bytes.toString(result.getRow());
                final Row outRow = new Row(AtomValue.parse(rowKey));

                int i = 0;

                for (Map.Entry<byte[], NavigableMap<byte[], byte[]>> colFamily : inCols.entrySet()) {

                    final AtomValue<?> colFamilyName = AtomValue.parse(Bytes.toString(colFamily.getKey()));

                    for (Map.Entry<byte[], byte[]> col : colFamily.getValue().entrySet()) {

                        final AtomValue<?> timeStamp = AtomValue.parse(result.raw()[i++].getTimestamp()+"");
                        final AtomValue<?> columnName = AtomValue.parse(Bytes.toString(col.getKey()));
                        final AtomValue<?> value = AtomValue.parse(Bytes.toString(col.getValue()));

                        outRow.put(new Column(colFamilyName, columnName, timeStamp), value);
                    }
                }
                return outRow;
            }

            @Override
            public void remove() {
                iterator.remove();
            }
        };
    }
}
