package handler.nodes;

import java.util.*;
import handler.JsonHandler;
import handler.recorder.RecordBuilder;

public class SetNode extends AbstractNode {

    public static final Set<Class> TYPES = getTypes();

    public SetNode(Object object) {
        super(object);
    }

    @Override
    public String write() {
        RecordBuilder record = new RecordBuilder();
        record.createArray();
        for (Object o : (Set) object) {
            record.addElement(JsonHandler.write(o));
        }
        return record.build();
    }

    protected static Set<Class> getTypes() {
        Set<Class> cls = new HashSet<>();
        cls.add(HashSet.class);
        cls.add(SortedSet.class);
        cls.add(TreeSet.class);
        return cls;
    }
}
