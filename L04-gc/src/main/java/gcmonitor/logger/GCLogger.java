package gcmonitor.logger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.management.NotificationEmitter;
import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.util.Date;
import java.util.List;

public class GCLogger {

    public GCLogger() {

    }

    private final Logger LOG = LogManager.getFormatterLogger(GCLogger.class);

    private GCListener listener;

    private Date timeStart;

    public void startMonitoring() {
        listener = new GCListener();
        timeStart = new Date();
        List<GarbageCollectorMXBean> gcBeans = ManagementFactory.getGarbageCollectorMXBeans();
        for (GarbageCollectorMXBean gcBean : gcBeans) {
            NotificationEmitter emitter = (NotificationEmitter) gcBean;
            emitter.addNotificationListener(listener, null, null);
        }
    }

    public void printFinalInfo() {
        LOG.info("Report:\n" +
                        "YOUNG: %d\n" +
                        "OLD: %d\n" +
                        "total duration: %dms\n" +
                        "total application live time: %ds",
                listener.getYoungCount(), listener.getOldCount(), listener.getTotalDuration(),
                (new Date().getTime()-timeStart.getTime())/1000);
    }
}