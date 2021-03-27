package com.dataStruct.bag;

/**
 * 背包接口
 * @param <T> 背包数据类型
 */
public interface IBag<T>
{
    /**
     * 获取元素个数
     * @return 返回元素个数
     */
    int getCount();
    /**
     *
     * @param val 元素值
     */
    void add(T val);
    /**
     * 判空
     */
    boolean empty();
}
