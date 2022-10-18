package edai.collections;

import edai.collections.utils.NodeUtils;

public class List<T> implements IDataStructure<T> {

    private Node<T> first;

    public Node<T> getFirst() {
        return first;
    }

    public int size() {
        return NodeUtils.count(first);
    }

    public boolean isEmpty() {
        return first == null;
    }

    public Object[] listData() {
        return NodeUtils.listData(first);
    }

    public List<T> insert(T data, int insertIndex) {

         Node<T> newNode = new Node<>(data);

         if(insertIndex == 0){
             insertNodeAtBegining(newNode);
         }else if(insertIndex == -1){
             insertNodeAtEnd(newNode);
         }else{
             throwIfIndexIsOutOfBounds(insertIndex);
             insertNodeAtPosition(insertIndex, newNode);
         }

        return this;
    }

    private void throwIfIndexIsOutOfBounds(int index) {
        boolean isValid = index == -1
                || index == 0
                || (index >= -1 &&  index <= size());

        if(!isValid)
            throw new IndexOutOfBoundsException();
    }

    private void insertNodeAtPosition(int insertIndex, Node<T> newNode) {
        Node<T> preInsertNode = getNodeAtPostion(insertIndex-1);

        newNode.setNext(preInsertNode.getNext());
        preInsertNode.setNext(newNode);
    }

    private Node<T> getNodeAtPostion(int index) {
        return NodeUtils.getNodeByIndex(first, index);
    }

    private void insertNodeAtEnd(Node<T> newNode) {
        if(first == null){
            first = newNode;
        }else{
            Node<T> last = getLastNode();
            last.setNext( newNode );
        }
    }

    private Node<T> getLastNode() {
        return NodeUtils.getLast(first);
    }

    private void insertNodeAtBegining(Node<T> newNode) {
        if(first == null) {
            first = newNode;
        }else{
            newNode.setNext(first);
            first = newNode;
        }
    }

    public void remove(int index) {
        if(index == 0){
            removeNodeAtBeginning();
        }else if(index == -1){
            removeNodeAtEnd();
        }else{
            removeNodeAtPosition(index);
        }
    }

    private void removeNodeAtPosition(int index) {
        Node<T> prevNode = getNodeAtPostion(index-1);
        Node<T> nodeToRemove = prevNode.getNext();
        if(nodeToRemove == null){
            throw new IndexOutOfBoundsException();
        }

        prevNode.setNext( nodeToRemove.getNext() );
    }

    private void removeNodeAtEnd() {
        if(first.getNext() == null){
            first = null;
        }else{
            Node<T> prelast = getSecondToLast();
            prelast.setNext(null);
        }
    }

    private Node<T> getSecondToLast(){
        return NodeUtils.getSecondToLast(first);
    }

    private void removeNodeAtBeginning() {
        first = first.getNext();
    }
}
