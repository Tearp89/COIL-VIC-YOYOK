import java.sql.Date;

public class Collaboration {
    String collaborationId;
    String description;
    Date finishDate;
    Date startDate;
    String collaborationName;

    public Collaboration() {
        this.collaborationId = collaborationId;
        this.description = description;
        this.finishDate = finishDate;
        this.startDate = startDate;
        this.collaborationName = collaborationName;
    }

    public String getCollaborationId() {
        return collaborationId;
    }

    public void setCollaborationId(String collaborationId) {
        this.collaborationId = collaborationId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public String getCollaborationName() {
        return collaborationName;
    }

    public void setCollaborationName(String collaborationName) {
        this.collaborationName = collaborationName;
    }

}
