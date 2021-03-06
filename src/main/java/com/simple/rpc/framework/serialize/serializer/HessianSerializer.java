package com.simple.rpc.framework.serialize.serializer;


import com.caucho.hessian.io.HessianInput;
import com.caucho.hessian.io.HessianOutput;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

/**
 * Hessian序列化协议:要求所有参与序列化的类必须实现Serializable接口
 *
 * @author jacksu
 * @date 2018/8/8
 */
public class HessianSerializer implements Serializer {

    @Override
    public byte[] serialize(Object obj) {
        if (null == obj) {
            throw new NullPointerException();
        }
        try {
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            HessianOutput ho = new HessianOutput(os);
            ho.writeObject(obj);
            return os.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public <T> T deserialize(byte[] data, Class<T> clazz) {
        if (null == data) {
            throw new NullPointerException();
        }
        try {
            ByteArrayInputStream is = new ByteArrayInputStream(data);
            HessianInput hi = new HessianInput(is);
            return (T) hi.readObject();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
