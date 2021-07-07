package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PropertyManager {


    private static ThreadLocal<Properties> propertiesThreadLocal = new ThreadLocal<>();
    private PropertyManager(){
    }

    public static void initProperties(String relativePath){
//        try {
//            System.out.println(relativePath);
//            propertiesThreadLocal.set(new Properties());
//            propertiesThreadLocal.get().load(new FileInputStream(System.getProperty("user.dir")+ File.separator + relativePath));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


    }

    public static String getValue(String key){
        return propertiesThreadLocal.get().getProperty(key);
    }

    public static HashMap<Object,Object> getPropertiesMap(){

        HashMap<Object,Object> propertiesMap = new HashMap<>();

        for (Map.Entry<Object,Object> property:propertiesThreadLocal.get().entrySet()) {
            propertiesMap.put(property.getKey(),property.getValue());
        }

        return propertiesMap;
    }


}
