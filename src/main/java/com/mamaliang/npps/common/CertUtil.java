package com.mamaliang.npps.common;

import org.apache.commons.lang3.ArrayUtils;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.x500.RDN;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x500.style.BCStyle;
import org.bouncycastle.asn1.x500.style.IETFUtils;
import org.bouncycastle.asn1.x509.Certificate;

public class CertUtil {

    public static void parseCert(Certificate certificate) {
        X500Name subject = certificate.getSubject();
        String cn = getRDNValue(subject, BCStyle.CN);
        String c = getRDNValue(subject, BCStyle.C);
        String l0 = getRDNValue(subject, BCStyle.L, 0);
        String l1 = getRDNValue(subject, BCStyle.L, 1);
        String l2 = getRDNValue(subject, BCStyle.L, 2);
        String st = getRDNValue(subject, BCStyle.ST);
        String o0 = getRDNValue(subject, BCStyle.O, 0);
        String o1 = getRDNValue(subject, BCStyle.O, 1);
        String o2 = getRDNValue(subject, BCStyle.O, 2);
        String ou0 = getRDNValue(subject, BCStyle.OU, 0);
        String ou1 = getRDNValue(subject, BCStyle.OU, 1);
        String ou2 = getRDNValue(subject, BCStyle.OU, 2);
        String e = getRDNValue(subject, BCStyle.E);
        String gn = getRDNValue(subject, BCStyle.GIVENNAME);
        String t = getRDNValue(subject, BCStyle.T);
        String surname = getRDNValue(subject, BCStyle.SURNAME);
        // ALIAS

        //String surname = getRDNValue(subject, BCStyle.TELEPHONE_NUMBER);
        // kl.security.pki.x520.Identifiers 对比oid
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
