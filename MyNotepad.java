import javax.swing.*;
import java.io.*;
import javax.swing.JOptionPane;
import java.awt.event.*;
import java.awt.*;
public class MyNotepad extends JFrame implements ActionListener
 {      
	JMenuBar menubar;
	JMenu file_menu;
        JOptionPane jp=new JOptionPane();
        JOptionPane jp2=new JOptionPane();
	JMenuItem new_item,open_item,saveas_item,save_item;
	JFileChooser jfc;
	JTextArea txt;
	JScrollPane jsp;
	File temp;
	MyNotepad()
	{
		super("My Notepad");
	   menubar=new JMenuBar();
	   file_menu=new JMenu("File");
	   menubar.add(file_menu);
	   new_item=new JMenuItem("New");
	   open_item= new JMenuItem("Open");
	   save_item=new JMenuItem("Save");
	   saveas_item= new JMenuItem("Save As");
	   file_menu.add(new_item);
	   file_menu.add(open_item);
	   file_menu.add(save_item);
	   file_menu.add(saveas_item);
	   jfc=  new JFileChooser();
	   txt=new JTextArea();
	   jsp=new JScrollPane(txt);
	   add(jsp);
	   txt.setFont(new Font("arial",Font.BOLD,20));
	   setJMenuBar(menubar);
	   new_item.addActionListener(this);
	   open_item.addActionListener(this);
	   save_item.addActionListener(this);
	   saveas_item.addActionListener(this);
	   setSize(1000,700);
	   setVisible(true);
	   setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public void actionPerformed(ActionEvent e)
	{
		JMenuItem source=(JMenuItem)e.getSource();
		if(source==new_item)
		{
			setTitle("Untitled Document");
			txt.setText("");
		}
		else if(source== open_item)
		{
			try
			{
			StringBuffer sb=new StringBuffer(2000);
			jfc.showOpenDialog(this);
			File f= jfc.getSelectedFile();
			setTitle(f.getName());
			temp=f;
			FileInputStream fin=new FileInputStream(f);
			int x=0;
			while(x!=-1)
			{
				x=fin.read();
				sb.append((char)x);
			}
			txt.setText(String.valueOf(sb));
			fin.close();

			}catch(Exception ee)
			{
				txt.setText(ee.getMessage());
			}	
	  	}
	  	else if(source== saveas_item)
	  	{
	  		String str=txt.getText();
	  		if(str.equals(""))
	  		{
	  		jp.showMessageDialog(this, "Enter Some Content");
	  		}
	  		else
	  			{
	  				try
	  				{
	  				jfc.showSaveDialog(this);
					File f= jfc.getSelectedFile();
					FileWriter fw=new FileWriter(f);
					setTitle(f.getName());
					fw.write(str);
					fw.close();
					}catch(Exception ee)
					{
						txt.setText(ee.getMessage());
					}
					
	  			}
	  	}
	  	else if(source== save_item)
	  	{
	  		
	  		String title=getTitle();
	  		String str=txt.getText();
	  		if(title.equals("Untitled Document") || title.equals("My Notepad"))
	  		{	
	  		if(str.equals(""))
	  		{
	  			jp2.showMessageDialog(this, "Enter Some Content");
	  		}
	  		else
	  			{
	  				try
	  				{
	  				jfc.showSaveDialog(this);
					File f= jfc.getSelectedFile();
					temp=f;
					FileWriter fw=new FileWriter(f);
					setTitle(f.getName());
					fw.write(str);
					fw.close();
					}catch(Exception ee)
					{
						txt.setText(ee.getMessage());
					}
					
	  			}
	  		}
	 		else
	  		{
	  			try{
	  			FileWriter fw=new FileWriter(temp);
					fw.write(str);
					fw.close();
				}catch(Exception ee)
				{
					txt.setText(ee.getMessage());
				}
	  		}
	  	}
	  		
	}
	public static void main(String args[])
	{
		new MyNotepad();
	}
}
