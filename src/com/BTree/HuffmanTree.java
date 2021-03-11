package com.BTree;
import com.Queue.LinkQueue;
import java.util.Comparator;

/**
 * 哈夫曼数据
 * @param <T> 节点数据类型
 */
class HuffmanData<T>
{
    //数据域
    public T name;
    //权重
    public double weight;

    /**
     * 构造函数
     * @param data 数据域
     * @param weight 权重
     */
    public HuffmanData(T data,double weight)
    {
        this.name =data;
        this.weight=weight;
    }
}

/**
 * 哈夫曼树
 * @param <T> 数据类型
 */
public class HuffmanTree<T>
{
    //堆
    private BHeap<HuffmanNode<HuffmanData<T>>> hHeap;
    //根节点
    private HuffmanNode<HuffmanData<T>> root;

    /**
     * 哈夫曼树
     * @param data 哈夫曼数据数组
     */
    public HuffmanTree(HuffmanData<T>[]data)
    {
        //初始化堆
        hHeap=new BHeap<HuffmanNode<HuffmanData<T>>>(new Comparator<HuffmanNode<HuffmanData<T>>>()
        {
            @Override
            public int compare(HuffmanNode<HuffmanData<T>> o1, HuffmanNode<HuffmanData<T>> o2)
            {
                return Double.compare(o2.data.weight,o1.data.weight);
            }
        });
        //构建堆
        for (int i = 0; i < data.length; i++)
            hHeap.add(new HuffmanNode<HuffmanData<T>>(data[i]));
        //构建哈夫曼树
        while (!hHeap.isEmpty())
        {
            var x=hHeap.remove();
            if(hHeap.isEmpty())
            {
                this.root=x;
                return;
            }
            var y=hHeap.remove();
            var t=new HuffmanNode<HuffmanData<T>>(new HuffmanData<T>(null,
                    x.data.weight+y.data.weight),x,y);
            hHeap.add(t);
        }
    }

    /**
     * 哈夫曼树
     * @param data 标识数组
     * @param weight 权重数组
     */
    public HuffmanTree(T[]data,double[]weight)
    {
        hHeap=new BHeap<HuffmanNode<HuffmanData<T>>>(new Comparator<HuffmanNode<HuffmanData<T>>>()
        {
            @Override
            public int compare(HuffmanNode<HuffmanData<T>> o1, HuffmanNode<HuffmanData<T>> o2)
            {
                return Double.compare(o2.data.weight,o1.data.weight);
            }
        });
        for (int i = 0; i < data.length; i++)
            hHeap.add(new HuffmanNode<HuffmanData<T>>(new HuffmanData<T>(data[i],weight[i])));
        while (!hHeap.isEmpty())
        {
            var x=hHeap.remove();
            if(hHeap.isEmpty())
            {
                this.root=x;
                return;
            }
            var y=hHeap.remove();
            var t=new HuffmanNode<HuffmanData<T>>
                    (new HuffmanData<T>(null,x.data.weight+y.data.weight),x,y);
            hHeap.add(t);
        }
    }
    /**
     * 获取带权路径长度
     * @return 获取带权路径长度
     */
    public double getWPL()
    {
        var q=new LinkQueue<HuffmanNode<HuffmanData<T>>>();
        double wpl=0;
        int level=0;
        q.enter(this.root);
        while (!q.isEmpty())
        {
            level++;
            int count=q.getCount();
            for (int i = 0; i < count; i++)
            {
                var t=q.out();
                if(t.data.name!=null)
                    wpl+=t.data.weight*(level-1);
                if(t.left!=null)
                    q.enter(t.left);
                if(t.right!=null)
                    q.enter(t.right);
            }
        }
        return wpl;
    }
}
