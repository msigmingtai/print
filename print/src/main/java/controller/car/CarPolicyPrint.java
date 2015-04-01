package controller.car;

import java.io.*;
import java.util.List;

import javax.swing.*;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import commans.basic.PrintPolicyBasic;
import commans.function.ErrorWindows;
import model.entity.CarPolicyEntity;

/*
 * CAPPM875
 * */
public class CarPolicyPrint extends PrintPolicyBasic<CarPolicyEntity>
{
    /**
     * 
     */
    private static final long serialVersionUID=-8669689757667082673L;

    public CarPolicyPrint(List<CarPolicyEntity> obj) throws DocumentException,
	    IOException
    {
	super(obj);
	// TODO 自動產生的建構子 Stub
    }

    public void CreatePolicy()
    {
	try
	{

	    getTotalbyte();
	    setJpg("/CarPolicy.jpg");
	    path="C:\\temp.pdf";
	    Document document=new Document(PageSize.A4,10,-50,20,-300);// Document(設定格式,左,右,上,下)
	    PdfWriter.getInstance(document,new FileOutputStream(path));
	    // **********************設定字型*************************************************************************************
	    setPDFTitle();
	    document.open();
	    for(CarPolicyEntity ply : obj)
	    {
		document.add(jpeg);
		// 寫入PDF
		// ***************************汽車保險要保書************************************************************************
		document.add(new Paragraph("分行代號： "+ply.getWORK_AGENT_UNIT()+"\n行員代號："+ply.getWORK_AGENT_EMP_NO(),FontChinese));
		document.add(new Paragraph(" ",FontChinese));
		document.add(new Paragraph(" ",FontChinese));
		document.add(new Paragraph(" ",FontChinese));
		// 保險卡號寶單號
		document.add(new Paragraph(14,"                                                                                                                                                                                                                             "+ply.getWORK_CI_CARD_NO(),FontChinese));
		document.add(new Paragraph(18,"                                                                                                                                                                                                                             "+ply.getWORK_POLICY_NO(),FontChinese));
		// ******被保人*****************
		str1=MakesUpSpace(ply.getWORK_INSURED(),ply.getWORK_INSURED().length());
		str2=MakesUpSpace2(ply.getWORK_TEL(),ply.getWORK_TEL().length());
		str4=MakesUpSpace2(ply.getWORK_ADDRESS(),ply.getWORK_ADDRESS().length());
		// 被保險人姓名
		document.add(new Paragraph(22,"                              "+str1,FontChinese));
		// 被保人出生年
		document.add(new Paragraph(0,"                                                                                                                                                   "+CatZero(ply.getWORK_DRIVER_BIRTH().substring(0,3)),FontChinese));
		// 被保人出生月
		document.add(new Paragraph(0,"                                                                                                                                                             "+ply.getWORK_DRIVER_BIRTH().substring(3,5),FontChinese));
		// 被保人出生日
		document.add(new Paragraph(0,"                                                                                                                                                                       "+ply.getWORK_DRIVER_BIRTH().substring(5,7),FontChinese));
		// 被保人性別
		document.add(new Paragraph(0,"                                                                                                                                                                                                 "+ply.getWORK_DRIVER_SEX(),FontChinese));
		// 被保人婚姻
		document.add(new Paragraph(0,"                                                                                                                                                                                                                                                "+ply.getWORK_DRIVER_MARRIAGE(),FontChinese));
		// 被保險人ID
		document.add(new Paragraph(15,"                              "+ply.getWORK_DRIVER_ID_NO(),FontChinese));
		// 被保人通訊地址
		document.add(new Paragraph(13,"                              "+ply.getWORK_MAILNO(),FontChinese));
		document.add(new Paragraph(0,"                                         "+str4,FontChinese));
		// 被保人電話
		document.add(new Paragraph(0,"                                                                                                                                                              "+str2,FontChinese));
		// ******要保人******************
		// 要保人姓名
		document.add(new Paragraph(14,"                                                      "+ply.getWORK_ASR_NAME(),FontChinese));
		// 要保人ID
		document.add(new Paragraph(12,"                              "+ply.getWORK_ASR_ID(),FontChinese));
		// 要保人通訊地址
		document.add(new Paragraph(14,"                              "+ply.getWORK_ASR_AREA(),FontChinese));
		document.add(new Paragraph(0,"                                         "+ply.getWORK_ASR_ADDR(),FontChinese));
		// 要保人電話
		document.add(new Paragraph(0,"                                                                                                                                                              "+ply.getWORK_ASR_TEL(),FontChinese));
		// 受益人
		document.add(new Paragraph(14,"                               "+ply.getWORK_BENEF(),FontChinese));
		// 保險期間
		if("0000000".equals(ply.getWORK_EFFECT_DAY()))
		{
		    ply.setWORK_EFFECT_DAY("       ");
		}
		if("0000000".equals(ply.getWORK_EXPIRE_DAY()))
		{
		    ply.setWORK_EXPIRE_DAY("       ");
		}
		document.add(new Paragraph(13,"                                      "+ply.getWORK_EFFECT_DAY().substring(0,3)+"             "+ply.getWORK_EFFECT_DAY().substring(3,5)+"        "+ply.getWORK_EFFECT_DAY().substring(5,7),FontChinese));
		// 乘載限制
		String people="";
		if(ply.getWORK_PSGER().equals("00"))
		{

		}
		else
		{
		    people=ply.getWORK_PSGER()+"人";
		}
		document.add(new Paragraph("                                      "+ply.getWORK_EXPIRE_DAY().substring(0,3)+"             "+ply.getWORK_EXPIRE_DAY().substring(3,5)+"        "+ply.getWORK_EXPIRE_DAY().substring(5,7)+"                                                                                                                                                                                                 "+people,FontChinese));
		// 車種代號　原始發照　牌照號碼　車輛代號　排氣量　製造年份
		String remoney;
		remoney=CatZero(ply.getWORK_REPLACE_VALUE());
		document.add(new Paragraph(29,"     "+ply.getWORK_BODY_CODE(),FontChinese));
		document.add(new Paragraph(0,"                                "+CatZero(ply.getWORK_ORGN_LISN_D().substring(0,3))+"          "+ply.getWORK_ORGN_LISN_D().substring(3,5),FontChinese));
		// 牌照號碼
		document.add(new Paragraph(0,"                                                                 "+ply.getWORK_PLATE_NO(),FontChinese));
		// 車輛廠牌形式代號
		document.add(new Paragraph(0,"                                                                                                  "+ply.getWORK_BRAND_CODE_8(),FontChinese));
		// 排氣量
		document.add(new Paragraph(0,"                                                                                                                                       "+CatZero(ply.getWORK_CC()),FontChinese));
		// 製造年分
		int createyear=Integer.parseInt(ply.getWORK_BUILT_YEAR());
		if(createyear>=74)
		{
		    createyear=createyear+1900;
		}
		else
		{
		    createyear=createyear+2000;
		}
		document.add(new Paragraph(0,"                                                                                                                                                                               "+createyear,FontChinese));
		// 引擎號碼
		document.add(new Paragraph(0,"                                                                                                                                                                                                                 "+ply.getWORK_ENGINE(),FontChinese));
		// 重置價格
		document.add(new Paragraph(0,"                                                                                                                                                                                                                                                            "+remoney,FontChinese));
		// 車體費率代號
		document.add(new Paragraph(48,"                                  "+ply.getWORK_PD_RATE(),FontChinese));
		// 竊盜費率代號
		document.add(new Paragraph(0,"                                                                                                 "+ply.getWORK_THF_RATE(),FontChinese));
		// 年齡性別係數
		document.add(new Paragraph(0,"                                                                                                                                                                  "+ply.getWORK_SEX_AGE(),FontChinese));

		// 31~64險種 種類 保險金額 自負額 保險費 核覆
		// 車體損失險
		String money35=(CatZero(ply.getWORK_1_INSU_AMT()));
		String money36=(CatZero(ply.getWORK_1_DEDUC()));
		String money37=(CatZero(ply.getWORK_1_PREM()));
		String money38=(CatZero(ply.getWORK_1_PREM_C()));
		document.add(new Paragraph(53," "+ply.getWORK_1_KIND_CODE(),FontChinese));
		document.add(new Paragraph(0,"            "+ply.getWORK_1_PD_NAME(),FontChinese));
		document.add(new Paragraph(0,"                                                                                                                 "+money35,FontChinese));
		document.add(new Paragraph(0,"                                                                                                                                                                  "+money36,FontChinese));
		document.add(new Paragraph(0,"                                                                                                                                                                                           "+money37,FontChinese));
		document.add(new Paragraph(0,"                                                                                                                                                                                                                     "+money38,FontChinese));

		// 竊盜損失險
		String money40=(CatZero(ply.getWORK_2_DEDUC()));
		String money41=(CatZero(ply.getWORK_2_PREM()));
		String money42=(CatZero(ply.getWORK_2_PREM_C()));
		document.add(new Paragraph(18,"                                                                                                                 "+CatZero(ply.getWORK_2_INSU_AMT()),FontChinese));
		document.add(new Paragraph(0,"                                                                                                                                                                  "+money40,FontChinese));
		document.add(new Paragraph(0,"                                                                                                                                                                                           "+money41,FontChinese));
		document.add(new Paragraph(0,"                                                                                                                                                                                                                     "+money42,FontChinese));
		document.add(new Paragraph(0,"                                                                                                                                                                                                                                              "+ply.getWORK_SYS_DAY(),FontChinese));
		// 汽車第三責任險
		String money45=(CatZero(ply.getWORK_3_AMT_1()));
		String money46=(CatZero(ply.getWORK_3_INSU_AMT()));
		String money47=(CatZero(ply.getWORK_3_PREM()));
		String money48=(CatZero(ply.getWORK_3_PREM_C()));
		String money49=(CatZero(ply.getWORK_4_INSU_AMT()));
		String money50=(CatZero(ply.getWORK_4_PREM()));
		String money51=(CatZero(ply.getWORK_4_PREM_C()));
		document.add(new Paragraph("                                                                                                                 "+money45,FontChinese));
		document.add(new Paragraph("                                                                                                                 "+money46,FontChinese));
		document.add(new Paragraph(0,"                                                                                                                                                                                           "+money47,FontChinese));
		document.add(new Paragraph(0,"                                                                                                                                                                                                                     "+money48,FontChinese));
		document.add(new Paragraph(14,"                                                                                                                 "+money49,FontChinese));
		document.add(new Paragraph(0,"                                                                                                                                                                                           "+money50,FontChinese));
		document.add(new Paragraph(0,"                                                                                                                                                                                                                     "+money51,FontChinese));
		// 受酒精附加條款
		String money52=(CatZero(ply.getWORK_5_PREM()));
		String money53=(CatZero(ply.getWORK_5_PREM_C()));
		document.add(new Paragraph("     ",FontChinese));
		document.add(new Paragraph(0,"                                                                                                                                                                                           "+money52,FontChinese));
		document.add(new Paragraph(0,"                                                                                                                                                                                                                     "+money53,FontChinese));
		// 汽車第三責任險乘客體商責任附加條款
		String money54=(CatZero(ply.getWORK_6_AMT_1()));
		// String money55 = (CatZero(ply.getWORK_6_INSU_AMT));
		String money56=(CatZero(ply.getWORK_6_AMT_2()));
		String money57=(CatZero(ply.getWORK_6_PREM()));
		String money58=(CatZero(ply.getWORK_6_PREM_C()));
		document.add(new Paragraph(14,"                                                                                                                 "+money54,FontChinese));
		document.add(new Paragraph("  ",FontChinese));
		document.add(new Paragraph(0,"                                                                                                                                                                                           "+money57,FontChinese));
		document.add(new Paragraph(0,"                                                                                                                                                                                                                     "+money58,FontChinese));
		document.add(new Paragraph(3,"                                                                                                                 "+money56,FontChinese));
		// //汽車第三責任險乘附加駕駛人傷害保險
		String money59=(CatZero(ply.getWORK_7_AMT_1()));
		String money60=(CatZero(ply.getWORK_7_INSU_AMT()));
		String money61=(CatZero(ply.getWORK_7_AMT_D()));
		String day2=(CatZero(ply.getWORK_7_AMT_DAY()));
		String money63=(CatZero(ply.getWORK_7_PREM()));
		String money64=(CatZero(ply.getWORK_7_PREM_C()));
		document.add(new Paragraph(12,"                                                                                                                 "+money59,FontChinese));
		document.add(new Paragraph("                                                                                                                 "+money60,FontChinese));
		document.add(new Paragraph(0,"                                                                                                                                                                                           "+money63,FontChinese));
		document.add(new Paragraph(0,"                                                                                                                                                                                                                     "+money64,FontChinese));
		document.add(new Paragraph("                                                                                                              "+money61+"                     "+day2,FontChinese));
		// 其他險種1
		document.add(new Paragraph(CutPolicyNumber(ply.getWORK_A_KIND())+"   "+ply.getWORK_A_KIND_N()+" "+ply.getWORK_A_KIND_N1()+"                                             "+CatZero(ply.getWORK_A_AMT())+"                       "+CatZero(ply.getWORK_A_DEDUC()),FontChinese));
		document.add(new Paragraph(0,"                                                                                                                                                                                           "+CatZero(ply.getWORK_A_PREM()),FontChinese));
		document.add(new Paragraph(0,"                                                                                                                                                                                                                     "+CatZero(ply.getWORK_A_PREM_C()),FontChinese));
		// 其他險種2
		document.add(new Paragraph(CutPolicyNumber(ply.getWORK_B_KIND())+"   "+ply.getWORK_B_KIND_N()+" "+ply.getWORK_B_KIND_N1()+"                                             "+CatZero(ply.getWORK_B_AMT())+"                       "+CatZero(ply.getWORK_B_DEDUC()),FontChinese));
		document.add(new Paragraph(0,"                                                                                                                                                                                           "+CatZero(ply.getWORK_B_PREM()),FontChinese));
		document.add(new Paragraph(0,"                                                                                                                                                                                                                     "+CatZero(ply.getWORK_B_PREM_C()),FontChinese));
		// 其他險種3
		document.add(new Paragraph(CutPolicyNumber(ply.getWORK_C_KIND())+"   "+ply.getWORK_C_KIND_N()+" "+ply.getWORK_C_KIND_N1()+"                                             "+CatZero(ply.getWORK_C_AMT())+"                       "+CatZero(ply.getWORK_C_DEDUC()),FontChinese));
		document.add(new Paragraph(0,"                                                                                                                                                                                           "+CatZero(ply.getWORK_C_PREM()),FontChinese));
		document.add(new Paragraph(0,"                                                                                                                                                                                                                     "+CatZero(ply.getWORK_C_PREM_C()),FontChinese));
		// 其他險種4
		document.add(new Paragraph(CutPolicyNumber(ply.getWORK_D_KIND())+"   "+ply.getWORK_D_KIND_N()+" "+ply.getWORK_D_KIND_N1()+"                                             "+CatZero(ply.getWORK_D_AMT())+"                       "+CatZero(ply.getWORK_D_DEDUC()),FontChinese));
		document.add(new Paragraph(0,"                                                                                                                                                                                           "+CatZero(ply.getWORK_D_PREM()),FontChinese));
		document.add(new Paragraph(0,"                                                                                                                                                                                                                     "+CatZero(ply.getWORK_D_PREM_C()),FontChinese));
		// 其他險種5
		document.add(new Paragraph(CutPolicyNumber(ply.getWORK_E_KIND())+"   "+CutPolicyNumber(ply.getWORK_E_KIND())+" "+ply.getWORK_E_KIND_N1()+"                                             "+CatZero(ply.getWORK_E_AMT())+"                       "+CatZero(ply.getWORK_E_DEDUC()),FontChinese));
		document.add(new Paragraph(0,"                                                                                                                                                                                           "+CatZero(ply.getWORK_E_PREM()),FontChinese));
		document.add(new Paragraph(0,"                                                                                                                                                                                                                     "+CatZero(ply.getWORK_E_PREM_C()),FontChinese));
		// 其他險種6
		document.add(new Paragraph(CutPolicyNumber(ply.getWORK_F_KIND())+"  "+ply.getWORK_F_KIND_N()+" "+ply.getWORK_F_KIND_N1()+"                                           "+CatZero(ply.getWORK_F_AMT())+"                       "+CatZero(ply.getWORK_F_DEDUC()),FontChinese));
		document.add(new Paragraph(0,"                                                                                                                                                                                           "+CatZero(ply.getWORK_F_PREM()),FontChinese));
		document.add(new Paragraph(0,"                                                                                                                                                                                                                     "+CatZero(ply.getWORK_F_PREM_C()),FontChinese));
		// 其他險種7
		document.add(new Paragraph(CutPolicyNumber(ply.getWORK_G_KIND())+"  "+ply.getWORK_G_KIND_N()+" "+ply.getWORK_G_KIND_N1()+"                                           "+CatZero(ply.getWORK_G_AMT())+"                       "+CatZero(ply.getWORK_G_DEDUC()),FontChinese));
		document.add(new Paragraph(0,"                                                                                                                                                                                           "+CatZero(ply.getWORK_G_DEDUC()),FontChinese));
		document.add(new Paragraph(0,"                                                                                                                                                                                                                     "+CatZero(ply.getWORK_G_PREM_C()),FontChinese));
		// 其他顯種8
		document.add(new Paragraph(CutPolicyNumber(ply.getWORK_H_KIND())+"  "+ply.getWORK_H_KIND_N()+" "+ply.getWORK_H_KIND_N1()+"                                           "+CatZero(ply.getWORK_H_AMT())+"                       "+CatZero(ply.getWORK_H_DEDUC()),FontChinese));
		document.add(new Paragraph(0,"                                                                                                                                                                                           "+CatZero(ply.getWORK_H_PREM()),FontChinese));
		document.add(new Paragraph(0,"                                                                                                                                                                                                                     "+CatZero(ply.getWORK_H_PREM_C()),FontChinese));
		String anypmoney=CatZero(ply.getWORK_TOT_PREM());
		String repmoney=CatZero(ply.getWORK_H_PREM_C());
		String forcemoney=CatZero(ply.getWORK_CI_PREM());
		String totalemoney=CatZero(ply.getWORK_TOT_ALL_PREM());
		// 任意保險費
		document.add(new Paragraph(1,"                                                                                                                                                                                          "+anypmoney,FontChinese));
		// 覆核總費用
		document.add(new Paragraph(0,"                                                                                                                                                                                                                  "+repmoney,FontChinese));
		// 係數等級
		document.add(new Paragraph(15,"                                                                                                      "+ply.getWORK_CI_NEW_RANK(),FontChinese));
		document.add(new Paragraph(0,"                                                                                                                                                                                           "+forcemoney,FontChinese));
		document.add(new Paragraph(0,"                                                                                                                                                                                                                                              "+totalemoney,FontChinese));
		// 強制保險證號碼
		if(ply.getWORK_CI_EFFECT_DATE().equals("0000000")||ply.getWORK_CI_EXPIRE_DATE().equalsIgnoreCase("0000000"))// 無日期帶空白
		{
		    ply.setWORK_CI_EFFECT_DATE("       ");
		    ply.setWORK_CI_EXPIRE_DATE("       ");
		}
		document.add(new Paragraph(17,"                                              "+ply.getWORK_CI_EFFECT_DATE().substring(0,3)+"       "+ply.getWORK_CI_EFFECT_DATE().substring(3,5)+"       "+ply.getWORK_CI_EFFECT_DATE().substring(5,7)+"                                        "+ply.getWORK_CI_EXPIRE_DATE().substring(0,3)+"       "+ply.getWORK_CI_EXPIRE_DATE().substring(3,5)+"       "+ply.getWORK_CI_EXPIRE_DATE().substring(5,7)+"                                                                                                 "+ply.getWORK_CI_SERIAL_NO(),FontChinese));
		document.add(new Paragraph(126,ply.getWORK_CHANNEL_TYPE()+"               "+ply.getWORK_STATIC_UNIT(),FontChinese));
		document.add(new Paragraph(38,"                                                                                                                                                                 "+ply.getWORK_VI_SERIAL_NO(),FontChinese));
		// 賠款紀錄係數
		document.add(new Paragraph(26,"                                                                                                                                                                             "+ply.getWORK_TPL_NEW_RANK(),FontChinese));
		document.add(new Paragraph(0,"                                                                                                                                                                                              "+ply.getWORK_PD_N_LOSS_PT(),FontChinese));
		document.newPage();
		// ************ProgressBar****************************
		setProgressBar();
	    }
	    document.close();
	    path=function.ChangeBankName("",path,"汽車要保書","彩色列印");
	    JOptionPane.showMessageDialog(null,path);
	    dispose();
	}
	catch (Exception e1)
	{
	    document.close();
	    new ErrorWindows(e1);

	}
    }

    @Override
    public void run()
    {
	CreatePolicy();
    }

}
