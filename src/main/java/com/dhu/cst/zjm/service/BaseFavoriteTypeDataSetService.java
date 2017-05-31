package com.dhu.cst.zjm.service;

import com.dhu.cst.zjm.base.BaseDaoSupport;
import com.dhu.cst.zjm.entity.FavoriteTypeDataSetEntity;

import java.util.List;
import java.util.Map;

/**
 * Created by zjm on 2017/5/31.
 */
public interface BaseFavoriteTypeDataSetService extends BaseDaoSupport<FavoriteTypeDataSetEntity> {
    void saveFavoriteTypeDataByMap(Map<Integer, List<Double>> freqMap, int owner);

    boolean updateFavoriteTypeRateByOwerAndTypeMap(int owner,Map<Integer, List<Double>> freqMap,int rate);

    void updateFavoriteTypeTrainByOwerAndTypeMap(List<FavoriteTypeDataSetEntity> trainSet);

    List<FavoriteTypeDataSetEntity> getFavoriteTypeDataByOwner(int owner);
}
