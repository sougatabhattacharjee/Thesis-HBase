package hbase.transformation.notaql.aggregation;

import hbase.transformation.notaql.hbase.AtomValue;

import java.util.List;

/**
 * Created by IntelliJ IDEA 14.0
 *
 * @author sougata
 * @date April 07, 2015
 * @brief Application class to test
 */
public interface AggregationFunction<data> {
    public AtomValue<?> aggregate(List<AtomValue<data>> values, AtomValue<?>... atoms);
}
