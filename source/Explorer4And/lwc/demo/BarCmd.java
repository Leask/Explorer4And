package lwc.demo;

import lwc.ui.*;
import lwc.ui.event.ActionListener;

public class BarCmd extends Panel implements ActionListener {
	
	public BarCmd(boolean dual) {
		this(Toolkit.HORIZONTAL,0,dual);
	}

	public BarCmd(int orient, int gap, boolean dual) {
		super();
		setLayout(new BorderLayout());
		Toolbar tb1=new Toolbar(orient, gap);
		tb1.setPSSize(-1, 28);
		//getViewMan(true).setBorder("br.raised");
		if (dual) {
    		Global.BarCmd[Global.BarCmd_Back]=Global.GenBtn(Global.BarCmd_Back, this);
    		tb1.add(Global.BarCmd[Global.BarCmd_Back]);
    		Global.BarCmd[Global.BarCmd_Forward]=Global.GenBtn(Global.BarCmd_Forward, this);
    		tb1.add(Global.BarCmd[Global.BarCmd_Forward]);
    		Global.BarCmd[Global.BarCmd_Refresh]=Global.GenBtn(Global.BarCmd_Refresh, this);
    		tb1.add(Global.BarCmd[Global.BarCmd_Refresh]);
    		Global.BarCmd[Global.BarCmd_Property]=Global.GenBtn(Global.BarCmd_Property, this);
    		tb1.add(Global.BarCmd[Global.BarCmd_Property]);
    		//
			Toolbar tb2=new Toolbar(orient, gap);
			tb2.setPSSize(-1, 24);
    		Global.BarCmd[Global.BarCmd_Cut]=Global.GenBtn(Global.BarCmd_Cut, this);
    		tb2.add(Global.BarCmd[Global.BarCmd_Cut]);
    		Global.BarCmd[Global.BarCmd_Copy]=Global.GenBtn(Global.BarCmd_Copy, this);
    		tb2.add(Global.BarCmd[Global.BarCmd_Copy]);
    		Global.BarCmd[Global.BarCmd_Paste]=Global.GenBtn(Global.BarCmd_Paste, this);
    		tb2.add(Global.BarCmd[Global.BarCmd_Paste]);
    		Global.BarCmd[Global.BarCmd_Undo]=Global.GenBtn(Global.BarCmd_Undo, this);
    		tb2.add(Global.BarCmd[Global.BarCmd_Undo]);
    		//
	    	add(BorderLayout.CENTER, tb1);
	    	add(BorderLayout.SOUTH, tb2);
		} else {
	    for (int i=0;i<Global.BarCmd.length;i++)
	    	if (Global.BarCmd_Tit[i].equalsIgnoreCase("|"))
	    		;//addLine(Toolkit.VERTICAL|Border.SUNKEN2);
	    	else
	    	{
	    		Global.BarCmd[i]=Global.GenBtn(i, this);
	    		tb1.add(Global.BarCmd[i]);
	    	}
	    	add(BorderLayout.CENTER, tb1);
		}
	}

	public void actionPerformed(Object src, Object data) {
		if (src instanceof SpeedButton) {
			int tag=((SpeedButton)src).tag;
			if (tag == 9 && Explorer.actions.get(Global.Menu_File_Property_) != null)
				Explorer.actions.get(Global.Menu_File_Property_).execute(src, data);
			else if (tag == 2 && Explorer.actions.get(Global.Menu_View_Refresh_) != null)
				Explorer.actions.get(Global.Menu_View_Refresh_).execute(src, data);
			else if (tag == 0 && Explorer.actions.get(Global.Menu_View_Back_) != null)
				Explorer.actions.get(Global.Menu_View_Back_).execute(src, data);
			else if (tag == 1 && Explorer.actions.get(Global.Menu_View_Forward_) != null)
				Explorer.actions.get(Global.Menu_View_Forward_).execute(src, data);
			else if (tag == 4 && Explorer.actions.get(Global.Menu_Edit_Cut_) != null)
				Explorer.actions.get(Global.Menu_Edit_Cut_).execute(src, data);
			else if (tag == 5 && Explorer.actions.get(Global.Menu_Edit_Copy_) != null)
				Explorer.actions.get(Global.Menu_Edit_Copy_).execute(src, data);
			else if (tag == 6 && Explorer.actions.get(Global.Menu_Edit_Paste_) != null)
				Explorer.actions.get(Global.Menu_Edit_Paste_).execute(src, data);
			else if (tag == 7 && Explorer.actions.get(Global.Menu_Edit_Undo_) != null)
				Explorer.actions.get(Global.Menu_Edit_Undo_).execute(src, data);
		};
	}
}