import javax.swing.ImageIcon;

public class ResourcesChecker implements Runnable {
	ImageIcon work1 = new ImageIcon("img/work1.png");
	ImageIcon work2 = new ImageIcon("img/work2.png");
	ImageIcon work3 = new ImageIcon("img/work3.png");
	ImageIcon workerStateIC = new ImageIcon("img/terranworkerState.jpg");
	
	public void run() {
		while (true) {
			resourcesCounter(); // 100ms ���� ����!
			iconChainger();
		}
	}
	
	// ĵ �ڿ���
	private void resourcesCounter() {
//		try {
//			Thread.sleep(100); //�� ������. 0.1�� ���� ����
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
	
	// ���� �ϰ��ִ� �۾�
	// ������ ���ο����� ���� �־��µ� StarUI �ȿ� setTB�ȿ� �ִ°� ����ȭ �� ����
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
