package wechat.qiye;

import org.junit.Test;
import wechat.common.entity.BaseParamsEntity;
import wechat.common.utils.AccessTokenUtils;

public class AccessToeknTest {

    @Test
    public void getAccessToken(){
        BaseParamsEntity baseParamsEntity = new BaseParamsEntity();
        baseParamsEntity.setCorpId("");
        baseParamsEntity.setSecret("");
        String accessToken = AccessTokenUtils.getAccessToken(baseParamsEntity);
        System.out.println(accessToken);
    }
}
