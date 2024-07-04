package mz.org.csaude.mentoring.model.ronda;

import java.util.List;
import java.util.Map;

import mz.org.csaude.mentoring.base.model.BaseModel;
import mz.org.csaude.mentoring.model.location.HealthFacility;
import mz.org.csaude.mentoring.model.session.SessionSummary;

public class RondaSummary extends BaseModel {
    private long nuit;
    private String mentee;
    private String mentor;
    private double zeroEvaluation;
    private double session1;
    private double session2;
    private double session3;
    private double session4;
    private double finalScore;

    private Ronda ronda;

    Map<Integer, List<SessionSummary>> summaryDetails;


    public long getNuit() {
        return nuit;
    }

    public void setNuit(long nuit) {
        this.nuit = nuit;
    }

    public String getMentee() {
        return mentee;
    }

    public void setMentee(String mentee) {
        this.mentee = mentee;
    }

    public String getMentor() {
        return mentor;
    }

    public void setMentor(String mentor) {
        this.mentor = mentor;
    }

    public double getZeroEvaluation() {
        return zeroEvaluation;
    }

    public void setZeroEvaluation(double zeroEvaluation) {
        this.zeroEvaluation = zeroEvaluation;
    }

    public double getSession1() {
        return session1;
    }

    public void setSession1(double session1) {
        this.session1 = session1;
    }

    public double getSession2() {
        return session2;
    }

    public void setSession2(double session2) {
        this.session2 = session2;
    }

    public double getSession3() {
        return session3;
    }

    public void setSession3(double session3) {
        this.session3 = session3;
    }

    public double getSession4() {
        return session4;
    }

    public void setSession4(double session4) {
        this.session4 = session4;
    }

    public double getFinalScore() {
        return finalScore;
    }

    public void setFinalScore(double finalScore) {
        this.finalScore = finalScore;
    }

    public Map<Integer, List<SessionSummary>> getSummaryDetails() {
        return summaryDetails;
    }

    public void setSummaryDetails(Map<Integer, List<SessionSummary>> summaryDetails) {
        this.summaryDetails = summaryDetails;
    }

    public Ronda getRonda() {
        return ronda;
    }

    public void setRonda(Ronda ronda) {
        this.ronda = ronda;
    }
}
