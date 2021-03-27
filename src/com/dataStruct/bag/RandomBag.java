package com.dataStruct.bag;

import java.util.Iterator;
import java.util.Random;

public class RandomBag<T> implements IBag<T>,Iterable<T>
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
    public RandomBag()
    {
        this.data=(T[])new Object[DEFAULT_SIZE];
        this.count=0;
    }
    /**
     * 构造函数
     * @param size 数据元素个数
     */
    public RandomBag(int size)
    {
        this.data=(T[])new Object[size];
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

    @Override
    public int getCount()
    {
        return this.count;
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
        return new RandomBagIterator();
    }


    /**
     * 洗牌算法
     * @param num 打乱数组
     */
    private static void shuffle(int[]indexes)
    {
        Random rnd=new Random();
        for (int i = indexes.length-1; i >0 ; i--)
        {
            int id=rnd.nextInt(i);
            var x=indexes[id];
            indexes[id]=indexes[i];
            indexes[i]=x;
        }
    }

    private class RandomBagIterator implements Iterator<T>
    {
        //索引
        private int[]indexes;
        //当前索引
        private int index;

        /**
         * 构造函数
         */
        public RandomBagIterator()
        {
            this.indexes=new int[count];
            for (int i = 0; i < this.indexes.length; i++)
                this.indexes[i]=i;
            shuffle(this.indexes);
            this.index=0;
        }


        @Override
        public boolean hasNext()
        {
            return this.index<count;
        }

        @Override
        public T next()
        {
            return data[indexes[index++]];
        }
    }
}
