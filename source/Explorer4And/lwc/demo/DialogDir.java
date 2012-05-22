package lwc.demo;

import lwc.ui.*;
import lwc.ui.event.ActionListener;
import lwc.util.DialogAsk;

public class DialogDir extends DialogAsk {
	private DirLoad dirtresel;
	private boolean onlyfolder;
	
	public DialogDir(int icon, String tit, Widget fcp, ActionListener al, boolean onlyfolder) {
		super(icon,tit,fcp,al);
		canscroll=false;
		this.onlyfolder=onlyfolder;
	}
	
	public DialogDir(int icon, String tit, Widget fcp, ActionListener al) {
		this(icon,tit,fcp,al,true);
	}

	@Override
	protected void genOptPnl(Panel panel) {
		//DirTre
		dirtresel=new DirLoad(onlyfolder);
		dirtresel.GetTree().setPSSize((new Double(Toolkit.disw*0.6)).intValue(), -1);
		panel.add(BorderLayout.CENTER, dirtresel.GetSp());
	}
	
	public DirTreeItem getValue() {
		return dirtresel.GetTree().getSelItm();
	}
}
