package com.dataStruct.queue;

import com.dataStruct.linearStruct.DNode;

/**
 * 链表 双端队列
 * @param <T> 数据类型
 */
public class LinkDeque<T> implements IDeque<T>
{
    //数据元素个数
    private int count;
    //头节点
    private DNode<T> head;
    //尾节点
    private DNode<T> tail;

    /**
     * 构造函数
     */
    public LinkDeque()
    {
        this.head=new DNode<T>();
        this.tail=new DNode<T>();
        this.head.next=this.tail;
        this.tail.prior=this.head;
        this.count=0;
    }
    @Override
    public boolean isEmpty()
    {
        return this.count==0;
    }

    @Override
    public int getCount()
    {
        return this.count;
    }

    @Override
    public void pushLeft(T val)
    {
        var t=new DNode<T>(val,this.head,this.head.next);
        this.head.next.prior=t;
        this.head.next=t;
        this.count++;
    }

    @Override
    public void pushRight(T val)
    {
        var t=new DNode<T>(val,this.tail.prior,this.tail);
        this.tail.prior.next=t;
        this.tail.prior=t;
        this.count++;
    }

    @Override
    public T popLeft()
    {
        if(isEmpty())
            throw new IndexOutOfBoundsException("队列空！");
        var t=this.head.next;
        var val=t.data;
        this.head.next=t.next;
        this.head.next.prior=this.head;
        this.count--;
        return val;
    }

    @Override
    public T popRight()
    {
        if(isEmpty())
            throw new IndexOutOfBoundsException("队列空！");
        var t=this.tail.prior;
        var val=t.data;
        this.tail.prior=t.prior;
        this.tail.prior.next=this.tail;
        this.count--;
        return val;
    }
}
