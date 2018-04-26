package com.hw.correlation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hw.frame.Dao;
import com.hw.mapper.CorrelationMapper;
import com.hw.vo.Correlation;
@Repository("correlationDao")
public class CorrelationDao implements Dao<Correlation, Integer> {

	@Autowired
	CorrelationMapper mapper;
	
	@Override
	public void insert(Correlation t) {
		mapper.insert(t);
	}

	@Override
	public void delete(Integer s) {
		mapper.delete(s);
	}

	@Override
	public void update(Correlation t) {
		mapper.update(t);
	}

	@Override
	public Correlation select(Integer s) {
		
		return mapper.select(s);
	}

	@Override
	public List<Correlation> select() {
		return mapper.selectall();
	}

	

}
