package com.btree.searchTree;
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

    /**
     * 判断当前节点是否为叶子节点
     * @return 返回是否为叶子节点
     */
    public boolean isLeaf()
    {
        return this.left==null&&this.right==null;
    }

    /**
     * 返回当前节点的度
     * @return 返回度
     */
    public int degree()
    {
        if(isLeaf())
            return 0;
        else if(this.left!=null&&this.right!=null)
            return 2;
        else
            return 1;
    }
}
