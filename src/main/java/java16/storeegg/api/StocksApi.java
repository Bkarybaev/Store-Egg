package java16.storeegg.api;

import java16.storeegg.dto.SimpleResponse;
import java16.storeegg.dto.request.SaveStockRequest;
import java16.storeegg.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/stock")
@RequiredArgsConstructor
public class StocksApi {

    private final StockService stockService;

    @PostMapping("/saveStock")
    public SimpleResponse saveStocks(@RequestBody SaveStockRequest saveStockRequest){
        return stockService.addStock(saveStockRequest);
    }
}
