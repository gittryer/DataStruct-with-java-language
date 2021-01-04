package com.Stack;

import com.Queue.IQueue;
import com.Queue.LinkQueue;

/**
 * 两个队列实现栈
 * @param <T>
 */
public class QueueStack<T> implements IStack<T>
{
    //第一个队列
    private IQueue<T> q1;
    //第二个队列
    private IQueue<T> q2;
    //数据元素个数
    private int count;

    /**
     * 构造函数
     */
    public QueueStack()
    {
        this.q1=new LinkQueue<T>();
        this.q2=new LinkQueue<T>();
        this.count=0;
    }

    @Override
    public T getTop()
    {
        return this.q1.isEmpty()?q2.getFront():q1.getFront();
    }

    @Override
    public void push(T val)
    {
        if(!q1.isEmpty())
            q1.enter(val);
        else
            q2.enter(val);
        this.count++;
    }

    @Override
    public T pop()
    {
        if(this.isEmpty())
            throw new IndexOutOfBoundsException("栈空！");
        this.count--;
        if(!q1.isEmpty())
        {
            while (q1.getCount()>1)
                q2.enter(q1.out());
            return q1.out();
        }
        else
        {
            while (q2.getCount()>1)
                q1.enter(q2.out());
            return q2.out();
        }
    }

    @Override
    public int getCount()
    {
        return this.count;
    }

    @Override
    public boolean isEmpty()
    {
        return this.count==0;
    }
}
