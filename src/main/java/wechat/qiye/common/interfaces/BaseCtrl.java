package wechat.qiye.common.interfaces;

/**
 * 控制器基础接口
 *
 * @author tianslc
 */
public interface BaseCtrl<T> {
    /**
     * 创建
     *
     * @param entity
     * @return
     */
    boolean create(T entity);

    /**
     * 更新
     *
     * @param entity
     * @return
     */
    boolean update(T entity);

    /**
     * 删除
     *
     * @param id
     * @return
     */
    T get(String id);

    /**
     * 获取
     *
     * @param id
     * @return
     */
    boolean delete(String id);
}
