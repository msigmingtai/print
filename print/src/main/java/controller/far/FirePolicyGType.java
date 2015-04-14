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

//家庭成員綜合險 
public class FirePolicyGType extends PrintPolicyBasic<FirePolicyEntity>
{

    public FirePolicyGType(List<FirePolicyEntity> obj)
	    throws DocumentException, IOException
    {
	super(obj);
	// TODO 自動產生的建構子 Stub
    }

    /**
     * 
     */
    private static final long serialVersionUID=7443582517701990958L;

    public void CreatePolicy()
    {

	int num2=0;
	commit=0;
	try
	{
	    // Count Total File
	    for(FirePolicyEntity ply : obj)
	    {
		if(ply.getNEWPLY().substring(4,5).equals(policy_type_G))
		    totalBytes=totalBytes+1;
	    }
	    setPDFTitle();
	    // 套表 產出檔
	    document=new Document(PageSize.A4,72,36,125,0);
	    setJpg("/G.jpg");
	    PdfWriter.getInstance(document,new FileOutputStream(path));
	    setPDFTitle();
	    document.open();

	    // 選擇檔案
	    for(FirePolicyEntity ply : obj)
	    {
		if(ply.getNEWPLY().substring(4,5).equals(policy_type_G))
		{
		    document.add(jpeg);
		    commit=commit+1;
		    // 寫入PDF
		    // 保單號
		    document.add(new Paragraph(28,"                                                      "+ply.getNEWPLY().substring(0,2)+"       "+ply.getNEWPLY().substring(2,10)+"                                  "+ply.getPLYNO(),FontChinese));
		    document.add(new Paragraph(12,"                                                      "+ply.getFLPNO().substring(0,2)+"       "+ply.getFLPNO().substring(2,12),FontChinese));
		    // 要保人
		    document.add(new Paragraph(14,"                    "+ply.getFASRCNAME()+"                                                                                         "+ply.getAREANO()+ply.getADDR().substring(0,15),FontChinese2));
		    document.add(new Paragraph(12,"                                                                                                                                                                        "+ply.getADDR().substring(15),FontChinese2));
		    document.add(new Paragraph(12,"                                              "+ply.getFASRIDNO()+"                   "+ply.getTEL(),FontChinese));
		    // 被保人
		    document.add(new Paragraph(12,"                    "+ply.getINSDNAME()+"                                                                                          "+ply.getAREANO()+ply.getADDR().substring(0,15),FontChinese2));
		    document.add(new Paragraph(12,"                                                                                                                                                                        "+ply.getADDR().substring(15),FontChinese2));
		    document.add(new Paragraph(12,"                                              "+ply.getIDNO()+"                   "+ply.getTEL(),FontChinese));
		    // 保險期間
		    num2=1+Integer.parseInt(ply.getEXPDAY().substring(0,3));
		    document.add(new Paragraph(14,"                   12                         "+ply.getEXPDAY().substring(0,3)+"       "+ply.getEXPDAY().substring(3,5)+"         "+ply.getEXPDAY().substring(5)+"                                        "+num2+"       "+ply.getEXPDAY().substring(3,5)+"          "+ply.getEXPDAY().substring(5),FontChinese));
		    // 標的物地址
		    document.add(new Paragraph(28,"                "+ply.getLOCATION().trim()+"     "+ply.getAREA(),FontChinese));
		    // 建築
		    document.add(new Paragraph(30,"                     "+"　"+"               "+"　　　"+"                                                                                                                                                                            "+ply.getFLOOR()+"                            "+getConstClass(ply.getCONSTCLASS()),FontChinese1));
		    // 建築年份
		    document.add(new Paragraph("                     "+"                                                     "+ply.getSTRUYEAR(),FontChinese1));
		    document.add(new Paragraph(0,"                                                                                             "+ply.getCONSTMARK(),FontChinese1));
		    document.add(new Paragraph("                     "+"                                                               "+CatZero(ply.getAREASIZE()),FontChinese1));
		    // 建築物
		    document.add(new Paragraph(" "));
		    document.add(new Paragraph(" "));
		    document.add(new Paragraph(" "));
		    document.add(new Paragraph(" "));
		    document.add(new Paragraph(53," "));
		    // 火險保費及金額
		    document.add(new Paragraph(0,"　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　"+ChangeCode.CatZero(ply.getINSDAMT().substring(0,5)),FontChinese));
		    // 火險費率
		    document.add(new Paragraph(0,"　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　"+ply.getRATE().substring(1),FontChinese));
		    // 火險保費
		    document.add(new Paragraph(0,"　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　"+ChangeCode.CatZero(ply.getFIREPREM()),FontChinese));
		    // 基本震保額
		    document.add(new Paragraph(" "));
		    document.add(new Paragraph(25,"　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　"+ChangeCode.CatZero(ply.getEAMT().substring(0,5)),FontChinese));
		    // 基本地震費率
		    document.add(new Paragraph(0,"　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　0.9",FontChinese));
		    // 基本地震保費
		    document.add(new Paragraph(0,"　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　"+ChangeCode.CatZero(ply.getEPREM()),FontChinese));
		    // 自動續保
		    document.add(new Paragraph(" "));
		    // 貸款戶別
		    document.add(new Paragraph(" "));
		    // 意外險
		    document.add(new Paragraph(2,"                                                                     "+ChangeCode.CatZero(5000000)+"                                                                "+ChangeCode.CatZero(1050),FontChinese));
		    // 總保費
		    int totalprem=1050+Integer.parseInt(ply.getEPREM().trim())+Integer.parseInt(ply.getFIREPREM().trim());
		    document.add(new Paragraph(0,"　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　"+ChangeCode.CatZero(totalprem),FontChinese));
		    // 抵押權人
		    document.add(new Paragraph(95,"                                                                               "+ply.getAGENTUNIT(),FontChinese));
		    document.add(new Paragraph(10,"                  "+ply.getMTG(),FontChinese));
		    // 行員代號
		    document.add(new Paragraph(0,"                                                                                "+ply.getAGENTEMPNO(),FontChinese));
		    // 貸款編號
		    document.add(new Paragraph(0,"                                                                                                                 "+ply.getBANKKEY(),FontChinese));
		    // 附註
		    document.add(new Paragraph(14,"　",FontChinese));
		    document.add(new Paragraph(" "));
		    document.add(new Paragraph(" "));
		    document.add(new Paragraph(" "));
		    document.add(new Paragraph(50,"              "+ply.getAGENT().trim()+"               "+ply.getMTG().trim()+"                   "+ply.getBROKER()+"                   "+ply.getEMPA()+"                   "+ply.getEMPA1()+"                      "+ply.getSTUNIT()+"                       "+ply.getSEMP1().trim(),FontChinese));
		    // document.add(new Paragraph(2,"16.9% + 6%",FontChinese));
		    document.newPage();
		    // ProgressBar
		    setProgressBar();
		    broker=ply.getBROKER();
		}
	    }

	    document.close();
	    path=function.ChangeBankName(broker,path,"家庭成員","黑白列印");
	    JOptionPane.showMessageDialog(null,path);
	    dispose();
	}
	catch (Exception e1)
	{
	    document.close();
	    deleteFile(path);
	    ErrorMessage(e1);
	    dispose();
	}
    }

    public void run()
    {
	CreatePolicy();
    }

}
