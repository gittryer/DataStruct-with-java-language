package com.dataStruct.stack.RPN;

import com.dataStruct.linearStruct.IList;
import com.dataStruct.linearStruct.LinkList;
import com.dataStruct.stack.IStack;
import com.dataStruct.stack.LinkStack;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 逆波兰计算
 */
public class RPNCalaulate
{
    /**
     * 中缀表达式
     */
    private IList<Part> infixExpression;
    /**
     * 逆波兰表达式
     */
    private IList<Part> rpnExpression;
    /**
     * 逆波兰需要用到的栈
     */
    private IStack<Part> stack;

    /**
     * 构造函数
     * @param exp 中缀表达式
     */
    public RPNCalaulate(String exp)
    {
        //初始化
        this.infixExpression =new LinkList<Part>();
        this.stack=new LinkStack<>();
        this.rpnExpression=new LinkList<>();
        //清理空格
        exp=exp.replaceAll(" ","");
        //处理和切分
        expressionSpilt(dealExp(new StringBuilder(exp)));
        //计算
        toRpn();
    }

    /**
     * 处理负号
     * @param exp 表达式
     * @return 处理后的表达式
     */
    private String dealExp(StringBuilder exp)
    {
        for (int i = 0; i < exp.length() ;i++)
        {
            if((exp.charAt(i)=='-'||exp.charAt(i)=='+')&&i==0)
                exp.insert(0,'0');
            else if (exp.charAt(i) == '(' && ( exp.charAt(i + 1) == '-'||exp.charAt(i + 1) == '+'))
                exp.insert(i+1, "0");
        }
        return exp.toString();
    }
//        //清理空格
//        exp=exp.replaceAll(" ","");
//        //处理负号
//        if(exp.startsWith("-(-"))
//            exp="";
//        Pattern p= Pattern.compile("^[^(]\\d+\\.?\\d*|(?<=\\()[^(]\\d+\\.?\\d*/|[^(]\\(.+\\)");
//        Matcher obj=p.matcher(exp);
//        StringBuilder sb=new StringBuilder();
//        while (obj.find())
//            obj.appendReplacement(sb,String.format("(0%s)",obj.group()));
//        obj.appendTail(sb);
//        return sb.toString();
    /**
     * 表达式切分：切分数字和运算符
     * @param exp 运算字符串
     */
    private void expressionSpilt(String exp)
    {
        var sb=new StringBuilder();
        for (int i = 0; i < exp.length(); i++)
        {
            char ch=exp.charAt(i);
            if(isNum(ch))
                sb.append(ch);
            else
            {
                if(sb.length()>0)
                    this.infixExpression.add(new Part(sb.toString(),true));
                this.infixExpression.add(new Part(ch+"",false));
                sb.delete(0,sb.length());
            }
        }
        if(sb.length()>0)
            this.infixExpression.add(new Part(sb.toString(),true));

        System.out.println(this.infixExpression);
    }

    /**
     * 判断一个字符是否为数字
     * @param ch
     * @return
     */
    private boolean isNum(char ch)
    {
        return (ch>='0'&&ch<='9')||ch=='.';
    }

    /**
     * 逆波兰转换
     */
    private void toRpn()
    {
        for (var item:this.infixExpression)
        {
            if(item.getTag()== Type.NUM)
                this.rpnExpression.add(item);
            else if(item.getTag()== Type.LBRACKET)
                this.stack.push(item);
            else if(item.getTag()== Type.RBRACKET)
            {
                while(this.stack.getTop().getTag()!= Type.LBRACKET)
                    this.rpnExpression.add(this.stack.pop());
                this.stack.pop();
            }
            else
            {
                while (!this.stack.isEmpty()&&
                        getOperatorPriproty(this.stack.getTop().toString())
                                >=getOperatorPriproty(item.toString()))
                {
                    this.rpnExpression.add(this.stack.pop());
                }
                this.stack.push(item);
            }
        }
        while (!this.stack.isEmpty())
            this.rpnExpression.add(this.stack.pop());
        System.out.println(this.rpnExpression);
    }

    /**
     * 开始计算
     * @return 返回计算结果
     */
    public double run()
    {
        IStack<Double> sk=new LinkStack<>();
        for (int i = 0; i < this.rpnExpression.getCount(); i++)
        {
            var item=this.rpnExpression.get(i);
            if(item.getTag()== Type.NUM)
                sk.push(Double.parseDouble(item.toString()));
            else
            {
                var x=sk.pop();
                var y=sk.pop();
                sk.push(getVal(y,x,item.toString()));
            }
        }
//        for(var item :this.rpnExpression)
//        {
//            if(item.getTag()== Type.NUM)
//                sk.push(Double.parseDouble(item.toString()));
//            else
//            {
//                var x=sk.pop();
//                var y=sk.pop();
//                sk.push(getVal(y,x,item.toString()));
//            }
//        }
        return sk.pop();
    }

    /**
     * 获取运算符优先级
     * @param str 运算符
     * @return 返回优先级
     */
    private int getOperatorPriproty(String str)
    {
        switch (str)
        {
            case "+":
            case "-":
                return 1;
            case "*":
            case "/":
                return 2;
            case "^":
                return 3;
            default:
                return -1;
        }
    }

    /**
     * 获取两个数字的运算结果
     * @param x 第一个数字
     * @param y  第二个数字
     * @param operator 运算符
     * @return 返回运算结果
     */
    private double getVal(double x,double y,String operator)
    {
        switch (operator)
        {
            case "+":
                return x+y;
            case "-":
                return x-y;
            case "*":
                return x*y;
            case "/":
                return x/y;
            case "^":
                return Math.pow(x,y);
            default:
                return 0;
        }
    }

    private double getVal(double x,String operator)
    {
        switch (operator)
        {
            case "+":
                return x;
            case "-":
                return -x;
            default:
                return x;
        }
    }
}

