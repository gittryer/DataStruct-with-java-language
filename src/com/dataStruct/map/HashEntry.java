package com.dataStruct.map;

/**
 * 哈希键值对
 * @param <K> 键
 * @param <V> 值
 */
public class HashEntry<K,V>
{
    /**
     * 键
     */
    private K key;
    /**
     * 值
     */
    private V value;

    /**
     * 构造函数
     * @param key 键
     * @param value 值
     */
    public HashEntry(K key,V value)
    {
        this.key=key;
        this.value=value;
    }

    /**
     * 获取值
     * @param key 键
     * @return 返回值
     */
    public V getValue(K key)
    {
        return this.value;
    }
}
