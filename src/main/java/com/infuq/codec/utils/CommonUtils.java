package com.infuq.codec.utils;

import io.protostuff.LinkedBuffer;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.runtime.RuntimeSchema;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class CommonUtils {


    /**
     * 序列化
     *
     */
    private static byte[] transferToByte(Object obj) {
        byte[] data = null;

        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            ObjectOutputStream buf = new ObjectOutputStream(out);
            buf.writeObject(obj);

            data = out.toByteArray();

            out.close();
            buf.close();
        } catch (Exception x) {
            x.printStackTrace();
        }
        return data;
    }

    /**
     * 反序列化
     *
     */
    private static Object transferToObject(byte[] bytes) {
        Object data = null;

        try {
            ByteArrayInputStream input = new ByteArrayInputStream(bytes);
            ObjectInputStream out = new ObjectInputStream(input);

            data = out.readObject();

            input.close();
            out.close();
        } catch (Exception x) {
            x.printStackTrace();
        }
        return data;
    }



    /**
     * protostuff 序列化
     *
     */
    public static <T> byte[] serializableProtostuff(T obj){
        try {
            RuntimeSchema schema = RuntimeSchema.createFrom(obj.getClass());
            return ProtostuffIOUtil.toByteArray(obj, schema, LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * protostuff 反序列化
     *
     */
    public static Object unserializableProtostuff(byte[] bytes, Class clazz){
        RuntimeSchema schema = RuntimeSchema.createFrom(clazz);
        Object obj = schema.newMessage();
        ProtostuffIOUtil.mergeFrom(bytes, obj, schema);
        return obj;
    }


}
