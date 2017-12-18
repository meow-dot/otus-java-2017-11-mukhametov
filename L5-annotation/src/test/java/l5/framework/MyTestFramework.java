package l5.framework;

import com.google.common.reflect.ClassPath;
import l5.framework.annotations.*;
import java.io.IOException;
import java.lang.reflect.*;

public class MyTestFramework {

    private static Class<?> klass;
    private static Method beforeMethod;
    private static Method[] testMethods;
    private static Method afterMethod;

    public static void runTestsFromPackage(String packageName) {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        try {
            for (ClassPath.ClassInfo info : ClassPath.from(loader).getTopLevelClasses()) {
                if (info.getName().startsWith(packageName)) {
                    Class<?> clazz = info.load();
                    runTestsFromClass(clazz.getName());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void runTestsFromClass(String className) {
        try {
            setClass(className);
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found");
        }

        sortingMethods();

        try {
            beforeMethodRun();
            testMethodsRun();
            afterMethodRun();
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    private static void sortingMethods() {
        Method[] methods = klass.getMethods();
        testMethods = new Method[calculateNumberOfTestMethods(methods)];
        int i = 0;
        for (Method method : methods) {
            if (method.isAnnotationPresent(Test.class)) {
                testMethods[i] = method;
                i++;
            }
            if (method.isAnnotationPresent(Before.class)) {
                beforeMethod = method;
            }
            if (method.isAnnotationPresent(After.class)) {
                afterMethod = method;
            }
        }
    }

    private static void setClass(String className) throws ClassNotFoundException {
        klass = Class.forName(className);
    }

    private static Object getNewInstanceOfClass() {
        try {
            return klass.getConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static Method getBeforeMethod() {
        return beforeMethod;
    }

    private static Method[] getTestMethods() {
        return testMethods;
    }

    private static Method getAfterMethod() {
        return afterMethod;
    }

    private static void beforeMethodRun() throws InvocationTargetException, IllegalAccessException {
        if (getBeforeMethod() != null) getBeforeMethod().invoke(getNewInstanceOfClass());
    }

    private static void testMethodsRun() throws InvocationTargetException, IllegalAccessException {
        if (getTestMethods().length != 0) {
            for (Method method : getTestMethods()) {
                System.out.println("Method '" + method.getName() + "' is testing...");
                method.invoke(getNewInstanceOfClass());
            }
        }
    }

    private static void afterMethodRun() throws InvocationTargetException, IllegalAccessException {
        if (getAfterMethod() != null) getAfterMethod().invoke(getNewInstanceOfClass());
    }

    private static int calculateNumberOfTestMethods(Method[] methods) {
        int i = 0;
        for (Method method : methods) {
            if (method.isAnnotationPresent(Test.class)) i++;
        }
        return i;
    }
}
