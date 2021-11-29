package com.example.demo.util;

import com.example.demo.exception.CustomException;
import com.fasterxml.jackson.databind.exc.IgnoredPropertyException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

public class SerializableUtil {
    private SerializableUtil(){}

    private final static Logger log= LoggerFactory.getLogger(SerializableUtil.class);

    //序列化
    public static byte[] serializable(Object object)
    {
        ByteArrayOutputStream baos=null;
        ObjectOutputStream oos=null;
        try
        {
            baos=new ByteArrayOutputStream();
            oos=new ObjectOutputStream(baos);
            oos.writeObject(object);
            return baos.toByteArray();
        }catch (IOException e)
        {
            log.error("序列化出现异常："+e.getMessage());
            throw new CustomException("序列化工具出现IOException异常:"+e.getMessage());
        }finally {
            try
            {
                if(oos!=null)
                {
                    oos.close();
                }
                if(baos!=null)
                {
                    baos.close();
                }
            }catch (IOException e)
            {
                log.error("出现序列化异常"+e.getMessage());
                throw new CustomException("出现异常"+e.getMessage());
            }
        }
    }

    //反序列化
    public static Object unserializable(byte[] bytes)
    {
        ByteArrayInputStream bais=null;
        ObjectInputStream ois=null;
        try
        {
            bais=new ByteArrayInputStream(bytes);
            ois=new ObjectInputStream(bais);
            return ois.readObject();
        }catch (ClassNotFoundException e)
        {
            log.error("反序列化出现异常："+e.getMessage());
            throw new CustomException("反序列化出现异常："+e.getMessage());
        }catch (IOException e)
        {
            log.error("反序列化出现异常："+e.getMessage());
            throw new CustomException("反序列化出现异常："+e.getMessage());
        }finally {
            try
            {
                if(ois!=null)
                {
                    ois.close();
                }
                if(bais!=null)
                {
                    bais.close();
                }
            }catch (IOException e)
            {
                log.error("反序列化出现异常："+e.getMessage());
                throw new CustomException("反序列化出现异常："+e.getMessage());
            }
        }
    }
}
