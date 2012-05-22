package lwc.demo;

import java.io.File;

import lwc.ui.action.Action;
import lwc.ui.action.ActionList;
import lwc.ui.action.ActionListListener;
import lwc.ui.event.ActionListener;
import lwc.util.DialogAsk;
import lwc.util.DialogInput;
import lwc.util.DialogMessage;

public class ExplorerActionList extends ActionList {

	public ExplorerActionList() {
		add(Global.Menu_File_New_, new ActionNew(this));
		add(Global.Menu_File_Delete_, new ActionDelete(this));
		add(Global.Menu_File_Rename_, new ActionRename(this));
		add(Global.Menu_File_Open_, new Action() {public void execute(Object src, Object data){Explorer.dirtre.Open();}});
		add(Global.Menu_View_Refresh_, new Action() {public void execute(Object src, Object data){Explorer.dirtre.tree.RefreshSel(true);}});
		add(Global.Menu_File_Property_, new ActionProperty(this));
		add(Global.Menu_Edit_CopyTo_, new ActionTo(this, Global.Menu_Edit_CopyTo));
		add(Global.Menu_Edit_MoveTo_, new ActionTo(this, Global.Menu_Edit_MoveTo));
		add(Global.Menu_View_Back_, new ActionDemo(Global.Menu_View_Tit[Global.Menu_View_Back]));
		add(Global.Menu_View_Forward_, new ActionDemo(Global.Menu_View_Tit[Global.Menu_View_Forward]));
		add(Global.Menu_Edit_Cut_, new ActionDemo(Global.Menu_Edit_Tit[Global.Menu_Edit_Cut]));
		add(Global.Menu_Edit_Copy_, new ActionDemo(Global.Menu_Edit_Tit[Global.Menu_Edit_Copy]));
		add(Global.Menu_Edit_Paste_, new ActionDemo(Global.Menu_Edit_Tit[Global.Menu_Edit_Paste]));
		add(Global.Menu_Edit_Undo_, new ActionDemo(Global.Menu_Edit_Tit[Global.Menu_Edit_Undo]));
	}
	
	public void update(Object src, Object data) {
	}

	class ActionDemo implements Action, ActionListener {
		private DialogMessage dlgMsg;
		private String s;

		public ActionDemo(String s) {
			this.s = s;
		}

		public void execute(Object src, Object data) {
			dlgMsg=new DialogMessage(R.drawable.lwc,Global.Txt_Inform,Explorer.dirtre.GetTree(),
					this,Global.Txt_TabStr+" This's the \""+s+"\" function.");
			dlgMsg.showModal();
		}

		public void actionPerformed(Object src, Object data) {
			if (dlgMsg!=null && (src == dlgMsg.btnOk)) {
				dlgMsg.hide();
				dlgMsg=null;
			}
		}
	}

	class ActionNew implements Action, ActionListener {
		private ActionListListener l;
		private DialogInput dlgNew;
		private DialogMessage dlgMsg;

		public ActionNew(ActionListListener l) {
			this.l = l;
		}

		public void execute(Object src, Object data) {
			dlgNew=new DialogInput(Global.Menu_File_Rid[Global.Menu_File_New],Global.Menu_File_Tit[Global.Menu_File_New],Explorer.dirtre.GetTree(),
					this,Global.Txt_Name,Global.Menu_File_Tit[Global.Menu_File_New]+" "+Global.Txt_Folder);
			dlgNew.show();
		}

		public void actionPerformed(Object src, Object data) {
			if (dlgMsg!=null && (src == dlgMsg.btnOk)) {
				dlgMsg.hide();
				dlgMsg=null;
			}
			else if (dlgNew != null && (src == dlgNew.btnOk || src == dlgNew.btnCancel)) {
				Boolean b=true;
				String s=dlgNew.getValue();
				dlgNew.hide();
				if (src == dlgNew.btnOk)
					b=Explorer.dirtre.New(s);
				dlgNew=null;
				if (!b)
				{
					dlgMsg=new DialogMessage(R.drawable.lwc,Global.Txt_Inform,Explorer.dirtre.GetTree(),
							this,Global.Txt_TabStr+Global.Menu_File_Tit[Global.Menu_File_New]+" \""+s+"\" "+
							Global.Txt_Folder+" "+Global.Txt_Failure);
					dlgMsg.showModal();
				}
				else
					l.update(src, data);
			}
		}
	}
	
	class ActionDelete implements Action, ActionListener {
		private ActionListListener l;
		private DialogAsk dlgask;
		private DialogMessage dlgMsg;

		public ActionDelete(ActionListListener l) {
			this.l = l;
		}

		public void execute(Object src, Object data) {
			dlgask=new DialogAsk(R.drawable.lwc,Global.Txt_Inform,Explorer.dirtre.GetTree(),
					this,Global.Txt_TabStr+Global.Txt_Sure+" "+Global.Menu_File_Tit[Global.Menu_File_Delete]+
					" \n\""+Explorer.dirtre.tree.getSelItmPath()+"\" "+Global.Txt_Folder+Global.Txt_QuestMark);
			dlgask.showModal();
		}

		public void actionPerformed(Object src, Object data) {
			if (dlgMsg!=null && (src == dlgMsg.btnOk)) {
				dlgMsg.hide();
				dlgMsg=null;
			}
			else if (dlgask != null && (src == dlgask.btnOk || src == dlgask.btnCancel)) {
				Boolean b=true;
				dlgask.hide();
				if (src==dlgask.btnOk)
					b=Explorer.dirtre.Delete();
				dlgask=null;
				if (!b)
				{
					dlgMsg=new DialogMessage(R.drawable.lwc,Global.Txt_Inform,Explorer.dirtre.GetTree(),
							this,Global.Txt_TabStr+Global.Menu_File_Tit[Global.Menu_File_Delete]+" \""+
							Explorer.dirtre.tree.getSelItmPath()+"\" "+Global.Txt_Folder+" "+Global.Txt_Failure);
					dlgMsg.showModal();
				}
				else
					l.update(src, data);
			}
		}
	}
	
	class ActionRename implements Action, ActionListener {
		private ActionListListener l;
		private DialogInput dlginput;
		private DialogMessage dlgMsg;

		public ActionRename(ActionListListener l) {
			this.l = l;
		}

		public void execute(Object src, Object data) {
			dlginput=new DialogInput(Global.BarCmd_Rid[Global.Menu_File_Rename],Global.Menu_File_Tit[Global.Menu_File_Rename],Explorer.dirtre.GetTree(),
					this,Global.Txt_Name,Explorer.dirtre.tree.getSelItmName());
			dlginput.showModal();
		}

		public void actionPerformed(Object src, Object data) {
			if (dlgMsg!=null && (src == dlgMsg.btnOk)) {
				dlgMsg.hide();
				dlgMsg=null;
			}
			else if (dlginput != null && (src == dlginput.btnOk || src == dlginput.btnCancel)) {
				Boolean b=true;
				String s=dlginput.getValue();
				dlginput.hide();
				if (src==dlginput.btnOk)
					b=Explorer.dirtre.Rename(s);
				dlginput=null;
				if (!b)
				{
					dlgMsg=new DialogMessage(R.drawable.lwc,Global.Txt_Inform,Explorer.dirtre.GetTree(),
							this,Global.Txt_TabStr+Global.Menu_File_Tit[Global.Menu_File_Rename]+" \""+s+"\" "+
							Global.Txt_Folder+" "+Global.Txt_Failure);
					dlgMsg.showModal();
				}
				else
					l.update(src, data);
			}
		}
	}

	class ActionProperty implements Action, ActionListener {
		private ActionListListener l;
		private DialogProperty dlgproperty;

		public ActionProperty(ActionListListener l) {
			this.l = l;
		}

		public void execute(Object src, Object data) {
			DirTreeItem ti=Explorer.dirtre.tree.getSelItm();
			File f=new File(ti.path);
			if (dlgproperty!=null)
			{
				dlgproperty.update(ti.path,f.length(),f.isFile()?Global.GetFileType(f.getName()):Global.Txt_Folder,
						Global.ForTime(f.lastModified()),!f.canWrite(),f.isHidden());
				dlgproperty.calc();
			}
			dlgproperty=new DialogProperty(Global.BarCmd_Rid[Global.Menu_File_Property],Global.Menu_File_Tit[Global.Menu_File_Property],
					Explorer.dirtre.GetTree(),this,ti.path,f.length(),
					f.isFile()?Global.GetFileType(f.getName()):Global.Txt_Folder,Global.ForTime(f.lastModified()),
							!f.canWrite(),f.isHidden());
			dlgproperty.show();
		}

		public void actionPerformed(Object src, Object data) {
			if (dlgproperty!=null && (dlgproperty.getWinProducer().isactive() &&
					(src == dlgproperty.btnOk || src == dlgproperty.btnCancel))) {
				dlgproperty.hide();
				if (src == dlgproperty.btnOk) {
					File f=new File(dlgproperty.getPath());
					if (dlgproperty.getReadOnly())
						f.setReadOnly();
					if (dlgproperty.getHidden())
						;//f.isHidden()
					Explorer.dirtre.tree.RefreshSel(true);
					l.update(src, data);
				}
				dlgproperty=null;
			}
		}
	}
	
	class ActionTo implements Action, ActionListener {
		private ActionListListener l;
		private int id;
		private DialogDir dlgdir;

		public ActionTo(ActionListListener l, int id) {
			this.l = l;
			this.id = id;
		}

		public void execute(Object src, Object data) {
			dlgdir=new DialogDir(Global.BarCmd_Rid[id],Global.Menu_Edit_Tit[id],Explorer.dirtre.GetTree(),this);
			dlgdir.showModal();
		}

		public void actionPerformed(Object src, Object data) {
			if (dlgdir!=null && (src==dlgdir.btnOk || src==dlgdir.btnCancel))
			{
				dlgdir.hide();
				if (src==dlgdir.btnOk)
					l.update(src, data);
				dlgdir=null;
			}
		}
	}
	
	class ActionSearch implements Action, ActionListener {
		private ActionListListener l;
		private DialogInput dlgsearch;

		public ActionSearch(ActionListListener l) {
			this.l = l;
		}

		public void execute(Object src, Object data) {
			if (dlgsearch!=null)
				dlgsearch.calc();
			dlgsearch=new DialogInput(Global.BarCmd_Rid[Global.Menu_File_Search],Global.Menu_File_Tit[Global.Menu_File_Search],Explorer.dirtre.GetTree(),
					this,Global.Txt_Name,Explorer.dirtre.tree.getSelItmName());
			dlgsearch.showModal();
		}

		public void actionPerformed(Object src, Object data) {
			if (dlgsearch!=null && dlgsearch.getWinProducer().isactive() && 
					(src==dlgsearch.btnOk || src==dlgsearch.btnCancel)) {
				dlgsearch.hide();
				if (src==dlgsearch.btnOk)
					l.update(src, data);
				dlgsearch=null;
			}
		}
	}
	
	class ActionCompare implements Action, ActionListener {
		private ActionListListener l;
		private DialogDir dlgdir;

		public ActionCompare(ActionListListener l) {
			this.l = l;
		}

		public void execute(Object src, Object data) {
			dlgdir=new DialogDir(Global.BarCmd_Rid[Global.Menu_File_Compare],Global.Menu_File_Tit[Global.Menu_File_Compare],Explorer.dirtre.GetTree(),
					this,false);
			dlgdir.showModal();
		}

		public void actionPerformed(Object src, Object data) {
			if (dlgdir!=null && (src==dlgdir.btnOk || src==dlgdir.btnCancel))
			{
				dlgdir.hide();
				if (src==dlgdir.btnOk)
					l.update(src, data);
				dlgdir=null;
			}
		}
	}
}
