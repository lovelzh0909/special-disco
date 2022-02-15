package com.example.demo.Response;

import lombok.Data;

@Data
public class CommonReturnType {
    private String msg;
    private Integer status;
    private Object data;

    public static CommonReturnType create(Object result)
    {
        return CommonReturnType.create(result,"success");
    }

    public static CommonReturnType create(Object result,String msg)
    {
        CommonReturnType commonReturnType = new CommonReturnType();
        commonReturnType.setData(result);
        commonReturnType.setMsg(msg);
        if(msg.equals("success"))
        {
            commonReturnType.setStatus(1);
        }else
        {
            commonReturnType.setStatus(0);
        }
        return commonReturnType;
    }
}
