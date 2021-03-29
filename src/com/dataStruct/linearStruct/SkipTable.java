package com.dataStruct.linearStruct;

/**
 * 跳表
 * @param <T> 数据类型
 */
public class JumpTable<T>
{
    /**
     * 头节点
     */
    private JNode<T> head;
    /**
     * 尾节点
     */
    private JNode<T> tail;
    /**
     * 最大层数
     */
    private int level;
    /**
     * 节点的个数
     */
    private int count;


}
