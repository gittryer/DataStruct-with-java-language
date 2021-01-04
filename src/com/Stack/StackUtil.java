package com.Stack;

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
    public static String From10(int val,int p)
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
     * 括号匹配
     * @param str 字符串
     * @return 返回是否匹配
     */
    public static boolean BracketsJudge(String str)
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

    public static  double Eval(String str)
    {
        var arr=str.split(" ");
        var sk1=new LinkStack<String>();
        var sk2=new LinkStack<Double>();
        for (int i=0;i<arr.length;++i)
        {
            if(arr[i].equals("("));
            else if(arr[i].equals("+"))
                sk1.push("+");
            else if(arr[i].equals("-"))
                sk1.push("-");
            else if(arr[i].equals("*"))
                sk1.push("*");
            else if(arr[i].equals("/"))
                sk1.push("/");
            else if(arr[i].equals("**"))
                sk1.push("**");
            else if(arr[i].equals(")"))
            {
                var op=sk1.pop();
                var x=sk2.pop();
                var y=sk2.pop();
                if(op.equals("+"))
                    sk2.push(x+y);
                else if(op.equals("-"))
                    sk2.push(x-y);
                else if(op.equals("*"))
                    sk2.push(x*y);
                else if(op.equals("/"))
                    sk2.push(x/y);
                else if(op.equals("**"))
                    sk2.push(Math.pow(y,x));
            }
            else
                sk2.push(Double.parseDouble(arr[i]));
        }
        return sk2.pop();
    }
}
