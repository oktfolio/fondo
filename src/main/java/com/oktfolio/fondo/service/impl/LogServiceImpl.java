package com.oktfolio.fondo.service.impl;

import com.github.pagehelper.PageInfo;
import com.oktfolio.fondo.dao.LogMapper;
import com.oktfolio.fondo.model.Log;
import com.oktfolio.fondo.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @author oktfolio oktfolio@gmail.com
 * @date 2020/01/06
 */
@Service
public class LogServiceImpl implements LogService {

    @Autowired
    private LogMapper logMapper;

    /**
     * 分页搜索获取日志
     *
     * @param type
     * @param key
     * @return
     */
    @Override
    public PageInfo<Log> findByCondition(int pageNum, int pageSize, Integer type, String key) {
        return PageInfo.of(null);
    }
}
