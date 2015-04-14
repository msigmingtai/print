package view.main;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import view.frame.CarPolicyFrame;
import view.frame.FirePolicyFrame;
import commans.function.ErrorWindows;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.net.ServerSocket;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @author 50123
 * @version 3.0
 * @category 火險要保書列印系統程式進入點
 * 
 * */
public class MainFrame extends JFrame
{
    private static final long serialVersionUID=-2519940835652565513L;
    private static JPanel contentPane;
    private static boolean showboxflag=true;
    @SuppressWarnings("unused")
    private static ServerSocket srvSocket=null;
    private static final int srvPort=12345; // 防止重複開啟

    public void SetOpenBoxFlag(boolean flag)
    {
	MainFrame.showboxflag=true;
    }

    public MainFrame() throws Exception
    {

	setResizable(false);
	setTitle("要保書列印程式");
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setBounds(100,100,281,92);
	setLocationRelativeTo(null); // 視窗至中
	contentPane=new JPanel();
	contentPane.setBorder(new EmptyBorder(5,5,5,5));
	setContentPane(contentPane);
	contentPane.setLayout(null);
	JButton btnNewButton=new JButton("火險");
	btnNewButton.setBounds(41,13,89,25);
	contentPane.add(btnNewButton);
	JButton btnNewButton_3=new JButton("\u8ECA\u96AA");
	btnNewButton_3.setBounds(147,13,89,25);
	contentPane.add(btnNewButton_3);
	btnNewButton_3.addActionListener(new ActionListener()
	{
	    public void actionPerformed(ActionEvent e)
	    {
		if(showboxflag)
		{
		    new CarPolicyFrame();
		    showboxflag=false;
		}
	    }
	});
	btnNewButton.addActionListener(new ActionListener()
	{
	    public void actionPerformed(ActionEvent e)
	    {
		if(showboxflag)
		{
		    new FirePolicyFrame();		    
		    showboxflag=false;
		}
	    }
	});
	addWindowListener(new WindowAdapter()
	{
	    @Override
	    public void windowClosed(WindowEvent arg0)
	    {
		System.exit(0);
	    }
	});
    }

    protected void checkSingleInstance()
    {
	try
	{ // 啟用ServerSocket，用以控制只能開啟一個程序
	    srvSocket=new ServerSocket(srvPort);
	}
	catch (Exception ex)
	{
	    if(ex.getMessage().indexOf("Address already in use: JVM_Bind")>=0)
	    {
		JOptionPane.showMessageDialog(null,"該程式已啟動","提示",JOptionPane.OK_OPTION);
		System.exit(0);

	    }
	}
    }

    public static void main(String[] args)
    {
	EventQueue.invokeLater(new Runnable()
	{
	    public void run()
	    {
		try
		{
		    MainFrame frame=new MainFrame();
		    frame.checkSingleInstance();
		    frame.setVisible(true);
		}
		catch (Exception e)
		{
		    new ErrorWindows(e);
		}
	    }
	});
    }

}
