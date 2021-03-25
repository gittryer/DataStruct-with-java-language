package com.queue;

import com.linearStruct.DNode;

/**
 * 链队列栈:循环链表表示
 */
//(push/pop)[h,5,4,3,2,1,2,3](enter)
public class Steque<T>
{
    //头节点
    private DNode<T> head;
    //尾巴节点
    private DNode<T> tail;
    //元素个数
    private int count;

    /**
     * 构造函数
     */
    public Steque()
    {
        this.head=new DNode<T>();
        this.tail=new DNode<T>();
        this.head.next=this.tail;
        this.tail.prior=this.head;
    }

    /**
     * 入栈
     * @param val 入栈元素
     */
    public void push(T val)
    {
        var t=new DNode<T>(val,head,head.next);
        this.head.next.prior=t;
        this.head.next=t;
        this.count++;
    }

    /**
     * 出栈
     * @return 返回出栈元素
     */
    public T pop()
    {
        if(this.isEmpty())
            throw new IndexOutOfBoundsException("队列空！");
        var t=this.head.next;
        var val=t.data;
        t.next.prior=this.head;
        this.head.next=t.next;
        this.count--;
        return val;
    }

    /**
     * 入队
     * @param val 入队元素
     */
    public void enter(T val)
    {
        var t=new DNode<T>(val,this.tail.prior,this.tail);
        this.tail.prior.next=t;
        this.tail.prior=t;
        this.count++;
    }

    /**
     * 返回队列是否为空
     * @return 返回队列是否为空
     */
    public boolean isEmpty()
    {
        return this.count==0;
    }

    /**
     * 将队列置空
     */
    public void clear()
    {
        this.head.next=this.tail;
        this.tail.prior=this.head;
        this.count=0;
    }

    /**
     * 返回元素个数
     * @return 返回元素个数
     */
    public int getCount()
    {
        return this.count;
    }

}