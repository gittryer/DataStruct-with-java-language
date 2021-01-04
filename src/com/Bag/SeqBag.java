package com.Bag;

import java.util.Iterator;

/**
 * 顺序背包
 * @param <T> 数据类型
 */
public class SeqBag<T> implements  IBag<T>,Iterable<T>
{
    //数据域
    private T[]data;
    //数据元素个数
    private int count;
    //默认元素个数
    private final int DEFAULT_SIZE=100;
    /**
     * 构造函数
     */
    public SeqBag()
    {
        this.data=(T[])new Object[DEFAULT_SIZE];
        this.count=0;
    }
    /**
     * 扩容
     */
    private void expand()
    {
        if(this.count<this.data.length)
            return;
        var arr=(T[])new Object[this.data.length<<1];
        System.arraycopy(this.data,0,arr,0,this.count);
        this.data=arr;
    }
    /**
     * 构造函数
     * @param size 数据元素个数
     */
    public SeqBag(int size)
    {
        this.data=(T[])new Object[size];
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
        expand();
        this.data[this.count++]=val;
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

    /**
     * 背包迭代器
     */
    private class BagIterator implements  Iterator<T>
    {
        //当前下标
        private int currentIndex;

        @Override
        public boolean hasNext()
        {
            return currentIndex<count;
        }

        @Override
        public T next()
        {
            if(!hasNext())
                throw new IndexOutOfBoundsException("");
            return data[currentIndex++];
        }
    }
}
