package hbase.transformation.notaql.splitting;

import hbase.transformation.notaql.hbase.AtomValue;
import org.antlr.v4.runtime.misc.ParseCancellationException;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by IntelliJ IDEA 14.0
 *
 * @author sougata
 * @date July 01, 2015
 * @brief Application class processes functionality to evaluate split functions.
 */

public class Split implements Splitter<Object> {
    @Override
    public List<AtomValue<?>> split(AtomValue<?> baseValue, AtomValue<?>... args) {
        if(args.length > 1)
            throw new ParseCancellationException("Split only takes a maximum of 2 arguments!");

        final String pattern;
        if(args.length >= 1)
            pattern = args[0].toString();
        else
            pattern = " ";

        final String[] splits = baseValue.toString().split(pattern);
        final List<AtomValue<?>> result = new LinkedList<AtomValue<?>>();
        for (String s : splits) {
            result.add(new AtomValue<String>(s));
        }

        return result;
    }
}
