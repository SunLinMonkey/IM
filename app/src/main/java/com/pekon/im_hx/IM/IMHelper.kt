package com.pekon.im_hx.IM

import android.content.Context
import android.text.TextUtils
import android.util.Log
import com.hyphenate.chat.EMOptions
import com.hyphenate.easeui.EaseIM
import com.hyphenate.push.EMPushConfig

/**
 * IM 作为hyphenate-sdk的入口控制类，获取sdk下的基础类均通过此类
 */
class IMHelper {

    val TAG = "IM通讯"
    private var isSDKInit: Boolean = false;//SDK是否初始化

    companion object {
        val instance: IMHelper by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            IMHelper()
        }
    }


    //防止单例对象在反序列化时重新生成对象
    private fun readResolve(): Any {
        return IMHelper.instance
    }


    private fun initSdk(context: Context): Boolean {
//        // 根据项目需求对SDK进行配置
//        EMOptions options = initChatOptions(context);
        val options: EMOptions = initChatOptions(context);
        //配置自定义的rest server和im server
        //options.setRestServer("a1-hsb.easemob.com");
        //options.setIMServer("106.75.100.247");
        //options.setImPort(6717);
        // 初始化SDK
        isSDKInit = EaseIM.getInstance().init(context, options)
        //设置删除用户属性数据超时时间
        //设置删除用户属性数据超时时间
//        demoModel.setUserInfoTimeOut(30 * 60 * 1000)
//        //更新过期用户属性列表
//        //更新过期用户属性列表
//        updateTimeoutUsers()
//        mianContext = context
        return isSDKInit()


    }

    fun isSDKInit(): Boolean {
        return isSDKInit
    }


    private fun initChatOptions(context: Context): EMOptions {

        Log.e(TAG, "init HuanXin Options")

        val options = EMOptions()
        // 设置是否自动接受加好友邀请,默认是true
        options.acceptInvitationAlways = false
        // 设置是否需要接受方已读确认
        options.requireAck = true
        // 设置是否需要接受方送达确认,默认false
        options.requireDeliveryAck = false

        options.useRtcConfig = true


        // 设置是否使用 fcm，有些华为设备本身带有 google 服务，
//        options.isUseFCM = demoModel.isUseFCM()

        /**
         * NOTE:你需要设置自己申请的账号来使用三方推送功能，详见集成文档
         */
        /**
         * NOTE:你需要设置自己申请的账号来使用三方推送功能，详见集成文档
         */
        val builder = EMPushConfig.Builder(context)

        builder.enableVivoPush() // 需要在AndroidManifest.xml中配置appId和appKey
            .enableMeiZuPush("134952", "f00e7e8499a549e09731a60a4da399e3")
            .enableMiPush("2882303761517426801", "5381742660801")
            .enableOppoPush(
                "0bb597c5e9234f3ab9f821adbeceecdb",
                "cd93056d03e1418eaa6c3faf10fd7537"
            )
            .enableHWPush() // 需要在AndroidManifest.xml中配置appId
            .enableFCM("921300338324")
        options.pushConfig = builder.build()


        //set custom servers, commonly used in private deployment
//        if (demoModel.isCustomSetEnable()) {
//            if (demoModel.isCustomServerEnable() && demoModel.getRestServer() != null && demoModel.getIMServer() != null) {
//                // 设置rest server地址
//                options.restServer = demoModel.getRestServer()
//                // 设置im server地址
//                options.setIMServer(demoModel.getIMServer())
//                //如果im server地址中包含端口号
//                if (demoModel.getIMServer().contains(":")) {
//                    options.setIMServer(demoModel.getIMServer().split(":").get(0))
//                    // 设置im server 端口号，默认443
//                    options.imPort = Integer.valueOf(demoModel.getIMServer().split(":").get(1))
//                } else {
//                    //如果不包含端口号
//                    if (demoModel.getIMServerPort() !== 0) {
//                        options.imPort = demoModel.getIMServerPort()
//                    }
//                }
//            }
//        }
//        if (demoModel.isCustomAppkeyEnabled() && !TextUtils.isEmpty(demoModel.getCutomAppkey())) {
//            // 设置appkey
//            options.appKey = demoModel.getCutomAppkey()
//        }

        val imServer = options.imServer
        val restServer = options.restServer


        // 设置是否允许聊天室owner离开并删除会话记录，意味着owner再不会受到任何消息
//        options.allowChatroomOwnerLeave(demoModel.isChatroomOwnerLeaveAllowed())
//        // 设置退出(主动和被动退出)群组时是否删除聊天消息
//        options.isDeleteMessagesAsExitGroup = demoModel.isDeleteMessagesAsExitGroup()
//        // 设置是否自动接受加群邀请
//        // 设置是否自动接受加群邀请
//        options.isAutoAcceptGroupInvitation = demoModel.isAutoAcceptGroupInvitation()
//        // 是否自动将消息附件上传到环信服务器，默认为True是使用环信服务器上传下载
//        options.autoTransferMessageAttachments = demoModel.isSetTransferFileByUser()
//        // 是否自动下载缩略图，默认是true为自动下载
//        options.setAutoDownloadThumbnail(demoModel.isSetAutodownloadThumbnail())
        return options
    }

}