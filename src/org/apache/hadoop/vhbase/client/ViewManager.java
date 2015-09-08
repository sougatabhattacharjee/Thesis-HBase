package org.apache.hadoop.vhbase.client;

import org.apache.log4j.Logger;

import java.io.*;
import java.util.LinkedHashMap;

/**
 * Created by IntelliJ IDEA 14.0
 *
 * @author sougata
 * @date March 11, 2015
 * @brief Application class to save views in a file for future use
 */
public class ViewManager {

    private static final Logger logger = Logger.getLogger(ViewManager.class.getName());

    public static void saveView(LinkedHashMap<String, HViewDescriptor> virtualTableMappingNotaQL) throws IOException, ClassNotFoundException
    {
        File file = new File(System.getProperty("user.home")+File.separator+"log"+File.separator+"viewManager");
        FileOutputStream f = new FileOutputStream(file);
        ObjectOutputStream s = new ObjectOutputStream(f);
        s.writeObject(virtualTableMappingNotaQL);
        s.close();
    }

    public static LinkedHashMap<String, HViewDescriptor> readView() throws IOException, ClassNotFoundException
    {
        if(new File(System.getProperty("user.home") + File.separator + "log" + File.separator + "viewManager").isFile()) {
            File file = new File(System.getProperty("user.home") + File.separator + "log" + File.separator + "viewManager");
            FileInputStream f = new FileInputStream(file);
            ObjectInputStream s = new ObjectInputStream(f);
            LinkedHashMap<String, HViewDescriptor> fileObj2 = (LinkedHashMap<String, HViewDescriptor>) s.readObject();
            s.close();
            return fileObj2;
        }
        //if it is called before saving the view on the disc then return empty Map
        else {
            LinkedHashMap<String, HViewDescriptor> fileObj2 = new LinkedHashMap<String, HViewDescriptor>();
            logger.error("java.io.FileNotFoundException:" +System.getProperty("user.home") + File.separator + "log" + File.separator + "viewManager"+ " (No such file or directory)");
            return fileObj2;
        }
    }

}
