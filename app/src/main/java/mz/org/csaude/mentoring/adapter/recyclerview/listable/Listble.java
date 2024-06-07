package mz.org.csaude.mentoring.adapter.recyclerview.listable;

public interface Listble {


    Integer getId();

    default int getListPosition() {
        return 0;
    }

    String getDescription();

    void setListPosition(int listPosition);
    void setItemSelected(boolean selected);

    boolean isSelected();

    int getDrawable();

    String getCode();

    String getListType();

    default void setListType(ListTypes type) {

    }

    String getExtraInfo();

    public enum ListTypes {
        SELECTION_LIST,
        NORMAL,
        UNDEFINED
    }
}
