package edai.collections;

import edai.collections.utils.NodeUtils;

import java.util.EmptyStackException;

public class Queue<T> implements IDataStructure<T> {

    private Node<T> head;
    private Node<T> tail;

    public void enqueue(T data) {
        Node<T> newNode =new Node<>(data);

        if(tail != null){
            tail.setNext(newNode);
        }else{
            head = newNode;
        }
        tail = newNode;
    }

    public T dequeue() {
        ensureStackIsNotEmpty();

        T data = head.getData();
        head = head.getNext();
        if(head == null){
            tail = null;
        }

        return data;
    }

    private void ensureStackIsNotEmpty(){
        if(head == null)
            throw new EmptyStackException();
    }

    public T head() {
        ensureStackIsNotEmpty();

        return head.getData();
    }

    @Override
    public int size() {
        return NodeUtils.count(head);
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public Object[] listData() {
        return NodeUtils.listData(head);
    }

}
