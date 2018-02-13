package handler.nodes;

public class NodeFactory {

    protected final Object object;

    public NodeFactory(Object object) {
        this.object = object;
    }

    public AbstractNode build() {
        if (NullNode.isSupported(object)) {
            return new NullNode(object);
        }
        if (ArrayNode.isSupported(object)) {
            return new ArrayNode(object);
        }
        if (PrimitiveNode.isSupported(object)) {
            return new PrimitiveNode(object);
        }
        if (ListNode.isSupported(object)) {
            return new ListNode(object);
        }
        if (SetNode.isSupported(object)) {
            return new SetNode(object);
        }
        if (MapNode.isSupported(object)) {
            return new MapNode(object);
        }
        return new ObjectNode(object);
    }
}
