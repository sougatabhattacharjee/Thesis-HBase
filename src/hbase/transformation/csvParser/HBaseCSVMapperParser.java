package hbase.transformation.csvParser;

import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;


/**
 * Created by IntelliJ IDEA 14.0
 *
 * @author sougata
 *
 * @date November 13, 2014
 *
 * @brief Mapper class to Parse csv file and store into HBase table.
 */

public class HBaseCSVMapperParser extends Mapper<LongWritable, Text, ImmutableBytesWritable, Put> {


    private long counter = 0;
    String qualifier1 = "";
    String qualifier2 = "";
    String qualifier3 = "";

    @Override
    public void map(LongWritable key, Text line, Context context)
            throws IOException {


        String [] values = line.toString().split(",");

        if(counter==0)
        {
            qualifier1 = values[1];
            qualifier2 = values[2];
            qualifier3 = values[3];
        }

        else
        {
            byte [] row = Bytes.toBytes(values[0]);


            Put put = new Put(row);
            put.add(Bytes.toBytes("Information"), Bytes.toBytes(qualifier1), Bytes.toBytes(values[1]));
            put.add(Bytes.toBytes("Information"), Bytes.toBytes(qualifier2), Bytes.toBytes(values[2]));
            put.add(Bytes.toBytes("Information"), Bytes.toBytes(qualifier3), Bytes.toBytes(values[3]));

            try {
                context.write(new ImmutableBytesWritable(row), put);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        counter++;
    }

   }
