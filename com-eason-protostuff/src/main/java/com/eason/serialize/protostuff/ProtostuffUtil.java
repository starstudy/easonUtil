package com.eason.serialize.protostuff;

import io.protostuff.JsonIOUtil;
import io.protostuff.LinkedBuffer;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author sxx
 * @brief
 * @details
 * @date 2018-07-18 14:38
 */
public class ProtostuffUtil {

    private static Map<Class<?>, Schema<?>> cachedSchema = new ConcurrentHashMap<Class<?>, Schema<?>>();


    public ProtostuffUtil() {
    }

    /**
     * @brief 将对象序列化成字节数组
     * @param obj 需要序列化的对象
     * @return byte[] 序列化后的字节shuzu
     * @author sxx
     * @date 2018-07-18 14:51
     * @note sxx@2018-07-18 14:51创建
     */
    public static <T> byte[] serializer(T obj) {
        @SuppressWarnings("unchecked")
        Class<T> clazz = (Class<T>) obj.getClass();
        LinkedBuffer buffer = LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE);
        try {
            Schema<T> schema = getSchema(clazz);
            return ProtostuffIOUtil.toByteArray(obj, schema, buffer);
        } catch (Exception e) {
            throw new IllegalStateException(e.getMessage(), e);
        } finally {
            buffer.clear();
        }
    }

    /**
     * @brief 将字节数组反序列化
     * @param data 字节数组
     * @param clazz 反序列化的Java类
     * @return T 反序列化后的对象
     * @author sxx
     * @date 2018-07-18 14:50
     * @note sxx@2018-07-18 14:50创建
     */
    public static <T> T deserializer(byte[] data, Class<T> clazz) {
        try {
            T obj = clazz.newInstance();
            Schema<T> schema = getSchema(clazz);
            ProtostuffIOUtil.mergeFrom(data, obj, schema);
            return obj;
        } catch (Exception e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
    }

    /**
     * @brief 根据Java类获取模板
     * @detail
     * 采集模板，如有重复的，则不需要重新生成
     * @param clazz
     * @return io.protostuff.Schema<T>
     * @author sxx
     * @date 2018-07-18 14:50
     * @note sxx@2018-07-18 14:50创建
     */
    private static <T> Schema<T> getSchema(Class<T> clazz) {
        @SuppressWarnings("unchecked")
        Schema<T> schema = (Schema<T>) cachedSchema.get(clazz);
        if (schema == null) {
            schema = RuntimeSchema.getSchema(clazz);
            if (schema != null) {
                cachedSchema.put(clazz, schema);
            }
        }
        return schema;
    }

    /**
     * @brief 序列化成json字节流
     * @param obj
     * @return byte[]
     * @author sxx
     * @date 2018-07-18 17:48
     * @note sxx@2018-07-18 17:48创建
     */
    public static <T> byte[] serializeJson(T obj){
        try {
            final ByteArrayOutputStream out = new ByteArrayOutputStream();
            Class<T> clazz = (Class<T>) obj.getClass();
            Schema<T> schema = getSchema(clazz);
            JsonIOUtil.writeTo(out, obj, schema, false);

            return out.toByteArray();
        }catch (Exception e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
    }
}
