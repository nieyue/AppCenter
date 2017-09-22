package com.nieyue.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.nieyue.bean.AppCate;

/**
 * 应用类型数据库接口
 * @author yy
 *
 */
@Mapper
public interface AppCateDao {
	/** 新增应用类型*/	
	public boolean addAppCate(AppCate appCate) ;	
	/** 删除应用类型 */	
	public boolean delAppCate(Integer appCateId) ;
	/** 更新应用类型*/	
	public boolean updateAppCate(AppCate appCate);
	/** 装载应用类型 */	
	public AppCate loadAppCate(Integer appCateId);	
	/** 应用类型总共数目 */	
	public int countAll();	
	/** 分页应用类型信息 */
	public List<AppCate> browsePagingAppCate(@Param("pageNum")int pageNum,@Param("pageSize")int pageSize,@Param("orderName")String orderName,@Param("orderWay")String orderWay) ;		
}
