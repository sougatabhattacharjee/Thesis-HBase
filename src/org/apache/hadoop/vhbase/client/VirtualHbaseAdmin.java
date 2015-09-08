package org.apache.hadoop.vhbase.client;

import hbase.transformation.hbase.HBaseViewDefinition;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.MasterNotRunningException;
import org.apache.hadoop.hbase.ZooKeeperConnectionException;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.*;

/**
 * Created by IntelliJ IDEA 14.0
 *
 * @author sougata
 * @date April 29, 2015
 * @brief Provides an interface to manage Virtual HBase table metadata + general
 * administrative functions.  Use VirtualHBaseAdmin to create, edit, delete, list views.
 */
public class VirtualHbaseAdmin extends HBaseAdmin {

    private static final Logger logger = Logger.getLogger(VirtualHbaseAdmin.class.getName());

    private static LinkedHashMap<String, HViewDescriptor> storeView = new LinkedHashMap<String, HViewDescriptor>();

    private volatile Configuration conf;

    public VirtualHbaseAdmin(Configuration conf) throws MasterNotRunningException, ZooKeeperConnectionException, IOException {
       super(conf);
    }

    /**
     * Creates a new view.
     *
     *
     * @param desc view descriptor for table
     *
     * @throws IllegalArgumentException if the table name is reserved
     * @throws MasterNotRunningException if master is not running
     * @throws IOException if a remote or network exception occurs
     */
    public void createView(HViewDescriptor desc) throws Exception {

        //before put, get the existing map from disc
        storeView = ViewManager.readView();

        if(this.viewExists(desc.getViewName()))
            throw new Exception("View already Exist!!");
        else {
            //checking the notaql syntax
            desc.parseNotaQL(desc.getNotaqlString());

            //stupid implementation, but to avoid serialization issue had to do this
            storeView.put(desc.getViewName(),new HViewDescriptor(desc.getViewName(),desc.getBaseTable(),desc.getNotaqlString()));
        }

        ViewManager.saveView(storeView);
    }

    /**
     * Delete an existing view.
     *
     *
     * @param viewName view descriptor for table
     *
     * @throws IllegalArgumentException if the table name is reserved
     * @throws MasterNotRunningException if master is not running
     * @throws IOException if a remote or network exception occurs
     */
    public void deleteView(final String viewName) throws Exception {

        //before delete, get the existing map from disc
        storeView = ViewManager.readView();

        if(this.viewExists(viewName))
            storeView.remove(viewName);
        else
            throw new Exception("View Doesn't Exist!!");

        ViewManager.saveView(storeView);
    }

    /**
     * Modify an existing view, simply replace the old descriptor with new one.
     *
     *
     * @param desc view descriptor for table
     *
     * @throws IllegalArgumentException if the table name is reserved
     * @throws MasterNotRunningException if master is not running
     * @throws IOException if a remote or network exception occurs
     */
    public void modifyView(HViewDescriptor desc) throws Exception {

        //before put, get the existing map from disc
        storeView = ViewManager.readView();

        //checking the notaql syntax
        desc.parseNotaQL(desc.getNotaqlString());

        //stupid implementation, but to avoid serialization issue had to do this
        storeView.put(desc.getViewName(),new HViewDescriptor(desc.getViewName(),desc.getBaseTable(),desc.getNotaqlString()));
        ViewManager.saveView(storeView);
    }

    /**
     * Return the list of HViewDescriptor[]
     *
     *
     * @param
     *
     * @throws IllegalArgumentException if the table name is reserved
     * @throws MasterNotRunningException if master is not running
     * @throws IOException if a remote or network exception occurs
     */
    public HViewDescriptor[] listViews() throws IOException, ClassNotFoundException {

        storeView =  ViewManager.readView();

        return storeView.values().toArray(new HViewDescriptor[storeView.size()]);
    }

    /**
     * Check a view exits or not.
     *
     *
     * @param viewName view descriptor for table
     *
     * @throws IllegalArgumentException if the table name is reserved
     * @throws MasterNotRunningException if master is not running
     * @throws IOException if a remote or network exception occurs
     */
    public boolean viewExists(final String viewName) throws IOException, ClassNotFoundException {

        //before put, get the existing map from disc
        storeView = ViewManager.readView();

        return storeView.containsKey(viewName);

    }

    /**
     * Return the view Descriptor
     *
     *
     * @param viewName view descriptor for table
     *
     * @throws IllegalArgumentException if the table name is reserved
     * @throws MasterNotRunningException if master is not running
     * @throws IOException if a remote or network exception occurs
     */
    public HViewDescriptor getViewDescriptor(final String viewName) throws IOException, ClassNotFoundException {

        //before put, get the existing map from disc
        storeView = ViewManager.readView();

        return storeView.get(viewName);
    }

}
