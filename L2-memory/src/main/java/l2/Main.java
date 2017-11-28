package l2;

public class Main {

    final private static int COUNT = 100000;
    final private static Runtime runtime = Runtime.getRuntime ();

    public static void main(String[] args) throws InstantiationException, IllegalAccessException {

        runGC();
        usedMemory();

        Object[] objects = new Object[COUNT];
        long startMemory = 0;

        for (int i = -1; i < COUNT; ++ i) {
            Object object = null;
            object = new FactoryObject(new Object()).create(); //specify an object for measuring

            if (i >= 0)
                objects[i] = object;
            else {
                object = null;
                runGC();
                startMemory = usedMemory ();
            }
        }

        runGC();
        long finalMemory = usedMemory();

        final int size = Math.round((finalMemory - startMemory)/COUNT);
        System.out.println("{" + objects [0].getClass () + "} size = " + size + " bytes");

        for (int i = 0; i < COUNT; i++)
            objects[i] = null;
        objects = null;
    }

    private static void runGC(){
        for (int i = 0; i < 4; i++)
            System.gc();
    }

    private static long usedMemory() {
        return runtime.totalMemory() - runtime.freeMemory();
    }
}