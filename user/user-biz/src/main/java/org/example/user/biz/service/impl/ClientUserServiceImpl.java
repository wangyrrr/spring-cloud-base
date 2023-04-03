package org.example.user.biz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.user.biz.entity.ClientUser;
import org.example.user.biz.service.ClientUserService;
import org.example.user.biz.mapper.ClientUserMapper;
import org.springframework.stereotype.Service;

/**
* @author wangyuanrong
* @description 针对表【client_user(客户端用户表)】的数据库操作Service实现
* @createDate 2023-03-31 14:33:19
*/
@Service
public class ClientUserServiceImpl extends ServiceImpl<ClientUserMapper, ClientUser>
    implements ClientUserService{

}




