package com.dataStruct.bag;

import com.dataStruct.linearStruct.Node;

import java.util.Iterator;

/**
 * 链背包
 * @param <T> 数据类型
 */
public class LinkBag<T> implements IBag<T>,Iterable<T>
{
    //尾节点
    private Node<T> p;
    //头节点
    private Node<T> head;
    //元素个数
    private int count;
    /**
     * 构造函数
     */
    public  LinkBag()
    {
        this.head=new Node<T>();
        this.p=head;
        this.count=0;
    }

    @Override
    public int getCount()
    {
        return count;
    }

    @Override
    public void add(T val)
    {
        var t=new Node<T>(val);
        p.next=t;
        p=t;
        this.count++;
    }

    @Override
    public boolean empty()
    {
        return this.count==0;
    }

    @Override
    public Iterator<T> iterator()
    {
        return new BagIterator();
    }
    private class BagIterator implements Iterator<T>
    {
        //当前节点
        private Node<T> currentNode=head;
        @Override
        public boolean hasNext()
        {
            return currentNode.next!=null;
        }

        @Override
        public T next()
        {
            currentNode=currentNode.next;
            return currentNode.data;
        }
    }
}
