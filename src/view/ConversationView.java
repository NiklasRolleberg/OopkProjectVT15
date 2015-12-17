package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

import model.Conversation;


public class ConversationView implements ActionListener {
	
	JButton myButtonsend;
	JButton myButtonadd;
	JButton myButtonremove;
	
	JFrame myFrame;
	
	JPanel myPanel;
	JPanel myContainer;
	JPanel myContainerchat;
	JPanel myContainerbutton;
	
	JEditorPane myTextchat;
	JTextField myTextwrite;
	JScrollPane scrollPane;
	Conversation messageConversation;
	
	/**
	 * The chat window for the conversation 
	 * It has a textfield, textchat window, scrollbar, a send button, an  add button and remove button
	 */
	
	public ConversationView() {
		myTextchat = new JEditorPane();
		myTextchat.setContentType("text/html");
		myTextwrite = new JTextField();
		myTextchat.setEditable(false);
		
		myPanel = new JPanel();
		myPanel.setLayout(new BorderLayout());
		
		JScrollPane scrollPane = new JScrollPane(myTextchat);
		
		myContainer = new JPanel();
		myContainer.setLayout(new BorderLayout());
		myContainerchat = new JPanel();
		myContainerchat.setLayout(new BorderLayout());
		myContainerbutton = new JPanel();
		myContainerbutton.setLayout(new BorderLayout());
		
		
		myButtonsend = new JButton("Send");
		myButtonremove = new JButton("Remove");
		myButtonadd = new JButton("Add");
		
		myButtonadd.addActionListener(this);
		myButtonremove.addActionListener(this);
		myButtonsend.addActionListener(this);
		
		myFrame = new JFrame();
		myFrame.setPreferredSize(new Dimension(400,300));
		myFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		myFrame.setVisible(true);
		myFrame.pack();
		
		
		
		
		
		myFrame.add(myPanel);
		myPanel.add(myContainer);
		myContainer.add(myContainerchat);
		myContainerchat.add(myContainerbutton);
		
		
		myContainer.add(scrollPane,BorderLayout.CENTER);
		myContainer.add(myContainerchat,BorderLayout.SOUTH);
		myContainerchat.add(myTextwrite,BorderLayout.CENTER);
		myContainerchat.add(myButtonsend,BorderLayout.EAST);
		myContainerchat.add(myContainerbutton,BorderLayout.WEST);
		myContainerbutton.add(myButtonadd,BorderLayout.NORTH);
		myContainerbutton.add(myButtonremove,BorderLayout.SOUTH);
		
		

		/**
		 * print out what the other person write
		 */
	}

	public void updateDisplay(String hist){
		myTextchat.setText(hist+"</body></html>");
		myTextchat.revalidate();
		myTextchat.repaint();
		System.out.println(hist);
	}
	
	/**
	 * keep track on right conversation
	 */
	
	public void setConversation(Conversation b){
		messageConversation=b;
	}

	/**
	 * sends the writen text to the chat window.
	 */

	@Override
	public void actionPerformed(ActionEvent e) {
		
	if (e.getActionCommand() == "Send")	{
		String h;
		h=myTextwrite.getText();
		messageConversation.sendMessage(h);
		myTextwrite.setText("");
	}
		
	/**
	 * open the add window
	 */
		
		
	if (e.getActionCommand() == "Add"){
		
		AddFriend f1 = new AddFriend(messageConversation);
		

	}
	
	/**
	 * open the remove window
	 */
	if (e.getActionCommand() == "Remove"){
		
		RemoveFriend f = new RemoveFriend(messageConversation);

	}
	/**
	 * hide the window on close
	 */

	}

	public void setVisible(boolean b){
		this.myFrame.setVisible(b);
	}


	
}
