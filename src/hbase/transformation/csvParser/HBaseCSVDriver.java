package hbase.transformation.csvParser;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.mapreduce.TableMapReduceUtil;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;


/**
 * Created by IntelliJ IDEA 14.0
 *
 * @author sougata
 *
 * @date November 13, 2014
 *
 * @brief Application class to run Map job to parse csv file and store into HBase table.
 */

public class HBaseCSVDriver extends Configured implements Tool

{
    /*private static Configuration conf = null;

    static {
        conf = HBaseConfiguration.create();
        conf.clear();

        conf.set("hbase.zookeeper.quorum", "localhost");
        conf.set("hbase.zookeeper.property.clientPort", "2181");

        try {
            HBaseAdmin.checkHBaseAvailable(conf);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("HBase is running!");
    }*/


    public int run(String[] args) throws Exception {
        if (args.length != 2) {
            System.out.println("Usage: [input/csv] [tableName]");
            System.exit(-1);
        }

        Path inputCSVFilePath = new Path(args[0]);
        String hbaseTableName = args[1];

        Configuration conf = HBaseConfiguration.create();
        //Job job = new Job(conf, inputCSVFilePath + "_" + hbaseTableName);
        //job.setJarByClass(HBaseDriver.class);
        Job job = Job.getInstance(getConf());
        job.setJobName("hbaseCSV");

        job.setMapperClass(HBaseCSVMapperParser.class);
        job.setInputFormatClass(TextInputFormat.class);


        FileInputFormat.setInputPaths(job, inputCSVFilePath);

        // No reducers.  Just write straight to table.  Call initTableReducerJob
        // because it sets up the TableOutputFormat.
        TableMapReduceUtil.initTableReducerJob(hbaseTableName, null, job);
        //job.setNumReduceTasks(0);

        return job.waitForCompletion(true) ? 0: 1;
    }


    public static void main(String[] args) throws Exception {
        HBaseCSVDriver hbaseDriver = new HBaseCSVDriver();
        int res = ToolRunner.run(hbaseDriver, args);
        System.exit(res);
    }
}
