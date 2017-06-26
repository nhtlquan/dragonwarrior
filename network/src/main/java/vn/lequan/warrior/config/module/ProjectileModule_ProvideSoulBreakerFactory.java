package vn.lequan.warrior.config.module;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.Generated;
import javax.inject.Provider;
import vn.lequan.warrior.entity.projectiles.SoulBreaker;
import vn.lequan.warrior.entity.projectiles.SoulBreakerTemplate;
import vn.lequan.warrior.util.App;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class ProjectileModule_ProvideSoulBreakerFactory implements Factory<SoulBreaker> {
  private final ProjectileModule module;

  private final Provider<App> appProvider;

  private final Provider<SoulBreakerTemplate> templateProvider;

  public ProjectileModule_ProvideSoulBreakerFactory(
      ProjectileModule module,
      Provider<App> appProvider,
      Provider<SoulBreakerTemplate> templateProvider) {
    assert module != null;
    this.module = module;
    assert appProvider != null;
    this.appProvider = appProvider;
    assert templateProvider != null;
    this.templateProvider = templateProvider;
  }

  @Override
  public SoulBreaker get() {
    return Preconditions.checkNotNull(
        module.provideSoulBreaker(appProvider.get(), templateProvider.get()),
        "Cannot return null from a non-@Nullable @Provides method");
  }

  public static Factory<SoulBreaker> create(
      ProjectileModule module,
      Provider<App> appProvider,
      Provider<SoulBreakerTemplate> templateProvider) {
    return new ProjectileModule_ProvideSoulBreakerFactory(module, appProvider, templateProvider);
  }

  /** Proxies {@link ProjectileModule#provideSoulBreaker(App, SoulBreakerTemplate)}. */
  public static SoulBreaker proxyProvideSoulBreaker(
      ProjectileModule instance, App app, SoulBreakerTemplate template) {
    return instance.provideSoulBreaker(app, template);
  }
}
