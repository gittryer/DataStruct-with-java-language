package com.dataStruct.queue;
import com.dataStruct.linearStruct.Node;

/**
 * 队列-循环链表
 * @param <T> 数据类型
 */
public class LinkQueue<T> implements IQueue<T>
{
    //尾节点
    private Node<T> last;
    //元素个数
    private int count=0;
    /**
     * 入队
     * @param val 入队元素
     */
    public void enter(T val)
    {
        if(isEmpty())
        {
            this.last=new Node<T>(val);
            this.last.next=this.last;
        }
        else
        {
            var t=new Node<T>(val);
            t.next=this.last.next;
            this.last.next=t;
            this.last=t;
        }
        this.count++;
    }

    /**
     * 出队
     * @return 返回出队元素
     */
    public T out()
    {
        if(isEmpty())
            throw new IndexOutOfBoundsException("队列空！");
        if(this.count==1)
        {
            var val=this.last.data;
            this.last=null;
            this.count--;
            return val;
        }
        else
        {
            var p=this.last;
            var t=p.next;
            var val=t.data;
            p.next=t.next;
            this.count--;
            return val;
        }
    }
    /**
     * 判断队列是否为空
     * @return 队列为空吗
     */
    public boolean isEmpty()
    {
        return this.count==0;
    }
    /**
     * 元素个数
     * @return 返回队列元素个数
     */
    public int getCount()
    {
        return this.count;
    }
    /**
     * 获取队列头部元素
     * @return 返回队列头部元素
     */
    public T getFront()
    {
        if(isEmpty())
            throw new IndexOutOfBoundsException("队列空！");
        return this.last.next.data;
    }

    /**
     * 移除第k个元素
     * @param k 元素位置
     * @return 返回移除的元素
     */
    public T deleteK(int k)
    {
        if(k<0||k>this.getCount())
            throw new IndexOutOfBoundsException("访问越结！");
        var p=this.last;
        for (int i = 0; i < k; i++)
            p=p.next;
        var t=p.next;
        var val=p.data;
        p.next=t.next;
        this.count--;
        return val;
    }
//
//    public Iterator<T> iterator()
//    {
//        return new LinkQueueIterator();
//    }
//    private class LinkQueueIterator implements Iterator<T>
//    {
//        private Node<T> p;
//        boolean flg;
//        public LinkQueueIterator()
//        {
//            this.p=last.next;
//            this.flg=true;
//        }
//
//        @Override
//        public boolean hasNext()
//        {
//            return p!=null&&flg||p!=last.next;
//        }
//
//        @Override
//        public T next()
//        {
//            flg=false;
//            var val=p.data;
//            p=p.next;
//            return val;
//        }
//    }
}
