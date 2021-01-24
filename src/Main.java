import com.BTree.BTree;
import com.LinearStruct.*;
import com.Queue.*;

public class Main
{
    public static void main(String[] args)
    {
        var sq=new SeqDeque<Integer>(3);
        for (int i = 0; i < 50; i++)
        {
            sq.pushLeft(i);
        }
        System.out.println(sq.getCount());
        while (!sq.isEmpty())
        {
            System.out.println(sq.popRight());
        }

    }
}
