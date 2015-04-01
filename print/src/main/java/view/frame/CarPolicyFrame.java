package view.frame;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import model.entity.CarPolicyEntity;
import model.policy.CarPolicy;
import view.main.MainFrame;
import commans.function.ErrorWindows;
import controller.car.CarPolicyPrint;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.List;

/**
 * 
 * 車險要保書進入點
 * 
 **/
public class CarPolicyFrame extends JFrame
{

    /**
     * 
     */
    private static final long serialVersionUID=-7342271675574047497L;
    private static String selectprint=null;
    private static CarPolicy cars=new CarPolicy();
    private static List<CarPolicyEntity> policys;
    private File insertFile=null;
    private JFileChooser fc=new JFileChooser();
    private int ret=1;

    /**
     * Launch the application.
     */
    @SuppressWarnings("unchecked")
    public CarPolicyFrame()
    {

	String icon="Logo.PNG";
	ImageIcon img=new ImageIcon(getClass().getResource("/"+icon));
	setIconImage(img.getImage());
	setResizable(false);
	setTitle("\u6C7D\u8ECA\u8981\u4FDD\u66F8");
	setBounds(100,100,241,156);
	setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	getContentPane().setLayout(null);

	JButton OpenFilebt=new JButton("\u958B\u555F");
	OpenFilebt.addActionListener(new ActionListener()
	{
	    public void actionPerformed(ActionEvent e)
	    {
		fc.setCurrentDirectory(new java.io.File("C:\\"));
		ret=fc.showOpenDialog(null);
		insertFile=fc.getSelectedFile();
	    }
	});
	JButton Createbt=new JButton("\u7522\u751F\u6A94\u6848");

	JButton exitbt=new JButton("\u96E2\u958B");

	OpenFilebt.setBounds(118,16,90,23);
	getContentPane().add(OpenFilebt);

	JLabel lblNewLabel=new JLabel("\u9078\u64C7\u958B\u555F\u6A94\u6848\uFF1A");
	lblNewLabel.setBounds(14,18,105,19);
	getContentPane().add(lblNewLabel);

	JComboBox<Object> comboBox=new JComboBox<Object>();
	comboBox.setModel(new DefaultComboBoxModel<Object>(new String[] { "一般列印", "套表列印" }));
	comboBox.setBounds(118,53,115,23);
	getContentPane().add(comboBox);

	JLabel label=new JLabel("\u9078\u64C7\u5217\u5370\u65B9\u5F0F\uFF1A");
	label.setBounds(14,55,105,19);
	getContentPane().add(label);

	Createbt.setBounds(14,92,90,23);
	getContentPane().add(Createbt);

	exitbt.setBounds(118,92,93,23);
	getContentPane().add(exitbt);
	setLocationRelativeTo(null);
	setVisible(true);
	exitbt.addActionListener(new ActionListener()
	{
	    public void actionPerformed(ActionEvent arg0)
	    {
		try
		{
		    new MainFrame().SetOpenBoxFlag(true);
		}
		catch (Exception e)
		{
		    new ErrorWindows(e);
		}
		dispose();
	    }
	});
	addWindowListener(new WindowAdapter()
	{
	    public void windowClosing(WindowEvent e)
	    {
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
	selectprint=comboBox.getSelectedItem().toString();
	Createbt.addActionListener(new ActionListener()
	{
	    public void actionPerformed(ActionEvent e)
	    {
		if(ret==JFileChooser.APPROVE_OPTION)
		{
		    if(selectprint.equals("一般列印"))
		    {
			try
			{
			    policys=cars.getPolicy(insertFile.getAbsolutePath());
			    new Thread(new CarPolicyPrint(policys)).start();
			}
			catch (Exception e1)
			{
			    new ErrorWindows(e1);
			}

		    }
		    else
		    {
		    }
		}
		else
		{
		    JOptionPane.showMessageDialog(null,"無任何何檔案");

		}
	    }
	});
    }
}
