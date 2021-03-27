package com.dataStruct.btree.heap;

/**
 * 堆接口
 * @param <T> 数据类型
 */
public interface IHeap<T>
{
    /**
     * 获取元素个数
     * @return 返回元素个数
     */
    int getCount();
    /**
     * 获取堆中最大元素
     * @return 返回最大值元素
     */
    T getTop();

    /**
     * 替换堆顶部元素
     * @param val 元素值
     * @return 返回堆顶部元素
     */
    T replace(T val);
    /**
     * 判断是否为空
     * @return
     */
    boolean isEmpty();

    /**
     * 清空
     */
    void clear();

    /**
     * 移除元素
     * @return 返回移除的元素值
     */
    T remove();

    /**
     * 添加元素
     * @param val 要添加的元素值
     */
    void add(T val);
}
