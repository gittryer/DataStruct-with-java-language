package com.dataStruct.tree;

import com.dataStruct.linearStruct.IList;
import com.dataStruct.linearStruct.LinkList;

class UFNode<T>
{
    //父节点
    public UFNode parent;
    //秩
    public int rank;
    //数据域
    public T val;

    /**
     * 构造函数
     * @param val 数据元素
     */
    public UFNode(T val)
    {
        this.val=val;
    }

    /**
     * 构造函数
     * @param parent 父节点
     * @param rank 秩
     * @param val 元素
     */
    public UFNode(UFNode parent,int rank,T val)
    {
        this.parent=parent;
        this.rank=rank;
        this.val=val;
    }

}
public class LinkUFSet<T>
{
    //数据域
    private IList<UFNode<T>> tree;

    /**
     * 构造函数
     */
    public LinkUFSet()
    {
        this.tree=new LinkList<UFNode<T>>();
    }

    public UFNode<T> find(UFNode<T> p)
    {
        while (p.parent!=null)
        {
            p=p.parent;
        }
        return p;
    }

    public void union(T x,T y)
    {

    }
}
