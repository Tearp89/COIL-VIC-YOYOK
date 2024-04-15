package logic.classes;


public class Facilitator {
    private int facilitatorId;
    private String facilitatorName;
    private String workShopId;

    public Facilitator() {
       
    }

    public int getFacilitatorId() {
        return facilitatorId;
    }

    public void setFacilitatorId(int facilitatorId) {
        this.facilitatorId = facilitatorId;
    }

    public String getFacilitatorName() {
        return facilitatorName;
    }

    public void setFacilitatorName(String facilitatorName) {
        this.facilitatorName = facilitatorName;
    }

    public String getWorkShopId() {
        return workShopId;
    }

    public void setWorkShopId(String workShopId) {
        this.workShopId = workShopId;
    }

}