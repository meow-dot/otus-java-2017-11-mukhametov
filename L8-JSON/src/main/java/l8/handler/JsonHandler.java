package l8.handler;

public class JsonHandler {
    
    public static String write(Object object) {
        AbstractNode node = (new NodeFactory(object)).build();
        return node.write();
    }
}
