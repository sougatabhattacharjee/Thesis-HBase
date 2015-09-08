package hbase.transformation.csvParser;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA 14.0
 *
 * @author sougata
 *
 * @date November 12, 2014
 *
 * @brief Application class to parse csv file and store into HBase table. Input parameter is the path of CSV file.
 */


public class HBaseCSVParser {

    private static Configuration conf = null;
    /**
     * Initialization
     */
    static {
        conf = HBaseConfiguration.create();
        conf.clear();

        conf.set("hbase.zookeeper.quorum", "localhost");
        conf.set("hbase.zookeeper.property.clientPort", "2181");

        try {
            HBaseAdmin.checkHBaseAvailable(conf);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("HBase is running!");
    }

    public void csvParse(String CSVPath) {
        String csvFileToParse = CSVPath;    //"/home/sougata/Employee.csv";

        String tableName = new File(csvFileToParse).getName().substring(0,
                new File(csvFileToParse).getName().indexOf("."));
        tableName = "emp11";

        BufferedReader fileReader = null;
        String line = "";
        final String DELIMITER = ",";
        int iterator = 0;
        String[] familys = null;
        try {

            fileReader = new BufferedReader(new FileReader(csvFileToParse));
            while ((line = fileReader.readLine()) != null) {
                if (iterator == 0) {
                    familys = line.split(DELIMITER);
                    HBaseCSVParser.creatTable(tableName, familys);
                } else {
                    String[] employeeTokens = line.split(DELIMITER);
                    for (int i = 2; i <= 3; i++)
                        HBaseCSVParser.addRecord(tableName, employeeTokens[0],
                                familys[i], employeeTokens[1],
                                employeeTokens[i]);

                }

                iterator++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fileReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Create a table
     */
    public static void creatTable(String tableName, String[] familys)
            throws Exception {
        HBaseAdmin admin = new HBaseAdmin(conf);
        if (admin.tableExists(tableName)) {
            System.out.println("table already exists!!!" + tableName);
        } else {
            @SuppressWarnings("deprecation")
            HTableDescriptor tableDesc = new HTableDescriptor(tableName);
            for (int i = 2; i < familys.length; i++) {
                tableDesc.addFamily(new HColumnDescriptor(familys[i]));
            }
            admin.createTable(tableDesc);
            System.out.println("create table " + tableName + " ok.");
        }
    }

    /**
     * Put (or insert) a row
     */
    public static void addRecord(String tableName, String rowKey,
                                 String family, String qualifier, String value) throws Exception {
        try {
            HTable table = new HTable(conf, tableName);
            Put put = new Put(Bytes.toBytes(rowKey));
            put.add(Bytes.toBytes(family), Bytes.toBytes(qualifier),
                    Bytes.toBytes(value));
            table.put(put);
            System.out.println("insert recored " + rowKey + " to table "
                    + tableName + " ok.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        HBaseCSVParser object = new HBaseCSVParser();
        object.csvParse(args[0]);
    }

}
