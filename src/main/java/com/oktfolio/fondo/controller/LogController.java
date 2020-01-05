package com.oktfolio.fondo.controller;

import com.oktfolio.fondo.service.LogService;
import com.oktfolio.fondo.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author oktfolio oktfolio@gmail.com
 * @date 2020/01/06
 */
@RestController
public class LogController {

    @Autowired
    private LogService logService;


}
