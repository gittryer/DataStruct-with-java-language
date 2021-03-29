package com.dataStruct.map;

import com.dataStruct.linearStruct.IList;
import com.dataStruct.linearStruct.LinkList;

import java.lang.reflect.Array;

/**
 * 哈希表：拉链法
 * @param <K> 键
 * @param <V> 值
 */
public class HashMap<K,V> implements IMap<K,V>
{
    /**
     * 已经使用的元素个数
     */
    private int count;
    /**
     * 数据域
     */
    private Node<K,V>[]data;
    /**
     *
     */
    private final int DEFAULT_SIZE=512;

    /**
     * 构造函数
     */
    public HashMap()
    {
        this.data= (Node<K, V>[]) Array.newInstance(Node.class,DEFAULT_SIZE);
        for (int i = 0; i < this.data.length; i++)
            this.data[i]=new Node<K,V>(null,null,null);
        this.count=0;
    }

    /**
     * 获取哈希索引
     * @param key 键
     * @return 返回索引
     */
    private int hash(K key)
    {
        return (key.hashCode()&0X7FFFFFFF)%this.data.length;
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
        for (int i = 0; i < this.data.length; i++)
            this.data[i]=new Node<K,V>(null,null,null);
    }

    @Override
    public void add(K key, V val)
    {
        var head=this.data[this.hash(key)];
        var p=head;
        while (p.next!=null)
        {
            p=p.next;
            if(p.key.equals(key))
            {
                p.value=val;
                return;
            }
        }
        head.next=new Node<K,V>(key,val,head.next);
        this.count++;
    }

    @Override
    public V get(K key)
    {
        var p=this.data[this.hash(key)];
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
        var p=this.data[this.hash(key)];
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
        for (int i = 0; i < this.data.length; i++)
        {
             var p=this.data[i];
             while (p.next!=null)
             {
                 p=p.next;
                 if (p.value.equals(val))
                     return true;
             }
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
        for (int i = 0; i < this.count; i++)
        {
            var p=this.data[i];
            while (p.next!=null)
            {
                p=p.next;
                ls.add(p.key);
            }
        }
        return ls;
    }

    @Override
    public String toString()
    {
        var sb=new StringBuilder();
        sb.append("{");
        boolean flg=true;
        for (int i = 0; i < this.data.length; i++)
        {
            var p=this.data[i];
            while (p.next!=null)
            {
                p=p.next;
                if(flg)
                {
                    sb.append(String.format("%s:%s",p.key,p.value));
                    flg=false;
                }
                else
                    sb.append(String.format(",%s:%s",p.key,p.value));
            }
        }
        sb.append("}");
        return sb.toString();
    }
}
