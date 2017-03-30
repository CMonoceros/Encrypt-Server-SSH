package com.dhu.cst.zjm.base;

/**
 * Created by zjm on 2017/3/8.
 */

import java.lang.reflect.ParameterizedType;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.dhu.cst.zjm.entity.base.PaperBaseEntity;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

@SuppressWarnings("unchecked")
@Transactional
public class BaseDaoSupportImpl<T> implements BaseDaoSupport<T> {

    @Resource
    private SessionFactory sessionFactory;

    private Class<T> clazz;

    public BaseDaoSupportImpl() {
        // 使用反射得到T的真实类型
        // 获取new的对象的泛型的父类类型
        ParameterizedType pt = (ParameterizedType) this.getClass()
                .getGenericSuperclass();
        // 获取第一个类型的真实类型
        this.clazz = (Class<T>) pt.getActualTypeArguments()[0];
        System.out.println("--->clazz" + clazz);
    }

    /**
     * 获取当前Session对象 protected子类中可以得到
     *
     * @return
     */
    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }


    public void save(T entity) {
        getSession().save(entity);
    }


    public void delete(Integer id) {
        Object obj = findById(id);
        if (null != obj) {
            getSession().delete(obj);
        }
    }


    public void update(T entity) {
        getSession().update(entity);
    }


    public T findById(Integer id) {
        if (id == null) {
            return null;
        } else {
            return (T) getSession().get(clazz, id);
        }
    }


    public List<T> findAll() {
        return getSession().createQuery("FROM " + clazz.getSimpleName())// from+类名
                .list();
    }

    @Override
    public PaperBaseEntity findListByPaper(PaperBaseEntity paperBaseEntity, Map<String, Object> pram) {
        String hql = paperBaseEntity.getHql();//获取查询语句
        Query query = getSession().createQuery(hql).setCacheable(true);
        //设置参数
        query.setProperties(pram);
        //查询具体数据
        int count = query.list().size();
        paperBaseEntity.setRowsTotal(count);
        int nowPage = 1;
        if (paperBaseEntity.getPage() > 0) {
            nowPage = paperBaseEntity.getPage();
        }
        //指定从那个对象开始查询，参数的索引位置是从0开始的，
        query.setFirstResult((paperBaseEntity.getPage() - 1) * paperBaseEntity.getRows());
        //分页时，一次最多产寻的对象数
        query.setMaxResults(paperBaseEntity.getRows());
        List<?> list = query.list();
        paperBaseEntity.setList(list);
        return paperBaseEntity;
    }

}