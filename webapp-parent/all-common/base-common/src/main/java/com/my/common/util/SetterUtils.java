package com.my.common.util;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;

public class SetterUtils {
    public static List<String> getSetterMethodString(String paramterName, Class<?> clazz){
        Method[] methods = clazz.getMethods();
        List<String> list = new ArrayList<String>();
        for(Method method : methods){
            if(!method.getName().startsWith("set")){
                continue;
            }
            if(paramterName == null){
                paramterName = "paramDTO";
            }
            StringBuilder sBuilder = new StringBuilder(paramterName);
            sBuilder.append(".");
            if(method.getName().startsWith("set")){
                sBuilder.append(method.getName()).append("(");
                Parameter[] parameters = method.getParameters();
                if(parameters == null){
                    sBuilder.append(")");
                    list.add(sBuilder.toString());
                    continue;
                }
                int count = parameters.length, i = 0;
                for(Parameter parameter : parameters){
                    String parameterName = parameter.getName();
                    if(i < count -1){
                        sBuilder.append(parameterName).append(",");
                    }else{
                        sBuilder.append(parameterName);
                    }
                    i++;
                }
                sBuilder.append(");");
                list.add(sBuilder.toString());
            }
        }
        return list;
    }

    public static void printSetter(Class<?> clazz){
        List<String> list = getSetterMethodString(null, clazz);
        for(String string : list){
            System.out.println(string);
        }
    }

    public static void printSetter(String paramterName, Class<?> clazz){
        List<String> list = getSetterMethodString(paramterName, clazz);
        System.out.println("");
        for(String string : list){
            System.out.println(string);
        }
    }

    public static void printObjectValueFromAnotherObject(Class<?> clazz, String getterDTO){
        List<String> list = setObjectValueFromAnotherObject(clazz, getterDTO);
        for(String string : list){
            System.out.println(string);
        }
    }

    public static List<String> setObjectValueFromAnotherObject(Class<?> clazz, String getterDTO){
        Method[] methods = clazz.getMethods();
        List<String> list = new ArrayList<String>();
        for(Method method : methods){
            if(!method.getName().startsWith("set")){
                continue;
            }
            StringBuilder sBuilder = new StringBuilder("paramDTO");
            sBuilder.append(".");
            sBuilder.append(method.getName()).append("(");
            sBuilder.append(getterDTO).append(".g");
            String methodName = method.getName();
            sBuilder.append(methodName.substring(1));
            sBuilder.append("());");
            list.add(sBuilder.toString());
        }
        return list;
    }
}
