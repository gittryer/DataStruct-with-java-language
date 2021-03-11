import com.LinearStruct.LinkList;
import com.LinearStruct.LinkListUtil;

class Main
{
    public static void main(String[] args)
    {
        LinkList<Integer> ls=new LinkList<>();
        LinkList<Integer> ly=new LinkList<>();
        for (int i = 0; i < 10; i++)
        {
            ls.add(i);
        }
        for (int i = 10; i <20 ; i++)
        {
            ly.add(i);
        }
        var p=LinkListUtil.merge(ls.getHead(),ly.getHead());
        while (p.next!=null)
        {

            p=p.next;
            System.out.println(p.data);
        }
    }
}