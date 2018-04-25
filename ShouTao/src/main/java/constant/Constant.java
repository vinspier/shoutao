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
	/**所有状态的用户*/
	int USER_ALL = 2;
	
	
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

	//上架商品
	int PRODUCT_FLAG_UP = 0;
	//下架商品
	int PRODUCT_FLAG_DOWN = 1;
	/**数据库里所有商品*/
	int PRODUCT_FLAG_ALL = 2;

	//每页展示的条数
	int PRODUCT_PAGE_SIZE = 18;

	/**未付款*/
	int ORDER_UN_PAY = 0;
	/**未发货*/
	int ORDER_UN_DELIVERY = 1;

	/**	配置第三方邮件服务器*/
	String MAIL_TRANSPORT_PROTOCOL = "smtp";
	String MAIL_SMTP_HOST = "smtp.163.com";
	String MAIL_SMTP_AUTH = "true";
	String MAIL_SENDER = "17767160232@163.com";
	String MAIL_SENDER_PASSWORD = "Ff13073886467";

	/**定义角色名称*/
	String ROLE_CHARACTER_ADMINISTRATOR = "3";//最高管理员
	String ROLE_CHARACTER_ADMIN = "1";//普通管理员
	String ROLE_CHARACTER_USER = "2";//用户

	/**定义用户模拟的初始账户资金*/
	Double USER_BALANCE = 500000.0;

	/**定义用户名是否可用*/
	int NAME_AVALIABLE = 1;
	int NAME_INAVALIABLE = 0;
}
