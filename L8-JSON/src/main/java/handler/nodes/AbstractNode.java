package handler.nodes;

public abstract class AbstractNode {

    protected Object object;

    AbstractNode(Object object) {
        this.object = object;
    }

    public abstract String write();
}
