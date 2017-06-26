package vn.lequan.warrior.config.module;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.Generated;
import javax.inject.Provider;
import vn.lequan.warrior.entity.items.Coin;
import vn.lequan.warrior.entity.items.CoinTemplate;
import vn.lequan.warrior.util.App;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class ItemModule_ProvideCoinFactory implements Factory<Coin> {
  private final ItemModule module;

  private final Provider<App> appProvider;

  private final Provider<CoinTemplate> templateProvider;

  public ItemModule_ProvideCoinFactory(
      ItemModule module, Provider<App> appProvider, Provider<CoinTemplate> templateProvider) {
    assert module != null;
    this.module = module;
    assert appProvider != null;
    this.appProvider = appProvider;
    assert templateProvider != null;
    this.templateProvider = templateProvider;
  }

  @Override
  public Coin get() {
    return Preconditions.checkNotNull(
        module.provideCoin(appProvider.get(), templateProvider.get()),
        "Cannot return null from a non-@Nullable @Provides method");
  }

  public static Factory<Coin> create(
      ItemModule module, Provider<App> appProvider, Provider<CoinTemplate> templateProvider) {
    return new ItemModule_ProvideCoinFactory(module, appProvider, templateProvider);
  }

  /** Proxies {@link ItemModule#provideCoin(App, CoinTemplate)}. */
  public static Coin proxyProvideCoin(ItemModule instance, App app, CoinTemplate template) {
    return instance.provideCoin(app, template);
  }
}
