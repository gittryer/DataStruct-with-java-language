package com.queue;

import com.stack.IStack;
import com.stack.LinkStack;

public class StackQueue<T> implements IQueue<T>
{
    //栈1
    private IStack<T> sk1;
    //栈2
    private IStack<T> sk2;
    //数据域
    private int count;

    /**
     * 构造函数
     */
    public StackQueue()
    {
        this.sk1=new LinkStack<T>();
        this.sk2=new LinkStack<T>();
        this.count=0;
    }
    @Override
    public int getCount()
    {
        return this.count;
    }

    @Override
    public T getFront()
    {
        if(this.sk2.isEmpty())
        {
            while (!this.sk1.isEmpty())
                this.sk2.push(this.sk1.pop());
        }
        return this.sk2.getTop();
    }

    @Override
    public void enter(T val)
    {
        this.sk1.push(val);
        this.count++;
    }

    @Override
    public T out()
    {
        if(this.isEmpty())
            throw new IndexOutOfBoundsException("栈空！");
        if(this.sk2.isEmpty())
        {
            while (!this.sk1.isEmpty())
                this.sk2.push(this.sk1.pop());
        }
        this.count--;
        return this.sk2.pop();
    }

    @Override
    public boolean isEmpty()
    {
        return this.count==0;
    }

}
