package l2;

import java.lang.reflect.Array;

class MemoryCalculator {

    final private static int COUNT = 100000;
    final private static Runtime runtime = Runtime.getRuntime ();

    private Object object;
    private Class componentTypeOfArray;
    private Class clazz;
    private int length;
    private boolean isString = false;
    private boolean isArray = false;

    MemoryCalculator(String string) throws Exception {
        this.object = string;
        this.length = string.length();
        this.isString = true;
        calculate();
    }

    MemoryCalculator(Object object) throws Exception {
        this.object = object;
        if (object.getClass().isArray()){
            this.length = Array.getLength(object);
            this.isArray = true;
            this.componentTypeOfArray = object.getClass().getComponentType();
        } else {
            this.clazz = object.getClass();
        }
        calculate();
    }

    //Calculate used memory
    private void calculate() throws Exception {

        Object[] objects = new Object[COUNT];
        runGC();
        long startMemory = usedMemory();

        for (int i = 0; i < COUNT; ++ i) {

                Object object = create();
                objects[i] = object;
        }

        runGC();
        long finalMemory = usedMemory();

        final int size = Math.round((finalMemory - startMemory)/COUNT);
        System.out.println("{" + objects[0].getClass() + "} size = " + size + " bytes");
    }

    private static void runGC(){
        for (int i = 0; i < 4; i++)
            System.gc();
    }

    private static long usedMemory() {
        return runtime.totalMemory() - runtime.freeMemory();
    }

    //Create new object
    private Object create() throws Exception {

        if (isString) {
            char[] chars = new char[length];
            for (int i = 0; i < length; ++ i) {
                chars [i] = (char) i;
            }
            return new String (chars);
        }

        if (isArray) {
            return Array.newInstance(componentTypeOfArray, length);
        }

        if (clazz.equals(Byte.class))
            return new Byte(Byte.parseByte("0"));

        if (clazz.equals(Short.class))
            return new Short(Short.parseShort("0"));

        if (clazz.equals(Integer.class))
            return new Integer(0);

        if (clazz.equals(Long.class))
            return new Long(0);

        if (clazz.equals(Float.class))
            return new Float(123.4f);

        if (clazz.equals(Double.class))
            return new Double(123.4d);

        if (clazz.equals(Character.class))
            return new Character('a');

        if (clazz.equals(Boolean.class))
            return new Boolean(false);

        return object.getClass().newInstance();
    }
}