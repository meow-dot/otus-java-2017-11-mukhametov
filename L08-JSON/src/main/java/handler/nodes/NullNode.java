package handler.nodes;

class NullNode extends AbstractNode {

    NullNode(Object object) {super(object);}

    @Override
    public String write() {
        return "null";
    }

    static boolean isSupported(Object object) {
        return object == null;
    }
}
