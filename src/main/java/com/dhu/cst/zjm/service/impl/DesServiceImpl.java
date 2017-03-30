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
        EncryptRelationEntity encryptRelationEntity = new EncryptRelationEntity();
        encryptRelationEntity.setId(relationId);
        DesEntity desEntity = new DesEntity();
        desEntity.setDesKey(key);
        desEntity.setLayer(layer);
        desEntity.setEncryptRelationByRelationId(encryptRelationEntity);
        try {
            save(desEntity);
            return findDesByRelationAndLayer(relationId, layer);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
