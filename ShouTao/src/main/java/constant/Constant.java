package constant;

public interface Constant {
	/**
	 * 用户未激活
	 */
	int USER_IS_NOT_ACTIVE = 0;
	
	
	/**
	 * 用户已激活
	 */
	int USER_IS_ACTIVE = 1;
	
	
	/**
	 * 记住用户名
	 */
	String SAVE_NAME = "ok";

	//自动登录
	String AUTO_LOGIN = "yes";

	// redis 的配置
	String REDIS_HOST = "192.168.17.136";
	int REDIS_PORT = 6379;
	String STORE_CATEGORY_LIST= "STORE_CATEGORY_LIST";

	//热门
	int PRODUCT_IS_HOT = 1;

	//上架
	int PRODUCT_FLAG_UP = 0;
	//下架
	int PRODUCT_FLAG_DOWN = 1;

	//每页展示的条数
	int PRODUCT_PAGE_SIZE = 12;

	/**未付款*/
	int ORDER_UN_PAY = 0;
	/**未发货*/
	int ORDER_UN_DELIVERY = 1;

	String MAIL_TRANSPORT_PROTOCOL = "smtp";
	String MAIL_SMTP_HOST = "smtp.163.com";
	String MAIL_SMTP_AUTH = "true";
	String MAIL_SENDER = "17767160232@163.com";
	String MAIL_SENDER_PASSWORD = "Ff13073886467";

}
