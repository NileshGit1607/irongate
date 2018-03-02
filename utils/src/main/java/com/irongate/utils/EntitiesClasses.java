package com.irongate.utils;

import com.google.common.collect.ImmutableSet;
import com.google.common.reflect.ClassPath;

import java.util.HashMap;
import java.util.Map;

public class EntitiesClasses {

    private static Map<String, Class> entityMap = null;

    public static Class<?> findClass(String entity) {
        if (entityMap == null) {
            entityMap = new HashMap<>();
            try {
                ImmutableSet<ClassPath.ClassInfo> pathSet =
                        ClassPath.from(EntitiesClasses.class.getClassLoader()).
                                getTopLevelClassesRecursive("com.irongate.entities");
                for (ClassPath.ClassInfo classInfo : pathSet) {
                    entityMap.put(classInfo.getSimpleName(), Class.forName(classInfo.getName()));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return entityMap.get(entity);
    }
}
