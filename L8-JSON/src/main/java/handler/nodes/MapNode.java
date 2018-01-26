package handler.nodes;

import java.util.*;
import handler.JsonHandler;
import handler.recorder.RecordBuilder;

class MapNode extends AbstractNode {

    MapNode(Object object) {
        super(object);
    }

    @Override
    public String write() {
        RecordBuilder record = new RecordBuilder();
        record.createMap();
        for (Object o : ((Map) object).keySet()) {
            record.addKeyValue(o.toString(), JsonHandler.write(((Map) object).get(o)));
        }
        return record.build();
    }

    static boolean isSupported(Object object) {
        return getTypes().contains(object.getClass());
    }

    protected static Set<Class> getTypes() {
        Set<Class> cls = new HashSet<>();
        cls.add(HashMap.class);
        cls.add(SortedMap.class);
        cls.add(TreeMap.class);
        return cls;
    }
}
