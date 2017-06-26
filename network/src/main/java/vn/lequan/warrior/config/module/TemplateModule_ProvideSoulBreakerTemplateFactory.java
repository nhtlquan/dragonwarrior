package vn.lequan.warrior.config.module;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.Generated;
import javax.inject.Provider;
import vn.lequan.warrior.entity.projectiles.SoulBreakerTemplate;
import vn.lequan.warrior.util.App;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class TemplateModule_ProvideSoulBreakerTemplateFactory
    implements Factory<SoulBreakerTemplate> {
  private final TemplateModule module;

  private final Provider<App> appProvider;

  public TemplateModule_ProvideSoulBreakerTemplateFactory(
      TemplateModule module, Provider<App> appProvider) {
    assert module != null;
    this.module = module;
    assert appProvider != null;
    this.appProvider = appProvider;
  }

  @Override
  public SoulBreakerTemplate get() {
    return Preconditions.checkNotNull(
        module.provideSoulBreakerTemplate(appProvider.get()),
        "Cannot return null from a non-@Nullable @Provides method");
  }

  public static Factory<SoulBreakerTemplate> create(
      TemplateModule module, Provider<App> appProvider) {
    return new TemplateModule_ProvideSoulBreakerTemplateFactory(module, appProvider);
  }

  /** Proxies {@link TemplateModule#provideSoulBreakerTemplate(App)}. */
  public static SoulBreakerTemplate proxyProvideSoulBreakerTemplate(
      TemplateModule instance, App app) {
    return instance.provideSoulBreakerTemplate(app);
  }
}
