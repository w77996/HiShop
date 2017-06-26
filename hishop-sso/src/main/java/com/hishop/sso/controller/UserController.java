package com.hishop.sso.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hishop.common.util.ExceptionUtil;
import com.hishop.common.util.HishopResult;
import com.hishop.pojo.TbUser;
import com.hishop.sso.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;

	@RequestMapping("/check/{param}/{type}")
	@ResponseBody
	public Object checkData(@PathVariable String param, @PathVariable Integer type, String callback) {
		HishopResult result = null;
		if (StringUtils.isBlank(param)) {
			result = HishopResult.build(400, "校验内容为空");
		}
		if (type == null) {
			result = HishopResult.build(400, "校验类型为空");
		}
		if (type != 1 && type != 2 && type != 3) {
			result = HishopResult.build(400, "校验类型为空");
		}
		// 校验出错
		if (null != result) {
			if (null != callback) {
				MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(result);
				mappingJacksonValue.setJsonpFunction(callback);
				return mappingJacksonValue;
			} else {
				return result;
			}
		}
		// 调用服务
		try {
			result = userService.checkData(param, type);

		} catch (Exception e) {
			result = HishopResult.build(500, ExceptionUtil.getStackTrace(e));
		}

		if (null != callback) {
			MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(result);
			mappingJacksonValue.setJsonpFunction(callback);
			return mappingJacksonValue;
		} else {
			return result;
		}

	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	@ResponseBody
	public HishopResult createUser(TbUser tbUser) {
		try {
			HishopResult result = userService.createUser(tbUser);
			return result;
		} catch (Exception e) {
			// TODO: handle exception
			return HishopResult.build(500, ExceptionUtil.getStackTrace(e));
		}
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public HishopResult userLogin(String username, String password,HttpServletRequest request,HttpServletResponse response) {
		try {
			HishopResult result = userService.userLogin(username, password,request,response);
			return result;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return HishopResult.build(500, ExceptionUtil.getStackTrace(e));
		}
	}

	@RequestMapping("/token/{token}")
	@ResponseBody
	public Object getUserByToken(@PathVariable String token, String callback) {
		HishopResult result = null;
		try {
			result = userService.getUserByToken(token);
		} catch (Exception e) {
			e.printStackTrace();
			result = HishopResult.build(500, ExceptionUtil.getStackTrace(e));
		}
		// 判断是否为jsonp调用
		if (StringUtils.isBlank(callback)) {
			return result;
		} else {
			MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(result);
			mappingJacksonValue.setJsonpFunction(callback);
			return mappingJacksonValue;
		}

	}

}
