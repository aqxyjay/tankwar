import java.awt.Frame;

public class TankClient extends Frame {
	
	private static final long serialVersionUID = 6254741544943336854L;

	public void launchFrame() {
		this.setLocation(400, 300);
		this.setSize(800, 600);
		setVisible(true);
	}

	public static void main(String[] args) {
		TankClient tc = new TankClient();
		tc.launchFrame();
	}

}
