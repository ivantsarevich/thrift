package efr.iv.igr.thriftbank.controller;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@Disabled
@Sql("/db/migration/V2025_02_01__insert_test_data.sql")
@Transactional
@SpringBootTest
@AutoConfigureMockMvc(printOnlyOnFailure = false)
class TransactionRestControllerIT {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void getTransactions_ReturnValidTransactionResponseEntity() throws Exception {
        MockHttpServletRequestBuilder mockHttpServletRequestBuilder =
                MockMvcRequestBuilders.get("/api/v1/transactions");

        mockMvc.perform(mockHttpServletRequestBuilder)
                .andExpectAll(
                        MockMvcResultMatchers.status().isOk(),
                        MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON),
                        MockMvcResultMatchers.content().json("""
                                                [
                                                     {
                                                         "id": 1,
                                                         "accountFrom": 1,
                                                         "accountTo": 1,
                                                         "currency": "USD",
                                                         "amount": 200,
                                                         "category": "Product",
                                                         "date": "2025-02-01T12:34:56.789Z"
                                                     }
                                                ]
                                """));
    }

    @Test
    void createTransaction_ReturnValidTransactionResponseEntity() throws Exception {
        MockHttpServletRequestBuilder mockHttpServletRequestBuilder =
                MockMvcRequestBuilders.post("/api/v1/transactions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(("""
                                        {
                                              "accountFrom": 1,
                                              "accountTo": 2,
                                              "currency": "USD",
                                              "amount": 200,
                                              "category": "Product"
                                        }
                                """));

        mockMvc.perform(mockHttpServletRequestBuilder)
                .andExpectAll(
                        MockMvcResultMatchers.status().isOk(),
                        MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON),
                        MockMvcResultMatchers.content().json("""
                                        {
                                              "accountFrom": 1,
                                              "accountTo": 2,
                                              "currency": "USD",
                                              "amount": 200,
                                              "category": "Product"
                                        }
                                """)
                );
    }
}
