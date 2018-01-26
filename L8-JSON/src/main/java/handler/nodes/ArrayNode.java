package handler.nodes;

import java.lang.reflect.Array;
import handler.JsonHandler;
import handler.recorder.RecordBuilder;

class ArrayNode extends AbstractNode {

    ArrayNode(Object object) {
        super(object);
    }

    @Override
    public String write() {
        RecordBuilder record = new RecordBuilder();
        record.createArray();
        for (int i = 0; i < Array.getLength(object); i++) {
            Object element = Array.get(object, i);
            record.addElement(JsonHandler.write(element));
        }
        return record.build();
    }

    static boolean isSupported(Object object) {
        return object.getClass().isArray();
    }
}
