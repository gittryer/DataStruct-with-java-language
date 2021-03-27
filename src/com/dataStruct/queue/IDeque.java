package com.dataStruct.queue;

/**
 * 双端队列接口
 * @param <T> 数据类型
 */
public interface IDeque<T>
{
    /**
     * 判断是否为空
     * @return 返回节点是否为空
     */
    boolean isEmpty();

    /**
     * 返回元素个数
     * @return 元素个数
     */
    int getCount();

    /**
     * 左端入队
     * @param val 元素值
     */
    void pushLeft(T val);

    /**
     * 右端入队
     * @param val 元素值
     */
    void pushRight(T val);

    /**
     * 左端出栈
     * @return 返回出队元素
     */
    T popLeft();

    /**
     * 右端出队
     * @return返回出队元素
     */
    T popRight();
}
