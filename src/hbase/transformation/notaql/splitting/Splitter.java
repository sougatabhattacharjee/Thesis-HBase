package hbase.transformation.notaql.splitting;

import hbase.transformation.notaql.hbase.AtomValue;
import java.util.List;

/**
 * Created by IntelliJ IDEA 14.0
 *
 * @author sougata
 * @date July 01, 2015
 * @brief split function interface
 */

public interface Splitter<A> {
    /**
     * Splits the given value into multiple values depending on the arguments.
     * @param baseValue
     * @param args
     * @return
     */
    public List<AtomValue<?>> split(AtomValue<?> baseValue, AtomValue<?>... args);
}
