package com.tree;

/**
 * 树的孩子存储结构
 */
public class ChildTree
{
    /**
     * 构造函数
     * @param num_node 节点总数
     * @param arr 节点数组
     */
    public ChildTree(int num_node, THead[] arr)
    {
        this.num_node = num_node;
        this.arr = arr;
    }

    //节点个数
    private int num_node;
    //节点数组
    private THead[] arr;


}

/**
 * 表头
 */
class THead
{
    /**
     * 构造函数
     * @param name 节点名称
     * @param first 第一个孩子的索引
     */
    public THead(String name, TNode first)
    {
        this.name = name;
        this.first = first;
    }

    //名称
    private String name;
    //第一个索引
    private TNode first;

    /**
     * 获取名称
     * @return
     */
    public String getName()
    {
        return name;
    }

    /**
     * 获取第一个孩子索引
     * @return
     */
    public TNode getFirst()
    {
        return first;
    }
}
/**
 * 树的节点
 */
class TNode
{
    /**
     * 获取孩子索引
     * @return 返回孩子索引
     */
    public int getChild_index()
    {
        return child_index;
    }

    /**
     * 获取下一个节点的索引
     * @return 返回下一个节点的索引
     */
    public TNode getNext()
    {
        return next;
    }

    //孩子索引
    private int child_index;
    //链表下一个引用
    private TNode next;

    /**
     * 构造函数
     * @param child_index 孩子节点索引
     * @param next 下一个树节点引用
     */
    public TNode(int child_index, TNode next)
    {
        this.child_index = child_index;
        this.next = next;
    }

    /**
     * 构造函数
     */
    public TNode()
    {

    }
}