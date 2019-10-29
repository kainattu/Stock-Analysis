package com.kainattu.stock.api.service;

import com.kainattu.stock.api.dto.Ratios;
import com.kainattu.stock.api.dto.Stock;

import java.util.ArrayList;
import java.util.List;

public interface StockService {

    public List<Stock> getAllStock();
    public List<Stock> getNifty50Stock();
    public List<Stock> getMidCapStock();
    public List<Stock> getSmalCap();


}
