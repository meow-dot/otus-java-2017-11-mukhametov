package l8.handler;

import java.util.HashSet;
import java.util.Set;

class PrimitiveNode extends AbstractNode {
    
    static final Set<Class> TYPES = getTypes();

    PrimitiveNode(Object object) {
        super(object);
    }    

    @Override
    String write() {
        if (object.getClass().equals(String.class)) {
            return "\"" + object.toString() + "\"";
        }
        return object.toString();
    }    
    
    private static Set<Class> getTypes() {
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
