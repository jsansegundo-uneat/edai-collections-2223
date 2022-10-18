package edai.collections;

import edai.collections.utils.NodeUtils;

import java.util.EmptyStackException;

public class Stack<T> implements IDataStructure<T>{

    private Node<T> first;

    @Override
    public int size() {
        return NodeUtils.count(first);
    }

    @Override
    public boolean isEmpty() {
        return first == null;
    }

    @Override
    public Object[] listData() {
        return NodeUtils.listData(first);
    }

    public T pop() {
        throwIfStackIsEmpty();

        T data = first.getData();
        first = first.getNext();

        return data;
    }

    private void throwIfStackIsEmpty() {
        if (first == null)
            throw new EmptyStackException();
    }

    public T top() {
        throwIfStackIsEmpty();

        return first.getData();
    }

    public void push(T data) {
        var newData = new Node<>(data);

        newData.setNext(first);
        first = newData;
    }
}
