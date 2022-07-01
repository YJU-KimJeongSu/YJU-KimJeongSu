
abstract class TerranBuilding extends StarObject implements IStarBuilding  {
	TerranBuilding(StarUI ui) {
		super(ui);
	}
	public void makeUnit() {
	}
	public void flightBuilding(){
		
	}
	public void progressing(int time){
		while(progress<100){
			try {
                Thread.sleep(time);
            } catch (InterruptedException ignore) {}
            progress+=1;
		}
	}
}
