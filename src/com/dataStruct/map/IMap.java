package com.dataStruct.map;

public interface IMap<K,V>
{
    /**
     * 获取元素个数
     * @return 返回装入元素个数
     */
    int getCount();

    /**
     * 清理键值对
     */
    void clear();

    /**
     * 添加元素
     * @param key 键
     * @param val 值
     */
    void add(K key,V val);

    /**
     * 根据键获取值
     * @param key 键
     * @return 返回值
     */
    V get(K key);

    /**
     * 根据键删除某个键值对
     * @param key 键
     * @return 返回删除的键对应的值
     */
    V remove(K key);

    /**
     * 是否包含某个键
     * @param key 键
     * @return 返回是否包含
     */
    boolean containsKey(K key);

    /**
     * 是否包含某个值
     * @param val 值
     * @return 返回是否包含
     */
    boolean contaninsValue(V val);

    /**
     * 是否为空
     * @return 返回是否为空
     */
    boolean isEmpty();

    /**
     * 返回键迭代集合
     * @return 返回键的迭代集
     */
    Iterable<K> keys();
}
