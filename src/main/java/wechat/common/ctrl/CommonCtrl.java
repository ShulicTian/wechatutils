package wechat.common.ctrl;

import wechat.common.constant.BaseUrlConstant;
import wechat.mini.ctrl.MiniCtrl;
import wechat.mini.entity.MiniParamsEntity;
import wechat.qiye.addressbook.ctrl.DepartmentCtrl;
import wechat.qiye.addressbook.ctrl.PersonnelCtrl;
import wechat.qiye.auth.ctrl.LoginAuthCtrl;
import wechat.qiye.common.entity.QiYeParamsEntity;
import wechat.qiye.message.ctrl.MessageCtrl;
import wechat.weixin.ctrl.WebCtrl;
import wechat.weixin.ctrl.WeiXinCtrl;
import wechat.weixin.entity.WebParamsEntity;
import wechat.weixin.entity.WeiXinParamsEntity;

/**
 * @author tianslc
 * @date 2024/3/29
 */
public class CommonCtrl {

    private QiYeParamsEntity qiYeParamsEntity;

    private MiniParamsEntity miniParamsEntity;

    private WeiXinParamsEntity weiXinParamsEntity;

    private WebParamsEntity webParamsEntity;

    private CommonCtrl(QiYeParamsEntity qiYeParamsEntity) {
        this.qiYeParamsEntity = qiYeParamsEntity;
    }

    private CommonCtrl(MiniParamsEntity miniParamsEntity) {
        this.miniParamsEntity = miniParamsEntity;
    }

    private CommonCtrl(WeiXinParamsEntity weiXinParamsEntity) {
        this.weiXinParamsEntity = weiXinParamsEntity;
    }

    private CommonCtrl(WebParamsEntity webParamsEntity) {
        this.webParamsEntity = webParamsEntity;
    }

    public static CommonCtrl registerQiYeService(QiYeParamsEntity entity) {
        return new CommonCtrl(entity);
    }

    public static CommonCtrl registerMiniService(MiniParamsEntity entity) {
        return new CommonCtrl(entity);
    }

    public static CommonCtrl registerWeiXinService(WeiXinParamsEntity entity) {
        return new CommonCtrl(entity);
    }

    public static CommonCtrl registerWebService(WebParamsEntity entity) {
        return new CommonCtrl(entity);
    }

    public DepartmentCtrl getQiYeDeptCtrl() {
        return qiYeParamsEntity != null ? new DepartmentCtrl(qiYeParamsEntity) : null;
    }

    public PersonnelCtrl getQiYePersonnelCtrl() {
        return qiYeParamsEntity != null ? new PersonnelCtrl(qiYeParamsEntity) : null;
    }

    public LoginAuthCtrl getQiYeLoginAuthCtrl() {
        return qiYeParamsEntity != null ? new LoginAuthCtrl(qiYeParamsEntity) : null;
    }

    public MessageCtrl getQiYeMessageCtrl() {
        return qiYeParamsEntity != null ? new MessageCtrl(qiYeParamsEntity) : null;
    }

    public MiniCtrl getMiniCtrl() {
        return miniParamsEntity != null ? new MiniCtrl(miniParamsEntity) : null;
    }

    public WebCtrl getWebCtrl() {
        return webParamsEntity != null ? new WebCtrl(webParamsEntity) : null;
    }

    public WeiXinCtrl getWeiXinCtrl() {
        return weiXinParamsEntity != null ? new WeiXinCtrl(weiXinParamsEntity) : null;
    }

    /**
     * 拼接OAuth2 Url
     *
     * @param redirectUrl
     * @param state
     * @return
     */
    public static String getAuthUrl(String redirectUrl, String appId, String state, String scope) {
        return BaseUrlConstant.WX_OAUTH2.replace("APPID", appId).
                replace("REDIRECT_URI", redirectUrl).
                replace("STATE", state).
                replace("SCOPE", scope);
    }
}
