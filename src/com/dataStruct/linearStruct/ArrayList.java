package com.dataStruct.linearStruct;
import java.util.Arrays;
import java.util.Iterator;

/**
 * 顺序表：用一组地址连续的存储单元,依次存储数据元素,逻辑上相邻,物理上也相邻
 * @param <T> 数据类型s
 */
public class ArrayList<T> implements IList<T>
{
    //元素数量
    private int count;
    //数据域
    private T[]data;
    //默认长度
    private static final int DEFAULT_SIZE=10;

    /**
     * 动态扩容
     */
    private void expand()
    {
        if(this.count==this.data.length)
        {
            var newArr=new Object[this.data.length<<1];
            System.arraycopy(this.data,0,newArr,0,count);
            this.data=(T[])newArr;
        }
    }

    /**
     * 返回元素个数
     * @return 返回元素个数
     */
    public int getCount()
    {
        return this.count;
    }
    /**
     *  构造函数
     */
    public ArrayList()
    {
        this(DEFAULT_SIZE);
    }

    /**
     * 构造函数
     * @param arr 数组
     */
    public ArrayList(T[]arr)
    {
        this.data=arr;
        this.count=arr.length;
    }
    /**
     * 构造函数
     * @param count 元素个数
     */
    public  ArrayList(int count)
    {
        if(count<=DEFAULT_SIZE)
            this.data=(T[])new Object[count];
        else
            this.data=(T[])new Object[DEFAULT_SIZE];
    }
    /**
     * 清理元素
     */
    public  void clear()
    {
        for (int i=0;i<this.count;++i)
            this.data[i]=null;
        this.count=0;
    }

    /**
     * 判断线性表是否为空
     * @return 返回是否为空
     */
    public boolean isEmpty()
    {
        return this.count==0;
    }

    @Override
    public <T> T[] toArray(T[]a)
    {
        if (a.length < this.count)
            return (T[]) Arrays.copyOf(this.data, this.count, a.getClass());
        System.arraycopy(data, 0, a, 0, this.count);
        if (a.length > this.count)
            a[this.count] = null;
        return a;
    }

    /**
     * 获取给定索引元素
     * @param index 索引
     * @return 返回指定索引元素
     */
    public T get(int index)
    {
        if(index<0||index>=count)
            throw new IndexOutOfBoundsException("越界于Index:"+index+",Count:"+count);
        return this.data[index];
    }

    /**
     * 设置数组的值，返回旧的元素
     * @param index 索引
     * @param val 要设置的元素值
     * @return 返回旧的元素
     */
    public T set(int index,T val)
    {
        if(index<0||index>=count)
            throw new IndexOutOfBoundsException("越界于Index:"+index+",Count:"+count);
        var old=data[index];
        data[index]=val;
        return old;
    }

    /**
     * 返回元素值在线性表的位置
     * @param val 元素值
     * @return 返回索引
     */
    public int indexOf(T val)
    {
        if(val==null)
        {
            for (int i=0;i<this.count;++i)
            {
                if(this.data[i]==null)
                    return i;
            }
        }
        else
        {
            for (int i=0;i<this.count;++i)
            {
                if(this.data[i].equals(val))
                    return i;
            }
        }
        return -1;
    }

    /**
     * 判断这个元素是否存在于线性表
     * @param val 元素值
     * @return 是否存在
     */
    public boolean contains(T val)
    {
        if(val==null)
        {
            for (int i=0;i<this.count;++i)
            {
                if(this.data[i]==null)
                    return true;
            }
        }
        else
        {
            for (int i=0;i<this.count;++i)
            {
                if(this.data[i].equals(val))
                    return true;
            }
        }
        return false;
    }

    /**
     * 添加元素
     * @param val 要添加的元素
     */
    public void add(T val)
    {
        expand();
        var len=this.data.length;
        this.data[this.count++]=val;
    }

    /**
     * 指定索引添加
     * @param index 索引
     * @param val 元素值
     */
    public void add(int index,T val)
    {
        expand();
        if(index<0||index>count)
            throw new IndexOutOfBoundsException("越界于Index:"+index+",Count:"+count);
        for (int i=this.count;i>index;--i)
            this.data[i]=this.data[i-1];
        this.data[index]=val;
        this.count++;
    }
    /**
     * 删除指定位置元素
     * @param index 索引
     * @return 返回删除的元素值
     */
    public T remove(int index)
    {
        if(index<0||index>=count)
            throw new IndexOutOfBoundsException("越界于Index:"+index+",Count:"+count);
        var val=this.data[index];
        for (int i=index;i<count-1;++i)
        {
            this.data[i]=this.data[i+1];
        }
        this.data[--this.count]=null;
        return val;
    }

    /**
     * 覆盖toString方法
     * @return 返回字符串
     */
    public String toString()
    {
        var sb=new StringBuilder();
        sb.append("[");
        for(int i=0;i<this.count;++i)
        {
            if(i==0)
                sb.append(this.data[i]);
            else
                sb.append(","+this.data[i]);
        }
        sb.append("]");
        return sb.toString();
    }

    @Override
    public Iterator<T> iterator()
    {
        return new ArrayListIterator();
    }

    private class ArrayListIterator implements Iterator<T>
    {
        private int index;

        @Override
        public boolean hasNext()
        {
            return index<count;
        }

        @Override
        public T next()
        {
            return data[index++];
        }
    }
}
