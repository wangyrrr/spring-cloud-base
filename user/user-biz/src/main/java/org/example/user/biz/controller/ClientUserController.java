package org.example.user.biz.controller;

import org.example.user.biz.entity.ClientUser;
import org.example.user.biz.service.ClientUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户接口
 * @Author: WangYuanrong
 * @Date: 2022/12/29 16:33
 */
@RestController
@RequestMapping("/clientUser")
public class ClientUserController {

    @Autowired
    private ClientUserService clientUserService;

    /**
     * 根据id查询
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ClientUser findById(@PathVariable Long id) {
        return clientUserService.getById(id);
    }

}
