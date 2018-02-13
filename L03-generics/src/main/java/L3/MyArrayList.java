package L3;

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

    public E set(int index, E element) {
        if (index < 0 || index >= capacity()) {
            throw new IndexOutOfBoundsException();
        }
        objects[index] = element;
        return element;
    }

    public boolean add(E element) {
        if (!ableToAdd())
            increaseCapacity();
        objects[size] = element;
        size++;
        return true;
    }

    public boolean remove(Object obj) {
        for (int i = 0; i < size(); i++) {
            if (objects[i].equals(obj)) {
                objects[i] = null;
                System.arraycopy(objects, i+1, objects, i, size()-i-1);
                size--;
                return true;
            }
        }
        return false;
    }

    public boolean addAll(Collection<? extends E> c) {
        while (size() + c.size() > capacity()) {
            increaseCapacity();
        }
        Object[] temp = c.toArray();
        System.arraycopy(temp, 0, objects, size() + 1, temp.length);
        size += temp.length;
        return true;
    }

    public int size() {
        return size;
    }

    private int capacity() {
        return objects.length;
    }

    private boolean ableToAdd() {
        return (size() + 1 < capacity());
    }

    private void increaseCapacity() {
        objects = Arrays.copyOf(new Object[capacity()*2], objects.length);
    }

    public Iterator<E> iterator() {
        return new MyListIterator();
    }

    public ListIterator<E> listIterator() { return new MyListIterator(); }

    private class MyListIterator implements ListIterator<E> {

        private int nextIndex = 0;

        public boolean hasNext() {
            return nextIndex != size();
        }

        @SuppressWarnings("unchecked")
        public E next() {
            nextIndex++;
            return (E)objects[previousIndex()];
        }

        public boolean hasPrevious() {
            return nextIndex() != 0 ;
        }

        public int nextIndex() {
            return nextIndex;
        }

        public int previousIndex() {
            if (hasPrevious()) return nextIndex()-1;
            throw new IndexOutOfBoundsException();
        }

        public void set(E e) {
            MyArrayList.this.set(previousIndex(), e);
        }

        //not defined
        public void remove() {}
        public E previous() {return null;}
        public void add(E e) {}
    }

    @SuppressWarnings("unchecked")
    public void sort(Comparator<? super E> c) {
         Arrays.sort((E[]) objects, 0, size, c);
    }

    //not defined
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
    public void clear() {}
    public void add(int index, Object element) {    }
    public E remove(int index) {return null;}
    public int indexOf(Object o) {
        return 0;
    }
    public int lastIndexOf(Object o) {
        return 0;
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
