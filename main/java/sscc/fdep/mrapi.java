package   com.sscc.fdep;


public class mrapi 
{ 
static 
{ 
System.loadLibrary("mrapi"); 
}

public static void main(String[] args) 
{ 
 
}  

/********************************* FDEP V3 **********************************/

/* 初始化，返回为整形
 * return: 0-failed; 1-OK
   App和AppPwd对应c接口中的AppId和AppPwd， 后四个参数对应C接口中的结构体oConnInfo中各个字段
 */
public native static int MrInit(String App,String AppPwd, String Ip,short Port,String Ipbak, short Portbak);



/* 发送消息 ，参数中SourceAppID填初始化的时候用的App
 * return:    ""-发送失败   pkgId-发送成功  
   psPkg中是要发送的数据，其余字段同C接口中pMsgPropery中各个字段 
 */

public native static String MrSend( byte[] psPkg, String SourceUserID,String SourceAppID, String DestUserID, String DestAppID,
String PkgID, String CorrPkgID, String UserData1, String UserData2, String ExpiredAbsTime, byte flag, byte Protocltype,int iMillSecTimeo);




//return "NULL"-接收失败    否则返回pkID(64byte) + CorrpkID(64byte) + sourceUserID(64byte) + sourceAppID(64byte) +destUserID(64byte) + UserData1(256byte) + UserData2(256byte) + data     上述前7个字段同C接口中pMsgPropery中各个字段 ，data为真正的数据 

public native static byte[] MrReceive1(String sAppID,String SourceUserID,String SourceAppID, String DestUserID, String DestAppID,
String PkgID, String CorrPkgID, String UserData1, String UserData2, String ExpiredAbsTime, byte flag, byte Protocltype,int iMillSecTimeo);



/* 判断与交换中枢的连接是否正常
 * return: 0-link_not_ok; 1-link_ok
   参数为mrinit中设置的app
 */
public native static int MrIsLinkOK(String sAppID);

/*释放资源
  参数为mrinit中设置的app*/
public native static void MrDestroy(String sAppID);


/********************************* FDEP V4 **********************************/

/* 初始化，返回为整形
 * return: 0-failed; 1-OK
   App和AppPwd对应c接口中的AppId和AppPwd， 后四个参数对应C接口中的结构体oConnInfo中各个字段
 */
public native static int Mr2Init(String App,String AppPwd, String Ip,short Port,String Ipbak, short Portbak);



/* 发送消息 ，参数中SourceAppID填初始化的时候用的App
 * return:    ""-发送失败   pkgId-发送成功  
   psPkg中是要发送的数据，其余字段同C接口中pMsgPropery中各个字段 
 */

public native static String Mr2Send( byte[] psPkg, String SourceUserID,String SourceAppID, String DestUserID, String DestAppID,
String PkgID, String CorrPkgID, String UserData1, String UserData2, String MsgType, byte flag, byte BizType, byte Priority, byte SensitiveLevel, int iMillSecTimeo);




//return "NULL"-接收失败    否则返回pkID(64byte) + CorrpkID(64byte) + sourceUserID(64byte) + sourceAppID(64byte) +destUserID(64byte) + UserData1(256byte) + UserData2(256byte) + data     上述前7个字段同C接口中pMsgPropery中各个字段 ，data为真正的数据 

public native static byte[] Mr2Receive1(String sAppID,String SourceUserID,String SourceAppID, String DestUserID, String DestAppID,
String PkgID, String CorrPkgID, String UserData1, String UserData2, int iMillSecTimeo);



/* 判断与交换中枢的连接是否正常
 * return: 0-link_not_ok; 1-link_ok
   参数为mrinit中设置的app
 */
public native static int Mr2IsLinkOK(String sAppID);

/*释放资源
  参数为mrinit中设置的app*/
public native static void Mr2Destroy(String sAppID);


/* 获取版本信息
 * return 版本号字符串
 */
public native static byte[]  Mr2GetVersion();


/* 创建包ID
 * return PkgID
 */
public native static byte[]  Mr2CreatePkgID(String szHandleAppID);

/* 获取对端用户是否在线状态
/ return: -1:函数调用失败  0:不在线    1:在线
*/
public native static int  Mr2GetPeerUserStat(String szHandleAppID,String szPeerUserID);


/* 调用注册条件函数
 * return: 0：注册成功,<0其它失败
 */
public native static int  Mr2RegRecvCondition(String szHandleAppId,String szSrcUserId, String szSrcAppId, String szDestUserId, String szDestAppId, 
String szPkgId, String szCorrPkgId, String szUserData1,String szUserData2) ;


///  "NULL，errmsg"-接收失败   否则返回errcode(4byte)+pkID(64byte) + CorrpkID(64byte) + sourceUserID(32byte) + sourceAppID(32byte) +destUserID(32byte) + destAppID(32byte)+UserData1(256byte) + UserData2(256byte) + data     上述前7个字段同C接口中pMsgPropery中各个字段 ，data为真正的数据 
///   其中errcode= "0000"为接收正常，如果errcode为非"0000"字符串，则由系统返回的错误信息，详细错误在data字段中； 
public native static byte[] Mr2Receive3(String sAppID,String SourceUserID,String SourceAppID, String DestUserID, String DestAppID,
String PkgID, String CorrPkgID, String UserData1, String UserData2, int iMillSecTimeo);


}
