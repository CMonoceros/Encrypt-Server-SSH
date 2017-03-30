package com.dhu.cst.zjm.base;

/**
 * Created by zjm on 2017/3/8.
 */
import com.dhu.cst.zjm.entity.base.PaperBaseEntity;

import java.util.List;
import java.util.Map;

public interface BaseDaoSupport<T> {
    /**
     * 保存一个对象
     * @param entity
     */
    void save(T entity);

    /**
     * 按id删除一个对象
     * @param id
     */
    void delete(Integer id);

    /**
     * 更新一个对象
     * @param entity
     */
    void update(T entity);

    /**
     * 按id查询一个对象
     * @param id
     * @return
     */
    T findById(Integer id);

    /**
     * 查询所有
     * @return
     */
    List<T> findAll();


    PaperBaseEntity findListByPaper(PaperBaseEntity paperBaseEntity,Map<String,Object> pram);

}
