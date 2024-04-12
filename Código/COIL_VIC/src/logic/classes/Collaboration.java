package logic.classes;

import java.sql.Date;
import java.time.LocalDate;

public class Collaboration {
    int collaborationId;
    String description;
    LocalDate finishDate;
    LocalDate startDate;
    String collaborationName;

    public Collaboration() {
        
    }

    public int getCollaborationId() {
        return collaborationId;
    }

    public void setCollaborationId(int collaborationId) {
        this.collaborationId = collaborationId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(LocalDate finishDate) {
        this.finishDate = finishDate;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public String getCollaborationName() {
        return collaborationName;
    }

    public void setCollaborationName(String collaborationName) {
        this.collaborationName = collaborationName;
    }

}
