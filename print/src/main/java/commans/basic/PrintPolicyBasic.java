package commans.basic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.text.DecimalFormat;
import java.text.Format;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.border.EmptyBorder;

import model.entity.FirePolicyEntity;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.pdf.Barcode39;
import com.lowagie.text.pdf.BaseFont;

import com.lowagie.text.pdf.PdfWriter;

import commans.function.ChangeCode;
import commans.function.ErrorWindows;

/*
 * 
 * 列印功能皆繼承此類別，繼承時注入保單entity即可
 * 
 **/
public abstract class PrintPolicyBasic<T extends BasicPolicyEntity> extends
	JFrame implements Serializable, Runnable
{

    /**
     * @author 50123
     */
    private static final long serialVersionUID=3613685661636920306L;
    public final static File ROOT=new File("C:\\");
    public final static JProgressBar progressBar=new JProgressBar();
    // 設定字型
    public static BaseFont bfChinese;
    public static Font FontChinese;
    public static Font FontChinese1;
    public static Font FontChinese2;    
    public final static String policy_type_A="A";
    public final static String policy_type_W="W";
    public final static String policy_type_G="G";
    // Input Output File Mode
    public File fileA=null;
    public File fileW=null;
    public FileWriter fw1=null;
    public FileWriter fw2=null;
    public InputStreamReader isr=null;
    public BufferedReader br=null;
    public InputStreamReader isrA=null;
    public BufferedReader brA=null;
    public InputStreamReader isrW=null;
    public BufferedReader brW=null;
    // iText Mode
    public PdfWriter writer=null;
    public Document document=new Document();
    public Image jpeg=null;
    public Barcode39 code39=new Barcode39();
    public String str1=null, str2=null, str4=null, str5=null, str6=null;
    public String Line=null, line1=null, line2=null, line3=null, line4=null,
	    line5=null, line6=null;
    public String Pkind=null;

    // File Name
    public String path="D:\\temp.pdf";;
    public String broker=null;
    public int commit=0;
    public Runtime rt=Runtime.getRuntime();
    // New function
    public static ChangeCode function=new ChangeCode();
    // OpenFile Mode
    public JFileChooser fc=new JFileChooser();
    public int ret=1;
    // Program Attribute
    public String row[]=new String[150];
    public String str3[]=new String[20];
    public int num1=0;
    public int num2=0;
    public int effect_date=0;
    public int expire_date=0;
    public FirePolicyEntity ply;
    public int const_class;
    public int checkAMT=0;
    public List<T> obj=new ArrayList<T>();
    public String Unit_Old;
    public String Unit_new;
    // 判別是否自動出單列印新保單號或續保號
    public boolean isAuto=true;
    // ProgressBar Mode
    public JPanel contentPane;
    public int currentValue;
    public int count;
    public long totalBytes=0;
    public long byteRead=0;

    public PrintPolicyBasic(List<T> obj) throws DocumentException, IOException
    {
	this.obj=obj;
	setTitle("Progress");
	setResizable(false);
	setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	setBounds(100,100,326,122);
	contentPane=new JPanel();
	contentPane.setBorder(new EmptyBorder(5,5,5,5));
	setContentPane(contentPane);
	contentPane.setLayout(null);
	setLocationRelativeTo(null); // 視窗至中
	progressBar.setBounds(14,34,290,21);
	// 設置初始值與最大值
	progressBar.setStringPainted(true);
	progressBar.setValue(0);
	progressBar.setMaximum(100);
	contentPane.add(progressBar);
	JLabel lblNewLabel=new JLabel("\u7522\u751F\u4E2D...........");
	lblNewLabel.setBounds(56,13,217,19);
	contentPane.add(lblNewLabel);
	// 設定字型
	bfChinese=BaseFont.createFont("C:\\windows\\fonts\\MINGLIU.TTC,1",BaseFont.IDENTITY_H,BaseFont.NOT_EMBEDDED);
	FontChinese=new Font(bfChinese,10,Font.NORMAL);
	FontChinese1=new Font(bfChinese,7,Font.NORMAL);
	FontChinese2=new Font(bfChinese,8,Font.NORMAL);	
	setVisible(true);

    }

    protected void deleteFile(String path)
    {
	File deleteFile;
	deleteFile=new File(path);
	deleteFile.delete();
    }

    protected void deleteFile(File obj)
    {
	File deleteFile;
	deleteFile=obj;
	deleteFile.delete();
    }

    protected void ErrorMessage(Throwable e)
    {
	new ErrorWindows(e);
    }

    protected static String MakesUpSpace(String str, int lenSize)
    {

	String space="　";// 全形空白
	str=str.replaceAll("　",""); // 取代全形空白為""
	str=str.replaceAll(" ",""); // 取代半形空白為""
	String returnValue=null;
	returnValue=str;
	for(int i=0; i<lenSize; i++)
	{
	    returnValue=returnValue+space;
	}

	return returnValue.substring(0,lenSize);//

    }

    protected static String MakesUpSpace2(String str, int lenSize)
    {

	String space=" ";// 半形空白
	str=str.replaceAll("　",""); // 取代全形空白為""
	str=str.replaceAll(" ",""); // 取代半形空白為""
	String returnValue=null;
	returnValue=str;
	for(int i=0; i<lenSize; i++)
	{
	    returnValue=returnValue+space;
	}
	return returnValue;// 取0到9碼
    }

    // 其他險代號如果為00就去掉
    protected static String CutPolicyNumber(String str)
    {

	if(str.equals("00"))
	{
	    str="  ";
	}
	return str;// 取0到9碼
    }

    // 金額前面為0的部分去掉//金額等於0就不顯示
    protected static String CatZero(String str)
    {

	char[] charArr=str.toCharArray();
	int i=0;
	String Newstr="";
	for(i=0; i<charArr.length; i++)
	{
	    if(charArr[i]=='0')
	    {
		charArr[i]=' ';
	    }
	    else
	    {
		break;
	    }
	}
	for(i=0; i<charArr.length; i++)
	{
	    Newstr=Newstr+charArr[i];
	}
	Newstr=Newstr.replaceAll(" ","");// 去半形空白
	Newstr=Newstr.replaceAll("　","");// 去全行空白
	// 轉換成千分位
	Format fm=new DecimalFormat();
	double Mnoey;
	try
	{
	    Mnoey=Integer.parseInt(Newstr);
	    Newstr=fm.format(Mnoey);
	}
	catch (NumberFormatException e)
	{

	}
	return Newstr;// 取0到9碼
    }

    // 計算總筆數
    @SuppressWarnings("unused")
    public void getTotalbyte()
    {
	for(T ply : obj)
	{
	    this.totalBytes=this.totalBytes+1;
	}
    }

    // 顯示目前進度
    public void setProgressBar()
    {
	byteRead=byteRead+1;
	currentValue=(int)(byteRead*100/totalBytes);
	progressBar.setValue(currentValue);
    }

    public void setJpg(String jpgPath) throws BadElementException,
	    MalformedURLException, IOException
    {
	jpeg=Image.getInstance(PrintPolicyBasic.class.getResource(jpgPath));
	jpeg.setAlignment(Image.UNDERLYING);
	jpeg.scaleAbsolute(590,840);
	jpeg.setAbsolutePosition(0,0);
    }

    public void setPDFTitle()
    {
	document.addTitle("Mingtai");
	document.addAuthor("Mingtai");
	document.addSubject("Mingtai");
	document.addKeywords("Mingtai");
	document.addCreator("Mingtai");
    }

    public String getConstClass(String level)
    {
	String constclass;
	if(level.equals("1"))
	{
	    constclass="A1";
	}
	else if(level.equals("2"))
	{
	    constclass="A2";
	}
	else if(level.equals("3"))
	{
	    constclass="B";
	}
	else
	{
	    constclass="C";
	}
	return constclass;
    }
    public String transRelate(String relate)
    {
	String relateName = " ";
	if(relate.equals("1"))
	{
	    relateName="本人及其家屬";
	}
	else if(relate.equals("2"))
	{
	    relateName="同一法人";
	}
	else if(relate.equals("3"))
	{
	    relateName="契約";
	}
	else if(relate.equals("4"))
	{
	    relateName="其他";
	}else
	{
	    relateName=" ";
	}
	
	return relateName;
    }

    public String getTotalAmount(String fireprem, String eprem)
    {

	int total_amount=0;
	try
	{
	    if(fireprem.trim()!=null)
	    {
		total_amount=total_amount+Integer.parseInt(fireprem.trim());
	    }
	    if(eprem.trim()!=null)
	    {
		total_amount=total_amount+Integer.parseInt(eprem.trim());
	    }
	}
	catch (NumberFormatException e2)
	{
	    new ErrorWindows(e2);
	}
	return ChangeCode.CatZero(total_amount);
    }

    // 取得第三人保額
    public String getDeathAmount(String amt)
    {
	String deathamt=null;
	int v_amt;
	try
	{
	    v_amt=Integer.parseInt(amt.trim());
	}
	catch (java.lang.NumberFormatException e)
	{
	    v_amt=0;
	}
	v_amt=v_amt/1000;
	if(v_amt==1)
	{
	    deathamt="50/500";
	}
	else if(v_amt==2)
	{
	    deathamt="100/1000";
	}
	else if(v_amt==3)
	{
	    deathamt="150/1500";
	}
	else if(v_amt==4)
	{
	    deathamt="200/2000";
	}
	else if(v_amt==5)
	{
	    deathamt="250/2500";
	}
	else
	{
	    deathamt="        ";
	}

	return deathamt;
    }

    // 取得財損保額
    public String getProductAmount(String amt)
    {
	String productamt=null;
	int v_amt;
	try
	{
	    v_amt=Integer.parseInt(amt.trim());
	}
	catch (java.lang.NumberFormatException e)
	{
	    v_amt=0;
	}
	v_amt = v_amt / 1000;
	if(v_amt==1)
	{
	    productamt="50";
	}
	else if(v_amt==2)
	{
	    productamt="100";
	}
	else if(v_amt==3)
	{
	    productamt="150";
	}
	else if(v_amt==4)
	{
	    productamt="200";
	}
	else if(v_amt==5)
	{
	    productamt="250";
	}

	return productamt;
    }

    public String get7lowAmount(String amt)
    {
	String amt7=null;
	int v_amt;
	try
	{
	    v_amt=Integer.parseInt(amt.trim());
	}
	catch (java.lang.NumberFormatException e)
	{
	    v_amt=0;
	}
	v_amt=v_amt/300000;
	if(v_amt==1)
	{
	    amt7="10";
	}
	else if(v_amt==2)
	{
	    amt7="20";
	}
	else if(v_amt==3)
	{
	    amt7="30";
	}
	else if(v_amt==4)
	{
	    amt7="40";
	}
	else if(v_amt==5)
	{
	    amt7="50";
	}
	else
	{
	    amt7="        ";
	}
	return amt7;
    }

    public String get7highAmount(String amt)
    {
	String amt7=null;
	int v_amt;
	try
	{
	    v_amt=Integer.parseInt(amt.trim());
	}
	catch (java.lang.NumberFormatException e)
	{
	    v_amt=0;
	}
	v_amt=v_amt/300000;
	if(v_amt==1)
	{
	    amt7="30";
	}
	else if(v_amt==2)
	{
	    amt7="60";
	}
	else if(v_amt==3)
	{
	    amt7="90";
	}
	else if(v_amt==4)
	{
	    amt7="120";
	}
	else if(v_amt==5)
	{
	    amt7="150";
	}
	else
	{
	    amt7="        ";
	}
	return amt7;
    }

    @Override
    public abstract void run();

    public abstract void CreatePolicy();
}
