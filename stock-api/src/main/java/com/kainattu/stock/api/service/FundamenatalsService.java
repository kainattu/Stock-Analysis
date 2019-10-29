package com.kainattu.stock.api.service;

import com.kainattu.stock.api.dto.*;

import java.io.IOException;
import java.util.List;

public interface FundamenatalsService {

    /**
     * @param scriptName, script name to get Stock Details
     * @return stock details including, summary and all financials and ratios.
     * @throws IOException
     */
    public StockDetails getDetails(String scriptName) throws IOException;

    /**
     * @param script
     * @return
     * @throws IOException
     */
    public Ratios getRatios(String script) throws IOException;

    /**
     * @param script
     * @return
     * @throws IOException
     */
    public BalanceSheet getBalanceSheet(String script) throws IOException;

    /**
     * @param script
     * @return
     * @throws IOException
     */
    public CashFlow getStockCashFlow(String script) throws IOException;

    /**
     * @param script
     * @return
     * @throws IOException
     */
    public List<IncomeStatement> getStockIncomeStatement(String script) throws IOException;

    public List<StockSummary> getAllStockSummary(List<Stock> scripts) throws IOException;

    StockSummary getStockSummary(String script) throws IOException;
}
