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
 * 【基本地震險】
 * @author 50123
 * @version 103.09.16
 * 
 */
// 基本地震險底圖
public class FirePolicyEType extends PrintPolicyBasic<FirePolicyEntity>
{
    public FirePolicyEType(List<FirePolicyEntity> obj)
	    throws DocumentException, IOException
    {
	super(obj);
    }

    private static final long serialVersionUID=6545809586923671725L;

    public void CreatePolicy()
    {
	// Count Total
	for(FirePolicyEntity ply : obj)
	{
	    if(ply.getNEWPLY().substring(4,5).equals(policy_type_A))
		totalBytes=totalBytes+1;
	}
	try
	{

	    setJpg("/S.jpg");
	    path="C:\\S.pdf";
	    document=new Document(PageSize.A4,78,36,180f,-90);
	    writer=PdfWriter.getInstance(document,new FileOutputStream(path));
	    setPDFTitle();
	    document.open();

	    for(FirePolicyEntity ply : obj)
	    {
		if(ply.getNEWPLY().substring(4,5).equals(policy_type_A))
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
		    // Image imgcode =
		    // code39.createImageWithBarcode(cb,null,null);
		    // Image imgQRcode = qrcode.getImage();
		    // imgQRcode.scaleAbsolute(100,20); // 設定大小
		    // imgQRcode.setAbsolutePosition(490,790);// 設定在PDF上的絕對位子
		    // document.add(imgQRcode);
		    document.add(new Paragraph(7,"                                                                                            "+ply.getNEWPLY(),FontChinese));
		    // 要保人
		    document.add(new Paragraph(21,"                   "+ply.getFASRCNAME()+"                                                                                      "+ply.getAREANO()+ply.getADDR().substring(0,15)+"                     ",FontChinese2));
		    document.add(new Paragraph(-12,"                                                                                                                                                                    "+ply.getADDR().substring(15),FontChinese2));
		    document.add(new Paragraph(12,"                                              "+ply.getFASRIDNO()+"                  "+ply.getTEL(),FontChinese));
		    // 被保人
		    document.add(new Paragraph(16,"                   "+ply.getINSDNAME()+"                                                                                       "+ply.getAREANO()+ply.getADDR().substring(0,15)+"                     ",FontChinese2));
		    document.add(new Paragraph(-5,"                                                                                                                                                                     "+ply.getADDR().substring(15),FontChinese2));
		    document.add(new Paragraph(12,"                                              "+ply.getIDNO()+"                  "+ply.getTEL(),FontChinese));
		    // 總保額
		    // String
		    // money=getTotalAmount(ply.getFIREPREM(),ply.getEPREM());
		    // document.add(new
		    // Paragraph(16,"                                  "+ChangeCode.CatZero(ply.getINSDAMT()),FontChinese));
		    // document.add(new
		    // Paragraph(0,"                                                                                                                                                              "+ChangeCode.CatZero(ply.getFIREPREM()),FontChinese));
		    // document.add(new
		    // Paragraph(14,"                                                                                                      "+money,FontChinese));
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
		    // 分項保費建築物
		    // document.add(new
		    // Paragraph(46,"     01                                                                              "+ChangeCode.CatZero(ply.getINSDAMT())+"          "+ply.getRATE().substring(1)+"    1/1        "+ChangeCode.CatZero(ply.getFIREPREM()),FontChinese));
		    // document.add(new
		    // Paragraph(0,"                                                                                                                                                                                     "+getConstClass(ply.getCONSTCLASS()),FontChinese));
		    // 加保動產
		    // document.add(new Paragraph(12,"  ",FontChinese));
		    // document.add(new Paragraph(12,"  ",FontChinese));
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
		    // ProgressBar
		    setProgressBar();
		    broker=ply.getBROKER();
		}
	    }
	    document.close();
	    path=function.ChangeBankName(broker,path,"基本地震","黑白列印");
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

    @Override
    public void run()
    {
	CreatePolicy();
    }
}
