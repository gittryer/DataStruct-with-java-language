import com.BTree.BTree;
import com.Queue.PriorityQueue;
import com.Queue.RandomQueue;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main
{
    public static void main(String[] args)
    {
        RandomQueue<Integer>
                rq= new RandomQueue<Integer>();
        for (int i = 0; i < 10; i++)
        {
            rq.enter(i);
        }
        while (!rq.isEmpty())
        {
            System.out.println(rq.out());
        }
    };

}
