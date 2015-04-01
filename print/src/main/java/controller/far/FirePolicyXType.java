package controller.far;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import javax.swing.JOptionPane;
import model.entity.FirePolicyEntity;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.Document;
import commans.basic.PrintPolicyBasic;
import commans.function.ChangeCode;

//屋主丙式
public class FirePolicyXType extends PrintPolicyBasic<FirePolicyEntity>
{

    private static final long serialVersionUID=5399752677672773311L;

    public FirePolicyXType(List<FirePolicyEntity> obj)
	    throws DocumentException, IOException
    {
	super(obj);

    }

    private static File Obj;
    private static String path="C:\\X.pdf";

    public void SetObj(File path)
    {
	Obj=path;
    }

    public void CreatePolicy(File obj) throws IOException
    {
	try
	{
	    // System.out.println(Thread.currentThread().getName());
	    // 套表 產出檔

	    document=new Document(PageSize.A4,72,36,158,-100);
	    jpeg=Image.getInstance(getClass().getResource("/X.jpg"));
	    jpeg.setAlignment(Image.UNDERLYING);
	    jpeg.scaleAbsolute(590,840);
	    jpeg.setAbsolutePosition(0,0);
	    PdfWriter.getInstance(document,new FileOutputStream(path));
	    setPDFTitle();
	    document.open();
	    isr=new InputStreamReader(new FileInputStream(obj.getPath()),"ISO8859_1");
	    br=new BufferedReader(isr);
	    // Count Total File
	    while ((Line=br.readLine())!=null)
	    {
		count=count+1;
	    }
	    totalBytes=count;
	    // 選擇檔案
	    isr=new InputStreamReader(new FileInputStream(obj.getPath()),"ISO8859_1");
	    br=new BufferedReader(isr);
	    while ((Line=br.readLine())!=null)
	    {
		commit=commit+1;
		document.add(jpeg);

		row[0]=Line.substring(0,40);// 銀行key
		row[1]=Line.substring(40,70);// 被保人
		row[2]=Line.substring(70,80);// 被保人ID
		row[3]=Line.substring(80,90);// 舊保單號
		row[4]=Line.substring(90,97);// 到期日
		row[5]=Line.substring(97,106);// 火險保額
		row[6]=Line.substring(106,113);// 火險保費
		row[7]=Line.substring(113,122);// 地震保額
		row[8]=Line.substring(122,129);// 地震保費
		row[9]=Line.substring(129,130);// 貸款還清記號
		row[10]=Line.substring(130,146);// 扣款帳號
		row[11]=Line.substring(146,153);// 扣款日期
		row[12]=Line.substring(153,156);// 通訊郵遞區號
		row[13]=Line.substring(156,216);// 通訊地址
		row[14]=Line.substring(216,226);// 通訊電話
		row[15]=Line.substring(226,306);// 標的地址
		row[16]=Line.substring(306,311);// 標的郵遞區號
		row[17]=Line.substring(311,315);// 建築結構
		row[18]=Line.substring(315,317);// 樓層數
		row[19]=Line.substring(317,318);// 建築等級
		row[20]=Line.substring(318,326);// 坪數
		row[21]=Line.substring(326,329);// 建築年份
		row[22]=Line.substring(329,339);// 分行別
		row[23]=Line.substring(339,350);// 行員代號
		row[24]=Line.substring(350,357);// 銀行代號
		row[25]=Line.substring(357,362);// 經手人一
		row[26]=Line.substring(362,363);// 經手人一檢核
		row[27]=Line.substring(363,365);// 統計單位
		row[28]=Line.substring(365,367);// 經手公司代號
		row[29]=Line.substring(367,368);// 業務來源
		row[30]=Line.substring(368,374);// 簽單費率
		row[31]=Line.substring(374,376);// 中歸屬
		row[32]=Line.substring(376,406);// 要保人
		row[33]=Line.substring(406,416);// 要保人ID
		row[34]=Line.substring(416,421);// 服務人
		row[35]=Line.substring(421,431);// 新保單號

		row[1]=function.ChgChCode(row[1],row[3]);
		row[13]=function.ChgChCode(row[13],row[3]);
		row[15]=function.ChgChCode(row[15],row[3]);
		row[32]=function.ChgChCode(row[32],row[3]);
		// 寫入PDF
		// 保單號
		// *****************保單號********************************************
		document.add(new Paragraph(22,"                                  "+row[35].substring(0,2)+"       "+row[35].substring(2,10)+"                               "+row[3],FontChinese));
		// 要保人
		str1=row[13].trim().substring(0,15);
		str2=row[13].trim().substring(15);
		document.add(new Paragraph(14,"                "+row[32]+"                                                                            "+row[12]+str1,FontChinese2));
		document.add(new Paragraph(8,"                                                                                                                                                        "+str2,FontChinese2));
		document.add(new Paragraph(12,"                                           "+row[33]+"            "+row[14],FontChinese));
		// 被保人
		str1=row[13].trim().substring(0,15);
		str2=row[13].trim().substring(15);
		document.add(new Paragraph(10,"                "+row[1]+"                                                                             "+row[12]+str1,FontChinese2));
		document.add(new Paragraph(5,"                                                                                                                                                        "+str2,FontChinese2));
		document.add(new Paragraph(10,"                                           "+row[2]+"            "+row[14],FontChinese));
		// 保險期間
		expire_date=1+Integer.parseInt(row[4].substring(0,3));
		document.add(new Paragraph(20,"                  12                           "+row[4].substring(0,3)+"       "+row[4].substring(3,5)+"       "+row[4].substring(5)+"                                                  "+expire_date+"       "+row[4].substring(3,5)+"       "+row[4].substring(5),FontChinese));
		// 標的物地址
		document.add(new Paragraph(30,"                  "+row[15]+row[16],FontChinese));
		// 建築
		// System.out.println(row[17]);
		for(int i=0; i<=13; i++)
		{
		    str3[i]="  ";
		}
		try
		{
		    num1=Integer.parseInt(row[19]);
		}
		catch (NumberFormatException e2)
		{
		    num1=0;
		}
		if(num1==1)
		    str4="A1";
		else if(num1==2)
		    str4="A2";
		else if(num1==3)
		    str4="B";
		else if(num1==4)
		    str4="C";

		document.add(new Paragraph(0,"                     "+str3[0]+"               "+str3[1]+str3[2],FontChinese1));
		document.add(new Paragraph("                     "+str3[3]+"               "+str3[4]+str3[5]+str3[6]+str3[7],FontChinese1));
		document.add(new Paragraph(12," "));
		document.add(new Paragraph("                     "+str3[8]+"               "+str3[9]+str3[10]+str3[11]+"                                                                                                                                                                              "+row[18]+"                            "+str4,FontChinese1));
		document.add(new Paragraph("                     "+"                                                     "+row[21]+str3[12]+str3[13],FontChinese1));
		document.add(new Paragraph(0,"                                                                                               "+row[17],FontChinese1));
		document.add(new Paragraph("                     "+"                                                               "+CatZero(row[20]),FontChinese1));
		document.add(new Paragraph(" "));
		document.add(new Paragraph(28,"                                                                                                                                                 "+ChangeCode.CatZero(row[5]),FontChinese));
		document.add(new Paragraph(0,"                                                                                                                                                                            "+ChangeCode.CatZero(row[6]),FontChinese));
		// 第三人
		document.add(new Paragraph(28,"                                                                                                                                                 "+ChangeCode.CatZero(5000000),FontChinese));
		document.add(new Paragraph(26,"                                                                                                                                                 "+ChangeCode.CatZero(10000000),FontChinese));
		document.add(new Paragraph(0,"                                                                                                                                                                            "+ChangeCode.CatZero(1156),FontChinese));
		// 基本地震
		document.add(new Paragraph(24,"                                                                                                                                                 "+ChangeCode.CatZero(row[7]),FontChinese));
		document.add(new Paragraph(0,"                                                                                                                                                                            "+ChangeCode.CatZero(row[8]),FontChinese));
		// 輕損地震
		document.add(new Paragraph(26,"                                                                                                                                                 "+ChangeCode.CatZero(300000),FontChinese));
		document.add(new Paragraph(0,"                                                                                                                                                                            "+ChangeCode.CatZero(375),FontChinese));
		// 總保費***********1011113修正保費************************************************/
		num1=1531;
		try
		{
		    if(row[6].trim()!=null)
		    {
			num1=num1+Integer.parseInt(row[6].trim());
		    }
		}
		catch (NumberFormatException e2)
		{
		    ErrorMessage(e2);
		}
		try
		{
		    if(row[8].trim()!=null)
		    {
			num1=num1+Integer.parseInt(row[8].trim());
		    }
		}
		catch (NumberFormatException e2)
		{
		    ErrorMessage(e2);
		}
		document.add(new Paragraph(16,"                                                                                                                                                                             "+ChangeCode.CatZero(num1),FontChinese));
		// 自動續保
		document.add(new Paragraph(" "));
		// 貸款戶別
		document.add(new Paragraph(" "));
		// 抵押權人
		document.add(new Paragraph(" "));
		document.add(new Paragraph(16,"                      "+row[24]+"                                      "+row[22],FontChinese));
		document.add(new Paragraph("                                                                         "+row[23],FontChinese));
		document.add(new Paragraph(0,"                                                                                                           "+row[0],FontChinese));
		// 附註
		document.add(new Paragraph(" "));
		document.add(new Paragraph(" "));
		document.add(new Paragraph(" "));
		document.add(new Paragraph(92,"            "+row[29]+"               "+row[24]+"                   "+row[28]+"                   "+row[25]+"                   "+row[26]+"                         "+row[27]+"                      "+row[34],FontChinese));
		document.add(new Paragraph(12,"16.9% + 6%",FontChinese));
		document.newPage();
		// ProgressBar
		setProgressBar();
	    }
	    broker=row[28];
	    br.close();
	    document.close();
	    System.gc();
	    path=function.ChangeBankName(broker,path,"屋主丙式","");
	    JOptionPane.showMessageDialog(null,path);
	    dispose();
	}
	catch (Exception e1)
	{
	    document.close();
	    System.gc();
	    deleteFile(path);
	    ErrorMessage(e1);
	    dispose();
	}

    }

    @Override
    public void run()
    {

	try
	{
	    CreatePolicy(Obj);
	}
	catch (IOException e)
	{
	    // TODO 自動產生的 catch 區塊
	    e.printStackTrace();
	}

    }

    @Override
    public void CreatePolicy()
    {
	// TODO 自動產生的方法 Stub

    }
}
