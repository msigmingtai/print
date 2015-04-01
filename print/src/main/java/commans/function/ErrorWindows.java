package commans.function;

import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/*
 * 產生錯誤訊息框
 * 
 */
public class ErrorWindows extends JDialog
{
    /**
     * 
     */
    private static final long serialVersionUID=1L;
    private static String errors=new String();

    // Error Windows Get Throwable to display error Message
    public ErrorWindows(Throwable T)
    {
	// Use printWriter to get string
	ByteArrayOutputStream ostr=new ByteArrayOutputStream();
	T.printStackTrace(new PrintWriter(ostr,true));
	String foo=ostr.toString();
	ErrorWindows.errors=foo;
	// construct
	setTitle("Error");
	setBounds(100,100,410,227);
	setLocationRelativeTo(null);
	getContentPane().setLayout(null);
	setResizable(false);
	JScrollPane scrollPane=new JScrollPane();
	scrollPane.setBounds(14,13,376,129);
	getContentPane().add(scrollPane);
	JTextArea textArea=new JTextArea();
	textArea.setEditable(false);
	textArea.append(errors);
	scrollPane.setViewportView(textArea);
	{
	    JButton okButton=new JButton("Close");
	    okButton.setBounds(310,155,80,29);
	    getContentPane().add(okButton);
	    okButton.addActionListener(new ActionListener()
	    {
		public void actionPerformed(ActionEvent arg0)
		{
		    dispose();
		    System.exit(0);
		}
	    });
	    okButton.setActionCommand("OK");
	    getRootPane().setDefaultButton(okButton);
	}
	setVisible(true);
    }
}
