package com.java.stock;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.java.biz.BizDTO;
import com.java.biz.BizDao;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class StockServiceImp implements StockService {
	
	private final StockDao stockDao;
	
	@Override
	public String searchIncome(Model model, IncomeDTO incomeDTO) {
		List<IncomeDTO> incomes = stockDao.searchIncome(incomeDTO);
		model.addAttribute("result", incomes);
		return "stock/income";
	}
	
	@Override
	public StockResDTO incomeEdit(StockReqDTO stockReqDTO) {
		boolean status = false;
		
		if("edit".equals(stockReqDTO.getState())) {
			for(IncomeDTO incomeDTO : stockReqDTO.getInComes()) {
				if(incomeDTO.getIncomeNo() > 0) {
					if(stockDao.editIncome(incomeDTO) > 0) {
						status = true;
					}
				}
			}
			for(IncomeDTO incomeDTO : stockReqDTO.getInComes()) {
				if(incomeDTO.getIncomeNo() > 0) {
					IncomeDTO incomeDTO2 = stockDao.findByItem(incomeDTO.getIncomeNo());
					if(incomeDTO2 != null) {
						StockDTO stockDTO = stockDao.findByBizAndItem(incomeDTO);
						if(stockDTO == null) {
							stockDTO = StockDTO.builder().bizNo(incomeDTO.getBizNo()).name(incomeDTO.getStockName()).qty(incomeDTO.getIncomeQty()).build();
							stockDao.createStock(stockDTO);
						} else {
							int incomeQty = incomeDTO.getIncomeQty() - incomeDTO2.getIncomeQty();
							stockDTO = StockDTO.builder().itemCode(stockDTO.getItemCode()).qty(incomeQty).build();
							stockDao.updateStock(stockDTO);
						}
						status = true;
					}
				}
			}
		}
		
		if("delete".equals(stockReqDTO.getState())) {
			for(IncomeDTO incomeDTO : stockReqDTO.getInComes()) {
				if(incomeDTO.getIncomeNo() > 0) {
					if(stockDao.deleteIncome(incomeDTO) == 1) {
						status = true;
					}
				}
			}
		}
		
		return StockResDTO.builder().status(status).build();
	}
	
	@Override
	public String searchStock(Model model, StockDTO stockDTO) {
		List<StockDTO> stocks = stockDao.searchStock(stockDTO);
		model.addAttribute("result", stocks);
		return "stock/stock";
	}
	
	@Override
	public StockResDTO stockEdit(StockReqDTO stockReqDTO) {
        boolean status = false;

        if("edit".equals(stockReqDTO.getState())) {
            for(StockDTO stockDTO : stockReqDTO.getStocks()) {
                if(stockDTO.getItemCode() > 0) {
                	if (stockDao.editStock(stockDTO) == 1) {
                		status = true;
                	}
                }

            }
        }

        if("delete".equals(stockReqDTO.getState())) {
            for(StockDTO stockDTO : stockReqDTO.getStocks()) {
                if(stockDTO.getItemCode() > 0) {
                    if(stockDao.deleteStock(stockDTO.getItemCode()) == 1) {
                        status = true;
                    }
                }
            }
        }

		return StockResDTO.builder().status(status).build();
    }
		
	
	private final BizDao bizDao;

	@Override
	public StockResDTO biz(Integer bizNo) {
        boolean status = true;
		BizDTO bizDTO = bizDao.findOne(bizNo);
		if(bizDTO == null) status = false;
		return StockResDTO.builder().status(status).result(bizDTO).build();
	}

}
