package model.policy;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import model.entity.CarPolicyEntity;
import model.entity.FirePolicyEntity;

import commans.basic.BasicPolicy;
import commans.function.ChangeCode;

public class CarPolicy extends BasicPolicy<CarPolicyEntity>
{
    private static final long serialVersionUID=1L;

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public List getPolicy(String files) throws IOException
    {	
	inFile=new InputStreamReader(new FileInputStream(files),"ISO8859_1");
	brFile=new BufferedReader(inFile);
	while ((Line=brFile.readLine())!=null)
	{
	    CarPolicyEntity ply=new CarPolicyEntity();
	    ply.setWORK_CARD_NO(Line.substring(0,8)); // 保証號
	    ply.setWORK_CARD_TAG(Line.substring(8,10)); // 尾號
	    ply.setWORK_POLICY_NO(Line.substring(10,19)); // 保單號
	    ply.setWORK_ASR_NAME(new ChangeCode().ChgChCode(Line.substring(19,49),"")); // 要保人
	    ply.setWORK_ASR_ID(Line.substring(49,59)); // 要保人ＩＤ
	    ply.setWORK_ASR_TEL(Line.substring(59,69)); // 要保人電話
	    ply.setWORK_ASR_AREA(Line.substring(69,72)); // 要保人郵遞區號
	    ply.setWORK_ASR_ADDR(new ChangeCode().ChgChCode(Line.substring(72,132),"")); // 要保人地址
	    ply.setWORK_INSURED(new ChangeCode().ChgChCode(Line.substring(132,162),"")); // 被保險人
	    ply.setWORK_TEL(Line.substring(162,172)); // 電話
	    ply.setWORK_ADDRESS(new ChangeCode().ChgChCode(Line.substring(172,232),"")); // 地址
	    ply.setWORK_MAILNO(Line.substring(232,235)); // 郵遞區號
	    ply.setWORK_BENEF(new ChangeCode().ChgChCode(Line.substring(235,265),"")); // 受益人
	    ply.setWORK_EFFECT_DAY(Line.substring(265,272)); // 生效日 /年月日
	    ply.setWORK_EXPIRE_DAY(Line.substring(272,279)); // 到期日 /年月日
	    ply.setWORK_PSGER(Line.substring(279,281)); // 載客人數
	    ply.setWORK_BODY_CODE(Line.substring(281,283)); // 車種代號
	    ply.setWORK_ORGN_LISN_D(Line.substring(283,288)); // 原始發照年月+1
	    ply.setWORK_PLATE_NO(Line.substring(288,297)); // 牌照號碼
	    ply.setWORK_BRAND_CODE_8(Line.substring(297,305)); // 廠牌車型代號
	    ply.setWORK_CC(Line.substring(305,310)); // 排氣量
	    ply.setWORK_BUILT_YEAR(Line.substring(310,312)); // 製造年份
	    ply.setWORK_ENGINE(Line.substring(312,332)); // 引擎號碼
	    ply.setWORK_REPLACE_VALUE(Line.substring(332,342)); // 重置價格
	    ply.setWORK_DRIVER_ID_NO(Line.substring(342,352)); // 主駛人駕照號碼
	    ply.setWORK_DRIVER_BIRTH(Line.substring(352,359)); // 主駛人出生日 /年月日
	    ply.setWORK_DRIVER_SEX(Line.substring(359,360)); // 主駛人性別
	    ply.setWORK_DRIVER_MARRIAGE(Line.substring(360,361)); // 主駛人婚姻
	    ply.setWORK_PD_RATE(Line.substring(361,363)); // 車體費率代號
	    ply.setWORK_THF_RATE(Line.substring(363,365)); // 竊盜費率代號
	    ply.setWORK_SEX_AGE(Line.substring(365,369)); // 年齡性別係數
	    ply.setWORK_1_KIND_CODE(Line.substring(369,371)); // １-36險種
	    ply.setWORK_1_PD_NAME(new ChangeCode().ChgChCode(Line.substring(371,387),"")); // １-37險種
	    ply.setWORK_1_PD_AMT_S(Line.substring(387,393)); // １-38保額
	    ply.setWORK_1_PD_AMT_FILE(Line.substring(393,394)); // １-38保額
	    ply.setWORK_1_INSU_AMT(Line.substring(394,404)); // １-38保額
	    ply.setWORK_1_DEDUC(Line.substring(404,409)); // １-39自負額
	    ply.setWORK_1_PREM(Line.substring(409,417)); // １-40保費
	    ply.setWORK_1_PREM_C(Line.substring(417,425)); // １-40保費覆核
	    ply.setWORK_2_INSU_AMT(Line.substring(425,435)); // ２-42保額
	    ply.setWORK_2_DEDUC(Line.substring(435,438)); // ２-43自負額
	    ply.setWORK_2_PREM(Line.substring(438,444)); // ２-44保費
	    ply.setWORK_2_PREM_C(Line.substring(444,450)); // ２-45保費覆核
	    ply.setWORK_3_TPL_OLD(Line.substring(450,451)); // ３-46等等級
	    ply.setWORK_3_TPL_NEW(Line.substring(451,452)); // ３-46新等級
	    ply.setWORK_3_AMT_1(Line.substring(452,462)); // ３-46保額傷
	    ply.setWORK_3_INSU_AMT(Line.substring(462,472)); // ３-47保額總
	    ply.setWORK_3_PREM(Line.substring(472,478)); // ３-48保費
	    ply.setWORK_3_PREM_C(Line.substring(478,484)); // ３-49保費覆核
	    ply.setWORK_4_INSU_AMT(Line.substring(484,494)); // ４-50保額
	    ply.setWORK_4_PREM(Line.substring(494,500)); // ４-51保費
	    ply.setWORK_4_PREM_C(Line.substring(500,506)); // ４-52保費覆核
	    ply.setWORK_5_PREM(Line.substring(506,512)); // ５-53保費
	    ply.setWORK_5_PREM_C(Line.substring(512,518)); // ５-54保費覆核
	    ply.setWORK_6_AMT_1(Line.substring(518,528)); // ６-55保額傷
	    ply.setWORK_6_INSU_AMT(Line.substring(528,538)); // ６-56保額總
	    ply.setWORK_6_AMT_2(Line.substring(538,548)); // ６-59保額總
	    ply.setWORK_6_PREM(Line.substring(548,554)); // ６-57保費
	    ply.setWORK_6_PREM_C(Line.substring(554,560)); // ６-58保費覆核
	    ply.setWORK_7_AMT_1(Line.substring(560,570)); // ７-60保額傷
	    ply.setWORK_7_INSU_AMT(Line.substring(570,580)); // ７-61保額總
	    ply.setWORK_7_AMT_D(Line.substring(580,584)); // ７-64保額總
	    ply.setWORK_7_AMT_DAY(Line.substring(584,587)); // ７-64保額總
	    ply.setWORK_7_PREM(Line.substring(587,593)); // ７-62保費
	    ply.setWORK_7_PREM_C(Line.substring(593,599)); // ７-63保費覆核
	    ply.setWORK_A_KIND(Line.substring(599,601)); // 其他險種
	    ply.setWORK_A_KIND_N(new ChangeCode().ChgChCode(Line.substring(601,617),"")); // 其他險種中文
	    ply.setWORK_A_KIND_N1(new ChangeCode().ChgChCode(Line.substring(617,633),"")); // 其他險種中文
	    ply.setWORK_A_AMT(Line.substring(633,643)); // 其他保額
	    ply.setWORK_A_DEDUC(Line.substring(643,649)); // 其他自負額
	    ply.setWORK_A_PREM(Line.substring(649,657)); // 其他保費 　
	    ply.setWORK_A_PREM_C(Line.substring(657,665)); // 其他庥費覆核
	    ply.setWORK_B_KIND(Line.substring(665,667)); // 其他險種
	    ply.setWORK_B_KIND_N(new ChangeCode().ChgChCode(Line.substring(667,683),"")); // 其他險種中文
	    ply.setWORK_B_KIND_N1(new ChangeCode().ChgChCode(Line.substring(683,699),"")); // 其他險種中文
	    ply.setWORK_B_AMT(Line.substring(699,709)); // 其他保額
	    ply.setWORK_B_DEDUC(Line.substring(709,715)); // 其他自負額
	    ply.setWORK_B_PREM(Line.substring(715,723)); // 其他保費
	    ply.setWORK_B_PREM_C(Line.substring(723,731)); // 其他庥費覆核
	    ply.setWORK_C_KIND(Line.substring(731,733)); // 其他險種
	    ply.setWORK_C_KIND_N(new ChangeCode().ChgChCode(Line.substring(733,749),"")); // 其他險種中文
	    ply.setWORK_C_KIND_N1(new ChangeCode().ChgChCode(Line.substring(749,765),"")); // 其他險種中文
	    ply.setWORK_C_AMT(Line.substring(765,775)); // 其他保額
	    ply.setWORK_C_DEDUC(Line.substring(775,781)); // 其他自負額
	    ply.setWORK_C_PREM(Line.substring(781,789)); // 其他保費
	    ply.setWORK_C_PREM_C(Line.substring(789,797)); // 其他庥費覆核
	    ply.setWORK_D_KIND(Line.substring(797,799)); // 其他險種
	    ply.setWORK_D_KIND_N(new ChangeCode().ChgChCode(Line.substring(799,815),"")); // 其他險種中文
	    ply.setWORK_D_KIND_N1(new ChangeCode().ChgChCode(Line.substring(815,831),"")); // 其他險種中文
	    ply.setWORK_D_AMT(Line.substring(831,841)); // 其他保額
	    ply.setWORK_D_DEDUC(Line.substring(841,847)); // 其他自負額
	    ply.setWORK_D_PREM(Line.substring(847,855)); // 其他保費
	    ply.setWORK_D_PREM_C(Line.substring(855,863)); // 其他庥費覆核
	    ply.setWORK_E_KIND(Line.substring(863,865)); // 其他險種
	    ply.setWORK_E_KIND_N(new ChangeCode().ChgChCode(Line.substring(865,881),"")); // 其他險種中文
	    ply.setWORK_E_KIND_N1(new ChangeCode().ChgChCode(Line.substring(881,897),"")); // 其他險種中文
	    ply.setWORK_E_AMT(Line.substring(897,907)); // 其他保額
	    ply.setWORK_E_DEDUC(Line.substring(907,913)); // 其他自負額
	    ply.setWORK_E_PREM(Line.substring(913,921)); // 其他保費
	    ply.setWORK_E_PREM_C(Line.substring(921,929)); // 其他庥費覆核
	    ply.setWORK_F_KIND(Line.substring(929,931)); // 其他險種
	    ply.setWORK_F_KIND_N(new ChangeCode().ChgChCode(Line.substring(931,947),"")); // 其他險種中文
	    ply.setWORK_F_KIND_N1(new ChangeCode().ChgChCode(Line.substring(947,963),"")); // 其他險種中文
	    ply.setWORK_F_AMT(Line.substring(963,973)); // 其他保額
	    ply.setWORK_F_DEDUC(Line.substring(973,979)); // 其他自負額
	    ply.setWORK_F_PREM(Line.substring(979,987)); // 其他保費
	    ply.setWORK_F_PREM_C(Line.substring(987,995)); // 其他庥費覆核
	    ply.setWORK_G_KIND(Line.substring(995,997)); // 其他險種
	    ply.setWORK_G_KIND_N(new ChangeCode().ChgChCode(Line.substring(997,1013),"")); // 其他險種中文
	    ply.setWORK_G_KIND_N1(new ChangeCode().ChgChCode(Line.substring(1013,1029),"")); // 其他險種中文
	    ply.setWORK_G_AMT(Line.substring(1029,1039)); // 其他保額
	    ply.setWORK_G_DEDUC(Line.substring(1039,1045)); // 其他自負額
	    ply.setWORK_G_PREM(Line.substring(1045,1053)); // 其他保費
	    ply.setWORK_G_PREM_C(Line.substring(1053,1061)); // 其他庥費覆核
	    ply.setWORK_H_KIND(Line.substring(1061,1063)); // 其他險種
	    ply.setWORK_H_KIND_N(new ChangeCode().ChgChCode(Line.substring(1063,1079),"")); // 其他險種中文
	    ply.setWORK_H_KIND_N1(new ChangeCode().ChgChCode(Line.substring(1079,1095),"")); // 其他險種中文
	    ply.setWORK_H_AMT(Line.substring(1095,1105)); // 其他保額
	    ply.setWORK_H_DEDUC(Line.substring(1105,1111)); // 其他自負額
	    ply.setWORK_H_PREM(Line.substring(1111,1119)); // 其他保費
	    ply.setWORK_H_PREM_C(Line.substring(1119,1127)); // 其他庥費覆核
	    ply.setWORK_TOT_PREM(Line.substring(1127,1137)); // 任意險合計保費
	    ply.setWORK_TOT_1_PREM(Line.substring(1137,1147)); // 覆核任意保費
	    ply.setWORK_CI_CARD_NO(Line.substring(1147,1157)); // 強制証號
	    ply.setWORK_CI_NEW_RANK(Line.substring(1157,1159)); // 強制新等級
	    ply.setWORK_CI_PREM(Line.substring(1159,1165)); // 強制保費
	    ply.setWORK_TOT_ALL_PREM(Line.substring(1165,1175)); // 合計總保費
	    ply.setWORK_CI_EFFECT_DATE(Line.substring(1175,1182)); // 強制生效日
								   // 年/月/日
	    ply.setWORK_CI_EXPIRE_DATE(Line.substring(1182,1189)); // 強制到期日
								   // 年/月/日
	    ply.setWORK_CI_SERIAL_NO(Line.substring(1189,1203)); // 關貿強制查詢序號
	    ply.setWORK_AGENT_UNIT(Line.substring(1203,1213)); // 銀行分行別
	    ply.setWORK_AGENT_EMP_NO(Line.substring(1213,1224)); // 銀行員工
	    ply.setWORK_STATIC_UNIT(Line.substring(1224,1226)); // 統計單位
	    ply.setWORK_EMP_NO(Line.substring(1226,1231)); // 經手人
	    ply.setWORK_S_EMP(Line.substring(1231,1236)); // 服務人
	    ply.setWORK_BROKER(Line.substring(1236,1238)); // 代理商代號
	    ply.setWORK_CHANNEL_TYPE(Line.substring(1238,1240)); // 通路記號 E
	    ply.setWORK_SYS_DAY(Line.substring(1240,1247)); // 收件單位及日期D
	    ply.setWORK_PD_N_LOSS_PT(Line.substring(1247,1249)); // 車體加減費級數 C
	    ply.setWORK_TPL_NEW_RANK(Line.substring(1249,1251)); // 責任新等級 B
	    ply.setWORK_VI_SERIAL_NO(Line.substring(1251,1271)); // 關貿任意查詢序號
	    ply.setWORK_PD_LOSS_RATE(Line.substring(1271,1275)); // 賠款次數車體
	    ply.setWORK_TPL_LOSS_RATE(Line.substring(1275,1279)); // 賠款次數責任
	    policys.add(ply);
	}
	return policys;
    }

    @Override
    public int compare(Object o1, Object o2)
    {
	FirePolicyEntity FirePolicy0=(FirePolicyEntity)o1;
	FirePolicyEntity FirePolicy1=(FirePolicyEntity)o2;
	int flag=FirePolicy0.getNEWPLY().compareTo(FirePolicy1.getNEWPLY());
	if(flag==0)
	{
	    return FirePolicy0.getSTUNIT().compareTo(FirePolicy1.getSTUNIT());
	}
	else
	{
	    return flag;
	}
    }

}
