

import java.io.BufferedReader;

import java.io.InputStreamReader;

import java.net.URL;

public class GetTest1 {

	public static void main(String[] args) {

		try {

			String urlstr = "https://api.openweathermap.org/data/2.5/weather?lat=44.34&lon=10.99&appid=ee82ccb182bb4db853249cd84cd42708";

			URL url = new URL(urlstr);

			BufferedReader bf;

			String line;

			String result = "";

			// 날씨 정보를 받아온다.

			bf = new BufferedReader(new InputStreamReader(url.openStream()));

			// 버퍼에 있는 정보를 문자열로 변환.

			while ((line = bf.readLine()) != null) {

				result += result.concat(line);

				System.out.println(line);

			}

		} catch (Exception e) {

			e.printStackTrace();

		}
	}
}