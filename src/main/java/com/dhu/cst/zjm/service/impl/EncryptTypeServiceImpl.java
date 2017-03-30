package com.dhu.cst.zjm.service.impl;

import com.dhu.cst.zjm.base.BaseDaoSupportImpl;
import com.dhu.cst.zjm.entity.EncryptTypeEntity;
import com.dhu.cst.zjm.entity.base.EncryptTypeBaseEntity;
import com.dhu.cst.zjm.service.BaseEncryptTypeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by zjm on 2017/3/11.
 */
@Transactional
@Service("encryptTypeService")
public class EncryptTypeServiceImpl extends BaseDaoSupportImpl<EncryptTypeEntity> implements BaseEncryptTypeService {
    public List<EncryptTypeBaseEntity> findEncryptTypeList() {
        return (List<EncryptTypeBaseEntity>) getSession().createQuery("FROM EncryptTypeBaseEntity")
                .setCacheable(true)
                .list();
    }

    public EncryptTypeBaseEntity findEncryptTypeById(int id) {
        return (EncryptTypeBaseEntity) getSession().createQuery("FROM EncryptTypeBaseEntity where id=?")
                .setParameter(0, id)
                .setCacheable(true)
                .uniqueResult();
    }
}
