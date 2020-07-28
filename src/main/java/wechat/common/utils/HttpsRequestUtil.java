package wechat.common.utils;

import javax.net.ssl.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 * https请求工具类
 *
 * @author tianslc
 */
public class HttpsRequestUtil {

    public static final String REQUEST_POST = "POST";
    public static final String REQUEST_GET = "GET";

    /**
     * HTTPS POST请求
     *
     * @param url
     * @param data
     * @return
     */
    public static String httpsPost(String url, byte[] data) {
        return httpsRequest(url, REQUEST_POST, data);
    }

    /**
     * HTTPS GET请求
     *
     * @param url
     * @return
     */
    public static String httpsGet(String url) {
        return httpsRequest(url, REQUEST_GET, null);
    }

    /**
     * 发送HTTPS请求
     *
     * @param url
     * @param method
     * @param data
     * @return
     */
    private static String httpsRequest(String url, String method, byte[] data) {
        StringBuffer stringBuffer = new StringBuffer();
        OutputStream outputStream = null;
        BufferedReader bufferedReader = null;
        try {
            //创建连接
            HttpsURLConnection httpsURLConnection = (HttpsURLConnection) new URL(url).openConnection();
            httpsURLConnection.setSSLSocketFactory(getSSLSocketFactory());
            httpsURLConnection.setDoOutput(true);
            httpsURLConnection.setDoInput(true);
            httpsURLConnection.setUseCaches(false);
            httpsURLConnection.setRequestMethod(method);
            //提交数据
            if (null != data) {
                outputStream = httpsURLConnection.getOutputStream();
                // 注意编码格式，防止中文乱码
                outputStream.write(data);
                outputStream.close();
            }
            // 将返回的输入流转换成字符串
            bufferedReader = new BufferedReader(new InputStreamReader(httpsURLConnection.getInputStream(), "UTF-8"));
            String str = "";
            while ((str = bufferedReader.readLine()) != null) {
                stringBuffer.append(str);
            }
            bufferedReader.close();
            httpsURLConnection.disconnect();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            try {
                if (outputStream != null) {
                    outputStream.close();
                }
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            } catch (IOException e1) {
                e1.printStackTrace();

            }
        }
        return stringBuffer.toString();
    }

    /**
     * 获取SLL通信工厂
     *
     * @return
     * @throws NoSuchProviderException
     * @throws NoSuchAlgorithmException
     * @throws KeyManagementException
     */
    private static SSLSocketFactory getSSLSocketFactory() throws NoSuchProviderException, NoSuchAlgorithmException, KeyManagementException {
        SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
        sslContext.init(null, new TrustManager[]{new CommonX509TrustManager()}, new SecureRandom());
        return sslContext.getSocketFactory();
    }

    /**
     * 通用证书信任管理器
     */
    static class CommonX509TrustManager implements X509TrustManager {

        public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

        }

        public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

        }

        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }
    }

}
