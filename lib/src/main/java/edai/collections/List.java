package edai.collections;

public class List<T> implements IDataStructure<T> {

    private Node<T> first;

    public Node<T> getFirst() {
        return first;
    }

    public int size() {

        Node<T> current = first;
        int counter = 0;

        while(current != null){
            counter++;
            current = current.getNext();
        }

        return counter;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public Object[] listData() {
        Object[] output = new Object[size()];

        Node<T> current = first;

        int i = 0;
        while(current != null){
            output[i] = current.getData();
            i++;
            current = current.getNext();
        }

        return output;
    }

    public List<T> insert(T data, int insertIndex) {
        throwIfIndexIsInBounds(insertIndex);

         Node<T> newNode = new Node<>(data);

         if(insertIndex == 0){
             insertNodeAtBegining(newNode);
         }else if(insertIndex == -1){
             insertNodeAtEnd(newNode);
         }else{
             insertNodeAtPosition(insertIndex, newNode);
         }

        return this;
    }

    private void throwIfIndexIsInBounds(int index) {
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

        Node<T> current = first;

        for(int i = 0; i < index && current != null; ++i){
            current = current.getNext();
        }

        if(current == null){
            throw new IndexOutOfBoundsException();
        }

        return current;
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
        Node<T> last = first;

        while(last.getNext() != null){
            last = last.getNext();
        }

        return last;
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
            Node<T> prelast = getPrelast();
            prelast.setNext(null);
        }
    }

    private Node<T> getPrelast(){

        Node<T> prelast = first;

        while(prelast.getNext().getNext() != null){

            prelast = prelast.getNext();
        }

        return prelast;
    }

    private void removeNodeAtBeginning() {
        first = first.getNext();
    }
}
