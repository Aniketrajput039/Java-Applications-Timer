
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class laps extends JFrame implements Runnable,ActionListener
{
	JButton bstop,breset,blap;
	int m,s,hr;
	JList list;
	Label lmin,lsec,lhr;
	TextField tmin,tsec,thr;
	String [] str;
	Panel p1,p2,p3;
	int i=0;
	String data;
	DefaultListModel<String> model;
	laps obj=null;
	laps()
	{

			super();
			m=s=hr=0;
			this.obj=this;
			p1=new Panel();
			model=new DefaultListModel<String>();
			list=new JList(model);
			p1.add(list);

			p2=new Panel();		
			bstop=new JButton("stop");
			breset=new JButton("reset");
			blap=new JButton("lap");

			bstop.addActionListener(this);
			breset.addActionListener(this);
			blap.addActionListener(this);
			p2.add(blap);
			p2.add(breset);
			p2.add(bstop);

			
			p3=new Panel();
			lmin=new Label("Min");
			lsec=new Label("Sec");
			lhr=new Label("hr");

			tmin=new TextField(2);
			tsec=new TextField(2);	
			thr=new TextField(2);

			p3.setLayout(new GridLayout(3,2,5,5));

			p3.add(lhr);
			p3.add(thr);
			p3.add(lmin);
			p3.add(tmin);
			p3.add(lsec);
			p3.add(tsec);
			
			add(p3,"Center");
			add(p2,"North");
			add(p1,"South");

			setSize(400,400);
			setVisible(true);

			tmin.setEditable(false);
			tsec.setEditable(false);
			thr.setEditable(false);

			tmin.setText(""+m);
			tsec.setText(""+s);
			thr.setText(""+hr);
	}

	public void run()
	{
		int cnt=0;
		while(true)
		{
			s++;

		try
		{
			Thread.sleep(1000);
		}
		catch(Exception e){}
		
		
			
		if(s>60)
		{
			s=s-60;
			m=m+1;

		}
		if(m>60)
		{
			m=m-60;
			hr=hr+1;
		}
		if(hr==24)
		{
			s=m=hr=0;
		}

		 data=hr+" :"+m+" :"+s;

			tmin.setText(""+m);
			tsec.setText(""+s);
			thr.setText(""+hr);	
			
			
		}
	}
	
	
	
	void wait1()
	{
		try
		{
			wait();
		}
		catch(Exception e) {}
	}

	
	void notify1()
	{
		try
		{
			notify();
		}
		catch(Exception e) {}
	}
	
	
	
	
	public  void actionPerformed(ActionEvent e)
	{
		JButton b=(JButton)e.getSource();
		
		if(b==bstop)
		{
			JOptionPane.showMessageDialog(obj,"Thank you" );
			System.exit(0);
		}
		if(b==breset)
		{
			s=m=hr=0;
			
			model.removeAllElements();
		}
		if(b==blap)
		{
			model.addElement(data);
		}
	}
}

class ThrdTime extends JFrame//implements Runnable
{
	laps lap;
	Thread th1;
	ThrdTime()
	{
		super();
		

		th1=new Thread(new laps());

		th1.start();
	}



	public static void main(String[] args) {
		ThrdTime t=new ThrdTime();
	}
}

