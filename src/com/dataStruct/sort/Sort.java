package com.dataStruct.sort;

public class Sort
{
    /**
     * 交换
     * @param num 数组
     * @param i 变量索引1
     * @param j 变量索引2
     * @param <T> 数据类型
     */
    public static  <T>void swap(T[]num,int i,int j)
    {
        var t=num[i];
        num[i]=num[j];
        num[j]=t;
    }

    /**
     * 选择排序
     * @param num 数组
     * @param <T> 类型
     */
    public static <T>void SelectSort(Comparable<T> []num)
    {
        int N=num.length;
        for (int i = 0; i < N; i++)
        {
            var min_index=i;
            for (int j = i+1; j <N; j++)
                if(num[j].compareTo((T) num[min_index])<0)
                    min_index=j;
            swap(num,i,min_index);
        }
    }

    public static <T>void InsertSort(Comparable<T> []num)
    {
        int N=num.length;
        for (int i = 0; i < N; i++)
        {
            for (int j = 0; j < i; j++)
            {
                
            }
        }
    }
}
