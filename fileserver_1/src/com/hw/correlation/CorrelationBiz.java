package com.hw.correlation;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hw.frame.Biz;
import com.hw.frame.Dao;
import com.hw.vo.Correlation;
import com.hw.vo.Product;
@Service("correlationBiz")
public class CorrelationBiz implements Biz<Correlation, Integer> {

	@Resource(name="correlationDao")
	Dao<Correlation, Integer> dao;
	
	public CorrelationBiz() {
		dao = new CorrelationDao();
	}
	@Transactional
	@Override
	public void register(Correlation t) {
		//dao.insert(t);
	}
	@Transactional
	@Override
	public void remove(Integer s) {
		//dao.delete(s);
	}
	@Transactional
	@Override
	public void modify(Correlation t) {
		//dao.update(t);
	}
	@Transactional
	@Override
	public Correlation get(Integer s) {
		return dao.select(s);
	}
	@Transactional
	@Override
	public List<Correlation> get() {
		return dao.select();
	}
}
