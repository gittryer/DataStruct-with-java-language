package com.Tree;

import java.util.Map;
import java.util.TreeMap;

/**
 * 字典树节点
 * @param <T> 数据类型
 */
class TrieNode<T>
{
    /**
     * 构造函数
     * @param isWord 是单词结尾
     * @param parent 父节点索引
     */
    public TrieNode(int count, TrieNode<T> parent)
    {
        this.count = count;
        this.next = next;
        this.parent=parent;
        this.next=new TreeMap<>();
    }
    /**
     *
     * 是否为单词结尾
     */
    public int count;
    /**
     * 指向孩子的索引
     */
    public Map<T,TrieNode> next;
    /**
     * 父节点索引
     */
    public TrieNode<T> parent;

}
/**
 * 字典树
 * @param <T> 数据类型
 */
public class Trie<T>
{
    //根节点
    private TrieNode<T> root;
    //元素个数
    private int count;

    /**
     * 构造函数
     */
    public Trie()
    {
        this.root=new TrieNode(0,null);
        this.count=0;
    }

    /**
     * 添加单词
     * @param arr 单词数组
     */
    public void add(T...arr)
    {
        var p=this.root;
        for (int i = 0; i < arr.length; i++)
        {
            if(p.next.get(arr[i])==null)
                p.next.put(arr[i],new TrieNode(0,p));
            p=p.next.get(arr[i]);
        }
        if(p.count==0)
            p.count =1;
        else
            p.count+=1;
        this.count++;
    }
    /**
     * 查找单词是否存在
     * @param arr 单词
     */
    public boolean contains(T...arr)
    {
        var p=this.root;
        for (int i = 0; i < arr.length; i++)
        {
            if(p.next.get(arr[i])==null)
                return false;
            p=p.next.get(arr[i]);
        }
        return p.count>0;
    }

    /**
     * 删除词语
     * @param arr 词语数组
     * @return 返回删除是否成功
     */
    public boolean remove(T ...arr)
    {
        var p=this.root;
        for (int i = 0; i < arr.length; i++)
        {
            if(p.next.get(arr[i])==null)
                return false;
            p=p.next.get(arr[i]);
        }
        //没有该单词
        if(p.count==0)
            return false;
        //单词存在，且非叶子节点
        else if(p.count>0 &&p.next.size()>0)
        {
            p.count =0;
            return true;
        }
        //单词存在，且为叶子节点
        else
        {
            while (p.parent.parent!=this.root)
            {
                p=p.parent;
                if(p.next.size()>1)
                    return true;
                p.next=null;

            }
            return true;
        }
    }

    /**
     * 是否为前缀
     * @param arr 数组
     * @return 返回是否为前缀
     */
    public boolean isPrefix(T...arr)
    {
        var p=this.root;
        for (int i = 0; i < arr.length; i++)
        {
            if(p.next.get(arr[i])==null)
                return false;
            p=p.next.get(arr[i]);
        }
        return true;
    }

    /**
     * 词频统计
     * @param arr 词语数组
     * @return 返回词频
     */
    public int getCount(T...arr)
    {
        var p=this.root;
        for (int i = 0; i < arr.length; i++)
        {
            if(p.next.get(arr[i])==null)
                return 0;
            p=p.next.get(arr[i]);
        }
        return p.count;
    }
    /**
     * 获取单词个数
     * @return 返回单词个数
     */
    public int getSize()
    {
        return this.count;
    }
}
