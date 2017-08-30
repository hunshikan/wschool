package com.heping.service.system.wslog.impl;

import com.heping.dao.DaoSupport;
import com.heping.service.system.wslog.WSlogManager;
import com.heping.util.Page;
import com.heping.util.PageData;
import com.heping.util.Tools;
import com.heping.util.UuidUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 * 〈操作日志〉
 *
 * @author liupengtao
 * @create 2017/8/25
 * @since 1.0.0
 */
@Service("wslogService")
public class WSlogService implements WSlogManager{
    @Resource(name = "daoSupport")
    private DaoSupport dao;

    /**新增
     * //@param pd
     * @throws Exception
     */
    public void save(String USERNAME, String CONTENT)throws Exception{
        PageData pd = new PageData();
        pd.put("USERNAME", USERNAME);					//用户名
        pd.put("CONTENT", CONTENT);						//事件
        pd.put("WSLOG_ID", UuidUtil.get32UUID());		//主键
        pd.put("CZTIME", Tools.date2Str(new Date()));	//操作时间
        dao.save("WSlogMapper.save", pd);
    }

    /**删除
     * @param pd
     * @throws Exception
     */
    public void delete(PageData pd)throws Exception{
        dao.delete("WSlogMapper.delete", pd);
    }

    /**列表
     * @param page
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public List<PageData> list(Page page)throws Exception{
        return (List<PageData>)dao.findForList("WSlogMapper.datalistPage", page);
    }

    /**列表(全部)
     * @param pd
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public List<PageData> listAll(PageData pd)throws Exception{
        return (List<PageData>)dao.findForList("WSlogMapper.listAll", pd);
    }

    /**通过id获取数据
     * @param pd
     * @throws Exception
     */
    public PageData findById(PageData pd)throws Exception{
        return (PageData)dao.findForObject("WSlogMapper.findById", pd);
    }

    /**批量删除
     * @param ArrayDATA_IDS
     * @throws Exception
     */
    public void deleteAll(String[] ArrayDATA_IDS)throws Exception{
        dao.delete("WSlogMapper.deleteAll", ArrayDATA_IDS);
    }
}
