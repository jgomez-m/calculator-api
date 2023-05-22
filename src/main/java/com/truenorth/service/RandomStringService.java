package com.truenorth.service;

import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RandomStringService {
    private static final String RANDOM_ORG_API_URL = "http://www.random.org/integers/";
    public List<String> tmpArrayListInt;
    public List<String> tmpArrayListString;
    public String [] tmpArrayString;

    /**
     * httpGet() returns the contents of a get line by line in an ArrayList
     * @param url String of url to get from
     * @return String ArrayList
     * @throws Exception
     */
    public List<String> httpGet(String url) throws Exception{
        tmpArrayListString = new ArrayList();

        HttpParams httpParams = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(httpParams, 1000);
        HttpConnectionParams.setSoTimeout(httpParams, 1000);
        HttpClient httpclient = new DefaultHttpClient(httpParams);
        HttpGet httpget = new HttpGet(url);
        ResponseHandler<String> responseHandler = new BasicResponseHandler();

        String responseBody = httpclient.execute(httpget, responseHandler);
        httpclient.getConnectionManager().shutdown();

        tmpArrayString = responseBody.split("\n");
        int arrayCount = tmpArrayString.length;

        for (int i = 0;i < arrayCount;i++){
            tmpArrayListString.add(tmpArrayString[i]);
        }
        return tmpArrayListString;
    }

    public List<String> randomNumberBaseTenInt(int amountOfNumbersToReturn,int minNumber, int maxNumber) throws Exception {
        String url = RANDOM_ORG_API_URL + "?num="+ amountOfNumbersToReturn +"&min="+ minNumber +"&max="+ maxNumber +"&col=1&base=10&format=plain&rnd=new";
        tmpArrayListString = httpGet(url);
        return tmpArrayListString;
    }

    public static void main(String[] args) {
        RandomStringService random = new RandomStringService();
        List<String> tmpArray = new ArrayList<>();

        System.out.println("randomNumberBaseTenInt()");
        try {
            tmpArray = random.randomNumberBaseTenInt(5, 1, 100);
        } catch (Exception e) {
            e.printStackTrace();
        }

        int arrayCount = tmpArray.size();
        System.out.println("arrayCount="+arrayCount);

        for (int i=0; i < arrayCount; i++){
            System.out.println("Array Index " + i + ": " +tmpArray.get(i));
        }
    }

}

