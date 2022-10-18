package edai.collections.utils;

import edai.collections.Node;

public class NodeUtils {
    public static <T> int count(Node<T> first) {

        int counter = 0;
        Node<T> current = first;

        while(current != null){
            counter++;
            current = current.getNext();
        }

        return counter;

        // Solución usando recursividad
        // if(first == null) return 0;
        // return 1 + count(first.getNext());
    }

    public static <T> Node<T> getSecondToLast(Node<T> first) {
        if(first == null || first.getNext() == null){
            throw new IndexOutOfBoundsException();
        }

        Node<T> secondToLast = first;

        while(secondToLast.getNext().getNext() != null){
            secondToLast = secondToLast.getNext();
        }

        return secondToLast;
    }

    public static <T> Node<T> getLast(Node<T> first) {
        if(first.getNext() == null) return first;

        return getSecondToLast(first).getNext();
    }

    public static <T> Node<T> getNodeByIndex(Node<T> first, int index) {
        if(index < 0) throw new IndexOutOfBoundsException();

        Node<T> current = first;

        int counter = 0;

        while (current != null && counter < index) {
            current = current.getNext();
            counter++;
        }

        if(current == null){
            throw new IndexOutOfBoundsException();
        }

        return current;
    }

    public static <T> Object[] listData(Node<T> first) {
        if(first == null) return new Object[0];

        Object[] output = new Object[count(first)];

        Node<T> current = first;
        int i = 0;
        while(current != null){
            output[i] = current.getData();
            i++;
            current = current.getNext();
        }

        return output;
    }
}
