package com.kainattu.stock.api.service;

import com.kainattu.stock.api.dto.*;
import com.kainattu.stock.api.utils.DateUtils;
import com.kainattu.stock.api.utils.FormateUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class YahooFundamenatalsServiceImpl implements FundamenatalsService {

    public static final String baseUrl = "https://in.finance.yahoo.com/quote/";
    public static final String suffexUrl = ".NS";
    private static final Logger LOGGER = LoggerFactory.getLogger(YahooFundamenatalsServiceImpl.class);

    private static final String FINANCIAL_INCOME_STATEMENT = "/financials";
    private static final String FINANCIAL_BALANCE_SHSEET = "/balance-sheet";
    private static final String FINANCIAL_CASH_FLOW = "/cash-flow";
    private static final String FINANCIAL_RATIOS = "/key-statistics";

    public static void main(String[] args) throws IOException {
        YahooFundamenatalsServiceImpl service = new YahooFundamenatalsServiceImpl();

//        System.out.println(" Stock Details  :" + service.getStockIncomeStatement("INDIACEM"));
        System.out.println(" Stock Details  :" + service.getStockIncomeStatement("SBIN"));
    }

    @Override
    public StockDetails getDetails(String scriptName) throws IOException {
        return null;
    }

    @Override
    public Ratios getRatios(String script) throws IOException {

        Document document = getPageDocument(getScriptURL(script) + FINANCIAL_RATIOS);


        Ratios ratios = new Ratios();

        //TODO your stuff here.


        // for debt to equity . the same element has all ratios.
        Elements elements = document.getElementsByClass("Fz(s) Fw(500) Ta(end) Pstart(10px) Miw(60px)");

        ratios.setBookValue(Double.parseDouble(elements.get(56).text()));
        ratios.setPriceToBook(Double.parseDouble(elements.get(6).text()));
        ratios.setDebtToEquity(Double.parseDouble(elements.get(54).text()));

        return ratios;
    }

    @Override
    public BalanceSheet getBalanceSheet(String script) throws IOException {

        Document document = getPageDocument(getScriptURL(script) + FINANCIAL_BALANCE_SHSEET);


        return null;
    }

    @Override
    public CashFlow getStockCashFlow(String script) throws IOException {
        Document document = getPageDocument(getScriptURL(script) + FINANCIAL_CASH_FLOW);

        return null;
    }

    @Override
    public List<IncomeStatement> getStockIncomeStatement(String script) throws IOException {

        LOGGER.debug(" url :" + getScriptURL(script));

        Document document = getPageDocument(getScriptURL(script) + FINANCIAL_INCOME_STATEMENT);

        Elements elements = document.getElementsByClass("D(tbr) fi-row Bgc($hoverBgColor):h");

        // For Revenue

    /*  LOGGER.debug(" Revenue to  current 2019. :"+ elements.get(0).getAllElements().select("div").get(4).text());
        LOGGER.debug(" Revenue to  year 2019. :"+elements.get(0).getAllElements().select("div").get(5).text());
        LOGGER.debug(" Revenue to  year 2018. :"+elements.get(0).getAllElements().select("div").get(6).text());
        LOGGER.debug(" Revenue to  year 2017. :"+elements.get(0).getAllElements().select("div").get(7).text());
        LOGGER.debug(" Revenue to  year 2016. :"+elements.get(0).getAllElements().select("div").get(8).text());
*/
        //for Operation income or loss
     /* LOGGER.debug(" income to  year Current. :"+elements.get(7).getAllElements().select("div").get(4).text());
        LOGGER.debug(" income to  year 2019. :"+elements.get(7).getAllElements().select("div").get(5).text());
        LOGGER.debug(" income to  year 2018. :"+elements.get(7).getAllElements().select("div").get(6).text());
        LOGGER.debug(" income to  year 2017. :"+elements.get(7).getAllElements().select("div").get(7).text());
        LOGGER.debug(" income to  year 2016. :"+elements.get(7).getAllElements().select("div").get(8).text()); */

        // Interest paid.

     /* LOGGER.debug(" interest expense to  year Current. :"+elements.get(8).getAllElements().select("div").get(4).text());
        LOGGER.debug(" interest expense to  year 2019. :"+elements.get(8).getAllElements().select("div").get(5).text());
        LOGGER.debug(" interest expense to  year 2018. :"+elements.get(8).getAllElements().select("div").get(6).text());
        LOGGER.debug(" interest expense to  year 2017. :"+elements.get(8).getAllElements().select("div").get(7).text());
        LOGGER.debug(" interest expense to  year 2016. :"+elements.get(8).getAllElements().select("div").get(8).text());*/


        // for net income.
    /*  LOGGER.debug(" income to  year Current. :"+elements.get(14).getAllElements().select("div").get(4).text());
        LOGGER.debug(" income to  year 2019. :"+elements.get(14).getAllElements().select("div").get(5).text());
        LOGGER.debug(" income to  year 2018. :"+elements.get(14).getAllElements().select("div").get(6).text());
        LOGGER.debug(" income to  year 2017. :"+elements.get(14).getAllElements().select("div").get(7).text());
        LOGGER.debug(" income to  year 2016. :"+elements.get(14).getAllElements().select("div").get(8).text());*/

        // for net EPS.
      /* LOGGER.debug(" EPS to  year Current. :" +elements.get(17).getAllElements().select("div").get(4).text());
        LOGGER.debug(" EPS to  year 2019. :" + elements.get(17).getAllElements().select("div").get(5).text());
        LOGGER.debug(" EPS to  year 2018. :" + elements.get(17).getAllElements().select("div").get(6).text());
        LOGGER.debug(" EPS to  year 2017. :" + elements.get(17).getAllElements().select("div").get(7).text());
        LOGGER.debug(" EPS to  year 2016. :" + elements.get(17).getAllElements().select("div").get(8).text());
*/

        List<IncomeStatement> incomeStatementList = new ArrayList<>(5);

        IncomeStatement curruntYeatrIS = new IncomeStatement(-1);
        curruntYeatrIS.setRevenue(FormateUtils.convertStringToLong(elements.get(0).getAllElements().select("div").get(4).text()));
        curruntYeatrIS.setIncomeFromOperation(FormateUtils.convertStringToLong(elements.get(7).getAllElements().select("div").get(4).text()));
        curruntYeatrIS.setInterestPaid(FormateUtils.convertStringToLong(elements.get(8).getAllElements().select("div").get(4).text()));
        curruntYeatrIS.setNetIncome(FormateUtils.convertStringToLong(elements.get(14).getAllElements().select("div").get(4).text()));
        curruntYeatrIS.setEbs(FormateUtils.convertStringToDouble(elements.get(17).getAllElements().select("div").get(4).text()));

        incomeStatementList.add(curruntYeatrIS);

        IncomeStatement last1YearIS = new IncomeStatement(DateUtils.getCurrentYear());
        last1YearIS.setRevenue(FormateUtils.convertStringToLong(elements.get(0).getAllElements().select("div").get(5).text()));
        last1YearIS.setIncomeFromOperation(FormateUtils.convertStringToLong(elements.get(7).getAllElements().select("div").get(5).text()));
        last1YearIS.setInterestPaid(FormateUtils.convertStringToLong(elements.get(8).getAllElements().select("div").get(5).text()));
        last1YearIS.setNetIncome(FormateUtils.convertStringToLong(elements.get(14).getAllElements().select("div").get(5).text()));
        last1YearIS.setEbs(FormateUtils.convertStringToDouble(elements.get(17).getAllElements().select("div").get(5).text()));

        incomeStatementList.add(last1YearIS);

        IncomeStatement last2YearIS = new IncomeStatement(DateUtils.getCurrentYear() - 1);
        last2YearIS.setRevenue(FormateUtils.convertStringToLong(elements.get(0).getAllElements().select("div").get(6).text()));
        last2YearIS.setIncomeFromOperation(FormateUtils.convertStringToLong(elements.get(7).getAllElements().select("div").get(6).text()));
        last2YearIS.setInterestPaid(FormateUtils.convertStringToLong(elements.get(8).getAllElements().select("div").get(6).text()));
        last2YearIS.setNetIncome(FormateUtils.convertStringToLong(elements.get(14).getAllElements().select("div").get(6).text()));
        last2YearIS.setEbs(FormateUtils.convertStringToDouble(elements.get(17).getAllElements().select("div").get(6).text()));

        incomeStatementList.add(last2YearIS);

        IncomeStatement last3YearIS = new IncomeStatement(DateUtils.getCurrentYear() - 2);
        last3YearIS.setRevenue(FormateUtils.convertStringToLong(elements.get(0).getAllElements().select("div").get(7).text()));
        last3YearIS.setIncomeFromOperation(FormateUtils.convertStringToLong(elements.get(7).getAllElements().select("div").get(7).text()));
        last3YearIS.setInterestPaid(FormateUtils.convertStringToLong(elements.get(8).getAllElements().select("div").get(7).text()));
        last3YearIS.setNetIncome(FormateUtils.convertStringToLong(elements.get(14).getAllElements().select("div").get(7).text()));
        last3YearIS.setEbs(FormateUtils.convertStringToDouble(elements.get(17).getAllElements().select("div").get(7).text()));

        incomeStatementList.add(last3YearIS);

        IncomeStatement last4YearIS = new IncomeStatement(DateUtils.getCurrentYear() - 3);
        last4YearIS.setRevenue(FormateUtils.convertStringToLong(elements.get(0).getAllElements().select("div").get(8).text()));
        last4YearIS.setIncomeFromOperation(FormateUtils.convertStringToLong(elements.get(7).getAllElements().select("div").get(8).text()));
        last4YearIS.setInterestPaid(FormateUtils.convertStringToLong(elements.get(8).getAllElements().select("div").get(8).text()));
        last4YearIS.setNetIncome(FormateUtils.convertStringToLong(elements.get(14).getAllElements().select("div").get(8).text()));
        last4YearIS.setEbs(FormateUtils.convertStringToDouble(elements.get(17).getAllElements().select("div").get(8).text()));

        incomeStatementList.add(last4YearIS);

        return incomeStatementList;
    }


    @Override
    public List<StockSummary> getAllStockSummary(List<Stock> scripts) throws IOException {
        List<StockSummary> stockSummaryList = null;

        for (Stock stock : scripts) {
            if (stockSummaryList == null)
                stockSummaryList = new ArrayList<>(scripts.size());

            stockSummaryList.add(getStockSummary(stock.getName()));
        }

        return stockSummaryList;
    }

    /**
     * @param scriptName
     * @return
     */
    public String getScriptURL(String scriptName) {
        return baseUrl + scriptName + suffexUrl;
    }

    public Document getPageDocument(String scriptUrl) throws IOException {

        Document document = Jsoup.connect(scriptUrl).userAgent("Mozilla").get();

        /*if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(" page title :" + document.title());
        }
*/
        return document;

    }


    @Override
    public StockSummary getStockSummary(String script) throws IOException {

        Document document = getPageDocument(getScriptURL(script));

        StockSummary stockSummary = new StockSummary();

        // temprary.
        Elements elements = null;
        // get Stock name, Description

        elements = document.getElementsByClass("D(ib) Mt(-5px) Mend(20px) Maw(56%)--tab768 Maw(52%) Ov(h) smartphone_Maw(85%) smartphone_Mend(0px)");

        String scriptName = elements.get(0).getAllElements().get(1).getAllElements().get(0).text();

        if (LOGGER.isDebugEnabled())
            LOGGER.debug(" stock name: " + scriptName);

        stockSummary.setName(scriptName.substring(0, scriptName.indexOf(".")));

        stockSummary.setDescription(scriptName.substring(scriptName.indexOf("-") + 1));

        // getting price.
        elements = document.getElementsByClass("Trsdu(0.3s) Trsdu(0.3s) Fw(b) Fz(36px) Mb(-4px) D(b)");
        stockSummary.setPrice(Double.parseDouble(elements.get(0).text()));


        return stockSummary;
    }


}
