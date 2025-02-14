package com.java.stock;

import java.util.List;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class StockDaoImp implements StockDao {
	
	private final StockMapper stockMapper;
	
    @Override
    public List<IncomeDTO> searchIncome(IncomeDTO incomeDTO) {
        System.out.println(incomeDTO);
        return stockMapper.searchIncome(incomeDTO);
    }

    @Override
    public int createIncome(IncomeDTO incomeDTO) {
        return stockMapper.createIncome(incomeDTO);
    }

    @Override
    public int editIncome(IncomeDTO incomeDTO) {
        return stockMapper.editIncome(incomeDTO);
    }
    
    @Override
    public int deleteIncome(int incomeNo) {
    	return stockMapper.deleteIncome(incomeNo);
    }

    
    
    @Override
    public List<StockDTO> searchStock(StockDTO stockDTO) {
        return stockMapper.searchStock(stockDTO);
    }

	@Override
	public int editStock(StockDTO stockDTO) {
		return stockMapper.editStock(stockDTO);
	}

	@Override
	public int deleteStock(int no) {
		return stockMapper.deleteStock(no);
	}

}
