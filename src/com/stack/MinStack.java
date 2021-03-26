package com.stack;

import java.util.Comparator;

public class MinStack<T> implements IStack<T>
{

    /**
     * 普通栈
     */
    private IStack<T> sk1;
    /**
     * 存储最小值的栈
     */
    private IStack<T> sk2;
    /**
     * 比较器
     */
    private Comparator<T> comparator;

    /**
     * 构造函数
     */
    public MinStack()
    {
        this.sk1 =new LinkStack<>();
        this.sk2 =new LinkStack<>();
        this.comparator=null;
    }

    /**
     * 构造函数
     * @param comparator 比较器
     */
    public MinStack(Comparator<T> comparator)
    {
        this();
        this.comparator=comparator;
    }

    /**
     * 比较x和y
     * @param x 第一个元素
     * @param y 第二个元素
     * @return 返回比较结果
     */
    private int compare(T x,T y)
    {
        return this.comparator==null?
                ((Comparable<T>)x).compareTo(y):comparator.compare(x,y);
    }

    /**
     * 获取栈中元素的最小值
     * @return 返回最小值
     */
    public T getMin()
    {
        return this.sk2.getTop();
    }

    @Override
    public T getTop()
    {
        return sk1.getTop();
    }

    @Override
    public void push(T val)
    {
        sk1.push(val);
        if(this.sk2.isEmpty()||this.compare(val,this.sk2.getTop())<=0)
            sk2.push(val);
    }

    @Override
    public T pop()
    {
        var t= sk1.pop();
        if(this.compare(t,this.sk2.getTop())==0)
            this.sk2.pop();
        return t;
    }

    @Override
    public int getCount()
    {
        return this.sk1.getCount();
    }

    @Override
    public boolean isEmpty()
    {
        return this.sk1.isEmpty();
    }
}
