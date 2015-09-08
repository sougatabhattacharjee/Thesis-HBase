package hbase.transformation.connectionPool;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.log4j.Logger;

/**
 * Created by IntelliJ IDEA 14.0
 *
 * @author sougata
 *
 * @date January 28, 2015
 *
 * @brief Application class to establish HBase Connection.
 */
public class HBaseConnection {

    /*
     * @param host   The host address where hbase is located
     */

    private static Configuration conf = null;

    private static final Logger logger = Logger.getLogger(HBaseConnection.class.getName());

    static String host = "localhost";

    static {
        conf = HBaseConfiguration.create();
        conf.clear();

        conf.set("hbase.zookeeper.quorum", host);
        conf.set("hbase.zookeeper.property.clientPort", "2181");
        conf.set("hbase.master", host + ":60000");
        conf.set("hbase.client.scanner.timeout.period", "600000");
        conf.set("hbase.rpc.timeout", "600000");
        conf.set("hbase.rpc.shortoperation.timeout", "600000");

        try {
            HBaseAdmin.checkHBaseAvailable(conf);
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.info("HBase is running!\n");
    }

    public static Configuration getConf() {
        return conf;
    }

    /*
     * check a base table exist or not.
     * @param table     //table name
     */
    public static boolean tableExist(String table) throws Exception {
        final HBaseAdmin admin = new HBaseAdmin(conf);

        if(!admin.tableExists(table)) {
            return false;
        }
        else
            return true;
    }

    public static class TableNotFoundException extends Exception {

        public TableNotFoundException(String message){
            super(message);
        }

    }
}
