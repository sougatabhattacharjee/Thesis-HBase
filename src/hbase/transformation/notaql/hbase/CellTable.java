package hbase.transformation.notaql.hbase;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * Created by IntelliJ IDEA 14.0
 *
 * @author sougata
 * @date April 08, 2015
 * @brief This class stores the values in list form
 */
public class CellTable<R, C, V>{

    private Map<Cell<R,C>,List<V>> table;

    public CellTable() {
        this.table = new LinkedHashMap<>();
    }

   public void append(R r, C c, V v){

       Cell cl = new Cell(r,c);

       List<V> curVs;

       if(!this.table.containsKey(cl)){
           curVs = new LinkedList<V>();
           this.table.put(cl,curVs);
       }
       else{
            curVs = table.get(cl);

           if (curVs == null) {
               curVs = new LinkedList<V>();
               curVs.add(v);
               this.table.put(cl,curVs);
           }

           if(!curVs.contains(v)){
               curVs.add(v);
               this.table.put(cl,curVs);
           }
       }
   }


    public class Cell<R,C> {

        private final R rowKey;
        private final C columnKeyID;

        public Cell(R rowKey, C columnKeyID) {
            this.rowKey = rowKey;
            this.columnKeyID = columnKeyID;
        }

        public R getRowKey() {
            return this.rowKey;
        }

        public C getColumnKeyID() {
            return this.columnKeyID;
        }
    }
}

