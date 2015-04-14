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

/**
 * 【屋主續保列印】
 * 屋主甲式底圖續保無新保單號
 * @author 50123
 * @version 103.09.16
 * 
 * 
 */
public class FirePolicyWTypeAuto extends PrintPolicyBasic<FirePolicyEntity>
{
    public FirePolicyWTypeAuto(List<FirePolicyEntity> obj)
	    throws DocumentException, IOException
    {
	super(obj);
	// TODO 自動產生的建構子 Stub
    }

    private static final long serialVersionUID=507026536531094819L;

  

    private void createParagraph() throws DocumentException
    {
	document.add(jpeg);
	commit=commit+1;
	// write pdf
	document.add(new Paragraph(16,"                                                                                                "+ply.getNEWPLY(),FontChinese));
	// 要保人
	document.add(new Paragraph(15,"                            "+ply.getFASRCNAME()+"                                                                                         "+ply.getAREANO()+ply.getADDR().substring(0,15),FontChinese2));
	document.add(new Paragraph(0,"                                                                                                       "+CatZero(ply.getFBIRTH().substring(0,3))+"       "+CatZero(ply.getFBIRTH().substring(3,5))+"        "+CatZero(ply.getFBIRTH().substring(5,7)),FontChinese));
	document.add(new Paragraph(7,"                                                                                                                                                                                "+ply.getADDR().substring(15),FontChinese2));
	document.add(new Paragraph(12,"                                                     "+ply.getFASRIDNO()+"                   "+ply.getTEL(),FontChinese));
        //關係   
	document.add(new Paragraph(0,"                                                                                                                                                                                                                                                                "+transRelate(ply.getRELATE()),FontChinese1));
	
	if(ply.getFSEX().equals("1"))
	{
	    document.add(new Paragraph(0,"V",FontChinese));
	}
	else if(ply.getFSEX().equals("2"))
	{
	    document.add(new Paragraph(0,"           V",FontChinese));
	}
	// 被保人
	document.add(new Paragraph(18,"                            "+ply.getINSDNAME()+"                                                                                          "+ply.getAREANO()+ply.getADDR().substring(0,15),FontChinese2));
	document.add(new Paragraph(0,"                                                                                                       "+CatZero(ply.getBIRTH().substring(0,3))+"       "+CatZero(ply.getBIRTH().substring(3,5))+"        "+CatZero(ply.getBIRTH().substring(5,7)),FontChinese));
	document.add(new Paragraph(7,"                                                                                                                                                                                "+ply.getADDR().substring(15),FontChinese2));
	document.add(new Paragraph(12,"                                                     "+ply.getIDNO()+"                   "+ply.getTEL(),FontChinese));
	if(ply.getSEX().equals("1"))
	{
	    document.add(new Paragraph(0,"V",FontChinese));
	}
	else if(ply.getSEX().equals("2"))
	{
	    document.add(new Paragraph(0,"           V",FontChinese));
	}
	// 保險期間
	effect_date=Integer.parseInt(ply.getEXPDAY().substring(0,3));
	expire_date=1+Integer.parseInt(ply.getEXPDAY().substring(0,3));
	document.add(new Paragraph(14,"                           12                             "+effect_date+"       "+ply.getEXPDAY().substring(3,5)+"         "+ply.getEXPDAY().substring(5)+"                                              "+expire_date+"       "+ply.getEXPDAY().substring(3,5)+"          "+ply.getEXPDAY().substring(5),FontChinese));
	// 標的物地址
	document.add(new Paragraph(28,"                        "+ply.getLOCATION().trim()+"     "+ply.getAREA(),FontChinese));
	// 建築
	document.add(new Paragraph(32,"                             "+"　"+"               "+"　　　"+"                                                                                                                                                                              "+ply.getFLOOR()+"                            "+getConstClass(ply.getCONSTCLASS()),FontChinese1));
	document.add(new Paragraph("                             "+"                                                     "+ply.getSTRUYEAR(),FontChinese1));
	document.add(new Paragraph(0,"                                                                                                       "+ply.getCONSTMARK(),FontChinese1));
	document.add(new Paragraph("                             "+"                                                               "+CatZero(ply.getAREASIZE()),FontChinese1));
	// 建築物
	document.add(new Paragraph(" "));
	document.add(new Paragraph(" "));
	document.add(new Paragraph(" "));
	document.add(new Paragraph(" "));
	document.add(new Paragraph(22," "));
	// 火險保費及金額
	document.add(new Paragraph(0,"        　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　"+ChangeCode.CatZero(ply.getINSDAMT().substring(0,5)),FontChinese));
	// 火險費率
	document.add(new Paragraph(0,"        　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　"+ply.getRATE().substring(1),FontChinese));
	// 火險保費
	document.add(new Paragraph(0,"        　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　"+ChangeCode.CatZero(ply.getFIREPREM()),FontChinese));
	// 竊盜
	document.add(new Paragraph(62,"        　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　"+ply.getRATE7(),FontChinese));
	document.add(new Paragraph(0,"        　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　"+ChangeCode.CatZero(ply.getPREM7()),FontChinese));
	// 竊盜險保額
	document.add(new Paragraph(20,"                                                                                                    "+get7lowAmount(ply.getAMT7()),FontChinese));
	document.add(new Paragraph(10,"                                                                                                    "+get7highAmount(ply.getAMT7()),FontChinese));
	// 基本震保額
	document.add(new Paragraph(14,"        　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　"+ChangeCode.CatZero(ply.getEAMT().substring(0,5)),FontChinese));
	// 基本地震費率
	document.add(new Paragraph(0,"        　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　0.9",FontChinese));
	// 基本地震保費
	document.add(new Paragraph(0,"        　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　"+ChangeCode.CatZero(ply.getEPREM()),FontChinese));
	// 訪客及第三人
	document.add(new Paragraph(28,"        　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　"+getDeathAmount(ply.getAMTV().substring(0,5)),FontChinese));
	document.add(new Paragraph(12,"        　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　"+getProductAmount(ply.getAMTV().substring(0,5)),FontChinese));
	document.add(new Paragraph(0,"        　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　",FontChinese));
	document.add(new Paragraph(0,"        　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　"+ply.getPREMV(),FontChinese));
	document.add(new Paragraph(12,"        　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　"+ChangeCode.CatZero(ply.getAMTV().substring(0,5)),FontChinese));
	// 自動續保
	document.add(new Paragraph(" "));
	// 貸款戶別
	document.add(new Paragraph(" "));
	// 抵押權人
	document.add(new Paragraph(" "));
	document.add(new Paragraph(" "));
	document.add(new Paragraph(18,"                                   "+ply.getMTG()+"                                   "+ply.getAGENTUNIT(),FontChinese));
	// 行員代號
	document.add(new Paragraph(12,"                                                                                  "+ply.getAGENTEMPNO(),FontChinese));
	// 貸款編號
	document.add(new Paragraph(0,"                                                                                                                         "+ply.getBANKKEY(),FontChinese));
	// 附註
	document.add(new Paragraph(14,"                           本保險單承保建築物之範圍係以抵押權者抵押部分為限。",FontChinese));
	document.add(new Paragraph(" "));
	document.add(new Paragraph(" "));
	document.add(new Paragraph(" "));
	document.add(new Paragraph(" "));
	document.add(new Paragraph(26,"            "+ply.getAGENT().trim()+"                   "+ply.getMTG().trim()+"                   "+ply.getBROKER()+"                   "+ply.getEMPA()+"                   "+ply.getEMPA1(),FontChinese));
	document.add(new Paragraph(12,"16.9% + 6%"+"                                                                                                                           "+ply.getSTUNIT().trim()+"                          "+ply.getSEMP1().trim(),FontChinese));
	document.newPage();
	// save first pdf
	Unit_Old=Unit_new;
    }

    public void CreatePolicy()
    {
	commit=0;
	try
	{
	    // Count Total File
	    getTotalbyte();
	    setJpg("/WR.jpg");
	    setPDFTitle();
	    document.open();
	    for(FirePolicyEntity ply1 : obj)
	    {
		ply=ply1;
		Unit_new=ply.getUnitName();
		if(Unit_new.equals(Unit_Old))
		{
		    createParagraph();
		}
		else
		{ // close doucment
		    document.close();
		    path="C:\\"+Unit_new+"-屋主要保書"+new ChangeCode().Gettimer()+".pdf";
		    document=new Document(PageSize.A4,52,36,125,0);
		    PdfWriter.getInstance(document,new FileOutputStream(path));
		    document.open();
		    createParagraph();
		}
		setProgressBar();
	    }
	    System.gc();
	    JOptionPane.showMessageDialog(null,"產生完成");
	    dispose();
	    document.close();
	}
	catch (Exception e1)
	{
	    System.out.println("錯了"+e1.toString());
	    document.close();
	    System.gc();
	    deleteFile(path);
	    ErrorMessage(e1);
	    dispose();
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
