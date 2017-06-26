/*
 * Copyright (c) 2017 Warrior Dragon Contributors
 * This program is made available under the terms of the MIT License.
 */

package vn.lequan.warrior.config.module;


import dagger.Module;
import dagger.Provides;
import vn.lequan.warrior.entity.Template;
import vn.lequan.warrior.entity.items.CoinTemplate;
import vn.lequan.warrior.entity.monsters.JiangshiTemplate;
import vn.lequan.warrior.entity.player.PlayerTemplate;
import vn.lequan.warrior.entity.projectiles.PowerBlastTemplate;
import vn.lequan.warrior.entity.projectiles.SoulBreakerTemplate;
import vn.lequan.warrior.util.App;

@Module
public class TemplateModule {

    // don't use reflection here because ProGuard removes class file attributes
    private static final int POWER_BLAST = 1;
    private static final int COIN = 2;
    private static final int PLAYER = 3;
    private static final int JIANGSHI = 4;
    private static final int SOUL_BREAKER = 5;

    @Provides
    PowerBlastTemplate providePowerBlastTemplate(App app) {
        Template template = app.getTemplate(POWER_BLAST);

        if (template == null) {
            template = new PowerBlastTemplate(app);
            app.putTemplate(POWER_BLAST, template);
        }

        return (PowerBlastTemplate) template;
    }

    @Provides
    SoulBreakerTemplate provideSoulBreakerTemplate(App app) {
        Template template = app.getTemplate(SOUL_BREAKER);

        if (template == null) {
            template = new SoulBreakerTemplate(app);
            app.putTemplate(SOUL_BREAKER, template);
        }

        return (SoulBreakerTemplate) template;
    }

    @Provides
    CoinTemplate provideCoinTemplate(App app) {
        Template template = app.getTemplate(COIN);

        if (template == null) {
            template = new CoinTemplate(app);
            app.putTemplate(COIN, template);
        }

        return (CoinTemplate) template;
    }

    @Provides
    PlayerTemplate providePlayerTemplate(App app) {
        Template template = app.getTemplate(PLAYER);

        if (template == null) {
            template = new PlayerTemplate(app);
            app.putTemplate(PLAYER, template);
        }

        return (PlayerTemplate) template;
    }

    @Provides
    JiangshiTemplate provideJiangshiTemplate(App app) {
        Template template = app.getTemplate(JIANGSHI);

        if (template == null) {
            template = new JiangshiTemplate(app);
            app.putTemplate(JIANGSHI, template);
        }

        return (JiangshiTemplate) template;
    }

}
