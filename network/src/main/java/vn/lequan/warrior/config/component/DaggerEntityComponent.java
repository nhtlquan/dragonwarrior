package vn.lequan.warrior.config.component;

import dagger.internal.DoubleCheck;
import dagger.internal.Preconditions;
import javax.annotation.Generated;
import javax.inject.Provider;
import vn.lequan.warrior.config.module.AppModule;
import vn.lequan.warrior.config.module.AppModule_ProvideAppFactory;
import vn.lequan.warrior.config.module.CharacterModule;
import vn.lequan.warrior.config.module.CharacterModule_ProvideJiangshiFactory;
import vn.lequan.warrior.config.module.CharacterModule_ProvidePlayerFactory;
import vn.lequan.warrior.config.module.ItemModule;
import vn.lequan.warrior.config.module.ItemModule_ProvideCoinFactory;
import vn.lequan.warrior.config.module.ProjectileModule;
import vn.lequan.warrior.config.module.ProjectileModule_ProvidePowerBlastFactory;
import vn.lequan.warrior.config.module.ProjectileModule_ProvideSoulBreakerFactory;
import vn.lequan.warrior.config.module.TemplateModule;
import vn.lequan.warrior.config.module.TemplateModule_ProvideCoinTemplateFactory;
import vn.lequan.warrior.config.module.TemplateModule_ProvideJiangshiTemplateFactory;
import vn.lequan.warrior.config.module.TemplateModule_ProvidePlayerTemplateFactory;
import vn.lequan.warrior.config.module.TemplateModule_ProvidePowerBlastTemplateFactory;
import vn.lequan.warrior.config.module.TemplateModule_ProvideSoulBreakerTemplateFactory;
import vn.lequan.warrior.entity.items.Coin;
import vn.lequan.warrior.entity.items.CoinTemplate;
import vn.lequan.warrior.entity.monsters.Jiangshi;
import vn.lequan.warrior.entity.monsters.JiangshiTemplate;
import vn.lequan.warrior.entity.player.Player;
import vn.lequan.warrior.entity.player.PlayerTemplate;
import vn.lequan.warrior.entity.projectiles.PowerBlast;
import vn.lequan.warrior.entity.projectiles.PowerBlastTemplate;
import vn.lequan.warrior.entity.projectiles.SoulBreaker;
import vn.lequan.warrior.entity.projectiles.SoulBreakerTemplate;
import vn.lequan.warrior.util.App;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class DaggerEntityComponent implements EntityComponent {
  private Provider<App> provideAppProvider;

  private Provider<CoinTemplate> provideCoinTemplateProvider;

  private Provider<Coin> provideCoinProvider;

  private Provider<PowerBlastTemplate> providePowerBlastTemplateProvider;

  private Provider<PowerBlast> providePowerBlastProvider;

  private Provider<SoulBreakerTemplate> provideSoulBreakerTemplateProvider;

  private Provider<SoulBreaker> provideSoulBreakerProvider;

  private Provider<PlayerTemplate> providePlayerTemplateProvider;

  private Provider<Player> providePlayerProvider;

  private Provider<JiangshiTemplate> provideJiangshiTemplateProvider;

  private Provider<Jiangshi> provideJiangshiProvider;

  private DaggerEntityComponent(Builder builder) {
    assert builder != null;
    initialize(builder);
  }

  public static Builder builder() {
    return new Builder();
  }

  public static EntityComponent create() {
    return new Builder().build();
  }

  @SuppressWarnings("unchecked")
  private void initialize(final Builder builder) {

    this.provideAppProvider =
        DoubleCheck.provider(AppModule_ProvideAppFactory.create(builder.appModule));

    this.provideCoinTemplateProvider =
        TemplateModule_ProvideCoinTemplateFactory.create(
            builder.templateModule, provideAppProvider);

    this.provideCoinProvider =
        ItemModule_ProvideCoinFactory.create(
            builder.itemModule, provideAppProvider, provideCoinTemplateProvider);

    this.providePowerBlastTemplateProvider =
        TemplateModule_ProvidePowerBlastTemplateFactory.create(
            builder.templateModule, provideAppProvider);

    this.providePowerBlastProvider =
        ProjectileModule_ProvidePowerBlastFactory.create(
            builder.projectileModule, provideAppProvider, providePowerBlastTemplateProvider);

    this.provideSoulBreakerTemplateProvider =
        TemplateModule_ProvideSoulBreakerTemplateFactory.create(
            builder.templateModule, provideAppProvider);

    this.provideSoulBreakerProvider =
        ProjectileModule_ProvideSoulBreakerFactory.create(
            builder.projectileModule, provideAppProvider, provideSoulBreakerTemplateProvider);

    this.providePlayerTemplateProvider =
        TemplateModule_ProvidePlayerTemplateFactory.create(
            builder.templateModule, provideAppProvider);

    this.providePlayerProvider =
        CharacterModule_ProvidePlayerFactory.create(
            builder.characterModule, provideAppProvider, providePlayerTemplateProvider);

    this.provideJiangshiTemplateProvider =
        TemplateModule_ProvideJiangshiTemplateFactory.create(
            builder.templateModule, provideAppProvider);

    this.provideJiangshiProvider =
        CharacterModule_ProvideJiangshiFactory.create(
            builder.characterModule, provideAppProvider, provideJiangshiTemplateProvider);
  }

  @Override
  public Coin provideCoin() {
    return provideCoinProvider.get();
  }

  @Override
  public PowerBlast providePowerBlast() {
    return providePowerBlastProvider.get();
  }

  @Override
  public SoulBreaker provideSoulBreaker() {
    return provideSoulBreakerProvider.get();
  }

  @Override
  public Player providePlayer() {
    return providePlayerProvider.get();
  }

  @Override
  public Jiangshi provideJiangshi() {
    return provideJiangshiProvider.get();
  }

  public static final class Builder {
    private AppModule appModule;

    private TemplateModule templateModule;

    private ItemModule itemModule;

    private ProjectileModule projectileModule;

    private CharacterModule characterModule;

    private Builder() {}

    public EntityComponent build() {
      if (appModule == null) {
        this.appModule = new AppModule();
      }
      if (templateModule == null) {
        this.templateModule = new TemplateModule();
      }
      if (itemModule == null) {
        this.itemModule = new ItemModule();
      }
      if (projectileModule == null) {
        this.projectileModule = new ProjectileModule();
      }
      if (characterModule == null) {
        this.characterModule = new CharacterModule();
      }
      return new DaggerEntityComponent(this);
    }

    public Builder appModule(AppModule appModule) {
      this.appModule = Preconditions.checkNotNull(appModule);
      return this;
    }

    public Builder itemModule(ItemModule itemModule) {
      this.itemModule = Preconditions.checkNotNull(itemModule);
      return this;
    }

    public Builder projectileModule(ProjectileModule projectileModule) {
      this.projectileModule = Preconditions.checkNotNull(projectileModule);
      return this;
    }

    public Builder characterModule(CharacterModule characterModule) {
      this.characterModule = Preconditions.checkNotNull(characterModule);
      return this;
    }

    public Builder templateModule(TemplateModule templateModule) {
      this.templateModule = Preconditions.checkNotNull(templateModule);
      return this;
    }
  }
}
