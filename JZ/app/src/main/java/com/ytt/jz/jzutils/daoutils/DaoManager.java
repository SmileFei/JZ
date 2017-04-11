package com.ytt.jz.jzutils.daoutils;

import com.ytt.jz.jzbase.BaseApplication;
import com.ytt.jz.jzdao.DaoMaster;
import com.ytt.jz.jzdao.DaoSession;
import com.ytt.jz.jzresource.ConstantResource;

/**
 * 本地数据库管理者
 */

public class DaoManager {
    public static DaoMaster.DevOpenHelper helper;
    public static DaoMaster master;
    public static DaoSession session;
    public static synchronized DaoSession getDaoManager(){
        if (null == helper){
            helper = new DaoMaster.DevOpenHelper(BaseApplication.context, ConstantResource.LOCAL_SQLITE_NAME,null);
        }
        if (null == master){
            master = new DaoMaster(helper.getWritableDatabase());
        }
        if (null == session){
            session = master.newSession();
        }
        return session;
    }
}
