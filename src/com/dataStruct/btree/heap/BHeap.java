package com.dataStruct.btree.heap;

import java.util.Comparator;

/**
 * 二叉树的顺序存储结构：二叉堆
 */
public class BHeap<T> implements IHeap<T>
{
    //默认数组容量
    private final int DEFAULT_SIZE=100;
    //元素个数
    private int count;
    //比较器
    private Comparator<T>   comparator;
    //数据域
    private T[]data;

    /**
     * 构造函数
     * @param comparator 比较器
     */
    public BHeap(Comparator<T> comparator)
    {
       this(null,comparator);
    }

    /**
     * 构造函数
     */
    public  BHeap()
    {
        this(null,null);
    }

    /**
     * 构造函数
     * @param data 数据域
     * @param comparator 比较器
     */
    public BHeap(T[]data,Comparator<T> comparator)
    {
        if(data==null)
        {
            this.comparator=comparator;
            this.data=(T[])new Object[DEFAULT_SIZE];
            this.count=0;
        }
        else
        {
            this.data=data;
            this.count=this.data.length;
            this.comparator=comparator;
            this.heapify();
        }
    }

    /**
     * 构造函数
     * @param data 数据
     */
    public BHeap(T[]data)
    {
        this(data,null);
    }

    /**
     * 比较x和y
     * @param x 第一个元素
     * @param y 第二个元素
     * @return 返回比较结果
     */
    private int compare(T x,T y)
    {
        return this.comparator==null?
                ((Comparable<T>)x).compareTo(y):comparator.compare(x,y);
    }
    /**
     * 动态扩容
     */
    private void expand()
    {
        if(this.count==this.data.length)
        {
            var newArr=new Object[this.data.length<<1];
            System.arraycopy(this.data,0,newArr,0,count);
            this.data=(T[])newArr;
        }
    }

    /**
     * 获取父节点索引
     * @param index 索引
     * @return 返回父节点索引
     */
    private int parent(int index)
    {
        return (index-1)/2;
    }

    /**
     * 获取左孩子索引
     * @param index 索引
     * @return 左孩子索引
     */
    private int left(int index)
    {
        return index*2+1;
    }

    /**
     * 获取右孩子索引
     * @param index
     * @return 右孩子索引
     */
    private int right(int index)
    {
        return  index*2+2;
    }

    /**
     * 交换两个索引对应的变量
     * @param x 第一个索引
     * @param y 第二个索引
     */
    private void swap(int x,int y)
    {
        var t=this.data[x];
        this.data[x]=this.data[y];
        this.data[y]=t;
    }
    /**
     * 堆上浮
     * @param index 索引
     */
    private void siftUp(int index)
    {
        while (index>0)
        {
            if(compare(this.data[index],this.data[parent(index)])<=0)
                break;
            swap(index,parent(index));
            index=parent(index);
        }
    }

    /**
     * 堆下沉
     * @param index 索引
     */
    private void siftDown(int index)
    {
        while (left(index) < this.count)
        {
            //首先判定的是右孩子存在性，若右孩子不存在，最大孩子节点索引就是左节点索引，没有问题
            //若右孩子存在，那就比较左右孩子节点，取得最大值
            //right(index)和left(index)的位置是不可以换的
            int max_child_index = right(index) < this.count &&
                    compare(this.data[right(index)], this.data[left(index)])> 0 ?
                    right(index) : left(index);
            if(compare(this.data[index],this.data[max_child_index])>0)
                return;
            swap(index,max_child_index);
            index=max_child_index;
        }
    }

    @Override
    public int getCount()
    {
        return this.count;
    }

    @Override
    public boolean isEmpty()
    {
        return this.count==0;
    }

    @Override
    public void clear()
    {
        this.count=0;
    }

    @Override
    public T remove()
    {
        if(isEmpty())
            throw new IndexOutOfBoundsException("堆空！");
        var val=this.getTop();
        swap(0,this.count-1);
        this.count--;
        siftDown(0);
        return val;
    }

    @Override
    public T replace(T val)
    {
        T ans=null;
        if(isEmpty())
           add(val);
        else
        {
            ans=this.data[0];
            this.data[0]=val;
            siftDown(0);
        }
        return  ans;
    }

    @Override
    public void add(T val)
    {
        this.expand();
        this.data[this.count++]=val;
        siftUp(this.count-1);
    }

    /**
     * 建堆
     */
    private void heapify()
    {
        /*自上而下的上浮：O(logn)
        for (int i = 1; i < this.count; i++)
        {
            this.siftUp(i);
        }
        */
        //自下而上的下沉,从第一个非叶子节点开始 O(n)
        for (int i = (count>>1)-1; i >=0 ; i--)
        {
            this.siftDown(i);
        }
    }
    @Override
    public T getTop()
    {
        if(isEmpty())
            throw new IndexOutOfBoundsException("堆空！");
        return this.data[0];
    }

    public String toString()
    {
        var sb=new StringBuilder();
        sb.append("[");
        for(int i=0;i<this.count;++i)
        {
            if(i==0)
                sb.append(this.data[i]);
            else
                sb.append(","+this.data[i]);
        }
        sb.append("]");
        return sb.toString();
    }
}