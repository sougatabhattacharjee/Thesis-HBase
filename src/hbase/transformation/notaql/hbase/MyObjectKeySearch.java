package hbase.transformation.notaql.hbase;
import java.util.HashMap;
import java.util.Set;
/**
 * Created by IntelliJ IDEA 14.0
 *
 * @author sougata
 * @date April 10, 2015
 * @brief Application class to test
 */
public class MyObjectKeySearch {
    public static void main(String a[]){

        HashMap<Price, String> hm = new HashMap<Price, String>();
        hm.put(new Price("Susi", "Information:salary"), "Banana");
        hm.put(new Price("Susi", "Information:salary"), "Apple");
        hm.put(new Price("Susi", "Information:salary"), "Orange");
        printMap(hm);
        Price key = new Price("Susi", "Information:salary");
        System.out.println("Does key available? "+hm.containsKey(key));
    }

    public static void printMap(HashMap<Price, String> map){

        Set<Price> keys = map.keySet();
        for(Price p:keys){
            System.out.println(p+"==>"+map.get(p));
        }
    }
}

class Price{

    private String item;
    private String price;

    public Price(String itm, String pr){
        this.item = itm;
        this.price = pr;
    }

    public int hashCode(){
        System.out.println("In hashcode");
        int hashcode = 0;
        hashcode = price.hashCode();
        hashcode += item.hashCode();
        return hashcode;
    }

    public boolean equals(Object obj){
        System.out.println("In equals");
        if (obj instanceof Price) {
            Price pp = (Price) obj;
            return (pp.item.equals(this.item) && pp.price == this.price);
        } else {
            return false;
        }
    }

    public String getItem() {
        return item;
    }
    public void setItem(String item) {
        this.item = item;
    }
    public String getPrice() {
        return price;
    }
    public void setPrice(String price) {
        this.price = price;
    }

    public String toString(){
        return "item: "+item+"  price: "+price;
    }

}
