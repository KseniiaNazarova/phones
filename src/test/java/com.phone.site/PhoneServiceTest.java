package com.phone.site;


import java.util.Optional;

import com.phone.site.api.BookPhoneRequest;
import com.phone.site.api.Model;
import com.phone.site.api.Phone;
import com.phone.site.api.PhoneId;
import com.phone.site.api.TechDetails;
import com.phone.site.api.TesterId;
import com.phone.site.core.integration.FonoapiIntegrationService;
import com.phone.site.core.integration.TechDetailsData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles({"test"})
public class PhoneServiceTest
{
    private static final String GET_PHONES_URI = "/api/phones/get";

    private static final String BOOK_PHONE_URI = "/api/phones/book";

    private static final TechDetailsData TECH_DETAILS = TechDetailsData.builder()
            .withTechnology("LTE")
            .withBands2g("band2g")
            .withBands3g("band3g")
            .withBands4g("band4g")
            .build();

    private static final long PHONE_ID = 1L;

    private static final long TESTER_ID = 2L;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private TestManager testManager;

    @MockBean
    private FonoapiIntegrationService fonoapiService;


    @BeforeEach
    public void setUp()
    {
        testManager.clear();
        Mockito.when(fonoapiService.getPhoneTechDetails(Mockito.any())).thenReturn(Optional.of(TECH_DETAILS));
    }


    @Test
    public void should_return_phone_info()
    {
        // given:
        final Phone expected = Phone.builder()
                .withId(PHONE_ID)
                .withModel(Model.builder().withBrand("SAMSUNG")
                                   .withModel("GALAXY S9")
                                   .build())
                .isAvailable(true)
                .withTechDetails(TechDetails.builder()
                                         .withTechnology("LTE")
                                         .withBands2g("band2g")
                                         .withBands3g("band3g")
                                         .withBands4g("band4g")
                                         .build())
                .build();

        // when:
        final ResponseEntity<Phone> response = restTemplate.postForEntity(getUrl(GET_PHONES_URI), PhoneId.of(PHONE_ID), Phone.class);

        // then:
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(expected, response.getBody());
    }


    @Test
    public void should_book_a_phone()
    {
        // given:
        testManager.addTester(TESTER_ID);
        final BookPhoneRequest request = BookPhoneRequest.builder()
                .withPhoneId(PhoneId.of(PHONE_ID))
                .withTesterId(TesterId.of(2L))
                .build();

        // when:
        final ResponseEntity<Void> response = restTemplate.postForEntity(getUrl(BOOK_PHONE_URI),
                                                                         request,
                                                                         Void.class);

        // then:
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertFalse(testManager.isPhoneAvailable(PHONE_ID));
    }


    private String getUrl(final String uri)
    {
        return String.format("http://localhost:%d%s", port, uri);
    }
}
