package com.booksaw.betterEngine.game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import com.booksaw.betterEngine.game.node.Node;

public class Scene implements ActionListener {

	private final Node rootNode;
	private Timer timer;

	public Scene(Node rootNode) {
		this.rootNode = rootNode;
		timer = new Timer(10, this);
	}

}
