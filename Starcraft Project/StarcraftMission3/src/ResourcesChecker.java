import javax.swing.ImageIcon;

public class ResourcesChecker implements Runnable {
	ImageIcon work1 = new ImageIcon("img/work1.png");
	ImageIcon work2 = new ImageIcon("img/work2.png");
	ImageIcon work3 = new ImageIcon("img/work3.png");
	ImageIcon workerStateIC = new ImageIcon("img/terranworkerState.jpg");
	
	public void run() {
		while (true) {
			resourcesCounter(); // 100ms 지연 있음!
			iconChainger();
		}
	}
	
	// 캔 자원량
	private void resourcesCounter() {
//		try {
//			Thread.sleep(100); //렉 방지용. 0.1초 간격 갱신
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
		for (int i = 0; i < Terran.workerVT.size(); i++) {
			if (((StarObject)Terran.workerVT.get(i)).getJob() == 1 ||
				((StarObject)Terran.workerVT.get(i)).getJob() == 2) {
				StarUI.U_workerState[i].setText(((StarObject) Terran.workerVT.get(i)).getResources());
			}
			else if (((StarObject)Terran.workerVT.get(i)).getJob() == 3) {
				StarUI.U_workerState[i].setText(((StarObject) Terran.workerVT.get(i)).getProgress() + "%");
			}
			else StarUI.U_workerState[i].setText(null);
		}
	}
	
	// 지금 하고있는 작업
	// 스레드 공부용으로 여기 넣었는데 StarUI 안에 setTB안에 넣는게 최적화 더 좋음
	private void iconChainger() {
		for (int i = 0; i < Terran.workerVT.size(); i++) {
			if (((StarObject)Terran.workerVT.get(i)).getJob() == 1) {
				StarUI.U_workerState[i].setIcon(work1);
			}
			else if (((StarObject)Terran.workerVT.get(i)).getJob() == 2) {
				StarUI.U_workerState[i].setIcon(work2);
			}
			else if (((StarObject)Terran.workerVT.get(i)).getJob() == 3) {
				StarUI.U_workerState[i].setIcon(work3);
			}
			else StarUI.U_workerState[i].setIcon(workerStateIC);
		}
	}

}
