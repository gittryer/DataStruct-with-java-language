package com.BTree;
import com.LinearStruct.ArrayList;
import com.LinearStruct.IList;
import com.LinearStruct.LinkList;
import com.Queue.IQueue;
import com.Queue.LinkQueue;
import com.Queue.PriorityQueue;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

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
     * @return 返回哈夫曼码表
     */
    public Map<T,String> getCode()
    {
        Map<T,String> mp = new TreeMap<>();
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
        return mp;
    }

    public static IList<HuffmanNode<Byte>> getNodes(byte[]data)
    {
        IList<HuffmanNode<Byte>>ls=new LinkList<>();
        Map<Byte,Double> map=new TreeMap<>();
        for (int i = 0; i < data.length; i++)
        {
            var count=map.get(data[i]);
            if(count==null)
                map.put(data[i],1.);
            else
                map.put(data[i],count+1.);
        }
        for(Map.Entry<Byte,Double> item:map.entrySet())
        {
            var node=new HuffmanNode<Byte>(item.getKey(),item.getValue());
            ls.add(node);
        }
        return ls;
    }
}
