package com.dataStruct.set;

import com.dataStruct.linearStruct.IList;
import com.dataStruct.linearStruct.LinkList;

import java.util.Iterator;

/**
 * 线性表实现集合
 * @param <T>
 */
public class ListSet<T> implements ISet<T>
{
    //数据域
    private IList<T> data;
    /**
     * 构造函数
     */
    public ListSet()
    {
        this.data=new LinkList<>();
    }
    @Override
    public int getCount()
    {
        return this.data.getCount();
    }

    @Override
    public boolean isEmpty()
    {
        return this.data.isEmpty();
    }

    @Override
    public boolean contains(T val)
    {
        return this.data.contains(val);
    }

    @Override
    public void add(T val)
    {
        if(!this.data.contains(val))
            this.data.add(val);
    }

    @Override
    public void remove(T val)
    {
        var index=this.data.indexOf(val);
        if(index>=0)
            this.data.remove(index);
    }

    @Override
    public Iterator<T> iterator()
    {
        return this.data.iterator();
    }
}
