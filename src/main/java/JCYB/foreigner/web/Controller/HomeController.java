package JCYB.foreigner.web.Controller;

import JCYB.foreigner.web.DTO.culturalSpaceInfo;
import JCYB.foreigner.web.Repository.culturalSpaceInfoRepository;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

@Controller
public class HomeController {

    @Autowired
    private culturalSpaceInfoRepository infoRepository;

    @GetMapping("/api")
    public String index(){
        return "index";
    }

    @PostMapping("/api")
    public String load_save(@RequestParam("ADDR") String ADDR, Model model){
        String result = "";

        try {
            String requestADDR=ADDR;
            URL url = new URL("http://openapi.seoul.go.kr:8088/" + "6678774f626a687437306f584b4846/" +
                    "json/culturalSpaceInfo/1/5/"+requestADDR);
            BufferedReader bf;
            bf = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
            result = bf.readLine();

            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject)jsonParser.parse(result);
            JSONObject culturalSpaceInfo = (JSONObject)jsonObject.get("culturalSpaceInfo");
            Long totalCount=(Long)culturalSpaceInfo.get("list_total_count");

            JSONObject subResult = (JSONObject)culturalSpaceInfo.get("RESULT");
            JSONArray infoArr = (JSONArray) culturalSpaceInfo.get("row");

            for(int i=0;i<infoArr.size();i++){
                JSONObject tmp = (JSONObject)infoArr.get(i);
                culturalSpaceInfo infoObj = new culturalSpaceInfo(i+(long)1, (int)tmp.get("num"),(String)tmp.get("SUBJCODE"),(String)tmp.get("FAC_NAME"),
                        (String)tmp.get("ADDR"), (double)tmp.get("X_COORD"), (double)tmp.get("Y_COORD"),(String)tmp.get("PHNE"), (String)tmp.get("FAX"),
                        (String)tmp.get("HOMEPAGE"), (String)tmp.get("OPENHOUR"),(String)tmp.get("ENTR_FEE"),(String)tmp.get("CLOSEDAY"), (String)tmp.get("OPEN_DAY"),
                        (String)tmp.get("SEAT_CNT"), (String)tmp.get("MAIN_IMG"), (String)tmp.get("ETC_DESC"), (String)tmp.get("FAC_DESC"), (String)tmp.get("ENTRFREE"),
                        (String)tmp.get("SUBWAY"), (String)tmp.get("BUSSTOP"), (String)tmp.get("YELLOW"), (String)tmp.get("GREEN"),
                        (String)tmp.get("BLUE"), (String)tmp.get("RED"), (String)tmp.get("AIRPORT"));
            }

        }catch(Exception e) {
            e.printStackTrace();
        }
        return "redirect:/findname";
    }
}