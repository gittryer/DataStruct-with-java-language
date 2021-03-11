package com.LinearStruct;

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
    public static<T> void Reverse(LinkList<T> ls)
    {
        var first=ls.getHead().next;
        Node<T> newHead=null;
        Node<T> temp=null;
        while (first!=null)
        {
            temp=first.next;
            first.next=newHead;
            newHead=first;
            first=temp;
        }
        ls.getHead().next=newHead;
    }

    public static<T> void Reverse_Recursive(LinkList<T> ls)
    {
        ls.getHead().next=reverse_(ls.getHead().next);
    }
    /**
     * 反转链表递归
     * @param first 头节点
     */
    private static<T> Node<T> reverse_(Node<T> first)
    {
        if(first==null)
            return null;
        if(first.next==null)
            return first;
        //假设前n-1个已经反转
        Node<T> newHead=reverse_(first.next);
        first.next.next=first;
        first.next=null;
        return newHead;
    }

    /**
     * 获取链表中间节点
     * @param ls 链表
     * @param <T> 数据类型
     * @return 返回中间节点
     */
    public static<T> Node<T> getMid(LinkList<T> ls)
    {
        var p=ls.getHead();
        var q=p;
        while (q!=null&&q.next!=null)
        {
            p=p.next;
            q=q.next.next;
        }
        return p.next;
    }

    /**
     * 链表归并
     * @param h1 第一个链表节点
     * @param h2 第二个链表节点
     * @return 返回归并结果
     */
    public static  Node<Integer> merge(Node<Integer> h1,Node<Integer> h2)
    {
        var p=h1.next;
        var q=h2.next;
        Node<Integer> t=new Node<>();
        var head=t;
        while (p!=null&&q!=null)
        {
            if(p.data.compareTo(q.data)<0)
            {
                t.next=p;
                p=p.next;
                t=t.next;
            }
            else
            {
                t.next=q;
                q=q.next;
                t=t.next;
            }
        }
        t.next=(p==null?q:p);
        return head;
    }
}
