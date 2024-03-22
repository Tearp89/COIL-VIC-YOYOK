package COIL_VIC_LOGIC.Classes;

import java.sql.Date;
import java.time.LocalDate;

public class Workshop {
    int workshopId;
    String workshopName;
    LocalDate startDate;
    LocalDate finishDate;
    String requirements;
    public Workshop() {
       
    }
    
    public int getWorkshopId() {
        return workshopId;
    }
    public void setWorkshopId(int workshopId) {
        this.workshopId = workshopId;
    }
    public String getWorkshopName() {
        return workshopName;
    }
    public void setWorkshopName(String workshopName) {
        this.workshopName = workshopName;
    }
    public LocalDate getStartDate() {
        return startDate;
    }
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }
    public LocalDate getFinishDate() {
        return finishDate;
    }
    public void setFinishDate(LocalDate finishDate) {
        this.finishDate = finishDate;
    }
    public String getRequirements() {
        return requirements;
    }
    public void setRequirements(String requirements) {
        this.requirements = requirements;
    }
}
