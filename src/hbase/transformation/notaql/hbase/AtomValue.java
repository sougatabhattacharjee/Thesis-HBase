package hbase.transformation.notaql.hbase;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

/**
 * Created by IntelliJ IDEA 14.0
 *
 * @author sougata
 * @date March 05, 2015
 * @brief try to generic the atomvalues as String,Integer or float/double
 */
public class AtomValue<obj> {

    private final obj atomValue;


    //constructor
    public AtomValue(obj atomValue) {
        this.atomValue = atomValue;
    }

    public obj getAtomValue() {
        return this.atomValue;
    }

    @Override
    public String toString() {
        return atomValue.toString();
    }

    public static Long toLong(String string) {
        return Long.valueOf(string).longValue();
    }

    @Override
    public int hashCode() {
        if (atomValue == null) {
            return 0;
        }

        return this.atomValue.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (atomValue.equals(obj)) {
            return true;
        }

        if (obj == null || !(obj instanceof AtomValue)) {
            return false;
        }

        final AtomValue that = (AtomValue) obj;

        return this.atomValue.equals(that.atomValue);
    }

    public static AtomValue<?> parse(String input) {
        if(input.matches("^\\d+$")) {
            try {
                return new AtomValue<Integer>(Integer.parseInt(input));
            }
            catch(Exception ex){
                return new AtomValue<Long>(Long.parseLong(input));
            }
        } else if(input.matches("^\\d+\\.\\d*$")) {
            return new AtomValue<Double>(Double.parseDouble(input));
        }

        return new AtomValue<String>(input);
    }

    public static <T> List<AtomValue<T>> convertList(List<AtomValue<?>> values, Class<T> clazz) {
        final List<AtomValue<T>> result = new LinkedList<AtomValue<T>>();

        for (AtomValue<?> value : values) {
            if(!clazz.isInstance(value.getAtomValue()))
                throw new ClassCastException("List can not be converted. Encountered type: " + value.getClass().getCanonicalName() + " expected: " + clazz.getCanonicalName());

            result.add(new AtomValue<T>(clazz.cast(value.getAtomValue())));
        }

        return result;
    }


    /**
     *   This method returns the maximum value for a given list of AtomValues using Java8 stream.
     *   This function returns Optional<T> to gracefully handle the stream being empty.
     *   AtomValue<?> max = Collections.max(curVal, Comparator.comparingInt(i -> i.hashCode())); //guava code to find max, alternative code, but it will throw NoSuchElementException if the collection is empty.
     *
     * @param values
     */
    public static AtomValue<?> MAX(List<AtomValue<?>> values) {

        Optional<AtomValue<?>> maxValue = values.stream().max(Comparator.comparingInt(i -> i.hashCode()));

        return maxValue.get();
    }





}
