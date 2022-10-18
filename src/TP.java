package src;

public class TP extends Thread {

    private int numeroMarco;
	private boolean valid;

	public TP() {
		// initially we do not have a valid mapping
		valid = false;
		numeroMarco = -1;
	}

	public boolean getValidBit() {
		return valid;
	}

	public int getFrameNumber() {
		return numeroMarco;
	}

	public synchronized void setMapping(int frameNumber) {
		this.numeroMarco = frameNumber;

		valid = true;
	}
}
