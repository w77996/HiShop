package com.hishop.sso.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hishop.common.util.HishopResult;
import com.hishop.pojo.TbUser;

public interface UserService {
	HishopResult checkData(String content,int type);
	HishopResult createUser(TbUser tbUser);
	HishopResult userLogin(String username,String password,HttpServletRequest request,HttpServletResponse response);
	HishopResult getUserByToken(String token);
}
