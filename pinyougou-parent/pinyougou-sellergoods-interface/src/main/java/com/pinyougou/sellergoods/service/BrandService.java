package com.pinyougou.sellergoods.service;

import java.util.List;
import java.util.Map;

import com.pinyougou.pojo.TbBrand;

import entity.PageResult;

/**
 * 品牌服务层接口
 * 
 * @author liucx
 *
 */
public interface BrandService {

	/**
	 * 返回全部列表
	 * 
	 * @return
	 */
	public List<TbBrand> findAll();

	/**
	 * 返回分页列表
	 * 
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public PageResult findPage(Integer pageNum, Integer pageSize);

	/**
	 * 添加
	 * 
	 * @param brand
	 */
	public void add(TbBrand brand);

	/**
	 * 修改
	 * 
	 * @param brand
	 */
	public void update(TbBrand brand);

	/**
	 * 根据 ID 获取实体
	 * 
	 * @param id
	 * @return
	 */
	public TbBrand findOne(Long id);

	/**
	 * 批量删除
	 * 
	 * @param ids
	 */
	public void delete(Long[] ids);

	/**
	 * 模糊查询
	 * 
	 * @param brand
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public PageResult search(TbBrand brand, Integer pageNum, Integer pageSize);

	/**
	 * 品牌下拉框数据
	 * 
	 * @return
	 */
	List<Map> selectOptionList();
}
