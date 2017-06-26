package com.hishop.protal.service;

import com.hishop.pojo.TbUser;

public interface UserService {	
	TbUser getUserByToken(String token);
}
