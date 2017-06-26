package com.hishop.sso.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.hishop.common.util.CookieUtils;
import com.hishop.common.util.HishopResult;
import com.hishop.common.util.JsonUtils;
import com.hishop.mapper.TbUserMapper;
import com.hishop.pojo.TbUser;
import com.hishop.pojo.TbUserExample;
import com.hishop.pojo.TbUserExample.Criteria;
import com.hishop.sso.dao.JedisClient;
import com.hishop.sso.service.UserService;

@Service
public class UerServiceImpl implements UserService {
	@Autowired
	private TbUserMapper tbUserMapper;
	@Autowired
	private JedisClient jedisClient;
	@Value("${REDIS_USER_SESSION_KEY}")
	private String REDIS_USER_SESSION_KEY;
	@Value("${SSO_SESSION_EXPIRE}")
	private Integer SSO_SESSION_EXPIRE;
	@Override
	public HishopResult checkData(String content, int type) {
		// TODO Auto-generated method stub
		TbUserExample example = new TbUserExample();
		Criteria criteria = example.createCriteria();
		if(1==type){
			criteria.andUsernameEqualTo(content);
		}else if(2==type){
			criteria.andPhoneEqualTo(content);
		}else if(3==type){
			criteria.andEmailEqualTo(content);
		}
		List<TbUser> list = tbUserMapper.selectByExample(example);
		if(list == null|| list.size()==0){
			return HishopResult.ok(true);
		}
		return HishopResult.ok(false);
	}
	@Override
	public HishopResult createUser(TbUser tbUser) {
		// TODO Auto-generated method stub
		tbUser.setUpdated(new Date());
		tbUser.setCreated(new Date());
		tbUser.setPassword(DigestUtils.md5DigestAsHex(tbUser.getPassword().getBytes()));
		tbUserMapper.insert(tbUser);
		return HishopResult.ok();
	}
	@Override
	public HishopResult userLogin(String username, String password,HttpServletRequest request,HttpServletResponse response) {
		// TODO Auto-generated method stub
		TbUserExample example = new TbUserExample();
		Criteria criteria = example.createCriteria();
		criteria.andUsernameEqualTo(username);
		List<TbUser> list = tbUserMapper.selectByExample(example);
		if(null == list || list.size()==0){
			return HishopResult.build(400, "用户或密码错误");
		}
		TbUser user = list.get(0);
		if(DigestUtils.md5Digest(password.getBytes()).equals(user.getPassword())){
			return HishopResult.build(400, "用户或密码错误");
		}
		String token = UUID.randomUUID().toString();
		user.setPassword(null);
		jedisClient.set(REDIS_USER_SESSION_KEY+":"+token, JsonUtils.objectToJson(user));
		jedisClient.expire(SSO_SESSION_EXPIRE+":"+token, SSO_SESSION_EXPIRE);
		
		CookieUtils.setCookie(request, response, "TT_TOKEN", token);
		return HishopResult.ok(token);
	}
	@Override
	public HishopResult getUserByToken(String token) {
		//根据token从redis中查询用户信息
				String json = jedisClient.get(REDIS_USER_SESSION_KEY + ":" + token);
				//判断是否为空
				
				if (StringUtils.isBlank(json)) {
					return HishopResult.build(400, "此session已经过期，请重新登录");
				}
				//更新过期时间
				jedisClient.expire(REDIS_USER_SESSION_KEY + ":" + token, SSO_SESSION_EXPIRE);
				//返回用户信息
				return HishopResult.ok(JsonUtils.jsonToPojo(json, TbUser.class));
			}


}
