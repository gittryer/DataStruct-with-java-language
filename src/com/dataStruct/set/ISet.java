package com.dataStruct.set;

/**
 * 集合接口
 * @param <T> 数据类型
 */
public interface ISet<T> extends Iterable<T>
{
    /**
     * 获取元素个数
     * @return 返回元素个数
     */
    int getCount();

    /**
     * 元素是否为空
     * @return 返回元素是否为空
     */
    boolean isEmpty();

    /**
     * 是否包含某个元素
     * @param val 元素值
     * @return 返回是否包含
     */
    boolean contains(T val);

    /**
     * 集合中添加元素
     * @param val 元素值
     */
    void add(T val);

    /**
     * 集合中移除某个元素值
     * @param val 元素值
     */
    void remove(T val);


}
