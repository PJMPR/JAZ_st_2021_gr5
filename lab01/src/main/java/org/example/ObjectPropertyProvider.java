package org.example;


import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.time.temporal.IsoFields;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class ObjectPropertyProvider {

    public List<Method> getPublicGetters(Class<?> clazz){

        List<Method> methods = Arrays.stream(clazz.getDeclaredMethods()).toList();
        List<Method> result = new ArrayList<>();
        MethodInspector inspector = new MethodInspector();

        for (Method method : methods) {
            inspector.setMethod(method);
            if(inspector.isGettter())
                result.add(method);
        }
        return result;
    }


    public List<Method> getPublicSetters(Class<?> clazz){
        List<Method> methods = Arrays.stream(clazz.getDeclaredMethods()).toList();
        List<Method> result = new ArrayList<>();
        MethodInspector inspector = new MethodInspector();
        for (Method method : methods) {
            inspector.setMethod(method);
            if(inspector.isSetter())
                result.add(method);
        }
        return result;
    }


    public List<Field> getFieldsForPublicProperties(Class<?> clazz){

        List<Field> fields = Arrays.stream(clazz.getDeclaredFields()).toList();
        List<Field> result = new ArrayList<>();
        List<Method> methods = getPublicGetters(clazz);
        methods.addAll(getPublicSetters(clazz));

        MethodInspector inspector = new MethodInspector();
        for (Method method :
                methods) {
            inspector.setMethod(method);
            String fieldName = inspector.getFieldName();
            for (Field field :
                    fields) {
             if(field.getName()==fieldName && !fields.contains(field))
                 result.add(field);
            }
        }
        return result;
    }


    class MethodInspector{
        Method method;
        public void setMethod(Method method) {
            this.method=method;
        }

        public boolean isPublic() {
            return Modifier.isPublic(method.getModifiers());
        }

        public boolean startsWith(String prefix) {
            return method.getName().startsWith(prefix);
        }

        public boolean isVoid() {
            return method.getReturnType().equals(void.class);
        }

        public boolean hasOneParameter() {
            return method.getParameterCount()==1;
        }

        public String getFieldName() {
            return method.getName()
                    .replace("get", "")
                    .replace("set","")
                    .toLowerCase(Locale.ROOT);
        }

        public boolean isGettter() {
            return isPublic()
                    && startsWith("get")
                    && !isVoid()
                    && !hasOneParameter();
        }

        public boolean isSetter(){
            return isPublic()
                    && startsWith("set")
                    && isVoid()
                    && hasOneParameter();
        }
    }
}
