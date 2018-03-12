package gcmonitor;

import java.util.ArrayList;
import java.util.List;

public class Looper {

    @SuppressWarnings({"InfiniteLoopStatement"})
    public void run() {

        List<String> list = new ArrayList<>();

        while (true) {
            for (int i = 0; i < 20_000; i++) {
                list.add(String.valueOf(i));
            }

            for (int i = 0; i < 2000; i++) {
                list.remove(0);
            }
        }
    }
}
