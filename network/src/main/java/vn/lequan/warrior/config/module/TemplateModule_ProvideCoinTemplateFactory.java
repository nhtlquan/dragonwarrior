package vn.lequan.warrior.config.module;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.Generated;
import javax.inject.Provider;
import vn.lequan.warrior.entity.items.CoinTemplate;
import vn.lequan.warrior.util.App;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class TemplateModule_ProvideCoinTemplateFactory implements Factory<CoinTemplate> {
  private final TemplateModule module;

  private final Provider<App> appProvider;

  public TemplateModule_ProvideCoinTemplateFactory(
      TemplateModule module, Provider<App> appProvider) {
    assert module != null;
    this.module = module;
    assert appProvider != null;
    this.appProvider = appProvider;
  }

  @Override
  public CoinTemplate get() {
    return Preconditions.checkNotNull(
        module.provideCoinTemplate(appProvider.get()),
        "Cannot return null from a non-@Nullable @Provides method");
  }

  public static Factory<CoinTemplate> create(TemplateModule module, Provider<App> appProvider) {
    return new TemplateModule_ProvideCoinTemplateFactory(module, appProvider);
  }

  /** Proxies {@link TemplateModule#provideCoinTemplate(App)}. */
  public static CoinTemplate proxyProvideCoinTemplate(TemplateModule instance, App app) {
    return instance.provideCoinTemplate(app);
  }
}
