package com.dataStruct.stack.RPN;

/**
 * 切分后的类型
 */
class Part
{
    /**
     * 该部分的数据
     */
    private String str;
    /**
     * 是否为数字
     */
    private Type tag;

    /**
     * 返回是否为数字
     * @return 是否为数字
     */
    public Type getTag()
    {
        return tag;
    }
    /**
     * 构造函数
     * @param str 数据
     * @param tag 类型
     */
    public Part(String str, Type tag)
    {
        this.str=str;
        this.tag =tag;
    }

    /**
     * 构造函数
     * @param str 字符串
     * @param isNum 是否为数字
     */
    public Part(String str,boolean isNum)
    {
        this.str=str;
        if(isNum)
            this.tag= Type.NUM;
        else
        {
            if(str.equals("("))
                this.tag= Type.LBRACKET;
            else if(str.equals(")"))
                this.tag= Type.RBRACKET;
            else
                this.tag= Type.OPERATOR;
        }
    }

    @Override
    public String toString()
    {
        return this.str.toString();
    }
}
