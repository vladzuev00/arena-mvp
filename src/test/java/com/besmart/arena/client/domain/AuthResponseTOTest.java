package com.besmart.arena.client.domain;

import com.besmart.arena.base.AbstractSpringBootTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;

public final class AuthResponseTOTest extends AbstractSpringBootTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void responseShouldBeSerialized()
            throws Exception {
        AuthResponseTO givenResponse = new AuthResponseTO(
                "hVUfD1Y_BJn01kVPNsBurVWXsmKGu5XCnaDdGSNcjG-HGimDgOnNjCEQXt1zDJmGn9p60JANjpMOM-MTFa1f9fS3EPVu_QzdbmU2GFM2lLB8xvAfiApq_l3VRs1feqv5nCQelFaY6oxnP4FZg_bMCvwhv_-RkiTjUdZgA6sc1VLIlu0T1MxdGEY75sSLGgZ8vb_ETqzPemxOtq449ornzs4nF06chL9ZsSuUzmM_GUE1-6-Yytbb2XoOdlHEovrqWS1rI5-QvGRFlQV6whE9eCfXXT9LbW0b1jIrr6qecoPwlbVsw_-QV45UWmu81CHXYIJU7OaLarb3fG5j1HZhgqsHhe4qDCPr8DZtJ3nIl1bWKJ0ZJIEsumKZgeHVjQcewzInvyPOXMQiTGvzZL2zGRwwLBYCk0BdFby3A6rkk7lv_hVhwru284MR_UTSdO2pUwk022TBtYFyszgEHtnGjHjcBJ_JejL27ogXLfm7FKIem_gXaapg80zg7LACh7eUd5ebq1AYwPpK9RQACVh3_oSAUaIuLs7ywNADl9dwdKlgIjV7N1qw4OF3oPieDLz2"
        );

        String actual = objectMapper.writeValueAsString(givenResponse);
        String expected = """
                {
                  "accessToken": "hVUfD1Y_BJn01kVPNsBurVWXsmKGu5XCnaDdGSNcjG-HGimDgOnNjCEQXt1zDJmGn9p60JANjpMOM-MTFa1f9fS3EPVu_QzdbmU2GFM2lLB8xvAfiApq_l3VRs1feqv5nCQelFaY6oxnP4FZg_bMCvwhv_-RkiTjUdZgA6sc1VLIlu0T1MxdGEY75sSLGgZ8vb_ETqzPemxOtq449ornzs4nF06chL9ZsSuUzmM_GUE1-6-Yytbb2XoOdlHEovrqWS1rI5-QvGRFlQV6whE9eCfXXT9LbW0b1jIrr6qecoPwlbVsw_-QV45UWmu81CHXYIJU7OaLarb3fG5j1HZhgqsHhe4qDCPr8DZtJ3nIl1bWKJ0ZJIEsumKZgeHVjQcewzInvyPOXMQiTGvzZL2zGRwwLBYCk0BdFby3A6rkk7lv_hVhwru284MR_UTSdO2pUwk022TBtYFyszgEHtnGjHjcBJ_JejL27ogXLfm7FKIem_gXaapg80zg7LACh7eUd5ebq1AYwPpK9RQACVh3_oSAUaIuLs7ywNADl9dwdKlgIjV7N1qw4OF3oPieDLz2"
                }""";
        JSONAssert.assertEquals(expected, actual, true);
    }

    @Test
    public void responseShouldBeDeserialized()
            throws Exception {
        String givenJson = """
                {
                    "access_token": "hVUfD1Y_BJn01kVPNsBurVWXsmKGu5XCnaDdGSNcjG-HGimDgOnNjCEQXt1zDJmGn9p60JANjpMOM-MTFa1f9fS3EPVu_QzdbmU2GFM2lLB8xvAfiApq_l3VRs1feqv5nCQelFaY6oxnP4FZg_bMCvwhv_-RkiTjUdZgA6sc1VLIlu0T1MxdGEY75sSLGgZ8vb_ETqzPemxOtq449ornzs4nF06chL9ZsSuUzmM_GUE1-6-Yytbb2XoOdlHEovrqWS1rI5-QvGRFlQV6whE9eCfXXT9LbW0b1jIrr6qecoPwlbVsw_-QV45UWmu81CHXYIJU7OaLarb3fG5j1HZhgqsHhe4qDCPr8DZtJ3nIl1bWKJ0ZJIEsumKZgeHVjQcewzInvyPOXMQiTGvzZL2zGRwwLBYCk0BdFby3A6rkk7lv_hVhwru284MR_UTSdO2pUwk022TBtYFyszgEHtnGjHjcBJ_JejL27ogXLfm7FKIem_gXaapg80zg7LACh7eUd5ebq1AYwPpK9RQACVh3_oSAUaIuLs7ywNADl9dwdKlgIjV7N1qw4OF3oPieDLz2",
                    "token_type": "bearer",
                    "expires_in": 431999,
                    "refresh_token": "7ef4b0c6584c46688b41ae5115495ead",
                    "userName": "siauliuarena@kakava.lt",
                    "client_id": "aaacbda4-08f6-4385-805a-2a3758145471",
                    ".issued": "Sat, 16 Nov 2024 08:19:08 GMT",
                    ".expires": "Thu, 21 Nov 2024 08:19:08 GMT"
                }""";

        AuthResponseTO actual = objectMapper.readValue(givenJson, AuthResponseTO.class);
        AuthResponseTO expected = new AuthResponseTO(
                "hVUfD1Y_BJn01kVPNsBurVWXsmKGu5XCnaDdGSNcjG-HGimDgOnNjCEQXt1zDJmGn9p60JANjpMOM-MTFa1f9fS3EPVu_QzdbmU2GFM2lLB8xvAfiApq_l3VRs1feqv5nCQelFaY6oxnP4FZg_bMCvwhv_-RkiTjUdZgA6sc1VLIlu0T1MxdGEY75sSLGgZ8vb_ETqzPemxOtq449ornzs4nF06chL9ZsSuUzmM_GUE1-6-Yytbb2XoOdlHEovrqWS1rI5-QvGRFlQV6whE9eCfXXT9LbW0b1jIrr6qecoPwlbVsw_-QV45UWmu81CHXYIJU7OaLarb3fG5j1HZhgqsHhe4qDCPr8DZtJ3nIl1bWKJ0ZJIEsumKZgeHVjQcewzInvyPOXMQiTGvzZL2zGRwwLBYCk0BdFby3A6rkk7lv_hVhwru284MR_UTSdO2pUwk022TBtYFyszgEHtnGjHjcBJ_JejL27ogXLfm7FKIem_gXaapg80zg7LACh7eUd5ebq1AYwPpK9RQACVh3_oSAUaIuLs7ywNADl9dwdKlgIjV7N1qw4OF3oPieDLz2"
        );
        Assertions.assertEquals(expected, actual);
    }
}
