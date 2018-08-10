package com.kdb.dao.utils;

import kx.c;
import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ClassUtils;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

public class FlipParser {
    private static final  Logger logger = LoggerFactory.getLogger(FlipParser.class);

    public FlipParser() {
    }

    public static <T> List<T> parseFlip(c.Flip flip, Class<T> instanceType){
        if(ClassUtils.isPrimitiveOrWrapper(instanceType)){
            return prcessSimpleFlip(flip, instanceType);
        } else {
            return processReferenceTypeFlip(flip, instanceType);
        }
    }

    private static <T> List<T> processReferenceTypeFlip(c.Flip flip, Class<T> instanceType) {
        List<T> result = new ArrayList<T>();
        Map<Integer, Field> indexToFieldMap = new HashMap<>();

        try {
            if (null == flip || ArrayUtils.isEmpty(flip.y)) {
                return result;
            } else {
                int rowsNumber = c.n(flip.y[0]);
                String[] colNames = flip.x;
                Map<String,Field> allInstanceFields = getAllFields(instanceType);
                for(int i=0; i< colNames.length;i++){
                    String columnName = colNames[i];
                    if(!allInstanceFields.containsKey(columnName)){
                        logger.debug("No such field in the target object fieldName: {}, InstanceType: {}", colNames, instanceType);
                        continue;
                    }
                    indexToFieldMap.put(i, allInstanceFields.get(columnName));
                }

                if(indexToFieldMap.isEmpty()){
                    throw  new RuntimeException("There were no matching fields for instance "+ instanceType.getCanonicalName());
                }

                for(int rowId = 0; rowId < rowsNumber; rowId++){
                    T instance = instanceType.newInstance();

                    for(Map.Entry<Integer, Field> entry : indexToFieldMap.entrySet()){
                        Integer colId = entry.getKey();
                        Field field = entry.getValue();
                        Object value = c.at(flip.y[colId], rowId);

                        value = prepareValue(field, value);

                        field.set(instance, value);
                    }
                    result.add(instance);
                }
            }

        } catch (UnsupportedEncodingException|IllegalAccessException|InstantiationException e) {
            logger.error("Unable to properly extract data from complex flip.", e);
            throw new RuntimeException("Unable to process complex Flip", e);
        }

        return result;
    }


    private static <T> List<T> prcessSimpleFlip(c.Flip flip, Class<T> instanceType) {
        List<T> result = new ArrayList<T>();

        try{
            if(ArrayUtils.isEmpty(flip.y)){
                return result;
            } else {
                int rowsNumber = c.n(flip.y[0]);
                for(int i = 0; i< rowsNumber;i++){
                    result.add((T) c.at(flip.y[0], i));
                }
            }
        } catch (UnsupportedEncodingException e) {
            logger.error("Unable to properly extract data from flip.", e);
            throw new RuntimeException("Unable to process simple Flip", e);
        }

        return result;
    }



    private static Object prepareValue(Field field, Object value){
        if(null == value) return null;

        if(value instanceof Double && !Double.class.isAssignableFrom(field.getType())){
            return parseDoubleToBigDecimal(field, value);
        } else if(value instanceof Date){
            Date dateValue = new Date(((Date) value).getTime());
            if(LocalDateTime.class.isAssignableFrom(field.getType())){
                return dateValue.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
            } else if(LocalDate.class.isAssignableFrom(field.getType())){
                return dateValue.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            } else if(java.sql.Date.class.isAssignableFrom(field.getType())){
                return new java.sql.Date(dateValue.getTime());
            }else if(java.sql.Timestamp.class.isAssignableFrom(field.getType())){
                return new java.sql.Timestamp(dateValue.getTime());
            } else {
                return dateValue;
            }
        } else if(field.getType().isEnum()){
            Class<Enum> enumType = (Class<Enum>) field.getType();
            return Enum.valueOf(enumType, value.toString());
        }
        return value;
    }

    private static Object parseDoubleToBigDecimal(Field field, Object value){
        try{
            return new BigDecimal(value.toString());
        } catch (NumberFormatException e) {
            logger.error("Failed to parse double {} for {}", value, field);
            return BigDecimal.ZERO;
        }

    }

    private static Map<String, Field> getAllFields(Class clazz) { return getAllFieldsRecursively(clazz, new HashMap());}
    private static Map<String, Field> getAllFieldsRecursively(Class clazz, Map<String, Field> allFields){
        Class superClass = clazz.getSuperclass();

        if(!Object.class.equals(superClass)){
            getAllFieldsRecursively(superClass, allFields);
        }

        Map<String, Field> fileds = Arrays.stream(clazz.getDeclaredFields()).
                collect(Collectors.toMap(
                        Field::getName,
                        field -> {
                            field.setAccessible(true);
                            return field;
                        }
                ));

        allFields.putAll(fileds);

        return allFields;
    }
}
