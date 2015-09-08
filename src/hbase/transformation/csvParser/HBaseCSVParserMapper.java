package hbase.transformation.csvParser;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.TableMapReduceUtil;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.util.GenericOptionsParser;

import java.io.IOException;

/**
 * Created by IntelliJ IDEA 14.0
 *
 * @author sougata
 *
 * @date November 14, 2014
 *
 * @brief Application class to parse csv file and store into HBase table, using MapReduce/Hadoop.
 * hadoop dfs -copyFromLocal /home/sougata/Employee_notaql.csv /usr/local/hadoop/input
 * hadoop jar hbaseCSVMapper.jar /usr/local/hadoop/input/Employee_notaql.csv EmployeeNotaQL
 */



public class HBaseCSVParserMapper {
    private static final String NAME = "HBaseCSVUploaderMapper";

    static class Uploader
            extends Mapper<LongWritable, Text, ImmutableBytesWritable, Put> {

        private long checkpoint = 100;
        private long counter = 0;

//        String qualifier1 = "";
//        String qualifier2 = "";
//        String qualifier3 = "";
        String [] qualifierNames;

        @Override
        public void map(LongWritable key, Text line, Context context)
                throws IOException {

            // Input is a CSV file
            // Each map() is a single line, where the key is the line number
            // Each line is comma-delimited; row,family,qualifier,value

            // Split CSV line
            String [] values = line.toString().split(",");
            qualifierNames = new String[values.length-1];
            int j = 0;
            int v=1;


//	        if(values.length != 4) {
//	          return;
//	        }

            // Extract each value
//            System.out.println(">>>>>>"+counter);
            if(counter==0)
            {
                for(int i=1;i<values.length;i++)
                {
                    qualifierNames[j++] = values[i];
                }
//                qualifier1 = values[1];
//                qualifier2 = values[2];
//                qualifier3 = values[3];
            }

            else
            {
                byte [] row = Bytes.toBytes(values[0]);
//	        byte [] family = Bytes.toBytes(values[1]);
//	        byte [] qualifier = Bytes.toBytes(values[2]);
//	        byte [] value = Bytes.toBytes(values[3]);

                // Create Put
                Put put = new Put(row);
//                for(int q=0;q<qualifierNames.length;q++) {
//                    put.add(Bytes.toBytes("Information"), Bytes.toBytes(qualifierNames[q]), Bytes.toBytes(values[v++]));
//                }
                    put.add(Bytes.toBytes("Information"), Bytes.toBytes(qualifierNames[0]), Bytes.toBytes(values[1]));
                    put.add(Bytes.toBytes("Information"), Bytes.toBytes(qualifierNames[1]), Bytes.toBytes(values[2]));
                    put.add(Bytes.toBytes("Information"), Bytes.toBytes(qualifierNames[2]), Bytes.toBytes(values[3]));


                // Uncomment below to disable WAL. This will improve performance but means
                // you will experience data loss in the case of a RegionServer crash.
                // put.setWriteToWAL(false);

                try {
                    context.write(new ImmutableBytesWritable(row), put);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            counter++;
            // Set status every checkpoint lines
//	       if(++count % checkpoint == 0) {
//	         context.setStatus("Emitting Put " + count);
//	       }
        }
    }

    /**
     * Job configuration.
     */
    public static Job configureJob(Configuration conf, String [] args)
            throws IOException {
        Path inputPath = new Path(args[0]);
        String tableName = args[1];
        Job job = new Job(conf, NAME + "_" + tableName);
        job.setJarByClass(Uploader.class);
        //FileInputFormat.setInputPaths(job, inputPath);

        job.setInputFormatClass(TextInputFormat.class);
        FileInputFormat.setInputPaths(job, inputPath);
        job.setMapperClass(Uploader.class);

        // No reducers.  Just write straight to table.  Call initTableReducerJob
        // because it sets up the TableOutputFormat.
        TableMapReduceUtil.initTableReducerJob(tableName, null, job);
        job.setNumReduceTasks(0);
        return job;
    }

    /**
     * Main entry point.
     *
     * @param args  The command line parameters.
     * @throws Exception When running the job fails.
     */
    public static void main(String[] args) throws Exception {
        Configuration conf = HBaseConfiguration.create();
        String[] otherArgs = new GenericOptionsParser(conf, args).getRemainingArgs();
        if(otherArgs.length != 2) {
            System.err.println("Wrong number of arguments: " + otherArgs.length);
            System.err.println("Usage: " + NAME + " <input> <tablename>");
            System.exit(-1);
        }
        Job job = configureJob(conf, otherArgs);
        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}
