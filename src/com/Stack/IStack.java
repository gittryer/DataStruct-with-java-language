package com.Stack;

/**
 * 栈接口
 * @param <T> 数据类型
 */
public interface IStack<T>
{
    /**
     * 取得栈顶部元素
     * @return 返回栈顶部元素
     */
    T getTop();
    /**
     * 入栈
     * @param val 入栈元素
     */
    void push(T val);

    /**
     * 出栈
     * @return 返回出栈元素
     */
    T pop();

    /**
     * 获取元素个数
     * @return 返回元素个数
     */
    int getCount();

    /**
     * 判断是否为空
     * @return 返回栈是否为空
     */
    boolean isEmpty();

}
