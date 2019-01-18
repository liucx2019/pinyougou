package com.test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.query.Criteria;
import org.springframework.data.solr.core.query.Query;
import org.springframework.data.solr.core.query.SimpleQuery;
import org.springframework.data.solr.core.query.result.ScoredPage;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.itcast.pojo.TbItem;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext-solr.xml")
public class TestTemplate {
	@Autowired
	private SolrTemplate solrTemplate;

	@Test
	public void testAdd() {

		TbItem item = new TbItem();
		item.setId(1L);
		item.setBrand("华为");
		item.setCategory("手机");
		item.setPrice(new BigDecimal(2000.01));
		item.setGoodsId(1L);
		item.setSeller("华为旗舰店1");
		item.setTitle("华为meta20");
		solrTemplate.saveBean(item);
		solrTemplate.commit();
	}

	@Test
	public void testFindOne() {
		TbItem item = solrTemplate.getById(1, TbItem.class);
		System.out.println(item.getBrand() + "  " + item.getCategory());
	}

	@Test
	public void testDelete() {
		solrTemplate.deleteById("1");
		solrTemplate.commit();
	}

	@Test
	public void testAddList() {
		List<TbItem> list = new ArrayList<TbItem>();

		for (int i = 0; i < 100; i++) {
			TbItem item = new TbItem();
			item.setId(i + 1L);
			item.setBrand("华为");
			item.setCategory("手机");
			item.setPrice(new BigDecimal(2000.01 + i));
			item.setGoodsId(1L);
			item.setSeller("华为旗舰店1" + i);
			item.setTitle("华为meta20" + i);
			list.add(item);
		}
		solrTemplate.saveBeans(list);
		solrTemplate.commit();
	}

	@Test
	public void testPageQuery() {
		// 分页查询
		Query query = new SimpleQuery("*:*");

		query.setRows(20);// 每页记录数(默认 10)
		query.setOffset(10);// 开始索引（默认 0）

		ScoredPage<TbItem> page = solrTemplate.queryForPage(query, TbItem.class);
		long elements = page.getTotalElements();// 总记录数
		System.out.println("总记录数:" + elements);
		List<TbItem> list = page.getContent();
		showList(list);
	}

	// 显示记录数据
	private void showList(List<TbItem> list) {

		for (int i = 0; i < list.size(); i++) {
			TbItem item = list.get(i);
			String title = item.getTitle();
			System.out.println(title);
		}

	}

	// 条件查询
	@Test
	public void testPageQueryMutil() {
		Query query = new SimpleQuery("*:*");

		Criteria criteria = new Criteria("item_title").contains("5");
		// criteria = criteria.and("item_seller").contains("华为旗舰店15");
		query.addCriteria(criteria);

		ScoredPage<TbItem> page = solrTemplate.queryForPage(query, TbItem.class);
		System.out.println("总记录数:" + page.getTotalElements());
		List<TbItem> content = page.getContent();
		showList(content);

	}

	// 删除全部数据
	@Test
	public void deleteAll() {
		Query query = new SimpleQuery("*:*");
		solrTemplate.delete(query);
		solrTemplate.commit();
	}

}