package com.dataStruct.map;

import com.dataStruct.linearStruct.IList;
import com.dataStruct.linearStruct.LinkList;
import com.dataStruct.queue.IQueue;
import com.dataStruct.queue.LinkQueue;

import java.util.Iterator;

/**
 * 链式符号表：无序表
 * @param <K> 键
 * @param <V> 值
 */
public class LinkMap<K,V> implements IMap<K,V>
{
    private Node<K,V> head;
    /**
     * 元素个数
     */
    private int count;

    /**
     * 构造函数
     */
    public LinkMap()
    {
        this.head=new Node<K,V>(null,null,null);
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
    public void add(K key, V val)
    {
        var p=this.head;
        while (p.next!=null)
        {
            p=p.next;
            if(p.key.equals(key))
            {
                p.value=val;
                return;
            }
        }
        this.head.next=new Node<K,V>(key,val,this.head.next);
        this.count++;
    }

    @Override
    public V get(K key)
    {
        var p=this.head;
        while (p.next!=null)
        {
            p=p.next;
            if(p.key.equals(key))
                return p.value;
        }
        return null;
    }

    @Override
    public V remove(K key)
    {
        var p=this.head;
        while (p.next!=null)
        {
            if(p.next.key.equals(key))
            {
                var t=p.next;
                p.next=t.next;
                this.count--;
                return t.value;
            }
            p=p.next;
        }
        return null;
    }

    @Override
    public boolean containsKey(K key)
    {
        return this.get(key)!=null;
    }

    @Override
    public boolean contaninsValue(V val)
    {
        var p=this.head;
        while (p.next!=null)
        {
            p=p.next;
            if(p.value.equals(val))
                return true;
        }
        return false;
    }

    @Override
    public boolean isEmpty()
    {
        return this.count==0;
    }

    @Override
    public Iterable<K> keys()
    {
        IList<K> ls=new LinkList<>();
        var p=this.head;
        while (p.next!=null)
        {
            p=p.next;
            ls.add(p.key);
        }
        return ls;
    }

    @Override
    public String toString()
    {
        StringBuilder sb=new StringBuilder();
        sb.append("{");
        var p=this.head;
        boolean flg=true;
        while (p.next!=null)
        {
            p=p.next;
            if(flg)
            {
                flg=false;
                sb.append(String.format("%s:%s",p.key,p.value));
            }
            else
                sb.append(String.format(",%s:%s",p.key,p.value));
        }
        sb.append("}");
        return sb.toString();
    }
}
