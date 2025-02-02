package efr.iv.igr.thriftlimit.controller;

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
@Sql("/db/migration/V2025_02_02__insert_test_data.sql")
@Transactional
@SpringBootTest
@AutoConfigureMockMvc(printOnlyOnFailure = false)
class LimitControllerIT {
    @Autowired
    MockMvc mockMvc;

    @Test
    void getLimits_ReturnListTransactionResponse() throws Exception {
        MockHttpServletRequestBuilder mockHttpServletRequestBuilder =
                MockMvcRequestBuilders.get("/api/v1/limits");

        mockMvc.perform(mockHttpServletRequestBuilder)
                .andExpectAll(
                        MockMvcResultMatchers.status().isOk(),
                        MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON),
                        MockMvcResultMatchers.content().json("""
                                                [
                                                     {
                                                         "id": 1,
                                                         "accountId": 1,
                                                         "sumLimit": 100,
                                                         "category": "Product",
                                                         "limitCurrency": "USD",
                                                         "limitDatetime": "2025-02-01T12:34:56.789Z"
                                                     },
                                                     {
                                                         "id": 2,
                                                         "accountId": 1,
                                                         "sumLimit": 100,
                                                         "category": "Product",
                                                         "limitCurrency": "USD",
                                                         "limitDatetime": "2025-02-01T12:34:56.789Z"
                                                     }
                                                ]
                                """));
    }

    @Test
    void getExceededLimit_ReturnListTransactionExceededResponse() throws Exception {
        MockHttpServletRequestBuilder mockHttpServletRequestBuilder =
                MockMvcRequestBuilders.get("/api/v1/limits/transactions/exceeded");

        mockMvc.perform(mockHttpServletRequestBuilder)
                .andExpectAll(
                        MockMvcResultMatchers.status().isOk(),
                        MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON),
                        MockMvcResultMatchers.content().json("""
                                                [
                                                     {
                                                        transactionResponse: {
                                                            "id": 1,
                                                            "accountFrom": 1,
                                                            "accountTo": 100,
                                                            "currency": "USD",
                                                            "amount": 100,
                                                            "category": "Product",
                                                            "date": "2025-02-01T12:34:56.789Z"
                                                        },
                                
                                                         limitReponse: {
                                                             "id": 1,
                                                             "accountId": 1,
                                                             "sumLimit": 100,
                                                             "category": "Product",
                                                             "limitCurrency": "USD",
                                                             "limitDatetime": "2025-02-01T12:34:56.789Z"
                                                         },
                                
                                                         exceeded: true
                                                     }
                                                ]
                                """));
    }

    @Test
    void createLimit_ReturnLimitResponse() throws Exception {
        MockHttpServletRequestBuilder mockHttpServletRequestBuilder =
                MockMvcRequestBuilders.post("/api/v1/limits")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(("""
                                        {
                                                "accountId": 1,
                                                "sumLimit": 100,
                                                "category": "Product",
                                                "limitCurrency": "USD"
                                        }
                                """));

        mockMvc.perform(mockHttpServletRequestBuilder)
                .andExpectAll(
                        MockMvcResultMatchers.status().isOk(),
                        MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON),
                        MockMvcResultMatchers.content().json("""
                                         {
                                                "accountId": 1,
                                                "sumLimit": 100,
                                                "category": "Product",
                                                "limitCurrency": "USD"
                                        }
                                """)
                );
    }
}
