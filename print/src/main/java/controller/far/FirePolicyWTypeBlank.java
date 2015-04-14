package controller.far;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import javax.swing.JOptionPane;
import com.lowagie.text.DocumentException;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.Document;
import commans.basic.PrintPolicyBasic;
import commans.function.ChangeCode;

import model.entity.FirePolicyEntity;

//屋主甲式套表 【套表列印W單】
public class FirePolicyWTypeBlank extends PrintPolicyBasic<FirePolicyEntity>
{
    public FirePolicyWTypeBlank(List<FirePolicyEntity> obj)
	    throws DocumentException, IOException
    {
	super(obj);
	// TODO 自動產生的建構子 Stub
    }

    private static final long serialVersionUID=1646539472010439142L;

    public void CreatePolicy()
    {
	try
	{
	    // Count Total File
	    for(FirePolicyEntity ply : obj)
	    {
		if(ply.getNEWPLY().substring(4,5).equals(policy_type_W))
		    totalBytes=totalBytes+1;
	    }
	    // 套表 產出檔
	    path="C:\\W.pdf";
	    document=new Document(PageSize.A4,72,36,125,0);
	    PdfWriter.getInstance(document,new FileOutputStream(path));
	    setPDFTitle();
	    document.open();

	    // 選擇檔案
	    for(FirePolicyEntity ply : obj)
	    {
		if(ply.getNEWPLY().substring(4,5).equals(policy_type_W))
		{
		    // 寫入PDF
		    // 保單號
		    document.add(new Paragraph(14,"                              "+ply.getNEWPLY().substring(0,2)+"       "+ply.getNEWPLY().substring(2,10)+"                              "+ply.getPLYNO(),FontChinese));
		    // 簽單日
		    document.add(new Paragraph(0,"                                                                                                                                                                      "+ply.getISSUE_DAY().substring(0,3)+"    "+ply.getISSUE_DAY().substring(3,5)+"    "+ply.getISSUE_DAY().substring(5,7),FontChinese));
		    // 要保人
		    document.add(new Paragraph(14,"                "+ply.getFASRCNAME()+"                                                                                             "+ply.getAREANO()+ply.getADDR().substring(0,15),FontChinese2));
		    document.add(new Paragraph(7,"                                                                                                                                                                        "+ply.getADDR().substring(15),FontChinese2));
		    document.add(new Paragraph(12,"                                              "+ply.getFASRIDNO()+"                      "+ply.getTEL(),FontChinese));
		    // 關係
		    document.add(new Paragraph(0,"                                                                                                                                                                                                                                                    "+transRelate(ply.getRELATE()),FontChinese1));

		    // 被保人
		    document.add(new Paragraph(17,"                "+ply.getINSDNAME()+"                                                                                              "+ply.getAREANO()+ply.getADDR().substring(0,15),FontChinese2));
		    document.add(new Paragraph(7,"                                                                                                                                                                        "+ply.getADDR().substring(15),FontChinese2));
		    document.add(new Paragraph(12,"                                              "+ply.getIDNO()+"                      "+ply.getTEL(),FontChinese));
		    // 保險期間
		    expire_date=1+Integer.parseInt(ply.getEXPDAY().substring(0,3));
		    document.add(new Paragraph(16,"                   12                             "+ply.getEXPDAY().substring(0,3)+"       "+ply.getEXPDAY().substring(3,5)+"         "+ply.getEXPDAY().substring(5)+"                                              "+expire_date+"       "+ply.getEXPDAY().substring(3,5)+"          "+ply.getEXPDAY().substring(5),FontChinese));
		    // 標的物地址
		    document.add(new Paragraph(25,"                "+ply.getLOCATION().trim()+"     "+ply.getAREA(),FontChinese));
		    // 建築
		    document.add(new Paragraph(33,"                     "+"　"+"               "+"　　　"+"                                                                                                                                                                              "+ply.getFLOOR()+"                            "+getConstClass(ply.getCONSTCLASS()),FontChinese1));
		    document.add(new Paragraph("                     "+"                                                     "+ply.getSTRUYEAR(),FontChinese1));
		    document.add(new Paragraph(0,"                                                                                               "+ply.getCONSTMARK(),FontChinese1));
		    document.add(new Paragraph("                     "+"                                                               "+CatZero(ply.getAREASIZE()),FontChinese1));
		    // 建築物
		    document.add(new Paragraph(96," "));
		    // 火險保費及金額
		    document.add(new Paragraph(0,"                                                                                                                            "+ChangeCode.CatZero(ply.getINSDAMT().substring(0,5)),FontChinese));
		    // 火險費率
		    document.add(new Paragraph(0,"　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　 "+ply.getRATE().substring(1),FontChinese));
		    // 火險保費
		    document.add(new Paragraph(0,"　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　                   "+ChangeCode.CatZero(ply.getFIREPREM()),FontChinese));
		    // 竊盜
		    document.add(new Paragraph(60,"　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　 "+ply.getRATE7(),FontChinese));
		    // 保費
		    document.add(new Paragraph(0,"　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　                   "+ChangeCode.CatZero(ply.getPREM7()),FontChinese));
		    // 竊盜險保額
		    document.add(new Paragraph(20,"                                                                                            "+get7lowAmount(ply.getAMT7()),FontChinese));
		    document.add(new Paragraph(10,"                                                                                            "+get7highAmount(ply.getAMT7()),FontChinese));
		    // 基本震保額
		    document.add(new Paragraph(12,"                                                                                                                            "+ChangeCode.CatZero(ply.getEAMT().substring(0,5)),FontChinese));
		    // 基本地震費率
		    document.add(new Paragraph(0,"　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　 0.9",FontChinese));
		    // 基本地震保費
		    document.add(new Paragraph(0,"　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　                   "+ChangeCode.CatZero(ply.getEPREM()),FontChinese));
		    // 訪客及第三人
		    document.add(new Paragraph(26,"                                                                                                                       "+getDeathAmount(ply.getAMTV().substring(0,5)),FontChinese));
		    document.add(new Paragraph(13,"                                                                                                                       "+getProductAmount(ply.getAMTV().substring(0,5)),FontChinese));
		    // 費率
		    document.add(new Paragraph(0,"　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　 "+ply.getRATEV(),FontChinese));
		    document.add(new Paragraph(0,"　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　                   "+ply.getPREMV(),FontChinese));
		    document.add(new Paragraph(12,"                                                                                                                       "+ChangeCode.CatZero(ply.getAMTV().substring(0,5)),FontChinese));
		    // 自動續保
		    document.add(new Paragraph(" "));
		    // 貸款戶別
		    document.add(new Paragraph(" "));
		    // 抵押權人

		    document.add(new Paragraph(" "));
		    document.add(new Paragraph(" "));
		    document.add(new Paragraph(20,"                           "+ply.getMTG()+"                                   "+ply.getAGENTUNIT(),FontChinese));
		    // 行員代號
		    document.add(new Paragraph(14,"                                                                          "+ply.getAGENTEMPNO(),FontChinese));
		    // 貸款編號
		    document.add(new Paragraph(0,"                                                                                                                 "+ply.getBANKKEY(),FontChinese));
		    // 附註
		    document.add(new Paragraph(12,"                本保險單承保建築物之範圍係以抵押權者抵押部分為限。",FontChinese));
		    document.add(new Paragraph(" "));
		    document.add(new Paragraph(" "));
		    document.add(new Paragraph(" "));
		    document.add(new Paragraph(" "));
		    document.add(new Paragraph(28,"              "+ply.getAGENT().trim()+"               "+ply.getMTG().trim()+"                   "+ply.getBROKER()+"                   "+ply.getEMPA()+"                   "+ply.getEMPA1(),FontChinese));
		    document.add(new Paragraph(12,"16.9% + 6%"+"                                                                                                                           "+ply.getSTUNIT().trim()+"                          "+ply.getSEMP1().trim(),FontChinese));
		    document.newPage();
		    // ProgressBar
		    setProgressBar();
		    broker=ply.getBROKER();
		}
	    }
	    document.close();
	    path=function.ChangeBankName(broker,path,"屋主甲式","套表列印");
	    JOptionPane.showMessageDialog(null,path);
	    dispose();
	}
	catch (Exception e1)
	{
	    document.close();
	    deleteFile(path);
	    ErrorMessage(e1);
	}
	finally
	{
	    document.close();
	    dispose();
	}
    }

    public void run()
    {
	CreatePolicy();
    }

}
