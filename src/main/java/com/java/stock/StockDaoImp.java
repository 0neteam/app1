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
        return stockMapper.searchIncome(incomeDTO);
    }

    @Override
    public int createIncome(IncomeDTO incomeDTO) {

    	int create = stockMapper.createIncome(incomeDTO);
    	System.out.println(incomeDTO);
    	
    	if (create > 0) {
    		System.out.println(incomeDTO);
    	    Integer incqty = stockMapper.searchQty(incomeDTO.getIncomeNo());
            System.out.println(incqty);
    	    if (incqty == null) {
                incqty = 0;
            }
            System.out.println(incqty);
    	    int itemCode = incomeDTO.getItemCode();
    	    System.out.println(itemCode);
    	    
    	    return stockMapper.updateQtyP(itemCode, incqty);
    	} else {
    		return 0;
    	}
    }


    @Override
    public int editIncome(IncomeDTO incomeDTO) {
    	
    	Integer stqty = stockMapper.searchQty(incomeDTO.getIncomeNo());

        if (stqty != null) {
        	
        	int edit = stockMapper.editIncome(incomeDTO);
        	
        	if (edit > 0) {
        	    int incqty = stockMapper.searchQty(incomeDTO.getIncomeNo());
        	    int itemCode = incomeDTO.getItemCode();
        	    int diff = incqty - stqty;
        	    
        	    return stockMapper.updateQtyP(itemCode, diff);
        	} else {
        		return 0;
        	}
        } else {
        	return 0;
        }
    }
    
    @Override
    public int deleteIncome(IncomeDTO incomeDTO) {
    	
    	int delete = stockMapper.deleteIncome(incomeDTO);
    	
    	if (delete > 0) {
    		int incomeNo = incomeDTO.getIncomeNo();
    		int itemCode = incomeDTO.getItemCode();
    	    int incqty = stockMapper.searchQty(incomeNo);
    	    
    	    return stockMapper.updateQtyM(itemCode, incqty);
    	} else {
    		return 0;
    	}
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

	public IncomeDTO findByItem(int incomeNo) {
		return stockMapper.findByItem(incomeNo);
	}
	public StockDTO findByBizAndItem(IncomeDTO incomeDTO) {
		return stockMapper.findByBizAndItem(incomeDTO);
	}
	public int createStock(StockDTO stockDTO) {
		return stockMapper.createStock(stockDTO);
	}
	public int updateStock(StockDTO stockDTO) {
		return stockMapper.updateStock(stockDTO);
	}

}
