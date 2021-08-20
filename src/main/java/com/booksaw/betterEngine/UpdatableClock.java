package com.booksaw.betterEngine;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import com.booksaw.betterEngine.logging.EngineLogger;

@SuppressWarnings("serial")
public class UpdatableClock extends Timer implements ActionListener {

	/**
	 * the number of updates behind the clock has to be to skip updates
	 */
	private static final int TIMEOUT = 50;

	private final Updatable update;

	private final int delay;

	private transient long lastUpdate;

	/**
	 * Defaults to 100 ups (delay of 10ms between updates)
	 * 
	 * @param update The updatable to update
	 */
	public UpdatableClock(Updatable update) {
		this(update, 10);
	}

	/**
	 * 
	 * @param update the updatable to update
	 * @param delay  The delay between updates
	 */
	public UpdatableClock(Updatable update, int delay) {
		super(delay, null);
		this.update = update;
		this.delay = delay;

		addActionListener(this);
	}

	@Override
	public void start() {
		super.start();
		lastUpdate = System.currentTimeMillis();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// calculating how many updates need to be run
		long thisUpdate = System.currentTimeMillis();
		int diff = (int) (thisUpdate - lastUpdate);

		diff /= delay;

		if (diff > TIMEOUT) {
			EngineLogger.getLogger().info("UpdatableClock",
					"Timer is running " + diff + " behind, skipping to present");
			diff = 1;
		} else if (diff == 0) {
			// timer should not have been called yet
			return;
		}

		update.update(diff);

		lastUpdate = thisUpdate;
	}

}
