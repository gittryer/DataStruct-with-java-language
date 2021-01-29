import com.BTree.BTree;
import com.LinearStruct.*;
import com.Queue.*;

import java.util.Comparator;

public class Main
{
    public static void main(String[] args)
    {
        var ls=new Integer[]{3,2,1,4,5};
        var bs=new BTree<Integer>(ls);
        bs.LDR();
    }
}
