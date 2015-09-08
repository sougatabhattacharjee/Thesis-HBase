package hbase.transformation.notaql.aggregation;

import hbase.transformation.notaql.hbase.AtomValue;
import org.antlr.v4.runtime.misc.ParseCancellationException;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;


/**
 * Created by IntelliJ IDEA 14.0
 *
 * @author sougata
 * @date April 15, 2015
 * @brief This class Provide the minimum value.
 */

@SuppressWarnings("unchecked")
public class Min implements AggregationFunction<Comparable> {
    @Override
    public AtomValue<Comparable> aggregate(List<AtomValue<Comparable>> values, AtomValue<?>... atoms) {
        if(atoms.length > 0) {
            throw new ParseCancellationException("MIN takes no additional values.");
        }

        try {
            return Collections.min(values, new Comparator<AtomValue<Comparable>>() {
                @Override
                public int compare(AtomValue<Comparable> comparableValue, AtomValue<Comparable> comparableValue2) {
                    try {
                        return comparableValue.getAtomValue().compareTo(comparableValue2.getAtomValue());
                    } catch (ClassCastException e) {
                        throw new ParseCancellationException("The values inside a group are not comparable to each " +
                                "other which is why a MIN cannot be determined.");
                    }
                }
            });
        } catch (NoSuchElementException e) {
            return null;
        }
    }
}
