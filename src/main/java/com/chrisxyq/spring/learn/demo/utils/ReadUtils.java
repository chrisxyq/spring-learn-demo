package com.chrisxyq.spring.learn.demo.utils;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;


/**
 * @author yuanqixu
 */
public class ReadUtils {
    private static       Gson   gson     = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).create();
    private static final String rootPath = "D:\\allCodes\\myGithubProjects\\javase-demo\\src\\test\\txt";

    public static <T> T getObjFromFile(String fileName, Class<T> t) {
        String s = readJsonFile(fileName);
        return gson.fromJson(s, t);
    }

    public static String readJsonFile(String fileName) {
        String jsonStr = "";
        try {
            File jsonFile = new File(rootPath + fileName);
            FileReader fileReader = new FileReader(jsonFile);

            Reader reader = new InputStreamReader(new FileInputStream(jsonFile), "utf-8");
            int ch = 0;
            StringBuffer sb = new StringBuffer();
            while ((ch = reader.read()) != -1) {
                sb.append((char) ch);
            }
            fileReader.close();
            reader.close();
            jsonStr = sb.toString();
            return jsonStr;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
