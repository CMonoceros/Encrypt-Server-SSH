package com.dhu.cst.zjm.service;

import com.dhu.cst.zjm.base.BaseDaoSupport;
import com.dhu.cst.zjm.entity.EncryptRelationEntity;
import com.dhu.cst.zjm.entity.base.EncryptRelationBaseEntity;

/**
 * Created by zjm on 2017/3/13.
 */
public interface BaseEncryptRelationService extends BaseDaoSupport<EncryptRelationEntity> {
    EncryptRelationBaseEntity findIDByFileAndType(int fileID, int typeID);

    EncryptRelationBaseEntity saveEncryptRelation(int fileID, int typeID);

    EncryptRelationBaseEntity updateEncryptRelation(int id,int fileID, int typeID);
}
