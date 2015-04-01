package commans.function;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.text.Format;
import java.util.Calendar;

/*
 * 
 *常用功能轉成千分位、取得日期、轉碼 、產生銀行名稱
 * 
 */
public class ChangeCode
{

    private Calendar cal=Calendar.getInstance();
    private int day=cal.get(Calendar.DATE);
    private int month=cal.get(Calendar.MONTH)+1;
    private int year=cal.get(Calendar.YEAR);
    private String time=Integer.toString(year)+"-"+Integer.toString(month)+"-"+Integer.toString(day);

    public String ChangeCodeData(String word)
    {
	System.out.println(word);
	word=word.replaceAll("","牛丹");
	System.out.println(word);
	return word;
    }

    public String ChangeBankName(String broker, String path, String PolicyType,
	    String chose)
    {

	File FileName=null;
	File DuName=null;
	String BankName=null;
	FileName=new File(path);
	if(broker.equals("L8"))
	{
	    BankName="第一銀行";
	}
	else if(broker.equals("XA"))
	{
	    BankName="永豐銀行";
	}
	else if(broker.equals("XQ"))
	{
	    BankName="高雄銀行";
	}
	else if(broker.equals("Y6"))
	{
	    BankName="渣打銀行";
	}
	else if(broker.equals("WA"))
	{
	    BankName="元大銀行";
	}
	else if(broker.equals("MB"))
	{
	    BankName="淡水一信";
	}
	else if(broker.equals("WY"))
	{
	    BankName="日盛銀行";
	}
	else if(broker.equals("S2"))
	{
	    BankName="台中商銀";
	}
	else if(broker.equals("KQ"))
	{
	    BankName="萬泰銀行";
	}
	else
	{
	    BankName="";
	}

	BankName="D:\\"+BankName+"-"+chose+PolicyType+"-"+time+".pdf";
	DuName=new File(BankName);

	if(DuName.exists())
	{
	    DuName.delete();
	    FileName.renameTo(new File(BankName));
	}
	else
	{
	    FileName.renameTo(new File(BankName));
	}

	return BankName;

    }

    public static String CatZero(int str)
    {

	Format fm=new DecimalFormat();
	String Mnoey;
	Mnoey=fm.format(str);
	return Mnoey;

    }

    public static String CatZero(String str)
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
	// 頧?????
	if(!Newstr.equals(null)&&!Newstr.equals(""))
	{
	    Format fm=new DecimalFormat();
	    double Mnoey;
	    try
	    {
		Mnoey=Integer.parseInt(Newstr);
		Newstr=fm.format(Mnoey);
	    }
	    catch (NumberFormatException e)
	    {
		new ErrorWindows(e);
	    }
	}

	return Newstr;// ????蝣?

    }

    public static String MakesUpSpace2(String str, int lenSize)
    {

	String space=" ";// ?耦蝛箇
	str=str.replaceAll("　",""); // 取代全形空白為""
	str=str.replaceAll(" ",""); // 取代半形空白為""
	String returnValue=null;
	returnValue=str;
	for(int i=0; i<lenSize; i++)
	{
	    returnValue=returnValue+space;
	}
	return returnValue;// ????蝣?
    }

    public String ChgChCode(String st, String plyno)
    {
	int tlength=0;
	String st1="", tst="";
	tlength=st.length();
	String error[]=new String[2000];
	int errori=0;
	for(int i=0; i<=tlength-2; i=i+2)
	{
	    // System.out.println("1");
	    try
	    {
		tst=new String(st.substring(i,i+2).getBytes("ISO8859_1"),"Big5_HKSCS");
		// System.out.println(st.substring(i,
		// i+2)+"-"+tst+"-"+tst.length());
	    }
	    catch (UnsupportedEncodingException e)
	    {
		new ErrorWindows(e);
	    }
	    if(tst.length()==2)
	    {
		tst="　";
		error[errori]=plyno;
		errori=errori+1;
	    }
	    st1=st1+tst;
	}
	// System.out.println(st1);
	return st1;
    }

    public String StringNullCut(String st, int num)
    {
	int tlength=0;
	st=st.trim();
	// st = st.replace("??, "");
	tlength=num-st.length();
	if(st.length()<num)
	{
	    for(int i=0; i<=tlength; i++)
	    {
		st=st.concat("　");
	    }
	}
	else
	{
	    st=st.substring(0,num);
	}
	return st;
    }

    public String ZeroCut(String st)
    {
	String st1, st2=null;
	for(int i=1; i<=st.length(); i++)
	{
	    if(st.substring(i-1,i).equals("0"))
	    {

	    }
	    else
	    {
		st1=st.substring(0,i).replace("0","  ");
		st2=st.substring(i,st.length());
		st=st1+st2;
		break;
	    }
	}
	return st;
    }

    public String Gettimer()
    {

	return time;
    }

}
