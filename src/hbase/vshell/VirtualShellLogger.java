package hbase.vshell;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by IntelliJ IDEA 14.0
 *
 * @author sougata
 * @date May 11, 2015
 * @brief Application class to test
 */
public class VirtualShellLogger {

    static Date date = new Date();
    static SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
    public static File log = new File(System.getProperty("user.home")+File.separator+"log4j"+File.separator+"hbaseVShell-log."+ft.format(date));

}
