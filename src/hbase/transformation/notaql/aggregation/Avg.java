package hbase.transformation.notaql.aggregation;

import hbase.transformation.notaql.hbase.AtomValue;
import org.antlr.v4.runtime.misc.ParseCancellationException;

import java.util.List;

/**
 * Created by IntelliJ IDEA 14.0
 *
 * @author sougata
 * @date April 15, 2015
 * @brief This class Provide the average of the given values. All values must be numerical.
 */

public class Avg implements AggregationFunction<Number> {

    @Override
    public AtomValue<Double> aggregate(List<AtomValue<Number>> values, AtomValue<?>... atoms) {
        if(atoms.length > 0) {
            throw new ParseCancellationException("AVG takes no additional values.");
        }

        if(values.size() == 0)
            return new AtomValue<Double>(0.0);

        Double sum = 0.0;
        for (AtomValue<Number> value : values) {
            sum += value.getAtomValue().doubleValue();
        }

        return new AtomValue<Double>(sum / values.size());
    }
}
