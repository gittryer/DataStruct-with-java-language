package com.stack;

/**
 * 共享栈
 * @param <T> 数据类型
 */
public class SharedStack<T>
{
    //默认元素个数
    private final int DEFAULT_SIZE=100;
    /**
     * 数据域
     */
    private T[]data;
    /**
     * 栈1元素个数
     */
    private int top1;
    /**
     * 栈2元素个数
     */
    private int top2;

    /**
     * 构造函数
     */
    public SharedStack(int size)
    {
        this.data=(T[])new Object[size];
        this.top1 =0;
        this.top2 =this.data.length;
    }
    /**
     * 构造函数重载
     */
    public SharedStack()
    {
        this.data=(T[])new Object[DEFAULT_SIZE];
        this.top1 =0;
        this.top2 =this.data.length-1;
    }

    /**
     * 栈1进栈
     * @param val 进栈元素
     */
    public void push1(T val)
    {
        if(full())
            throw new IndexOutOfBoundsException("栈满！");
        this.data[this.top1++]=val;
    }

    /**
     * 栈2进栈
     */
    public void push2(T val)
    {
        if(full())
            throw new IndexOutOfBoundsException("栈满！");
        this.data[--this.top2]=val;
    }

    /**
     * 栈1出栈
     * @return 返回出栈元素
     */
    public T pop1()
    {
        if(empty1())
            throw new IndexOutOfBoundsException("栈空！");
        return this.data[--this.top1];
    }
    /**
     * 栈2出栈
     * @return 返回出栈元素
     */
    public T pop2()
    {
        if(empty2())
            throw new IndexOutOfBoundsException("栈空！");
        return this.data[this.top2++];
        
    }

    /**
     * 获取栈1的栈顶元素
     * @return 返回栈顶元素
     */
    public T getTop1()
    {
        if(empty1())
            throw new IndexOutOfBoundsException("栈空！");
        return this.data[this.top1];
    }

    /**
     * 获取栈2的栈顶元素
     * @return 返回栈顶元素
     */
    public T getTop2()
    {
        if(empty2())
            throw new IndexOutOfBoundsException("栈空！");
        return this.data[this.top2];
    }
    /**
     * 获取栈1元素个数
     * @return 返回栈1元素个数
     */
    public int getCount1()
    {
        return top1;
    }

    /**
     * 获取栈2元素个数
     * @return 返回栈2元素个数
     */
    public int getCount2()
    {
        return this.data.length-top2;
    }

    /**
     * 栈1是否为空
     * @return 返回栈1元素个数是否为0
     */
    public boolean empty1()
    {
        return this.getCount1()==0;
    }
    /**
     * 栈1是否为空
     * @return 返回栈2元素个数是否为0
     */
    public  boolean empty2()
    {
        return this.getCount2()==0;
    }

    /**
     * 判断栈是否满
     * @return 返回栈元素个数
     */
    public boolean full()
    {
        return this.top1==this.top2;
    }

}
