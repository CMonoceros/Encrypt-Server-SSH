package com.dhu.cst.zjm.service.impl;

import com.dhu.cst.zjm.base.BaseDaoSupportImpl;
import com.dhu.cst.zjm.entity.DesEntity;
import com.dhu.cst.zjm.entity.EncryptRelationEntity;
import com.dhu.cst.zjm.entity.base.DesBaseEntity;
import com.dhu.cst.zjm.service.BaseDesService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by zjm on 2017/3/21.
 */
@Transactional
@Service("desService")
public class DesServiceImpl extends BaseDaoSupportImpl<DesEntity> implements BaseDesService {

    @Override
    public DesBaseEntity findDesByRelationAndLayer(int relationId, int layer) {
        return (DesBaseEntity) getSession().createQuery("from DesBaseEntity where relationId=? and layer=?")
                .setParameter(0, relationId)
                .setParameter(1, layer)
                .uniqueResult();
    }

    @Override
    public DesBaseEntity saveDesByRelationAndKeyAndLayer(int relationId, int layer, String key) {
        DesBaseEntity desBaseEntity = findDesByRelationAndLayer(relationId, layer);
        if (desBaseEntity == null) {
            EncryptRelationEntity encryptRelationEntity = new EncryptRelationEntity();
            encryptRelationEntity.setId(relationId);
            DesEntity desEntity = new DesEntity();
            desEntity.setDesKey(key);
            desEntity.setLayer(layer);
            desEntity.setEncryptRelationByRelationId(encryptRelationEntity);
            try {
                save(desEntity);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
            return findDesByRelationAndLayer(relationId, layer);
        } else {
            desBaseEntity = updateDesByRelationAndKeyAndLayer(desBaseEntity.getId(), relationId, layer, key);
            return desBaseEntity;
        }

    }

    @Override
    public DesBaseEntity updateDesByRelationAndKeyAndLayer(int id, int relationId, int layer, String key) {
        DesBaseEntity desBaseEntity = findDesByRelationAndLayer(relationId, layer);
        if (desBaseEntity == null) {
            desBaseEntity = saveDesByRelationAndKeyAndLayer(relationId, layer, key);
            return desBaseEntity;
        } else {
            EncryptRelationEntity encryptRelationEntity = new EncryptRelationEntity();
            encryptRelationEntity.setId(relationId);
            DesEntity desEntity = new DesEntity();
            desEntity.setId(id);
            desEntity.setDesKey(key);
            desEntity.setLayer(layer);
            desEntity.setEncryptRelationByRelationId(encryptRelationEntity);
            try {
                update(desEntity);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
            return findDesByRelationAndLayer(relationId, layer);
        }
    }
}
