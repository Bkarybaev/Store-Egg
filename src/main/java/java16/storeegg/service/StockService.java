package java16.storeegg.service;

import java16.storeegg.dto.SimpleResponse;
import java16.storeegg.dto.request.SaveStockRequest;
import org.springframework.boot.autoconfigure.task.TaskSchedulingProperties;

public interface StockService {

    SimpleResponse addStock(SaveStockRequest saveStockRequest);
}
