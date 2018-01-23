package l8.handler;

import java.util.*;

class ListNode extends AbstractNode {

    static final Set<Class> TYPES = getTypes();

    ListNode(Object object) {
        super(object);
    }

    @Override
    String write() {
        StringBuilder result = new StringBuilder();
        result.append("[");
        int index = 0;
        for (Object o : (List) object) {
            if (index != 0) {
                result.append(",");
            }
            result.append(JsonHandler.write(o));
            index++;
        }
        result.append("]");
        return result.toString();
    }

    private static Set<Class> getTypes() {
        Set<Class> cls = new HashSet<>();
        cls.add(ArrayList.class);
        cls.add(LinkedList.class);
        return cls;
    }
}
