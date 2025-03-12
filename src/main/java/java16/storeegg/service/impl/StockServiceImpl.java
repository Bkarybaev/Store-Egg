package java16.storeegg.service.impl;

import java16.storeegg.dto.SimpleResponse;
import java16.storeegg.dto.request.SaveStockRequest;
import java16.storeegg.enums.Role;
import java16.storeegg.models.Stock;
import java16.storeegg.repo.StockRepo;
import java16.storeegg.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StockServiceImpl implements StockService {
    private final StockRepo stockRepo;

    @Override
    public SimpleResponse addStock(SaveStockRequest saveStockRequest) {

        Stock stock = new Stock();

        stock.setName(saveStockRequest.getName());
        stock.setAddress(saveStockRequest.getAddress());
        stock.setCity(saveStockRequest.getCity());
        stock.setPhoneNumber(saveStockRequest.getPhoneNumber());
        stock.setRole(saveStockRequest.getRole());

        try {
            stockRepo.save(stock);
            return SimpleResponse.builder()
                    .status(HttpStatus.CREATED)
                    .massage("successfull created stocks")
                    .build();
        } catch (RuntimeException e) {
            return SimpleResponse.builder()
                    .status(HttpStatus.CONFLICT)
                    .massage("error"+e.getMessage())
                    .build();
        }

    }
}
