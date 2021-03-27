package com.dataStruct.btree.searchTree;
import com.dataStruct.queue.IQueue;
import com.dataStruct.queue.LinkQueue;
import com.dataStruct.stack.IStack;
import com.dataStruct.stack.LinkStack;

import java.util.Comparator;
//videojs.getPlayers("video-player").html5player.tech_.setPlaybackRate(2)

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
     * 获取二叉树最大值
     * @return 返回最大值
     */
    public T getMax()
    {
        if(isEmpty())
            throw new IndexOutOfBoundsException("二叉树为空!");
        return getMax(this.root).data;
    }

    /**
     * 获取二叉树最小值
     * @return 返回最小值
     */
    public T getMin()
    {
        if(isEmpty())
            throw new IndexOutOfBoundsException("二叉树为空!");
        return getMin(this.root).data;
    }
    /**
     *  获取根为某个节点的子树的最大值节点
     * @param p 根节点
     * @return 最大值节点
     */
    private Node<T> getMax(Node p)
    {
        if(p==null)
            return null;
        while (p.right!=null)
            p=p.right;
        return p;
    }
    /**
     *  获取根为某个节点的子树的最小值节点
     * @param p 根节点
     * @return 最小值节点
     */
    private Node<T> getMin(Node p)
    {
        if(p==null)
            return null;
        while (p.left!=null)
            p=p.left;
        return p;
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

    /**
     * 删除元素
     * @param val 元素值
     */
    public void remove(T val)
    {
        this.remove(this.root,val);
    }

    /**
     * 删除元素
     * @param p 节点
     * @param val 删除的元素值
     */
    private Node<T> remove(Node<T> p,T val)// 删除节点
    {
        if (p == null)
            return null;
        else if (this.compare(val,p.data)<0)
            p.left = remove( p.left,val);
        else if (this.compare(val,p.data)>0)
            p.right = remove(p.right,val);
        else
        {
            if (p.left == null && p.right == null)
                p = null;
            else if (p.left != null && p.right != null)
            {
                p.data = getMin(p.right).data;// 找到右侧最小值替代
                p.right = remove(p.right,p.data);
            }
            else if (p.right != null)
                p = p.right;
            else
                p = p.left;
        }
        return p;
    }

    /**
     * 前序遍历-非递归
     */
    public void DLR()
    {
        IStack<Node<T>> sk=new LinkStack<>();
        var p=this.root;
        while (!sk.isEmpty()||p!=null)
        {
            if (p!=null)
            {
                System.out.println(p.data);
                sk.push(p);
                p=p.left;
            }
            else
            {
                var temp=sk.pop();
                p=temp.right;
            }
        }
    }

    /**
     * 中序遍历-非递归
     */
    public void LDR()
    {
        IStack<Node<T>> sk=new LinkStack<>();
        var p=this.root;
        while (!sk.isEmpty()||p!=null)
        {
            if (p!=null)
            {
                sk.push(p);
                p=p.left;
            }
            else
            {
                var temp=sk.pop();
                System.out.println(temp.data);
                p=temp.right;
            }
        }
    }

    /**
     * 后续遍历-非递归
     */
    public void LRD()
    {
        IStack<Node<T>> sk1=new LinkStack<>();
        IStack<Node<T>> sk2=new LinkStack<>();
        var p=this.root;
        while (!sk1.isEmpty()||p!=null)
        {
            if (p != null)
            {
                sk1.push(p);
                sk2.push(p);
                p=p.right;
            }
            else
            {
                var temp=sk1.pop();
                p=temp.left;
            }
        }
        while (!sk2.isEmpty())
        {
            System.out.println(sk2.pop().data);
        }
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
     * 判断是否为完全二叉树
     * @return 返回是否为完全二叉树
     */
    public boolean isComplete()
    {
        if(root==null)
            return false;
        IQueue<Node<T>> q=new LinkQueue<>();
        q.enter(this.root);
        //开始遍历叶子节点
        var startLeaf=false;
        while (!q.isEmpty())
        {
            var node=q.out();
            if(node.degree()==2)
            {
                q.enter(node.left);
                q.enter(node.right);
            }
            else if(startLeaf&&!node.isLeaf())
                return false;
            else if(node.left==null&&node.right!=null)
                return false;
            else
                startLeaf=true;
        }
        return false;
    }
    /**
     * 查找某个节点
     * @param val 元素在
     * @return 返回节点
     */
    public Node<T> get(T val)
    {
        var p=this.root;
        while (p!=null)
        {
            if(this.compare(p.data,val)<0)
                p=p.left;
            else if(this.compare(p.data,val)>0)
                p=p.left;
            else
                return p;
        }
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