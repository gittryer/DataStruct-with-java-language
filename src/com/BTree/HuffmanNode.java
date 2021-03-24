package com.BTree;

/**
 * 哈夫曼节点
 * @param <T> 数据类型
 */
public class HuffmanNode<T>
{
    //数据域
    public T name;
    //权重
    public double weight;
    //左孩子索引
    public HuffmanNode<T> left;
    //右孩子索引
    public HuffmanNode<T> right;

    /**
     * 构造函数
     * @param name 节点名称
     * @param weight 节点权重
     */
    public HuffmanNode(T name,double weight)
    {
        this.name=name;
        this.weight=weight;
    }

    /**
     * 构造函数
     * @param name 节点名称
     * @param weight 节点权重
     * @param left 左孩子
     * @param right 右孩子
     */
    public HuffmanNode(T name,double weight,HuffmanNode<T> left,HuffmanNode<T> right)
    {
        this.name=name;
        this.weight=weight;
        this.left=left;
        this.right=right;
    }
}
