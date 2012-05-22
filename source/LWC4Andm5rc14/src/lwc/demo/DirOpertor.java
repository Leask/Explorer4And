package lwc.demo;

import java.io.File;
import java.net.URISyntaxException;

import android.content.Intent;
import android.net.Uri;

import lwc.ui.Label;
import lwc.ui.notab.TreeNT;

public class DirOpertor extends DirLoad{
	private Explorer explorer;
	
	public DirOpertor(Explorer explorer,boolean onlyfolder) {
		this(explorer,Global.DirTre_Txt_InitDir,onlyfolder);
	}
	
	public DirOpertor(Explorer explorer,String rootdir,boolean onlyfolder) {
		super(rootdir,onlyfolder);
		this.explorer=explorer;
	}
	
	public DirOpertor(Label inflbl,File root,boolean onlyfolder) {
		super(root,onlyfolder);
	}

	public void Clear() {
	}
	
	public void select(DirTreeItem ti) {
		/*
		Global.BarCmd[Global.BarCmd_New].setEnabled(ti.sort!=TreeNT.TYP_Item);
		Global.BarCmd[Global.BarCmd_Refresh].setEnabled(ti.sort!=TreeNT.TYP_Root);
		Global.BarCmd[Global.BarCmd_Delete].setEnabled(ti.sort!=TreeNT.TYP_Root);
		Global.BarCmd[Global.BarCmd_Rename].setEnabled(ti.sort!=TreeNT.TYP_Root);
		Global.BarCmd[Global.BarCmd_Open].setEnabled(ti.sort==TreeNT.TYP_Item);
		*/
		Explorer.dirlst.LoadData(ti.path);
	}
	
	public boolean New(String name)	{
		DirTreeItem selitm=(DirTreeItem)tree.getSelectedItem();
		File f=new File(selitm.path+"/"+name);
		if (f.mkdir())
		{
			tree.LoadAdd(selitm,f);
			Explorer.dirlst.LoadDataAdd(Explorer.dirlst.GetRowCou(), f);
			return true;
		}
		else
			return false;
	}

	public Boolean Rename(String name) {
		DirTreeItem selitm=(DirTreeItem)tree.getSelectedItem();
		File f=new File(selitm.path);
		if (f.renameTo(new File(tree.getSelItmParPath()+"/"+name)))
		{
			Explorer.dirlst.RenameItm(selitm.getValue().toString(),name);
			((DirTree) tree).RenameItm(selitm,name);
			return true;
		}
		else
			return false;
	}

	public Boolean Delete() {
		DirTreeItem selitm=(DirTreeItem)tree.getSelectedItem();
		File f=new File(selitm.path);
		if (f.delete())
		{
			int i=treeModel.getChildIndex(selitm);
			DirTreeItem paritm=(DirTreeItem)treeModel.getParent(selitm);
			int c=treeModel.getChildrenCount(paritm);
			tree.DelItm(selitm);
			Explorer.dirlst.DelItm(selitm.getValue().toString());
			if (c==1)
				tree.select(paritm);
			else
			{
				if (i==0)
					tree.select(treeModel.getChildAt(paritm, 0));
				else
					tree.select(treeModel.getChildAt(paritm, i-1));
			}
			return true;
		}
		else
			return false;
	}
	
    public void Open() { 
		DirTreeItem selitm=(DirTreeItem)tree.getSelectedItem();
		File f=new File(selitm.path);
		if (f.isDirectory())
			return;
        Intent myIntent = new Intent(android.content.Intent.VIEW_ACTION, 
		      android.net.Uri.parse("file://" + f.getAbsolutePath())); 
		 explorer.startActivity(myIntent); 
   } 
}
