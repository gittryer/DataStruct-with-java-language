package com.dataStruct.queue;

/**
 * 双端队列：顺序表
 * @param <T> 数据类型
 */
public class SeqDeque<T> implements IDeque<T>
{
    //队头
    private int front;
    //队尾
    private int rear;
    //默认元素个数
    private final int DEFAULT_SIZE=100;
    //数据域
    private T []data;
    /**
     * 构造函数
     */
    public SeqDeque()
    {
        this.data=(T[])new Object[DEFAULT_SIZE];
    }
    /**
     * 构造函数
     * @param size 容量大小
     */
    public SeqDeque(int size)
    {
        this.data=(T[])new Object[size];
    }

    /**
     * 是否为满
     * @return 返回判断结果
     */
    public boolean isFull()
    {
        return (this.rear+1)%this.data.length==this.data.length;
    }
    @Override
    public boolean isEmpty()
    {
        return this.front==this.rear;
    }

    @Override
    public int getCount()
    {
        return (this.rear-this.front+this.data.length)%this.data.length;
    }

    @Override
    public void pushLeft(T val)
    {
        //如果有必要，扩容
        if(this.getCount()+1==this.data.length)
            this.resize(this.data.length<<1);
        this.front=(this.front-1+this.data.length)%this.data.length;
        this.data[this.front]=val;
    }

    @Override
    public void pushRight(T val)
    {
        //如果有必要，扩容
        if(this.getCount()+1==this.data.length)
            this.resize(this.data.length<<1);
        this.data[this.rear]=val;
        this.rear=(this.rear+1)%this.data.length;
    }

    @Override
    public T popLeft()
    {
        if(isEmpty())
            throw new IndexOutOfBoundsException("队列空！");
        if(this.getCount()<this.data.length/2)
            this.resize(this.data.length/2);
        var val=this.data[this.front];
        this.front=(this.front+1)%this.data.length;
        return val;
    }

    @Override
    public T popRight()
    {
        if(isEmpty())
            throw new IndexOutOfBoundsException("队列空！");
        this.rear=(this.rear-1+this.data.length)%this.data.length;
        var val=this.data[this.rear];
        return val;
    }
    /**
     * 重新设置数组大小
     * @param size 数组大小
     */
    private void resize(int size)
    {
        T[]data=(T[])new Object[size];
        this.front=0;
        this.rear=this.data.length-1;
        this.data=data;
    }
//    /**
//     * 扩容
//     */
//    private void expand()
//    {
//        if(this.getCount()+1==this.data.length)
//        {
//            T[]data=(T[])new Object[this.data.length<<1];
//            for (int i = 0; i < this.getCount(); i++)
//            {
//                data[i]=this.data[(i+this.front)%this.data.length];
//            }
//            this.front=0;
//            this.rear=this.data.length-1;
//            this.data=data;
//        }
//    }
}
