package com.BTree;

/**
 * 二叉搜索树接口
 * @param <T> 数据类型
 */
public interface IBSTree<T>
{

    /**
     * 获取元素个数
     * @return 二叉搜索树元素个数
     */
    int getCount();

    /**
     * 是否为空
     * @return 返回二叉搜索树是否为空
     */
    boolean isEmpty();

    /**
     * 清理元素
     */
    void clear();

    /**
     * 添加元素
     * @param val 元素值
     */
    void add(T val);

    /**
     * 移除元素
     * @param val 元素值
     */
    void remove(T val);

    /**
     * 是否包含某个元素
     * @return 返回是否包含
     */
    boolean contains();
}
