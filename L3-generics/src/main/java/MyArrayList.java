import java.util.*;
import java.util.function.UnaryOperator;

public class MyArrayList<E> implements List<E> {

    private Object[] objects;
    final private static int DEFAULT_CAPACITY = 20;
    private int size = 0;

    public MyArrayList() {
        this.objects = new Object[DEFAULT_CAPACITY];
    }

    @SuppressWarnings("unchecked")
    public E get(int index) {
        return (E) objects[index];
    }

    public int size() {
        return size;
    }

    private int capacity() {
        return objects.length;
    }

    private boolean ableToAdd() {
        return (size + 1 < capacity());
    }

    private void increaseCapacity() {
        Object[] temp = new Object[capacity()*2];
        System.arraycopy(objects, 0, temp, 0, objects.length);
        objects = temp;
    }

    public boolean add(Object obj) {
        if (!ableToAdd())
            increaseCapacity();
        objects[size + 1] = obj;      //check
        size++;
        return true;                //check
    }

    public boolean remove(Object obj) {
        for (Object object : objects) {
            if (object.equals(obj)) {
                object = null;
                return true;
            }
        }
        return false;
    }

    public boolean addAll(Collection<? extends E> c) {
        while (size() + c.size() > capacity()) {
            increaseCapacity();
        }
        int startPoint = size() + 1;
        Object[] temp = c.toArray();
        System.arraycopy(temp, 0, objects, size() + 1, temp.length);
        size += temp.length;
        return true;
    }

    public Iterator iterator() {
        return null;
    }

    //not changed
    public boolean isEmpty() {
        return false;
    }
    public boolean contains(Object o) {
        return false;
    }
    public Object[] toArray() {
        return new Object[0];
    }
    public boolean addAll(int index, Collection c) {
        return false;
    }
    public void replaceAll(UnaryOperator operator) {}
    public void sort(Comparator c) {}
    public void clear() {}
    public E set(int index, Object element) {
        return null;
    }
    public void add(int index, Object element) {    }
    public E remove(int index) {return null;}
    public int indexOf(Object o) {
        return 0;
    }
    public int lastIndexOf(Object o) {
        return 0;
    }
    public ListIterator<E> listIterator() {
        return null;
    }
    public ListIterator<E> listIterator(int index) {
        return null;
    }
    public List<E> subList(int fromIndex, int toIndex) {
        return null;
    }
    public Spliterator<E> spliterator() {
        return null;
    }
    public boolean retainAll(Collection c) {
        return false;
    }
    public boolean removeAll(Collection c) {
        return false;
    }
    public boolean containsAll(Collection c) {
        return false;
    }
    public Object[] toArray(Object[] a) {
        return new Object[0];
    }
}
