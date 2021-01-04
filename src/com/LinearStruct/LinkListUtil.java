package com.LinearStruct;

import java.util.Comparator;

public class LinkListUtil
{
    /**
     * 求链表最大值
     * @param head 头节点
     * @param <T> 数据类型
     * @return 返回最大值
     */
    public static<T>  Comparable Max(Node<? extends Comparable> head)
    {
        if(head==null||head.next==null)
            throw new IndexOutOfBoundsException("链表空");
        var p=head;
        var max=p.next.data;
        while (p.next!=null)
        {
            p=p.next;
            if(p.data.compareTo(max)>0)
                max=p.data;
        }
        return max;
    }
    /**
     * 求链表最大值-递归
     * @param head 头节点
     * @param <T> 数据类型
     * @return 返回最大值
     */
    public static<T>  Comparable Max_Recursive(Node<? extends Comparable> head)
    {
        if(head==null||head.next==null)
            throw new IndexOutOfBoundsException("链表空");
        return Max_(head.next);
    }
    private static<T>  Comparable Max_(Node<? extends Comparable> first)
    {
        if(first.next==null)
            return first.data;
        else
        {
            var x=first.data;
            var y=Max_(first.next);
            return x.compareTo(y)>0?x:y;
        }
    }

    /**
     * 单链表逆置
     * @param head 头节点
     * @param <T> 数据类型
     * @return 返回逆置后的头节点
     */
    public static<T> Node<T> Reverse(Node<T> head)
    {
        var first=head.next;
        Node<T> reverse=null;
        while (first!=null)
        {
            var second=first.next;
            first.next=reverse;
            reverse=first;
            first=second;
        }
        head.next=reverse;
        return head;
    }
    /**
     * 单链表逆置-递归
     * @param head 头节点
     * @param <T> 数据类型
     * @return 返回逆置后的头节点
     */
    public static <T> Node<T> Reverse_Recursive(Node<T> head)
    {
        var reverse=Reverse_(head.next,null);
        head.next=reverse;
        return reverse;
    }

    public static <T> Node<T> Reverse_(Node<T> first,Node<T> reverse)
    {
        if(first==null)
            return reverse;
        var second=first.next;
        first.next=reverse;
        return Reverse_(second,first);
    }
}
