package com.dataStruct.stack;

/**
 * 栈辅助工具类
 */
public  class StackUtil
{
    /**
     * 10进制数字转为p进制
     * @param val 数据值
     * @param p 进制
     * @return 返回转换后的数值
     */
    public static String from10(int val, int p)
    {
        if(val==0)
            return "0";
        var sk=new LinkStack<Integer>();
        var sb=new StringBuilder();
        int ans=0;
        while (val!=0)
        {
            sk.push(val%p);
            val/=p;
        }
        while (!sk.isEmpty())
        {
            var temp=sk.pop();
            sb.append(temp>9?""+(char)('A'+temp-10):temp);
        }
        return sb.toString();
    }

    /**
     * p进制转化为10进制
     * @param val 数字字符串
     * @param p 进制数
     * @return 返回10进制数字
     */
    public static int to10(String val, int p)
    {
        int ans=0;
        int temp=0;
        for (int i = val.length()-1; i >=0 ; i--)
        {
            char ch=Character.toUpperCase(val.charAt(i));
            if(ch>='0'&&ch<='9')
                temp=(int)(ch)-48;
            else if(ch>='A'&&ch<='Z')
                temp=(int)(ch)-55;
            ans+=temp*Math.pow(p,val.length()-i-1);
        }
        return ans;
    }
    /**
     * 括号匹配
     * @param str 字符串
     * @return 返回是否匹配
     */
    public static boolean bracketsJudge(String str)
    {
        var sk=new LinkStack<Character>();
        for (int i=0;i<str.length();++i)
        {
            char ch=str.charAt(i);
            if(ch=='('||ch=='['||ch=='{')
                sk.push(ch);
            else if(ch==')')
            {
                if(sk.pop()!='(')
                    return false;
            }
            else if(ch==']')
            {
                if(sk.pop()!='[')
                    return false;
            }
            else if(ch=='}')
            {
                if(sk.pop()!='{')
                    return false;
            }
        }
        return sk.getCount()==0;
    }





}
