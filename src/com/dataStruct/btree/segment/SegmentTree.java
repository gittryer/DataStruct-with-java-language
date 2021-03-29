package com.dataStruct.btree.segment;

public class SegmentTree<T>
{
    /**
     * 融合器
     */
    private IMerger<T> merger;

    private T[]data;

    private T[]tree;

    /**
     * 构造函数
     * @param data 源数据
     * @param merger 融合器
     */
    public SegmentTree(T[]data,IMerger<T> merger)
    {
        this.data=data;
        this.tree=(T[])new Object[this.data.length*4];
        this.merger=merger;
        build(0,0,data.length-1);

    }

    /**
     * 建立线段树
     * @param root 根节点
     * @param low 左区间
     * @param high 右区间
     */
    private void build(int root,int low,int high)
    {
        //叶子节点的情况
        if(low==high)
        {
            this.tree[root]=this.data[low];
        }
        else
        {
            int mid=(low+high)<<1;
            build(left(root),low,mid);
            build(right(root),mid+1,high);
            this.tree[root]=this.merger.merge(this.tree[left(root)],this.tree[right(root)]);
        }
    }

    /**
     * 获取左孩子索引
     * @param index 根节点索引
     * @return 返回左孩子索引
     */
    private int left(int index)
    {
        return index*2+1;
    }

    /**
     * 获取右孩子索引
     * @param index 根节点索引
     * @return 返回右孩子索引
     */
    private int right(int index)
    {
        return index*2+2;
    }

//    @Override
//    public String toString()
//    {
//        StringBuilder sb=new StringBuilder();
//        sb.append("[");
//        for (int i = 0; i < this.tree.length; i++)
//        {
//            if(i==0)
//                sb.append()
//        }
//        sb.append("]");
//    }
}
