package com.java.stock;

import java.util.List;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class StockDaoImp implements StockDao {
	
	private final StockMapper stockMapper;
	
	@Override
	public List<IncomeDTO> findIncome(int no) {
		return stockMapper.findIncome(no);
	}

	@Override
	public List<StockDTO> findStock(int no) {
		return stockMapper.findStock(no);
	}

	@Override
	public int editincome(StockDTO stockDTO) {
		return stockMapper.editincome(stockDTO);
	}

	@Override
	public int editstock(StockDTO stockDTO) {
		return stockMapper.editstock(stockDTO);
	}

	@Override
	public int deleteincome(int no) {
		return stockMapper.deleteincome(no);
	}

	@Override
	public int deletestock(int no) {
		return stockMapper.deletestock(no);
	}

}
