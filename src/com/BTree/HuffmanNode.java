package com.BTree;

/**
 * 哈夫曼节点
 * @param <T> 数据类型
 */
public class HuffmanNode<T>
{
    //数据域
    public T data;
    //左孩子索引
    public HuffmanNode<T> left;
    //右孩子索引
    public HuffmanNode<T> right;

    /**
     * 构造函数
     * @param data 数据域
     */
    public HuffmanNode(T data)
    {
        this.data=data;
    }

    /**
     * 构造函数
     * @param data 数据域
     * @param left 左孩子
     * @param right 右孩子
     */
    public HuffmanNode(T data,HuffmanNode<T> left,HuffmanNode<T> right)
    {
        this.data=data;
        this.left=left;
        this.right=right;
    }
}
