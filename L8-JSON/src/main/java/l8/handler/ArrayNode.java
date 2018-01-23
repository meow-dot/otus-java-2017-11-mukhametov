package l8.handler;

import java.lang.reflect.Array;

class ArrayNode extends AbstractNode {

    ArrayNode(Object object) {
        super(object);
    }

    @Override
    String write() {
        StringBuilder result = new StringBuilder();
        result.append("[");               
        for (int i = 0; i < Array.getLength(object); i++) {
            if (i != 0) {
                result.append(",");
            }
            Object element = Array.get(object, i);
            result.append(JsonHandler.write(element));
        }
        result.append("]");
        return result.toString();
    }    
}
