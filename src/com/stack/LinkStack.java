package com.stack;

import com.linearStruct.Node;

import java.util.Iterator;

/**
 * 链栈
 * @param <T> 数据类型
 */
public class LinkStack<T> implements IStack<T>,Iterable<T>
{
    //计数器
    private int count;
    //栈顶部节点
    private Node<T> sk;
    //初始化构造函数
    public LinkStack()
    {
        this.sk=new Node<T>();
        this.count=0;
    }

    @Override
    public T getTop()
    {
        if(this.count<=0)
            throw new IndexOutOfBoundsException("栈空！");
        return this.sk.next.data;
    }

    @Override
    public void push(T val)
    {
        Node<T> t=new Node<T>(val,sk.next);
        this.sk.next=t;
        this.count++;
    }

    @Override
    public T pop()
    {
        if(this.count<=0)
            throw new IndexOutOfBoundsException("栈空！");
        var t=this.sk.next;
        var val=t.data;
        this.sk.next=t.next;
        this.count--;
        return val;
    }

    @Override
    public int getCount()
    {
        return this.count;
    }

    @Override
    public boolean isEmpty()
    {
        return this.count==0;
    }

    @Override
    public Iterator<T> iterator()
    {
        return new LinkStackIterator();
    }

    private class LinkStackIterator implements Iterator<T>
    {
        private Node<T> p;

        public LinkStackIterator()
        {
            this.p=sk;
        }

        @Override
        public boolean hasNext()
        {
            return this.p.next!=null;
        }

        @Override
        public T next()
        {
            this.p=this.p.next;
            return this.p.data;
        }
    }
}
