package com.dataStruct.map;

import com.dataStruct.linearStruct.IList;
import com.dataStruct.linearStruct.LinkList;


/**
 * 哈希表：线性探查法
 * @param <K> 键
 * @param <V> 值
 */
public class HashMap2<K,V> implements IMap<K,V>
{
    /**
     * 键值对个数
     */
    private int count;
    /**
     * 默认数组大小
     */
    private final int DEFAULT_SIZE=2;
    /**
     * 键
     */
    private K[]keys;
    /**
     * 值
     */
    private V[]vals;

    /**
     * 构造函数
     */
    public  HashMap2()
    {
        this.keys=(K[])new Object[DEFAULT_SIZE];
        this.vals=(V[])new Object[DEFAULT_SIZE];
        this.count=0;
    }

    /**
     * 获取负载因子
     * @return 返回负载因子
     */
    private int getLoadFactor()
    {
        return this.getCount()/this.keys.length;
    }
    /**
     * 扩容
     */
    private void expand()
    {
        if(this.count+1==this.keys.length)
        {
            var nkeys=new Object[this.keys.length<<2];
            var nvals=new Object[this.keys.length<<2];
            for (int i = 0; i < this.keys.length; i++)
            {
                //复制原先的key和value
                if(this.keys[i]!=null)
                {
                    int j=hash(keys[i]);
                    while (nkeys[j++]!=null);
                    nkeys[j]=this.keys[i];
                    nvals[j]=this.vals[i];
                }
            }
            this.keys=(K[])nkeys;
            this.vals=(V[])nvals;
        }
    }

    /**
     * 获取哈希索引
     * @param k 键
     * @return 返回哈希索引
     */
    public int hash(K k)
    {
        return (k.hashCode()&0x7FFFFFFF)%this.keys.length;
    }

    @Override
    public int getCount()
    {
        return this.count;
    }

    @Override
    public void clear()
    {
        for (int i = 0; i < this.keys.length; i++)
        {
            this.keys[i]=null;
            this.vals[i]=null;
        }
        this.count=0;
    }

    @Override
    public void add(K key, V val)
    {
        int i;
        expand();
        for (i = hash(key); this.keys[i]!=null ; i=(i+1)%this.keys.length)
        {
            if(this.keys[i].equals(key))
            {
                vals[i]=val;
                return;
            }
        }
        this.keys[i]=key;
        this.vals[i]=val;
        this.count++;
    }

    @Override
    public V get(K key)
    {
        int i;
        for (i = hash(key); this.keys[i]!=null ; i=(i+1)%this.keys.length)
        {
            if(this.keys[i].equals(key))
                return this.vals[i];
        }
        return null;
    }

    @Override
    public V remove(K key)
    {
        //不平移元素，直接将key和val设为null
        for (int i = this.hash(key); this.keys[i]!=null ; i=(i+1)%this.keys.length)
        {
            if(this.keys[i].equals(key))
            {
                var t=this.vals[i];
                this.keys[i]=null;
                this.vals[i]=null;
                return t;
            }
        }
        return null;
    }

    @Override
    public boolean containsKey(K key)
    {
        return get(key)!=null;
    }

    @Override
    public boolean contaninsValue(V val)
    {
        IList<K> ls=new LinkList<>();
        for (int i = 0; i < this.keys.length; i++)
            if(this.keys[i]!=null&&this.vals[i].equals(val))
                return true;
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
        IList<K> ls=new LinkList<K>();
        for (int i = 0; i < this.keys.length; i++)
            if(this.keys[i]!=null)
                ls.add(this.keys[i]);
        return ls;
    }

    @Override
    public String toString()
    {
        IList<K> ls=new LinkList<>();
        var sb=new StringBuilder();
        boolean flg=true;
        sb.append("{");
        for (int i = 0; i < this.keys.length; i++)
        {
            if(this.keys[i]!=null)
            {
                if(flg)
                {
                    sb.append(String.format("%s:%s",this.keys[i],this.vals[i]));
                    flg=false;
                }
                else
                    sb.append(String.format(",%s:%s",this.keys[i],this.vals[i]));
            }
        }
        sb.append("}");
        return sb.toString();
    }
}
