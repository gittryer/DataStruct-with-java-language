package com.Graph;

import com.LinearStruct.IList;
import com.LinearStruct.LinkList;

/**
 * 顶点
 * @param <T> 数据类型
 */
class Vertex<T>
{
    //顶点名称
    public  T data;
    //第一条边
    EdgeNode first;

    /**
     * 构造函数
     * @param data 顶点名称
     * @param first 第一条边
     */
    public Vertex(T data, EdgeNode first)
    {
        this.data = data;
        this.first = first;
    }
}
/**
 * 边
 */
class EdgeNode
{
    //开始顶点索引
    public int vertex_index;
    //权重
    public double weight;
    //下一条边
    public EdgeNode next;

    /**
     * 构造函数
     * @param vertex_index 开始顶点索引
     * @param weight 权重
     * @param next 下一条边
     */
    public EdgeNode(int vertex_index, double weight, EdgeNode next)
    {
        this.vertex_index = vertex_index;
        this.weight = weight;
        this.next = next;
    }

    /**
     * 构造函数
     * @param vertex_index 第一条边
     * @param weight 权重
     */
    public EdgeNode(int vertex_index, double weight)
    {
        this.vertex_index = vertex_index;
        this.weight = weight;
    }
}

/**
 * 邻接表
 */
public class TableGraph<T> implements IGraph<T>
{
    //定点表
    private IList<Vertex<T>> vertex;
    //顶点数
    private int num_vertex;
    //边条数
    private int num_edge;
    /**
     * 构造函数
     * @param vertex
     */
    public TableGraph(IList<Vertex<T>> vertex)
    {
        this.vertex = vertex;
        this.num_vertex=vertex.getCount();
        this.num_edge=0;
    }

    /**
     * 构造函数
     */
    public TableGraph()
    {
        this.num_edge=this.num_vertex=0;
    }
    /**
     * 添加边
     * @param eg 边
     */
    public void addEdge(EdgeNode eg)
    {
        eg.next=this.vertex.get(eg.vertex_index).first;
        this.vertex.get(eg.vertex_index).first=eg;
        this.num_edge++;
    }

    /**
     * 添加顶点
     * @param v
     */
    public void addVertex(Vertex<T> v)
    {
        this.vertex.add(v);
        this.num_vertex++;
    }

    @Override
    public int inDegree(int index)
    {
        int sum=0;
        for (int i = 0; i < this.num_vertex; i++)
        {
            var p=this.vertex.get(i).first;
            while (p!=null)
            {
                if(p.vertex_index==index)
                    sum++;
                p=p.next;
            }
        }
        return sum;
    }

    @Override
    public int outDegree(int index)
    {
        int sum=0;
        var p=this.vertex.get(index).first;
        while (p!=null)
        {
            sum++;
            p=p.next;
        }
        return sum;
    }

    /**
     * 获取节点度
     * @param index 索引
     * @return 返回该节点度
     */
    @Override
    public int degree(int index)
    {
        int sum=0;
        for (int i = 0; i < this.num_vertex; i++)
        {
            var p=this.vertex.get(i).first;
            while (p!=null)
            {
                //出度+入度
                if(i==index||p.vertex_index==index)
                    sum++;
                p=p.next;
            }
        }
        return sum;
    }

    @Override
    public boolean haveEdge(int i, int j)
    {
        return false;
    }

    @Override
    public boolean connected(int i, int j)
    {
        return false;
    }


    @Override
    public void dfs()
    {

    }

    @Override
    public void bfs()
    {

    }

    @Override
    public IList<T> getAdj(T vertex)
    {
        IList ls=new LinkList();
        EdgeNode p=null;
        //获取所在顶点
        for (int i = 0; i < this.vertex.getCount(); i++)
        {
            if(this.vertex.get(i)==vertex)
            {
                p=this.vertex.get(i).first;
                break;
            }
        }
        while (p!=null)
        {
            ls.add(this.vertex.get(p.vertex_index).data);
            p=p.next;
        }
        return ls;
    }

    @Override
    public IList<T> getAdj(int index)
    {
        return null;
    }
}
