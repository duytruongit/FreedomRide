package org.thyee.freedomride.client.utils;

public class Data {

	public final static String WALLECT_SETTING = "wallect_setting";
	public final static String SOME_SETTING = "some_setting";
	public final static String COLLECTION_SETTING = "collect_setting";
	public final static String LAST_REFRESH_TIME = "last_refresh_time";
	public final static String CURRENT_CACHE_NUM = "current_cache_num";
	public final static String COLLECTION_KEY = "my_collection";

	public final static String MAP_KEY = "";

	public final static String HOST = "http://10.10.107.208:8080/FreedomRideServer";
	// public final static String GETSTRATEGY = HOST + "/strategy/getstrategy";
	public final static String GETSTRATEGY = HOST + "/strategy/searchstrategy";
	public final static String GETSQUAE = HOST + "/strategy/list";
	public final static String GETATTRACTIONS = HOST + "/attractions/list";
	public final static String SYNC_STRATEGY = HOST + "/strategy/sync";
	public final static String SHARE_STRATEGY = HOST + "/strategy/share";
	public final static String LONGIN = HOST + "/account/login";
	public final static String REGIST = HOST + "/account/res";

	public final static int REFRESH_SUCCESS = 0;
	public final static int REFRESH_FAIL = 1;
	public final static int CACHE_SUCCESS = 2;
	public final static int CACHE_FAIL = 3;
	public final static int NETWORK_DISABLE = 4;

	public final static String RESULT_DATA = "data";

}
