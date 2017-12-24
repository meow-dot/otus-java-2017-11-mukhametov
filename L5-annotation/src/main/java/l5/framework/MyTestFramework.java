package l5.framework;

import com.google.common.reflect.ClassPath;
import l5.framework.annotations.*;
import l5.framework.exceptions.NotTestMethodsException;
import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.ArrayList;

public class MyTestFramework {

    private static Class<?> klass;

    public static void runTestsFromPackage(String packageName) {
        boolean isTested = false;
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        try {
            for (ClassPath.ClassInfo info : ClassPath.from(loader).getTopLevelClasses()) {
                if (info.getName().startsWith(packageName)) {
                    klass = info.load();
                    if (isTestClass()) {
                        runMethods();
                        isTested = true;
                    }
                }
            }
            if (!isTested)
                throw new NotTestMethodsException();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void runTestsFromClass(String className) {
        try {
            klass = Class.forName(className);
            if (isTestClass())
                runMethods();
            else
                throw new NotTestMethodsException();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static boolean isTestClass() {
        return getMethods(Test.class).size() > 0;
    }

    private static ArrayList<Method> getMethods(Class<? extends Annotation> annotationClass) {
        Method[] allMethods = klass.getMethods();
        ArrayList<Method> m = new ArrayList<>(0);
        for (Method method : allMethods) {
            if (method.isAnnotationPresent(annotationClass))
                m.add(method);
        }
        m.trimToSize();
        return m;
    }

    private static void runMethods () throws Exception {
        for (Method tm : getMethods(Test.class)) {
            Object instance = klass.getConstructor().newInstance();
            for (Method bm : getMethods(Before.class)) {
                bm.invoke(instance);
            }
            System.out.println("Method '" + tm.getName() + "' is testing...");
            tm.invoke(instance);
            for (Method am : getMethods(After.class)) {
                am.invoke(instance);
            }
        }
    }
}