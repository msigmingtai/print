package view.frame;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.List;
import javax.swing.*;

import com.lowagie.text.DocumentException;

import view.main.MainFrame;
import commans.function.ErrorWindows;
import commans.function.SplitPolicy;
import commans.sort.SortByBankEmp;
import controller.far.FirePolicyAType;
import controller.far.FirePolicyATypeAuto;
import controller.far.FirePolicyATypeBlank;
import controller.far.FirePolicyEType;
import controller.far.FirePolicyGType;
import controller.far.FirePolicyWType;
import controller.far.FirePolicyWTypeAuto;
import controller.far.FirePolicyWTypeBlank;
import controller.far.FirePolicyXType;
import model.entity.FirePolicyEntity;
import model.policy.FirePolicy;

/*
 * 
 * 火險程式進入點
 * 
 * */
public final class FirePolicyFrame extends JFrame implements ActionListener
{
    /**
     * 
     */
    private static final long serialVersionUID=7223030681976274137L;
    private static final JLabel lb=new JLabel("請選擇要保書列印資料檔 ",JLabel.LEFT);
    private static final JLabel lb1=new JLabel("請選擇要保書列種類",JLabel.LEFT);
    private static final JComboBox<String> cb1=new JComboBox<String>();
    private static final String basePrint="套表列印";
    private static final String BWPrint="黑白列印";
    private static List<FirePolicyEntity> policys;
    private static List<FirePolicyEntity> policyA;
    private static List<FirePolicyEntity> policyW;
    private static List<FirePolicyEntity> policyG;
    private static FirePolicy firepolicy=new FirePolicy();

    private JButton OpenButton=new JButton("開啟");
    private JButton CreatPDFButton=new JButton("\u8981\u4FDD\u66F8\u5217\u5370");
    private JButton CloseButton=new JButton("關閉");
    private int ret=1;
    private JFileChooser fc=new JFileChooser();
    private File insertFile=null;
    // private File[] FileArray=new File[18];
    // private SortByEmpa sortEmpa;
    private SortByBankEmp sortAgentemp;

    public FirePolicyFrame()
    {
	// 視窗LOGO
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	String icon="Logo.PNG";
	ImageIcon img=new ImageIcon(getClass().getResource("/"+icon));
	setIconImage(img.getImage());
	Container cp=getContentPane();
	getContentPane().setLayout(null);
	lb.setBounds(10,10,153,20);
	cp.add(lb);
	OpenButton.setBounds(163,5,75,25);
	cp.add(OpenButton);
	OpenButton.addActionListener(this);
	lb1.setBounds(10,45,119,15);
	cp.add(lb1);
	cb1.setModel(new DefaultComboBoxModel<String>(new String[] { "丙式列印", "住火續保列印", "屋主續保列印", "家庭成員", "基本地震險" }));
	cb1.setBounds(149,41,89,23);
	cb1.addItem(basePrint);
	cb1.addItem(BWPrint);
	cp.add(cb1);
	CreatPDFButton.setBounds(10,70,109,25);
	cp.add(CreatPDFButton);
	CreatPDFButton.addActionListener(this);
	CloseButton.setBounds(149,70,89,25);
	cp.add(CloseButton);
	CloseButton.addActionListener(this);
	setTitle("明台產物保險要保書列印程式");
	setSize(255,135);
	setLocationRelativeTo(null); // 視窗至中
	setResizable(false);
	setVisible(true);
	setDefaultCloseOperation(DISPOSE_ON_CLOSE);
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
		    new ErrorWindows(e1);
		}
		dispose();
	    }
	});
	this.requestFocus();
    }

    public void actionPerformed(final ActionEvent e)
    {

	try
	{
	    // Open file Default root
	    fc.setCurrentDirectory(new java.io.File("C:\\workf"));
	    if(e.getSource()==OpenButton)
	    { // Open choose file windows
		ret=fc.showOpenDialog(null);
		insertFile=fc.getSelectedFile();
	    }
	    else if(e.getSource()==CloseButton)
	    { // exit program
		try
		{
		    new MainFrame().SetOpenBoxFlag(true);
		}
		catch (Exception e1)
		{
		    new ErrorWindows(e1);
		}
		dispose();
	    }
	    else
	    {
		// Open and choose file
		if(ret==JFileChooser.APPROVE_OPTION)
		{
		    if(insertFile.exists())
		    {

			if(cb1.getSelectedItem().equals(basePrint))
			{
			    // None Background
			    PrintNobackground();
			}
			else if(cb1.getSelectedItem().equals("丙式列印"))
			{
			    FirePolicyXType PrintX=new FirePolicyXType(null);
			    PrintX.SetObj(insertFile);
			    new Thread(PrintX,"CreatePolicy").start();
			}
			else if(cb1.getSelectedItem().equals("屋主續保列印"))
			{
			    firepolicy.getPolicy(insertFile.getAbsolutePath());
			    policys=firepolicy.SortPolicy();
			    new Thread(new FirePolicyWTypeAuto(policys),"CreatePolicy").start();
			}
			else if(cb1.getSelectedItem().equals("住火續保列印"))
			{
			    firepolicy.getPolicy(insertFile.getAbsolutePath());
			    policys=firepolicy.SortPolicy();
			    new Thread(new FirePolicyATypeAuto(policys),"CreatePolicy").start();
			}
			else if(cb1.getSelectedItem().equals("家庭成員"))
			{
			    firepolicy.getPolicy(insertFile.getAbsolutePath());
			    policys=firepolicy.SortPolicy();
			    policyG=new SplitPolicy(policys).GetGpolicy();
			    if(!policyG.isEmpty())
				new Thread(new FirePolicyGType(policyG),"CreatePolicy").start();
			    else
				JOptionPane.showMessageDialog(null,"無檔案");
			}
			else if(cb1.getSelectedItem().equals("基本地震險"))
			{
			    firepolicy.getPolicy(insertFile.getAbsolutePath());
			    policys=firepolicy.SortPolicy();
			    new Thread(new FirePolicyEType(policys),"CreatePolicy").start();
			}
			else
			{ // Have background
			    PrintPolicy();
			}
		    }
		}
		else
		{
		    JOptionPane.showMessageDialog(null,"無匯入FAP554F檔案");
		}
	    }
	}
	catch (Exception e1)
	{
	    new ErrorWindows(e1);
	}
	finally
	{
	    // initialize object
	    firepolicy=null;
	    firepolicy=new FirePolicy();
	}
    }

    @SuppressWarnings("unchecked")
    public void PrintNobackground() throws IOException, DocumentException
    {
	// 產生要列印之保單
	policys=firepolicy.getPolicy(insertFile.getAbsolutePath());
	policyA=new SplitPolicy(policys).GetApolicy();
	policyW=new SplitPolicy(policys).GetWpolicy();
	// 送印
	if(!policyA.isEmpty())
	    new Thread(new FirePolicyATypeBlank(policyA),"CreatePolicy").start();
	if(!policyW.isEmpty())
	    new Thread(new FirePolicyWTypeBlank(policyW),"CreatePolicy").start();

    }

    @SuppressWarnings("unchecked")
    public void PrintPolicy() throws IOException, DocumentException
    {
	policys=firepolicy.getPolicy(insertFile.getAbsolutePath());
	policyA=new SplitPolicy(policys).GetApolicy();
	policyW=new SplitPolicy(policys).GetWpolicy();
	// 送印
	if(!policyA.isEmpty())
	{
	    sortAgentemp=new SortByBankEmp();
	    sortAgentemp.setPolicys(policyA);
	    new Thread(new FirePolicyAType(sortAgentemp.getPolicys()),"CreatePolicyA").start();
	}
	if(!policyW.isEmpty())
	{
	    new Thread(new FirePolicyWType(policyW),"CreatePolicyW").start();
	}

    }

}
