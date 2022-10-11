package tn.esprit.rh.achat.services.produit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.rh.achat.entities.Stock;
import tn.esprit.rh.achat.repositories.StockRepository;
import tn.esprit.rh.achat.services.IStockService;
import tn.esprit.rh.achat.services.StockServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class StockServiceImplMock {
    @Mock
    StockRepository stockRepository;

    @InjectMocks
    StockServiceImpl stockServiceImpl;

    Stock s= Stock.builder().libelleStock("stock test").qte(100).qteMin(10).build();
    List<Stock> listUsers = new ArrayList<Stock>() {
        {
            add(Stock.builder().libelleStock("stock test1").qte(100).qteMin(10).build());
            add(Stock.builder().libelleStock("stock test2").qte(200).qteMin(20).build());
        }
    };

    @Test
    public void testRetrieveUser() {
        Mockito.when(stockRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(s));
        Stock s1 = stockServiceImpl.retrieveStock(new Long(2));
        Assertions.assertNotNull(s1);
    }
}
