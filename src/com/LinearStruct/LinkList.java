package com.LinearStruct;

import java.util.Iterator;

/**
 * 链表 有头节点，首节点索引0
 * @param <T>
 */
public class LinkList<T> implements IList<T>
{

    //元素个数
    private int count;
    //头结点的
    private Node<T> head;
    /**
     * 构造函数
     */
    public LinkList()
    {
        this.head=new Node<T>();
        this.count=0;
    }

    /**
     * 返回头节点
     * @return 头节点
     */
    public Node<T> getHead()
    {
        return this.head;
    }
    /**
     * 获取第k个节点
     * @param k
     * @return
     */
    private Node<T> getNode(int k)
    {
        //规定头节点索引为-1，首节点索引为0
        if(k<-1||k>=this.count)
            throw new IndexOutOfBoundsException("索引越界！");
        var p=this.head;
        for (int i = -1; i <k; i++)
            p=p.next;
        return p;
    }
    @Override
    public boolean contains(T val)
    {
        var p=this.head;
        while (p.next!=null)
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
        int i=-1;
        while (p.next!=null)
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
        var ans=p.data;
        p.data=val;
        return ans;
    }

    @Override
    public T remove(int index)
    {
//        if(index<1||index>this.count)
//            throw new IndexOutOfBoundsException("索引越界！");
        var p=this.getNode(index-1);
        var t=p.next;
        var val=t.data;
        p.next=t.next;
        this.count--;
        return val;
    }

    /**
     * 删除指定节点
     * @param p 给定节点
     */
    public void remove(Node p)
    {
        if(p.next==null)
        {
            p=null;
            this.count--;
        }
        else
        {
            var t=p.next;
            p.data=t.data;
            p.next=t.next;
            this.count--;
        }
    }
    /**
     * 删除所有与val相同的元素
     * @param val 要删除的元素值
     * @return 返回删除的元素个数
     **/
    public int removeAll(T val)
    {
        var index=0;
        var p=this.head;
        while (p.next!=null)
        {
            var t=p.next;
            if(t!=null && t.data.equals(val))
            {
                p.next=t.next;
                index++;
                this.count--;
            }
            else
                p=p.next;
        }
        return index;
    }

    @Override
    public void add(int index, T val)
    {
            var p=this.getNode(index-1);
            var t=new  Node<T>(val,p.next);
            p.next=t;
            this.count++;
    }

    @Override
    public void add(T val)
    {
        var p=this.getNode(this.count-1);
        var t=new Node<T>(val,p.next);
        p.next=t;
        this.count++;
    }

    @Override
    public int getCount()
    {
        return this.count;
    }

    @Override
    public void clear()
    {
        this.head.next=null;
        this.count=0;
    }

    @Override
    public boolean isEmpty()
    {
        return this.count==0;
    }

    /**
     * 返回链表的倒数第k个元素
     * @param k 倒数第k元素
     * @return 返回元素值
     */
    public T lastK(int k)
    {
        if(k<0||k>this.count)
            throw new IndexOutOfBoundsException("越界访问!");
        var p=this.head;
        var q=this.head;
        for (int i = 1; i <= k; i++)
            p=p.next;
        while (p!=null)
        {
            p=p.next;
            q=q.next;
        }
        return q.data;
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
        return new LinkListIterator();
    }

    private class LinkListIterator implements Iterator<T>
    {
        private Node<T> p;

        public LinkListIterator()
        {
            this.p=head;
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