package cn.zero.spider.service.impl;

import cn.zero.spider.dao.BaseMapper;
import cn.zero.spider.service.IBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * 服务实现泛型类
 *
 * @author 蔡元豪
 * @date 2018/5/28 11:20
 */
public abstract class BaseServiceImpl<T> implements IBaseService<T> {


    @Autowired
    private BaseMapper<T> baseMapper;

    /**
     * 保存
     *
     * @param t the t
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(T t) {
        baseMapper.save(t);
    }

    /**
     * 更新
     *
     * @param t the t
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(T t) {
        baseMapper.update(t);
    }

    /**
     * 删除
     *
     * @param t the t
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(T t) {
        baseMapper.delete(t);
    }

    /**
     * 通过id批量删除
     *
     * @param ids the ids
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteByIds(Serializable... ids) {
        baseMapper.deleteByIds(ids);
    }

    /**
     * 获取所有
     *
     * @return all
     */
    @Override
    public List<T> getAll() {
        return baseMapper.getAll();
    }

    /**
     * 通过id获取
     *
     * @param id the id
     * @return by id
     */
    @Override
    public T getById(Serializable id) {
        return baseMapper.getById(id);
    }

    /**
     * 通过条件获取
     *
     * @param t the t
     * @return list
     */
    @Override
    public List<T> get(T t) {
        return baseMapper.get(t);
    }
}
