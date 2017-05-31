package com.dhu.cst.zjm.base;

/**
 * Created by zjm on 2017/3/9.
 */

import com.dhu.cst.zjm.service.*;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import javax.annotation.Resource;
import java.lang.reflect.ParameterizedType;

/**
 * Created by zjm on 2017/3/8.
 */
@SuppressWarnings("serial")
public abstract class BaseAction<T> extends ActionSupport implements
        ModelDriven<T> {

    // =============== ModelDriven的支持 ==================
    // 作用：把实体类当成页面数据的收集对象

    protected T model;

    @SuppressWarnings("unchecked")
    public BaseAction() {
        try {
            // 通过反射获取model的真实类型
            ParameterizedType pt = (ParameterizedType) this.getClass()
                    .getGenericSuperclass();
            Class<T> clazz = (Class<T>) pt.getActualTypeArguments()[0];
            // 通过反射创建model的实例
            model = clazz.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public T getModel() {
        return model;
    }

    // =============== Service实例的声明 ==================
    @Resource
    protected BaseUserService userService;
    @Resource
    protected BaseFileService fileService;
    @Resource
    protected BaseEncryptTypeService encryptTypeService;
    @Resource
    protected BaseEncryptRelationService encryptRelationService;
    @Resource
    protected BaseSessionService sessionService;
    @Resource
    protected BaseRsaService rsaService;
    @Resource
    protected BaseDesService desService;
    @Resource
    protected BaseMd5Service md5Service;
    @Resource
    protected BaseAesService aesService;
    @Resource
    protected BaseEncryptFrequencyService encryptFrequencyService;
    @Resource
    protected BaseFavoriteTypeDataSetService favoriteTypeDataSetService;

}
