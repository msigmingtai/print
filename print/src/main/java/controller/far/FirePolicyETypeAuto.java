package controller.far;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.swing.JOptionPane;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.Barcode39;
import com.lowagie.text.pdf.PdfWriter;

import commans.basic.PrintPolicyBasic;
import commans.function.ChangeCode;
import model.entity.FirePolicyEntity;

/**
 * @author 50123
 * @version 104.02.10 依分公司產生PDF檔
 */
// 基本地震險底圖
public class FirePolicyETypeAuto extends PrintPolicyBasic<FirePolicyEntity>
{
    public FirePolicyETypeAuto(List<FirePolicyEntity> obj)
	    throws DocumentException, IOException
    {
	super(obj);
    }

    private static final long serialVersionUID=6545809586923671725L;

    private void createParagraph() throws DocumentException
    {
	// 載入圖檔
	document.add(jpeg);
	// 寫入PDF
	// 保單號
	// 保單號
	// BarcodeQRCode qrcode = new
	// BarcodeQRCode(ply.getNEWPLY(),1,1,null);
	Barcode39 code39=new Barcode39();
	code39.setCode(ply.getNEWPLY());
	document.add(new Paragraph(7,"                                                                                            "+ply.getNEWPLY(),FontChinese));
	// 被保人
	document.add(new Paragraph(21,"                   "+ply.getFASRCNAME()+"                                                                                      "+ply.getAREANO()+ply.getADDR().substring(0,15)+"                     ",FontChinese2));
	document.add(new Paragraph(-12,"                                                                                                                                                                    "+ply.getADDR().substring(15),FontChinese2));
	document.add(new Paragraph(12,"                                              "+ply.getFASRIDNO()+"                  "+ply.getTEL(),FontChinese));
	// 要保人
	document.add(new Paragraph(16,"                   "+ply.getINSDNAME()+"                                                                                       "+ply.getAREANO()+ply.getADDR().substring(0,15)+"                     ",FontChinese2));
	document.add(new Paragraph(-5,"                                                                                                                                                                     "+ply.getADDR().substring(15),FontChinese2));
	document.add(new Paragraph(12,"                                              "+ply.getIDNO()+"                  "+ply.getTEL(),FontChinese));
	document.add(new Paragraph(30,"                                                                                                                                                     "+ChangeCode.CatZero(ply.getEPREM()),FontChinese));
	// 地震險保額險
	document.add(new Paragraph(0,"                                  "+ChangeCode.CatZero(ply.getEAMT())+"                                                                                                                    ",FontChinese));

	// 保險期間
	expire_date=1+Integer.parseInt(ply.getEXPDAY().substring(0,3));
	document.add(new Paragraph(30,"                  12                       "+ply.getEXPDAY().substring(0,3)+"         "+ply.getEXPDAY().substring(3,5)+"        "+ply.getEXPDAY().substring(5)+"                                        "+expire_date+"       "+ply.getEXPDAY().substring(3,5)+"        "+ply.getEXPDAY().substring(5),FontChinese));
	// 標的物地址
	document.add(new Paragraph(28,"                   "+ply.getLOCATION().trim()+ply.getAREA(),FontChinese));
	// 建築物
	document.add(new Paragraph(28,"             "+"　"+"               "+"　　"+"                                                                                                                                                                                     "+ply.getFLOOR()+"                              "+getConstClass(ply.getCONSTCLASS()),FontChinese1));
	// 建築年份
	document.add(new Paragraph(14,"                                                                           "+ply.getSTRUYEAR(),FontChinese1));
	document.add(new Paragraph(0,"                                                                                               "+ply.getCONSTMARK(),FontChinese1));
	document.add(new Paragraph("                                                                                "+CatZero(ply.getAREASIZE()),FontChinese1));
	document.add(new Paragraph(60,"   ",FontChinese));
	// 基本地震
	document.add(new Paragraph(20,"     01                                                                              "+ChangeCode.CatZero(ply.getEAMT())+"          "+"0.9        "+"1/1        "+ChangeCode.CatZero(ply.getEPREM()),FontChinese));
	document.add(new Paragraph(0,"                                                                                                                                                                                     "+getConstClass(ply.getCONSTCLASS()),FontChinese));
	// 備註
	document.add(new Paragraph(62,"       自101年1月1日起，住宅地震基本保險最高賠償責任調整為新台幣150萬元。",FontChinese1));
	// 抵押權人 //貸款編號
	document.add(new Paragraph(30,"                                                                                                              "+ply.getBANKKEY(),FontChinese));
	document.add(new Paragraph(14,"                                                                                                   "+ply.getMTG()+"                                        "+ply.getAGENTUNIT(),FontChinese));
	document.add(new Paragraph(16,"                                                                                                                                                         "+ply.getAGENTEMPNO(),FontChinese));
	// 其他
	document.add(new Paragraph(" "));
	document.add(new Paragraph(" "));
	document.add(new Paragraph(92,"            "+ply.getAGENT().trim()+"               "+ply.getMTG().trim()+"                   "+ply.getBROKER()+"                   "+ply.getEMPA()+"                   "+ply.getEMPA1(),FontChinese));
	document.add(new Paragraph(16,"16.9% + 6%"+"                                                                                                                           "+ply.getSTUNIT().trim()+"                          "+ply.getSEMP1().trim(),FontChinese));
	document.newPage();
	Unit_Old=Unit_new;
    }

    public void CreatePolicy()
    {
	// Count Total

	try
	{
	    getTotalbyte();
	    setJpg("/S.jpg");
	    path="C:\\S.pdf";
	    setPDFTitle();
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
		    path="C:\\"+Unit_new+"-基本地震險要保書"+new ChangeCode().Gettimer()+".pdf";
		    document=new Document(PageSize.A4,78,36,180f,-90);
		    PdfWriter.getInstance(document,new FileOutputStream(path));
		    document.open();
		    createParagraph();
		}
		// now work policy and update progress bar
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
	    dispose();
	}
    }

    @Override
    public void run()
    {
	CreatePolicy();
    }
}
