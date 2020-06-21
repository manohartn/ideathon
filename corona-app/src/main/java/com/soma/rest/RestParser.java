package com.soma.rest;

import java.io.IOException;
import java.util.Scanner;
import java.net.*;

public class RestParser {
	
	public String getIndCaronaDts(String urlInfo){
		HttpURLConnection conn = null;
		String inline = "";
		try{
			URL url = new URL(urlInfo);
			conn = (HttpURLConnection)url.openConnection();
			conn.connect();
			int responseCode = conn.getResponseCode();
			if (responseCode != 200)
			throw new RuntimeException("HttpResponseCode: "+responseCode);
			else
			{
				Scanner sc = new Scanner(url.openStream());
				while(sc.hasNext()){
					inline += sc.nextLine();
				}
				sc.close();
			}
		} catch (IOException e){
			e.printStackTrace();
		} finally {
			if (conn != null)
			{
				conn.disconnect();
			}
		}
		return inline;
	}

	public static void main(String[] args) {
		RestParser rstParser = new RestParser();
		String indCovidData = rstParser.getIndCaronaDts("https://api.covid19india.org/raw_data1.json");
		System.out.println(indCovidData);
	}

}
