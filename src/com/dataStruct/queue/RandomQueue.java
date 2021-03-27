package com.dataStruct.queue;

import java.util.Random;

/**
 * 随机队列
 * @param <T> 数据类型
 */
public class RandomQueue<T> implements IQueue<T>
{
    //默认数据量大小
    private final int DEFAUT_SIZE=100;
    //数据域
    private T[]data;
    //数据元素个数
    private int count;

    /**
     * 构造函数
     */
    public RandomQueue()
    {
        this.data=(T[])new Object[DEFAUT_SIZE];
        this.count=0;
    }

    /**
     * 构造函数
     * @param size 数据元素个数
     */
    public RandomQueue(int size)
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
        System.arraycopy(this.data,0,arr,0,this.data.length);
        this.data=arr;
    }

    @Override
    public int getCount()
    {
        return this.count;
    }

    @Override
    public T getFront()
    {
        if(isEmpty())
            throw new IndexOutOfBoundsException("队空！");
        var id=this.getRandomIndex();
        return this.data[id];
    }

    @Override
    public void enter(T val)
    {
        expand();
        this.data[this.count++]=val;
    }

    @Override
    public T out()
    {
        if(isEmpty())
            throw new IndexOutOfBoundsException("队空！");
        var id=this.getRandomIndex();
        var val=this.data[id];
        swap(id,this.count-1);
        this.count--;
        return val;
    }

    /**
     * 获取随机索引
     * @return 返回随机索引
     */
    private int getRandomIndex()
    {
        Random rnd=new Random();
        return rnd.nextInt(this.count);
    }

    /**
     * 交换两个变量
     * @param x 第一个变量索引
     * @param y 第二个变量索引
     */
    private void swap(int x,int y)
    {
        var t=this.data[x];
        this.data[x]=this.data[y];
        this.data[y]=t;
    }

    /**
     * 洗牌算法
     * @param num 打乱数组
     */
    private static void shuffle(int[]num)
    {
        Random rnd=new Random();
        for (int i = num.length-1; i >0  ;i--)
        {
            var id=rnd.nextInt(i);
            var t=num[id];
            num[id]=num[i];
            num[i]=t;
        }
    }

    @Override
    public boolean isEmpty()
    {
        return this.count==0;
    }


//    public Iterator<T> iterator()
//    {
//        return new RandomQueueIterator();
//    }
//
//    private class RandomQueueIterator implements Iterator<T>
//    {
//        //打乱的索引表
//        private int[]indexes;
//        //当前索引
//        private int index;
//        /**
//         * 构造函数
//         */
//        public RandomQueueIterator()
//        {
//            this.indexes=new int[count];
//            for (int i = 0; i < count; i++)
//                indexes[i]=i;
//            shuffle(this.indexes);
//        }
//        @Override
//        public boolean hasNext()
//        {
//            return index<indexes.length;
//        }
//
//        @Override
//        public T next()
//        {
//            return data[this.indexes[this.index++]];
//        }
//    }
}
