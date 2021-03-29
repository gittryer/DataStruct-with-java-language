package com.dataStruct.linearStruct;

/**
 * 跳表节点
 * @param <T> 数据类型
 */
public class SNode<T>
{
    /**
     * 构造函数
     */
    public SNode(int level_size)
    {
        this(level_size,0,null);
    }

    /**
     * 构造函数
     * @param level_size 层数
     * @param score 分值
     * @param obj 对象
     */
    public SNode(int level_size,double score,String obj)
    {
        //层数组
        this.levels=new Level[level_size];
        //分值
        this.score=score;
        //标识
        this.obj=obj;
        //前一个节点引用
        this.prior=null;
        //初始化每一层
        for (int i = 0; i <levels.length ; i++)
            levels[i]=new Level();
    }
    /**
     * 分值：用来规定节点顺序
     */
    public double score;
    /**
     * 字符串对象：分值相同时按obj排序
     */
    public String obj;
    /**
     * 层数组：用来加快查找速度
     */
    public Level[] levels;
    /**
     * 指向前一个节点的引用
     */
    public SNode<T> prior;
}
/**
 * 层
 */
class Level<T>
{
    /**
     * 构造函数
     * @param span 跨度
     * @param next 下一节点的引用
     */
    public Level(SNode<T> next, int span)
    {
        this.span = span;
        this.next = next;
    }

    /**
     * 跨度：到下一个节点的距离
     */
    public int span;
    /**
     * 指向下一个节点
     */
    public SNode<T> next;

    /**
     * 构造函数
     */
    public Level()
    {
        this.span=0;
        this.next=null;
    }

}