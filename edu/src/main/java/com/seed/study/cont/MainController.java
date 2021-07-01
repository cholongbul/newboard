package com.seed.study.cont;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.seed.study.serv.MainService;
import com.seed.study.vo.InsertUser;
import com.seed.study.vo.MainVo;

/**
 * Handles requests for the application home page.
 */
@Controller
public class MainController {

	private static final Logger logger = LoggerFactory.getLogger(MainController.class);

	@Autowired
	private MainService mainService;

	@RequestMapping(value = "selectList.do", method = RequestMethod.GET)
	@ResponseBody
	public String selectList(HttpServletResponse response, HttpServletRequest request) throws Exception {
		logger.info("[리스트 불러오기]");

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");
		ArrayList<MainVo> userList = mainService.selectList();
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("userList", userList);
		return new Gson().toJson(resultMap);
	}

	@RequestMapping(value = "insertUser.do", method = RequestMethod.POST)
	@ResponseBody
	public String insertUser(HttpServletResponse response, HttpServletRequest request, InsertUser iu) throws Exception {
		logger.info("[유저 추가]");

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");

		Map<String, Object> resultMap = new HashMap<String, Object>();
		int result1 = 0;
		int result2 = 0;

		result1 = mainService.insertUser1(iu);

		if (result1 > 0) {
			result2 = mainService.insertUser2(iu);
			if (result2 > 0) {
				logger.info("[INSERT성공]");
				resultMap.put("status", "success");
			} else {
				logger.info("[INSERT실패");
				resultMap.put("status", "fail");
			}
		} else {
			logger.info("[INSERT실패[");
			resultMap.put("status", "fail");
		}
		return new Gson().toJson(resultMap);
	}

	@RequestMapping(value = "deleteUser.do", method = RequestMethod.POST)
	@ResponseBody
	public String deleteUser(HttpServletResponse response, HttpServletRequest request, @RequestBody ArrayList<Integer> data) throws Exception {
		logger.info("[유저 삭제]");

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");

		int result1 = 0;
		int result2 = 0;
		Map<String, Object> resultMap = new HashMap<String, Object>();
		for (int userNum : data) {

			result1 = mainService.deleteUser1(userNum);
			if (result1 > 0) {
				result2 = mainService.deleteUser2(userNum);
				if (result2 > 0) {
					logger.info("[" + userNum + "번 유저 DELETE 성공]");
					resultMap.put("status", "success");
				} else {
					logger.info("[" + userNum + "번 유저 DELETE 실패]");
					resultMap.put("status", "fail");
				}
			} else {
				logger.info("[" + userNum + " 실패]");
				resultMap.put("status", "fail");
			}

		}

		return new Gson().toJson(resultMap);
	}
}