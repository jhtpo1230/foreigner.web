package JCYB.foreigner.web.DTO;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class culturalSpaceInfo {
    @Id //PK
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int num; //번호
    private String SUBJCODE; //주제분류
    private String FAC_NAME; //문화시설명
    private String ADDR; //주소
    private double X_COORD; //위도
    private double Y_COORD; //경도
    private String PHNE; //전화번호
    private String FAX; //팩스번호
    private String HOMEPAGE; //홈페이지
    private String OPENHOUR; //관람시간
    private String ENTR_FEE; //관람료
    private String CLOSEDAY; //휴관일
    private String OPEN_DAY; //개관일자
    private String SEAT_CNT; //객석수
    private String MAIN_IMG; //대표이미지
    private String ETC_DESC; //기타사항
    private String FAC_DESC; //시설소개
    private String ENTRFREE; //무료구분
    private String SUBWAY; //지하철
    private String BUSSTOP; //버스정거장
    private String YELLOW; //YELLOW
    private String GREEN; //GREEN
    private String BLUE; //BLUE
    private String RED; //RED
    private String AIRPORT; //공항버스
    protected culturalSpaceInfo(){}
    public culturalSpaceInfo(Long id, int num, String SUBJCODE, String FAC_NAME, String ADDR, double x_COORD, double y_COORD, String PHNE, String FAX, String HOMEPAGE, String OPENHOUR, String ENTR_FEE, String CLOSEDAY, String OPEN_DAY, String SEAT_CNT, String MAIN_IMG, String ETC_DESC, String FAC_DESC, String ENTRFREE, String SUBWAY, String BUSSTOP, String YELLOW, String GREEN, String BLUE, String RED, String AIRPORT) {
        this.id = id;
        this.num = num;
        this.SUBJCODE = SUBJCODE;
        this.FAC_NAME = FAC_NAME;
        this.ADDR = ADDR;
        X_COORD = x_COORD;
        Y_COORD = y_COORD;
        this.PHNE = PHNE;
        this.FAX = FAX;
        this.HOMEPAGE = HOMEPAGE;
        this.OPENHOUR = OPENHOUR;
        this.ENTR_FEE = ENTR_FEE;
        this.CLOSEDAY = CLOSEDAY;
        this.OPEN_DAY = OPEN_DAY;
        this.SEAT_CNT = SEAT_CNT;
        this.MAIN_IMG = MAIN_IMG;
        this.ETC_DESC = ETC_DESC;
        this.FAC_DESC = FAC_DESC;
        this.ENTRFREE = ENTRFREE;
        this.SUBWAY = SUBWAY;
        this.BUSSTOP = BUSSTOP;
        this.YELLOW = YELLOW;
        this.GREEN = GREEN;
        this.BLUE = BLUE;
        this.RED = RED;
        this.AIRPORT = AIRPORT;
    }
}