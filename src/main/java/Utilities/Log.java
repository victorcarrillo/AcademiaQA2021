package Utilities;

import org.apache.log4j.Logger;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Log {

    static {
        SimpleDateFormat df = new SimpleDateFormat("MMM-dd-yyyy_HH-mm-ss");
        System.setProperty("current.date.time", df.format(new Date()));
    }

    // Initialize Log4j instance
    private static Logger log = Logger.getLogger(Log.class.getName());

    // Starting tests
    public static void startLog(String testClassName) {
        log.info("Starting Testing ..." + testClassName);
    }

    // Ending testing
    public static void endLog(String testClassName) {
        log.info("Ending Testing ..." + testClassName);
    }

    // Info level logs
    public static void info(String message) {
        log.info(message);
    }

    // Warn level logs
    public static void warn(String message) {
        log.warn(message);
    }

    // Fatal level logs
    public static void fatal(String message) {
        log.fatal(message);
    }

    // Debug level logs
    public static void debug(String message) {
        log.debug(message);
    }

}
