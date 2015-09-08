package hbase.transformation;


import hbase.transformation.hbase.HBaseVShell;

import java.io.IOException;

/**
 * Created by IntelliJ IDEA 14.0
 *
 * @author sougata
 * @date February 04, 2015
 * @brief Main class to start the VShell
 * java -jar Thesis/HBaseNotaQL/target/HBaseNotaQL-1.0-SNAPSHOT.jar
 */
public class Main {

    public static void main(String[] args) throws IOException {
        HBaseVShell.processInputString();
    }
}
