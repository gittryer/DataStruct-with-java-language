//package com.dataStruct.map;
//
//import com.dataStruct.linearStruct.IList;
//import com.dataStruct.linearStruct.LinkList;
//
//import java.util.Comparator;
//
///**
// * 顺序符号表：有序表
// * @param <K>
// * @param <V>
// */
//public class SeqMap<K,V> implements IMap<K,V>
//{
//    //默认长度
//    private static final int DEFAULT_SIZE=10;
//    /**
//     * 比较器
//     */
//    private Comparator<K> comparator;
//    /**
//     * 键数组
//     */
//    private K[]keys;
//    /**
//     * 值数组
//     */
//    private V[]vals;
//    /**
//     * 元素个数
//     */
//    private int count;
//
//    /**
//     * 构造函数
//     * @param comparable 比较器
//     */
//    public SeqMap(Comparator<K> comparator)
//    {
//        this.keys=(K[])new Object[DEFAULT_SIZE];
//        this.vals=(V[])new Object[DEFAULT_SIZE];
//        this.comparator=comparator;
//        this.count=0;
//    }
//
//    /**
//     * 构造函数
//     */
//    public SeqMap()
//    {
//        this(null);
//    }
//
//    /**
//     * 某个键的索引
//     * @param k 键
//     * @return 返回索引
//     */
//    public int indexOfK(K k)
//    {
//        int low=0;
//        int high=this.count-1;
//        while (low<=high)
//        {
//            int mid=(low+high)<<1;
//            if(this.compare(k,this.keys[mid])>0)
//                low=mid+1;
//            else if(this.compare(k,this.keys[mid])<0)
//                high=mid-1;
//            else
//                return mid;
//        }
//        //小于k的元素个数
//        return low;
//    }
//
//    /**
//     * 比较x和y的大小
//     * @param x 第一个元素
//     * @param y 第二个元素
//     * @return 返回比较结果
//     */
//    private int compare(K x,K y)
//    {
//        return this.comparator==null?
//                ((Comparable<K>)x).compareTo(y):comparator.compare(x,y);
//    }
//
//    @Override
//    public int getCount()
//    {
//        return this.count;
//    }
//
//    @Override
//    public void clear()
//    {
//        this.count=0;
//    }
//
//    @Override
//    public void add(K key, V val)
//    {
//        int id=this.indexOfK(key);
//        if(id<this.count&&this.compare(key,this.keys[id])==0)
//            this.vals[id]=val;
//        else
//        {
//            for (int i = this.count; i >id ; i--)
//                this.keys[i]=this.keys[i-1];
//            this.keys[id]=key;
//            this.vals[id]=val;
//            this.count++;
//        }
//    }
//
//    @Override
//    public V get(K key)
//    {
//        int id=this.indexOfK(key);
//        if(id<this.count&&this.compare(key,this.keys[id])==0)
//            return this.vals[id];
//        else
//            return null;
//    }
//
//    @Override
//    public V remove(K key)
//    {
//        int id=this.indexOfK(key);
//        if(id<this.count&&this.compare(key,this.keys[id])==0)
//        {
//            var val=this.vals[id];
//            for (int i = id; i <this.count ; i++)
//            {
//                this.keys[i]=this.keys[i+1];
//                this.vals[i]=this.vals[i+1];
//            }
//            this.count--;
//            return val;
//        }
//        else
//            return null;
//    }
//
//    @Override
//    public boolean containsKey(K key)
//    {
//        int id=this.indexOfK(key);
//        if(id<this.count&&this.compare(this.keys[id],key)==0)
//            return true;
//        return false;
//    }
//
//    @Override
//    public boolean contaninsValue(V val)
//    {
//        //值是无序的
//        for (int i = 0; i < this.count; i++)
//        {
//            if(this.vals[i].equals(val))
//                return true;
//        }
//        return false;
//    }
//
//    @Override
//    public boolean isEmpty()
//    {
//        return this.count==0;
//    }
//
//    @Override
//    public Iterable<K> keys()
//    {
//        IList<K> ls=new LinkList<K>();
//        for (int i = 0; i < this.count; i++)
//            ls.add(this.keys[i]);
//        return ls;
//    }
//
//    /**
//     * 返回最小键
//     * @return
//     */
//    public K min()
//    {
//        return this.keys[0];
//    }
//
//    /**
//     * 返回最大键
//     * @return
//     */
//    public K max()
//    {
//        return this.keys[this.count-1];
//    }
//
//    /**
//     * 根据排名获取键
//     * @param index 索引
//     * @return 返回键
//     */
//    public K get(int index)
//    {
//        if(index>0||index>this.count)
//            throw new IndexOutOfBoundsException("访问越界于"+index);
//        return this.keys[index];
//    }
//
//    /**
//     * 小于等于key的最大键
//     * @param key 键
//     * @return 返回等于小于key的最大键
//     */
//    public K floor(K key)
//    {
//
//    }
//
//    @Override
//    public String toString()
//    {
//        var sb=new StringBuilder();
//        sb.append("{");
//        for (int i = 0; i < this.count; i++)
//        {
//            if(i==0)
//                sb.append(String.format("%s:%s",this.keys[i],this.vals[i]));
//            else
//                sb.append(String.format(",%s:%s",this.keys[i],this.vals[i]));
//        }
//        sb.append("}");
//        return sb.toString();
//    }
//}
