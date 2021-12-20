package cn.szuer.publicboard.utils;

import java.util.Random;

public class CodeRandomUtil
{
    private static final String ALLCHARS="A0B1C2D3E4F5G6H7I8J9K0L1M2N3O4P5Q6R7S8T9U0V1W2X3Y4Z5";
    public static String verifyCode(int n)
    {
        StringBuilder sb=new StringBuilder();
        Random r=new Random();
        for (int i=0;i<n;i++)
        {
            sb.append(ALLCHARS.charAt(r.nextInt(52)));
        }
        return sb.toString();
    }
}
