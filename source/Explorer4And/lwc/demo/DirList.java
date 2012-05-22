package lwc.demo;

import java.io.File;

import lwc.data.*;
import lwc.port.Graphics;
import lwc.ui.*;
import lwc.ui.grid.*;

public class DirList{
    private static DirGrd dirgrd;
    private static ScrollPan sp;
    
    private static Matrix model;
    
    public static String curdir=android.os.SystemConfig.getTempDir();//android.os.Environment.getRootDirectory());
    
	public DirList(int delta) {
		super();
	    Init(delta);
	    sp = new ScrollPan(dirgrd);
	}

	protected void Init(int delta) {
	    model = new Matrix(0, Global.DirLst_Col_Tit.length);
	    dirgrd = new DirGrd(model);
	    GridCaption cap  = new GridCaption(dirgrd);
	    for (int i=0;i<Global.DirLst_Col_Tit.length;i++)
	    	cap.putTitle (i, Global.DirLst_Col_Tit[i]);
	    cap.setOpaque(true);//.getViewMan(true).setView(new ImgSetRender(Graphics.alfImage(Toolkit.getImage(Integer.toString(R.drawable.bg_aurora_borealis)),3), 0, 0, 120, 16,ImgRender.ORIGINAL));
	    dirgrd.setColWidth(Global.DirLst_Col_Name, 95+delta);
	    dirgrd.setColWidth(Global.DirLst_Col_Size, 35);
	    dirgrd.setColWidth(Global.DirLst_Col_Type, 45+delta);
	    dirgrd.setColWidth(Global.DirLst_Col_Date, 95);
	    dirgrd.add(Grid.TOP_CAPTION_EL, cap);
	    dirgrd.setViewProvider(new GridAlignProvider());
	}
	
	public void LoadData(String path) {
		File cur=new File(path);
		File dir=cur;
		if (cur.isFile())
			dir=cur.getParentFile();
		if (!curdir.equalsIgnoreCase(dir.getAbsolutePath()))
		{
			int r=0,p=-1;
			if (model.getRows()>0)
				model.removeRows(0, model.getRows());
			for (File f:dir.listFiles())
			{
	   			LoadDataAdd(r, f);
	   			if (f.getAbsolutePath().equalsIgnoreCase(cur.getAbsolutePath()))
	   				p=r;
	   			r++;
			}
			curdir=dir.getAbsolutePath();
			if (p>-1)
	        	dirgrd.getPosController().setOffset(p);
			else if (r>0)
			{
	        	dirgrd.getPosController().setOffset(0);
	        	dirgrd.inflbl.setText(Global.Txt_Include+" "+dir.listFiles().length+" "+Global.Txt_Objects);
			}
		}
		else if (cur.isFile() && curdir.equalsIgnoreCase(dir.getAbsolutePath()))
			for (int i=0;i<model.getRows();i++)
				if (cur.getName().equalsIgnoreCase(model.get(i, Global.DirLst_Col_Name).toString()))
				{
		        	dirgrd.getPosController().setOffset(i);
					break;
				}
	}

	public void LoadDataAdd(int r, File f) {
		model.put(r,Global.DirLst_Col_Name,f.getName());
		model.put(r,Global.DirLst_Col_Size,Global.GetFileSize(f.length()));
		model.put(r,Global.DirLst_Col_Type,f.isFile()?Global.GetFileType(f.getName()):Global.Txt_Folder);
		model.put(r,Global.DirLst_Col_Date,Global.ForTime(f.lastModified()));
	}
	
	public ScrollPan GetSp() {
		return sp;
	}
	
	public Label GetInfLbl() {
		return dirgrd.inflbl;
	}

	public void Clear() {
	}

	public void requestFocus() {
		dirgrd.requestFocus();
	}

	public int GetRowCou() {
		return model.getRows();
	}

	public void DelItm(String str) {
		for (int i=0;i<model.getRows();i++)
			if (model.get(i, Global.DirLst_Col_Name).toString().equalsIgnoreCase(str))
			{
				model.removeRows(i, 1);
				if (i>0)
		        	dirgrd.getPosController().setOffset(i-1);
				else if (model.getRows()>0)
		        	dirgrd.getPosController().setOffset(0);
				break;
			}
	}

	public void RenameItm(String oldname, String name) {
		for (int i=0;i<model.getRows();i++)
			if (model.get(i, Global.DirLst_Col_Name).toString().equalsIgnoreCase(oldname))
			{
				model.put(i, Global.DirLst_Col_Name, name);
				break;
			}
	}
}

class DirGrd extends Grid {
	public Label inflbl;
    
    public DirGrd(MatrixModel data) {
    	super(data);
    	setNetMask(0);
	    //setBackground(lwc.port.Color.white);
    	inflbl=new Label("");
	}
    
    public void posChanged(Object target, int prevOffset, int prevLine, int prevCol) {
    	super.posChanged(target, prevOffset, prevLine, prevCol);
    	int off = controller.getCurrentLine();
    	if (off >= 0)
    		inflbl.setText(getModel().get(off, Global.DirLst_Col_Name).toString()+" "+
    				       getModel().get(off, Global.DirLst_Col_Type).toString()+" "+
    				       getModel().get(off, Global.DirLst_Col_Date).toString()+" "+
    				       getModel().get(off, Global.DirLst_Col_Size).toString());
    }
}

class GridAlignProvider extends DefViews {
	public GridAlignProvider() {
		super();
	}
	
	public /*C#override*/ View getView (int row, int col, Object data) {
		if (data instanceof String)
			return new TextRender((String)data);
		else
			return null;
	}

	public /*C#override*/ int getXAlignment (int row, int col) {
		switch (col)
		{
			case Global.DirLst_Col_Name:
			case Global.DirLst_Col_Type:
			case Global.DirLst_Col_Date:
				return Toolkit.LEFT;
			case Global.DirLst_Col_Size:
				return Toolkit.RIGHT;
			default:
				return Toolkit.CENTER;
		}
	}

	public /*C#override*/ int getYAlignment (int row, int col) {
	    return Toolkit.CENTER;
	}
}
