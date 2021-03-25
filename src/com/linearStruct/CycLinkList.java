package com.linearStruct;
import java.util.Iterator;

/**
 * 循环单链表(不含头节点)
 * @param <T> 数据类型
 */
public class CycLinkList<T> implements IList<T>
{
    private Node<T> first;
    //数据元素个数
    private int count;

    /**
     * 构造函数
     */
    public CycLinkList()
    {
        this.count=0;
    }

    /**
     * 构造函数
     * @param arr 数组
     */
    public CycLinkList(T[]arr)
    {
        for (int i = 0; i < arr.length; i++)
            this.add(arr[i]);
    }

    /**
     * 获取第k个节点
     * @param index 索引
     * @return 节点
     */
    private Node<T> getNode(int index)
    {
        if(index<0||index>=this.count)
            throw new IndexOutOfBoundsException("索引越界！");
        var p=this.first;
        for (int i = 0; i < index; i++)
            p=p.next;
        return p;
    }

    @Override
    public boolean contains(T val)
    {
        var p=this.first;
        for (int i = 0; i < this.count; i++)
        {
            if(p.data.equals(val))
                return true;
            p=p.next;
        }
        return false;
    }

    @Override
    public int indexOf(T val)
    {
        var p=this.first;
        for (int i = 0; i < this.count; i++)
        {
            if(p.data.equals(val))
                return i;
            p=p.next;
        }
        return -1;
    }

    @Override
    public T get(int index)
    {
        var p=this.getNode(index);
        return p.data;
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
        if(index<0||index>this.count)
            throw new IndexOutOfBoundsException("索引越界！");
        if(index==0)
        {
            var p=this.getNode(this.count-1);
            var ans=p.data;
            var t=p.next;
            p.next=t.next;
            this.first=t.next;
            this.count--;
            return ans;
        }
        else
        {
            var p=this.getNode(index-1);
            var t=p.next;
            var ans=t.data;
            p.next=t.next;
            this.count--;
            return ans;
        }

    }

    @Override
    public void add(int index, T val)
    {
        if(index<0||index>this.count)
            throw new IndexOutOfBoundsException("索引越界！");
        if(index==0)
        {
            var p=this.getNode(this.count-1);
            var t=new Node<T>(val,p.next);
            p.next=t;
            this.first=t;
        }
        else
        {
            var p=this.getNode(index-1);
            var t=new Node<T>(val,p.next);
            p.next=t;
        }
        this.count++;
    }

    @Override
    public void add(T val)
    {
        if(this.isEmpty())
            this.first=new Node<T>(val,this.first);
        else
        {
            var p=this.getNode(this.count-1);
            p.next=new Node<T>(val,this.first);
        }

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
        this.count=0;
        this.first=null;
    }

    @Override
    public boolean isEmpty()
    {
        return this.count==0;
    }

    @Override
    public <T>T[] toArray(T[]a)
    {
        if (a.length < this.count)
            a = (T[])java.lang.reflect.Array.newInstance(
                    a.getClass().getComponentType(), this.count);
        Object[]result=a;
        for (int i = 0; i < this.count; i++)
            result[i]=this.get(i);
        if (a.length > this.count)
            a[this.count] = null;
        return a;
    }

    /**
     * 转化为字符串
     * @return 返回字符串
     */
    public String toString()
    {
        var sb=new StringBuilder();
        var p=this.first;
        sb.append("[");
        for (int i = 1; i <= this.count; i++)
        {
            if(i==1)
                sb.append(p.data);
            else
                sb.append(","+p.data);
            p=p.next;
        }
        sb.append("]");
        return sb.toString();
    }

    @Override
    public Iterator<T> iterator()
    {
        return new CycLinkListIterator();
    }

    private class CycLinkListIterator implements Iterator<T>
    {
        private Node<T> p;

        private boolean flg;

        public CycLinkListIterator()
        {
            this.p=first;
            flg=true;
        }
        @Override
        public boolean hasNext()
        {
            return p!=null&&(flg||p!=first);
        }

        @Override
        public T next()
        {
            flg=false;
            var val=p.data;
            p=p.next;
            return val;
        }
    }
}
