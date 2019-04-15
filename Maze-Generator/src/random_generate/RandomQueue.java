package random_generate;

import java.util.ArrayList;

/**
 * Create by Daolin
 * on 04 / 2019
 */
public class RandomQueue<E> {

    private ArrayList<E> queue;

    public RandomQueue() {
        this.queue = new ArrayList<E>();
    }

    public void add(E e){
        queue.add(e);
    }

    public E remove(){
        if(queue.size() == 0)
            throw new IllegalArgumentException("There is no element in RandomQueue to remove");
        //随机索引,获取元素
        int randIndex = (int) (Math.random()*queue.size());
        E randElement = queue.get(randIndex);
        queue.set(randIndex, queue.get(queue.size()-1));
        queue.remove(queue.size() - 1);

        return randElement;
    }

    public int size(){
        return queue.size();
    }

    public boolean isEmpty(){
        return queue.size() == 0;
    }
}
