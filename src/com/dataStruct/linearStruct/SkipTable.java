package com.dataStruct.linearStruct;

import java.util.Random;
//https://blog.csdn.net/WhereIsHeroFrom/article/details/84997418?utm_medium=distribute.pc_relevant_t0.none-task-blog-BlogCommendFromMachineLearnPai2-1.control&dist_request_id=1328741.28959.16169315968282759&depth_1-utm_source=distribute.pc_relevant_t0.none-task-blog-BlogCommendFromMachineLearnPai2-1.control
/**
 * 跳表
 * @param <T> 数据类型
 */
public class SkipTable<T>
{
    /**
     * 最大层数
     */
    private final  int MAX_LEVEL=32;
    /**
     * 头节点
     */
    private SNode<T> head;
    /**
     * 尾节点
     */
    private SNode<T> tail;
    /**
     * 当前所有节点里的最大层数
     */
    private int maxLevel;
    /**
     * 节点的个数
     */
    private int count;

    /**
     * 构造函数
     */
    public SkipTable()
    {
        //创建一个头节点
        this.head=new SNode<T>(setLevel());
        //初始化最大层数
        this.maxLevel =0;
        //初始化节点数
        this.count=0;
    }

    /**
     * 随机生成层数
     * @return
     */
    private int setLevel()
    {
        Random rnd=new Random(10);
        return rnd.nextInt(MAX_LEVEL);
    }

    /**
     * 获取span
     * @param score
     * @param obj
     * @return
     */
    private int getSpan(double score,String obj)
    {
        /**
         * 从最高层往下找
         * 如果存在后继，且后继的socre或obj比给定的小，更新span
         * 继续找
         * 若字符串不为空且相等，代表找到
         */
        int span = 0;
        var p = this.head;
        for (int i = this.maxLevel - 1; i >= 0; i--)
        {
            while (p.levels[i].next!=null
                    &&(p.levels[i].next.score<score
                    ||p.levels[i].next.obj.compareTo(obj)<0))
            {
                //span加上沿途距离
                span = span + p.levels[i].span;
                //向后找
                p = p.levels[i].next;
            }
            //找到了
            if (p.obj != null && p.obj.equals(obj))
                return span;
        }
        return 0;
    }

    /**
     * 添加元素值
     * @param score 分值
     * @param obj 对象
     */
    public void add(double score,String obj)
    {
        var p=this.head;
        //经过节点的跨度
        int []spans=new int[MAX_LEVEL];
        //查找路径
        SNode[]updates=new SNode[MAX_LEVEL];
        //寻找插入位置
        for (int i = this.maxLevel -1; i >=0 ; --i)
        {
            //顶层span为0
            spans[i]=i==this.maxLevel -1?0:spans[i+1];
            while (p.levels[i].next!=null
                    &&(p.levels[i].next.score<score
                    ||p.levels[i].next.obj.compareTo(obj)<0))
            {
                //加上沿途经过的距离
                spans[i]+=p.levels[i].span;
                p=p.levels[i].next;
            }
            //记录查找过
            updates[i]=p;
        }
        //随机插入层
        int level=setLevel();
        if(level>this.maxLevel)
        {
            //更新超出部分的updates和spans
            for (int i =maxLevel; i < level; i++)
            {
                spans[i]=0;
                updates[i]=this.head;
                updates[i].levels[i].span=this.count;
            }
            this.maxLevel=level;
        }
        //创建新节点
        p=new SNode<T>(level,score,obj);
        //开始插入，更新每一层的前后引用
        for (int i = 0; i <level; i++)
        {

        }

    }
}
