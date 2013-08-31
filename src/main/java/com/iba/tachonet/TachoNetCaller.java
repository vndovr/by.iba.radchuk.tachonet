package com.iba.tachonet;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.rmi.RemoteException;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.namespace.QName;
import javax.xml.rpc.Call;
import javax.xml.rpc.Service;
import javax.xml.rpc.ServiceException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import com.iba.tachonet.message.ThreadPool;
import com.iba.tachonet.message.XMLSchemaValidator;
import com.iba.tachonet.utils.MessageUtils;
import com.iba.tachonet.utils.Resources;

/**
 * @author Vladimir Radchuk
 * 
 */
public final class TachoNetCaller {

    private static final Log log = LogFactory.getLog(TachoNetCaller.class);

    /**
     * Default constructor
     */
    private TachoNetCaller() {
        super();
    }

    /**
     * Sends the message to TachoNet
     * 
     * @return The Response code
     * @param message
     * @throws JAXBException
     * @throws IOException
     * @throws ClientProtocolException
     */
    public static final int call(JAXBElement<? extends OutgoingMessage> message)
            throws JAXBException, ClientProtocolException, IOException {
        String xml = MessageUtils.marshall(message);
        String validationResult = new XMLSchemaValidator().validate(xml);

        if (validationResult != null) {
            log.error("TachoNetCaller.call()" + " outgoing xml is not valid: "
                    + validationResult);
            return HttpURLConnection.HTTP_BAD_REQUEST;
        }

        HttpPost post = new HttpPost(Resources.getTCNMessageHandlerAddress());
        StringEntity se = new StringEntity(xml, "utf-8");
        post.setEntity(se);
        HttpResponse result = new DefaultHttpClient().execute(post);

        // TODO: check status code and throws an exception in some cases

        // We log message only in case call to TachoNet did not fail
        if (Resources.isSystemLogOutgoingMessagesEnabled())
            ThreadPool.logXmlMessage(xml, Resources
                    .getTCNRequestHeaderReceiver());

        log.info("call() HTTP Status code  ["
                + result.getStatusLine().getStatusCode() + "] with message ["
                + result.getStatusLine().getReasonPhrase() + "]");

        return result.getStatusLine().getStatusCode();
    }

    /**
     * @param surName
     * @param firstName
     * @return
     * @throws RemoteException
     * @throws MalformedURLException
     * @throws ServiceException
     */
    public static final String[] getPhonexSearchKeys(String surName,
            String firstName) throws RemoteException, MalformedURLException,
            ServiceException {
        Service service = new org.apache.axis.client.Service();
        Call call = (Call) service.createCall();

        call.setTargetEndpointAddress(Resources
                .getTCNWSEndPointGetPhonexSearchKeysService());
        call.setOperationName(new QName("EU.Cec.Tren.Tcn.WebServices",
                "GetPhonexSearchKeys"));

        return (String[]) call.invoke(new Object[] { surName, firstName });
    }

    /**
     * @param surName
     * @param firstName
     * @param placeOfBirth
     * @param drivingLicenseNumber
     * @return
     * @throws MalformedURLException
     * @throws ServiceException
     * @throws RemoteException
     */
    public static final String[] transliterateToUSAscii(String surName,
            String firstName, String placeOfBirth, String drivingLicenseNumber)
            throws MalformedURLException, ServiceException, RemoteException {
        Service service = new org.apache.axis.client.Service();
        Call call = (Call) service.createCall();

        call.setTargetEndpointAddress(Resources
                .getTCNWSEndPointTransliterateToUSAscii());
        call.setOperationName(new QName("EU.Cec.Tren.Tcn.WebServices",
                "TransliterateToUSAscii"));

        return (String[]) call.invoke(new Object[] { surName, firstName,
                placeOfBirth, drivingLicenseNumber });
    }

    static {
        TrustManager[] trustAllCerts = { new X509TrustManager() {
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return null;
            }

            public void checkClientTrusted(
                    java.security.cert.X509Certificate[] certs, String authType) {
            }

            public void checkServerTrusted(
                    java.security.cert.X509Certificate[] certs, String authType) {
            }
        } };

        try {
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection
                    .setDefaultSSLSocketFactory(sc.getSocketFactory());
        } catch (Exception e) {
            log.error("Error installing TrustManager", e);
        }
    }
}
