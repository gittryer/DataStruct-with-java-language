package com.dataStruct.btree.segment;

/**
 * 融合两个数值
 * @param <T> 数据类型
 */
public interface IMerger<T>
{
    /**
     * 融合两个值
     * @param x 第一个值
     * @param y 第二个值
     * @return 返回结果
     */
    T merge(T x, T y);
}
