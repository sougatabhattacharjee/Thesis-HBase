package hbase.transformation.notaql.hbase;

/**
 * Created by IntelliJ IDEA 14.0
 *
 * @author sougata
 * @date March 05, 2015
 * @brief Application class to test
 */
public class RowColValue {

    private static Column _c;
    private static AtomValue<?> _v;
    private static Row _r;

    public RowColValue(Column _c, AtomValue<?> _v, Row _r) {
        this._c = _c;
        this._v = _v;
        this._r = _r;
    }

    public RowColValue() {
        this._c = null;
        this._v = null;
        this._r = null;
    }

    public Column get_c() {
        return _c;
    }

    public AtomValue<?> get_v() {
        return _v;
    }

    public Row get_r() {
        return _r;
    }

    public static void setC(Column c) {
        _c = c;
    }

    public static void setV(AtomValue<?> v) {
        _v = v;
    }

    public static void setR(Row r) {
        _r = r;
    }
}
