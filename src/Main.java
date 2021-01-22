import com.BTree.BTree;
import com.LinearStruct.ArrayList;
import com.LinearStruct.IList;
import com.LinearStruct.LinkList;
import com.LinearStruct.LinkListUtil;
import com.Queue.PriorityQueue;
import com.Queue.RandomQueue;

public class Main
{
    public static void main(String[] args)
    {
        LinkList<Integer> ls=new LinkList<>();
        for (int i = 1; i < 5; i++)
        {
            ls.add(i);
        }
        var p=LinkListUtil.getMid(ls);
        System.out.println(p.data);

    }
}
