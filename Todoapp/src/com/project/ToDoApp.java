package com.project;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class ToDoApp extends JFrame {
	 private DefaultListModel<String> taskListModel;
	    private JList<String> taskList;
	    private JTextField taskInput;

	    public ToDoApp() {
	        setTitle("To-Do App");
	        setSize(400, 400);
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        setLocationRelativeTo(null); // Center window

	        // Task list model and JList
	        taskListModel = new DefaultListModel<>();
	        taskList = new JList<>(taskListModel);
	        JScrollPane scrollPane = new JScrollPane(taskList);
	        taskInput = new JTextField();
	        taskInput.setFont(new Font("Arial", Font.PLAIN, 14));

	        // Buttons
	        JButton addButton = new JButton("Add Task");
	        JButton removeButton = new JButton("Remove Selected");

	        // Add task action
	        addButton.addActionListener(e -> {
	            String task = taskInput.getText().trim();
	            if (!task.isEmpty()) {
	                taskListModel.addElement(task);
	                taskInput.setText("");
	            } else {
	                JOptionPane.showMessageDialog(this, "Please enter a task!");
	            }
	        });

	        // Remove task action
	        removeButton.addActionListener(e -> {
	            int selectedIndex = taskList.getSelectedIndex();
	            if (selectedIndex != -1) {
	                taskListModel.remove(selectedIndex);
	            } else {
	                JOptionPane.showMessageDialog(this, "Select a task to remove!");
	            }
	        });

	        // Layout
	        JPanel inputPanel = new JPanel(new BorderLayout());
	        inputPanel.add(taskInput, BorderLayout.CENTER);
	        inputPanel.add(addButton, BorderLayout.EAST);

	        JPanel buttonPanel = new JPanel();
	        buttonPanel.add(removeButton);

	        setLayout(new BorderLayout());
	        add(inputPanel, BorderLayout.NORTH);
	        add(scrollPane, BorderLayout.CENTER);
	        add(buttonPanel, BorderLayout.SOUTH);
	    }

	        
	    

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		SwingUtilities.invokeLater(() -> {
            new ToDoApp().setVisible(true);
        });

	}

}
