package JCYB.foreigner.web.Controller;

import JCYB.foreigner.web.DTO.CulturalSpaceInfo;
import JCYB.foreigner.web.Repository.CulturalSpaceInfoRepository;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Controller
public class CulturalSpaceInfoController {

    private final CulturalSpaceInfoRepository infoRepository;

    @Autowired
    public CulturalSpaceInfoController(CulturalSpaceInfoRepository infoRepository) {
        this.infoRepository = infoRepository;
    }

    @PostMapping("/api")
    public String loadSave() {
        String result = "";

        try {
            URL url = new URL("http://openapi.seoul.go.kr:8088/6678774f626a687437306f584b4846/json/culturalSpaceInfo/1/839/");
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setRequestProperty("Content-type", "application/json");

            BufferedReader bf = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
            result = bf.readLine();

            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(result);
            JSONObject culturalSpaceInfo = (JSONObject) jsonObject.get("culturalSpaceInfo");
            JSONArray infoArr = (JSONArray) culturalSpaceInfo.get("row");

            System.out.println("infoArr size: " + infoArr.size());

            for (int i = 0; i < infoArr.size(); i++) {
                JSONObject tmp = (JSONObject) infoArr.get(i);

                Integer num = (Integer) tmp.get("num");
                num = num == null ? 0 : num;

                Double xCoord;
                if (tmp.get("X_COORD") != null && !"".equals(tmp.get("X_COORD"))) {
                    xCoord = Double.parseDouble((String) tmp.get("X_COORD"));
                } else {
                    xCoord = 0.0;
                }

                Double yCoord;
                if (tmp.get("Y_COORD") != null && !"".equals(tmp.get("Y_COORD"))) {
                    yCoord = Double.parseDouble((String) tmp.get("Y_COORD"));
                } else {
                    yCoord = 0.0;
                }

                CulturalSpaceInfo infoObj = new CulturalSpaceInfo(i + 1L, num, (String) tmp.get("SUBJCODE"), (String) tmp.get("FAC_NAME"),
                        (String) tmp.get("ADDR"), xCoord, yCoord, (String) tmp.get("PHNE"), (String) tmp.get("FAX"), (String) tmp.get("HOMEPAGE"),
                        (String) tmp.get("OPENHOUR"), (String) tmp.get("ENTR_FEE"), (String) tmp.get("CLOSEDAY"), (String) tmp.get("OPEN_DAY"),
                        (String) tmp.get("SEAT_CNT"), (String) tmp.get("MAIN_IMG"), (String) tmp.get("ETC_DESC"), (String) tmp.get("FAC_DESC"),
                        (String) tmp.get("ENTRFREE"), (String) tmp.get("SUBWAY"), (String) tmp.get("BUSSTOP"), (String) tmp.get("YELLOW"),
                        (String) tmp.get("GREEN"), (String) tmp.get("BLUE"), (String) tmp.get("RED"), (String) tmp.get("AIRPORT"));

                infoRepository.save(infoObj);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/end";
    }
}

