package prob2;

public class SmartPhone extends MusicPhone {
	
	@Override
	public void execute(String function) {
		if (function.equals("음악")) {
			playMusic();
		} else if (function.equals("통화")) {
			super.execute("통화");
		} else if (function.equals("앱")) {
			playApp();
		}
	}
	
	private void playMusic() {
		System.out.println("다운로드해서 음악재생");
	}
	
	private void playApp() {
		System.out.println("앱 실행");
	}
}
