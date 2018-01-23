package l8.handler;

import java.lang.reflect.Field;

class ObjectNode extends AbstractNode {

    ObjectNode(Object object) {
        super(object);
    }
    
    @Override
    String write() {
        StringBuilder result = new StringBuilder();
        result.append("{");
        int index = 0;
        for (Field field : object.getClass().getDeclaredFields()) {
            if (index != 0) {
                result.append(",");
            }
            field.setAccessible(true);
            result.append("\"")
                    .append(field.getName())
                    .append("\":");
            try {
                result.append(JsonHandler.write(field.get(object)));
            } catch (IllegalArgumentException | IllegalAccessException e) {
                e.printStackTrace();
            }
            index++;
        }
        result.append("}");
        return result.toString();
    }    
}
