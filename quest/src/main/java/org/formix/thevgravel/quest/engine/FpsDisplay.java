package org.formix.thevgravel.quest.engine;

import java.awt.Color;
import java.awt.Graphics;
import java.util.EventObject;

public class FpsDisplay extends AbstractItem {

	private int lastFrameCount;
	private long lastUpdate;
	private String text;

	public boolean update() {
		long now = System.currentTimeMillis();
		double interval = now - this.lastUpdate;
		if (interval > 2000) {
			int frameCount = this.getScene().getFrameCount();
			int frameInterval = frameCount - this.lastFrameCount;
			double fps = frameInterval / (interval / 1000.0);
			this.text = String.format("%.3f fps", fps);
			this.lastUpdate = now;
			this.lastFrameCount = frameCount;
			return true;
		}
		return false;
	}

	public void draw(Graphics g) {
		g.setColor(Color.yellow);
		g.drawString(this.text, 20, 20);
	}

	public void initialize() {
		this.setInitialData();
		this.getScene().addAnimationListener(new AnimationListener() {
			
			public void animationStopped(EventObject e) {
				setInitialData();
			}
			
			public void animationStarted(EventObject e) {
				lastUpdate = System.currentTimeMillis();
			}
		});
	}

	private void setInitialData() {
		this.lastFrameCount = this.getScene().getFrameCount();
		this.setZ(200);
		this.lastUpdate = 0;
		this.text = "Waiting for data...";
		this.lastFrameCount = 0;
		
	}
}
