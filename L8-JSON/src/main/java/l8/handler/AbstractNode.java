package l8.handler;

abstract class AbstractNode {
    
    protected Object object;
    
    AbstractNode() {}    

    AbstractNode(Object object) {
        this.object = object;
    }    
    
    abstract String write();    
}
