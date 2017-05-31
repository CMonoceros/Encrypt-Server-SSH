package com.dhu.cst.zjm.action;

import com.dhu.cst.zjm.base.BaseAction;
import com.dhu.cst.zjm.entity.EncryptTypeEntity;
import com.dhu.cst.zjm.entity.FavoriteTypeDataSetEntity;
import com.dhu.cst.zjm.entity.base.EncryptFrequencyBaseEntity;
import com.dhu.cst.zjm.entity.base.EncryptTypeBaseEntity;
import com.dhu.cst.zjm.util.JsonUtil;
import com.dhu.cst.zjm.util.meachinelearning.FavoriteType;
import org.apache.commons.collections.map.HashedMap;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by zjm on 2017/3/11.
 */
@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class EncryptTypeAction extends BaseAction<EncryptTypeEntity> {
    public String getEncryptType() throws Exception {
        List<EncryptTypeBaseEntity> list = encryptTypeService.findEncryptTypeList();
        if (list != null) {
            JsonUtil.toJson(ServletActionContext.getResponse(), list);
        } else {
            JsonUtil.toJson(ServletActionContext.getResponse(), null);
        }
        return null;
    }

    public String getEncryptTypeByOwner() throws Exception {
        String user = ServletActionContext.getRequest().getParameter("user_id");
        if (user == null) {
            return null;
        }
        int owner = Integer.parseInt(user);
        List<EncryptTypeBaseEntity> list = new ArrayList<>();
        List<Integer> typeList = encryptFrequencyService.findTypeIDByOwnerOrderByPriority(owner);
        if (typeList == null) {
            return null;
        }
        for (Integer i : typeList) {
            list.add(encryptTypeService.findEncryptTypeById(i));
        }
        List<EncryptTypeBaseEntity> otherList = encryptTypeService.findEncryptTypeList();
        if (otherList == null) {
            return null;
        }
        for (EncryptTypeBaseEntity encryptTypeBaseEntity : otherList) {
            if (!list.contains(encryptTypeBaseEntity)) {
                list.add(encryptTypeBaseEntity);
            }
        }
        if (list != null) {
            JsonUtil.toJson(ServletActionContext.getResponse(), list);
        } else {
            JsonUtil.toJson(ServletActionContext.getResponse(), null);
        }
        return null;
    }

    public String setEncryptTypeRate() throws Exception {
        String type = ServletActionContext.getRequest().getParameter("type_id");
        String rate = ServletActionContext.getRequest().getParameter("label_rate");
        String user = ServletActionContext.getRequest().getParameter("user_id");
        if (type == null || rate == null || user == null) {
            return null;
        }
        int typeId = Integer.parseInt(type);
        int label = Integer.parseInt(rate);
        int owner = Integer.parseInt(user);
        EncryptTypeBaseEntity encryptTypeBaseEntity = encryptTypeService.findEncryptTypeById(typeId);
        if (encryptTypeBaseEntity != null) {
            trainFavoriteType(typeId, label, owner);
            List<EncryptTypeBaseEntity> typeList = new ArrayList<>();
            typeList.add(encryptTypeBaseEntity);
            JsonUtil.toJson(ServletActionContext.getResponse(), typeList);
        } else {
            JsonUtil.toJson(ServletActionContext.getResponse(), null);
        }
        return null;
    }

    private void trainFavoriteType(final int typeID, final int labelID, final int userID) {
        final int type = typeID;
        final int label = labelID;
        final int user = userID;
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<EncryptFrequencyBaseEntity> frequencyBaseEntityList = encryptFrequencyService.findEncryptFrequencyByOwner(user);
                if (frequencyBaseEntityList != null) {
                    Map<Integer, List<Double>> freqMap = FavoriteType.prepareFreqMap(frequencyBaseEntityList);
                    Map<Integer, List<Double>> trainMap = new HashedMap();
                    trainMap.put(type, freqMap.get(type));
                    if (favoriteTypeDataSetService.updateFavoriteTypeRateByOwerAndTypeMap(user, trainMap, label)) {
                        List<FavoriteTypeDataSetEntity> trainSet = favoriteTypeDataSetService.getFavoriteTypeDataByOwner(user);
                        if(trainSet!=null){
//                            if (FavoriteType.trainFavoriteTypeListByLabel(trainSet)) {
//                                favoriteTypeDataSetService.updateFavoriteTypeTrainByOwerAndTypeMap(trainSet);
//                                List<Integer> res = FavoriteType.predictFavoriteTypeList(freqMap, user);
//                                if (res != null) {
//                                    int priority = 1;
//                                    for (Integer i : res) {
//                                        encryptFrequencyService.updateEncryptFrequencySetPriority(
//                                                i, user, priority++);
//                                    }
//                                }
//                            }
                        }
                    }
                }
            }
        }).start();
    }
}
