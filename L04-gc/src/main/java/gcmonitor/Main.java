package gcmonitor;
/*
-Xms128m -Xmx128m
-XX:+UseSerialGC

-Xms128m -Xmx128m
-XX:+UseParallelGC

-Xms128m -Xmx128m
-XX:+UseConcMarkSweepGC

-Xms128m -Xmx128m
-XX:+UseG1GC
*/
import gcmonitor.logger.GCLogger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.management.ManagementFactory;

public class Main {

    private static final Logger LOG = LogManager.getFormatterLogger(Main.class);

    public static void main(String[] args) {

        LOG.info("***Started with pid = " + ManagementFactory.getRuntimeMXBean().getName() + "***");

        GCLogger logger = new GCLogger();
        logger.startMonitoring();

        Looper looper = new Looper();

        try {
            looper.run();
        } catch (OutOfMemoryError err) {
            logger.stopMonitoring();
        }
    }
}
