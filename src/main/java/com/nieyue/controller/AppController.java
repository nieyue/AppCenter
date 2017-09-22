package com.nieyue.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.nieyue.bean.App;
import com.nieyue.bean.DataRabbitmqDTO;
import com.nieyue.comments.IPCountUtil;
import com.nieyue.rabbitmq.confirmcallback.Sender;
import com.nieyue.service.AppService;
import com.nieyue.util.ResultUtil;
import com.nieyue.util.StateResult;
import com.nieyue.util.StateResultList;


/**
 * 应用控制类
 * @author yy
 *
 */
@RestController
@RequestMapping("/app")
public class AppController {
	@Resource
	private AppService appService;
	@Resource
	private Sender sender;
	
	/**
	 * web阅读文章获取 根据AppId、acountId、uv来统计数据
	 * @return
	 */
	@RequestMapping(value = "/webRead", method = {RequestMethod.GET,RequestMethod.POST})
	public StateResult webReadApp(
			@RequestParam(value="appId") Integer appId,
			@RequestParam(value="acountId") Integer acountId,
			@RequestParam(value="uv",defaultValue="0",required=false) Integer uv,
			HttpSession session,HttpServletRequest request)  {
		if(uv!=0 &&uv!=1){
			return ResultUtil.getFail();
		}
		DataRabbitmqDTO drd=new DataRabbitmqDTO();
		drd.setAcountId(acountId);//转发    10积分（获得3个有效阅读）
		drd.setAppId(appId);
		drd.setUv(uv);
		drd.setIp(IPCountUtil.getIpAddr(request));//请求的ip地址
		sender.sendAppWebRead(drd);
		return ResultUtil.getSuccess();
	}
	/**
	 * 应用跳转/下载
	 * @return
	 */
	@RequestMapping(value = "/redirectUrl", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult redirectApp(
			@RequestParam(value="appId") Integer appId,
			HttpSession session)  {
		App app = appService.loadApp(appId);
		boolean b=false;
		if(app!=null&&!app.equals("")){
			app.setDownloadNumber(app.getDownloadNumber()+1);
			 b = appService.updateApp(app);
		}
		return ResultUtil.getSR(b);
	}
	/**
	 * 应用分页浏览
	 * @param orderName 应用排序数据库字段
	 * @param orderWay 应用排序方法 asc升序 desc降序
	 * @return
	 */
	@RequestMapping(value = "/list", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList browsePagingApp(
			@RequestParam(value="type",required=false)Integer type,
			@RequestParam(value="capacity",required=false)Double capacity,
			@RequestParam(value="readingNumber",required=false)Integer readingNumber,
			@RequestParam(value="downloadNumber",required=false)Integer downloadNumber,
			@RequestParam(value="appCateId",required=false)Integer appCateId,
			@RequestParam(value="createDate",required=false)Date createDate,
			@RequestParam(value="updateDate",required=false)Date updateDate,
			@RequestParam(value="status",required=false)Integer status,
			@RequestParam(value="pageNum",defaultValue="1",required=false)int pageNum,
			@RequestParam(value="pageSize",defaultValue="10",required=false) int pageSize,
			@RequestParam(value="orderName",required=false,defaultValue="app_id") String orderName,
			@RequestParam(value="orderWay",required=false,defaultValue="desc") String orderWay)  {
			List<App> list = new ArrayList<App>();
			list= appService.browsePagingApp(type,capacity,readingNumber,downloadNumber,appCateId,createDate,updateDate,status,pageNum, pageSize, orderName, orderWay);
			if(list.size()>0){
				return ResultUtil.getSlefSRSuccessList(list);
			}else{
				return ResultUtil.getSlefSRFailList(list);
			}
	}
	/**
	 * 应用修改
	 * @return
	 */
	@RequestMapping(value = "/update", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult updateApp(@RequestBody App app,HttpSession session)  {
		boolean um = appService.updateApp(app);
		return ResultUtil.getSR(um);
	}
	/**
	 * 应用增加
	 * @return 
	 */
	@RequestMapping(value = "/add", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult addApp(@RequestBody App app, HttpSession session) {
		boolean am = appService.addApp(app);
		return ResultUtil.getSR(am);
	}
	/**
	 * 应用删除
	 * @return
	 */
	@RequestMapping(value = "/delete", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult delApp(@RequestParam("appId") Integer appId,HttpSession session)  {
		boolean dm = appService.delApp(appId);
		return ResultUtil.getSR(dm);
	}
	/**
	 * 应用浏览数量
	 * @return
	 */
	@RequestMapping(value = "/count", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody int countAll(
			@RequestParam(value="type",required=false)Integer type,
			@RequestParam(value="capacity",required=false)Double capacity,
			@RequestParam(value="readingNumber",required=false)Integer readingNumber,
			@RequestParam(value="downloadNumber",required=false)Integer downloadNumber,
			@RequestParam(value="appCateId",required=false)Integer appCateId,
			@RequestParam(value="createDate",required=false)Date createDate,
			@RequestParam(value="updateDate",required=false)Date updateDate,
			@RequestParam(value="status",required=false)Integer status,
			HttpSession session)  {
		int count = appService.countAll(type,capacity,readingNumber,downloadNumber,appCateId,createDate,updateDate,status);
		return count;
	}
	/**
	 * 应用单个加载
	 * @return
	 */
	@RequestMapping(value = "/{appId}", method = {RequestMethod.GET,RequestMethod.POST})
	public  StateResultList loadApp(@PathVariable("appId") Integer appId,HttpSession session)  {
		List<App> list = new ArrayList<App>();
		App app = appService.loadApp(appId);
			if(app!=null &&!app.equals("")){
				list.add(app);
				return ResultUtil.getSlefSRSuccessList(list);
			}else{
				return ResultUtil.getSlefSRFailList(list);
			}
	}
	/**
	 * 应用单个加载
	 * @return
	 */
	@RequestMapping(value = "/loadSmall/{appId}", method = {RequestMethod.GET,RequestMethod.POST})
	public  StateResultList loadSmallApp(@PathVariable("appId") Integer appId,HttpSession session)  {
		List<App> list = new ArrayList<App>();
		App app = appService.loadSmallApp(appId);
		if(app!=null &&!app.equals("")){
			list.add(app);
			return ResultUtil.getSlefSRSuccessList(list);
		}else{
			return ResultUtil.getSlefSRFailList(list);
		}
	}
	
}
