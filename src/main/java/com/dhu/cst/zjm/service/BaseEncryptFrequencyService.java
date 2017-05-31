package com.dhu.cst.zjm.service;

import com.dhu.cst.zjm.base.BaseDaoSupport;
import com.dhu.cst.zjm.entity.EncryptFrequencyEntity;
import com.dhu.cst.zjm.entity.base.EncryptFrequencyBaseEntity;

import java.util.List;

/**
 * Created by zjm on 2017/5/29.
 */
public interface BaseEncryptFrequencyService extends BaseDaoSupport<EncryptFrequencyEntity> {
    EncryptFrequencyBaseEntity saveEncryptFrequency(int owner,int typeID);

    EncryptFrequencyBaseEntity updateEncryptFrequency(int id,int owner,int typeID);

    EncryptFrequencyBaseEntity findEncryptFrequencyByOwnerAndTypeID(int owner,int typeID);

    List<EncryptFrequencyBaseEntity> findEncryptFrequencyByOwner(int owner);

    EncryptFrequencyBaseEntity updateEncryptFrequencySetPriority(int typeID,int owner,int priority);

    List<Integer> findTypeIDByOwnerOrderByPriority(int owner);
}
