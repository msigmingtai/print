package commans.basic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.List;

import com.lowagie.text.DocumentException;

@SuppressWarnings("rawtypes")
public abstract class CreateTempFileClass extends PrintPolicyBasic implements
	Serializable
{
    private static final long serialVersionUID=-3256897369246410153L;

    @SuppressWarnings("unchecked")
    public CreateTempFileClass(List obj) throws DocumentException, IOException
    {
	super(obj);
    }

    private File objA=null;
    private File objW=null;

    @SuppressWarnings("unchecked")
    public void CreateTempFile(File obj)
    {
	String Line, line1, line2, line3, line4, line5, line6=null;
	String Pkind=null;
	File ROOT=new File("C:\\");
	commit=1;
	try
	{
	    fileA=File.createTempFile("住火保單",".tmp",ROOT);
	    fileW=File.createTempFile("屋主保單",".tmp",ROOT);
	    fw1=new FileWriter(fileA);
	    fw2=new FileWriter(fileW);
	    isr=new InputStreamReader(new FileInputStream(obj.getPath()),"ISO8859_1");
	    br=new BufferedReader(isr);

	    while ((Line=br.readLine())!=null)
	    {
		// Key Value of type
		Pkind=Line.substring(425,426);
		commit=commit+1;
		// Change to Chinese
		line1=new String(Line.substring(40,70).getBytes("ISO8859_1"),"MS950");
		line2=new String(Line.substring(156,216).getBytes("ISO8859_1"),"MS950");
		line3=new String(Line.substring(226,306).getBytes("ISO8859_1"),"MS950");
		line4=new String(Line.substring(376,406).getBytes("ISO8859_1"),"MS950");
		line5=new String(Line.substring(461,473).getBytes("ISO8859_1"),"MS950");
		line6=new String(Line.substring(482,494).getBytes("ISO8859_1"),"MS950");
		// **************************Change Text Length*******
		Line=Line.substring(0,40)+line1+Line.substring(70,156)+line2+Line.substring(216,226)+line3+Line.substring(306,376)+line4+Line.substring(406,461)+line5+Line.substring(473,482)+line6;
		// System.out.println(line2);
		// System.out.println(line3);
		// Write Temp File
		if(Pkind.equals("A"))
		{
		    fw1.write(Line+"\r\n");
		    objA=fileA;

		}
		else if(Pkind.equals("W"))
		{
		    fw2.write(Line+"\r\n");
		    objW=fileW;
		}
	    }
	    fw1.close();
	    fw2.close();
	    br.close();
	    isr.close();
	    // Recovery temp file
	    if(fileW==null||fileW.length()==0)
		fileW.delete();
	    if(fileA==null||fileA.length()==0)
		fileA.delete();

	}
	catch (IOException e)
	{
	    try
	    {
		fw1.close();
		fw2.close();
		br.close();
		isr.close();
	    }
	    catch (IOException e1)
	    {
		System.out.println("CreateTempFile IOException e1");
		ErrorMessage(e1);
	    }
	    deleteFile(fileA);
	    deleteFile(fileW);
	    ErrorMessage(e);
	}
	catch (java.lang.StringIndexOutOfBoundsException e)
	{
	    try
	    {
		fw1.close();
		fw2.close();
		br.close();
		isr.close();
	    }
	    catch (IOException e1)
	    {
		System.out.println("CreateTempFile java.lang.StringIndexOutOfBoundsException e");
		ErrorMessage(e1);
	    }
	    deleteFile(fileA);
	    deleteFile(fileW);
	    ErrorMessage(e);
	}

    }

    public File GetAtype()
    {
	return objA;
    };

    public File GetWtype()
    {
	return objW;
    }
}
