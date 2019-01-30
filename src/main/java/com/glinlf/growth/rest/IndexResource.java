package com.glinlf.growth.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author created by glinlf
 * @date 2019/1/30
 * @remark
 */
@Controller
public class IndexResource {

    private final static Logger LOG = LoggerFactory.getLogger(IndexResource.class);


    @GetMapping("/index")
    public ResponseEntity<String> index() {
        LOG.info("http.ok 200!");
        return ResponseEntity.ok().build();
    }

}
