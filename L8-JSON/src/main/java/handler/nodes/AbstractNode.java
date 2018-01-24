package handler.nodes;

public abstract class AbstractNode {

    protected Object object;

    public AbstractNode() {}

    public AbstractNode(Object object) {
        this.object = object;
    }

    public abstract String write();
}
