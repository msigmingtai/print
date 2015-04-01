package view.frame;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import model.entity.FirePolicyEntity;
import model.policy.FireEPolicy;
import commans.function.ErrorWindows;
import controller.far.FirePolicyETypeAuto;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

/**
 * 單保基本地震險要保書
 */
public class FireEtypeFrame extends JFrame
{

    /**
     * 
     */
    private static final long serialVersionUID=-6407901167500545744L;
    private static JPanel contentPane;
    private static File insertFile=null;
    private int ret=1;
    private static JFileChooser fc=new JFileChooser();

    /**
     * Launch the application.
     */
    public static void main(String[] args)
    {
	EventQueue.invokeLater(new Runnable()
	{
	    public void run()
	    {
		try
		{
		    FireEtypeFrame frame=new FireEtypeFrame();
		    frame.setVisible(true);
		}
		catch (Exception e)
		{
		    new ErrorWindows(e);
		}
	    }
	});
    }

    /**
     * Create the frame.
     */
    public FireEtypeFrame()
    {
	setTitle("基本地震險要保書");
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	String icon="Logo.PNG";
	ImageIcon img=new ImageIcon(getClass().getResource("/"+icon));
	setIconImage(img.getImage());
	setBounds(100,100,263,90);
	contentPane=new JPanel();
	contentPane.setBorder(new EmptyBorder(5,5,5,5));
	setContentPane(contentPane);
	contentPane.setLayout(null);
	JButton btnNewButton=new JButton("開啟檔案");
	setLocationRelativeTo(null); // 視窗至中
	setResizable(false);
	btnNewButton.addActionListener(new ActionListener()
	{
	    public void actionPerformed(ActionEvent arg0)
	    {
		fc.setCurrentDirectory(new java.io.File("C:\\workf"));
		ret=fc.showOpenDialog(null);
		insertFile=fc.getSelectedFile();
	    }
	});
	btnNewButton.setBounds(14,13,101,29);
	contentPane.add(btnNewButton);
	JButton btnNewButton_1=new JButton("產生");
	btnNewButton_1.addActionListener(new ActionListener()
	{
	    public void actionPerformed(ActionEvent arg0)
	    {
		if(ret==JFileChooser.APPROVE_OPTION)
		{
		    FireEPolicy firepolicy=new FireEPolicy();
		    try
		    {
			firepolicy.getPolicy(insertFile.getAbsolutePath());
			List<FirePolicyEntity> policys=firepolicy.SortPolicy();

			if(policys.size()>0)
			    new Thread(new FirePolicyETypeAuto(policys),"CreatePolicy").start();
			else
			    JOptionPane.showMessageDialog(null,"無資料可列印");
		    }
		    catch (FileNotFoundException e1)
		    {
			JOptionPane.showMessageDialog(null,"找不到"+insertFile.getAbsolutePath());
		    }
		    catch (Exception e)
		    {
			new ErrorWindows(e);
		    }

		}
		else
		{
		    JOptionPane.showMessageDialog(null,"無匯入檔案");
		}
	    }
	});
	btnNewButton_1.setBounds(131,13,101,29);
	contentPane.add(btnNewButton_1);
    }
}
