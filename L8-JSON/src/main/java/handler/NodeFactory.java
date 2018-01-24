package handler;

import handler.nodes.*;

public class NodeFactory {

    protected final Object object;

    public NodeFactory(Object object) {
        this.object = object;
    }

    public AbstractNode build() {
        if (object == null) {
            return new NullNode();
        }
        Class klass = object.getClass();
        if (klass.isArray()) {
            return new ArrayNode(object);
        }
        if (PrimitiveNode.TYPES.contains(klass)) {
            return new PrimitiveNode(object);
        }
        if (ListNode.TYPES.contains(klass)) {
            return new ListNode(object);
        }
        if (SetNode.TYPES.contains(klass)) {
            return new SetNode(object);
        }
        if (MapNode.TYPES.contains(klass)) {
            return new MapNode(object);
        }
        return new ObjectNode(object);
    }
}
