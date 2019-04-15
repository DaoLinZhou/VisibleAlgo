package random_generate;

import java.util.LinkedList;

/**
 * Create by Daolin
 * on 04 / 2019
 */
public class RandomLinkedQueue<E> {
    private LinkedList<E> queue;

    public RandomLinkedQueue() {
        this.queue = new LinkedList<E>();
    }

    public void add(E e){
        if(Math.random() < 0.5)
            queue.addFirst(e);
        else
            queue.addLast(e);
    }

    public E remove(){
        if(queue.size() == 0)
            throw new IllegalArgumentException("There is no element in RandomQueue to remove");
        if(Math.random() < 0.5)
            return queue.removeFirst();
        else
            return queue.remove(queue.size()-1);
    }

    public int size(){
        return queue.size();
    }

    public boolean isEmpty(){
        return queue.size() == 0;
    }
}
