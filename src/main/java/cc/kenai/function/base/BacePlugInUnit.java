package cc.kenai.function.base;

public abstract class BacePlugInUnit implements BaceInterfacePlugInUnit {
    /**
     * 用来确保不被重复执行
     */
    public boolean state = false;

    public abstract boolean myStart();

    public abstract boolean myStop();

    public final void xstart() {
        if (!state) {
            if (myStart())
                state = true;
        }
    }

    public final void xstop() {
        if (state) {
            if (myStop())
                state = false;
        }
        onStop();
    }
}
