package com.tree;

/**
 * 树的顺序（双亲）存储结构：并查集
 */
public class UFSet
{
    //数据域，存储父节点索引
    private int[]parent;
    //各节点秩
    private int[]rank;
    //各个节点的元素个数
    private int[]num;

    /**
     * 元素个数
     * @param size 元素个数
     */
    public UFSet(int size)
    {
        this.parent=new int[size];
        this.rank=new int[size];
        this.num=new int[size];
        for (int i = 0; i < size; i++)
        {
            //每个节点为独立集合
            this.parent[i]=i;
            //初始化秩
            this.rank[i]=0;
            //初始化各节点元素
            this.num[i]=1;
        }
    }

    /**
     * 判断两个索引对应的集合是否关联
     * @param x 索引1
     * @param y 索引2
     * @return 返回是否关联
     */
    public boolean isConnected(int x,int y)
    {
        return find(x)==find(y);
    }

    /**
     * 查找元素所在的集合编号
     * @param x 查找元素的索引
     * @return 返回集合编号
     */
    public int find(int x)
    {
        //找到根节点(父节点为自身的节点是根节点)
        while (this.parent[x]!=x)
        {
            //路径压缩
            this.parent[x]=this.parent[this.parent[x]];
            x=this.parent[x];
        }
        return x;
    }

    /**
     * 合并两个元素所在的集合
     * @param x 第一个索引
     * @param y 第二个索引
     */
    public void union(int x,int y)
    {
        var root_x=find(x);
        var root_y=find(y);
        if(root_x==root_y)
            return;
        if(this.rank[x]<this.num[y])
        {
            this.parent[root_x]=this.parent[root_y];
            this.num[y]+=this.num[x];
        }
        else if(this.rank[x]>this.rank[y])
        {
            this.parent[root_y]=this.parent[root_x];
            this.num[x]+=this.num[y];
        }
        else
        {
            this.parent[root_x]=this.parent[root_y];
            this.rank[root_y]++;
        }
    }

    /**
     * 不同集合个数
     * @return 返回不同集合个数
     */
    public int numOfSet()
    {
        int cnt=0;
        for (int i = 0; i < this.parent.length; i++)
        {
            if(this.parent[i]==i)
                cnt++;
        }
        return cnt;
    }

    /**
     * 索引所在集合的元素个数
     * @param id 索引
     * @return
     */
    public int numOfCount(int id)
    {
        return this.find(id);
    }

}