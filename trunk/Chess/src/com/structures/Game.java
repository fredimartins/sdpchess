package com.structures;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;


public class Game extends JPanel{
	boolean butActive=false;
	JButton tempJButton=new JButton();
	private static final long serialVersionUID = 1L;
	class move implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if(!butActive)
			{
				
				butActive=true;
				tempJButton=((JButton)e.getSource());
				System.out.println(tempJButton.getName());
			}
			else
			{
				JButton aux=((JButton)e.getSource());
				aux.setIcon(tempJButton.getIcon());
				tempJButton.setIcon(null);
				butActive=false;
			}
				
		}
	}
	public Game()
	{	
	Board gBoard=new Board();
	this.setSize(500, 500);
	this.setVisible(true);
	GridLayout gridLayout = new GridLayout();
	gridLayout.setRows(8);
	gridLayout.setColumns(8);
	this.setLayout(gridLayout);
	JButton[][] buttons=new JButton[8][8];

	for(int i=0;i<buttons.length;i++)
		for(int j=0;j<buttons.length;j++)
		{
			buttons[i][j]=new JButton();
			buttons[i][j].setIcon(gBoard.mat[i][j].icon());
			buttons[i][j].addActionListener(this.new move());
			buttons[i][j].setName(i+" "+j);
			if(i%2==0)
				if(j%2==0)
					buttons[i][j].setBackground(Color.GRAY);
				else
					buttons[i][j].setBackground(Color.WHITE);
			else
				if(j%2==0)
					buttons[i][j].setBackground(Color.WHITE);
				else
					buttons[i][j].setBackground(Color.GRAY);
			this.add(buttons[i][j]);
		}
	}
}
