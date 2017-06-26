package com.hishop.protal.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.hishop.common.util.HishopResult;
import com.hishop.common.util.HttpClientUtil;
import com.hishop.pojo.TbUser;
import com.hishop.protal.service.UserService;
@Service
public class UserServiceImpl implements UserService {
	@Value("${SSO_BASE_URL}")
	public String SSO_BASE_URl;
	@Value("${SSO_USER_TOKEN}")
	public String SSO_USER_TOKEN;
	@Value("${SSO_PAGE_LOGIN}")
	public String SSO_PAGE_LOGIN;
	@Override
	public TbUser getUserByToken(String token) {
		// TODO Auto-generated method stub
		try {
			String json = HttpClientUtil.doGet(SSO_BASE_URl+SSO_USER_TOKEN+token);
			HishopResult result = HishopResult.formatToPojo(json, TbUser.class);
			if(result.getStatus()==200){
				TbUser user = (TbUser) result.getData();
				return user;
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

}
