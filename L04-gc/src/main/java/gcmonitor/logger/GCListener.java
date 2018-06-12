package gcmonitor.logger;

import com.sun.management.GarbageCollectionNotificationInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import javax.management.Notification;
import javax.management.NotificationListener;
import javax.management.openmbean.CompositeData;

public class GCListener implements NotificationListener {

    private final static String ROW_FORMAT = "| %-6s | %-5s | %-3s | %-12s |";
    private final static String SEPARATOR = "———————————————————————————————————————";

    private final Logger LOG = LogManager.getFormatterLogger(GCListener.class);
    private Thread thread;

    private int minute = 0;
    private int youngCount = 0;
    private int oldCount = 0;
    private long totalDuration = 0L;

    public GCListener() {
        LOG.info(SEPARATOR);
        LOG.info(ROW_FORMAT, "Minute", "Young", "Old", "Duration, ms");
        LOG.info(SEPARATOR);
        thread = new Thread(this::runTimer);
        thread.start();
    }

    private void runTimer() {
        while (!thread.isInterrupted()) {
            try {
                Thread.sleep(1000 * 60);
            } catch (InterruptedException e) {
                break;
            }
            minute++;
            LOG.info(ROW_FORMAT, minute, youngCount, oldCount, totalDuration);
            LOG.info(SEPARATOR);
            youngCount = 0;
            oldCount = 0;
            totalDuration = 0L;
        }
    }

    public void logLastMinute() {
        thread.interrupt();
        minute ++;
        LOG.info(ROW_FORMAT, minute, youngCount, oldCount, totalDuration);
        LOG.info(SEPARATOR);
    }

    @Override
    public void handleNotification(Notification notification, Object handback) {

        if (notification.getType().equals(GarbageCollectionNotificationInfo.GARBAGE_COLLECTION_NOTIFICATION)) {
            GarbageCollectionNotificationInfo notificationInfo =
                    GarbageCollectionNotificationInfo.from((CompositeData) notification.getUserData());

            long duration = notificationInfo.getGcInfo().getDuration();

            switch (notificationInfo.getGcAction()) {
                case "end of major GC":
                    oldCount ++;
                    break;

                case "end of minor GC":
                    youngCount ++;
                    break;

                default:
                    break;
            }

            totalDuration += duration;
        }
    }
}