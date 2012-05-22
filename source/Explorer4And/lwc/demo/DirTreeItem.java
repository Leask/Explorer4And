package lwc.demo;

import lwc.data.Item;

public class DirTreeItem extends Item {
	public byte sort;
	public String path;
	public boolean readonly;
	public boolean hidefile;
	
	public DirTreeItem(byte sort,String name,String path,boolean readonly,boolean hidefile)
	{
		super(name);
		this.sort=sort;
		this.path=path;
		this.readonly=readonly;
		this.hidefile=hidefile;
	}
}
