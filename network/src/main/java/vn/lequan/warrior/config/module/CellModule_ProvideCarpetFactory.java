package vn.lequan.warrior.config.module;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.Generated;
import javax.inject.Provider;
import vn.lequan.warrior.map.cells.Carpet;
import vn.lequan.warrior.util.App;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class CellModule_ProvideCarpetFactory implements Factory<Carpet> {
  private final CellModule module;

  private final Provider<App> appProvider;

  public CellModule_ProvideCarpetFactory(CellModule module, Provider<App> appProvider) {
    assert module != null;
    this.module = module;
    assert appProvider != null;
    this.appProvider = appProvider;
  }

  @Override
  public Carpet get() {
    return Preconditions.checkNotNull(
        module.provideCarpet(appProvider.get()),
        "Cannot return null from a non-@Nullable @Provides method");
  }

  public static Factory<Carpet> create(CellModule module, Provider<App> appProvider) {
    return new CellModule_ProvideCarpetFactory(module, appProvider);
  }

  /** Proxies {@link CellModule#provideCarpet(App)}. */
  public static Carpet proxyProvideCarpet(CellModule instance, App app) {
    return instance.provideCarpet(app);
  }
}
