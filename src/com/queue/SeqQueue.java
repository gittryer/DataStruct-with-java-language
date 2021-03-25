package com.queue;

/**
 * 顺序队列
 * @param <T> 数据类型
 */
public class SeqQueue<T> implements IQueue<T>
{
    /**
     * 默认队列容量
     */
    private final int DEFAULT_SIZE=100;
    /**
     * 数据域
     */
    private T[]data;
    /**
     * 队头索引
     */
    private  int front;
    /**
     * 队尾索引
     */
    private int rear;

    /**
     * 构造函数
     */
    public  SeqQueue()
    {
        this.data=(T[])new Object[DEFAULT_SIZE];
    }

    /**
     * 构造函数
     * @param size 容量大小
     */
    public SeqQueue(int size)
    {
        this.data=(T[])new Object[size];
    }
    /**
     * 获取元素个数
     * @return 返回队列元素个数
     */
    @Override
    public int getCount()
    {
        return (this.rear-this.front+this.data.length)%this.data.length;
    }

    /**
     * 获取队头元素
     * @return
     */
    @Override
    public T getFront()
    {
        if(isEmpty())
            throw new IndexOutOfBoundsException("队空！");
        return this.data[this.front];
    }

    @Override
    public void enter(T val)
    {
        expand();
        this.data[this.rear]=val;
        this.rear=(this.rear+1)%this.data.length;
    }

    /**
     * 扩容
     */
    private void expand()
    {
        if(isFull())
        {
            T[]data=(T[])new Object[this.data.length<<1];
            for (int i = 0; i < this.data.length; i++)
            {
                data[i]=this.data[(this.front+i)%this.data.length];
            }
            this.front=0;
            this.rear=this.data.length-1;
            this.data=data;
        }
    }

    @Override
    public T out()
    {
        if(isEmpty())
            throw new IndexOutOfBoundsException("队空！");
        var val=this.data[this.front];
        this.front=(this.front+1)%this.data.length;
        return val;
    }

    @Override
    public boolean isEmpty()
    {
        return this.front==this.rear;
    }

    /**
     * 判断队满
     * @return
     */
    public boolean isFull()
    {
        return (this.rear+1)%this.data.length==this.front;
    }

    /**
     * 删除第k个元素
     * @param k 元素索引
     * @return 返回删除的元素值
     */
    public T deleteK(int k)
    {
        if(k>(this.rear-this.front))
            throw new IndexOutOfBoundsException("访问越界！");
        var id=(this.front+k)%this.data.length;
        var val=this.data[id];
        for (int i = id; i <this.rear; i++)
            this.data[(this.front+i)%this.data.length]=this.data[(this.front+i+1)%this.data.length];
        this.rear=(this.rear-1)%this.data.length;
        return val;
    }


//    public Iterator<T> iterator()
//    {
//        return new SeqQueueIterator();
//    }
//
//    private class SeqQueueIterator implements Iterator<T>
//    {
//        private int index;
//
//        public SeqQueueIterator()
//        {
//            this.index=front;
//        }
//
//        @Override
//        public boolean hasNext()
//        {
//            return this.index!=rear;
//        }
//
//        @Override
//        public T next()
//        {
//            var val=data[index];
//            index=(index+1)%data.length;
//            return val;
//        }
//    }
}
