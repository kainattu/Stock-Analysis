package com.kainattu.stock.api.service;

import com.kainattu.stock.api.dto.Stock;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StockServiceImpl implements StockService {




    @Override
    public List<Stock> getAllStock() {

        //TODO needs to be changed later.

        List<Stock> stockList=null;
        if (stockList==null) {
            stockList = new ArrayList<>();
            stockList.add(new Stock("SBIN", "State Bank of India", Stock.Type.NIFTY, Stock.Sector.BANK));
            stockList.add(new Stock("INDIACEM", "India Cement ", Stock.Type.MIDCAP, Stock.Sector.CEMENT));
        }
        return stockList;
    }

    @Override
    public List<Stock> getNifty50Stock() {
        return null;
    }

    @Override
    public List<Stock> getMidCapStock() {
        return null;
    }

    @Override
    public List<Stock> getSmalCap() {
        return null;
    }
}
