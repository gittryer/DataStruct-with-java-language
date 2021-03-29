package com.dataStruct.stack.RPN;

/**
 * 切分后的类型
 */
class Part
{
    /**
     * 上一个值
     */
    private static Part LASTVAL;
    /**
     * 字符串数据
     */
    private String str;
    /**
     * 是否为数字
     */
    private Type tag;

    /**
     * 是否为一元运算符
     */
    private boolean isSingleOperator;

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
            {
                switch (str)
                {
                    case "+":
                        if(Part.LASTVAL==null||(Part.LASTVAL.tag!=Type.NUM&&
                                Part.LASTVAL.tag!=Type.RBRACKET))
                        {
                            this.tag=Type.POSITIVE;
                            this.isSingleOperator=true;
                        }
                        else
                            this.tag=Type.ADD;
                        break;
                    case "-":
                        if(Part.LASTVAL==null||(Part.LASTVAL.tag!=Type.NUM&&
                            Part.LASTVAL.tag!=Type.RBRACKET))
                        {
                            this.tag=Type.MINIS;
                            this.isSingleOperator=true;
                        }
                        else
                            this.tag=Type.SUB;
                        break;
                    case "*":
                        this.tag=Type.MUL;
                        break;
                    case "/":
                        this.tag=Type.DIV;
                        break;
                    case "^":
                        this.tag=Type.POW;
                        break;
                    case "%":
                        this.tag=Type.MOD;
                        break;
                    case  "!":
                        this.tag=Type.FACTORIAL;
                        this.isSingleOperator=true;
                        break;
                    case "#":
                        this.tag=Type.END;
                        Part.LASTVAL=null;
                        this.isSingleOperator=true;
                        break;
                     default:
                         break;
                }
            }
        }
        Part.LASTVAL=this;
    }

    /**
     * 获取优先级
     * @return
     */
    public int getOperatorPriproty()
    {
        switch (this.tag)
        {
            case ADD:
            case SUB:
                return 1;
            case MUL:
            case DIV:
            case MOD:
                return 2;
            case POSITIVE:
            case MINIS:
                return 3;
            case POW:
                return 4;
            case FACTORIAL:
                return 5;
            default:
                return -1;
        }
    }

    /**
     * 返回是否为一元运算符
     * @return 是否为一元运算符
     */
    public boolean getIsSingleOperator()
    {
        return isSingleOperator;
    }

    @Override
    public String toString()
    {
        return this.str.toString();
    }
}
