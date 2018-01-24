package handler.recorder;

public class RecordBuilder {
    
    protected StringBuilder string;
    protected int index = 0;

    public RecordBuilder() {
        string = new StringBuilder();
    }
    
    public void createObject() {
        string.append('{');
    }
    
    public void createArray() {
        string.append('[');
    }
    
    public void createMap() {
        string.append('{');
    }
    
    public void addField(String name, String value) {
        if (index != 0) {
            string.append(',');
        }
        string.append('\"')
                .append(name)
                .append('\"')
                .append(':')
                .append(value);
        index++;
    }
    
    public void addKeyValue(String key, String value) {
        if (index != 0) {
            string.append(',');
        }
        string.append('\"')
                .append(key)
                .append('\"')
                .append(':')
                .append(value);
        index++;
    }
    
    public void addElement(String element) {
        if (index != 0) {
            string.append(',');
        }
        string.append(element);
        index++;
    }
    
    public String build() {
        if (string.charAt(0) == '{') {
            string.append('}');
        }
        if (string.charAt(0) == '[') {
            string.append(']');
        }
        return string.toString();
    }
}
