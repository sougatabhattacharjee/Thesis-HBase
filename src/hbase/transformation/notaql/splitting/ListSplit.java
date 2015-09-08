package hbase.transformation.notaql.splitting;


import hbase.transformation.notaql.hbase.AtomValue;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by IntelliJ IDEA 14.0
 *
 * @author sougata
 * @date July 01, 2015
 * @brief Application class processes functionality to evaluate listSplit functions.
 */
public class ListSplit extends Split {
    @Override
    public List<AtomValue<?>> split(AtomValue<?> baseValue, AtomValue<?>... args) {
        final List<AtomValue<?>> split = super.split(baseValue, new AtomValue<String>(", "));
        final List<AtomValue<?>> result = new LinkedList<AtomValue<?>>();

        for(int i = 0; i < split.size(); i++) {
            AtomValue<?> value = split.get(i);
            if(i == 0)
                value = new AtomValue<String>(value.toString().substring(1));
            if(i == split.size() - 1)
                value = new AtomValue<String>(value.toString().substring(0, value.toString().length() - 1));

            result.add(value);
        }

        return result;
    }
}
