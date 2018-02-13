package handler.nodes;

import java.util.*;
import handler.JsonHandler;
import handler.recorder.RecordBuilder;

class ListNode extends AbstractNode {

    ListNode(Object object) {
        super(object);
    }

    @Override
    public String write() {
        RecordBuilder record = new RecordBuilder();
        record.createArray();
        for (Object o : (List) object) {
            record.addElement(JsonHandler.write(o));
        }
        return record.build();
    }

    static boolean isSupported(Object object) {
        return getTypes().contains(object.getClass());
    }

    protected static Set<Class> getTypes() {
        Set<Class> cls = new HashSet<>();
        cls.add(ArrayList.class);
        cls.add(LinkedList.class);
        return cls;
    }
}
