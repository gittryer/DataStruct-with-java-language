package com.dataStruct.linearStruct;


/**
 * 链表节点
 * @param <T> 数据类型
 */
public class Node<T>
{
    /**
     *下一个节点
     */
    public Node<T> next;
    /**
     * 数据域
     */
    public T data;

    /**
     * 构造函数
     * @param data 数据值
     * @param next 下一个节点
     */
    public Node(T data,Node<T> next)
    {
        this.next = next;
        this.data = data;
    }

    /**
     * 构造函数
     * @param data 数据域
     */
    public Node(T data)
    {
        this.data=data;
    }
    /**
     * 构造函数
     */
    public Node()
    {

    }
}
