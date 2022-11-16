package com.giftedcat.beammvp.bijection;


public abstract class PresenterManager {

    private static PresenterManager instance = new DefaultPresenterManager();

    public static PresenterManager getInstance() {
        return instance;
    }

    public abstract <T extends BeamBasePresenter> T create(Object view);

    public abstract <T extends BeamBasePresenter> T get(String id);

    public abstract void destroy(String id);

}
