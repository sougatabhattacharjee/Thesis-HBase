package hbase.transformation.utils;

import hbase.transformation.hbase.HBaseViewDefinition;
import org.apache.log4j.Logger;

import java.io.*;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA 14.0
 *
 * @author sougata
 * @date March 11, 2015
 * @brief Application class to save views in a file for future use
 */
public class ViewStore {

    private static final Logger logger = Logger.getLogger(ViewStore.class.getName());

    public static void saveView(LinkedHashMap<String, HBaseViewDefinition> virtualTableMappingNotaQL) throws IOException, ClassNotFoundException
    {
        File file = new File(System.getProperty("user.home")+File.separator+"log"+File.separator+"view");
        FileOutputStream f = new FileOutputStream(file);
        ObjectOutputStream s = new ObjectOutputStream(f);
        s.writeObject(virtualTableMappingNotaQL);
        s.close();
    }

    public static LinkedHashMap<String, HBaseViewDefinition> readView() throws IOException, ClassNotFoundException
    {
        if(new File(System.getProperty("user.home") + File.separator + "log" + File.separator + "view").isFile()) {
            File file = new File(System.getProperty("user.home") + File.separator + "log" + File.separator + "view");
            FileInputStream f = new FileInputStream(file);
            ObjectInputStream s = new ObjectInputStream(f);
            LinkedHashMap<String, HBaseViewDefinition> fileObj2 = (LinkedHashMap<String, HBaseViewDefinition>) s.readObject();
            s.close();
            return fileObj2;
        }
        //if it is called before saving the view on the disc then return empty Map
        else {
            LinkedHashMap<String, HBaseViewDefinition> fileObj2 = new LinkedHashMap<String, HBaseViewDefinition>();
            logger.error("java.io.FileNotFoundException:" +System.getProperty("user.home") + File.separator + "log" + File.separator + "view"+ " (No such file or directory)");
            return fileObj2;
        }
    }

}
