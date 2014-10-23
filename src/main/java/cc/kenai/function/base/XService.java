package cc.kenai.function.base;


import android.app.Service;
import android.content.Context;
import android.content.Intent;

public abstract class XService {
    public Context context;
    public Service service;

    public XService(Context context, Service service) {
        this.context = context;
        this.service = service;
    }
    public XService (Context context){
    	this.context=context;
	}

    public abstract void xCreate();

    public abstract void xDestroy();

    public abstract void xstart(Intent intent);
}
