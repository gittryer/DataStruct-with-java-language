package com.BTree;
/**
 * 二叉树节点
 * @param <T> 数据类型
 */
public class Node<T>
{
    /**
     * 数据域
     */
    public T data;
    /**
     * 左孩子索引
     */
    public Node<T> left;
    /**
     * 右孩子索引
     */
    public Node<T> right;

    /**
     * 构造函数
     * @param data 数据域
     * @param left 左索引
     * @param right 右索引
     */
    public Node(T data,Node<T> left,Node<T> right)
    {
        this.data=data;
        this.left=left;
        this.right=right;
    }

    /**
     * 构造函数
     * @param data 数据域
     */
    public Node(T data)
    {
        this.data=data;
    }
}
