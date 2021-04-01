package com.dataStruct.stack;

import java.util.Iterator;

/**
 * 顺序栈
 * @param <T> 数据类型
 */
public class SeqStack<T> implements IStack<T>,Iterable<T>
{
    //默认元素个数
    private final int DEFAULT_SIZE=1;
    //数据域
    private T []data;
    //元素个数
    private int count;
    /**
     * 重新设置数组大小
     * @param size 数组大小
     */
    private void resize(int size)
    {
        var newArr=new Object[size];
        System.arraycopy(this.data,0,newArr,0,count);
        this.data=(T[])newArr;
    }

    /**
     * 构造函数
     */
    public SeqStack()
    {
        this.data=(T[])new Object[DEFAULT_SIZE];
        this.count=0;
    }
    @Override
    public T getTop()
    {
        if(isEmpty())
            throw new IndexOutOfBoundsException("栈空！");
        return this.data[this.count-1];
    }

    @Override
    public void push(T val)
    {
        //如果有必要，扩容
        if(this.count+1==this.data.length)
            this.resize(this.data.length<<1);
        this.data[this.count++]=val;
    }

    @Override
    public T pop()
    {
        if(isEmpty())
            throw new IndexOutOfBoundsException("栈为空！");
        if(this.count<this.data.length/2)
            this.resize(this.data.length/2);
        return this.data[--this.count];
    }

    @Override
    public int getCount()
    {
        return this.count;
    }

    /**
     * 返回是否为空
     * @return 返回元素是否为0
     */
    @Override
    public boolean isEmpty()
    {
        return this.count==0;
    }

    @Override
    public Iterator<T> iterator()
    {
        return new SqeStackIterator();
    }
    private class SqeStackIterator implements Iterator<T>
    {
        private int index;

        public SqeStackIterator()
        {
            this.index=count;
        }

        @Override
        public boolean hasNext()
        {
            return this.index>0;
        }

        @Override
        public T next()
        {
            return data[--index];
        }
    }
}
