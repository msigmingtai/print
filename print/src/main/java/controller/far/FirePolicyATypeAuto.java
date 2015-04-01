package controller.far;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import javax.swing.JOptionPane;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

import commans.basic.PrintPolicyBasic;
import commans.function.ChangeCode;

import model.entity.FirePolicyEntity;

/**
 * 住火要保書底圖無新保單號列印
 * 
 * @author 50123
 * @version 103.09.16
 * 
 ***/

public class FirePolicyATypeAuto extends PrintPolicyBasic<FirePolicyEntity>
{

    private static final long serialVersionUID=-9075827488492106438L;

    public FirePolicyATypeAuto(List<FirePolicyEntity> obj)
	    throws DocumentException, IOException
    {
	super(obj);
    }

    private void createParagraph() throws DocumentException
    {
	document.add(jpeg);
	commit=commit+1;
	// write pdf
	// 保單號
	document.add(new Paragraph(-2,"                                                                                                "+ply.getNEWPLY(),FontChinese));
	// document.add(new
	// Paragraph("                                                                            "+row[35]+"                                  ",FontChinese));
	// 被保人
	document.add(new Paragraph(21,"                   "+ply.getFASRCNAME()+"                                                                                             "+ply.getAREANO()+ply.getADDR().substring(0,15)+"                     ",FontChinese2));
	document.add(new Paragraph(-12,"                                                                                                                                                                          "+ply.getADDR().substring(15),FontChinese2));
	document.add(new Paragraph(12,"                                               "+ply.getFASRIDNO()+"                   "+ply.getTEL(),FontChinese));
	// 要保人
	document.add(new Paragraph(16,"                   "+ply.getINSDNAME()+"                                                                                             "+ply.getAREANO()+ply.getADDR().substring(0,15)+"                     ",FontChinese2));
	document.add(new Paragraph(-5,"                                                                                                                                                                          "+ply.getADDR().substring(15),FontChinese2));
	document.add(new Paragraph(12,"                                              "+ply.getIDNO()+"                   "+ply.getTEL(),FontChinese));
	// 總保額
	String money=getTotalAmount(ply.getFIREPREM(),ply.getEPREM());
	document.add(new Paragraph(12,"                                  "+ChangeCode.CatZero(ply.getINSDAMT()),FontChinese));
	document.add(new Paragraph(0,"                                                                                                                                                              "+ChangeCode.CatZero(ply.getFIREPREM()),FontChinese));
	document.add(new Paragraph(18,"                                                                                                      "+money,FontChinese));
	document.add(new Paragraph(0,"                                                                                                                                                              "+ChangeCode.CatZero(ply.getEPREM()),FontChinese));
	// 地震險保額險
	document.add(new Paragraph(10,"                                  "+ChangeCode.CatZero(ply.getEAMT())+"                                                                                                                    0",FontChinese));
	// 保險期間
	effect_date=Integer.parseInt(ply.getEXPDAY().substring(0,3));
	expire_date=1+Integer.parseInt(ply.getEXPDAY().substring(0,3));
	document.add(new Paragraph(16,"                  12                       "+effect_date+"         "+ply.getEXPDAY().substring(3,5)+"        "+ply.getEXPDAY().substring(5)+"                                        "+expire_date+"       "+ply.getEXPDAY().substring(3,5)+"        "+ply.getEXPDAY().substring(5),FontChinese));
	// 標的物地址
	document.add(new Paragraph(30,"                   "+ply.getLOCATION().trim()+ply.getAREA(),FontChinese));
	// 建築物
	document.add(new Paragraph(28,"             "+"　"+"               "+"　　　"+"                                                                                                                                                                                       "+ply.getFLOOR()+"                              "+getConstClass(ply.getCONSTCLASS()),FontChinese1));
	// 建築年份
	document.add(new Paragraph("                                                                        "+ply.getSTRUYEAR(),FontChinese1));
	document.add(new Paragraph(0,"                                                                                               "+ply.getCONSTMARK(),FontChinese1));
	document.add(new Paragraph("                                                                                "+CatZero(ply.getAREASIZE()),FontChinese1));
	document.add(new Paragraph(100,"   ",FontChinese));
	// 分項保費建築物
	document.add(new Paragraph(46,"     01                                                                              "+ChangeCode.CatZero(ply.getINSDAMT())+"          "+ply.getRATE().substring(1)+"    1/1        "+ChangeCode.CatZero(ply.getFIREPREM()),FontChinese));
	document.add(new Paragraph(0,"                                                                                                                                                                                     "+getConstClass(ply.getCONSTCLASS()),FontChinese));
	// 加保動產
	document.add(new Paragraph(12,"  ",FontChinese));
	document.add(new Paragraph(12,"  ",FontChinese));

	// 基本地震
	document.add(new Paragraph(20,"     02                                                                              "+ChangeCode.CatZero(ply.getEAMT())+"          "+"0.9        "+"1/1        "+ChangeCode.CatZero(ply.getEPREM()),FontChinese));
	document.add(new Paragraph(0,"                                                                                                                                                                                     "+getConstClass(ply.getCONSTCLASS()),FontChinese));
	// 備註
	document.add(new Paragraph(28,"          自101年1月1日起，住宅地震基本保險最高賠償責任調整為新台幣150萬元。",FontChinese1));
	// 抵押權人 //貸款編號
	document.add(new Paragraph(14,"                                                                                                              "+ply.getBANKKEY(),FontChinese));
	document.add(new Paragraph(14,"                                                                                                     "+ply.getMTG()+"                                        "+ply.getAGENTUNIT(),FontChinese));
	document.add(new Paragraph(16,"                                                                                                                                                         "+ply.getAGENTEMPNO(),FontChinese));
	// 其他
	document.add(new Paragraph(" "));
	document.add(new Paragraph(" "));
	document.add(new Paragraph(68,"            "+ply.getAGENT().trim()+"               "+ply.getMTG().trim()+"                   "+ply.getBROKER()+"                   "+ply.getEMPA()+"                   "+ply.getEMPA1(),FontChinese));
	document.add(new Paragraph(16,"16.9% + 6%"+"                                                                                                                           "+ply.getSTUNIT().trim()+"                          "+ply.getSEMP1().trim(),FontChinese));
	document.newPage();
	// save first unit
	Unit_Old=Unit_new;

    }

    public void CreatePolicy()
    {
	commit=0;
	try
	{
	    // count total policys
	    getTotalbyte();
	    setJpg("/A.jpg");
	    setPDFTitle();
	    // initialize open document
	    document.open();
	    for(FirePolicyEntity ply1 : obj)
	    {
		ply=ply1;
		Unit_new=ply.getUnitName();
		// create pdf and group by unit
		if(Unit_new.equals(Unit_Old))
		{
		    createParagraph();
		}
		else
		{
		    document.close();
		    path="C:\\"+Unit_new+"-住火要保書"+new ChangeCode().Gettimer()+".pdf";
		    document=new Document(PageSize.A4,72,36,173,-60);
		    PdfWriter.getInstance(document,new FileOutputStream(path));
		    document.open();
		    createParagraph();
		}
		// now work policy and update progressbar
		setProgressBar();
	    }
	    JOptionPane.showMessageDialog(null,"產生完成");
	    dispose();
	    document.close();
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
	    System.gc();
	    dispose();
	}
    }

    public void run()
    {
	CreatePolicy();
    }

}
