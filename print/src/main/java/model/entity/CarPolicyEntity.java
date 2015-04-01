package model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import commans.basic.BasicPolicyEntity;

@Data
@EqualsAndHashCode(callSuper=false)
public class CarPolicyEntity extends BasicPolicyEntity
{
    /**
     * 
     */
    private static final long serialVersionUID=-2644841559329102583L;
    private String WORK_CARD_NO; // 保証號
    private String WORK_CARD_TAG; // 尾號
    private String WORK_POLICY_NO; // 保單號
    private String WORK_ASR_NAME; // 要保人
    private String WORK_ASR_ID; // 要保人ＩＤ
    private String WORK_ASR_TEL; // 要保人電話
    private String WORK_ASR_AREA; // 要保人郵遞區號
    private String WORK_ASR_ADDR; // 要保人地址
    private String WORK_INSURED; // 被保險人
    private String WORK_TEL; // 電話
    private String WORK_ADDRESS; // 地址
    private String WORK_MAILNO; // 郵遞區號
    private String WORK_BENEF; // 受益人
    private String WORK_EFFECT_DAY; // 生效日 /年月日
    private String WORK_EXPIRE_DAY; // 到期日 /年月日
    private String WORK_PSGER; // 載客人數
    private String WORK_BODY_CODE; // 車種代號
    private String WORK_ORGN_LISN_D; // 原始發照年月+1
    private String WORK_PLATE_NO; // 牌照號碼
    private String WORK_BRAND_CODE_8; // 廠牌車型代號
    private String WORK_CC; // 排氣量
    private String WORK_BUILT_YEAR; // 製造年份
    private String WORK_ENGINE; // 引擎號碼
    private String WORK_REPLACE_VALUE; // 重置價格
    private String WORK_DRIVER_ID_NO; // 主駛人駕照號碼
    private String WORK_DRIVER_BIRTH; // 主駛人出生日 /年月日
    private String WORK_DRIVER_SEX; // 主駛人性別
    private String WORK_DRIVER_MARRIAGE; // 主駛人婚姻
    private String WORK_PD_RATE; // 車體費率代號
    private String WORK_THF_RATE; // 竊盜費率代號
    private String WORK_SEX_AGE; // 年齡性別係數
    private String WORK_1_KIND_CODE; // １-36險種
    private String WORK_1_PD_NAME; // １-37險種
    private String WORK_1_PD_AMT_S; // １-38保額
    private String WORK_1_PD_AMT_FILE; // １-38保額
    private String WORK_1_INSU_AMT; // １-38保額
    private String WORK_1_DEDUC; // １-39自負額
    private String WORK_1_PREM; // １-40保費
    private String WORK_1_PREM_C; // １-40保費覆核
    private String WORK_2_INSU_AMT; // ２-42保額
    private String WORK_2_DEDUC; // ２-43自負額
    private String WORK_2_PREM; // ２-44保費
    private String WORK_2_PREM_C; // ２-45保費覆核
    private String WORK_3_TPL_OLD; // ３-46等等級
    private String WORK_3_TPL_NEW; // ３-46新等級
    private String WORK_3_AMT_1; // ３-46保額傷
    private String WORK_3_INSU_AMT; // ３-47保額總
    private String WORK_3_PREM; // ３-48保費
    private String WORK_3_PREM_C; // ３-49保費覆核
    private String WORK_4_INSU_AMT; // ４-50保額
    private String WORK_4_PREM; // ４-51保費
    private String WORK_4_PREM_C; // ４-52保費覆核
    private String WORK_5_PREM; // ５-53保費
    private String WORK_5_PREM_C; // ５-54保費覆核
    private String WORK_6_AMT_1; // ６-55保額傷
    private String WORK_6_INSU_AMT; // ６-56保額總
    private String WORK_6_AMT_2; // ６-59保額總
    private String WORK_6_PREM; // ６-57保費
    private String WORK_6_PREM_C; // ６-58保費覆核
    private String WORK_7_AMT_1; // ７-60保額傷
    private String WORK_7_INSU_AMT; // ７-61保額總
    private String WORK_7_AMT_D; // ７-64保額總
    private String WORK_7_AMT_DAY; // ７-64保額總
    private String WORK_7_PREM; // ７-62保費
    private String WORK_7_PREM_C; // ７-63保費覆核
    private String WORK_A_KIND; // 其他險種
    private String WORK_A_KIND_N; // 其他險種中文
    private String WORK_A_KIND_N1; // 其他險種中文
    private String WORK_A_AMT; // 其他保額
    private String WORK_A_DEDUC; // 其他自負額
    private String WORK_A_PREM; // 其他保費 　
    private String WORK_A_PREM_C; // 其他庥費覆核
    private String WORK_B_KIND; // 其他險種
    private String WORK_B_KIND_N; // 其他險種中文
    private String WORK_B_KIND_N1; // 其他險種中文
    private String WORK_B_AMT; // 其他保額
    private String WORK_B_DEDUC; // 其他自負額
    private String WORK_B_PREM; // 其他保費
    private String WORK_B_PREM_C; // 其他庥費覆核
    private String WORK_C_KIND; // 其他險種
    private String WORK_C_KIND_N; // 其他險種中文
    private String WORK_C_KIND_N1; // 其他險種中文
    private String WORK_C_AMT; // 其他保額
    private String WORK_C_DEDUC; // 其他自負額
    private String WORK_C_PREM; // 其他保費
    private String WORK_C_PREM_C; // 其他庥費覆核
    private String WORK_D_KIND; // 其他險種
    private String WORK_D_KIND_N; // 其他險種中文
    private String WORK_D_KIND_N1; // 其他險種中文
    private String WORK_D_AMT; // 其他保額
    private String WORK_D_DEDUC; // 其他自負額
    private String WORK_D_PREM; // 其他保費
    private String WORK_D_PREM_C; // 其他庥費覆核
    private String WORK_E_KIND; // 其他險種
    private String WORK_E_KIND_N; // 其他險種中文
    private String WORK_E_KIND_N1; // 其他險種中文
    private String WORK_E_AMT; // 其他保額
    private String WORK_E_DEDUC; // 其他自負額
    private String WORK_E_PREM; // 其他保費
    private String WORK_E_PREM_C; // 其他庥費覆核
    private String WORK_F_KIND; // 其他險種
    private String WORK_F_KIND_N; // 其他險種中文
    private String WORK_F_KIND_N1; // 其他險種中文
    private String WORK_F_AMT; // 其他保額
    private String WORK_F_DEDUC; // 其他自負額
    private String WORK_F_PREM; // 其他保費
    private String WORK_F_PREM_C; // 其他庥費覆核
    private String WORK_G_KIND; // 其他險種
    private String WORK_G_KIND_N; // 其他險種中文
    private String WORK_G_KIND_N1; // 其他險種中文
    private String WORK_G_AMT; // 其他保額
    private String WORK_G_DEDUC; // 其他自負額
    private String WORK_G_PREM; // 其他保費
    private String WORK_G_PREM_C; // 其他庥費覆核
    private String WORK_H_KIND; // 其他險種
    private String WORK_H_KIND_N; // 其他險種中文
    private String WORK_H_KIND_N1; // 其他險種中文
    private String WORK_H_AMT; // 其他保額
    private String WORK_H_DEDUC; // 其他自負額
    private String WORK_H_PREM; // 其他保費
    private String WORK_H_PREM_C; // 其他庥費覆核
    private String WORK_TOT_PREM; // 任意險合計保費
    private String WORK_TOT_1_PREM; // 覆核任意保費
    private String WORK_CI_CARD_NO; // 強制証號
    private String WORK_CI_NEW_RANK; // 強制新等級
    private String WORK_CI_PREM; // 強制保費
    private String WORK_TOT_ALL_PREM; // 合計總保費
    private String WORK_CI_EFFECT_DATE; // 強制生效日 年/月/日
    private String WORK_CI_EXPIRE_DATE; // 強制到期日 年/月/日
    private String WORK_CI_SERIAL_NO; // 關貿強制查詢序號
    private String WORK_AGENT_UNIT; // 銀行分行別
    private String WORK_AGENT_EMP_NO; // 銀行員工
    private String WORK_STATIC_UNIT; // 統計單位
    private String WORK_EMP_NO; // 經手人
    private String WORK_S_EMP; // 服務人
    private String WORK_BROKER; // 代理商代號
    private String WORK_CHANNEL_TYPE; // 通路記號 E
    private String WORK_SYS_DAY; // 收件單位及日期D
    private String WORK_PD_N_LOSS_PT; // 車體加減費級數 C
    private String WORK_TPL_NEW_RANK; // 責任新等級 B
    private String WORK_VI_SERIAL_NO; // 關貿任意查詢序號
    private String WORK_PD_LOSS_RATE; // 賠款次數車體
    private String WORK_TPL_LOSS_RATE; // 賠款次數責任
}
