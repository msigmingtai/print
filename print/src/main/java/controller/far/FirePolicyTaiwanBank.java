package controller.far;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.text.Format;
import java.util.Calendar;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.Document;

/*20121004改版為套表及黑白列印*/
public class FirePolicyTaiwanBank extends JFrame implements

ActionListener
{

    File obj = null;
    int wrong = 0;
    String error[] = new String[5000];
    int errori = 0;
    String SuccessFile = "";
    File FileArray[] = new File[29];
    FileWriter FileWArray[] = new FileWriter[29];
    private static final long serialVersionUID = 1L;
    int ret = 1;

    // 建立檔案對話方塊
    JFileChooser fc = new JFileChooser();

    JButton bt = new JButton("開啟");
    JButton bt1 = new JButton("產生列印檔");
    JButton bt2 = new JButton("關閉");
    JLabel lb = new JLabel("請選擇要保書資料檔　　　　  ",JLabel.LEFT);
    JLabel lb1 = new JLabel("請選擇要保書種類",JLabel.LEFT);
    JComboBox<String> cb = new JComboBox<String>();
    JComboBox<String> cb1 = new JComboBox<String>();
    JComboBox<String> cb2 = new JComboBox<String>();
    // JRadioButton rb1 = new JRadioButton("單一檔案       ");
    // JRadioButton rb2 = new JRadioButton("單位分割       ");
    JLabel lb2 = new JLabel("請選擇要保書是否分割",JLabel.LEFT);
    JLabel lb3 = new JLabel("請選擇列印方式　　　　　",JLabel.LEFT);

    // JPasswordField pw =new JPasswordField(8);

    // *********程式進入點**********************
    public static void main(String args[])
    {
        FirePolicyTaiwanBank app = new FirePolicyTaiwanBank();
        app.setTitle("明台產物保險要保書產生程式(彰銀農金台銀版)");
        app.setSize(300,200);
        app.setLocationRelativeTo(null);
        app.setResizable(false); // 鎖定視窗大小
        app.setVisible(true);
        app.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        app.addWindowListener(new WindowAdapter()
        {
	  public void windowClosing(WindowEvent e)
	  {
	      System.exit(0);
	  }
        });
    }

    // *********物件結構************************
    public FirePolicyTaiwanBank()
    {
        Container cp = getContentPane();
        cp.setLayout(new FlowLayout());
        cp.add(lb);
        cp.add(bt);
        bt.addActionListener(this);
        cp.add(lb1);
        cb.addItem("基本住宅地震黑白");
        cp.add(cb);
        cp.add(lb2);
        cb1.addItem("單位分割列印");
        cb2.addItem("套表列印");
        cb2.addItem("黑白列印");
        cp.add(cb1);
        cp.add(lb3);
        cp.add(cb2);
        cp.add(bt1);
        bt1.addActionListener(this);
        cp.add(bt2);
        bt2.addActionListener(this);

    }

    public void actionPerformed(ActionEvent e)
    {

        String Line , line1 , line2 , line3 , line4 , line5 , line6 = null;
        File TempPath = new File("C:\\");

        if(e.getSource()==bt)
        {
	  ret = fc.showOpenDialog(null);
	  obj = fc.getSelectedFile();
        }
        else if(e.getSource()==bt2)
        {
	  System.exit(0);
        }
        else
        {

	  // 按下[開啟]鍵後，顯示檔案的路徑
	  if(ret==JFileChooser.APPROVE_OPTION)
	  {
	      // 依保單單位切29檔(51統計單位RS特別切出)
	      try
	      {
		FileArray[0] = File.createTempFile("00台北@",".tmp",TempPath);
		FileArray[1] = File.createTempFile("01桃園@",".tmp",TempPath);
		FileArray[2] = File.createTempFile("01中壢@",".tmp",TempPath);
		FileArray[3] = File.createTempFile("02台中@",".tmp",TempPath);
		FileArray[4] = File.createTempFile("02豐原@",".tmp",TempPath);
		FileArray[5] = File.createTempFile("03台南@",".tmp",TempPath);
		FileArray[6] = File.createTempFile("04北高@",".tmp",TempPath);
		FileArray[7] = File.createTempFile("04鳳山@",".tmp",TempPath);
		FileArray[8] = File.createTempFile("04南高@",".tmp",TempPath);
		FileArray[9] = File.createTempFile("05彰化@",".tmp",TempPath);
		FileArray[10] = File.createTempFile("05員林@",".tmp",TempPath);
		FileArray[11] = File.createTempFile("06羅東@",".tmp",TempPath);
		FileArray[12] = File.createTempFile("13嘉義@",".tmp",TempPath);
		FileArray[13] = File.createTempFile("32新竹@",".tmp",TempPath);
		FileArray[14] = File.createTempFile("36屏東@",".tmp",TempPath);
		FileArray[15] = File.createTempFile("43苗栗@",".tmp",TempPath);
		FileArray[16] = File.createTempFile("45斗六@",".tmp",TempPath);
		FileArray[17] = File.createTempFile("46新營@",".tmp",TempPath);
		FileArray[18] = File.createTempFile("51板橋@",".tmp",TempPath);
		FileArray[19] = File.createTempFile("51土城@",".tmp",TempPath);
		FileArray[20] = File.createTempFile("51中和@",".tmp",TempPath);
		FileArray[21] = File.createTempFile("55新莊@",".tmp",TempPath);
		FileArray[22] = File.createTempFile("55三重@",".tmp",TempPath);
		FileArray[23] = File.createTempFile("85中山@",".tmp",TempPath);
		FileArray[24] = File.createTempFile("85北投@",".tmp",TempPath);
		FileArray[25] = File.createTempFile("85南港@",".tmp",TempPath);
		FileArray[26] = File.createTempFile("85新店@",".tmp",TempPath);
		FileArray[27] = File.createTempFile("98花蓮@",".tmp",TempPath);
		FileArray[28] = File.createTempFile("99南投@",".tmp",TempPath);

		// 將檔案匯入FILEWRITER
		for(int i = 0; i<FileArray.length; i++)
		{
		    FileWArray[i] = new FileWriter(FileArray[i]);
		}

		String STUNIT , BLUNIT = null;
		// 讀檔 排序 分割 存檔
		InputStreamReader isr = new InputStreamReader(new FileInputStream(obj.getPath()),"ISO8859_1");
		BufferedReader br = new BufferedReader(isr);

		while ( ( Line = br.readLine() )!=null)
		{

		    STUNIT = Line.substring(510,512); // 保單編碼
		    BLUNIT = Line.substring(387,389);// 統計單位

		    // 轉中文
		    line1 = new String(Line.substring(31,61).getBytes("ISO8859_1"),"MS950");
		    line2 = new String(Line.substring(61,91).getBytes("ISO8859_1"),"MS950");
		    line3 = new String(Line.substring(96,196).getBytes("ISO8859_1"),"MS950");
		    line4 = new String(Line.substring(201,261).getBytes("ISO8859_1"),"MS950");
		    line5 = new String(Line.substring(463,640).getBytes("ISO8859_1"),"MS950");
		    line6 = new String(Line.substring(640,660).getBytes("ISO8859_1"),"MS950");
		    Line = Line.substring(0,31)+line1+line2+Line.substring(91,96)+line3+Line.substring(196,201)+line4+Line.substring(261,463)+line5+line6;

		    // Line = new
		    // String(Line.substring(0,660).getBytes("ISO8859_1"),"MS950");
		    // System.out.println(line1);
		    // System.out.println(STUNIT + "-" +BLUNIT);
		    if(STUNIT.equals("00"))
		    {
		        FileWArray[0].write(Line+"\r\n");
		    }
		    else if(STUNIT.equals("01"))
		    {
		        if(BLUNIT.equals("FP")||BLUNIT.equals("40")||BLUNIT.equals("FN"))
		        {
			  FileWArray[2].write(Line+"\r\n");
		        }
		        else
		        {
			  FileWArray[1].write(Line+"\r\n");
		        }
		    }
		    else if(STUNIT.equals("02"))
		    {
		        if(BLUNIT.equals("GP")||BLUNIT.equals("57"))
		        {
			  FileWArray[4].write(Line+"\r\n");
		        }
		        else
		        {
			  FileWArray[3].write(Line+"\r\n");
		        }
		    }
		    else if(STUNIT.equals("03"))
		    {
		        FileWArray[5].write(Line+"\r\n");
		    }
		    else if(STUNIT.equals("04"))
		    {
		        if(BLUNIT.equals("18")||BLUNIT.equals("60")||BLUNIT.equals("JT"))
		        {
			  FileWArray[6].write(Line+"\r\n");
		        }
		        else if(BLUNIT.equals("19"))
		        {
			  FileWArray[7].write(Line+"\r\n");
		        }
		        else
		        {
			  FileWArray[8].write(Line+"\r\n");
		        }
		    }
		    else if(STUNIT.equals("05"))
		    {
		        if(BLUNIT.equals("KZ"))
		        {
			  FileWArray[10].write(Line+"\r\n");
		        }
		        else
		        {
			  FileWArray[9].write(Line+"\r\n");
		        }
		    }
		    else if(STUNIT.equals("06"))
		    {
		        FileWArray[11].write(Line+"\r\n");
		    }
		    else if(STUNIT.equals("13"))
		    {
		        FileWArray[12].write(Line+"\r\n");
		    }
		    else if(STUNIT.equals("32"))
		    {
		        FileWArray[13].write(Line+"\r\n");
		    }
		    else if(STUNIT.equals("36"))
		    {
		        FileWArray[14].write(Line+"\r\n");
		    }
		    else if(STUNIT.equals("43"))
		    {
		        FileWArray[15].write(Line+"\r\n");
		    }
		    else if(STUNIT.equals("45"))
		    {
		        FileWArray[16].write(Line+"\r\n");
		    }
		    else if(STUNIT.equals("46"))
		    {
		        FileWArray[17].write(Line+"\r\n");
		    }
		    else if(STUNIT.equals("51"))
		    {
		        if(BLUNIT.equals("RR"))
		        {
			  FileWArray[20].write(Line+"\r\n");
		        }
		        else if(BLUNIT.equals("RF"))
		        {
			  FileWArray[18].write(Line+"\r\n");
		        }
		        else
		        {
			  FileWArray[19].write(Line+"\r\n");
		        }
		    }
		    else if(STUNIT.equals("55"))
		    {
		        if(BLUNIT.equals("QF")||BLUNIT.equals("QN"))
		        {
			  FileWArray[21].write(Line+"\r\n");
		        }
		        else
		        {
			  FileWArray[22].write(Line+"\r\n");
		        }
		    }
		    else if(STUNIT.equals("85"))
		    {
		        if(BLUNIT.equals("KW")||BLUNIT.equals("KU"))
		        {
			  FileWArray[23].write(Line+"\r\n");
		        }
		        else
		        {
			  if(BLUNIT.equals("WT")||BLUNIT.equals("WX"))
			  {
			      FileWArray[24].write(Line+"\r\n");
			  }
			  else
			  {
			      if(BLUNIT.equals("UP")||BLUNIT.equals("UQ")||BLUNIT.equals("UW"))
			      {
				FileWArray[25].write(Line+"\r\n");
			      }
			      else if(BLUNIT.equals("YQ")||BLUNIT.equals("YV"))
			      {
				FileWArray[26].write(Line+"\r\n");
			      }
			  }
		        }
		    }
		    else if(STUNIT.equals("98"))
		    {
		        FileWArray[27].write(Line+"\r\n");
		    }
		    else if(STUNIT.equals("99"))
		    {
		        FileWArray[28].write(Line+"\r\n");
		    }
		}

		// 關閉串流
		for(int i = 0; i<FileWArray.length; i++)
		{
		    FileWArray[i].close();
		}
		br.close();
		System.gc();

		if(cb.getSelectedItem().equals("基本住宅地震黑白"))
		{
		    // 產生PDF檔

		    for(int i = 0; i<FileArray.length; i++)
		    {

		        isr = new InputStreamReader(new FileInputStream(FileArray[i].getPath()),"ISO8859_1");
		        br = new BufferedReader(isr);
		        while ( ( Line = br.readLine() )!=null)
		        {
			  line1 = new String(Line.substring(0,660).getBytes("ISO8859_1"),"MS950");

		        }
		    }

		    for(int i = 0; i<FileArray.length; i++)
		        if(FileArray[i].length()!=0)
		        {
			  PrintBW(FileArray[i]);
			  // SuccessFile =
			  // SuccessFile+FileArray[i].getAbsolutePath()+"\n";
		        }
		}
		// 刪掉TEMPFILE(必須關閉串流才能刪除檔案)****************************
		for(int i = 0; i<FileArray.length; i++)
		{
		    FileArray[i].delete();
		}

		// 錯誤清單*********************************************************
		if(errori>0)
		{
		    File fileE = new File("C:\\要保書資料錯誤清單.txt");
		    if(fileE.exists())
		    {
		        fileE.delete();
		    }
		    fileE.createNewFile();
		    FileWriter fwE = new FileWriter(fileE);
		    for(int i = 0; i<errori; i++)
		    {
		        fwE.write(error[i]+"\r\n");
		    }
		    fwE.close();
		    JOptionPane.showMessageDialog(null,"請檢查錯誤清單");
		}
		if(SuccessFile.equals(""))
		{
		}
		else
		{
		    JOptionPane.showMessageDialog(null,SuccessFile);
		}
		JOptionPane.showMessageDialog(null,"執行完畢\n ");

	      }
	      catch (IOException e1)
	      {
		JOptionPane.showMessageDialog(null," 檔案有問題！\n 請重新選擇檔案！");
	      }
	      catch (java.lang.StringIndexOutOfBoundsException e1)
	      {
		for(int i = 0; i<FileWArray.length; i++)
		{
		    try
		    {
		        FileWArray[i].close();
		    }
		    catch (IOException e2)
		    {

		        e2.printStackTrace();
		    }
		}
		for(int i = 0; i<FileArray.length; i++)
		{
		    FileArray[i].delete();
		}

		JOptionPane.showMessageDialog(null,"檔案格式有問題！\n 請重新選擇檔案！");
	      }
	  }
	  else
	  {
	      JOptionPane.showMessageDialog(null,"無匯入檔案");
	  }
        }
    }

    public void PrintBW(File obj)
    {
        String row[] = new String[55];
        Document document = null;
        String Line = null;
        Image jpeg = null;
        String str1 , str2 , str4 , str5 = null , str6 = null;
        String str3[] = new String[14];
        int num1 = 0;
        try
        {
	  // 套表
	  String path = ".";
	  document = new Document(PageSize.A4,72,0,175,0);
	  try
	  {
	      jpeg = Image.getInstance(getClass().getResource("/A.jpg"));
	  }
	  catch (java.io.FileNotFoundException p)
	  {
	      JOptionPane.showMessageDialog(null,"找不到"+path+"/A.JPG");
	  }
	  jpeg.setAlignment(Image.UNDERLYING);
	  jpeg.scaleAbsolute(590,840);
	  jpeg.setAbsolutePosition(0,0);
	  // 建檔
	  Calendar cal = Calendar.getInstance();
	  int YEAR = cal.get(Calendar.YEAR);
	  int MONTH = cal.get(Calendar.MONTH)+1;
	  int DATE = cal.get(Calendar.DATE);
	  // int HOURS = cal.get(Calendar.HOUR);
	  // int MINUTE = cal.get(Calendar.MINUTE);
	  PdfWriter.getInstance(document,new FileOutputStream("C:\\火險要保書"+YEAR+"-"+MONTH+"-"+DATE+"-"+"-"+obj.getName().substring(0,4)+cb2.getSelectedItem().toString()+".pdf"));
	  // PdfEncryptor.encrypt(document,new FileOutputStream(outFile),
	  // readPassword.getBytes(), writePassword.getBytes(), 0, false);
	  // 設定字型
	  BaseFont bfChinese = BaseFont.createFont("C:\\windows\\fonts\\MINGLIU.TTC,1",BaseFont.IDENTITY_H,BaseFont.NOT_EMBEDDED);
	  Font FontChinese = new Font(bfChinese,10,Font.NORMAL);
	  Font FontChinese1 = new Font(bfChinese,7,Font.NORMAL);
	  Font FontChinese2 = new Font(bfChinese,8,Font.NORMAL);

	  document.addTitle("ROCKP");
	  document.addAuthor("MINGTAI");
	  document.addSubject("MINGTAI");
	  document.addKeywords("MINGTAI");
	  document.addCreator("MINGTAI");
	  document.open();
	  // 選擇檔案
	  InputStreamReader isr = new InputStreamReader(new FileInputStream(obj.getPath()),"ISO8859_1");
	  BufferedReader br = new BufferedReader(isr);
	  while ( ( Line = br.readLine() )!=null)
	  {

	      if(cb2.getSelectedItem().equals("黑白列印"))
	      {
		document.add(jpeg);
	      }

	      try
	      {
		row[0] = Line.substring(0,10);// 保單號 10
		row[1] = Line.substring(10,20);// 經紀人單位 10
		row[2] = Line.substring(20,31);// 經紀人業代 11
		row[3] = Line.substring(31,61);// 被保人姓名 30
		row[4] = Line.substring(61,91);// 要保人姓名 30
		row[5] = Line.substring(91,96);// 標的郵遞區號 5
		row[6] = Line.substring(96,196);// 標的地址 100
		row[7] = Line.substring(196,201);// 要保人通訊郵遞區號 5
		row[8] = Line.substring(201,261);// 要保人通訊地址 60
		row[9] = Line.substring(261,262);// 建築等級 1
		row[10] = Line.substring(262,265);// 樓層數 3
		row[11] = Line.substring(265,272);// 面積 7
		row[12] = Line.substring(272,279);// 生效日 7
		row[13] = Line.substring(279,286);// 到期日 7
		row[14] = Line.substring(286,297);// 火險保額 11
		row[15] = Line.substring(297,306);// 火險保費 9
		row[16] = Line.substring(306,317);// 建築內保額 11
		row[17] = Line.substring(317,326);// 建築內保費0 9
		row[18] = Line.substring(326,337);// 地震保額 11
		row[19] = Line.substring(337,346);// 地震保費 9
		row[20] = Line.substring(346,355);// 總保費 9
		row[21] = Line.substring(355,362);// 簽單日 7
		row[22] = Line.substring(362,372);// 被保人ID 10
		row[23] = Line.substring(372,387);// 被保人電話 15
		row[24] = Line.substring(387,389);// 統計單位 2
		row[25] = Line.substring(389,396);// 抵押權者(銀行) 7
		row[26] = Line.substring(396,399);// 建造年份 3
		row[27] = Line.substring(399,406);// 手續費 7
		row[28] = Line.substring(406,411);// 經手人代號 5
		row[29] = Line.substring(411,416);// 服務人代號 5
		row[30] = Line.substring(416,418);// 經紀人代號 BROKER 2
		row[31] = Line.substring(418,438);// 貸款編號 20
		row[32] = Line.substring(438,458);// 貸款帳號 20
		row[33] = Line.substring(458,463);// 火險費率 5
		row[34] = Line.substring(463,475);// 使用性質中文 12
		row[35] = Line.substring(475,485);// 使用性質 10
		row[36] = Line.substring(485,491);// 佣金率(給付率) 6
		row[37] = Line.substring(491,502);// 營業生財保額 11
		row[38] = Line.substring(502,511);// 營業生財保費 9
		row[39] = Line.substring(510,520);// 原保單號 10
		row[40] = Line.substring(520,530);// 要保人ID 10
		row[41] = Line.substring(530,602);// 備註 36*2=72
		row[42] = Line.substring(602,630);// 專案記號 28
		row[43] = Line.substring(630,640);// 行員ID
		row[44] = Line.substring(640,660);// 行員姓名
		// row[45] = Line.substring(599,619);//要保人生日
		// row[46] = Line.substring(599,619);//被保人生日
		// row[47] = Line.substring(599,619);//要保人關係
		// row[48] = Line.substring(599,619);//要保人代表人
		// row[49] = Line.substring(599,619);//被保人代表人
	      }
	      catch (StringIndexOutOfBoundsException e3)
	      {
		// JOptionPane.showMessageDialog(null,
		// row[0]+" error 轉碼錯誤");
		error[errori] = row[0];
		errori = errori+1;

	      }
	      // 中文轉碼

	      wrong = 0;
	      row[3] = ChgChCode(row[3],row[0],row[3]);
	      row[4] = ChgChCode(row[4],row[0],row[4]);
	      row[6] = ChgChCode(row[6],row[0],row[6]);
	      row[8] = ChgChCode(row[8],row[0],row[8]);
	      if(row[34]!=null)
		row[34] = ChgChCode(row[34],row[0],row[34]);
	      /******** 空白不處理 ********************************************/
	      if(row[41]!=null)
	      {
		row[41] = ChgChCode(row[41],row[0],row[41]);
	      }
	      if(row[44]!=null)
	      {
		row[44] = ChgChCode(row[44],row[0],row[44]);
	      }
	      if(wrong>0)
	      {
		error[errori] = row[0];
		errori = errori+1;
	      }

	      // 寫入PDF
	      // 保單號
	      // System.out.println(row[0]);
	      document.add(new Paragraph(25,"                                                                                       "+row[39]+"                                  ",FontChinese));
	      // 被保人姓名 地址 要保人姓名 地址
	      // System.out.println(row[8].trim().length());
	      if(row[8].trim().length()>15)
	      {
		str1 = row[8].trim().substring(0,15);
		str2 = row[8].trim().substring(15);
	      }
	      else
	      {
		str1 = row[8].trim();
		str2 = "";
	      }
	      // 要保人+通訊地址
	      document.add(new Paragraph(18,"                      "+StringNullCut(row[4],15)+"                                                                                         "+row[7]+str1,FontChinese2));
	      document.add(new Paragraph(7,"                                                                                                                                                                             "+str2,FontChinese2));
	      document.add(new Paragraph(16,"                                                 "+row[40]+"                 "+row[23],FontChinese));
	      document.add(new Paragraph(12,"                      "+StringNullCut(row[3],15)+"                                                                                         "+row[7]+str1,FontChinese2));
	      document.add(new Paragraph(7,"                                                                                                                                                                             "+str2,FontChinese2));
	      document.add(new Paragraph(16,"                                                 "+row[22]+"                 "+row[23],FontChinese));
	      // 總保額
	      document.add(new Paragraph(16,"                                  "+CatZero(ZeroCut(row[14])),FontChinese));
	      document.add(new Paragraph(0,"                                                                                                                                                                       "+CatZero(ZeroCut(row[15])),FontChinese));
	      document.add(new Paragraph(12,"                                                                                                      "+CatZero(ZeroCut(row[20])),FontChinese));
	      document.add(new Paragraph(0,"                                                                                                                                                                       "+CatZero(ZeroCut(row[19])),FontChinese));
	      document.add(new Paragraph(16,"                                  "+CatZero(ZeroCut(row[18]))+"                                                                                                                            0",FontChinese));
	      // 保險期間
	      document.add(new Paragraph(16,"                    12                      "+row[12].substring(0,3)+"         "+row[12].substring(3,5)+"        "+row[12].substring(5)+"                                      "+row[13].substring(0,3)+"      "+row[13].substring(3,5)+"           "+row[13].substring(5),FontChinese));
	      // 標的物地址
	      document.add(new Paragraph(26,"                "+StringNullCut(row[6],40)+row[5],FontChinese));
	      // 建築物
	      for(int i = 0; i<=13; i++)
	      {
		str3[i] = "  ";
	      }
	      try
	      {
		num1 = Integer.parseInt(row[9]);
	      }
	      catch (NumberFormatException e2)
	      {
		num1 = 0;
	      }
	      if(num1==1)
	      {
		str4 = "A1";
	      }
	      else
	      {
		if(num1==2)
		{
		    str4 = "A2";
		}
		else
		{
		    if(num1==3)
		    {
		        str4 = "B";
		    }
		    else
		    {
		        str4 = "C";
		    }
		}
	      }
	      str5 = ZeroCut(row[11]).substring(0,ZeroCut(row[11]).length()-2);
	      str6 = ZeroCut(row[11]).substring(ZeroCut(row[11]).length()-2);
	      document.add(new Paragraph(12,"             "+str3[0]+"               "+str3[1]+str3[2],FontChinese1));
	      document.add(new Paragraph("             "+str3[3]+"               "+str3[4]+str3[5]+str3[6]+str3[7],FontChinese1));
	      document.add(new Paragraph("             "+str3[8]+"               "+str3[9]+str3[10]+str3[11]+"                                                                                                                                                                                    "+row[10]+"                              "+str4,FontChinese1));
	      // 建築年份
	      document.add(new Paragraph("                                                                              "+row[26]+str3[12]+str3[13],FontChinese1));
	      document.add(new Paragraph("                                                                              "+str5+"."+str6,FontChinese1));
	      document.add(new Paragraph(88,"   ",FontChinese));
	      // 分項保費建築物
	      document.add(new Paragraph(22,"     01                                                                            "+CatZero(ZeroCut(row[14])),FontChinese));
	      document.add(new Paragraph(0,"                                                                                                               0."+row[33].substring(2)+"    1/1",FontChinese));
	      document.add(new Paragraph(0,"                                                                                                                                          "+CatZero(ZeroCut(row[15])),FontChinese));
	      document.add(new Paragraph(0,"                                                                                                                                                                                     "+str4,FontChinese));
	      // 加保動產
	      // document.add(new
	      // Paragraph(20,"     01                                                                            "+row[16]+"      0."+row[33].substring(2)+"    1/1           "+row[17]+"                           "+str4,FontChinese));
	      document.add(new Paragraph(" "));
	      document.add(new Paragraph(20,"         自101年1月1日起，住宅地震基本保險最高賠償責任調整為新台幣150萬元。",FontChinese));
	      // 基本地震
	      document.add(new Paragraph(20,"     02                                                                            "+CatZero(ZeroCut(row[18])),FontChinese));
	      document.add(new Paragraph(0,"                                                                                                               0.9        "+"1/1           ",FontChinese));
	      document.add(new Paragraph(0,"                                                                                                                                          "+CatZero(ZeroCut(row[19])),FontChinese));
	      document.add(new Paragraph(0,"                                                                                                                                                                                     "+str4,FontChinese));
	      // 備註
	      document.add(new Paragraph(" "));
	      document.add(new Paragraph(" "));
	      document.add(new Paragraph("                                                                                                              "+row[31]+row[32],FontChinese));
	      // 抵押權人
	      document.add(new Paragraph(" "));
	      document.add(new Paragraph(6,"                                                                                                                                                           "+row[1],FontChinese));
	      document.add(new Paragraph(12,"                                                                                                       "+row[25]+"                                     "+row[2],FontChinese));
	      // 其他
	      document.add(new Paragraph(" "));
	      document.add(new Paragraph(" "));
	      document.add(new Paragraph(40,"            "+"  2  "+"               "+row[25]+"                   "+row[30]+"                   "+row[28]+"                   ",FontChinese));
	      document.add(new Paragraph(15,"16.9% + 6%                                                                                                                                  "+row[24]+"                   "+row[29],FontChinese));
	      // 行員(遮碼)
	      document.add(new Paragraph(" "));
	      document.add(new Paragraph(" "));
	      /****** 空白不處理 *****************************************/

	      if(row[43]!=null)
		str1 = row[43].substring(0,4)+"****"+row[43].substring(8);
	      else
		str1 = "";
	      if(row[44]!=null)
		str2 = row[44].substring(0,1)+"＊"+row[44].substring(2);
	      else
		str2 = "";
	      document.add(new Paragraph(10,"                                                                                                                                                                                                                                                                       "+str1,FontChinese1));
	      document.add(new Paragraph(10,"                                                                                                                                                                                                                                                                       "+str2,FontChinese1));

	      document.newPage();
	  }

	  br.close();
        }
        catch (FileNotFoundException e1)
        {
	  e1.printStackTrace();
        }
        catch (IOException e1)
        {
	  e1.printStackTrace();
        }
        catch (DocumentException e1)
        {
	  e1.printStackTrace();
        }
        document.close();
        System.gc();
        // PdfEncryptor.encrypt(reader, os, strength, userPassword,
        // ownerPassword, permissions)
    }

    // 刪除數字前0
    public String ZeroCut(String st)
    {
        String st1 , st2 = null;
        for(int i = 1; i<=st.length(); i++)
        {
	  // System.out.println(st.substring(i-1,i));
	  if(st.substring(i-1,i).equals("0"))
	  {

	  }
	  else
	  {
	      st1 = st.substring(0,i).replace("0","  ");
	      st2 = st.substring(i,st.length());
	      st = st1+st2;
	      break;
	  }
        }
        return st;
    }

    // 刪除字串中空白全形並補齊位數
    public String StringNullCut(String st, int num)
    {
        int tlength = 0;
        st = st.trim();
        // st = st.replace("　", "");
        tlength = num-st.length();
        if(st.length()<num)
        {
	  for(int i = 0; i<=tlength; i++)
	  {
	      st = st.concat("　");
	  }
        }
        else
        {
	  st = st.substring(0,num);
        }
        // System.out.println(st.length());
        return st;
    }

    public String ChgChCode(String st, String plyno, String obj)
    {
        int tlength = 0;
        String st1 = "" , tst = "";
        try
        {
	  if(obj!=null)
	  {
	      obj = new String(obj.getBytes("ISO8859_1"),"MS950");
	  }
        }
        catch (UnsupportedEncodingException e1)
        {
	  // TODO 自動產生的 catch 區塊
	  e1.printStackTrace();
        }
        st = st.replace("0","０");
        st = st.replace("1","１");
        st = st.replace("2","２");
        st = st.replace("3","３");
        st = st.replace("4","４");
        st = st.replace("5","５");
        st = st.replace("6","６");
        st = st.replace("7","７");
        st = st.replace("8","８");
        st = st.replace("9","９");
        st = st.replace("+","＋ ");
        st = st.replace("　","");
        st = st.replace("","　");
        st = st.trim();
        tlength = st.length();
        for(int i = 0; i<=tlength-2; i = i+2)
        {
	  // System.out.println("1");
	  try
	  {
	      // tst = new String(st.substring(i,
	      // i+2).getBytes("ISO8859_1"),"Big5_HKSCS");
	      tst = new String(st.substring(i,i+2).getBytes("ISO8859_1"),"MS950");
	      // System.out.println(st.substring(i,
	      // i+2)+"-"+tst+"-"+tst.length());
	  }
	  catch (UnsupportedEncodingException e)
	  {
	      // e.printStackTrace();
	  }
	  if(tst.length()==2)
	  {
	      // System.out.println(plyno+"-"+st.substring(i,
	      // i+2)+"-"+tst+"-"+tst.length()+"-"+obj);
	      tst = "　";
	      wrong = wrong+1;
	  }
	  st1 = st1+tst;
        }
        // System.out.println(st1);
        return st1;
    }

    public static String CatZero(String str)
    {

        char[] charArr = str.toCharArray();
        int i = 0;
        String Newstr = "";
        for(i = 0; i<charArr.length; i++)
        {
	  if(charArr[i]=='0')
	  {
	      charArr[i] = ' ';
	  }
	  else
	  {
	      break;
	  }
        }
        for(i = 0; i<charArr.length; i++)
        {
	  Newstr = Newstr+charArr[i];
        }
        Newstr = Newstr.replaceAll(" ","");// 去半形空白
        Newstr = Newstr.replaceAll("　","");// 去全行空白
        // 轉換成千分位
        Format fm = new DecimalFormat();
        double Mnoey;
        try
        {
	  Mnoey = Integer.parseInt(Newstr);
	  Newstr = fm.format(Mnoey);
        }
        catch (NumberFormatException e)
        {

        }

        return Newstr;// 取0到9碼

    }

    public static String CatZero(int str)
    {
        // 轉換成千分位
        Format fm = new DecimalFormat();
        String Mnoey;
        Mnoey = fm.format(str);
        return Mnoey;// 取0到9碼

    }
}
