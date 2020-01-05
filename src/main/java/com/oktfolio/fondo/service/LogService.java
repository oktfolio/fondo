package com.oktfolio.fondo.service;

import com.github.pagehelper.PageInfo;
import com.oktfolio.fondo.model.Log;

/**
 * @author oktfolio oktfolio@gmail.com
 * @date 2020/01/06
 */
public interface LogService {
    PageInfo<Log> findByCondition(int pageNum, int pageSize, Integer type, String key);
}
