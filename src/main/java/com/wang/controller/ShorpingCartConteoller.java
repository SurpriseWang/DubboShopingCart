package com.wang.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wang.service.ItemsService;
import com.wang.service.ShopingCartService;
import com.wang.vo.ItemVo;

/**
 * @author SurpriseWang
 * @date 2019年9月10日下午1:30:32
 */
@Controller
public class ShorpingCartConteoller {
	@Autowired
	private ShopingCartService shopingCartService;
	@Autowired
	private ItemsService itemService;

	@ResponseBody
	@RequestMapping(value = "/queryShopingCart.action", produces = "text/html;charset=UTF-8")
	public String queryShopingCart(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		for (Cookie cookie : cookies) {
			String name = cookie.getName();
			String value = cookie.getValue();
			if (name.equals("userName")) {
				if (value != null) {
					String returnValue = shopingCartService.queryShopingCart(value);
					return returnValue;
				}
			}
		}
		return null;
	}

	@ResponseBody
	@RequestMapping("/insertItemShopingCart.action")
	public String insertInItemShopingCart(HttpServletRequest request, Integer id) {
		ItemVo itemVo = itemService.queryItemsByItemId(id);
		String callback = request.getParameter("callback");
		Cookie[] cookies = request.getCookies();
		for (Cookie cookie : cookies) {
			String name = cookie.getName();
			String value = cookie.getValue();
			if (name.equals("userName")) {
				if (value != null) {
					return shopingCartService.insertItemInShopingCart(callback, value, id, itemVo);
				}
			}
		}
		return null;
	}

	@RequestMapping("/deleteItemInShopingCart.action")
	public String deleteItemInShopingCart(HttpServletRequest request,Integer[] itemId) {
		Cookie[] cookies = request.getCookies();
		for (Cookie cookie : cookies) {
			String name = cookie.getName();
			String value = cookie.getValue();
			if (name.equals("userName")) {
				if (value != null) {
					return shopingCartService.deleteItemInShopingCart(value,itemId);
				}
			}
		}
		return null;
	}

	@RequestMapping("/buyChosenItemInShopingCart.action")
	public String buyChosenItemInShopingCart(HttpServletRequest request,Integer[] itemId) {
		Cookie[] cookies = request.getCookies();
		for (Cookie cookie : cookies) {
			String name = cookie.getName();
			String value = cookie.getValue();
			if (name.equals("userName")) {
				if (value != null) {
					Integer monery = shopingCartService.buyChosenItemInShopingCart(value,itemId);
					return "redirect:http://localhost:8083/DubboShopingCart/view/html/paymonery.html?"+"monery="+monery+"";
				}
			}
		}
		return null;
	}
}
