package com.dataStruct.map;
import com.dataStruct.linearStruct.IList;
import com.dataStruct.linearStruct.LinkList;

import java.util.Comparator;

/**
 * 顺序表
 * @param <K> 键
 * @param <V> 值
 */
public class SeqMap<K,V> implements IMap<K,V>
{
    /**
     * 当前放置元素个数
     */
    private int count;
    /**
     * 键数组
     */
    private K[]keys;
    /**
     * 值数组
     */
    private V[]values;
    /**
     * 比较器
     */
    private Comparator<K> comparator;
    /**
     * 默认元素个数
     */
    private final int DEFAULT_SIZE=200;

    /**
     * 构造函数
     * @param comparator 比较器
     */
    public SeqMap(Comparator<K> comparator)
    {
        this.comparator=comparator;
        this.count=0;
        this.keys=(K[])new Object[DEFAULT_SIZE];
        this.values=(V[])new Object[DEFAULT_SIZE];
    }

    /**
     * 构造函数
     */
    public SeqMap()
    {
        this(null);
    }

    /**
     * 重新设置数组大小
     * @param size 数组大小
     */
    private void resize(int size)
    {
        var nkeys=(K[])new Object[size];
        var nValues=(V[])new Object[size];
        for (int i = 0; i <this.count ; i++)
        {
            nkeys[i]=this.keys[i];
            nValues[i]=this.values[i];
        }
        this.keys=nkeys;
        this.values=nValues;
    }
    /**
     * 比较函数
     * @param x 第一个键
     * @param y 第二个键
     * @return 返回比较结果
     */
    private int compare(K x,K y)
    {
        return this.comparator==null?((Comparable<K>)(x)).compareTo(y)
                :this.comparator.compare(x,y);
    }

    /**
     * 二分查找，成功返回索引，失败返回小于k的元素个数
     * @param key 键
     * @return 成功返回索引，失败返回小于k的元素个数
     */
    private int indexOf(K key)
    {
        int low=0;
        int high= count -1;
        while (low<=high)
        {
            int mid=low+(high-low)/2;
            int cmp=this.compare(key,this.keys[mid]);
            if (cmp<0)
                high=mid-1;
            else if(cmp>0)
                low=mid+1;
            else
                return mid;
        }
        return low;
    }

    @Override
    public int getCount()
    {
        return this.count;
    }

    @Override
    public void clear()
    {
        for (int i = 0; i < this.count; i++)
        {
            this.keys[i]=null;
            this.values[i]=null;
        }
        this.count=0;
    }

    @Override
    public void add(K key, V val)
    {
        if(this.count+1==this.keys.length)
            resize(this.keys.length<<1);
        var id=this.indexOf(key);
        //值为null，无意义，删除
        if(val==null)
            this.remove(key);
        else
        {
            //如果已经存在=>覆盖
            if(id<this.count&&this.compare(key,this.keys[id])==0)
                this.values[id]=val;
            //否则插入
            else
            {
                for (int i =this.count; i >id ; --i)
                {
                    this.keys[i]=this.keys[i-1];
                    this.values[i]=this.values[i-1];
                }
                this.keys[id]=key;
                this.values[id]=val;
                this.count++;
            }
        }
    }

    @Override
    public V get(K key)
    {
        var id=this.indexOf(key);
        //找到了
        if(id<count&&this.compare(key,this.keys[id])==0)
            return this.values[id];
        //没有找到
        return null;
    }

    @Override
    public V remove(K key)
    {
        //如果有必要就缩容
        if(this.count<this.keys.length/2)
            this.resize(this.keys.length/2);
        var id=this.indexOf(key);
        //找到了
        if(id<count&&this.compare(key,this.keys[id])==0)
        {
            var t=this.values[id];
            for (int i = id; i < this.count; i++)
            {
                this.keys[i]=this.keys[i+1];
                this.values[i]=this.values[i+1];
            }
            this.count--;
            return t;
        }
        else
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
        for (int i = 0; i < this.count; i++)
            if(this.values[i].equals(val))
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
        for (int i = 0; i < this.count; i++)
            if(this.keys[i]!=null)
                ls.add(keys[i]);
        return ls;
    }

    @Override
    public String toString()
    {
        var sb=new StringBuilder();
        sb.append("{");
        for (int i = 0; i < this.count; i++)
        {
            if(this.keys[i]!=null)
            {
                if(i==0)
                    sb.append(String.format("%s:%s",keys[i],values[i]));
                else
                    sb.append(String.format(",%s:%s",keys[i],values[i]));
            }
        }
        sb.append("}");
        return sb.toString();
    }

    /**
     * 返回索引对应的值
     * @param index 索引
     * @return 返回索引对应的值
     */
    public V get(int index)
    {
        return this.values[index];
    }

    /**
     * 删除最小的键
     * @return 返回删除最小的键对应的值
     */
    public V removeMin()
    {
        return this.remove(this.min());
    }

    /**
     * 删除最大的键
     * @return 返回删除最大的键对应的值
     */
    public V removeMax()
    {
        return this.remove(this.max());
    }
    /**
     * 小于等于key的最大key
     * @param key 键
     * @return 返回小于等于给定键的最大键
     */
    public K floor(K key)
    {
        int id=this.indexOf(key);
        //查找成功，直接返回
        if (id<this.count&&this.compare(this.keys[id],key)==0)
            return keys[id];
        //否则，查找失败返回小于key的元素的个数，要获取索引，需要id-1
        return keys[id-1];
    }
    /**
     * 大于等于key的key
     * @param key 键
     * @return 返回大于等于给定键的键
     */
    public K ceiling(K key)
    {
        return this.keys[this.indexOf(key)];
    }

    /**
     * 最小键
     * @return 返回最小的键
     */
    public K min()
    {
        return this.keys[0];
    }

    /**
     * 最大键
     * @return 返回最大的键
     */
    public K max()
    {
        return this.keys[this.count-1];
    }

    /**
     * 获取某个区间内的键
     * @param low 左区间
     * @param high 右区间
     * @return 返回这个区间内的所有键
     */
    public Iterable<K> keys(K low,K high)
    {
        var ls=new LinkList<K>();
        var id=this.indexOf(low);
        while (id<this.count)
        {
            if(this.compare(this.keys[id],low)>=0&&this.compare(this.keys[id],high)<=0)
                ls.add(this.keys[id]);
            id++;
        }
        return ls;
    }

    /**
     * 获取某个区间内的元素个数
     * @param low 左区间
     * @param high 右区间
     * @return 返回这个区间内的所有元素个数
     */
    public int getCount(K low,K high)
    {
        var ls=new LinkList<K>();
        int cnt=0;
        int id=0;
        while (id<this.count)
        {
            if(this.compare(this.keys[id],low)>=0&&this.compare(this.keys[id],high)<=0)
            {
                ls.add(this.keys[id]);
                cnt++;
            }
            id++;
        }
        return cnt;
    }
}