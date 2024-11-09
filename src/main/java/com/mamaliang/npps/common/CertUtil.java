package com.mamaliang.npps.common;

import io.vertx.core.json.JsonObject;
import org.apache.commons.lang3.ArrayUtils;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.x500.RDN;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x500.style.BCStyle;
import org.bouncycastle.asn1.x500.style.IETFUtils;
import org.bouncycastle.asn1.x509.Certificate;
import org.bouncycastle.util.encoders.Hex;

import java.io.IOException;
import java.math.BigInteger;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class CertUtil {

    // todo 对比生成的数据
    public static JsonObject parseCert(String certPem) throws IOException {
        Certificate cert = PemUtil.pem2Cert(certPem);
        JsonObject certInfo = JsonObject.of();
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss");
        certInfo.put("KOAL_NOT_BEFORE", cert.getStartDate().getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime().format(dateFormat));
        certInfo.put("KOAL_NOT_AFTER", cert.getEndDate().getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime().format(dateFormat));
        BigInteger serialNumber = cert.getSerialNumber().getValue();
        certInfo.put("KOAL_CERT_SERIAL_NUMBER", serialNumber);
        certInfo.put("KOAL_CERT_SERIAL_NUMBER_HEX", Hex.toHexString(serialNumber.toByteArray()).toUpperCase());
        certInfo.put("KOAL_CERT", certPem);
        X500Name subject = cert.getSubject();
        certInfo.put("KOAL_CERT_DN", subject.toString());
        certInfo.put("KOAL_CERT_CN",  getRDNValue(subject, BCStyle.CN));
        certInfo.put("KOAL_CERT_C", getRDNValue(subject, BCStyle.C));
        certInfo.put("KOAL_CERT_L", getRDNValue(subject, BCStyle.L, 0));
        certInfo.put("KOAL_CERT_L1", getRDNValue(subject, BCStyle.L, 1));
        certInfo.put("KOAL_CERT_L2", getRDNValue(subject, BCStyle.L, 2));
        certInfo.put("KOAL_CERT_ST", getRDNValue(subject, BCStyle.ST));
        certInfo.put("KOAL_CERT_O", getRDNValue(subject, BCStyle.O, 0));
        certInfo.put("KOAL_CERT_O1", getRDNValue(subject, BCStyle.O, 1));
        certInfo.put("KOAL_CERT_O2", getRDNValue(subject, BCStyle.O, 2));
        certInfo.put("KOAL_CERT_OU", getRDNValue(subject, BCStyle.OU, 0));
        certInfo.put("KOAL_CERT_OU1", getRDNValue(subject, BCStyle.OU, 1));
        certInfo.put("KOAL_CERT_OU2", getRDNValue(subject, BCStyle.OU, 2));
        certInfo.put("KOAL_CERT_E", getRDNValue(subject, BCStyle.E));
        certInfo.put("KOAL_CERT_GN", getRDNValue(subject, BCStyle.GIVENNAME));
        certInfo.put("KOAL_CERT_G", "");
        certInfo.put("KOAL_CERT_T", getRDNValue(subject, BCStyle.T));
        certInfo.put("KOAL_CERT_S", getRDNValue(subject, BCStyle.SURNAME));
        certInfo.put("KOAL_CERT_ISSUER_CN", "");
        certInfo.put("KOAL_CERT_ISSUER_O", "");
        certInfo.put("KOAL_CERT_ALIAS", "");
        certInfo.put("KOAL_CERT_PHONE", "");
        certInfo.put("KOAL_CERT_ALTNAME", "");
        certInfo.put("KOAL_CERT_EXT_WORK", "");
        certInfo.put("KOAL_CERT_EXT_LEVEL", "");
        certInfo.put("KOAL_CERT_EXT_CUSTOM", "");
        certInfo.put("BJCA_CERT_EXT_TEST_USER_ID", "");
        certInfo.put("BJCA_CERT_EXT_USER_ID", "");
        certInfo.put("SHECA_CERT_EXT_144", "");
        certInfo.put("SHECA_CERT_EXT_145", "");
        certInfo.put("SHECA_CERT_EXT_146", "");
        certInfo.put("SHECA_CERT_EXT_147", "");
        certInfo.put("SHECA_CERT_EXT_148", "");
        certInfo.put("SHECA_CERT_EXT_151", "");
        certInfo.put("SHECA_CERT_EXT_167", "");

        return certInfo;
    }

    public static String getRDNValue(X500Name dn, ASN1ObjectIdentifier oid) {
        return getRDNValue(dn, oid, 0);
    }

    public static String getRDNValue(X500Name dn, ASN1ObjectIdentifier oid, int index) {
        index = Math.max(index, 0);
        RDN[] rdNs = dn.getRDNs(oid);
        if (ArrayUtils.isEmpty(rdNs) || index >= rdNs.length) {
            return null;
        }
        RDN rdN = rdNs[index];
        ASN1Encodable value = rdN.getFirst().getValue();
        return IETFUtils.valueToString(value);
    }

}



