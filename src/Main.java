import com.btree.huffmanTree.HuffmanUtil;
import com.linearStruct.ArrayList;
import com.linearStruct.IList;
import com.linearStruct.LinkList;
import com.queue.RandomQueue;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

class Main
{
    public static void main(String[] args)
    {
        var bytes="aaaaabbbbbdddddccccfffffbbbbb".getBytes();
        //var bytes="sijhkcjnlkdjmgsaklnb".getBytes();
        IList<Byte> ls=new ArrayList<>();
        for (int i = 0; i < bytes.length; i++)
            ls.add(bytes[i]);

        String ll=HuffmanUtil.encoding(ls);
        var ans=HuffmanUtil.decoding(ll);
        System.out.println("源码="+ls);
        System.out.println("哈夫曼码="+ll);
        System.out.println("解码="+ans);
        var ans_bytes=new byte[ans.getCount()];
        for (int i = 0; i < ans.getCount(); i++)
            ans_bytes[i]=ans.get(i);
        System.out.println("源字符串"+new String(ans_bytes));
    }
}

