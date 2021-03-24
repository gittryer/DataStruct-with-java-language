package com.Queue;

import com.BTree.BHeap;
import java.util.Comparator;
/**
 * 优先级队列
 * @param <T> 数据类型
 */
public class PriorityQueue<T> implements IQueue<T>
{
    //二叉堆
    private BHeap<T> bHeap;

    /**
     * 构造函数
     * 默认为小顶堆，即大优先级先出
     */
    public PriorityQueue()
    {
        this.bHeap=new BHeap<T>();
    }

    /**
     * 构造函数
     * @param comparator 比较器
     */
    public PriorityQueue(Comparator<T> comparator)
    {
        this.bHeap=new BHeap<T>(comparator);
    }

    @Override
    public int getCount()
    {
        return this.bHeap.getCount();
    }

    @Override
    public T getFront()
    {
        return this.bHeap.getTop();
    }

    @Override
    public void enter(T val)
    {
        this.bHeap.add(val);
    }

    @Override
    public T out()
    {
        return this.bHeap.remove();
    }

    @Override
    public boolean isEmpty()
    {
        return this.bHeap.isEmpty();
    }

}
