package l8.handler;

import java.util.*;

class MapNode extends AbstractNode {

    static final Set<Class> TYPES = getTypes();

    MapNode(Object object) {
        super(object);
    }

    @Override
    String write() {
        StringBuilder result = new StringBuilder();
        result.append("{");
        int index = 0;
        for (Object o : ((Map) object).keySet()) {
            if (index != 0) {
                result.append(",");
            }
            result.append("\"")
                    .append(o)
                    .append("\"")
                    .append(":")
                    .append(JsonHandler.write(((Map) object).get(o)));
            index++;
        }
        result.append("}");
        return result.toString();
    }

    private static Set<Class> getTypes() {
        Set<Class> cls = new HashSet<>();
        cls.add(HashMap.class);
        cls.add(SortedMap.class);
        cls.add(TreeMap.class);
        return cls;
    }    
}
