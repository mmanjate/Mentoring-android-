package mz.org.csaude.mentoring.base.databasehelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

import mz.org.csaude.mentoring.R;
import mz.org.csaude.mentoring.dao.tutored.TutoredDao;
import mz.org.csaude.mentoring.model.tutored.Tutored;

public class MentoringDataBaseHelper extends OrmLiteSqliteOpenHelper {

    private static final String DATABASE_NAME    = "mentoring.db";
    private static final int    DATABASE_VERSION = 1;

    private final Context context;

    private TutoredDao tutoredDao;


    private static MentoringDataBaseHelper dataBaseHelper;

    private MentoringDataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION, R.raw.ormlite_config);
        this.context = context;
    }

    public static MentoringDataBaseHelper getInstance(Context context) {
        if (dataBaseHelper == null){
            dataBaseHelper = new MentoringDataBaseHelper(context);
        }
        return dataBaseHelper;
    }

    public TutoredDao getTutoredDao() throws SQLException {
        if(tutoredDao == null){
            tutoredDao = getDao(Tutored.class);
        }
        return tutoredDao;
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {

        try {
            TableUtils.createTableIfNotExists(connectionSource, Tutored.class);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {

    }
}
