package com.mamaliang.npps.common;

import io.vertx.core.json.JsonObject;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class CertUtilTest {


    @Test
    void testParseCert() throws IOException {
        String certPem= """
                -----BEGIN CERTIFICATE-----
                MIIEEDCCAvigAwIBAgIMSeoAAAAAC+vjc6prMA0GCSqGSIb3DQEBCwUAMIGJMQsw
                CQYDVQQGEwJDTjEPMA0GA1UECAwG5LiK5rW3MRIwEAYDVQQHDAnkuIrmtbfluIIx
                LTArBgNVBAoMJOS4iua1t+agvOWwlOi9r+S7tuiCoeS7veaciemZkOWFrOWPuDEV
                MBMGA1UECwwM5qC85bCU6L2v5Lu2MQ8wDQYDVQQDDAZLb2FsQ2EwHhcNMjEwMzMx
                MTYwMDAwWhcNMjYwNDAxMTU1OTU5WjBTMQswCQYDVQQGEwJDTjEVMBMGA1UECgwM
                5qC85bCU6L2v5Lu2MRwwGgYJKoZIhvcNAQkBFg1nYW9mQGtvYWwuY29tMQ8wDQYD
                VQQDDAbpq5jls7AwggEiMA0GCSqGSIb3DQEBAQUAA4IBDwAwggEKAoIBAQC9ewti
                6+vy+o44kgv3w4U51/ee1IEFDJwQaVg5sJC0dTFR8MeptKJYZ2pcogEzvkYuXBuH
                fK93DCRNxBoZBdbOn9FZtw4j56u+pohrElR2NNl9HZ/HS+8jFeWzBlPvd/SCD9Ef
                WjNr1gxkLmDZBaly+Z2IhLgMDPpFEqbzK9mOZmzOnKqJAAYfG87L2C+p/fZVFEHI
                8/0pwBVL+9uywpj8wCqhCpXg4gSlHBERhdVBvP9+X4i3mnPVocwzZi78/8NgwzJE
                gG/0tET3q22wu9ub8slyMkHUn3okRBASH5owaD4L42FizmxPUhOGrJLK5MmFKaES
                c3fpdTWQUux9pSJ3AgMBAAGjgawwgakwDAYDVR0TBAUwAwEBADAfBgNVHSUEGDAW
                BggrBgEFBQcDBAYKKwYBBAGCNxQCAjALBgNVHQ8EBAMCADAwKwYJKwYBBAGCNxQC
                BB4eHABTAG0AYQByAHQAYwBhAHIAZABMAG8AZwBvAG4wHwYDVR0jBBgwFoAU19j8
                SB4iZlPUQdMnWtwMvzt0aaMwHQYDVR0OBBYEFK59Krv4saJYzatwdxgbUq3NUOZJ
                MA0GCSqGSIb3DQEBCwUAA4IBAQAA8bSX1h+VRohvByjULbULA9xiAJRVGQunEpGM
                cqORng+u+3r1qqkIt6BhjMi8AxD0qYyDqXtZQBAbfjaHExGpVkUSs10aA+krKSCU
                BUsvx/3jEbS0tRPEzSFW4lmt7FZCzpI+FYFNC+vNf5cJKATTYjtIREShI3ZGglwX
                M6aa3y6hE3gI31St6386BXWECsX7CFJBc7XGvUl7W8/AgBjL/UEjkZO2pbOzX86W
                PpyVRnez1GPWl5UZIbv2cxay4TAipv8ZJI5J1M4aqfbBV3bt7fz5f2PudHj5zM8O
                sUjgaXNz2xwdlC9Vi89TRKV8dJiYSHPlwhN29l3lOo3eC2Cb
                -----END CERTIFICATE-----
                """;
        JsonObject entries = CertUtil.parseCert(certPem);
        System.out.println("1");
    }
}