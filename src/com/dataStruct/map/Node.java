package com.dataStruct.map;

/**
 * 哈希键值对
 * @param <K> 键
 * @param <V> 值
 */
public class Node<K,V>
{
    /**
     * 键
     */
    public K key;
    /**
     * 值
     */
    public V value;
    /**
     * 下一个节点索引
     */
    public Node<K,V> next;

    /**
     * 构造函数
     * @param key 键
     * @param value 值
     */
    public Node(K key, V value)
    {
        this.key=key;
        this.value=value;
        this.next=null;
    }

    /**
     *
     * @param key 键
     * @param value 值
     * @param next 下一个节点的索引
     */
    public Node(K key,V value,Node<K,V> next)
    {
        this.key=key;
        this.value=value;
        this.next=next;
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
