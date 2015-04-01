package controller.far;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.MalformedURLException;
import java.util.Calendar;
import javax.swing.*;

import view.main.MainFrame;

import com.lowagie.text.BadElementException;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.Document;

import commans.function.ChangeCode;

public class FirePolicyPrintBeta extends JFrame implements ActionListener,
	Serializable
{

    File insertFile=null;
    File deleteFile=null;
    String path;
    String pathImage=".";
    InputStreamReader isr;
    BufferedReader br;
    private static final long serialVersionUID=1L;
    // 判別是否有選取檔案
    int ret=1;
    // 開啟檔案對話框
    JFileChooser fc=new JFileChooser();

    // 產生按鈕
    JButton OpenButton=new JButton("開啟");
    JButton CreatPDFButton=new JButton("產生列印檔");
    JButton CloseButton=new JButton("關閉");
    JLabel lb=new JLabel("請選擇要保書列印資料檔 ",JLabel.LEFT);
    JLabel lb1=new JLabel("請選擇要保書列種類",JLabel.LEFT);
    static JComboBox<String> cb=new JComboBox<String>();
    static JComboBox<String> cb1=new JComboBox<String>();
    static JLabel lb2=new JLabel("請選擇列印方式及顏色 ",JLabel.LEFT);
    static String baseA="基本住宅地震";
    static String baseB="基本住宅甲式";
    static String homeA="綜合屋主甲式";
    static String homeB="綜合屋主丙式";
    static String basePrint="套表列印";
    static String BWPrint="黑白列印";
    static String ColorPrint="彩色列印";
    // 產生日期
    static Calendar cal=Calendar.getInstance();
    static int day=cal.get(Calendar.DATE);
    static int month=cal.get(Calendar.MONTH)+1;
    static int year=cal.get(Calendar.YEAR);
    static int hour=cal.get(Calendar.HOUR);
    static int minute=cal.get(Calendar.MINUTE);
    static int second=cal.get(Calendar.SECOND);
    static String time=Integer.toString(year)+"-"+Integer.toString(month)+"-"+Integer.toString(day);
    // Integer.toString(hour)+ Integer.toString(minute)+second;
    // 定義document
    Document document=null;
    String Line=null;
    String str1=null;
    String str2=null;
    String str3[]=new String[14];
    String str4=null;
    String[] row=new String[36];
    int num1=0;
    int num2=0;
    Image jpeg=null;
    static String broker;
    ChangeCode function=new ChangeCode();//
    String PolicyType=null;

    public FirePolicyPrintBeta()
    {
	// windosw logo
	String icon="Logo.PNG";
	ImageIcon img=new ImageIcon(getClass().getResource("/"+icon));
	this.setIconImage(img.getImage());
	Container cp=getContentPane();
	getContentPane().setLayout(null);
	lb.setBounds(10,10,153,20);
	cp.add(lb);
	OpenButton.setBounds(163,5,75,25);
	cp.add(OpenButton);
	OpenButton.addActionListener(this);
	lb1.setBounds(10,45,119,15);
	cp.add(lb1);
	cb.setBounds(163,74,99,23);
	// cb.addItem("");
	cb.addItem(baseA);
	// cb.addItem(baseB);
	cb.addItem(homeA);
	cb.addItem(homeB);
	cp.add(cb);
	lb2.setBounds(10,78,143,15);
	cp.add(lb2);
	cb1.setBounds(163,41,75,23);
	cb1.addItem(basePrint);
	// cb1.addItem(ColorPrint);
	cb1.addItem(BWPrint);
	cp.add(cb1);
	CreatPDFButton.setBounds(20,103,109,25);
	cp.add(CreatPDFButton);
	CreatPDFButton.addActionListener(this);
	CloseButton.setBounds(139,103,75,25);
	cp.add(CloseButton);
	CloseButton.addActionListener(this);
	setSize(280,180); // WindowsSize
	setLocationRelativeTo(null); // Center
	setResizable(false); // LockWindows
	setTitle("火險要保書列印");
	setVisible(true);
	addWindowListener(new WindowAdapter()
	{
	    public void windowClosing(WindowEvent e)
	    {
		// System.exit(0);
		try
		{
		    new MainFrame().SetOpenBoxFlag(true);
		}
		catch (Exception e1)
		{
		    JOptionPane.showMessageDialog(null,"發錯誤\n"+e1.toString());
		    e1.printStackTrace();
		}
		dispose();
	    }
	});
    }

    public void actionPerformed(ActionEvent e)
    {

	fc.setCurrentDirectory(new java.io.File("C:\\workf")); // 開始檔案對話窗欲設路徑
	if(e.getSource()==OpenButton)
	{ // 建立檔案對話方塊
	    ret=fc.showOpenDialog(null);
	    insertFile=fc.getSelectedFile();
	}
	else if(e.getSource()==CloseButton)
	{
	    try
	    {
		new MainFrame().SetOpenBoxFlag(true);
	    }
	    catch (Exception e1)
	    {
		JOptionPane.showMessageDialog(null,"發錯誤\n"+e1.toString());
		e1.printStackTrace();
	    }
	    dispose();
	}
	else
	{

	    // 按下[開啟]鍵後，顯示檔案的路徑
	    if(ret==JFileChooser.APPROVE_OPTION)
	    {
		// tf.setText(insertFile.getPath());
		try
		{
		    if(insertFile.exists())
		    {
			if(cb1.getSelectedItem().equals(basePrint))
			{
			    // System.out.println(cb.getSelectedItem().toString());
			    PrintNobackground(cb.getSelectedItem().toString());
			}
			else
			{

			    // System.out.println(cb.getSelectedItem().toString());
			    PrintPolicy(cb.getSelectedItem().toString());
			}
			isr.close();
			br.close();
			System.gc();
			document.close();
			path=function.ChangeBankName(broker,path,cb1.getSelectedItem().toString(),cb.getSelectedItem().toString());
			JOptionPane.showMessageDialog(null,path);

			// 執行完畢後開啟PDF檔/
			// Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler "
			// + path);
		    }

		}
		catch (java.io.FileNotFoundException e1)
		{
		    JOptionPane.showMessageDialog(null," 無法產生PDF檔\n  請關閉"+path+"檔後執行");
		    System.gc();
		}
		catch (IOException e1)
		{
		    JOptionPane.showMessageDialog(null,"檔案錯誤!!");
		    System.gc();

		}
		catch (java.lang.NumberFormatException e1)
		{
		    JOptionPane.showMessageDialog(null," 選擇的檔案錯誤!\n  請重新選擇");
		    try
		    {
			isr.close();
			br.close();
		    }
		    catch (IOException e2)
		    {
			e2.printStackTrace();
		    }
		    deleteFile=new File(path);
		    deleteFile.delete();
		}
		catch (StringIndexOutOfBoundsException e1)
		{
		    JOptionPane.showMessageDialog(null," 選擇的檔案錯誤!\n  請重新選擇");
		    try
		    {
			isr.close();
			br.close();
		    }
		    catch (IOException e2)
		    {

			e2.printStackTrace();
		    }
		    deleteFile=new File(path);
		    deleteFile.delete();
		}
		catch (java.lang.NullPointerException e2)
		{
		    JOptionPane.showMessageDialog(null,"找不到"+cb.getSelectedItem().toString()+".JPG");
		    System.gc();

		}
	    }
	    else
	    {
		JOptionPane.showMessageDialog(null,"無匯入FAP554F檔案");
	    }
	}
    }

    // ********NoBackground*****************************
    public void PrintNobackground(String PolicyType)
    {

	try
	{

	    if(PolicyType.equals(baseA))
	    {
		path="C:\\"+baseA+basePrint+time+".pdf";
		document=new Document(PageSize.A4,72,36,173,-50);// 上下左右
		PdfWriter.getInstance(document,new FileOutputStream(path));
	    }
	    if(PolicyType.equals(baseB))
	    {
		path="C:\\"+baseB+basePrint+time+".pdf";
		document=new Document(PageSize.A4,72,36,178,0);
		PdfWriter.getInstance(document,new FileOutputStream(path));
		System.out.println(PolicyType+"="+baseB);
	    }
	    if(PolicyType.equals(homeA))
	    {
		path="C:\\"+homeA+basePrint+time+".pdf";
		document=new Document(PageSize.A4,72,36,125,0);
		PdfWriter.getInstance(document,new FileOutputStream(path));
	    }
	    if(PolicyType.equals(homeB))
	    {
		path="C:\\"+homeB+basePrint+time+".pdf";
		document=new Document(PageSize.A4,72,36,158,0);
		PdfWriter.getInstance(document,new FileOutputStream(path));
	    }

	    // 設定字型
	    BaseFont bfChinese=BaseFont.createFont("C:\\windows\\fonts\\MINGLIU.TTC,1",BaseFont.IDENTITY_H,BaseFont.NOT_EMBEDDED);
	    Font FontChinese=new Font(bfChinese,10,Font.NORMAL);
	    Font FontChinese1=new Font(bfChinese,7,Font.NORMAL);
	    Font FontChinese2=new Font(bfChinese,8,Font.NORMAL);
	    document.addTitle("MINGTAI FIRE INSURANCE APPLICATION");
	    document.addAuthor("MINGTAI");
	    document.addSubject("MINGTAI");
	    document.addKeywords("MINGTAI");
	    document.addCreator("MINGTAI");
	    document.open();

	    isr=new InputStreamReader(new FileInputStream(insertFile.getPath()),"ISO8859_1");
	    br=new BufferedReader(isr);

	    while ((Line=br.readLine())!=null)
	    {

		row[0]=Line.substring(0,40);// 銀行資料ＫＥＹ 40
		row[1]=Line.substring(40,70);// 被保險人姓名 70
		row[2]=Line.substring(70,80);// 被保險人ＩＤ 80
		row[3]=Line.substring(80,90);// 舊保單號 90
		row[4]=Line.substring(90,97);// 保單到期日 97
		row[5]=Line.substring(97,106);// 續保火險保額
		row[6]=Line.substring(106,113);// 續保火險保費 113;
		row[7]=Line.substring(113,122);// 續保基本地震險保額 122
		row[8]=Line.substring(122,129);// 續保基本地震險保費 129
		row[9]=Line.substring(129,130);// 銀行貸款還清記號 130
		row[10]=Line.substring(130,146);// 扣款帳號 146
		row[11]=Line.substring(146,153);// 扣款日期 153
		row[12]=Line.substring(153,156);// 通訊郵遞區號 156
		row[13]=Line.substring(156,216);// 被保險人通訊地址 216
		row[14]=Line.substring(216,226);// 被保險人電話 226
		row[15]=Line.substring(226,306);// 標的物地址 306
		row[16]=Line.substring(306,311);// 郵遞區號 311
		row[17]=Line.substring(311,315);// 建築物結構 315
		row[18]=Line.substring(315,317);// 樓層數 317
		row[19]=Line.substring(317,318);// 建築等級 318
		row[20]=Line.substring(318,326);// 坪數 326
		row[21]=Line.substring(326,329);// 建築年份 329
		row[22]=Line.substring(329,339);// 分行別 339
		row[23]=Line.substring(339,350);// 行員代號 350
		row[24]=Line.substring(350,357);// 銀行代號 357
		row[25]=Line.substring(357,362);// 經手人員一 362
		row[26]=Line.substring(362,363);// 經手人員一檢核碼 363
		row[27]=Line.substring(363,365);// 統計單位 365
		row[28]=Line.substring(365,367);// 經紀公司代號 367
		row[29]=Line.substring(367,368);// 業務來源 368
		row[30]=Line.substring(368,374);// 簽單費率 374
		row[31]=Line.substring(374,376);// 統計單位中歸屬 376
		row[32]=Line.substring(376,406);// 要保人姓名 406
		row[33]=Line.substring(406,416);// 要保人ＩＤ 416
		row[34]=Line.substring(416,421);// 服務人 421
		row[35]=Line.substring(421,431);// 新保單號 431
		/*********** 新欄位 ************************************************/
		// row[35] = Line.substring(421,431);//新保單號 432
		// row[35] = Line.substring(421,431);//新保單號 43
		// row[35] = Line.substring(421,431);//新保單號 431
		// row[35] = Line.substring(421,431);//新保單號 431
		// row[35] = Line.substring(421,431);//新保單號 431
		// row[35] = Line.substring(421,431);//新保單號 431
		// 中文轉碼
		row[1]=new String(row[1].getBytes("ISO8859_1"),"MS950");
		row[13]=new String(row[13].getBytes("ISO8859_1"),"MS950");
		row[15]=new String(row[15].getBytes("ISO8859_1"),"MS950");
		row[32]=new String(row[32].getBytes("ISO8859_1"),"MS950");

		// 寫入PDF
		row[0]=row[0].trim();
		row[1]=function.ChangeCodeData(row[1]);
		row[32]=function.ChangeCodeData(row[32]);
		// 印表
		// ***************基本住宅*****************************
		if(PolicyType.equals(baseA))
		{
		    // 保單號
		    document.add(new Paragraph(-10,"                        "+row[35].substring(0,2)+"         "+row[35].substring(2,10)+"                                       "+row[3]+"                                  ",FontChinese));
		    // document.add(new
		    // Paragraph("                                                                            "+row[35]+"                                  ",FontChinese));
		    // 被保人
		    str1=row[13].trim().substring(0,15);
		    str2=row[13].trim().substring(15);
		    document.add(new Paragraph(21,"                   "+row[1]+"                                                                                             "+row[12]+str1+"                     ",FontChinese2));
		    document.add(new Paragraph(-12,"                                                                                                                                                                          "+str2,FontChinese2));
		    document.add(new Paragraph(12,"                                               "+row[2]+"                   "+row[14],FontChinese));
		    // 要保人
		    str1=row[13].trim().substring(0,15);
		    str2=row[13].trim().substring(15);
		    document.add(new Paragraph(14,"                   "+row[32]+"                                                                                             "+row[12]+str1+"                     ",FontChinese2));
		    document.add(new Paragraph(-5,"                                                                                                                                                                          "+str2,FontChinese2));
		    document.add(new Paragraph(12,"                                              "+row[33]+"                   "+row[14],FontChinese));
		    // 總保額
		    num1=0;
		    try
		    {
			if(row[6].trim()!=null)
			{
			    num1=num1+Integer.parseInt(row[6].trim());
			}
		    }
		    catch (NumberFormatException e2)
		    {
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
		    }
		    String money=ChangeCode.CatZero(num1);
		    document.add(new Paragraph(16,"                               "+ChangeCode.CatZero(row[5]),FontChinese));
		    document.add(new Paragraph(0,"                                                                                                                                                              "+ChangeCode.CatZero(row[6]),FontChinese));
		    document.add(new Paragraph(13,"                                                                                                      "+money,FontChinese));
		    document.add(new Paragraph(0,"                                                                                                                                                               "+ChangeCode.CatZero(row[8]),FontChinese));
		    document.add(new Paragraph(13,"                               "+ChangeCode.CatZero(row[7])+"                                                                                                                     0",FontChinese));

		    // 保險期間
		    num2=1+Integer.parseInt(row[4].substring(0,3));
		    document.add(new Paragraph(26,"                  12                       "+row[4].substring(0,3)+"         "+row[4].substring(3,5)+"        "+row[4].substring(5)+"                                        "+num2+"       "+row[4].substring(3,5)+"        "+row[4].substring(5),FontChinese));
		    // 標的物地址
		    document.add(new Paragraph(27,"                   "+row[15]+row[16],FontChinese));
		    // 建築物
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
		    {
			str4="A1";
		    }
		    else if(num1==2)
		    {
			str4="A2";
		    }
		    else if(num1==3)
		    {
			str4="B";
		    }
		    else
		    {
			str4="C";
		    }
		    // document.add(new Paragraph(" "));
		    // 樓層建築等級
		    document.add(new Paragraph(10,"             "+str3[0]+"               "+str3[1]+str3[2],FontChinese1));
		    document.add(new Paragraph("             "+str3[3]+"               "+str3[4]+str3[5]+str3[6]+str3[7],FontChinese1));
		    document.add(new Paragraph("             "+str3[8]+"               "+str3[9]+str3[10]+str3[11]+"                                                                                                                                                                                       "+row[18]+"                              "+str4,FontChinese1));
		    // 建築物年份
		    document.add(new Paragraph("                                                                         "+row[21]+str3[12]+str3[13],FontChinese1));
		    document.add(new Paragraph(0,"                                                                                    "+row[17],FontChinese1));
		    // 建築物坪數
		    document.add(new Paragraph("                                                                                "+row[20],FontChinese1));
		    document.add(new Paragraph(92,"   ",FontChinese));
		    // 分項保費建築物
		    document.add(new Paragraph(20,"    01                                                                                "+ChangeCode.CatZero(row[5])+"         "+row[30].substring(1)+"    1/1        "+ChangeCode.CatZero(row[6]),FontChinese));
		    document.add(new Paragraph(0,"                                                                                                                                                                                       "+str4,FontChinese));
		    // 加保動產
		    document.add(new Paragraph(12,"  ",FontChinese));
		    document.add(new Paragraph(12,"  ",FontChinese));
		    document.add(new Paragraph(12,"          自101年1月1日起，住宅地震基本保險最高賠償責任調整為新台幣150萬元。",FontChinese));
		    // 基本地震
		    document.add(new Paragraph(18,"    02                                                                                "+ChangeCode.CatZero(row[7])+"         "+"0.9        "+"1/1        "+ChangeCode.CatZero(row[8]),FontChinese));
		    document.add(new Paragraph(0,"                                                                                                                                                                                       "+str4,FontChinese));
		    // 備註
		    document.add(new Paragraph(" "));
		    document.add(new Paragraph(" "));
		    document.add(new Paragraph(" "));
		    // 貸款編號
		    document.add(new Paragraph(8,"                                                                                                              "+row[0],FontChinese));
		    // 抵押權人/分行別
		    document.add(new Paragraph(16,"                                                                                                     "+row[24]+"                                        "+row[22],FontChinese));
		    // 行員代號
		    document.add(new Paragraph(16,"                                                                                                                                                         "+row[23],FontChinese));
		    // 其他
		    document.add(new Paragraph(" "));
		    document.add(new Paragraph(" "));

		    document.add(new Paragraph(70,"            "+row[29]+"               "+row[24]+"                   "+row[28]+"                   "+row[25]+"                   "+row[26]+"                          "+row[27]+"                          "+row[34],FontChinese));
		    document.add(new Paragraph(14,"16.9% + 6%",FontChinese));
		    document.newPage();
		}

		// ***************************屋主甲式格式(套表列印)**************************1020430完成****************************************************
		if(PolicyType.equals(homeA))
		{
		    // 保單號
		    document.add(new Paragraph(16,"                              "+row[35].substring(0,2)+"       "+row[35].substring(2,10)+"                              "+row[3],FontChinese));
		    // 要保人
		    str1=row[13].trim().substring(0,14);
		    str2=row[13].trim().substring(14);
		    document.add(new Paragraph(14,"                "+row[32]+"                                                                                             "+row[12]+str1,FontChinese2));
		    document.add(new Paragraph(7,"                                                                                                                                                                        "+str2,FontChinese2));
		    document.add(new Paragraph(12,"                                              "+row[33]+"                      "+row[14],FontChinese));
		    // 被保人
		    str1=row[13].trim().substring(0,14);
		    str2=row[13].trim().substring(14);
		    document.add(new Paragraph(12,"                "+row[1]+"                                                                                              "+row[12]+str1,FontChinese2));
		    document.add(new Paragraph(7,"                                                                                                                                                                        "+str2,FontChinese2));
		    document.add(new Paragraph(12,"                                              "+row[2]+"                      "+row[14],FontChinese));
		    // 保險期間
		    num2=1+Integer.parseInt(row[4].substring(0,3));
		    document.add(new Paragraph(16,"                   12                             "+row[4].substring(0,3)+"       "+row[4].substring(3,5)+"         "+row[4].substring(5)+"                                              "+num2+"       "+row[4].substring(3,5)+"          "+row[4].substring(5),FontChinese));
		    // 標的物地址
		    document.add(new Paragraph(25,"                "+row[15]+"     "+row[16],FontChinese));
		    // 建築
		    // document.add(new Paragraph(" "));
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

		    document.add(new Paragraph("                     "+str3[0]+"               "+str3[1]+str3[2],FontChinese1));
		    document.add(new Paragraph("                     "+str3[3]+"               "+str3[4]+str3[5]+str3[6]+str3[7],FontChinese1));
		    document.add(new Paragraph("                     "+str3[8]+"               "+str3[9]+str3[10]+str3[11]+"                                                                                                                                                                              "+row[18]+"                            "+str4,FontChinese1));
		    document.add(new Paragraph("                     "+"                                                     "+row[21]+str3[12]+str3[13],FontChinese1));
		    document.add(new Paragraph(0,"                                                                                    "+row[17],FontChinese1));
		    document.add(new Paragraph("                     "+"                                                               "+row[20],FontChinese1));
		    // 建築物
		    document.add(new Paragraph(" "));
		    document.add(new Paragraph(" "));
		    document.add(new Paragraph(" "));
		    document.add(new Paragraph(" "));
		    document.add(new Paragraph(13," "));
		    // 火險保費及金額
		    document.add(new Paragraph(0,"                                                                                                                            "+ChangeCode.CatZero(row[5].substring(0,5)),FontChinese));
		    // 火險費率
		    document.add(new Paragraph(0,"　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　 "+row[30].substring(1),FontChinese));
		    // 火險保費
		    document.add(new Paragraph(0,"　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　                   "+ChangeCode.CatZero(row[6]),FontChinese));
		    // 竊盜
		    document.add(new Paragraph(60,"　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　 2.006",FontChinese));
		    // 保費
		    document.add(new Paragraph(0,"　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　                   "+ChangeCode.CatZero(1003),FontChinese));
		    // 基本震保額
		    document.add(new Paragraph(" "));
		    document.add(new Paragraph(25,"                                                                                                                            "+ChangeCode.CatZero(row[7].substring(0,5)),FontChinese));
		    // 基本地震費率
		    document.add(new Paragraph(0,"　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　 0.9",FontChinese));
		    // 基本地震保費
		    document.add(new Paragraph(0,"　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　                   "+ChangeCode.CatZero(row[8]),FontChinese));
		    // 訪客及第三人
		    document.add(new Paragraph(26,"                                                                                                                       100/1000",FontChinese));
		    document.add(new Paragraph(13,"                                                                                                                       100",FontChinese));
		    // 費率
		    document.add(new Paragraph(0,"　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　 0.005",FontChinese));
		    document.add(new Paragraph(0,"　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　                   102",FontChinese));
		    document.add(new Paragraph(12,"                                                                                                                       "+ChangeCode.CatZero(2000),FontChinese));
		    // 自動續保
		    document.add(new Paragraph(" "));
		    // 貸款戶別
		    document.add(new Paragraph(" "));
		    // 抵押權人

		    document.add(new Paragraph(" "));
		    document.add(new Paragraph(" "));
		    document.add(new Paragraph(22,"                           "+row[24]+"                                   "+row[22],FontChinese));
		    // 行員代號
		    document.add(new Paragraph(12,"                                                                          "+row[23],FontChinese));
		    // 貸款編號
		    document.add(new Paragraph(0,"                                                                                                                 "+row[0],FontChinese));
		    // 附註
		    document.add(new Paragraph(12,"                   自101年1月1日起，住宅地震基本保險最高賠償責任調整為新台幣150萬元。",FontChinese));
		    document.add(new Paragraph(" "));
		    document.add(new Paragraph(" "));
		    document.add(new Paragraph(" "));
		    document.add(new Paragraph(" "));
		    document.add(new Paragraph(34,"              "+row[29]+"               "+row[24]+"                   "+row[28]+"                   "+row[25]+"                   "+row[26]+"                      "+row[27]+"                       "+row[34],FontChinese));
		    document.add(new Paragraph(12,"16.9% + 6%",FontChinese));
		    document.newPage();
		}

		broker=row[28];
	    }

	}
	catch (FileNotFoundException e)
	{

	}
	catch (DocumentException e)
	{

	    e.printStackTrace();
	}
	catch (IOException e)
	{
	    // TODO 自動產生的 catch 區塊
	    e.printStackTrace();
	}

    }

    // ********InputImage*****************************
    public void PrintPolicy(String PolicyType)
    {

	try
	{

	    // **********選則保單類型並建立document**************************************
	    // ***********圖片副檔名大小寫要一樣才能包進PACKAGE****************************
	    if(PolicyType.equals(baseA))
	    {
		path="C:\\"+baseA+BWPrint+time+".pdf";
		document=new Document(PageSize.A4,72,36,173,-50);
		jpeg=Image.getInstance(FirePolicyPrintBeta.class.getResource("/A.jpg"));
		jpeg.setAlignment(Image.UNDERLYING);
		jpeg.scaleAbsolute(590,840);
		jpeg.setAbsolutePosition(0,0);
		PdfWriter.getInstance(document,new FileOutputStream(path));
	    }
	    if(PolicyType.equals(baseB))
	    {
		path="C:\\"+baseB+BWPrint+time+".pdf";
		document=new Document(PageSize.A4,72,36,173,0);
		jpeg=Image.getInstance(getClass().getResource("/A.JPG"));
		jpeg.setAlignment(Image.UNDERLYING);
		jpeg.scaleAbsolute(590,840);
		jpeg.setAbsolutePosition(0,0);
		PdfWriter.getInstance(document,new FileOutputStream(path));
	    }
	    if(PolicyType.equals(homeA))
	    {
		path="C:\\"+homeA+BWPrint+time+".pdf";
		document=new Document(PageSize.A4,72,36,125,0);
		jpeg=Image.getInstance(getClass().getResource("/W.jpg"));
		jpeg.setAlignment(Image.UNDERLYING);
		jpeg.scaleAbsolute(590,840);
		jpeg.setAbsolutePosition(0,0);
		PdfWriter.getInstance(document,new FileOutputStream(path));

	    }
	    if(PolicyType.equals(homeB))
	    {
		path="C:\\"+homeB+BWPrint+time+".pdf";
		document=new Document(PageSize.A4,72,36,158,0);
		jpeg=Image.getInstance(getClass().getResource("/X.jpg"));
		jpeg.setAlignment(Image.UNDERLYING);
		jpeg.scaleAbsolute(590,840);
		jpeg.setAbsolutePosition(0,0);
		PdfWriter.getInstance(document,new FileOutputStream(path));
	    }

	    // 設定字型
	    BaseFont bfChinese=BaseFont.createFont("C:\\windows\\fonts\\MINGLIU.TTC,1",BaseFont.IDENTITY_H,BaseFont.NOT_EMBEDDED);
	    // BaseFont chineseTraditionalFont =
	    // BaseFont.createFont("MHei-Medium","UniCNS-UCS2-H",BaseFont.NOT_EMBEDDED);
	    // BaseFont bfChinese =
	    // BaseFont.createFont("C:\\windows\\fonts\\KAIU.TTF",BaseFont.IDENTITY_H,BaseFont.NOT_EMBEDDED);
	    Font FontChinese=new Font(bfChinese,10,Font.NORMAL);
	    Font FontChinese1=new Font(bfChinese,7,Font.NORMAL);
	    Font FontChinese2=new Font(bfChinese,8,Font.NORMAL);
	    document.addTitle("MINGTAI FIRE INSURANCE APPLICATION");
	    document.addAuthor("MINGTAI");
	    document.addSubject("MINGTAI");
	    document.addKeywords("MINGTAI");
	    document.addCreator("MINGTAI");
	    document.open();

	    isr=new InputStreamReader(new FileInputStream(insertFile.getPath()),"ISO8859_1");
	    br=new BufferedReader(isr);

	    while ((Line=br.readLine())!=null)
	    {

		row[0]=Line.substring(0,40);// 銀行資料ＫＥＹ 40
		row[1]=Line.substring(40,70);// 被保險人姓名 70
		row[2]=Line.substring(70,80);// 被保險人ＩＤ 80
		row[3]=Line.substring(80,90);// 舊保單號 90
		row[4]=Line.substring(90,97);// 保單到期日 97
		row[5]=Line.substring(97,106);// 續保火險保額
		row[6]=Line.substring(106,113);// 續保火險保費 113;
		row[7]=Line.substring(113,122);// 續保基本地震險保額 122
		row[8]=Line.substring(122,129);// 續保基本地震險保費 129
		row[9]=Line.substring(129,130);// 銀行貸款還清記號 130
		row[10]=Line.substring(130,146);// 扣款帳號 146
		row[11]=Line.substring(146,153);// 扣款日期 153
		row[12]=Line.substring(153,156);// 通訊郵遞區號 156
		row[13]=Line.substring(156,216);// 被保險人通訊地址 216
		row[14]=Line.substring(216,226);// 被保險人電話 226
		row[15]=Line.substring(226,306);// 標的物地址 306
		row[16]=Line.substring(306,311);// 郵遞區號 311
		row[17]=Line.substring(311,315);// 建築物結構 315
		row[18]=Line.substring(315,317);// 樓層數 317
		row[19]=Line.substring(317,318);// 建築等級 318
		row[20]=Line.substring(318,326);// 坪數 326
		row[21]=Line.substring(326,329);// 建築年份 329
		row[22]=Line.substring(329,339);// 分行別 339
		row[23]=Line.substring(339,350);// 行員代號 350
		row[24]=Line.substring(350,357);// 銀行代號 357
		row[25]=Line.substring(357,362);// 經手人員一 362
		row[26]=Line.substring(362,363);// 經手人員一檢核碼 363
		row[27]=Line.substring(363,365);// 統計單位 365
		row[28]=Line.substring(365,367);// 經紀公司代號 367
		row[29]=Line.substring(367,368);// 業務來源 368
		row[30]=Line.substring(368,374);// 簽單費率 374
		row[31]=Line.substring(374,376);// 統計單位中歸屬 376
		row[32]=Line.substring(376,406);// 要保人姓名 406
		row[33]=Line.substring(406,416);// 要保人ＩＤ 416
		row[34]=Line.substring(416,421);// 服務人 421
		row[35]=Line.substring(421,431);// 新保單號 431
		/*********** 新欄位 ************************************************/
		// row[35] = Line.substring(421,431);//新保單號 432
		// row[35] = Line.substring(421,431);//新保單號 43
		// row[35] = Line.substring(421,431);//新保單號 431
		// row[35] = Line.substring(421,431);//新保單號 431
		// row[35] = Line.substring(421,431);//新保單號 431
		// row[35] = Line.substring(421,431);//新保單號 431
		// 中文轉碼
		row[1]=new String(row[1].getBytes("ISO8859_1"),"MS950");
		row[13]=new String(row[13].getBytes("ISO8859_1"),"MS950");
		row[15]=new String(row[15].getBytes("ISO8859_1"),"MS950");
		row[32]=new String(row[32].getBytes("ISO8859_1"),"MS950");

		// 寫入PDF
		row[0]=row[0].trim();
		// ********複製底圖**************
		document.add(jpeg);
		// *******************************
		row[1]=function.ChangeCodeData(row[1]);
		row[32]=function.ChangeCodeData(row[32]);
		// ********基本住宅**************
		if(PolicyType.equals(baseA))
		{
		    // 保單號
		    document.add(new Paragraph(-2,"                          "+row[35].substring(0,2)+"         "+row[35].substring(2,10)+"                                       "+row[3]+"                                  ",FontChinese));
		    // document.add(new
		    // Paragraph("                                                                            "+row[35]+"                                  ",FontChinese));
		    // 被保人
		    str1=row[13].trim().substring(0,15);
		    str2=row[13].trim().substring(15);
		    document.add(new Paragraph(21,"                   "+row[1]+"                                                                                             "+row[12]+str1+"                     ",FontChinese2));
		    document.add(new Paragraph(-12,"                                                                                                                                                                          "+str2,FontChinese2));
		    document.add(new Paragraph(12,"                                               "+row[2]+"                   "+row[14],FontChinese));
		    // 要保人
		    str1=row[13].trim().substring(0,15);
		    str2=row[13].trim().substring(15);
		    document.add(new Paragraph(14,"                   "+row[32]+"                                                                                             "+row[12]+str1+"                     ",FontChinese2));
		    document.add(new Paragraph(-5,"                                                                                                                                                                          "+str2,FontChinese2));
		    document.add(new Paragraph(12,"                                              "+row[33]+"                   "+row[14],FontChinese));
		    // 總保額
		    num1=0;
		    try
		    {
			if(row[6].trim()!=null)
			{
			    num1=num1+Integer.parseInt(row[6].trim());
			}
		    }
		    catch (NumberFormatException e2)
		    {
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
		    }
		    String money=ChangeCode.CatZero(num1);
		    document.add(new Paragraph(16,"                                  "+ChangeCode.CatZero(row[5]),FontChinese));
		    document.add(new Paragraph(0,"                                                                                                                                                              "+ChangeCode.CatZero(row[6]),FontChinese));
		    document.add(new Paragraph(18,"                                                                                                      "+money,FontChinese));
		    document.add(new Paragraph(0,"                                                                                                                                                              "+ChangeCode.CatZero(row[8]),FontChinese));
		    // 地震險保額險
		    document.add(new Paragraph(16,"                                  "+ChangeCode.CatZero(row[7])+"                                                                                                                    0",FontChinese));

		    // 保險期間
		    num2=1+Integer.parseInt(row[4].substring(0,3));
		    document.add(new Paragraph(18,"                  12                       "+row[4].substring(0,3)+"         "+row[4].substring(3,5)+"        "+row[4].substring(5)+"                                        "+num2+"       "+row[4].substring(3,5)+"        "+row[4].substring(5),FontChinese));
		    // 標的物地址
		    document.add(new Paragraph(30,"                   "+row[15]+row[16],FontChinese));
		    // 建築物
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
		    {
			str4="A1";
		    }
		    else if(num1==2)
		    {
			str4="A2";
		    }
		    else if(num1==3)
		    {
			str4="B";
		    }
		    else
		    {
			str4="C";
		    }
		    // document.add(new Paragraph(" "));
		    document.add(new Paragraph(8,"             "+str3[0]+"               "+str3[1]+str3[2],FontChinese1));
		    document.add(new Paragraph("             "+str3[3]+"               "+str3[4]+str3[5]+str3[6]+str3[7],FontChinese1));
		    document.add(new Paragraph("             "+str3[8]+"               "+str3[9]+str3[10]+str3[11]+"                                                                                                                                                                                       "+row[18]+"                              "+str4,FontChinese1));
		    // 建築年份
		    document.add(new Paragraph("                                                                        "+row[21]+str3[12]+str3[13],FontChinese1));
		    document.add(new Paragraph(0,"                                                                                    "+row[17],FontChinese1));
		    document.add(new Paragraph("                                                                                "+row[20],FontChinese1));
		    document.add(new Paragraph(90,"   ",FontChinese));
		    // 分項保費建築物
		    document.add(new Paragraph(22,"     01                                                                              "+ChangeCode.CatZero(row[5])+"          "+row[30].substring(1)+"    1/1        "+ChangeCode.CatZero(row[6]),FontChinese));
		    document.add(new Paragraph(0,"                                                                                                                                                                                     "+str4,FontChinese));
		    // 加保動產
		    document.add(new Paragraph(12,"  ",FontChinese));
		    document.add(new Paragraph(12,"  ",FontChinese));
		    document.add(new Paragraph(12,"          自101年1月1日起，住宅地震基本保險最高賠償責任調整為新台幣150萬元。",FontChinese));
		    // 基本地震
		    document.add(new Paragraph(20,"     02                                                                              "+ChangeCode.CatZero(row[7])+"          "+"0.9        "+"1/1        "+ChangeCode.CatZero(row[8]),FontChinese));
		    document.add(new Paragraph(0,"                                                                                                                                                                                     "+str4,FontChinese));
		    // 備註
		    document.add(new Paragraph(" "));
		    document.add(new Paragraph(" "));
		    document.add(new Paragraph(" "));
		    // 抵押權人 //貸款編號
		    document.add(new Paragraph(8,"                                                                                                              "+row[0],FontChinese));
		    document.add(new Paragraph(25,"                                                                                                     "+row[24]+"                                        "+row[22],FontChinese));
		    document.add(new Paragraph(16,"                                                                                                                                                         "+row[23],FontChinese));
		    // 其他
		    document.add(new Paragraph(" "));
		    document.add(new Paragraph(" "));

		    document.add(new Paragraph(56,"            "+row[29]+"               "+row[24]+"                   "+row[28]+"                   "+row[25]+"                   "+row[26]+"                          "+row[27]+"                          "+row[34],FontChinese));
		    document.add(new Paragraph(16,"16.9% + 6%",FontChinese));
		    document.newPage();
		}

		// ***************************基本住宅甲式格式**************************
		if(PolicyType.equals(baseB))
		{
		    // 保單號
		    document.add(new Paragraph("                      "+row[35].substring(0,2)+"       "+row[35].substring(2,10)+"                               "+row[3],FontChinese));
		    // 被保人
		    str1=row[13].trim().substring(0,15);
		    str2=row[13].trim().substring(15);
		    document.add(new Paragraph(22,"          "+row[1]+"          "+row[12]+str1+"                     "+row[14],FontChinese));
		    document.add(new Paragraph(20,"                                    "+row[2]+"                               "+str2,FontChinese));
		    // 要保人
		    str1=row[13].trim().substring(0,15);
		    str2=row[13].trim().substring(15);
		    document.add(new Paragraph(22,"          "+row[32]+"          "+row[12]+str1+"                     "+row[14],FontChinese));
		    document.add(new Paragraph(20,"                                    "+row[33]+"                              "+str2,FontChinese));
		    // 總保額
		    num1=0;
		    try
		    {
			if(row[6].trim()!=null)
			{
			    num1=num1+Integer.parseInt(row[6].trim());
			}
		    }
		    catch (NumberFormatException e2)
		    {
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
		    }
		    String money=ChangeCode.CatZero(num1);
		    document.add(new Paragraph(16,"                               "+row[5]+"                                                                                                             "+row[6],FontChinese));
		    document.add(new Paragraph(12,"                                                                                                      "+money+"                                             "+row[8],FontChinese));
		    document.add(new Paragraph(12,"                               "+row[7]+"                                                                                                                     0",FontChinese));
		    // 保險期間
		    num2=1+Integer.parseInt(row[4].substring(0,3));
		    document.add(new Paragraph(18,"             12                      "+row[4].substring(0,3)+"            "+row[4].substring(3,5)+"          "+row[4].substring(5)+"                                            "+num2+"       "+row[4].substring(3,5)+"           "+row[4].substring(5),FontChinese));
		    // 標的物地址
		    document.add(new Paragraph(22,"         "+row[15]+row[16],FontChinese));
		    // 建築物
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

		    document.add(new Paragraph(8,"             "+str3[0]+"               "+str3[1]+str3[2],FontChinese1));
		    document.add(new Paragraph("             "+str3[3]+"               "+str3[4]+str3[5]+str3[6]+str3[7],FontChinese1));
		    document.add(new Paragraph("             "+str3[8]+"               "+str3[9]+str3[10]+str3[11]+"                                                                                                                                                                              "+row[18]+"                            "+str4,FontChinese1));
		    document.add(new Paragraph("    "+"                                                     "+row[21]+str3[12]+str3[13],FontChinese1));
		    document.add(new Paragraph(0,"                                                                                    "+row[17],FontChinese1));
		    document.add(new Paragraph("             "+"                                                               "+row[20],FontChinese1));
		    document.add(new Paragraph(110,"   ",FontChinese));
		    // 分項保費建築物
		    document.add(new Paragraph(24,"   01                                                                          "+row[5]+"      "+row[30].substring(1)+"  1/1           "+row[6]+"                                     "+str4,FontChinese));
		    // 加保動產
		    try
		    {
			num1=Integer.parseInt(row[6].trim());
		    }
		    catch (NumberFormatException e2)
		    {
			num1=0;
		    }
		    num2=(int)Math.round(num1*0.32);
		    document.add(new Paragraph(12,"  ",FontChinese));
		    document.add(new Paragraph(24,"   02                                                                             32              "+row[30].substring(1)+"  1/1               "+num2+"                                     "+str4,FontChinese));
		    // 基本地震
		    document.add(new Paragraph(24,"   03                                                                            "+row[7]+"    "+"0.9      "+"1/1           "+row[8]+"                                     "+str4,FontChinese));
		    // 備註
		    document.add(new Paragraph(" "));
		    document.add(new Paragraph(" "));
		    // 抵押權人
		    document.add(new Paragraph("                                                                                     "+row[0],FontChinese));
		    document.add(new Paragraph(22,"                                                                                                     "+row[24]+"                              "+row[22],FontChinese));
		    document.add(new Paragraph(16,"                                                                                                                                             "+row[23],FontChinese));
		    System.out.println(row[23]);
		    // 其他
		    document.add(new Paragraph(" "));
		    document.add(new Paragraph(36,"            "+row[29]+"               "+row[24]+"                   "+row[28]+"                   "+row[25]+"                   "+row[26]+"                      "+row[27]+"                      "+row[34],FontChinese));
		    document.add(new Paragraph(12,"16.9% + 6%",FontChinese));
		    document.newPage();
		}
		if(PolicyType.equals(homeA))
		{

		    // 保單號
		    document.add(new Paragraph(16,"                            "+row[35].substring(0,2)+"       "+row[35].substring(2,10)+"                                  "+row[3],FontChinese));
		    // 要保人
		    str1=row[13].trim().substring(0,14);
		    str2=row[13].trim().substring(14);
		    document.add(new Paragraph(14,"                    "+row[32]+"                                                                                         "+row[12]+str1,FontChinese2));
		    document.add(new Paragraph(7,"                                                                                                                                                                        "+str2,FontChinese2));
		    document.add(new Paragraph(12,"                                              "+row[33]+"                   "+row[14],FontChinese));
		    // 被保人
		    str1=row[13].trim().substring(0,14);
		    str2=row[13].trim().substring(14);
		    document.add(new Paragraph(12,"                    "+row[1]+"                                                                                          "+row[12]+str1,FontChinese2));
		    document.add(new Paragraph(7,"                                                                                                                                                                        "+str2,FontChinese2));
		    document.add(new Paragraph(12,"                                              "+row[2]+"                   "+row[14],FontChinese));
		    // 保險期間
		    num2=1+Integer.parseInt(row[4].substring(0,3));
		    document.add(new Paragraph(16,"                   12                             "+row[4].substring(0,3)+"       "+row[4].substring(3,5)+"         "+row[4].substring(5)+"                                              "+num2+"       "+row[4].substring(3,5)+"          "+row[4].substring(5),FontChinese));
		    // 標的物地址
		    document.add(new Paragraph(28,"                "+row[15]+"     "+row[16],FontChinese));
		    // 建築
		    // document.add(new Paragraph(" "));
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

		    document.add(new Paragraph("                     "+str3[0]+"               "+str3[1]+str3[2],FontChinese1));
		    document.add(new Paragraph("                     "+str3[3]+"               "+str3[4]+str3[5]+str3[6]+str3[7],FontChinese1));
		    document.add(new Paragraph("                     "+str3[8]+"               "+str3[9]+str3[10]+str3[11]+"                                                                                                                                                                              "+row[18]+"                            "+str4,FontChinese1));
		    document.add(new Paragraph("                     "+"                                                     "+row[21]+str3[12]+str3[13],FontChinese1));
		    document.add(new Paragraph(0,"                                                                                    "+row[17],FontChinese1));
		    document.add(new Paragraph("                     "+"                                                               "+row[20],FontChinese1));
		    // 建築物
		    document.add(new Paragraph(" "));
		    document.add(new Paragraph(" "));
		    document.add(new Paragraph(" "));
		    document.add(new Paragraph(" "));
		    document.add(new Paragraph(8," "));
		    // 火險保費及金額
		    document.add(new Paragraph(0,"　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　"+ChangeCode.CatZero(row[5].substring(0,5)),FontChinese));
		    // 火險費率
		    document.add(new Paragraph(0,"　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　"+row[30].substring(1),FontChinese));
		    // 火險保費
		    document.add(new Paragraph(0,"　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　"+ChangeCode.CatZero(row[6]),FontChinese));
		    // 竊盜
		    document.add(new Paragraph(62,"　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　2.006",FontChinese));
		    document.add(new Paragraph(0,"　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　"+ChangeCode.CatZero(1003),FontChinese));
		    // 基本震保額
		    document.add(new Paragraph(" "));
		    document.add(new Paragraph(23,"　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　"+ChangeCode.CatZero(row[7].substring(0,5)),FontChinese));
		    // 基本地震費率
		    document.add(new Paragraph(0,"　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　0.9",FontChinese));
		    // 基本地震保費
		    document.add(new Paragraph(0,"　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　"+ChangeCode.CatZero(row[8]),FontChinese));
		    // 訪客及第三人
		    document.add(new Paragraph(28,"　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　100/1000",FontChinese));
		    document.add(new Paragraph(12,"　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　100",FontChinese));
		    document.add(new Paragraph(0,"　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　0.005",FontChinese));
		    document.add(new Paragraph(0,"　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　102",FontChinese));
		    document.add(new Paragraph(12,"　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　"+ChangeCode.CatZero(2000),FontChinese));
		    // 自動續保
		    document.add(new Paragraph(" "));
		    // 貸款戶別
		    document.add(new Paragraph(" "));
		    // 抵押權人
		    document.add(new Paragraph(" "));
		    document.add(new Paragraph(" "));
		    document.add(new Paragraph(22,"                           "+row[24]+"                                   "+row[22],FontChinese));
		    // 行員代號
		    document.add(new Paragraph(12,"                                                                          "+row[23],FontChinese));
		    // 貸款編號
		    document.add(new Paragraph(0,"                                                                                                                 "+row[0],FontChinese));
		    // 附註
		    document.add(new Paragraph(14,"                   自101年1月1日起，住宅地震基本保險最高賠償責任調整為新台幣150萬元。",FontChinese));
		    document.add(new Paragraph(" "));
		    document.add(new Paragraph(" "));
		    document.add(new Paragraph(" "));
		    document.add(new Paragraph(" "));
		    document.add(new Paragraph(32,"              "+row[29]+"               "+row[24]+"                   "+row[28]+"                   "+row[25]+"                   "+row[26]+"                      "+row[27]+"                       "+row[34],FontChinese));
		    document.add(new Paragraph(12,"16.9% + 6%",FontChinese));
		    document.newPage();
		}
		// ***************************屋主丙式格式**************************1020319完成****************************************
		if(PolicyType.equals(homeB))
		{
		    // 保單號
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
		    num2=1+Integer.parseInt(row[4].substring(0,3));
		    document.add(new Paragraph(20,"                  12                           "+row[4].substring(0,3)+"       "+row[4].substring(3,5)+"       "+row[4].substring(5)+"                                                  "+num2+"       "+row[4].substring(3,5)+"       "+row[4].substring(5),FontChinese));
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
		    document.add(new Paragraph(0,"                                                                                    "+row[17],FontChinese1));
		    document.add(new Paragraph("                     "+"                                                               "+row[20],FontChinese1));
		    // 財損*********此火險續保保費為財損******************************************/
		    /*
		     * try{ if (row[6].trim() != null){ num1 =
		     * Integer.parseInt(row[6].trim()); }
		     * }catch(NumberFormatException e2){ }
		     */
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
		    document.add(new Paragraph(92,"            "+row[29]+"               "+row[24]+"                   "+row[28]+"                   "+row[25]+"                   "+row[26]+"                         "+row[27]+"                        "+row[34],FontChinese));
		    document.add(new Paragraph(12,"16.9% + 6%",FontChinese));
		    document.add(new Paragraph(36,""));
		    document.newPage();
		}
		broker=row[28];
	    }

	}
	catch (java.io.FileNotFoundException p)
	{
	    JOptionPane.showMessageDialog(null,"找不到"+pathImage+PolicyType+".JPG");
	    System.gc();
	}
	catch (BadElementException e)
	{
	    System.gc();
	    e.printStackTrace();
	}
	catch (MalformedURLException e)
	{
	    System.gc();
	    e.printStackTrace();
	}
	catch (IOException e)
	{
	    System.gc();
	    e.printStackTrace();
	}
	catch (DocumentException e)
	{
	    System.gc();
	    e.printStackTrace();
	}
	catch (java.lang.NullPointerException e)
	{
	    JOptionPane.showMessageDialog(null,"找不到"+PolicyType+".JPG");
	    System.gc();

	}
    }
}
