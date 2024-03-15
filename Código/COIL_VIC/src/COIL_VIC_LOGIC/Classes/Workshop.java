package COIL_VIC_LOGIC.Classes;

import java.sql.Date;

public class Workshop {
    String workshopId;
    String workshopName;
    Date startDate;
    Date finishDate;
    String requirements;
    public Workshop() {
       
    }
    
    public String getWorkshopId() {
        return workshopId;
    }
    public void setWorkshopId(String workshopId) {
        this.workshopId = workshopId;
    }
    public String getWorkshopName() {
        return workshopName;
    }
    public void setWorkshopName(String workshopName) {
        this.workshopName = workshopName;
    }
    public Date getStartDate() {
        return startDate;
    }
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    public Date getFinishDate() {
        return finishDate;
    }
    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }
    public String getRequirements() {
        return requirements;
    }
    public void setRequirements(String requirements) {
        this.requirements = requirements;
    }
}
