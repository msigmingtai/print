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
import commans.function.ErrorWindows;

import model.entity.FirePolicyEntity;

//住火套表
public class FirePolicyATypeBlank extends PrintPolicyBasic<FirePolicyEntity>
{

    public FirePolicyATypeBlank(List<FirePolicyEntity> obj)
	    throws DocumentException, IOException
    {
	super(obj);
    }

    /**
     * 
     */
    private static final long serialVersionUID=-8854443406051469196L;

    public void CreatePolicy()
    {

	// Count Total
	for(FirePolicyEntity ply : obj)
	{
	    if(ply.getNEWPLY().substring(4,5).equals(policy_type_A))
		count=count+1;
	}
	totalBytes=count;
	try
	{
	    path="C:\\A.pdf";
	    document=new Document(PageSize.A4,72,36,166,-60);
	    // 建檔
	    // 產出檔
	    PdfWriter.getInstance(document,new FileOutputStream(path));
	    setPDFTitle();
	    document.open();
	    // 選擇檔案
	    for(FirePolicyEntity ply : obj)
	    {
		if(ply.getNEWPLY().substring(4,5).equals(policy_type_A))
		{
		    commit=commit+1;
		    // 寫入PDF
		    // 保單號
		    // 保單號
		    // 保單號
		    document.add(new Paragraph(8,"                        "+ply.getNEWPLY().substring(0,2)+"         "+ply.getNEWPLY().substring(2,10)+"                                       "+ply.getPLYNO()+"                                  ",FontChinese));
		    // 被保人
		    document.add(new Paragraph(21,"                   "+ply.getFASRCNAME()+"                                                                                             "+ply.getAREANO()+ply.getADDR().substring(0,15)+"                     ",FontChinese2));
		    document.add(new Paragraph(-12,"                                                                                                                                                                          "+ply.getADDR().substring(15),FontChinese2));
		    document.add(new Paragraph(12,"                                               "+ply.getFASRIDNO()+"                   "+ply.getTEL(),FontChinese));
		    // 要保人
		    document.add(new Paragraph(14,"                   "+ply.getINSDNAME()+"                                                                                             "+ply.getAREANO()+ply.getADDR().substring(0,15)+"                     ",FontChinese2));
		    document.add(new Paragraph(-5,"                                                                                                                                                                          "+ply.getADDR().substring(15),FontChinese2));
		    document.add(new Paragraph(12,"                                              "+ply.getIDNO()+"                   "+ply.getTEL(),FontChinese));
		    // 總保額
		    String money=getTotalAmount(ply.getFIREPREM(),ply.getEPREM());
		    document.add(new Paragraph(16,"                               "+ChangeCode.CatZero(ply.getINSDAMT()),FontChinese));
		    document.add(new Paragraph(0,"                                                                                                                                                              "+ChangeCode.CatZero(ply.getFIREPREM()),FontChinese));
		    document.add(new Paragraph(13,"                                                                                                      "+money,FontChinese));
		    document.add(new Paragraph(0,"                                                                                                                                                               "+ChangeCode.CatZero(ply.getEPREM()),FontChinese));
		    document.add(new Paragraph(13,"                               "+ChangeCode.CatZero(ply.getEAMT())+"                                                                                                                     0",FontChinese));

		    // 保險期間
		    expire_date=1+Integer.parseInt(ply.getEXPDAY().substring(0,3));
		    document.add(new Paragraph(18,"                  12                       "+ply.getEXPDAY().substring(0,3)+"         "+ply.getEXPDAY().substring(3,5)+"        "+ply.getEXPDAY().substring(5)+"                                        "+expire_date+"       "+ply.getEXPDAY().substring(3,5)+"        "+ply.getEXPDAY().substring(5),FontChinese));
		    // 標的物地址
		    document.add(new Paragraph(27,"                   "+ply.getLOCATION().trim()+ply.getAREA(),FontChinese));
		    // 建築物
		    // 樓層建築等級
		    document.add(new Paragraph(30,"             "+"　"+"               "+"　　　"+"                                                                                                                                                                                  "+ply.getFLOOR()+"                              "+getConstClass(ply.getCONSTCLASS()),FontChinese1));
		    // 建築物年份
		    document.add(new Paragraph("                                                                         "+ply.getSTRUYEAR(),FontChinese1));
		    document.add(new Paragraph(0,"                                                                                                         "+ply.getCONSTMARK(),FontChinese1));
		    // 建築物坪數
		    document.add(new Paragraph("                                                                                           "+CatZero(ply.getAREASIZE()),FontChinese1));
		    document.add(new Paragraph(92,"   ",FontChinese));
		    // 分項保費建築物
		    document.add(new Paragraph(50,"     01                                                                                "+ChangeCode.CatZero(ply.getINSDAMT())+"         "+ply.getRATE().substring(1)+"    1/1        "+ChangeCode.CatZero(ply.getFIREPREM()),FontChinese));
		    document.add(new Paragraph(0,"                                                                                                                                                                                       "+getConstClass(ply.getCONSTCLASS()),FontChinese));
		    // 加保動產
		    document.add(new Paragraph(12,"  ",FontChinese));
		    document.add(new Paragraph(12,"  ",FontChinese));
		    // 基本地震
		    document.add(new Paragraph(18,"     02                                                                                "+ChangeCode.CatZero(ply.getEAMT())+"         "+"0.9        "+"1/1        "+ChangeCode.CatZero(ply.getEPREM()),FontChinese));
		    document.add(new Paragraph(0,"                                                                                                                                                                                       "+getConstClass(ply.getCONSTCLASS()),FontChinese));
		    checkAMT=Integer.parseInt(ply.getAMT2().trim());
		    if(checkAMT>0)
		    {
			document.add(new Paragraph(20,"    03    建築物",FontChinese));
			document.add(new Paragraph(0,"                                                                                        "+ChangeCode.CatZero(ply.getAMT2())+"           "+ply.getRATE2()+"      "+"1/1        "+ChangeCode.CatZero(ply.getPREM2()),FontChinese));
			document.add(new Paragraph(0,"                                                                                                                                                                                       "+getConstClass(ply.getCONSTCLASS()),FontChinese));
		    }
		    else
		    {
			document.add(new Paragraph(" "));
		    }
		    document.add(new Paragraph(16,"      自101年1月1日起，住宅地震基本保險最高賠償責任調整為新台幣150萬元。",FontChinese1));
		    // 備註
		    // 貸款編號
		    document.add(new Paragraph(16,"                                                                                                              "+ply.getBANKKEY(),FontChinese));
		    // 抵押權人/分行別
		    document.add(new Paragraph(16,"                                                                                                     "+ply.getMTG()+"                                        "+ply.getAGENTUNIT(),FontChinese));
		    // 行員代號
		    document.add(new Paragraph(16,"                                                                                                                                                         "+ply.getAGENTEMPNO(),FontChinese));
		    // 其他
		    document.add(new Paragraph(" "));
		    document.add(new Paragraph(" "));
		    document.add(new Paragraph(66,"            "+ply.getAGENT()+"               "+ply.getMTG().trim()+"                   "+ply.getBROKER()+"                   "+ply.getEMPA()+"                   "+ply.getEMPA1(),FontChinese));
		    document.add(new Paragraph(16,"16.9% + 6%"+"                                                                                                                           "+ply.getSTUNIT().trim()+"                          "+ply.getSEMP1().trim(),FontChinese));
		    document.newPage();
		    // ProgressBar Mode
		    setProgressBar();
		    broker=ply.getBROKER();
		}
	    }
	    document.close();
	    path=function.ChangeBankName(broker,path,"基本住宅","套表列印");
	    JOptionPane.showMessageDialog(null,path);
	    dispose();

	}
	catch (Exception e1)
	{
	    document.close();
	    System.gc();
	    deleteFile(path);
	    new ErrorWindows(e1);

	}
	finally
	{
	    document.close();
	    System.gc();
	}
    }

    @Override
    public void run()
    {
	CreatePolicy();
    }

}
