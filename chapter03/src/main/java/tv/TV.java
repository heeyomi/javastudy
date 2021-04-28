package tv;

public class TV {
	private int channel; //1~255
	private int volume; //0~100
	private boolean power;
	
	public int getChannel() {
		return channel;
	}

	public int getVolume() {
		return volume;
	}

	public boolean isPower() {
		return power;
	}
	
	public TV() {
		
	}

	public TV(int channel, int volume, boolean on) {
		this.channel = channel;
		this.volume = volume;
		this.power = on;
	}
	
	public void power(boolean on) {
		this.power = on;
	}
	
	public void channel(boolean up) {
		if (up) {
			if (channel >= 255) {
				this.channel = 1;
			} else {
				this.channel++;
			}
		} else {
			if (this.channel -1 < 1) {
				this.channel = 255;
			} else {
				this.channel--;
			}
		}
	}
	
	public void channel(int channel) {
		if (channel<= 255 && channel > 0) {
			this.channel = channel;
		}
	}
	
	public void volume(int volume) {
		if (volume > 100) {
			this.volume = 100;
		} else if (volume <0) {
			this.volume = 0;
		} else {
			this.volume = volume;
		}
	}
	
	public void volume(boolean up) {
		if (up) {
			if (this.volume+1 >= 100) {
				this.volume = 100;
			} else {
				this.volume++;
			}
		} else {
			
			if (this.volume -1 <= 0) {
				this.volume= 0;
			} else {
				this.volume--;
			}
		}
	}
	
	public void status() {
		System.out.println("TV[power] : " + power + ", channel : " + channel + ", volume : " + volume);
	}

}
