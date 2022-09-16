import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
/*
 lat/lon :위도 경도
 appid : API KEY
 lang : 언어설정
 */
public class GetWeather2 {
    public static void main(String[] args) {
        try{
            Scanner in = new Scanner(System.in);
            System.out.print("어떤 도시의 날씨를 알고 싶습니까?");
            String city = in.next();
         
            //OpenAPI call하는 URL
            String urlstr = "http://api.openweathermap.org/data/2.5/weather?"
                        + "q=" + city 
                        +"&appid=ee82ccb182bb4db853249cd84cd42708\r\n"
                        + "";
            
           
            
            URL url = new URL(urlstr);
            BufferedReader bf;
            String line;
            String result = "";

            //날씨 정보를 받아오기
            bf = new BufferedReader(new InputStreamReader(url.openStream()));

            //버퍼에 있는 정보를 문자열로 변환.
            while((line = bf.readLine()) != null){
                result = result.concat(line);
                //System.out.println(line);
            }

            //문자열을 JSON으로 파싱
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObj = (JSONObject) jsonParser.parse(result);

            //지역 출력
            System.out.println("지역 : " + jsonObj.get("name"));

            //날씨 출력
            JSONArray weatherArray = (JSONArray) jsonObj.get("weather");
            JSONObject obj = (JSONObject) weatherArray.get(0);
            System.out.println("날씨 : "+obj.get("main"));

            //온도 출력(절대온도라서 변환 필요)
            JSONObject mainArray = (JSONObject) jsonObj.get("main");
            double ktemp = Double.parseDouble(mainArray.get("temp").toString());
            double temp = ktemp-273.15;
            System.out.printf("온도 : %.2f\n",temp);

            bf.close();
            
        }catch(Exception e){
            //System.out.println(e.getMessage());
            System.out.println("지원하지 않거나 없는 도시입니다.");
            
        }
    }
}