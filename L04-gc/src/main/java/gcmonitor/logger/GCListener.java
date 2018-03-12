package gcmonitor.logger;

import com.sun.management.GarbageCollectionNotificationInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import javax.management.Notification;
import javax.management.NotificationListener;
import javax.management.openmbean.CompositeData;

public class GCListener implements NotificationListener {

    private final Logger LOG = LogManager.getFormatterLogger(GCListener.class);

    private static int youngCount = 0;
    private static int oldCount = 0;
    private static long totalDuration = 0L;

    public GCListener() {
        LOG.info("———————————————————————————————————————————————");
        LOG.info("| %-11s| %-20s| %-9s|","Generation", "GC name", "Duration");
        LOG.info("———————————————————————————————————————————————");
    }

    @Override
    public void handleNotification(Notification notification, Object handback) {

        if (notification.getType().equals(GarbageCollectionNotificationInfo.GARBAGE_COLLECTION_NOTIFICATION)) {
            GarbageCollectionNotificationInfo notificationInfo =
                    GarbageCollectionNotificationInfo.from((CompositeData) notification.getUserData());

            String generation = null;
            String gcName = notificationInfo.getGcName();
            long duration = notificationInfo.getGcInfo().getDuration();

            switch (notificationInfo.getGcAction()) {
                case "end of major GC":
                    generation = "OLD";
                    oldCount ++;
                    break;

                case "end of minor GC":
                    generation = "YOUNG";
                    youngCount ++;
                    break;

                default:
                    break;
            }

            totalDuration += duration;

            LOG.info("| %-11s| %-20s| %-9d|",generation, gcName, duration);
            LOG.info("———————————————————————————————————————————————");
        }
    }

    public int getYoungCount() {
        return youngCount;
    }

    public int getOldCount() {
        return oldCount;
    }

    public long getTotalDuration() {
        return totalDuration;
    }
}