package net;

public class Stack<T> {
    
    LinkedNode<T> head;

    public Stack() {
        this.head = null;
    }
    
    public void push(T data)
    {
        head = new LinkedNode<T>(data, head);
    }
    
    public T pop()
    {
        T temp = head.getData();
        head = head.getNext();
        return temp;
    }
    
    public T peek()
    {
        return head.getData();
    }
    
    public boolean isEmpty()
    {
        return head == null;
    }

}
