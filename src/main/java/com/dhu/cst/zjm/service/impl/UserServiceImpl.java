package com.dhu.cst.zjm.service.impl;

/**
 * Created by zjm on 2017/3/8.
 */

import com.dhu.cst.zjm.entity.UserEntity;
import com.dhu.cst.zjm.entity.base.UserBaseEntity;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dhu.cst.zjm.base.BaseDaoSupportImpl;
import com.dhu.cst.zjm.service.BaseUserService;

import java.sql.Timestamp;

//开事务
@Transactional
//注入
@Service("userService")
public class UserServiceImpl extends BaseDaoSupportImpl<UserEntity> implements
        BaseUserService {


    public UserBaseEntity findUserByIDAndPwd(int id, String password) {
        // TODO Auto-generated method stub
        String md5Digest = DigestUtils.md5Hex(password);
        return (UserBaseEntity) getSession().createQuery(//
                "FROM UserBaseEntity WHERE id=? AND password=? ")//
                .setParameter(0, id)//
                .setParameter(1, md5Digest)//
                .setCacheable(true)
                .uniqueResult();
    }

    public UserBaseEntity findUserByNameAndPwd(String name, String password) {
        String md5Digest = DigestUtils.md5Hex(password);
        return (UserBaseEntity) getSession().createQuery(//
                "FROM UserBaseEntity WHERE name=? AND password=? ")//
                .setParameter(0, name)//
                .setParameter(1, md5Digest)//
                .setCacheable(true)
                .uniqueResult();
    }

    public UserBaseEntity saveUserByNameAndPwd(String name, String password) {
        String md5Digest = DigestUtils.md5Hex(password);
        UserEntity userEntity = new UserEntity();
        userEntity.setName(name);
        userEntity.setPassword(md5Digest);
        userEntity.setRegisterTime(new Timestamp(System.currentTimeMillis()));
        try {
            save(userEntity);
            return findUserByNameAndPwd(name, password);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public UserBaseEntity findUserByID(int id) {
        return (UserBaseEntity) getSession().createQuery(//
                "FROM UserBaseEntity WHERE id=? ")//
                .setParameter(0, id)//
                .setCacheable(true)
                .uniqueResult();
    }

}