package hbase.transformation.notaql.aggregation;

import hbase.transformation.notaql.hbase.AtomValue;
import org.antlr.v4.runtime.misc.ParseCancellationException;

import java.util.List;

/**
 * Created by IntelliJ IDEA 14.0
 *
 * @author sougata
 * @date April 15, 2015
 * @brief This class Provide the count of the values.
 */

public class Count implements AggregationFunction {
    @Override
    public AtomValue<Integer> aggregate(List values, AtomValue... atoms) {
        if(atoms.length > 0) {
            throw new ParseCancellationException("COUNT takes no additional values.");
        }

        return new AtomValue<Integer>(values.size());
    }
}
