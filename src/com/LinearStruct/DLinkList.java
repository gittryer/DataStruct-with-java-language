package com.LinearStruct;

import java.util.Iterator;

/**
 * 带头尾节点的双链表，索引从1开始
 * @param <T>
 */
public class DLinkList<T> implements IList<T>
{
    //头节点
    private DNode<T> head;
    //尾节点
    private DNode<T> tail;
    //元素个数
    private int count=0;

    /**
     * 构造函数
     */
    public DLinkList()
    {
        this.head=new DNode<T>();
        this.tail=new DNode<T>();
        this.head.next=this.tail;
        this.tail.prior=this.head;
        this.count=0;
    }

    /**
     * 获取索引对应的节点
     * @param index 索引
     * @return 返回节点
     */
    private DNode<T> getNode(int index)
    {
        if(index<0||index>this.count)
            throw new IndexOutOfBoundsException("访问月结！");
        var p=this.head;
        for (int i = 1; i <= index; i++)
            p=p.next;
        return p;
    }

    @Override
    public boolean contains(T val)
    {
        var p=this.head;
        while (p.next!=this.tail)
        {
            p=p.next;
            if(p.data.equals(val))
                return true;
        }
        return false;
    }

    @Override
    public int indexOf(T val)
    {
        var p=this.head;
        int i=0;
        while (p.next!=this.tail)
        {
            p=p.next;
            i++;
            if(p.data.equals(val))
                return i;
        }
        return -1;
    }

    @Override
    public T get(int index)
    {
        return this.getNode(index).data;
    }

    @Override
    public T set(int index, T val)
    {
        var p=this.getNode(index);
        var temp=p.data;
        p.data=val;
        return temp;
    }

    @Override
    public T remove(int index)
    {
        if(index<1||index>this.count)
            throw new IndexOutOfBoundsException("访问越界！");
        var p=this.getNode(index-1);
        var t=p.next;
        var val=t.data;
        p.next=t.next;
        p.next.prior=p;
        this.count--;
        return val;
    }

    @Override
    public void add(int index, T val)
    {
        if(index==this.count+1)
            this.addEnd(val);
        else if(index<1||index>this.count)
            throw new IndexOutOfBoundsException("访问越界！");
        else
        {
            var p=this.getNode(index-1);
            var t=new DNode<T>(val,p,p.next);
            p.next.prior=t;
            p.next=t;
            this.count++;
        }
    }

    /**
     * 在头部添加
     * @param val 添加的元素值
     */
    @Override
    public void add(T val)
    {
        var t=new DNode<T>(val,this.head,this.head.next);
        this.head.next.prior=t;
        this.head.next=t;
        this.count++;
    }

    /**
     * 在尾巴添加
     * @param val 添加的元素值
     */
    public void addEnd(T val)
    {
        var t=new DNode<T>(val,this.tail.prior,this.tail);
        this.tail.prior.next=t;
        this.tail.prior=t;
        this.count++;
    }

    /**
     * 删除首个元素
     * @return 返回删除的元素值
     */
    public T removeFirst()
    {
        if(this.isEmpty())
            throw new  IndexOutOfBoundsException("链表空！");
        var p=this.head;
        var t=this.head.next;
        var val=t.data;
        t.next.prior=p;
        p.next=t.next;
        this.count--;
        return val;
    }

    /**
     * 删除尾部元素
     * @return 返回删除的元素值
     */
    public T removeEnd()
    {
        if(this.isEmpty())
            throw new  IndexOutOfBoundsException("链表空！");
        var p=this.tail;
        var t=this.tail.prior;
        var val=t.data;
        this.tail.prior=t.prior;
        this.tail.prior.next=this.tail;
        this.count--;
        return val;
    }

    @Override
    public int getCount()
    {
        return this.count;
    }

    @Override
    public void clear()
    {
        this.head.next=this.tail;
        this.tail.prior=this.head;
        this.count=0;
    }

    @Override
    public boolean isEmpty()
    {
        return this.getCount()==0;
    }

    public String toString()
    {
        var sb=new StringBuilder();
        var p=this.head;
        sb.append("[");
        for (int i = 1; i <=this.count; i++)
        {
            p=p.next;
            if(i==1)
                sb.append(p.data);
            else
                sb.append(","+p.data);
        }
        sb.append("]");
        return sb.toString();
    }

    @Override
    public Iterator<T> iterator()
    {
        return new DLinkListIterator();
    }

    private class DLinkListIterator implements Iterator<T>
    {
        private DNode<T> p;

        public DLinkListIterator()
        {
            this.p=head;
        }

        @Override
        public boolean hasNext()
        {
            return p.next!=tail;
        }

        @Override
        public T next()
        {
            p=p.next;
            return p.data;
        }
    }
}
