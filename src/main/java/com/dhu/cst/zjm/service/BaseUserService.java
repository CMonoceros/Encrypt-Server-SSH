package com.dhu.cst.zjm.service;

/**
 * Created by zjm on 2017/3/8.
 */
import com.dhu.cst.zjm.base.BaseDaoSupport;
import com.dhu.cst.zjm.entity.UserEntity;
import com.dhu.cst.zjm.entity.base.UserBaseEntity;

public interface BaseUserService extends BaseDaoSupport<UserEntity>{

    UserBaseEntity findUserByIDAndPwd(int id, String password);

    UserBaseEntity findUserByNameAndPwd(String name,String password);

    UserBaseEntity saveUserByNameAndPwd(String name,String password);

    UserBaseEntity findUserByID(int id);
}
