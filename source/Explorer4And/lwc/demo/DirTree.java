package lwc.demo;

import java.io.File;

import lwc.data.Item;
import lwc.data.TreeModel;
import lwc.ui.Toolkit;
import lwc.ui.event.KeyEvent;
import lwc.ui.notab.TreeNT;

public class DirTree extends TreeNT {
	private DirTreeListener trelis;
	private boolean onlyfolder;
	
	public DirTree(DirTreeListener trelis,TreeModel model,boolean onlyfolder) {
		super(Toolkit.ncanvas,model,
				Integer.toString(R.drawable.tre_tog_on),Integer.toString(R.drawable.tre_tog_off),
				Integer.toString(R.drawable.tre_least),Integer.toString(R.drawable.tre_opened),
				Integer.toString(R.drawable.tre_closed),Integer.toString(R.drawable.lwc));
		this.trelis=trelis;
		this.onlyfolder=onlyfolder;
		setColor(lwc.ui.tree.Tree.LN_COLOR, null);
	}
	
	protected int getItmTyp(Item i)
	{
		return ((DirTreeItem)i).sort;
	}

	public void keyPressed (KeyEvent e)
	{
		boolean isort=((DirTreeItem)selected).sort==TreeNT.TYP_Sort;
		boolean isenter=e.getKeyCode()==Toolkit.VK_ENTER;
		super.keyPressed(e);
		if (isort && isenter)
			toggle((DirTreeItem)selected);
	}	
	  
	public void toggle(Item item)
	{
    	super.toggle(item);
		Load((DirTreeItem)item);
	}
	
	public void Load(DirTreeItem item)
	{
		if (getModel().getChildrenCount(item)==1 && 
				getModel().getChildAt(item, 0).getValue().toString().equalsIgnoreCase(Global.DirTre_Txt_Loading))
			getModel().remove(getModel().getChildAt(item, 0));
		if (getModel().hasChildren(item))
			return;
		File dir=new File(item.path);
		if (dir.isDirectory())
		{
			for (File f:dir.listFiles())
			{
				if (onlyfolder && !f.isDirectory())
					continue;
				DirTreeItem subitem = LoadAdd(item, f);
				if (f.isDirectory() && f.listFiles().length>0)
				{
					getModel().add(subitem, 
							new DirTreeItem(TreeNT.TYP_Item,Global.DirTre_Txt_Loading,
									Global.DirTre_Txt_Loading,false,true));
					super.toggle(subitem);
				}
			}
		}
	}

	protected DirTreeItem LoadAdd(DirTreeItem item, File f) {
		DirTreeItem subitem=new DirTreeItem(f.isDirectory()?TreeNT.TYP_Sort:TreeNT.TYP_Item,
				f.getName(),f.getAbsolutePath(),!f.canWrite(),f.isHidden());
		getModel().add(item, subitem);
		return subitem;
	}
	
	public void select(Item item)
	{
		super.select(item);
		if (item!=null)
		{
			Load((DirTreeItem)item);
			trelis.select((DirTreeItem)item);
		}
	}
	
	public DirTreeItem getSelItm()
	{
		return (DirTreeItem)getSelectedItem();
	}
	
	public String getSelItmName()
	{
		return ((DirTreeItem)getSelectedItem()).getValue().toString();
	}
	
	public String getSelItmPath()
	{
		return ((DirTreeItem)getSelectedItem()).path;
	}

	public void DelItm(DirTreeItem selitm) {
		getModel().remove(selitm);
	}

	public String getSelItmParPath() {
		DirTreeItem par=(DirTreeItem)getModel().getParent(getSelectedItem());
		return par.path;
	}

	public void RenameItm(DirTreeItem selitm, String name) {
		selitm.setValue(name);
		selitm.path=((DirTreeItem)getModel().getParent(selitm)).path+"/"+name;
		RefreshSub(selitm);
	}
	
	private void RefreshSub(DirTreeItem selitm) {
		if (selitm.sort==TreeNT.TYP_Item)
			return;
		while (getModel().getChildrenCount(selitm)>0)
			getModel().remove(getModel().getChildAt(selitm, 0));
		Load(selitm);
	}

	private void RefreshItm(DirTreeItem ti) {
		File f=new File(ti.path);
		ti.readonly=!f.canWrite();
		ti.hidefile=f.isHidden();
		ti.setValue(f.getName());
	}
	
	public void RefreshSel(boolean andchild) {
		DirTreeItem ti=(DirTreeItem)getSelectedItem();
		RefreshItm(ti);
		if (andchild)
			RefreshSub(ti);
	}
}
