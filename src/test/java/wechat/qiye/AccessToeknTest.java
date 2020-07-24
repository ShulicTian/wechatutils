package wechat.qiye;

import org.junit.Test;
import wechat.qiye.entity.BaseParamsEntity;
import wechat.qiye.utils.AccessTokenUtils;

public class AccessToeknTest {

    @Test
    public void getAccessToken(){
        BaseParamsEntity baseParamsEntity = new BaseParamsEntity();
        baseParamsEntity.setCorpId("wx77ed669a36b7bb9a");
        baseParamsEntity.setSecret("oa82wVGwu2fyRZUiv9x0QNnAaWcY_9uPM39DdbBzMuk");
        String accessToken = AccessTokenUtils.getAccessToken(baseParamsEntity);
        System.out.println(accessToken);
    }
}
