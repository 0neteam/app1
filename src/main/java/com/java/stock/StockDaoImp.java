package com.java.stock;

import java.util.List;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class StockDaoImp implements StockDao {
	
	private final StockMapper stockMapper;
	
    @Override
    public List<IncomeDTO> searchIncome(Integer pcode, String icode, String pname) {
        return stockMapper.searchIncome(pcode, icode, pname);
    }
    
    @Override
    public List<StockDTO> searchStock(StockDTO stockDTO) {
        return stockMapper.searchStock(stockDTO);
    }

	@Override
	public int editIncome(IncomeDTO incomeDTO) {
		return stockMapper.editIncome(incomeDTO);
	}

	@Override
	public int editStock(StockDTO stockDTO) {
		return stockMapper.editStock(stockDTO);
	}

	@Override
	public int deleteIncome(int no) {
		return stockMapper.deleteIncome(no);
	}

	@Override
	public int deleteStock(int no) {
		return stockMapper.deleteStock(no);
	}


}
