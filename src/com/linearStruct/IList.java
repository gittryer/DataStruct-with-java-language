package com.linearStruct;

/**
 * 线性表
 * @param <T> 数据类型
 */
public interface IList<T>  extends Iterable<T>
{
    /**
     * 是否包含给定元素
     * @param val 给定元素
     * @return 返回是否包含
     */
    boolean contains(T val);

    /**
     * 获取指定元素索引
     * @param val 指定元素
     * @return 返回索引
     */
    int indexOf(T val);

    /**
     * 获取给定索引对应的元素
     * @param index 索引
     * @return 返回元素值
     */
    T get(int index);

    /**
     * 设定给定索引对应元素的值
     * @param index 给定索引
     * @param val 元素值
     * @return 返回元素的原先值
     */
    T set(int index,T val);

    /**
     * 移除某索引的元素值
     * @param index 索引
     * @return 返回删除的元素值
     */
    T remove(int index);

    /**
     * 在某个位置添加元素
     * @param index 索引
     * @param val 元素值
     */
    void add(int index,T val);

    /**
     * 在线性表末尾添加元素
     * @param val 要添加的元素值
     */
    void add(T val);

    /**
     * 获取元素的数量
     * @return 返回线性表的元素个数
     */
    int getCount();

    /**
     * 清理线性表元素
     */
    void clear();

    /**
     * 判断线性表是否为空
     * @return
     */
    boolean isEmpty();

    /**
     * 转化为数组
     */
    <T>T[] toArray(T[]a);
}
