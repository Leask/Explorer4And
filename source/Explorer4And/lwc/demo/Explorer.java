package lwc.demo;

import lwc.port.Graphics;
import lwc.ui.*;

public class Explorer extends Activity {
	public static DirOpertor dirtre;
	public static DirList    dirlst;
	public static ExplorerActionList actions=new ExplorerActionList();
	
    public void init() {
    	int type=R.drawable.bgql;//320*240
    	if (Toolkit.disw==240)//240*320
    		type=R.drawable.bgqp;
    	else if (Toolkit.disw==480)
    		type=R.drawable.bghl;//480*320
    	else if (Toolkit.dish>450)
    		type=R.drawable.bghp;//320*480
				ImgRender imgrender=new ImgRender(Toolkit.getImage(Integer.toString(type)),ImgRender.ORIGINAL);
				getRoot().getViewMan(true).setView(imgrender);
				getRoot().setLayout(new BorderLayout());
	    		
	    		if (Toolkit.disw>Toolkit.dish)
	    		{
		    		//CmdBar
		    		BarCmd tb=new BarCmd(false);
		    		getRoot().add(BorderLayout.SOUTH, tb);
		    		//DirLst
		    		int disw6=(new Double(Toolkit.disw*0.6)).intValue();
		    		if (disw6>Global.DirLst_Wid)
		    			dirlst=new DirList((disw6-Global.DirLst_Wid)/2);
		    		else
		    			dirlst=new DirList(0);
		    		//DirTre
		    		dirtre=new DirOpertor(this,false);
		    		//DirTreLst
		    		SplitPan sptrelst = new SplitPan(dirtre.GetSp(),dirlst.GetSp());
					//sptrelst.getViewMan(true).setView(Toolkit.bkrender);
		    		sptrelst.setOpaque(true);
		    		sptrelst.setBackground(new lwc.port.Color(225,235,235));
		    		sptrelst.setGripperLoc(Toolkit.disw-disw6);
		    		getRoot().add(BorderLayout.CENTER, sptrelst);
		    		//InfBar
		    		BarInf infbar=new BarInf(dirlst.GetInfLbl());
		    		getRoot().add(BorderLayout.NORTH, infbar);
		    	}
	    		else
	    		{
		    		//CmdBar
		    		BarCmd tb=new BarCmd(type==R.drawable.bgqp);
		    		getRoot().add(BorderLayout.SOUTH, tb);
		    		//DirLst
		    		if (Toolkit.disw>Global.DirLst_Wid)
		    			dirlst=new DirList((Toolkit.disw-Global.DirLst_Wid)/2);
		    		else
		    			dirlst=new DirList(0);
		    		//DirTre
		    		dirtre=new DirOpertor(this,false);
		    		//DirTreLst
		    		SplitPan sptrelst = new SplitPan(dirtre.GetSp(),dirlst.GetSp(),Toolkit.HORIZONTAL);
					//sptrelst.getViewMan(true).setView(Toolkit.bkrender);
		    		sptrelst.setOpaque(true);
		    		sptrelst.setBackground(new lwc.port.Color(215,225,225));
		    		sptrelst.setGripperLoc((new Double(Toolkit.dish*0.4)).intValue());
		    		getRoot().add(BorderLayout.CENTER, sptrelst);
		    		//InfBar
		    		BarInf infbar=new BarInf(dirlst.GetInfLbl());
		    		getRoot().add(BorderLayout.NORTH, infbar);
	    		}
	    		
	    		setContentView(Toolkit.ncanvas);
    }
	  
	  protected void onStart() {
	      super.onStart();
	      dirtre.requestFocus();
	  }
}