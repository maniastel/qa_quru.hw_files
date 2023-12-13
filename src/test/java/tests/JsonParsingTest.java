package tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

public class JsonParsingTest {

    private ClassLoader cl = JsonParsingTest.class.getClassLoader();
    ObjectMapper mapper = new ObjectMapper();
    @Test
    @DisplayName("Проверка содержимого json файла")
    public void jsonParsingTest () throws Exception {
        try (Reader reader = new InputStreamReader(
                cl.getResourceAsStream("product.json")
        )) {
            Product json = mapper.readValue(reader, Product.class);
            Assertions.assertEquals(12, json.product_id);
            Assertions.assertEquals("U0012O5AF0", json.product_code);
            Assertions.assertEquals("P", json.product_type);
            Assertions.assertEquals(2, json.company_id);
            Assertions.assertEquals(45.00, json.list_price);
            Assertions.assertEquals(10, json.amount);
            Assertions.assertEquals("100g Pants", json.product);
            Assertions.assertEquals(30.000000, json.price);
            Assertions.assertEquals(List.of(255, 224), json.category_ids);
            Assertions.assertEquals(30.000000, json.base_price);
            Assertions.assertEquals(224, json.main_category);
        }



    }
}
