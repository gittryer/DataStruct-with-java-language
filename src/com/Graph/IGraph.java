package com.Graph;

import com.LinearStruct.IList;

/**
 * 图接口
 * @param <T> 节点数据类型
 */
public interface IGraph<T>
{
    /**
     * 获取节点入度
     * @param index 索引
     * @return 返回入度
     */
    int inDegree(int index);
    /**
     * 获取节点出度
     * @param index 索引
     * @return 返回出度
     */
    int outDegree(int index);
    /**
     * 获取节点度
     * @param index 索引
     * @return 返回该索引下节点的度
     */
    int degree(int index);

    /**
     * 两个顶点是否存在边
     * @param i 第一个顶点
     * @param j 第二个顶点
     * @return 是否存在
     */
    boolean haveEdge(int i,int j);
    /**
     * 两个顶点是否存在路径
     * @param i 第一个顶点
     * @param j 第二个顶点
     * @return 是否存在
     */
    boolean connected(int i,int j);

    /**
     * 深度优先遍历
     */
    void dfs();

    /**
     * 广度优先搜索是
     */
    void bfs();

    /**
     * 获取邻接点索引集合
     * @param vertex 顶点
     * @return 返回邻接点索引集合
     */
    IList<T> getAdj(T vertex);

    /**
     * 获取邻接点索引重载
     * @param index 索引
     * @return 返回邻接点索引集合
     */
    IList<T> getAdj(int index);

}
