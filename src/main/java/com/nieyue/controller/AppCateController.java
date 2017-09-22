package com.nieyue.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.nieyue.bean.AppCate;
import com.nieyue.service.AppCateService;
import com.nieyue.util.ResultUtil;
import com.nieyue.util.StateResult;
import com.nieyue.util.StateResultList;


/**
 * 应用类型控制类
 * @author yy
 *
 */
@RestController
@RequestMapping("/appCate")
public class AppCateController {
	@Resource
	private AppCateService appCateService;
	
	/**
	 * 应用类型分页浏览
	 * @param orderName 应用排序数据库字段
	 * @param orderWay 应用排序方法 asc升序 desc降序
	 * @return
	 */
	@RequestMapping(value = "/list", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList browsePagingAppCate(
			@RequestParam(value="pageNum",defaultValue="1",required=false)int pageNum,
			@RequestParam(value="pageSize",defaultValue="10",required=false) int pageSize,
			@RequestParam(value="orderName",required=false,defaultValue="app_cate_id") String orderName,
			@RequestParam(value="orderWay",required=false,defaultValue="desc") String orderWay)  {
			List<AppCate> list = new ArrayList<AppCate>();
			list= appCateService.browsePagingAppCate(pageNum, pageSize, orderName, orderWay);
			if(list.size()>0){
				return ResultUtil.getSlefSRSuccessList(list);
			}else{
				return ResultUtil.getSlefSRFailList(list);
			}
	}
	/**
	 * 应用类型修改
	 * @return
	 */
	@RequestMapping(value = "/update", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult updateAppCate(@ModelAttribute AppCate appCate,HttpSession session)  {
		boolean um = appCateService.updateAppCate(appCate);
		return ResultUtil.getSR(um);
	}
	/**
	 * 应用类型增加
	 * @return 
	 */
	@RequestMapping(value = "/add", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult addAppCate(@ModelAttribute AppCate appCate, HttpSession session) {
		boolean am = appCateService.addAppCate(appCate);
		return ResultUtil.getSR(am);
	}
	/**
	 * 应用类型删除
	 * @return
	 */
	@RequestMapping(value = "/delete", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult delAppCate(@RequestParam("appCateId") Integer appCateId,HttpSession session)  {
		boolean dm =appCateService.delAppCate(appCateId);
		return ResultUtil.getSR(dm);
	}
	/**
	 * 应用类型浏览数量
	 * @return
	 */
	@RequestMapping(value = "/count", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody int countAll(HttpSession session)  {
		int count = appCateService.countAll();
		return count;
	}
	/**
	 * 应用类型单个加载
	 * @return
	 */
	@RequestMapping(value = "/{appCateId}", method = {RequestMethod.GET,RequestMethod.POST})
	public  StateResultList loadAppCate(@PathVariable("appCateId") Integer appCateId,HttpSession session)  {
		List<AppCate> list = new ArrayList<AppCate>();
		AppCate appCate = appCateService.loadAppCate(appCateId);
			if(appCate!=null &&!appCate.equals("")){
				list.add(appCate);
				return ResultUtil.getSlefSRSuccessList(list);
			}else{
				return ResultUtil.getSlefSRFailList(list);
			}
	}
	
}
