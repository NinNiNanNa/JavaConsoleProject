package banking6;

// 쓰레드로 정의할 AutoSaver 클래스 (Thread를 상속받음)
public class AutoSaver extends Thread {

	// AccountManager타입의 멤버변수 생성
	AccountManager accMgr;
	
	// 생성자를 통해 AccountManager 타입에 변수를 초기화
	public AutoSaver(AccountManager accMgr) {
		this.accMgr = accMgr;
	}
	
	/*
	run() 메서드는 쓰레드의 main() 메서드에 해당한다.
	Thread 클래스의 run() 메서드는 오버라이딩 한것으로 직접 호출하면 안되고,
	start()를 통해 간접적으로 호출해야한다.
	만약 직접 호출하면 단순히 실행만되고 쓰레드가 생성되지 않는다.
	*/
	@Override
	public void run() {
		try {
			while(true) {
				// 5초마다 txt로 자동저장
				accMgr.autoSaveTxt();
				// 실행 중인 스레드를 5초만큼 일시정지 했다가 다시 실행 상태로 돌아간다.
				// InterruptedException 처리를 강제하기 때문에 try 구문 안에 작성
				sleep(5000);
			}
		}
		catch(InterruptedException e) {
			
		}
	}
	
	
	
}
