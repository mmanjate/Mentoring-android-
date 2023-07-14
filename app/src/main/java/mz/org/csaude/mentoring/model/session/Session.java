package mz.org.csaude.mentoring.model.session;

import java.time.LocalDate;
import java.time.LocalDateTime;

import mz.org.csaude.mentoring.base.model.BaseModel;

public class Session extends BaseModel {

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private LocalDate performedDate;

    private SessionStatus status;

    private String reason;

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public LocalDate getPerformedDate() {
        return performedDate;
    }

    public SessionStatus getStatus() {
        return status;
    }

    public String getReason() {
        return reason;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public void setPerformedDate(LocalDate performedDate) {
        this.performedDate = performedDate;
    }

    public void setStatus(SessionStatus status) {
        this.status = status;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
