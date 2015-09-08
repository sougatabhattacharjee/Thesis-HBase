package hbase.transformation.notaql.aggregation;

import hbase.transformation.notaql.hbase.AtomValue;
import org.antlr.v4.runtime.misc.ParseCancellationException;

import java.util.List;

/**
 * Created by IntelliJ IDEA 14.0
 *
 * @author sougata
 * @date April 07, 2015
 * @brief Application class to test
 */
public class Sum implements AggregationFunction<Number> {
        @Override
        public AtomValue<? extends Number> aggregate(List<AtomValue<Number>> values, AtomValue<?>... atoms) {
            if(atoms.length > 0) {
                throw new ParseCancellationException("SUM takes no additional values.");
            }

            Double sum = 0.0;
            for (AtomValue<? extends Number> value : values) {
                sum += value.getAtomValue().doubleValue();
            }

            return new AtomValue<Double>(sum);
        }
    }
