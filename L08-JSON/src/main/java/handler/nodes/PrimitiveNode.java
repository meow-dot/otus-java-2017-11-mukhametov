package handler.nodes;

import java.util.HashSet;
import java.util.Set;

class PrimitiveNode extends AbstractNode {

    PrimitiveNode(Object object) {
        super(object);
    }

    @Override
    public String write() {
        if (object.getClass().equals(String.class)) {
            return "\"" + object.toString() + "\"";
        }
        return object.toString();
    }

    static boolean isSupported(Object object) {
        return getTypes().contains(object.getClass());
    }

    protected static Set<Class> getTypes() {
        Set<Class> cls = new HashSet<>();
        cls.add(String.class);
        cls.add(Short.class);
        cls.add(Integer.class);
        cls.add(Long.class);
        cls.add(Float.class);
        cls.add(Double.class);
        cls.add(Byte.class);
        cls.add(Character.class);
        cls.add(Boolean.class);
        return cls;
    }
}
