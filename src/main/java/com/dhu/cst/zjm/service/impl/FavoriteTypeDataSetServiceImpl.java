package com.dhu.cst.zjm.service.impl;

import com.dhu.cst.zjm.base.BaseDaoSupportImpl;
import com.dhu.cst.zjm.entity.FavoriteTypeDataSetEntity;
import com.dhu.cst.zjm.service.BaseFavoriteTypeDataSetService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Created by zjm on 2017/5/31.
 */
@Transactional
@Service("favoriteTypeDataSetService")
public class FavoriteTypeDataSetServiceImpl extends BaseDaoSupportImpl<FavoriteTypeDataSetEntity> implements BaseFavoriteTypeDataSetService {

    @Override
    public void saveFavoriteTypeDataByMap(Map<Integer, List<Double>> freqMap, int owner) {
        for (Map.Entry<Integer, List<Double>> entry : freqMap.entrySet()) {
            FavoriteTypeDataSetEntity favoriteTypeDataSetEntity = new FavoriteTypeDataSetEntity();
            favoriteTypeDataSetEntity.setOwner(owner);
            favoriteTypeDataSetEntity.setTypeId(entry.getKey());
            List<Double> list = entry.getValue();
            favoriteTypeDataSetEntity.setDayScale(list.get(0));
            favoriteTypeDataSetEntity.setWeekScale(list.get(1));
            favoriteTypeDataSetEntity.setMonthScale(list.get(2));
            favoriteTypeDataSetEntity.setYearScale(list.get(3));
            try {
                save(favoriteTypeDataSetEntity);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean updateFavoriteTypeRateByOwerAndTypeMap(int owner, Map<Integer, List<Double>> freqMap, int rate) {
        double day = 0, week = 0, month = 0, year = 0;
        int type = 0;
        for (Map.Entry<Integer, List<Double>> entry : freqMap.entrySet()) {
            type = entry.getKey();
            List<Double> list = entry.getValue();
            day = list.get(0);
            week = list.get(1);
            month = list.get(2);
            year = list.get(3);
        }
        try {
            getSession().createQuery("update FavoriteTypeDataSetEntity f set f.rate=? " +
                    "where owner=? and typeId=? and dayScale=? and weekScale=? and monthScale=? and yearScale=?")
                    .setParameter(0, rate)
                    .setParameter(1, owner)
                    .setParameter(2, type)
                    .setParameter(3, day)
                    .setParameter(4, week)
                    .setParameter(5, month)
                    .setParameter(6, year)
                    .executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void updateFavoriteTypeTrainByOwerAndTypeMap(List<FavoriteTypeDataSetEntity> trainSet) {
        for (FavoriteTypeDataSetEntity favoriteTypeDataSetEntity : trainSet) {
            favoriteTypeDataSetEntity.setIsTrain((byte) 1);
            try {
                update(favoriteTypeDataSetEntity);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<FavoriteTypeDataSetEntity> getFavoriteTypeDataByOwner(int owner) {
        return (List<FavoriteTypeDataSetEntity>) getSession()
                .createQuery("FROM FavoriteTypeDataSetEntity where owner=?  and (rate!=0 or rate!=null) and (isTrain=0 or isTrain=null)")
                .setParameter(0, owner)
                .list();
    }
}
