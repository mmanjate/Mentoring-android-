package mz.org.csaude.mentoring.model.user;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import mz.org.csaude.mentoring.base.model.BaseModel;
import mz.org.csaude.mentoring.dao.user.UserDaoImpl;

@Data
@NoArgsConstructor
@DatabaseTable(tableName = "user", daoClass = UserDaoImpl.class)
@EqualsAndHashCode(callSuper=false)
public class User extends BaseModel {

    public static final String COLUMN_USER_NAME = "user_name";
    public static final String COLUMN_PASSWORD = "password";

    @DatabaseField(columnName = COLUMN_USER_NAME)
    private String userName;

    @DatabaseField(columnName = COLUMN_PASSWORD)
    private String password;

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }
}
