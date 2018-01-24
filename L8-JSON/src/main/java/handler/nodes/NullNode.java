package handler.nodes;

public class NullNode extends AbstractNode {

    public NullNode() {}

    @Override
    public String write() {
        return "null";
    }
}
