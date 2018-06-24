package cn.zero.spider.dao;

import java.io.Serializable;
import java.util.List;

/**
 * Base mapper接口
 *
 * @param <T> the type parameter
 * @author 蔡元豪
 * @date 2018 /4/21 16:43
 */
public interface BaseMapper<T> {
    /**
     * 保存
     *
     * @param t the t
     */
    void save(T t);

    /**
     * 更新
     *
     * @param t the t
     */
    void update(T t);

    /**
     * 删除
     *
     * @param t the t
     */
    void delete(T t);

    /**
     * 通过id删除
     *
     * @param id the id
     */
    void deleteByIds(Serializable... id);

    /**
     * 获取所有
     *
     * @return all all
     */
    List<T> getAll();

    /**
     * 通过id获取
     *
     * @param id the id
     * @return by id
     */
    T getById(Serializable id);

    /**
     * 通过条件获取
     *
     * @param t the t
     * @return list list
     */
    List<T> get(T t);
}
