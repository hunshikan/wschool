package com.heping.service.system.wslog;

import com.heping.util.Page;
import com.heping.util.PageData;

import java.util.List;


/**
 * 〈一句话功能简述〉<br>
 * 〈系统操作日志〉
 *
 * @author liupengtao
 * @create 2017/8/25
 * @since 1.0.0
 */
public interface WSlogManager {

    /**新增
     * //@param pd
     * @throws Exception
     */
    public void save(String USERNAME, String CONTENT)throws Exception;

    /**删除
     * @param pd
     * @throws Exception
     */
    public void delete(PageData pd)throws Exception;

    /**列表
     * @param page
     * @throws Exception
     */
    public List<PageData> list(Page page)throws Exception;

    /**列表(全部)
     * @param pd
     * @throws Exception
     */
    public List<PageData> listAll(PageData pd)throws Exception;

    /**通过id获取数据
     * @param pd
     * @throws Exception
     */
    public PageData findById(PageData pd)throws Exception;

    /**批量删除
     * @param ArrayDATA_IDS
     * @throws Exception
     */
    public void deleteAll(String[] ArrayDATA_IDS)throws Exception;
}
