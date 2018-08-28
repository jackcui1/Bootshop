package com.bootshop.common;

/**
 * @author Guowei Cui
 * @date 8/13/2018 11:02 AM
 */
public class CommonConstants {

    //for article
    public static final int ARTICLE_TYPE_ARTICLE = 0;
    public static final int ARTICLE_TYPE_CAROUSEL = 1;
    public static final int ARTICLE_TYPE_DELIVERY = 2;
    public static final int ARTICLE_TYPE_CONTACT = 3;

    //Cookie name
    public static final String BOOTSHOP_COOKIE_NAME = "BootShopCookie";
    public static final int BOOTSHOP_COOKIE_MAXAGE = 24 * 60 * 60;


    //Redis key for loged in user
    public static final String KEY_LOGIN_USER = "loginMap";
    //Redis key for
    public static final String KEY_RECENT_USER = "recentSet";

    //Redis key for customer view history;
    public static final String KEY_USER_VIEW_PREFIX = "viewZset:";



}
