package net;

public class LinkedNode<T> {
    
    private T data;
    private LinkedNode<T> next;
    
    public LinkedNode(T data, LinkedNode<T> next) {
        this.data = data;
        this.next = next;
    }
    
    public T getData()
    {
        return data;
    }

    public LinkedNode<T> getNext()
    {
        return next;
    }
}
