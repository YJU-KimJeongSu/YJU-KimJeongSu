import java.util.ArrayList;
import javax.swing.ImageIcon;

public class Tribe {
	StarUI ui;
	ImageIcon mainBIC, workerIC, workerStateIC;
	ImageIcon buildingface, workerface;
	
	protected int mineral; //미네랄
	protected int gas; //가스
	protected int unit; //총 유닛 갯수
	protected int maxunit=10; //최대 만들수 있는 유닛 갯수
	protected int building; //총 건물 갯수
	protected int upgradepower; //업그레이드 파워		
	protected String tribe; //종족이름	
	protected String mainbuildingName; //본건물 이름
	protected String workerName; //일꾼이름
	
	//유닛 및 건물 설명 부분//
	protected String mainBdesc;
	protected String workerdesc;
	protected int workerPower;//일꾼 공격력
	protected int workerEnergy;//일꾼 에너지 정보
	protected int mainBEnergy;//메인건물 에너지 정보

	ArrayList mainBVT; //메인건물 객체 담는곳
	static ArrayList workerVT; // 일꾼객체
	
	/////////각 건물 및 유닛 아이템(콤보박스)정보 담는 벡터//////
	ArrayList mainBItem;
	ArrayList workerItem;

	Tribe(StarUI ui) {
		this.ui = ui;
		mineral = 100;
		gas = 100;
	}
	
	public int getMainBEnergy(){
		return mainBEnergy;
	}
	public int getWorkerEnergy(){
		return workerEnergy;
	}
	public void setUpgrade(int i){
		upgradepower += i;
	}
	public int getUpgrade(){
		return upgradepower;
	}
	public ImageIcon getWorkerIC(){
		return workerIC;
	}
	public ImageIcon getWorkerStateIC(){
		return workerStateIC;
	}
	
	// synchronized는 멀티스레드 환경에서 동기화 시키는 키워드
	// (현재 데이터를 사용하고 있는 해당 스레드를 제외하고 나머지 스레드들은 데이터에 접근 할 수 없도록 막는 개념)
	synchronized void setMineral(int i){
		mineral+=i;
	}
	public int getMineral(){
		return mineral;
	}
	synchronized void setGas(int i){
		gas+=i;
	}
	public int getGas(){
		return gas;
	}	
	synchronized void setUnit(int a){
		unit=a;
	}
	public int getUnit(){
		return unit;
	}
	synchronized void setMaxunit(int i){
		maxunit +=i;
	}
	public int getMaxunit(){
		return maxunit;
	}
	synchronized void setBuilding(int a){
		building = a;
	}
	public int getBuilding(){
		return building;
	}	
	public String getTribeName(){
		return tribe;
	}
	public String getMainBName(){
		return mainbuildingName;
	}
	public String getWorkerName(){
		return workerName;
	}
	public int getWorkerpower(){
		return workerPower;
	}
}
