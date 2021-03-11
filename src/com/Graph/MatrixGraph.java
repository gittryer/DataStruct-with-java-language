package com.Graph;

import com.LinearStruct.IList;
import com.LinearStruct.LinkList;

/**
 * 边集合
 */
class Edge
{
    //开始顶点
    public int start;
    //结束顶点
    public int end;
    //权重
    public double weight;

    /**
     * 构造函数
     * @param start 开始点
     * @param end 结束点
     * @param weight 权重
     */
    public Edge(int start,int end,double weight)
    {
        this.start=start;
        this.end=end;
        this.weight=weight;
    }
    /**
     * 构造函数
     * @param start 开始点
     * @param end 结束点
     */
    public Edge(int start,int end)
    {
        this.start=start;
        this.end=end;
        this.weight=1;
    }
}

/**
 * 邻接矩阵
 * @param <T> 数据类型
 */
public class MatrixGraph<T> implements IGraph<T>
{
    //顶点表
    private IList<T> vertex;
    //边个数
    private int num_edge;
    //顶点个数
    private int num_vertex;
    //权重表
    private double [][]weight;
    //顶点是否访问
    private boolean []visited;
    /**
     * 构造函数
     * @param vertex 顶点
     */
    public MatrixGraph(IList<T> vertex)
    {
        this.num_edge=0;
        this.num_vertex=vertex.getCount();
        this.vertex=vertex;
        this.weight=new double[this.num_vertex][this.num_vertex];
    }
    /**
     * 构造函数
     * @param vertex 顶点
     * @param edge 边
     */
    public MatrixGraph(IList<T> vertex, Edge[]edge)
    {
            this.num_edge=edge.length;
            this.num_vertex=edge.length;
            this.vertex=vertex;
            this.weight=new double[this.num_vertex][this.num_vertex];
            var u=0;
            for (int i = 0; i < num_vertex; i++)
            {
                for (int j = 0; j < num_vertex; j++)
                {
                    int start=edge[u].start;
                    int end=edge[u].end;
                    double val=edge[u++].weight;
                    this.weight[start][end]=val;
                }
            }
    }


    @Override
    public int inDegree(int index)
    {
        int sum=0;
        for (int i = 0; i < this.num_vertex; i++)
        {
            if(this.weight[i][index]!=0)
                sum++;
        }
        return sum;
    }

    @Override
    public int outDegree(int index)
    {
        int sum=0;
        for (int i = 0; i < this.num_vertex; i++)
        {
            if(this.weight[index][i]!=0)
                sum++;
        }
        return sum;
    }

    /**
     * 某个节点的度
     * @param index 索引
     * @return 返回节点的度
     */
    @Override
    public int degree(int index)
    {
        int sum=0;
        //degree(i)=indegree(i)+outdegree(i)=sum(i行不为0的个数)+sum(i列不为0的个数)
        for (int i = 0; i < this.num_vertex; i++)
        {
            if(this.weight[i][index]!=0)
                sum++;
            if(this.weight[index][i]!=0)
                sum++;
        }
        return sum;
    }

    @Override
    public boolean haveEdge(int i, int j)
    {
        return this.weight[i][j]>0;
    }

    @Override
    public boolean connected(int i, int j)
    {
        return false;
    }

    /**
     * 遍历
     */
    @Override
    public void dfs()
    {
        this.visited=new boolean[this.num_vertex];
        dfs_(1);
    }
    /**
     * 遍历辅助工具
     * @param index 索引
     */
    public void dfs_(int index)
    {
        if(index==this.num_vertex)
            return;
        for (int i = 0; i < num_vertex; i++)
        {
            if(haveEdge(i,index)&&!visited[i])
            {
                visited[i]=true;
                dfs_(i);
            }
        }
    }

    @Override
    public void bfs()
    {

    }

    @Override
    public IList<T> getAdj(T vertex)
    {
        IList<T> ls=new LinkList<>();
        var index=this.vertex.indexOf(vertex);
        for (int i = 0; i < this.num_vertex; i++)
        {
            if(this.weight[index][i]!=0)
                ls.add(this.vertex.get(i));
        }
        return ls;
    }

    @Override
    public IList<T> getAdj(int index)
    {
        return null;
    }

    /**
     * 添加边
     * @param eg 边
     */
    public void addEdge(Edge eg)
    {
        this.weight[eg.start][eg.end]=eg.weight;
        this.num_edge++;
    }


}
