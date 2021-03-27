package com.dataStruct.btree.huffmanTree;
import com.dataStruct.linearStruct.IList;
import com.dataStruct.queue.IQueue;
import com.dataStruct.queue.LinkQueue;
import com.dataStruct.queue.PriorityQueue;

import java.util.*;

/**
 * 哈夫曼树
 * @param <T> 数据类型
 */
public class HuffmanTree<T>
{
    //堆
    private PriorityQueue<HuffmanNode<T>> queue;
    //根节点
    private HuffmanNode<T> root;
    /**
     * 构造函数
     * @param huffmannodes
     */
    public HuffmanTree(IList<HuffmanNode<T>> huffmannodes)
    {
        this.queue=new PriorityQueue<>(new Comparator<HuffmanNode<T>>()
        {
            @Override
            public int compare(HuffmanNode<T> o1, HuffmanNode<T> o2)
            {
                return Double.compare(o1.weight,o2.weight);
            }
        });
        for (int i = 0; i < huffmannodes.getCount(); i++)
        {
            this.queue.enter(huffmannodes.get(i));
        }
        while (!this.queue.isEmpty())
        {
            var x=this.queue.out();
            if(this.queue.isEmpty())
            {
                this.root=x;
                return;
            }
            var y=this.queue.out();
            this.queue.enter(new HuffmanNode<T>(null,x.weight+y.weight,x,y));
        }
    }
    /**
     * 哈夫曼树
     *
     * @param data   标识数组
     * @param weight 权重数组
     */
    public HuffmanTree(T[] name, double[] weight)
    {
        this.queue = new PriorityQueue<>(new Comparator<HuffmanNode<T>>()
        {
            @Override
            public int compare(HuffmanNode<T> o1, HuffmanNode<T> o2)
            {
                return Double.compare(o2.weight, o1.weight);
            }
        });
        for (int i = 0; i < name.length; i++)
            this.queue.enter(new HuffmanNode<T>(name[i], weight[i]));
        while (!queue.isEmpty())
        {
            var x = queue.out();
            if (queue.isEmpty())
            {
                this.root = x;
                return;
            }
            var y = queue.out();
            var t = new HuffmanNode<T>(null, x.weight + y.weight, x, y);
            queue.enter(t);
        }
    }

    /**
     * 获取带权路径长度
     *
     * @return 获取带权路径长度
     */
    public double getWPL()
    {
        var q = new LinkQueue<HuffmanNode<T>>();
        double wpl = 0;
        int level = 0;
        q.enter(this.root);
        while (!q.isEmpty())
        {
            level++;
            int count = q.getCount();
            for (int i = 0; i < count; i++)
            {
                var t = q.out();
                if (t.name != null)
                    wpl += t.weight * (level - 1);
                if (t.left != null)
                    q.enter(t.left);
                if (t.right != null)
                    q.enter(t.right);
            }
        }
        return wpl;
    }

    /**
     * 获取哈夫曼编码
     * @param map 返回哈夫曼编码表
     */
    public void getCode(Map<T,String> mp)
    {
        IQueue<HuffmanNode<T>> q = new LinkQueue<>();
        IQueue<String> qStr = new LinkQueue<>();
        q.enter(root);
        qStr.enter("");
        while (!q.isEmpty())
        {
            var temp = q.out();
            String curStr = qStr.out();
            //叶子节点
            if (temp.left == null && temp.right == null)
                mp.put(temp.name,curStr);
               // ls.add(curStr + temp.name);
            if (temp.left != null)
            {
                q.enter(temp.left);
                qStr.enter(curStr+"0");
                //qStr.enter(curStr + temp.name + "->");
            }
            if (temp.right != null)
            {
                q.enter(temp.right);
                qStr.enter(curStr+"1");
                //qStr.enter(curStr + temp.name + "->");
            }
        }
    }
}
