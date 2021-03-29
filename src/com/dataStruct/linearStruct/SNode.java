package com.dataStruct.linearStruct;

/**
 * 跳表节点
 * @param <T> 数据类型
 */
public class JNode<T>
{
    /**
     * 分值：用来规定节点顺序
     */
    private double score;
    /**
     * 字符串对象：分值相同时按obj排序
     */
    private String obj;
    /**
     * 层数组：用来加快查找速度
     */
    private Level[] level;
    /**
     * 层
     */
    class Level
    {
        /**
         * 构造函数
         * @param span 跨度
         * @param next 下一节点的引用
         */
        public Level( JNode<T> next,int span)
        {
            this.span = span;
            this.next = next;
        }

        /**
         * 跨度：到下一个节点的距离
         */
        private int span;
        /**
         * 指向下一个节点
         */
        private JNode<T> next;

        /**
         * 构造函数
         */
        public Level()
        {
            this.span=0;
            this.next=null;
        }
    }

}
