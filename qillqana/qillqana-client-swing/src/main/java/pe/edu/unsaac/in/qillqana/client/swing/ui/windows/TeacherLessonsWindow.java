package pe.edu.unsaac.in.qillqana.client.swing.ui.windows;

import java.awt.BorderLayout;
import java.awt.Frame;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

import javax.swing.AbstractListModel;

import pe.edu.unsaac.in.qillqana.client.swing.locale.Messages;
import pe.edu.unsaac.in.qillqana.common.command.Command;
import pe.edu.unsaac.in.qillqana.common.command.QueryCommand;
import pe.edu.unsaac.in.qillqana.common.mediator.Mediator;
import pe.edu.unsaac.in.qillqana.common.mediator.Session;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TeacherLessonsWindow extends DlgBase implements Session {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel pnlActions;
	private JButton btnNew;
	private JButton btnEdit;
	private JButton btnRem;
	private JPanel panel;
	private JList<String> lstTopics;
	private Mediator mediator;

	public TeacherLessonsWindow(Frame owner, boolean modal, Mediator mediator) {
		super(owner, modal);
		this.mediator = mediator;
		initGUI();
		requestList();
	}

	@SuppressWarnings({ "unchecked", "rawtypes", "serial" })
	private void initGUI() {
		setOwnTitle(Messages.getString("lesson.title"));
		pnlActions = new JPanel();
		getContentPane().add(pnlActions, BorderLayout.EAST);
		pnlActions.setLayout(new MigLayout(Messages
				.getString("LessonsWindow.0"), "[]", "[][][][]")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

		btnNew = new JButton(""); //$NON-NLS-1$
		btnNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_btnNew_actionPerformed(e);
			}
		});
		btnNew.setIcon(new ImageIcon("images/_16_add.png")); //$NON-NLS-1$
		pnlActions.add(btnNew, "cell 0 0"); //$NON-NLS-1$

		btnEdit = new JButton(""); //$NON-NLS-1$
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_btnEdit_actionPerformed(e);
			}
		});
		btnEdit.setIcon(new ImageIcon("images/_16_edit.png")); //$NON-NLS-1$
		pnlActions.add(btnEdit, "cell 0 1"); //$NON-NLS-1$

		btnRem = new JButton(""); //$NON-NLS-1$
		btnRem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_btnRem_actionPerformed(e);
			}
		});
		btnRem.setIcon(new ImageIcon("images/_16_delete.png")); //$NON-NLS-1$
		pnlActions.add(btnRem, "cell 0 2"); //$NON-NLS-1$

		panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));

		lstTopics = new JList<>();
		lstTopics.setModel(new AbstractListModel() {
			String[] values = new String[] {
					"Poligonos", "Perimetros y areas de figuras poligonales", "Angulos Internos y externos de Poligonos", "Geometria del espacio" }; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$

			public int getSize() {
				return values.length;
			}

			public Object getElementAt(int index) {
				return values[index];
			}
		});
		panel.add(lstTopics, BorderLayout.CENTER);

		pack();
		setLocationRelativeTo(getParent());
	}

	protected void do_btnNew_actionPerformed(ActionEvent e) {
		TopicWindow topicWindow = new TopicWindow((Frame) getOwner(), true);
		topicWindow.setVisible(true);
	}

	protected void do_btnEdit_actionPerformed(ActionEvent e) {
		if (lstTopics.getSelectedIndex() >= 0) {
			TopicWindow topicWindow = new TopicWindow((Frame) getOwner(), true);
			topicWindow.setVisible(true);
		} else {
			JOptionPane.showMessageDialog(this,
					Messages.getString("lesson.question.topic_request"),
					Messages.getString("label.error"),
					JOptionPane.ERROR_MESSAGE);
		}
	}

	protected void do_btnRem_actionPerformed(ActionEvent e) {
		if (lstTopics.getSelectedIndex() >= 0) {
			JOptionPane.showConfirmDialog(this,
					Messages.getString("lesson.question.delete"),
					Messages.getString("label.delete"),
					JOptionPane.YES_NO_OPTION);
		} else {
			JOptionPane.showMessageDialog(this,
					Messages.getString("lesson.question.topic_request"),
					Messages.getString("label.error"),
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private void requestList() {
		QueryCommand query = new QueryCommand();
		query.setQueryName(QueryCommand.LIST_TEACHER_LESSON);
		query.setUserId("");
		sendCommand(query);
	}

	// Session Interface Method
	// Mediator -> Class
	@Override
	public void receiveCommand(Command command) {
		if(command.getName().equals(QueryCommand.LIST_TEACHER_LESSON+"_response")){
			
		}
	}

	// Command -> Mediator
	@Override
	public void sendCommand(Command command) {
		mediator.sendMessage(command, this);
	}

	@Override
	public boolean processCommad(Command command) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getIdSession() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

	}

	@Override
	public void start() {
		// TODO Auto-generated method stub

	}
}
