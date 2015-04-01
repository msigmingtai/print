package model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import commans.basic.BasicPolicyEntity;

@Data
@EqualsAndHashCode(callSuper=false)
public class FirePolicyEntity extends BasicPolicyEntity
{

    private static final long serialVersionUID=-5075672841541613415L;
    private String BANKKEY; // 銀行資料ＫＥＹ
    private String INSDNAME; // 被保險人姓名
    private String IDNO; // 被保險人ＩＤ
    private String PLYNO; // 舊保單號
    private String EXPDAY; // 保單到期日
    private String INSDAMT; // 續保火險保額
    private String FIREPREM; // 續保火險保費
    private String EAMT; // 續保基本地震險保額
    private String EPREM; // 續保基本地震險保費
    private String BANKFLAG; // 銀行貸款還清記號
    private String CLLATM; // 扣款帳號
    private String CLLDAY; // 扣款日期
    private String AREANO; // 通訊郵遞區號
    private String ADDR; // 被保險人通訊地址
    private String TEL; // 被保險人電話
    private String LOCATION; // 標的物地址
    private String AREA; // 郵遞區號
    private String CONSTMARK; // 建築物結構
    private String FLOOR; // 樓層數
    private String CONSTCLASS; // 建築等級
    private String AREASIZE; // 坪數
    private String STRUYEAR; // 建築年份
    private String AGENTUNIT; // 分行別
    private String AGENTEMPNO; // 行員代號
    private String MTG; // 銀行代號
    private String EMPA; // 經手人員一
    private String EMPA1; // 經手人員一檢核碼
    private String STUNIT; // 統計單位
    private String BROKER; // 經紀公司代號
    private String AGENT; // 業務來源
    private String RATE; // 簽單費率
    private String BELONG2; // 統計單位中歸屬
    private String FASRCNAME; // 要保人姓名
    private String FASRIDNO; // 要保人ＩＤ
    private String SEMP1; // 服務人
    private String NEWPLY; // 新保單號
    private String AMT2; // 擴大地震保額
    private String PREM2; // 擴大地震保費
    private String RATE2; // 擴大地震費率
    private String FSEX; // 性別
    private String FBIRTH; // 生日
    private String FEHALF; // 代表人
    private String RELATE; // 關係
    private String SEX; // 要保人性別
    private String BIRTH; // 要保人生日
    private String BEHALF; // 要保人代表人
    private String FLPNO; // G單新種險保單號
    private String UnitName;// 單位中文
    private String AMT7;// 竊盜險保額
    private String PREM7;// 竊盜險保費
    private String RATE7;// 竊盜險費率
    private String AMTV;// 訪客險保額
    private String PREMV;// 訪客險保費
    private String RATEV;// 訪客險費率

}
