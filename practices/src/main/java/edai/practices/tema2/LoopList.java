package edai.practices.tema2;

import edai.collections.Node;

public class LoopList<T> {

    private Node<T> first;

    public Node<T> getFirst() {
        return first;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        if(first == null) return 0;

        int count = 1; // At least first node exists
        Node<T> current = first;

        while (current.getNext() != first){
            count++;
            current = current.getNext();
        }

        return count;
    }

    public Object[] listData() {
        Object[] output = new Object[size()];

        Node<T> current = first;
        for(int i = 0; i < output.length; ++i){
            output[i] = current.getData();
            current = current.getNext();
        }

        return output;
    }

    public Node<T> getLast() {
        if(first == null) return null;

        Node<T> last = first;

        while (last.getNext() != first){
            last = last.getNext();
        }

        return last;
    }

    public LoopList<T> append(T value) {
        Node<T> newNode = new Node<>(value);
        if(first == null){
            first = newNode;
        }else {
            Node<T> last = getLast();
            last.setNext(newNode);
        }
        newNode.setNext(first);

        return this;
    }

    public LoopList<T> prepend(T value) {
        Node<T> newNode = new Node<>(value);

        if(first == null){
            first = newNode;
            newNode.setNext(newNode);
        }else {
            Node<T> last = getLast();

            last.setNext(newNode);
            newNode.setNext(first);
            first = newNode;
        }
        return this;
    }

    public boolean removeFirst() {
        if(first == null) return false;

        if(first.getNext() == first){
            first = null;
        }else {
            Node<T> last = getLast();

            first = first.getNext();

            last.setNext(first);
        }
        return true;
    }

    public boolean removeLast() {
        if(first == null) return false;
        if(first.getNext() == first){
            return removeFirst();
        }

        Node<T> prelast = first;
        while(prelast.getNext().getNext() != first){
            prelast = prelast.getNext();
        }

        prelast.setNext(first);
        return true;
    }
}
