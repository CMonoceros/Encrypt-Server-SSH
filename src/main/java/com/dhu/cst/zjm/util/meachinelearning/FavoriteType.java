package com.dhu.cst.zjm.util.meachinelearning;

import com.dhu.cst.zjm.entity.FavoriteTypeDataSetEntity;
import com.dhu.cst.zjm.entity.base.EncryptFrequencyBaseEntity;
import com.dhu.cst.zjm.entity.base.EncryptTypeBaseEntity;
import com.dhu.cst.zjm.util.JsonUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.collections.map.HashedMap;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Created by zjm on 2017/5/30.
 */
public class FavoriteType {

    public static Map<Integer, List<Double>> prepareFreqMap(List<EncryptFrequencyBaseEntity> encryptFrequencyBaseEntityList) {
        Map<Integer, List<Double>> freqMap = new HashedMap();
        double day, week, month, year;
        day = week = month = year = 0.0;
        for (EncryptFrequencyBaseEntity encryptFrequencyBaseEntity : encryptFrequencyBaseEntityList) {
            day += encryptFrequencyBaseEntity.getDayFrequency();
            week += encryptFrequencyBaseEntity.getWeekFrequency();
            month += encryptFrequencyBaseEntity.getMonthFrequency();
            year += encryptFrequencyBaseEntity.getYearFrequency();
        }
        for (EncryptFrequencyBaseEntity encryptFrequencyBaseEntity : encryptFrequencyBaseEntityList) {
            List<Double> freqList = new ArrayList<>();
            double dayFreq = encryptFrequencyBaseEntity.getDayFrequency() / day;
            double weekFreq = encryptFrequencyBaseEntity.getWeekFrequency() / week;
            double monthFreq = encryptFrequencyBaseEntity.getMonthFrequency() / month;
            double yearFreq = encryptFrequencyBaseEntity.getYearFrequency() / year;
            freqList.add(dayFreq);
            freqList.add(weekFreq);
            freqList.add(monthFreq);
            freqList.add(yearFreq);
            freqMap.put(encryptFrequencyBaseEntity.getTypeId(), freqList);
        }
        return freqMap;
    }

    public static List<Integer> predictFavoriteTypeList(Map<Integer, List<Double>> freqMap, int owner) {
        Gson gson = new Gson();
        String par = gson.toJson(freqMap);
        par = par.replaceAll("\"", "'");
        System.out.println("==Python set" + par + "==");

        Map<Integer, Integer> resMap = new HashedMap();
        List<Integer> result = new ArrayList<>();
        try {
            String s;
            Process rt = Runtime.getRuntime().exec("F:\\Anaconda\\python E:\\Code\\FavoriteType.py" +
                    " --json " + par + " --owner " + owner);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(rt.getInputStream()));
            while ((s = bufferedReader.readLine()) != null) {
                System.out.println("====Python get" + s + "====");
                resMap = gson.fromJson(s, new TypeToken<Map<Integer, Integer>>() {
                }.getType());
            }
            rt.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        List<Map.Entry<Integer, Integer>> listEntry =
                new ArrayList<>(resMap.entrySet());
        Collections.sort(listEntry, new Comparator<Map.Entry<Integer, Integer>>() {
            public int compare(Map.Entry<Integer, Integer> o1,
                               Map.Entry<Integer, Integer> o2) {
                return (o2.getValue() - o1.getValue());
            }
        });
        Iterator<Map.Entry<Integer, Integer>> iter = listEntry.iterator();
        Map.Entry<Integer, Integer> entry;
        while (iter.hasNext()) {
            entry = iter.next();
            result.add(entry.getKey());
        }
        return result;
    }


    public static Boolean trainFavoriteTypeListByLabel(List<FavoriteTypeDataSetEntity> trainSet) {
        Map<Integer, List<Double>> typeMap = new HashedMap();
        String label = "";
        for (FavoriteTypeDataSetEntity favoriteTypeDataSetEntity : trainSet) {

            List<Double> list = new ArrayList<>();
            list.add(favoriteTypeDataSetEntity.getDayScale());
            list.add(favoriteTypeDataSetEntity.getWeekScale());
            list.add(favoriteTypeDataSetEntity.getMonthScale());
            list.add(favoriteTypeDataSetEntity.getYearScale());
            typeMap.put(favoriteTypeDataSetEntity.getTypeId(), list);
            label += favoriteTypeDataSetEntity.getRate();
            label += ",";
        }
        Gson gson = new Gson();
        String par = gson.toJson(typeMap);
        par = par.replaceAll("\"", "'");
        System.out.println("====Python set" + par + "====" + label + "====");

        Map<Integer, Integer> resMap = new HashedMap();
        try {
            String s;
            Process rt = Runtime.getRuntime().exec("F:\\Anaconda\\python E:\\Code\\FavoriteType.py" +
                    " --json " + par + " --owner " + trainSet.get(0).getOwner() +
                    " --isTrain " + "true" + " --label " + label);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(rt.getInputStream()));
            while ((s = bufferedReader.readLine()) != null) {
                System.out.println("====Python get" + s + "====");
            }
            rt.waitFor();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
