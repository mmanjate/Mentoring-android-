package mz.org.csaude.mentoring.listner.dialog;

public interface IDialogListener {

    void doOnConfirmed();

    void doOnDeny();

    default void doOnConfirmed(String value) {};
}
