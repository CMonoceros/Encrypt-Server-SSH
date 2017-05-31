package com.dhu.cst.zjm.service.impl;

import com.dhu.cst.zjm.base.BaseDaoSupportImpl;
import com.dhu.cst.zjm.entity.EncryptRelationEntity;
import com.dhu.cst.zjm.entity.EncryptTypeEntity;
import com.dhu.cst.zjm.entity.FileEntity;
import com.dhu.cst.zjm.entity.base.EncryptRelationBaseEntity;
import com.dhu.cst.zjm.service.BaseEncryptRelationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;

/**
 * Created by zjm on 2017/3/13.
 */
@Transactional
@Service("encryptRelationService")
public class EncryptRelationServiceImpl extends BaseDaoSupportImpl<EncryptRelationEntity> implements BaseEncryptRelationService {
    public EncryptRelationBaseEntity findIDByFileAndType(int fileID, int typeID) {
        return (EncryptRelationBaseEntity) getSession().createQuery("FROM EncryptRelationBaseEntity where fileId=? and typeId=?")
                .setParameter(0, fileID)
                .setParameter(1, typeID)
                .uniqueResult();
    }


    public EncryptRelationBaseEntity saveEncryptRelation(int fileID, int typeID) {
        EncryptRelationBaseEntity encryptRelationBaseEntity = findIDByFileAndType(fileID, typeID);
        if (encryptRelationBaseEntity == null) {
            EncryptRelationEntity encryptRelationEntity = new EncryptRelationEntity();
            FileEntity fileEntity = new FileEntity();
            fileEntity.setId(fileID);
            encryptRelationEntity.setFileByFileId(fileEntity);
            EncryptTypeEntity encryptTypeEntity = new EncryptTypeEntity();
            encryptTypeEntity.setId(typeID);
            encryptRelationEntity.setEncryptTypeByTypeId(encryptTypeEntity);
            encryptRelationEntity.setEncryptTime(new Timestamp(System.currentTimeMillis()));
            try {
                save(encryptRelationEntity);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
            return findIDByFileAndType(fileID, typeID);
        } else {
            encryptRelationBaseEntity = updateEncryptRelation(encryptRelationBaseEntity.getId(), fileID, typeID);
            return encryptRelationBaseEntity;
        }
    }

    @Override
    public EncryptRelationBaseEntity updateEncryptRelation(int id, int fileID, int typeID) {
        EncryptRelationBaseEntity encryptRelationBaseEntity = findIDByFileAndType(fileID, typeID);
        if (encryptRelationBaseEntity == null) {
            encryptRelationBaseEntity = saveEncryptRelation(fileID, typeID);
            return encryptRelationBaseEntity;
        } else {
            EncryptRelationEntity encryptRelationEntity = new EncryptRelationEntity();
            FileEntity fileEntity = new FileEntity();
            fileEntity.setId(fileID);
            encryptRelationEntity.setFileByFileId(fileEntity);
            EncryptTypeEntity encryptTypeEntity = new EncryptTypeEntity();
            encryptTypeEntity.setId(typeID);
            encryptRelationEntity.setEncryptTypeByTypeId(encryptTypeEntity);
            encryptRelationEntity.setId(id);
            encryptRelationEntity.setEncryptTime(new Timestamp(System.currentTimeMillis()));
            try {
                update(encryptRelationEntity);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
            return findIDByFileAndType(fileID, typeID);
        }
    }


}
