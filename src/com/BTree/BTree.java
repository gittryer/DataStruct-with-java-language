package com.BTree;
import com.Queue.IQueue;
import com.Queue.LinkQueue;

import java.util.Comparator;
//videojs.getPlayers("video-player").html5player.tech_.setPlaybackRate(2)

/**
 * 二叉树节点
 * @param <T> 数据类型
 */
class Node<T>
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

/**
 * 二叉搜索树
 * @param <T> 数据类型
 */
public class BTree<T>
{
    /**
     * 根节点
     */
    private Node<T> root;
    /**
     * 比较器
     */
    private Comparator<T> comparator;
    /**
     * 元素个数
     */
    private int count;

    /**
     * 构造函数
     */
    public BTree()
    {
        this.count=0;
    }

    /**
     * 构造函数
     * @param comparator
     */
    public BTree(Comparator<T> comparator)
    {
        this.comparator=comparator;
        this.count=0;
    }

    /**
     * 构造函数
     * @param data 数据
     */
    public BTree(T[]data)
    {
        this(data,null);
    }

    /**
     * 构造函数
     * @param data 数据
     * @param comparator 比较器
     */
    public BTree(T[]data,Comparator<T> comparator)
    {
        for (int i = 0; i < data.length; i++)
        {
            this.add(data[i]);
        }
        this.comparator=comparator;
    }

    /**
     * 比较x和y的大小
     * @param x 第一个元素
     * @param y 第二个元素
     * @return 返回比较结果
     */
    private int compare(T x,T y)
    {
        return this.comparator==null?((Comparable<T>)x).compareTo(y):
                this.comparator.compare(x,y);
}

    /**
     * 添加元素
     * @param val 元素值
     */
    public void add(T val)
    {
        this.root=add(this.root,val);
    }

    /**
     * 递归添加元素
     * @param p 节点
     * @param val 元素值
     */
    private Node<T> add(Node<T> p,T val)
    {
        if(p==null)
        {
            this.count++;
            return new Node<T>(val);
        }
        else if(this.compare(val,p.data)>0)
            p.right=add(p.right,val);
        else if(this.compare(val,p.data)<0)
            p.left=add(p.left,val);
        return p;
    }

    public void DLR()
    {
        DLR_(this.root);
    }

    public void LDR()
    {
        LDR_(this.root);
    }

    /**
     * 层序遍历
     */
    public int level()
    {
       int level=0;
       IQueue<Node<T>> q=new LinkQueue<>();
       q.enter(this.root);
       while (!q.isEmpty())
       {

           level++;
           int cnt=q.getCount();
           for (int i = 0; i < cnt; i++)
           {
               var temp=q.out();
               System.out.print(temp.data+" ");
               if (temp.left!=null)
                   q.enter(temp.left);
               if(temp.right!=null)
                   q.enter(temp.right);
           }
           System.out.println();
       }
       return level;
    }

    /**
     * 前序遍历-递归
     * @param p 起始节点
     */
    private void DLR_(Node<T> p)
    {
        if(p!=null)
        {
            System.out.println(p.data);
            DLR_(p.left);
            DLR_(p.right);
        }
    }
    /**
     * 中序遍历-递归
     * @param p 起始节点
     */
    private void LDR_(Node<T> p)
    {
        if(p!=null)
        {
            LDR_(p.left);
            System.out.println(p.data);
            LDR_(p.right);
        }
    }
    /**
     * 后序遍历-递归
     * @param p 起始节点
     */
    private void LRD_(Node<T> p)
    {
        if(p!=null)
        {
            LRD_(p.left);
            LRD_(p.right);
            System.out.println(p.data);
        }
    }
    /**
     * 是否包含给定元素值
     * @param val 给定元素值
     * @return 是否包含
     */
    public boolean contains(T val)
    {
        return contains(this.root,val);
    }

    /**
     * 是否包含给定元素值
     * @param p
     * @param val 给定元素值
     * @return 是否包含
     */
    private boolean contains(Node<T> p,T val)
    {
        if(p==null)
            return false;
        else if(p.data.equals(val))
            return true;
        else if(this.compare(p.data,val)>0)
            return contains(p.left,val);
        else
            return contains(p.right,val);
    }

    /**
     * 删除元素
     * @param val 元素值
     * @return 返回
     */
    public Node remove(T val)
    {
        return null;
    }
    /**
     * 是否为空
     * @return 返回元素个数是否为空
     */
    public boolean isEmpty()
    {
        return this.count==0;
    }

    /**
     * 获取元素个数
     * @return
     */
    public int getCount()
    {
        return this.count;
    }
}