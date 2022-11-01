package net.kaleoweb.newappcpi.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;

import net.kaleoweb.newappcpi.dao.DaoModule;
import net.kaleoweb.newappcpi.databases.UserDatabase;
import net.kaleoweb.newappcpi.utilities.User;

import java.util.List;

public class UserRepository {
    private final DaoModule daoModule;
    private final User mInfos;

    public UserRepository(Application application) {
       UserDatabase userDatabase = UserDatabase.get(application);
        daoModule = userDatabase.daoModule();
        mInfos = daoModule.getById(1);


    }
    public String getList() {
        return mInfos.nom;
    }
}
