package com.btree.huffmanTree;

import com.linearStruct.IList;
import com.linearStruct.LinkList;

import java.util.Map;
import java.util.TreeMap;

public class HuffmanUtil
{
    /**
     * 哈夫曼编码表
     */
    public static Map<Byte,String> huffmanCodeTable;
    /**
     * 二进制串转字节数组
     * @param binary 二进制串
     * @return 返回字节数组
     */
    private static IList<Byte> binaryToByte(String binary)
    {
        //哈夫曼字节数组
        IList<Byte> huffmanByteCode=new LinkList<>();
        //8位一组生成字节
        for (int i = 0; i < binary.length(); i+=8)
        {
            if(i+8<binary.length())
                huffmanByteCode.add((byte)Integer.parseInt(binary.substring(i,i+8),2));
            else
                huffmanByteCode.add((byte)Integer.parseInt(binary.substring(i),2));
        }
        return huffmanByteCode;
    }
    /**
     * 字节数组转二进制串
     * @param bytes 字节数组
     * @return 返回二进制转
     */
    private static String ByteToBinary(IList<Byte> bytes)
    {
        //存储二进制串
        var sb=new StringBuilder();
        //字节数组转二进制放入
        for (int i = 0; i < bytes.getCount(); i++)
            sb.append(Integer.toBinaryString(bytes.get(i)));
        //返回二进制串
        return sb.toString();
    }

    /**
     * 哈夫曼编码
     * @param bytes 字节数组
     * @return 返回哈夫曼编码
     */
    public static String encoding(IList<Byte> bytes)
    {
        //创建哈夫曼树
        var tree=HuffmanUtil.createHuffmanTree(bytes);
        //获取哈夫曼编码表
        HuffmanUtil.huffmanCodeTable=tree.getCode();
        //存储哈夫曼编码
        var sb=new StringBuilder();
        //放入二进制串
        for(var item :bytes)
            sb.append(HuffmanUtil.huffmanCodeTable.get(item));
        return sb.toString();
    }

    /**
     * 哈夫曼解码
     * @param str 哈夫曼编码门字符串
     * @return 返回解码后的字节数组
     */
    public static IList<Byte> decoding(String str)
    {
        //建立反向编码表
        var codes=new TreeMap<String,Byte>();
        for (var item :HuffmanUtil.huffmanCodeTable.entrySet())
            codes.put(item.getValue(),item.getKey());
        //开始解码
        IList<Byte> bytes=new LinkList<>();
        var temp=new StringBuilder();
        for (int i = 0; i < str.length(); i++)
        {
            temp.append(str.charAt(i));
            var code=codes.get(temp.toString());
            if(code!=null)
            {
                bytes.add(code);
                temp.delete(0,temp.length());
            }
        }
        return bytes;
    }
//    private void getCode(HuffmanNode<T> root,String code,StringBuilder SB)
//    {
//        var temp=new StringBuilder();
//        temp.append(code);
//        if(root.name==null)
//        {
//            getCode(root.left,"0",temp);
//            getCode(root.right,"1",temp);
//        }
//        else
//            //加入map
//    }

    /**
     * 根据字节数组中字节出现频率创建哈夫曼树
     * @param bytes 字节数组
     * @return 哈夫曼树
     */
    private static HuffmanTree<Byte> createHuffmanTree(IList<Byte> bytes)
    {
        IList<HuffmanNode<Byte>>ls=new LinkList<>();
        Map<Byte,Double> map=new TreeMap<>();
        //统计出现次数
        for (int i = 0; i < bytes.getCount(); i++)
        {
            //存储出现次数
            var count=map.get(bytes.get(i));
            if(count==null)//没有出现
                map.put(bytes.get(i),1.);
            else//已经出现
                map.put(bytes.get(i),count+1.);
        }
        //加入哈夫曼节点表
        for(Map.Entry<Byte,Double> item:map.entrySet())
        {
            var node=new HuffmanNode<Byte>(item.getKey(),item.getValue());
            ls.add(node);
        }
        return new HuffmanTree<Byte>(ls);
    }
}
