package com.kainattu.stock.api.contoller;

import com.kainattu.stock.api.dto.*;
import com.kainattu.stock.api.service.FundamenatalsService;
import com.kainattu.stock.api.service.StockService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping("/stock")
public class StockController {

    private static final Logger LOGGER = LoggerFactory.getLogger(StockController.class);
    @Autowired
    FundamenatalsService fundamenatalsService;
    @Autowired
    StockService stockService;


    @GetMapping("/{script}")
    public ResponseEntity<CustomResponse> getStockSummary(@PathVariable String script) throws IOException {


        ResponseEntity<CustomResponse> responseEntity = null;


        LOGGER.debug("all stocks" + stockService.getAllStock());

        if (!(stockService.getAllStock().contains(new Stock(script))))
            return responseEntity = new ResponseEntity<CustomResponse>(new CustomResponse(CustomResponse.Response.FAILURE, "NotFound", "Script Not Founde"), HttpStatus.BAD_REQUEST);

        StockSummary stockSummary = fundamenatalsService.getStockSummary(script);

        if (stockSummary != null) {

            CustomResponse customResponse = new CustomResponse(CustomResponse.Response.SUCCESS, stockSummary);
            customResponse.add(linkTo(methodOn(StockController.class).getStockSummary(script)).withSelfRel());
            customResponse.add(linkTo(methodOn(StockController.class).getAllStockSummary()).withRel("allstocks"));
            customResponse.add(linkTo(methodOn(StockController.class).getFinancialReport(script)).withRel("financial-report"));

            responseEntity = new ResponseEntity<CustomResponse>(customResponse, HttpStatus.OK);
        }
        else {


            CustomResponse customResponse = new CustomResponse(CustomResponse.Response.FAILURE, HttpStatus.CONFLICT.toString(), "something wrong");
            customResponse.add(linkTo(methodOn(StockController.class).getStockSummary(script)).withSelfRel());


            responseEntity = new ResponseEntity<CustomResponse>(customResponse, HttpStatus.BAD_REQUEST);
        }
        return responseEntity;
    }

    @GetMapping("/{script}/financial-report")
    public  ResponseEntity<CustomResponse> getFinancialReport(String script) throws IOException {
        CustomResponse customResponse= new CustomResponse(CustomResponse.Response.SUCCESS,null);
        customResponse.add(linkTo(methodOn(StockController.class).getFinancialReport(script)).withSelfRel());
        customResponse.add(linkTo(methodOn(StockController.class).getStockIncomeStatement(script)).slash("income-statement").withRel("financial-reports"));

        return new ResponseEntity<>(customResponse,HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<CustomResponse> getAllStockSummary() throws IOException {

        ResponseEntity<CustomResponse> responseEntity = null;


        LOGGER.debug("all stocks" + stockService.getAllStock());

        List<StockSummary> stockSummaryList = fundamenatalsService.getAllStockSummary(stockService.getAllStock());

        CustomResponse customResponse = new CustomResponse(CustomResponse.Response.SUCCESS, stockSummaryList);
        customResponse.add(linkTo(methodOn(StockController.class).getAllStockSummary()).withSelfRel());

        for(StockSummary summary:stockSummaryList){

            Link stockLink = linkTo(methodOn(StockController.class)
                    .getAllStockSummary()).slash(summary.getName()).withRel("allStocks");
            customResponse.add(stockLink);
        }
        responseEntity = new ResponseEntity<>(customResponse, HttpStatus.OK);

        return responseEntity;
    }


    @GetMapping("/{script}/details")
    public ResponseEntity<CustomResponse> getStockDetails(@PathVariable String script) throws IOException {

        ResponseEntity<CustomResponse> responseEntity = null;

        return responseEntity;
    }


    @GetMapping("/{script}/ratio")
    public ResponseEntity<CustomResponse> getStockRatios(@PathVariable String script) throws IOException {

        ResponseEntity<CustomResponse> responseEntity = null;

        Ratios ratios = fundamenatalsService.getRatios(script);

        responseEntity = new ResponseEntity<>(new CustomResponse(CustomResponse.Response.SUCCESS, ratios), HttpStatus.OK);

        return responseEntity;
    }

    @GetMapping("/{script}/income-statement")
    public ResponseEntity<CustomResponse> getStockIncomeStatement(@PathVariable String script) throws IOException {

        ResponseEntity<CustomResponse> responseEntity = null;
        List<IncomeStatement> incomeStatementList =fundamenatalsService.getStockIncomeStatement(script);

        CustomResponse customResponse=new CustomResponse(CustomResponse.Response.SUCCESS, incomeStatementList);
        customResponse.add(linkTo(methodOn(StockController.class).getStockIncomeStatement(script)).withSelfRel());

        customResponse.add(linkTo(methodOn(StockController.class).getStockSummary(script)).withRel("summary"));

        responseEntity = new ResponseEntity<>(customResponse, HttpStatus.OK);
        return responseEntity;
    }

    @GetMapping("/{script}/balance-sheet")
    public ResponseEntity<CustomResponse> getStockBalanceSheet(@PathVariable String script) throws IOException {

        ResponseEntity<CustomResponse> responseEntity = null;

        fundamenatalsService.getBalanceSheet(script);

        return responseEntity;
    }


    @GetMapping("/{script}/cash-flow")
    public ResponseEntity<CustomResponse> getStockCashFlow(@PathVariable String script) throws IOException {

        ResponseEntity<CustomResponse> responseEntity = null;

        fundamenatalsService.getStockCashFlow(script);

        return responseEntity;
    }
}
