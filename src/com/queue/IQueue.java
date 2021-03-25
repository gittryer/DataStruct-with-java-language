package com.queue;

/**
 * 队列接口
 * @param <T> 数据类型
 */
public interface IQueue<T>
{
    /**
     * 元素个数
     * @return 元素个数
     */
    int getCount();

    /**
     * 获取队头元素
     * @return 返回队头元素
     */
    T getFront();

    /**
     * 进队
     * @param val 入队元素
     */
    void enter(T val);

    /**
     * 出队
     * @return 返回出队元素
     */
    T out();

    /**
     * 是否为空
     * @return 队列是否为空
     */
    boolean isEmpty();
}
