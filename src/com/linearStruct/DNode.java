package com.linearStruct;

/**
 * 双链表节点
 * @param <T> 数据类型
 */
public class DNode<T>
{
    //后引用
    public DNode<T> next;
    //前引用
    public DNode<T> prior;
    //数据域
    public T data;

    /**
     * 默认构造函数
     */
    public DNode()
    {

    }

    /**
     * 构造函数重载
     * @param data 数据域
     */
    public DNode(T data)
    {
        this.data=data;
    }

    /**
     * 构造函数重载
     * @param data 数据域
     * @param left 左引用
     * @param right 右引用
     */
    public DNode(T data,DNode<T> left,DNode<T> right)
    {
        this.data=data;
        this.prior=left;
        this.next=right;
    }

}
